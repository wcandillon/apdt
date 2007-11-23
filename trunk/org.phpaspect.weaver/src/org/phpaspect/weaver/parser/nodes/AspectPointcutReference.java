package org.phpaspect.weaver.parser.nodes;

import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.parser.visitors.PHPAspectVisitor;

public class AspectPointcutReference extends Expression {

	private String pointcutName;
	
	public AspectPointcutReference(int start, int end, String pointcutName) {
		super(start, end);
		this.pointcutName = pointcutName;
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

	public String getPointcutName() {
		return pointcutName;
	}

	public void setPointcutName(String pointcutName) {
		this.pointcutName = pointcutName;
	}

}
