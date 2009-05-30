package org.phpaspect.apdt.internal.core.weaver.joinpoints;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.MethodDeclaration;
import org.phpaspect.apdt.core.weaver.AbstractJoinpoint;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.SourceLocation;

public class MethodExecutionJoinpoint extends AbstractJoinpoint{

	private MethodDeclaration methodDeclaration;

	public MethodExecutionJoinpoint(SourceLocation sourceLocation, MethodDeclaration node) {
		super(Joinpoint.Kind.METHOD_EXECUTION, sourceLocation);
		methodDeclaration = node;
	}

	public ASTNode getNode() {
		return methodDeclaration;
	}

	@Override
	public String toString(){
		String className = ((ClassDeclaration)methodDeclaration.getParent().getParent()).getName().getName();
		return "exec("+className+
			"::"+methodDeclaration.getFunction().getFunctionName().getName()+"())";
	}
}
