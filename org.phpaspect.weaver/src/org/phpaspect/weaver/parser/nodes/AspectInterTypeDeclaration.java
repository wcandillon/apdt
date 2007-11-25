package org.phpaspect.weaver.parser.nodes;

import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

public class AspectInterTypeDeclaration extends Identifier{

	private boolean subtype = false;
	
	public AspectInterTypeDeclaration(int start, int end, String name, boolean subtype) {
		super(start, end, name);
		this.subtype = subtype;
	}
	
	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<InterTypeDeclaration");
		appendInterval(buffer);
		buffer.append(" name='").append(getName()).append("' subtype='").append(subtype).append("'/>\n");
	}
	
	public int getType(){
		return PHPAspectASTNode.ASPECT_INTERTYPE_FIELD_DECLARATION;
	}
	
	public void accept(Visitor visitor){
		((PHPAspectVisitor)visitor).visit(this);
	}
	
	public boolean isSubType(){
		return subtype;
	}
}