package org.phpaspect.weaver.visitor;

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
import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ASTParser;
import org.eclipse.php.internal.core.ast.nodes.ArrayElement;
import org.eclipse.php.internal.core.ast.nodes.Block;
import org.eclipse.php.internal.core.ast.nodes.ClassInstanceCreation;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.FunctionInvocation;
import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.nodes.Include;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.eclipse.php.internal.core.ast.nodes.Program;
import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.eclipse.php.internal.core.ast.nodes.TypeDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.eclipse.php.internal.core.ast.nodes.VariableBase;
import org.eclipse.php.internal.core.ast.rewrite.ASTRewrite;
import org.eclipse.php.internal.core.ast.visitor.AbstractVisitor;
import org.eclipse.text.edits.TextEdit;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;
import org.phpaspect.weaver.SourceLocation;
import org.phpaspect.weaver.impl.SourceLocationImpl;
import org.phpaspect.weaver.joinpoints.MethodInvocationJoinPoint;

public class WeaverVisitor extends AbstractVisitor{

	protected List<Pointcut> pointcuts = new LinkedList<Pointcut>();

	private ASTParser parser;
	private Program program;
	private AST ast;
	private ASTRewrite rewriter;
	private IDocument document = new Document();
	private String currentPHPVersion;
	private String fileName;
	
	public WeaverVisitor(IFile file, String fileName) throws Exception {
		currentPHPVersion = "php5";
		this.fileName = fileName;
	    StringBuffer source = new StringBuffer();
	    InputStreamReader in = new InputStreamReader(file.getContents());
	    while(in.ready()){
	    	source.append((char)in.read());
	    }
	    document.set(source.toString());
		parser = ASTParser.newParser(new InputStreamReader(file.getContents()), currentPHPVersion, false, DLTKCore.createSourceModuleFrom(file));
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
	
	public void endVisit(Program program){ 
		//List of the includes for PHPAspect Runtime
		List<Statement> includes = new  LinkedList<Statement>();
		//require_once 'Runtime/Aspect.php';
		Expression aspectFile = ast.newScalar("'Runtime/Aspect.php'");
	    includes.add(ast.newExpressionStatement(ast.newInclude(aspectFile, Include.IT_REQUIRE_ONCE)));
	    //require_once 'Runtime/Reflection/JoinPointImpl.php';
		Expression jpFile = ast.newScalar("'Runtime/Reflection/JoinPointImpl.php'");
	    includes.add(ast.newExpressionStatement(ast.newInclude(jpFile, Include.IT_REQUIRE_ONCE)));
	    //require_once 'Runtime/Dispatcher.php';
		Expression dispatcherFile = ast.newScalar("'Runtime/Dispatcher.php'");
	    includes.add(ast.newExpressionStatement(ast.newInclude(dispatcherFile, Include.IT_REQUIRE_ONCE)));
	    //Add the includes in front of the statement list
	    List<Statement> statements = program.statements();
	    Statement firstStatement = ASTNode.copySubtree(ast, statements.get(0));
	    includes.add(firstStatement);
		Block PHPAspectRuntimeImports = ast.newBlock(includes);
	    rewriter.replace(statements.get(0), PHPAspectRuntimeImports, null);
	}
	
	public void endVisit(MethodInvocation methodInvocation) {
		//Create a joinpoint
		SourceLocation sourceLocation = getSourceLocation(methodInvocation);
		JoinPoint joinpoint = new MethodInvocationJoinPoint(sourceLocation, methodInvocation);
		//If a pointcut is matched we store its id
		List<ArrayElement> ids = new LinkedList<ArrayElement>();
		//Foreach id, we store the corresponding runtime assertion
	    List<ArrayElement> runtimePredicates = new LinkedList<ArrayElement>();
		for(Pointcut pt: pointcuts){
			if(pt.match(ast, joinpoint)){
				ArrayElement e = ast.newArrayElement();
				e.setValue(ast.newScalar(String.valueOf(pt.getId())));
				ids.add(e);
				Expression assertion = pt.getRuntimeAssertion();
				if(assertion != null){
					ArrayElement element = ast.newArrayElement();
					element.setKey(ast.newScalar(String.valueOf(pt.getId())));
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
			//TODO: add type safety
			//JoinPoint type
			jpArgs.add(ast.newStaticConstantAccess(ast.newIdentifier("JoinPoint"), ast.newIdentifier("METHOD_EXECUTION")));
			//Object name
			VariableBase dispatcher = ASTNode.copySubtree(ast, methodInvocation.getDispatcher());
			jpArgs.add(dispatcher);
			//Method name
			Variable var = (Variable)methodInvocation.getMethod().getFunctionName().getName();
			Identifier methodIdentifier = (Identifier)var.getName();
			String methodName = "'"+methodIdentifier.getName()+"'";
			List<Expression> ctorParams = new LinkedList<Expression>();
			dispatcher = ASTNode.copySubtree(ast, methodInvocation.getDispatcher());
			ctorParams.add(dispatcher);
			ctorParams.add(ast.newScalar(methodName));
			ClassInstanceCreation source = ast.newClassInstanceCreation(ast.newClassName(ast.newIdentifier("ReflectionMethod")), ctorParams);
			jpArgs.add(source);
			//Arguments
			List<ArrayElement> arguments = new LinkedList<ArrayElement>();
			List<Expression> parameters = methodInvocation.getMethod().parameters();
			for(Expression param: parameters){
			    ArrayElement e = ast.newArrayElement();
			    e.setValue(ASTNode.copySubtree(ast, param));
				arguments.add(e);
			}
			jpArgs.add(ast.newArrayCreation(arguments));
			//Source location
			jpArgs.add(ast.newScalar("__FILE__"));
			jpArgs.add(ast.newScalar("__LINE__"));
			args.add(ast.newClassInstanceCreation(ast.newClassName(ast.newIdentifier("JoinPointImpl")), jpArgs));
			if(runtimePredicates.size() > 0){
				args.add(ast.newArrayCreation(runtimePredicates));
			}
			FunctionInvocation inv = ast.newFunctionInvocation(ast.newFunctionName(ast.newIdentifier("PHPAspect_dispatch")), args);
			rewriter.replace(methodInvocation, inv, null);
		}
	}
}
