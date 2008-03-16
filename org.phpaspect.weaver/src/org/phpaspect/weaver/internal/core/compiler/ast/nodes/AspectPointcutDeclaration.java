package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class AspectPointcutDeclaration extends Statement {
	
	private String name;
	private List<AspectPointcutParameter> args;
	private Expression expr;
	
	public AspectPointcutDeclaration(int start, int end, String name, List<AspectPointcutParameter> args, Expression expr) {
		super(start, end);
		this.name = name;
		this.args = args;
		this.expr = expr;
		
	}

//	public void accept(Visitor visitor){
//		((PHPAspectVisitor)visitor).visit(this);
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getArgs() {
		return args;
	}

	public void setArgs(List args) {
		this.args = args;
	}

	public Expression getExpr() {
		return expr;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
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