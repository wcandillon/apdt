package org.phpaspect.apdt.internal.core.weaver.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassInstanceCreation;
import org.phpaspect.core.weaver.AbstractJoinPoint;
import org.phpaspect.core.weaver.JoinPoint;
import org.phpaspect.core.weaver.SourceLocation;

public class ConstructorJoinPoint extends AbstractJoinPoint{

	private ClassInstanceCreation classInstanceCreation;
	
	public ConstructorJoinPoint(SourceLocation sourceLocation, ClassInstanceCreation node) {
		super(JoinPoint.Kind.CONSTRUCTOR_CALL, sourceLocation);
		classInstanceCreation = node;
	}

	public ASTNode getNode() {
		return classInstanceCreation;
	}

	@Override
	public String toString(){
		return "new("+classInstanceCreation.getClassName().toString()+")";
	}

}
