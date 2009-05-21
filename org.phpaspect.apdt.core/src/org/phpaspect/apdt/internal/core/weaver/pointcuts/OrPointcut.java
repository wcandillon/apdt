package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.InfixExpression;
import org.phpaspect.core.weaver.*;

public class OrPointcut extends AbstractPointcut {

	protected Pointcut pt1;
	protected Pointcut pt2;
	
	public OrPointcut(Pointcut pt1, Pointcut pt2){
		super(pt1.getName());
		this.pt1 = pt1;
		this.pt2 = pt2;
	}

	public boolean match(AST ast, JoinPoint jp) {
		boolean match = pt1.match(ast, jp) || pt2.match(ast, jp);
		runtimeAssertion = ast.newInfixExpression(pt1.getRuntimeAssertion(), InfixExpression.OP_BOOL_OR, pt2.getRuntimeAssertion());
		return match;
	}

	public Pointcut clone(){
		return new OrPointcut(pt1, pt2);
	}
}
