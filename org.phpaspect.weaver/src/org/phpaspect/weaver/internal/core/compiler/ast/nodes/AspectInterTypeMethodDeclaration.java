package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.php.internal.core.compiler.ast.nodes.FormalParameter;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPDocBlock;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPMethodDeclaration;

public class AspectInterTypeMethodDeclaration extends PHPMethodDeclaration {

	private List<AspectInterTypeDeclaration> interTypeDeclaration;
	
	public AspectInterTypeMethodDeclaration(int start, int end, int nameStart, int nameEnd,
			List<AspectInterTypeDeclaration> interTypeDeclaration, String functionName, int modifiers,
			List<FormalParameter> formalParameters, Block body, final boolean isReference, PHPDocBlock phpDoc) {
		super(start, end, nameStart, nameEnd, functionName, modifiers, formalParameters, body, isReference, phpDoc);
		this.interTypeDeclaration = interTypeDeclaration;
	}

	public int getType(){
		return PHPAspectASTNode.ASPECT_INTERTYPE_METHOD_DECLARATION;
	}
	
//	public void accept(Visitor visitor){
//		((PHPAspectVisitor)visitor).visit(this);
//	}
}