package org.phpaspect.apdt.internal.core.weaver.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.phpaspect.apdt.core.weaver.AbstractJoinpoint;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.SourceLocation;

public class MethodInvocationJoinpoint extends AbstractJoinpoint {

	private MethodInvocation methodInvocation;
	
	public MethodInvocationJoinpoint(SourceLocation sourceLocation, MethodInvocation node) {
		super(Joinpoint.Kind.METHOD_CALL, sourceLocation);
		methodInvocation = node;
	}
	
	@Override
	public String toString(){
		return "call("+methodInvocation.getDispatcher()+"->"+methodInvocation.getMethod()+"())";
	}
	
	public ASTNode getNode() {
		return methodInvocation;
	}
}
