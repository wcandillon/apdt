package org.phpaspect.weaver.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InterTypeClassConstantDeclaration extends ClassConstantDeclaration {
	
	private String interType;

	public static final ChildPropertyDescriptor INTERTYPE_PROPERTY = 
		new ChildPropertyDescriptor(InterTypeClassConstantDeclaration.class, "interType", String.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;
	
	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<StructuralPropertyDescriptor>(1);
		propertyList.add(INTERTYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}
	
	public InterTypeClassConstantDeclaration(int start, int end, AST ast, String interType, List variablesAndDefaults) {
		super(start, end, ast, variablesAndDefaults);
		this.setInterType(interType);
	}

	public InterTypeClassConstantDeclaration(AST ast) {
		super(ast);
	}

	public void setInterType(String interType) {
		this.interType = interType;
	}

	public String getInterType() {
		return interType;
	}
	
	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<InterTypeClassConstantDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(tab).append("<InterType>"); //$NON-NLS-1$
		buffer.append(tab).append(interType); //$NON-NLS-1$
		buffer.append(tab).append("</InterType>"); //$NON-NLS-1$
		Iterator<Identifier> iterator1 = names.iterator();
		Iterator<Expression> iterator2 = initializers.iterator();
		while (iterator1.hasNext()) {
			buffer.append(tab).append(TAB).append("<VariableName>\n"); //$NON-NLS-1$
			iterator1.next().toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
			buffer.append(tab).append(TAB).append("</VariableName>\n"); //$NON-NLS-1$
			buffer.append(tab).append(TAB).append("<InitialValue>\n"); //$NON-NLS-1$
			Expression expr = iterator2.next();
			if (expr != null) {
				expr.toString(buffer, TAB + TAB + tab);
				buffer.append("\n"); //$NON-NLS-1$
			}
			buffer.append(tab).append(TAB).append("</InitialValue>\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</InterTypeClassConstantDeclaration>"); //$NON-NLS-1$
	}
}
