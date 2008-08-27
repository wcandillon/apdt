package org.phpaspect.weaver.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.phpaspect.weaver.AbstractJoinPoint;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.SourceLocation;

public class MethodInvocationJoinPoint extends AbstractJoinPoint {

	public MethodInvocationJoinPoint(SourceLocation sourceLocation, ASTNode node) {
		super(JoinPoint.Kind.METHOD_CALL, sourceLocation, node);
	}

	public String toLongString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		// TODO Auto-generated method stub
		return null;
	}

	public String toShortString() {
		// TODO Auto-generated method stub
		return null;
	}

}
