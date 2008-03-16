package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.TypeReference;
import org.eclipse.dltk.ast.statements.Statement;

public class AspectInterTypeParentDeclaration extends Statement {
	
	private TypeReference className;
	private TypeReference superClass;
	private List interfaces;
	
	public AspectInterTypeParentDeclaration(int start, int end, TypeReference className,
												TypeReference superClass, List interfaces){
		super(start, end);
		this.className = className;
		this.superClass = superClass;
		this.interfaces = interfaces;
	}

	public TypeReference getClassName(){
		return className;
	}
	
	public TypeReference getSuperClass(){
		return superClass;
	}
	
	public List getInterfaces(){
		return interfaces;
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