package org.phpaspect.apdt.internal.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.php.internal.core.compiler.ast.nodes.FormalParameter;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPDocBlock;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPMethodDeclaration;

public class InterTypePHPMethodDeclaration extends PHPMethodDeclaration {

	private String interType;
	
	public InterTypePHPMethodDeclaration(int start, int end, int nameStart,
			int nameEnd, String interType, String functionName, int modifiers,
			List<FormalParameter> formalParameters, Block body,
			boolean isReference, PHPDocBlock phpDoc) {
		super(start, end, nameStart, nameEnd, functionName, modifiers,
				formalParameters, body, isReference, phpDoc);
		this.interType = interType;
	}
	
	public InterTypePHPMethodDeclaration(int start, int end, int nameStart, int nameEnd, String interType, String functionName, List<FormalParameter> formalParameters, Block body, final boolean isReference, PHPDocBlock phpDoc) {
		this(start, end, nameStart, nameEnd, interType, functionName, Modifiers.AccDefault, formalParameters, body, isReference, phpDoc);
	}

}
