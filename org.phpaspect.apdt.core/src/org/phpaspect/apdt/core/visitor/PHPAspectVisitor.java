package org.phpaspect.apdt.core.visitor;

import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.apdt.internal.core.nodes.AspectDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeFieldDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeFieldsDeclaration;
import org.phpaspect.apdt.internal.core.nodes.AspectInterTypeMethodDeclaration;

public interface PHPAspectVisitor extends Visitor {
	
	public void visit(AspectDeclaration aspectDeclaration);
	public void visit(AspectInterTypeDeclaration aspectInterTypeDeclaration);
	public void visit(AspectInterTypeFieldDeclaration aspectInterTypeFieldDeclaration);
	public void visit(AspectInterTypeFieldsDeclaration aspectInterTypeFieldsDeclaration);
	public void visit(AspectInterTypeMethodDeclaration aspectInterTypeMethodDeclaration);
}
