package org.phpaspect.weaver.parser.nodes;

import java.util.*;

import org.eclipse.php.internal.core.ast.nodes.BodyDeclaration;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.parser.visitors.PHPAspectVisitor;

public class AspectInterTypeFieldsDeclaration extends BodyDeclaration{

	private List<AspectInterTypeFieldDeclaration> interTypesFields = new LinkedList<AspectInterTypeFieldDeclaration>();
	
	public AspectInterTypeFieldsDeclaration(int start, int end, int modifier, boolean shouldComplete, List<AspectInterTypeFieldDeclaration> interTypesField) {
		super(start, end, modifier, shouldComplete);
		this.interTypesFields = interTypesField;
	}

	@Override
	public int getType() {
		return PHPAspectASTNode.ASPECT_INTERTYPE_FIELDS_DECLARATION;
	}

	public void accept(Visitor visitor){
		((PHPAspectVisitor)visitor).visit(this);
	}

	public void childrenAccept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<InterTypeFieldsDeclaration");
		appendInterval(buffer);
		buffer.append(" modifier='").append(getModifierString()).append("'>\n");
		for(AspectInterTypeFieldDeclaration interTypeFieldDeclaration: interTypesFields){
			interTypeFieldDeclaration.toString(buffer, tab);
		}
//		for (int i = 0; i < variableNames.length; i++) {
//			buffer.append(tab).append(TAB).append("<VariableName>\n");
//			variableNames[i].toString(buffer, TAB + TAB + tab);
//			buffer.append("\n");
//			buffer.append(tab).append(TAB).append("</VariableName>\n");
//			buffer.append(tab).append(TAB).append("<InitialValue>\n");
//			Expression expr = initialValues[i];
//			if (expr != null) {
//				expr.toString(buffer, TAB + TAB + tab);
//				buffer.append("\n");
//			}
//			buffer.append(tab).append(TAB).append("</InitialValue>\n");
//		}
		buffer.append(tab).append("</InterTypeFieldsDeclaration>\n");		
	}

	public void traverseBottomUp(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	public void traverseTopDown(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	public List<AspectInterTypeFieldDeclaration> getInterTypesFields() {
		return interTypesFields;
	}

}
