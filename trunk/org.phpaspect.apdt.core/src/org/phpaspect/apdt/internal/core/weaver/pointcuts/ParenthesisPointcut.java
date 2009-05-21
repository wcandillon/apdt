package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.phpaspect.core.weaver.*;

public class ParenthesisPointcut extends AbstractPointcut {

	protected Pointcut pt;
	protected Expression runtimeAssertion = null;
	protected boolean matched;
	
	public ParenthesisPointcut(Pointcut pt){
		super(pt.getName());
		this.pt = pt;
	}

	public boolean match(AST ast, JoinPoint jp) {
		boolean match = pt.match(ast, jp);
		runtimeAssertion = ast.newParenthesisExpression(pt.getRuntimeAssertion());
		return match;
	}
	
	public Pointcut clone(){
		return new ParenthesisPointcut(pt);
	}

}
