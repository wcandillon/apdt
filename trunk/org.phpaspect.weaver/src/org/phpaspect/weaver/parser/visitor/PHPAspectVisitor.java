package org.phpaspect.weaver.parser.visitor;

import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.internal.weaver.parser.nodes.*;
import org.phpaspect.weaver.parser.nodes.AspectDeclaration;
import org.phpaspect.weaver.parser.nodes.AspectInterTypeDeclaration;
import org.phpaspect.weaver.parser.nodes.AspectInterTypeFieldDeclaration;
import org.phpaspect.weaver.parser.nodes.AspectInterTypeFieldsDeclaration;
import org.phpaspect.weaver.parser.nodes.AspectInterTypeMethodDeclaration;

public interface PHPAspectVisitor extends Visitor {
	
	public void visit(AspectDeclaration aspectDeclaration);
	public void visit(AspectInterTypeDeclaration aspectInterTypeDeclaration);
	public void visit(AspectInterTypeFieldDeclaration aspectInterTypeFieldDeclaration);
	public void visit(AspectInterTypeFieldsDeclaration aspectInterTypeFieldsDeclaration);
	public void visit(AspectInterTypeMethodDeclaration aspectInterTypeMethodDeclaration);
}
