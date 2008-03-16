package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

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
	public void traverse(ASTVisitor visitor) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
