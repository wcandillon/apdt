package org.phpaspect.weaver.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AspectDeclaration extends ClassDeclaration {

	private boolean persistent = false;
	
	public static final ChildPropertyDescriptor PERSISTENT_PROPERTY = 
		new ChildPropertyDescriptor(AspectDeclaration.class, "persistent", Boolean.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;
	
	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<StructuralPropertyDescriptor>(1);
		propertyList.add(PERSISTENT_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}	
	
	public AspectDeclaration(AST ast) {
		super(ast);
	}
	
	public AspectDeclaration(int start, int end, AST ast, int modifier, Identifier className, boolean persistent, Identifier superClass, List interfaces, Block body) {
		super(start, end, ast, modifier, className, superClass, interfaces, body);
		this.persistent = persistent;
	}
	
	protected ChildPropertyDescriptor getBodyProperty() {
		return PERSISTENT_PROPERTY;
	}

	public int getType() {
		return ASTNode.ASPECT_DECLARATION;
	}
	
	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<AspectDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" modifier='").append(getModifier(modifier)).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(tab).append(TAB).append("<AspectName>\n"); //$NON-NLS-1$
		getName().toString(buffer, TAB + TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append(TAB).append("</AspectName>\n"); //$NON-NLS-1$

		buffer.append(tab).append(TAB).append("<SuperAspectName>\n"); //$NON-NLS-1$
		if (superClass != null) {
			superClass.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append(TAB).append("</SuperAspectName>\n"); //$NON-NLS-1$

		buffer.append(tab).append(TAB).append("<Interfaces>\n"); //$NON-NLS-1$
		for (Object object : interfaces()) {
			final ASTNode node = (ASTNode) object;
			node.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append(TAB).append("</Interfaces>\n"); //$NON-NLS-1$
		getBody().toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</AspectDeclaration>"); //$NON-NLS-1$
	}
}
