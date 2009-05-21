package org.phpaspect.apdt.internal.core.weaver.mixin;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.phpaspect.core.weaver.Mixin;

public class MixinImpl implements Mixin {

	private String type;
	private String declaringAspect;
	private String name;
	
	public MixinImpl(String type, ASTNode mixin)
	{
		this.type = type;
	}
	
	public ASTNode getMixin(AST ast, ClassDeclaration classDeclaration) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean match(ClassDeclaration classDeclaration) {
		// TODO Auto-generated method stub
		return false;
	}

}
