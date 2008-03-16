package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class AspectConstructionJoinpoint extends Expression{

	private AspectInterTypeDeclaration interType;
	private List parameters;
	private String xpath;
	
	public AspectConstructionJoinpoint(int start, int end, AspectInterTypeDeclaration interType, List parameters) {
		super(start, end);
		this.interType  = interType;
		this.parameters = parameters;
		xpath = "(//*[@class='classInstanceCreation'][*/className/variable] || //*[@class='classInstanceCreation'][contains("+interType.getName()+", */className/@name)])";
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