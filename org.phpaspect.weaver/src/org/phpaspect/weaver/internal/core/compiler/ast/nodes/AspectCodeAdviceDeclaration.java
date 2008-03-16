package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

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

	@Override
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
