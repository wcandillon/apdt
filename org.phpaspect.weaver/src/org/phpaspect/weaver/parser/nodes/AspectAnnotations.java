package org.phpaspect.weaver.parser.nodes;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

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
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void accept(Visitor visitor) {
		((PHPAspectVisitor)visitor).visit(this);
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