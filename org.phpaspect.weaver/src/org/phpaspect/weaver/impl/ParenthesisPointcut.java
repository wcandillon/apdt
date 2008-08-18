package org.phpaspect.weaver.impl;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;

public class ParenthesisPointcut implements Pointcut {

	protected int id;
	protected Pointcut pt;
	protected Expression runtimeAssertion = null;
	protected boolean matched;
	
	public ParenthesisPointcut(Pointcut pt){
		this(PointcutCounter.getId(), pt);
	}
	
	private ParenthesisPointcut(int id, Pointcut pt){
		this.id = id;
		this.pt = pt;
	}
	
	public int getId() {
		return id;
	}

	public Expression getRuntimeAssertion() {
		// TODO Auto-generated method stub
		return runtimeAssertion;
	}

	public boolean match(AST ast, JoinPoint jp) {
		boolean match = pt.match(ast, jp);
		runtimeAssertion = ast.newParenthesisExpression(pt.getRuntimeAssertion());
		return match;
	}
	
	public Pointcut clone(){
		return new ParenthesisPointcut(id, pt);
	}

}
