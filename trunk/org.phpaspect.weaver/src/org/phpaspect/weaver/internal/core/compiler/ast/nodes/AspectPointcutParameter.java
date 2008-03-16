package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class AspectPointcutParameter extends ASTNode {

	private AspectInterTypeDeclaration interType;
	private Expression variable;
	
	public AspectPointcutParameter(int start, int end, AspectInterTypeDeclaration interType, Expression variable) {
		super(start, end);
		this.interType = interType;
		this.variable = variable;
	}

//	public void accept(Visitor visitor){
//		((PHPAspectVisitor)visitor).visit(this);
//	}

	public AspectInterTypeDeclaration getInterType() {
		return interType;
	}

	public void setInterType(AspectInterTypeDeclaration interType) {
		this.interType = interType;
	}

	public Expression getVariable() {
		return variable;
	}

	public void setVariable(Expression variable) {
		this.variable = variable;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		// TODO Auto-generated method stub
		
	}
}