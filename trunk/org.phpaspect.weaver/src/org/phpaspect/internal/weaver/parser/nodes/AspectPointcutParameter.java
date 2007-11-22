package org.phpaspect.internal.weaver.parser.nodes;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.eclipse.php.internal.core.ast.visitor.Visitor;

public class AspectPointcutParameter extends ASTNode {

	private AspectInterTypeDeclaration interType;
	private Variable variable;
	
	public AspectPointcutParameter(int start, int end, AspectInterTypeDeclaration interType, Variable variable) {
		super(start, end);
		this.interType = interType;
		this.variable = variable;
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

	public AspectInterTypeDeclaration getInterType() {
		return interType;
	}

	public void setInterType(AspectInterTypeDeclaration interType) {
		this.interType = interType;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

}
