package org.phpaspect.weaver.pointucts;

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
import org.phpaspect.weaver.AbstractPointcut;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;

public class MethodInvocationPredicate extends AbstractPointcut {
	
	protected String declaringType;
	protected String methodName;
	protected boolean subType;

	public MethodInvocationPredicate(String declaringType, String methodName){
		super();
		this.declaringType = declaringType.replace("*", ".*");
		this.methodName = methodName.replace("*", ".*");
	}
	
	public Pointcut clone(){
		return new MethodInvocationPredicate(declaringType, methodName);
	}

	public boolean match(AST ast, JoinPoint jp) {
		//TODO: warning for ambiguous types
		//TODO: check for subtypes
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
				matched = true;
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
}
