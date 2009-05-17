package org.phpaspect.apdt.internal.core.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.phpaspect.core.weaver.*;

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
