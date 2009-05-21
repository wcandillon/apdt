package org.phpaspect.apdt.core.weaver;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;

public abstract class AbstractPointcut implements Pointcut {

	protected final String name;
	protected Expression runtimeAssertion = null;
	protected boolean matched = false;
	
	public AbstractPointcut(String name){
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}

	public final Expression getRuntimeAssertion() {
		if(!matched){
			throw new IllegalStateException("The pointcut has to be matched before getting runtime assertions");
		}
		return runtimeAssertion;
	}

	public abstract boolean match(AST ast, Joinpoint jp);
	
	public abstract Pointcut clone();

}
