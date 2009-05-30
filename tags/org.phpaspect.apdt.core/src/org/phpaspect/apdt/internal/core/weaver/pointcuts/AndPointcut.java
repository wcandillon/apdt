package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.InfixExpression;
import org.phpaspect.apdt.core.weaver.*;

public class AndPointcut extends AbstractPointcut{

	protected Pointcut pt1;
	protected Pointcut pt2;
	
	public AndPointcut(Pointcut pt1, Pointcut pt2){
		super(pt1.getName());
		this.pt1 = pt1;
		this.pt2 = pt2;
	}

	public boolean match(AST ast, Joinpoint jp) {
		boolean m1 = pt1.match(ast, jp);
		boolean m2 = pt2.match(ast, jp);
		boolean match = m1 && m2;
		matched = true;
		Expression pt1 = m1?this.pt1.getRuntimeAssertion():ast.newScalar("false");
		Expression pt2 = m2?this.pt2.getRuntimeAssertion():ast.newScalar("false");
		runtimeAssertion = ast.newInfixExpression(pt1==null?ast.newScalar("true"):pt1,
													InfixExpression.OP_BOOL_AND,
													pt2==null?ast.newScalar("true"):pt2);
		return match;
	}

	public Pointcut clone(){
		return new AndPointcut(pt1, pt2);
	}
}
