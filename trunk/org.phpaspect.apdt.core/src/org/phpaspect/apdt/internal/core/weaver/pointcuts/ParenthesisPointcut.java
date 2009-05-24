package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.phpaspect.apdt.core.weaver.*;

public class ParenthesisPointcut extends AbstractPointcut {

	protected Pointcut pt;
	protected Expression runtimeAssertion = null;
	
	public ParenthesisPointcut(Pointcut pt){
		super(pt.getName());
		this.pt = pt;
	}

	public boolean match(AST ast, Joinpoint jp) {
		boolean match = pt.match(ast, jp);
		matched = true;
		Expression assertion = match?pt.getRuntimeAssertion():ast.newScalar("false");
		runtimeAssertion = ast.newParenthesisExpression(assertion==null?ast.newScalar("true"):ASTNode.copySubtree(ast, assertion));
		return match;
	}
	
	public Pointcut clone(){
		return new ParenthesisPointcut(pt);
	}

}
