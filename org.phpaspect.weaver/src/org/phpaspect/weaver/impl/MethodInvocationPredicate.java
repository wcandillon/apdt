package org.phpaspect.weaver.impl;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.FunctionInvocation;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.eclipse.php.internal.core.ast.nodes.VariableBase;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;

public class MethodInvocationPredicate implements Pointcut {
	
	protected int id;
	protected String declaringType;
	protected String methodName;
	protected Expression runtimeAssertion = null;
	protected boolean matched = false;
	
	public MethodInvocationPredicate(String declaringType, String methodName){
		this(PointcutCounter.getId(), declaringType, methodName);
	
	}
	
	private MethodInvocationPredicate(int id, String declaringType, String methodName){
		this.id = id;
		this.declaringType = declaringType.replace("*", ".*");
		this.methodName = methodName.replace("*", ".*");
	}
	
	public Pointcut clone(){
		return new MethodInvocationPredicate(id, declaringType, methodName);
	}
	
	public int getId() {
		return id;
	}

	public boolean match(AST ast, JoinPoint jp) {
		if (jp.getKind() == JoinPoint.Kind.METHOD_CALL){
			MethodInvocation node = (MethodInvocation) jp.getNode();
			Variable var = (Variable)node.getMethod().getFunctionName().getName();
			Identifier identifier = (Identifier)var.getName();
			String methodName = identifier.getName();
			if(node.getDispatcher().resolveTypeBinding().getName() == null){
				//We need to add a runtime predicate
				List<Expression> parameters = new LinkedList<Expression>();
				VariableBase dispatch = ASTNode.copySubtree(ast, node.getDispatcher());
				parameters.add(dispatch);
				parameters.add(ast.newScalar("'"+declaringType+"'"));
				FunctionInvocation functionInvocation = ast.newFunctionInvocation(ast.newFunctionName(ast.newIdentifier("PHPAspect_match")), parameters);
				runtimeAssertion = ast.newParenthesisExpression(functionInvocation);
				return methodName.matches(this.methodName);
			} else if(methodName.matches(this.methodName) && node.getDispatcher().resolveTypeBinding().getName().matches(declaringType)){
				runtimeAssertion = ast.newScalar("true");
			}
			matched = true;
			return true;
		}
		runtimeAssertion = ast.newScalar("false");
		return false;
	}

	public Expression getRuntimeAssertion() {
		if(!matched){
			throw new IllegalStateException("The point has to be matched before getting runtime assertions");
		}
		return runtimeAssertion;
	}
}
