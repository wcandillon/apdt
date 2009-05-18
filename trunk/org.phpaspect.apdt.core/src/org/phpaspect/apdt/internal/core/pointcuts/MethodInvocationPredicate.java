package org.phpaspect.apdt.internal.core.pointcuts;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.FunctionInvocation;
import org.eclipse.php.internal.core.ast.nodes.ITypeBinding;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.eclipse.php.internal.core.ast.nodes.VariableBase;
import org.phpaspect.core.weaver.*;

public class MethodInvocationPredicate extends AbstractPointcut {
	
	protected String declaringType;
	protected String methodName;
	protected boolean subType;

	public MethodInvocationPredicate(String name, String declaringType, String methodName){
		this(name, declaringType, methodName, false);
	}
	
	public MethodInvocationPredicate(String name, String declaringType, String methodName, boolean subType){
		super(name);
		this.declaringType = declaringType.replace("*", ".*");
		this.methodName = methodName.replace("*", ".*");
		this.subType = subType;
	}
	
	public Pointcut clone(){
		return new MethodInvocationPredicate(name, declaringType, methodName, subType);
	}

	public boolean match(AST ast, JoinPoint jp) {
		//TODO: warning for ambiguous types
		//TODO: check for subtypes
		if (jp.getKind() == JoinPoint.Kind.METHOD_CALL){
			MethodInvocation node = (MethodInvocation) jp.getNode();
			Variable var = (Variable)node.getMethod().getFunctionName().getName();
			Identifier identifier = (Identifier)var.getName();
			String methodName = identifier.getName();
			String objectType = node.getDispatcher().resolveTypeBinding().getName();
			if(objectType == null){
				return setRuntimePredication(ast, node, declaringType);
			} else if(methodName.matches(this.methodName) && objectType.matches(declaringType)){
				runtimeAssertion = ast.newScalar("true");
				matched = true;
			} else if(methodName.matches(this.methodName) && subType){
				ITypeBinding subClass = node.getDispatcher().resolveTypeBinding().getSuperclass();
				while(subClass != null){
					if(subClass.getName().matches(declaringType)){
						runtimeAssertion = ast.newScalar("true");
						matched = true;
						return true;
					}
					subClass = subClass.getSuperclass();
				}
				ITypeBinding[] interfaces = node.getDispatcher().resolveTypeBinding().getInterfaces();
				for(int i=0; i<interfaces.length; i++){
					if(interfaces[i].getName().matches(declaringType)){
						runtimeAssertion = ast.newScalar("true");
						matched = true;
						return true;
					}
				}
				return setRuntimePredication(ast, node, declaringType);
			} else {
				runtimeAssertion = ast.newScalar("false");
				return false;
			}
			matched = true;
			return true;
		}
		runtimeAssertion = ast.newScalar("false");
		matched = true;
		return false;
	}
	
	private boolean setRuntimePredication(AST ast, MethodInvocation inv, String type){
		List<Expression> parameters = new LinkedList<Expression>();
		VariableBase dispatch = ASTNode.copySubtree(ast, inv.getDispatcher());
		parameters.add(dispatch);
		parameters.add(ast.newScalar("'"+declaringType+"'"));
		FunctionInvocation functionInvocation = ast.newFunctionInvocation(ast.newFunctionName(ast.newIdentifier("isTypeMatching")), parameters);
		runtimeAssertion = ast.newParenthesisExpression(functionInvocation);
		return methodName.matches(this.methodName);		
	}
}