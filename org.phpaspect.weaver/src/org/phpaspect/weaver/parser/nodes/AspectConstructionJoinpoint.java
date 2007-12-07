package org.phpaspect.weaver.parser.nodes;

import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.visitor.Visitor;


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
	
}