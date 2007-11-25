package org.phpaspect.weaver.parser.nodes;

import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

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

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void accept(Visitor visitor){
		((PHPAspectVisitor)visitor).visit(this);
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

}
