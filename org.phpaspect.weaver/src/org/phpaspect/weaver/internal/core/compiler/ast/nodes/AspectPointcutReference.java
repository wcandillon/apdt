package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class AspectPointcutReference extends Expression {

	private String pointcutName;
	private String xpath;
	
	public AspectPointcutReference(int start, int end, String pointcutName) {
		super(start, end);
		this.pointcutName = pointcutName;
	}

	public String getPointcutName() {
		return pointcutName;
	}

	public void setPointcutName(String pointcutName) {
		this.pointcutName = pointcutName;
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
