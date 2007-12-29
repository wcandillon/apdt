package org.phpaspect.weaver.parser.nodes;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.visitor.Visitor;

public class AspectAnnotation extends ASTNode{

	private String name;
	private String value;
	
	public AspectAnnotation(int start, int end, String name, String value){
		super(start, end);
		this.name = name;
		this.value = value;
	}
	
	public String getName(){
		return name;
	}
	
	public String getValue(){
		return value;
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
