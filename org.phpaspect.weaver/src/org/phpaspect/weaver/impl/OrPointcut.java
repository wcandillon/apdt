package org.phpaspect.weaver.impl;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.InfixExpression;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;

public class OrPointcut implements Pointcut {

	protected int id;
	protected Pointcut pt1;
	protected Pointcut pt2;
	protected Expression runtimeAssertion = null;
	protected boolean matched = false;
	
	public OrPointcut(Pointcut pt1, Pointcut pt2){
		this(PointcutCounter.getId(), pt1, pt2);
	}
	
	private OrPointcut(int id, Pointcut pt1, Pointcut pt2){
		this.id = id;
		this.pt1 = pt1;
		this.pt2 = pt2;
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
		boolean match = pt1.match(ast, jp) || pt2.match(ast, jp);
		runtimeAssertion = ast.newInfixExpression(pt1.getRuntimeAssertion(), InfixExpression.OP_BOOL_OR, pt2.getRuntimeAssertion());
		return match;
	}

	public Pointcut clone(){
		return new OrPointcut(id, pt1, pt2);
	}
}
