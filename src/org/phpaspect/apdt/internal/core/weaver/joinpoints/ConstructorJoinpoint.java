package org.phpaspect.apdt.internal.core.weaver.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassInstanceCreation;
import org.phpaspect.apdt.core.weaver.AbstractJoinpoint;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.SourceLocation;

public class ConstructorJoinpoint extends AbstractJoinpoint
{
	private ClassInstanceCreation classInstanceCreation;
	
	public ConstructorJoinpoint(SourceLocation sourceLocation, ClassInstanceCreation node)
	{
		super(Joinpoint.Kind.CONSTRUCTOR_CALL, sourceLocation);
		classInstanceCreation = node;
	}

	public ASTNode getNode()
	{
		return classInstanceCreation;
	}

	@Override
	public String toString()
	{
		return "new("+classInstanceCreation.getClassName().toString()+")";
	}
}