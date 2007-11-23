package org.phpaspect.weaver.parser.visitor;

import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.parser.nodes.*;

public interface PHPAspectVisitor extends Visitor {
	
	public void visit(AspectDeclaration aspectDeclaration);
	public void visit(AspectInterTypeDeclaration aspectInterTypeDeclaration);
	public void visit(AspectInterTypeFieldDeclaration aspectInterTypeFieldDeclaration);
	public void visit(AspectInterTypeFieldsDeclaration aspectInterTypeFieldsDeclaration);
	public void visit(AspectInterTypeMethodDeclaration aspectInterTypeMethodDeclaration);
}
