package org.phpaspect.weaver.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.phpaspect.weaver.AbstractJoinPoint;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.SourceLocation;

public class MethodInvocationJoinPoint extends AbstractJoinPoint {

	private MethodInvocation methodInvocation;
	
	public MethodInvocationJoinPoint(SourceLocation sourceLocation, MethodInvocation node) {
		super(JoinPoint.Kind.METHOD_CALL, sourceLocation);
		methodInvocation = node;
	}

	public String toLongString() {
		return toString()+" at "+sourceLocation;
	}
	
	public String toString(){
		return "call("+methodInvocation.getDispatcher()+"->"+methodInvocation.getMethod()+"())";
	}
	
	public ASTNode getNode() {
		return methodInvocation;
	}
}