package org.phpaspect.weaver.impl;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.UnaryOperation;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;

public class NotPointcut implements Pointcut {

	protected int id;
	protected Pointcut pt;
	protected Expression runtimeAssertion = null;
	protected boolean matched = false;
	
	public NotPointcut(Pointcut pt){
		this(PointcutCounter.getId(), pt);
	}
	
	private NotPointcut(int id, Pointcut pt){
		this.id = id;
		this.pt = pt;
	}
	
	public int getId() {
		return id;
	}

	public Expression getRuntimeAssertion() {
		if(!matched){
			throw new IllegalStateException("The point has to be matched before getting runtime assertions");
		}
		return runtimeAssertion;
	}

	public boolean match(AST ast, JoinPoint jp) {
		boolean match = !pt.match(ast, jp);
		Expression assertion = pt.getRuntimeAssertion();
		runtimeAssertion = ast.newUnaryOperation(assertion, UnaryOperation.OP_NOT);
		matched = true;
		return match;
	}
	
	public NotPointcut clone(){
		return new NotPointcut(id, pt);
	}
}
