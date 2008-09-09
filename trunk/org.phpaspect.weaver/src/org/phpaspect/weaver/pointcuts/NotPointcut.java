package org.phpaspect.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.UnaryOperation;
import org.phpaspect.weaver.AbstractPointcut;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;

public class NotPointcut extends AbstractPointcut {

	protected Pointcut pt;
	
	public NotPointcut(Pointcut pt){
		super();
		this.pt = pt;
	}
	
	public boolean match(AST ast, JoinPoint jp) {
		boolean match = !pt.match(ast, jp);
		Expression assertion = pt.getRuntimeAssertion();
		runtimeAssertion = ast.newUnaryOperation(assertion, UnaryOperation.OP_NOT);
		matched = true;
		return match;
	}
	
	public Pointcut clone(){
		return new NotPointcut(pt);
	}
}
