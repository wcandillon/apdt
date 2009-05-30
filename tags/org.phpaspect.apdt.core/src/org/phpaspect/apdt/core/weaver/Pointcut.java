package org.phpaspect.apdt.core.weaver;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;

public interface Pointcut{
	public boolean match(AST ast, Joinpoint jp);
	public String getName();
	public Expression getRuntimeAssertion();
	public Pointcut clone();
}
