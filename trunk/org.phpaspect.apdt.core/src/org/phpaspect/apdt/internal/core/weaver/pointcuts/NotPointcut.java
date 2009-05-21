package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.UnaryOperation;
import org.phpaspect.apdt.core.weaver.*;

public class NotPointcut extends AbstractPointcut {

	protected Pointcut pt;
	
	public NotPointcut(Pointcut pt){
		super(pt.getName());
		this.pt = pt;
	}
	
	public boolean match(AST ast, Joinpoint jp) {
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
