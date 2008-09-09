package org.phpaspect.weaver;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;

public interface Introduction {
	public boolean match(ClassDeclaration classDeclaration);
	public ASTNode getIntroduction(AST ast, ClassDeclaration classDeclaration);
}