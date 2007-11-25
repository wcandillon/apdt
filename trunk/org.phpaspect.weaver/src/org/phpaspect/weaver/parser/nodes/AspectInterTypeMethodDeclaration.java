package org.phpaspect.weaver.parser.nodes;

import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.FunctionDeclaration;
import org.eclipse.php.internal.core.ast.nodes.MethodDeclaration;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

public class AspectInterTypeMethodDeclaration extends MethodDeclaration {

	private List<AspectInterTypeDeclaration> interTypeDeclaration;
	
	public AspectInterTypeMethodDeclaration(int start, int end, int modifier,
			List<AspectInterTypeDeclaration> interTypeDeclaration,
			FunctionDeclaration function, boolean shouldComplete) {
		super(start, end, modifier, function, shouldComplete);
		this.interTypeDeclaration = interTypeDeclaration;
	}
	
	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append("<InterTypeMethodDeclaration");
		appendInterval(buffer);
		buffer.append(" modifier='").append(getModifierString()).append("'>\n");
		for(AspectInterTypeDeclaration a: interTypeDeclaration){
			a.toString(buffer, TAB + tab);
		}
		getFunction().toString(buffer, TAB + tab);
		buffer.append("\n");
		buffer.append(tab).append("</InterTypeMethodDeclaration>\n");
	}
	
	public int getType(){
		return PHPAspectASTNode.ASPECT_INTERTYPE_METHOD_DECLARATION;
	}
	
	public void accept(Visitor visitor){
		((PHPAspectVisitor)visitor).visit(this);
	}
}