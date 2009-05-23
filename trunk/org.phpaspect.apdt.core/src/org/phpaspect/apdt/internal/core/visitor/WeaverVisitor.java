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
import org.phpaspect.apdt.internal.core.weaver.joinpoints.MethodExecutionJoinpoint;
import org.phpaspect.apdt.internal.core.weaver.joinpoints.MethodInvocationJoinpoint;
import org.phpaspect.internal.core.weaver.SourceLocationImpl;

public final class WeaverVisitor extends AbstractVisitor{

	private List<Pointcut> pointcuts;
	private List<Mixin> mixins = new LinkedList<Mixin>();

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
	
	private Expression getSource(ASTNode node)
	{
		ASTNode enclosingNode = node.getEnclosingBodyNode();
		if(enclosingNode instanceof TypeDeclaration)
		{
			return ast.newVariable("this");
		}
		return ast.newScalar("'Main'");
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
		} else if(node == enclosingNode) {
			return "Main";
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
			List<Expression> parameters, List<ArrayElement> runtimePredicates)
	{ 
		List<Expression> args = new LinkedList<Expression>();
		args.add(ast.newArrayCreation(ids));
		List<Expression> jpArgs = new LinkedList<Expression>();
		jpArgs.add(source);
		jpArgs.add(target);
		List<ArrayElement> arguments = new LinkedList<ArrayElement>();
		if(parameters != null)
		{
			for(Expression param: parameters){
			    ArrayElement e = ast.newArrayElement();
			    e.setValue(ASTNode.copySubtree(ast, param));
				arguments.add(e);
			}			
		}
		jpArgs.add(ast.newArrayCreation(arguments));
		if(kind != Joinpoint.Kind.METHOD_EXECUTION)
		{
			jpArgs.add(ast.newScalar("__FILE__"));
			jpArgs.add(ast.newScalar("__LINE__"));
		}
		args.add(
			ast.newClassInstanceCreation(
				ast.newClassName(
					ast.newIdentifier(kind.getName())
				), jpArgs)
		);
		if(runtimePredicates != null && runtimePredicates.size() > 0){
			args.add(ast.newArrayCreation(runtimePredicates));
		}
		return ast.newFunctionInvocation(
				ast.newFunctionName(
						ast.newIdentifier("dispatch")), args);
	}
	
	@Override
	public void endVisit(MethodDeclaration methodDeclaration)
	{
		//Create a joinpoint
		SourceLocation sourceLocation = getSourceLocation(methodDeclaration);
		Joinpoint joinpoint = new MethodExecutionJoinpoint(sourceLocation, methodDeclaration);
		Pair<List<ArrayElement>, List<ArrayElement>> pair = getRuntimePredicates(joinpoint);
		List<ArrayElement> ids = pair.getIndex();
		if(ids.size() > 0)
		{
			ASTNode classDecl = methodDeclaration.getParent().getParent();
			if(classDecl instanceof ClassDeclaration)
			{
				String methodName = methodDeclaration.getFunction().getFunctionName().getName();
				ClassDeclaration clazz = (ClassDeclaration)classDecl;
				List<Statement> cSt = clazz.getBody().statements();
				List<Statement> statements = new LinkedList<Statement>();
				for(Statement statement: cSt)
				{
					if(statement instanceof MethodDeclaration)
					{
						MethodDeclaration m = (MethodDeclaration)statement;
						boolean sameMethod = m.getFunction().getFunctionName().getName().endsWith(methodName);
						if(sameMethod){ continue; }
					}
					statements.add(ASTNode.copySubtree(ast, statement));
				}
				FunctionDeclaration function = methodDeclaration.getFunction();
				List<FormalParameter> formalParameters = function.formalParameters();
				List<FormalParameter> newFormalParameters = ASTNode.copySubtrees(ast, formalParameters);
				Block body = ASTNode.copySubtree(ast, function.getBody());
				MethodDeclaration newMethod = ast.newMethodDeclaration(methodDeclaration.getModifier(),
						ast.newFunctionDeclaration(
								ast.newIdentifier("__"+function.getFunctionName().getName()),
								newFormalParameters,
								body, function.isReference()
							)
						);
				statements.add(newMethod);
				List<Statement> newStatements = new LinkedList<Statement>();
				List<Expression> parameters = new LinkedList<Expression>();
				for(FormalParameter param: formalParameters)
				{
					parameters.add(ast.newVariable(param.getParameterNameIdentifier().getName()));
				}
				newStatements.add(
						ast.newReturnStatement(
								getDispatchCall(ids,
										Joinpoint.Kind.METHOD_EXECUTION,
										ast.newVariable("this"),
										ast.newIdentifier(methodName),
										parameters, null)
						)
				);
				body = ast.newBlock(newStatements);
				newFormalParameters = ASTNode.copySubtrees(ast, formalParameters);
				newMethod = ast.newMethodDeclaration(methodDeclaration.getModifier(),
						ast.newFunctionDeclaration(
								ast.newIdentifier(function.getFunctionName().getName()),
								newFormalParameters,
								body, function.isReference()
							)
						);
				statements.add(newMethod);
				body = ast.newBlock(statements);
				String superClass = clazz.getSuperClass()!=null?clazz.getSuperClass().toString():null;;
				ClassDeclaration newClass = ast.newClassDeclaration(
						clazz.getModifier(),
						clazz.getName().getName(),
						superClass,
						clazz.interfaces(),
						body
				);
				rewriter.replace(clazz, newClass, null);
				//rewriter.replace(methodDeclaration, ASTNode.copySubtree(ast, newMethod), null);
			}
		}
	}
	
	@Override
	public void endVisit(ClassInstanceCreation instanceCreation)
	{
		//Create a joinpoint
		SourceLocation sourceLocation = getSourceLocation(instanceCreation);
		Joinpoint joinpoint = new ConstructorJoinpoint(sourceLocation, instanceCreation);
		Pair<List<ArrayElement>, List<ArrayElement>> pair = getRuntimePredicates(joinpoint);
		List<ArrayElement> ids = pair.getIndex();
	    List<ArrayElement> runtimePredicates = pair.getValue();
		//If at least one pointcut has been matched
		if(ids.size() > 0){
			//Source
			Expression source = getSource(instanceCreation);
			//Target
			Expression target = instanceCreation.getClassName().getName();
			target = ASTNode.copySubtree(ast, target);
			//Arguments
			List<Expression> parameters = instanceCreation.ctorParams();
			FunctionInvocation inv = getDispatchCall(ids, Joinpoint.Kind.CONSTRUCTOR_CALL, source, target, parameters, runtimePredicates);
			rewriter.replace(instanceCreation, inv, null);
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
			List<Expression> parameters = methodInvocation.getMethod().parameters();
			FunctionInvocation inv = getDispatchCall(ids, Joinpoint.Kind.METHOD_CALL, dispatcher, target, parameters, runtimePredicates);
			rewriter.replace(methodInvocation, inv, null);
		}
	}
}
