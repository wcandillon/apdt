package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.references.TypeReference;


public class AspectInterTypeDeclaration extends TypeReference{

	private boolean subtype = false;
	
	public AspectInterTypeDeclaration(int start, int end, String name, boolean subtype) {
		this(start, end, name);
		this.subtype = subtype;
	}
	
	public AspectInterTypeDeclaration(int start, int end, String name){
		super(start, end, name);
	}
	
	public int getType(){
		return PHPAspectASTNode.ASPECT_INTERTYPE_FIELD_DECLARATION;
	}
	
//	public void accept(Visitor visitor){
//		((PHPAspectVisitor)visitor).visit(this);
//	}
	
	public boolean isSubType(){
		return subtype;
	}
}