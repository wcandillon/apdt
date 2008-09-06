package org.phpaspect.weaver.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterTypeMethodDeclaration extends MethodDeclaration {
	
	private String interType;

	//public static final ChildPropertyDescriptor INTERTYPE_PROPERTY = 
	//	new ChildPropertyDescriptor(InterTypeClassConstantDeclaration.class, "interType", String.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;
	
	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<StructuralPropertyDescriptor>(1);
		//propertyList.add(INTERTYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}
	
	public InterTypeMethodDeclaration(int start, int end, AST ast, int modifier, String interType, FunctionDeclaration function, boolean shouldComplete) {
		super(start, end, ast, modifier, function, shouldComplete);
		this.setInterType(interType);
	}

	public InterTypeMethodDeclaration(int start, int end, AST ast, int modifier, String interType, FunctionDeclaration function) {
		super(start, end, ast, modifier, function);
		this.setInterType(interType);
	}

	public InterTypeMethodDeclaration(AST ast) {
		super(ast);
	}

	public void setInterType(String interType) {
		this.interType = interType;
	}

	public String getInterType() {
		return interType;
	}
	
	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<MethodDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" modifier='").append(getModifierString()).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(tab).append("<InterType>"); //$NON-NLS-1$
		buffer.append(tab).append(interType); //$NON-NLS-1$
		buffer.append(tab).append("</InterType>"); //$NON-NLS-1$
		function.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</MethodDeclaration>"); //$NON-NLS-1$
	}
}
