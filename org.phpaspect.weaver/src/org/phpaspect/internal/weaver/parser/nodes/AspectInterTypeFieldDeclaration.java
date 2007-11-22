package org.phpaspect.internal.weaver.parser.nodes;

import java.util.*;

import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.eclipse.php.internal.core.ast.visitor.Visitor;

public class AspectInterTypeFieldDeclaration extends Statement {

	private List<AspectInterTypeDeclaration> aspectInterTypeDeclaration;
	private Variable var;
	private Expression expr;
	
	public AspectInterTypeFieldDeclaration(int start, int end,
			List<AspectInterTypeDeclaration> aspectInterTypeDeclaration,
			Variable var, Expression expr) {
		super(start, end);
		this.aspectInterTypeDeclaration = aspectInterTypeDeclaration;
		this.var = var;
		this.expr = expr;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void childrenAccept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append(TAB).append("<InterTypeFieldDeclaration");
		appendInterval(buffer);
		buffer.append(">\n");
			for(AspectInterTypeDeclaration a: aspectInterTypeDeclaration){
				a.toString(buffer, TAB+TAB+tab);
			}
			var.toString(buffer, TAB+TAB+tab);
			buffer.append("\n");
			expr.toString(buffer, TAB+TAB+tab);
			buffer.append("\n");
		buffer.append(tab).append(TAB).append("</InterTypeFieldDeclaration>\n");
	}

	public void traverseBottomUp(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void traverseTopDown(Visitor visitor) {
		// TODO Auto-generated method stub

	}

}
