package org.phpaspect.apdt.internal.core.weaver.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.MethodInvocation;
import org.phpaspect.apdt.core.weaver.AbstractJoinpoint;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.SourceLocation;

public class StaticMethodInvocationJoinpoint extends AbstractJoinpoint {

	private MethodInvocation methodInvocation;
	
	public StaticMethodInvocationJoinpoint(SourceLocation sourceLocation, MethodInvocation node) {
		super(Joinpoint.Kind.STATIC_METHOD_CALL, sourceLocation);
		methodInvocation = node;
	}

	public String toLongString() {
		return toString()+" at "+sourceLocation;
	}
	
	public String toString(){
		return "call("+methodInvocation.getDispatcher()+"->"+methodInvocation.getMethod().getFunctionName().getName()+"())";
	}
	
	public ASTNode getNode() {
		return methodInvocation;
	}
}
