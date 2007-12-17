package org.phpaspect.weaver.parser.nodes;

import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Block;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

public class AspectDeclaration extends ClassDeclaration {

	private boolean persistent = false;
	
	public AspectDeclaration(int start, int end, int modifier,
			Identifier className, Identifier superClass, List interfaces,
			boolean persistent, Block body) {
		super(start, end, modifier, className, superClass, interfaces, body);
		this.persistent = persistent;
	}
	
	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<AspectDeclaration");
		appendInterval(buffer);
		buffer.append(" modifier='").append(getModifier(getModifier())).append("'>\n");
		buffer.append(tab).append(TAB).append("<AspectName>\n");
		getName().toString(buffer, TAB + TAB + tab);
		buffer.append("\n");
		buffer.append(tab).append(TAB).append("</AspectName>\n");

		buffer.append(tab).append(TAB).append("<SuperClassName>\n");
		if (getSuperClass() != null) {
			getSuperClass().toString(buffer, TAB + TAB + tab);
			buffer.append("\n");
		}
		buffer.append(tab).append(TAB).append("</SuperClassName>\n");

		buffer.append(tab).append(TAB).append("<Interfaces>\n");
		Identifier[] interfaces = getInterfaces();
		for (int i = 0; interfaces != null && i < interfaces.length; i++) {
			interfaces[i].toString(buffer, TAB + TAB + tab);
			buffer.append("\n");
		}
		buffer.append(tab).append(TAB).append("</Interfaces>\n");
		getBody().toString(buffer, TAB + tab);
		buffer.append("\n");
		buffer.append(tab).append("</AspectDeclaration>");
	}
	
	public int getType() {
		return PHPAspectASTNode.ASPECT_DECLARATION;
	}
	
	public void accept(Visitor visitor){
		((PHPAspectVisitor)visitor).visit(this);
	}
}
