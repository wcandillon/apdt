package org.phpaspect.apdt.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPDocBlock;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPFieldDeclaration;

public class InterTypePHPFieldDeclaration extends PHPFieldDeclaration {

	private String interType;
	
	public InterTypePHPFieldDeclaration(String interType, VariableReference variable,
			Expression initializer, int start, int end, int modifiers,
			int declStart, PHPDocBlock phpDoc) {
		super(variable, initializer, start, end, modifiers, declStart, phpDoc);
		this.setInterType(interType);
	}

	public void setInterType(String interType) {
		this.interType = interType;
	}

	public String getInterType() {
		return interType;
	}

}
