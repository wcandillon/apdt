package org.phpaspect.apdt.internal.core.visitor;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.php.internal.core.PHPVersion;
import org.eclipse.php.internal.core.ast.nodes.*;
import org.eclipse.php.internal.core.ast.rewrite.ASTRewrite;
import org.eclipse.php.internal.core.ast.visitor.AbstractVisitor;
import org.eclipse.text.edits.TextEdit;
import org.phpaspect.apdt.core.weaver.*;
import org.phpaspect.apdt.internal.core.weaver.joinpoints.ConstructorJoinpoint;
import org.phpaspect.apdt.internal.core.weaver.joinpoints.MethodInvocationJoinpoint;
import org.phpaspect.internal.core.weaver.SourceLocationImpl;

public class WeaverVisitor extends AbstractVisitor{

	protected List<Pointcut> pointcuts;
	protected List<Mixin> mixins = new LinkedList<Mixin>();

	private ASTParser parser;
	private Program program;
	private AST ast;
	private ASTRewrite rewriter;
	private IDocument document = new Document();
	private String fileName;
	
	public WeaverVisitor(IFile file, List<Pointcut> pointcuts) throws Exception {
		this.fileName = file.getName();
		this.pointcuts = pointcuts;
	    StringBuffer source = new StringBuffer();
	    InputStreamReader in = new InputStreamReader(file.getContents());
	    while(in.ready()){
	    	source.append((char)in.read());
	    }
	    document.set(source.toString());
		parser = ASTParser.newParser(new InputStreamReader(file.getContents()), PHPVersion.PHP5, false, DLTKCore.createSourceModuleFrom(file));
		program = parser.createAST(new NullProgressMonitor());
		ast = program.getAST();
		rewriter = ASTRewrite.create(ast);
	}
	
	private SourceLocation getSourceLocation(ASTNode node){
		return new SourceLocationImpl(fileName, getWithinType(node));
	}
	
	private String getWithinType(ASTNode node){
		ASTNode enclosingNode = node.getEnclosingBodyNode();
		if(enclosingNode instanceof Program){
			return "Main";
		} else if(enclosingNode instanceof TypeDeclaration){
			TypeDeclaration typeDeclaration = (TypeDeclaration)enclosingNode;
			return typeDeclaration.getName().getName();
		}
		return getWithinType(enclosingNode);
	}

	public WeaverVisitor addPointcut(Pointcut pointcut){
		pointcuts.add(pointcut);
		return this;
	}
	
	public WeaverVisitor addPointcuts(List<Pointcut> pointcuts)
	{
		this.pointcuts.addAll(pointcuts);
		return this;
	}
	
	public WeaverVisitor addMixin(Mixin mixin){
		mixins.add(mixin);
		return this;
	}
	
	public String weave(){
		program.accept(this);
		Map<String, String> options = new HashMap<String, String>();
		TextEdit textEdit = rewriter.rewriteAST(document, options);
		try {
			textEdit.apply(document);
		} catch (BadLocationException e) {
			System.err.println("Couldn't weave the source code.");
			System.err.println("Reason: "+e.getMessage());
		}
		return document.get();
	}
	
	private Pair<List<ArrayElement>, List<ArrayElement>> getRuntimePredicates(Joinpoint joinpoint)
	{	
		//If a pointcut is matched we store its id
		List<ArrayElement> ids = new LinkedList<ArrayElement>();
		//Foreach id, we store the corresponding runtime assertion
	    List<ArrayElement> runtimePredicates = new LinkedList<ArrayElement>();
		for(Pointcut pt: pointcuts){
			if(pt.match(ast, joinpoint)){
				ArrayElement e = ast.newArrayElement();
				e.setValue(ast.newScalar(String.valueOf(pt.getName()), Scalar.TYPE_STRING));
				ids.add(e);
				Expression assertion = pt.getRuntimeAssertion();
				if(assertion != null){
					ArrayElement element = ast.newArrayElement();
					element.setKey(ast.newScalar(String.valueOf(pt.getName()), Scalar.TYPE_STRING));
					element.setValue(assertion);
					runtimePredicates.add(element);
				}
			}
		}
		Pair<List<ArrayElement>, List<ArrayElement>> pair =
			new Pair<List<ArrayElement>, List<ArrayElement>>(ids, runtimePredicates);
		return pair;
	}
	
	public FunctionInvocation getDispatchCall(List<ArrayElement> ids,
			Joinpoint.Kind kind, Expression source, Expression target,
			List<ArrayElement> arguments, List<ArrayElement> runtimePredicates)
	{ 
		List<Expression> args = new LinkedList<Expression>();
		args.add(ast.newArrayCreation(ids));
		List<Expression> jpArgs = new LinkedList<Expression>();
		jpArgs.add(source);
		jpArgs.add(target);
		jpArgs.add(ast.newArrayCreation(arguments));
		jpArgs.add(ast.newScalar("__FILE__"));
		jpArgs.add(ast.newScalar("__LINE__"));
		args.add(ast.newClassInstanceCreation(ast.newClassName(ast.newIdentifier(kind.getName())), jpArgs));
		if(runtimePredicates.size() > 0){
			args.add(ast.newArrayCreation(runtimePredicates));
		}
		return ast.newFunctionInvocation(
				ast.newFunctionName(
						ast.newIdentifier("dispatch")), args);
	}
	
	@Override
	public void endVisit(Program program){ 
		//List of the includes for PHPAspect Runtime
		List<Statement> includes = new  LinkedList<Statement>();
		//TODO: include runtime aspects
		//require_once 'Runtime/Dispatcher.php';
		Expression dispatcherFile = ast.newScalar("'PHPAspect/Dispatcher.php'");
	    includes.add(ast.newExpressionStatement(ast.newInclude(dispatcherFile, Include.IT_REQUIRE_ONCE)));
	    //Add the includes in front of the statement list
	    List<Statement> statements = program.statements();
	    Statement firstStatement = ASTNode.copySubtree(ast, statements.get(0));
	    includes.add(firstStatement);
		Block PHPAspectRuntimeImports = ast.newBlock(includes);
	    rewriter.replace(statements.get(0), PHPAspectRuntimeImports, null);
	}
	
	@Override
	public void endVisit(ClassInstanceCreation instanceCreation)
	{
		//Create a joinpoint
		SourceLocation sourceLocation = getSourceLocation(instanceCreation);
		Joinpoint joinpoint = new ConstructorJoinpoint(sourceLocation, instanceCreation);
		//If a pointcut is matched we store its id
		List<ArrayElement> ids = new LinkedList<ArrayElement>();
		//Foreach id, we store the corresponding runtime assertion
	    List<ArrayElement> runtimePredicates = new LinkedList<ArrayElement>();
		for(Pointcut pt: pointcuts){
			if(pt.match(ast, joinpoint)){
				ArrayElement e = ast.newArrayElement();
				e.setValue(ast.newScalar(String.valueOf(pt.getName()), Scalar.TYPE_STRING));
				ids.add(e);
				Expression assertion = pt.getRuntimeAssertion();
				if(assertion != null){
					ArrayElement element = ast.newArrayElement();
					element.setKey(ast.newScalar(String.valueOf(pt.getName()), Scalar.TYPE_STRING));
					element.setValue(assertion);
					runtimePredicates.add(element);
				}
			}
		}
		//If at least one pointcut has been matched
		if(ids.size() > 0){
			AST ast = program.getAST();
			List<Expression> args = new LinkedList<Expression>();
			args.add(ast.newArrayCreation(ids));
			List<Expression> jpArgs = new LinkedList<Expression>();
		}
	}
	
	@Override
	public void endVisit(MethodInvocation methodInvocation) {
		//Create a joinpoint
		SourceLocation sourceLocation = getSourceLocation(methodInvocation);
		Joinpoint joinpoint = new MethodInvocationJoinpoint(sourceLocation, methodInvocation);
		Pair<List<ArrayElement>, List<ArrayElement>> pair = getRuntimePredicates(joinpoint);
		List<ArrayElement> ids = pair.getIndex();
	    List<ArrayElement> runtimePredicates = pair.getValue();
		//If at least one pointcut has been matched
		if(ids.size() > 0){
			//Object name
			VariableBase dispatcher = ASTNode.copySubtree(ast, methodInvocation.getDispatcher());
			//Method name
			Variable var = (Variable)methodInvocation.getMethod().getFunctionName().getName();
			Identifier methodIdentifier = (Identifier)var.getName();
			String methodName = "'"+methodIdentifier.getName()+"'";
			Scalar target = ast.newScalar(methodName, Scalar.TYPE_STRING);
			//Arguments
			List<ArrayElement> arguments = new LinkedList<ArrayElement>();
			List<Expression> parameters = methodInvocation.getMethod().parameters();
			for(Expression param: parameters){
			    ArrayElement e = ast.newArrayElement();
			    e.setValue(ASTNode.copySubtree(ast, param));
				arguments.add(e);
			}
			FunctionInvocation inv = getDispatchCall(ids, Joinpoint.Kind.METHOD_CALL, dispatcher, target, arguments, runtimePredicates);
			rewriter.replace(methodInvocation, inv, null);
		}
	}
}
