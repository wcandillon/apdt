package org.phpaspect.weaver;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;

public abstract class AbstractPointcut implements Pointcut {

	protected static int lastId = 0;
	protected final int id;
	protected Expression runtimeAssertion = null;
	protected boolean matched = false;
	
	public AbstractPointcut(){
		id = ++lastId;
	}
	
	public final int getId() {
		return id;
	}

	public final Expression getRuntimeAssertion() {
		if(!matched){
			throw new IllegalStateException("The point has to be matched before getting runtime assertions");
		}
		return runtimeAssertion;
	}

	public abstract boolean match(AST ast, JoinPoint jp);
	
	public abstract Pointcut clone();

}
