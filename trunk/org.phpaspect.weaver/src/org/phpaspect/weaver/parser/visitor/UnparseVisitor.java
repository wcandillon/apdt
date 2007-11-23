package org.phpaspect.weaver.parser.visitor;

import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.visitor.CodeBuilder;
import org.phpaspect.weaver.parser.nodes.*;

public class UnparseVisitor extends CodeBuilder implements PHPAspectVisitor {

	
	public void visit(AspectDeclaration aspectDeclaration) {
		buffer.append("aspect "); //$NON-NLS-1$
		aspectDeclaration.getName().accept(this);
		if (aspectDeclaration.getSuperClass() != null) {
			buffer.append(" extends "); //$NON-NLS-1$
			aspectDeclaration.getSuperClass().accept(this);
		}
		Identifier[] interfaces = aspectDeclaration.getInterfaces();
		if (interfaces != null && interfaces.length != 0) {
			buffer.append(" implements "); //$NON-NLS-1$
			interfaces[0].accept(this);
			for (int i = 1; i < interfaces.length; i++) {
				buffer.append(" , "); //$NON-NLS-1$
				interfaces[i].accept(this);
			}
		}
		aspectDeclaration.getBody().accept(this);
	}

	public void visit(AspectInterTypeDeclaration aspectInterTypeDeclaration) {
		// TODO Auto-generated method stub

	}

	public void visit(
			AspectInterTypeFieldDeclaration aspectInterTypeFieldDeclaration) {
		// TODO Auto-generated method stub

	}

	public void visit(
			AspectInterTypeFieldsDeclaration aspectInterTypeFieldsDeclaration) {
		// TODO Auto-generated method stub

	}

	public void visit(
			AspectInterTypeMethodDeclaration aspectInterTypeMethodDeclaration) {
		// TODO Auto-generated method stub

	}


}
