package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;

public class AspectAnnotations extends Statement {

	public AspectAnnotation[] annotations;
	
	public AspectAnnotations(int start, int end, AspectAnnotation[] annotations) {
		super(start, end);
		this.annotations = annotations;
	}
	
	public AspectAnnotation[] getAnnotations(){
		return annotations;
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