package org.phpaspect.core.weaver;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;

public interface Pointcut{
	public boolean match(AST ast, JoinPoint jp);
	public String getName();
	public Expression getRuntimeAssertion();
	public Pointcut clone();
}
