package org.phpaspect.apdt.core.weaver;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;

public interface Mixin {
	public boolean match(ClassDeclaration classDeclaration);
	public ASTNode getMixin(AST ast, ClassDeclaration classDeclaration);
}