package org.phpaspect.apdt.core.visitor;

import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.apdt.internal.core.nodes.AspectDeclaration;

public interface PHPAspectVisitor extends Visitor {
	
	public void visit(AspectDeclaration aspectDeclaration);
	
}
