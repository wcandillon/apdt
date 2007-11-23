package org.phpaspect.weaver.parser.nodes;

import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.Block;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.eclipse.php.internal.core.ast.visitor.Visitor;

public class AspectCodeAdviceDeclaration extends Statement {

	private AdviceType adviceType;
	private List args;
	private Block body;
	private Expression pointcut;
	
	public AspectCodeAdviceDeclaration(int start, int end, AdviceType adviceType, List args, Expression pointcut, Block body) {
		super(start, end);
		this.adviceType = adviceType;
		this.args = args;
		this.pointcut = pointcut;
		this.body = body;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void childrenAccept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void toString(StringBuffer buffer, String tab) {
		// TODO Auto-generated method stub

	}

	public void traverseBottomUp(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void traverseTopDown(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public AdviceType getAdviceType() {
		return adviceType;
	}

	public void setAdviceType(AdviceType adviceType) {
		this.adviceType = adviceType;
	}

	public List getArgs() {
		return args;
	}

	public void setArgs(List args) {
		this.args = args;
	}

	public Block getBody() {
		return body;
	}

	public void setBody(Block body) {
		this.body = body;
	}

	public Expression getPointcut() {
		return pointcut;
	}

	public void setPointcut(Expression pointcut) {
		this.pointcut = pointcut;
	}

}
