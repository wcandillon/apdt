package org.phpaspect.weaver.ast.nodes;

import java.util.List;

import org.phpaspect.weaver.Pointcut;
import org.phpaspect.weaver.ast.match.ASTMatcher;
import org.phpaspect.weaver.ast.visitor.Visitor;

public class AspectPointcutDeclaration extends Statement {

	private String name;
	private Pointcut pt;
	
	public AspectPointcutDeclaration(int start, int end, AST ast,
			String name, Pointcut pt) {
		super(start, end, ast);
		this.setName(name);
		this.pt = pt;
	}

	public AspectPointcutDeclaration(AST ast) {
		super(ast);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	void accept0(Visitor visitor) {
		
	}

	ASTNode clone0(AST target) {
		return new AspectPointcutDeclaration(getStart(), getEnd(), ast, this.name, pt);
	}

	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(
			String apiLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// TODO Auto-generated method stub
		return false;
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
}
