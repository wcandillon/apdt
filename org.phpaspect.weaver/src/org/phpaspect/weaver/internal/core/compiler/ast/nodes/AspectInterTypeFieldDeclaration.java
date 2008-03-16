package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import java.util.*;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class AspectInterTypeFieldDeclaration extends Statement {

	private List<AspectInterTypeDeclaration> aspectInterTypeDeclarations;
	private Expression var;
	private Expression expr;
	
	public AspectInterTypeFieldDeclaration(int start, int end,
			List<AspectInterTypeDeclaration> aspectInterTypeDeclaration,
			Expression var, Expression expr) {
		super(start, end);
		this.aspectInterTypeDeclarations = aspectInterTypeDeclaration;
		this.var = var;
		this.expr = expr;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

//	public void accept(Visitor visitor){
//		((PHPAspectVisitor)visitor).visit(this);
//	}
	
	public List<AspectInterTypeDeclaration> getAspectInterTypeDeclarations(){
		return aspectInterTypeDeclarations;
	}
	
	public Expression getVariable(){
		return var;
	}
	
	public Expression getExpression(){
		return expr;
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
