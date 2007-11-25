package org.phpaspect.weaver.parser.nodes;

import java.util.*;

import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

public class AspectInterTypeFieldDeclaration extends Statement {

	private List<AspectInterTypeDeclaration> aspectInterTypeDeclarations;
	private Variable var;
	private Expression expr;
	
	public AspectInterTypeFieldDeclaration(int start, int end,
			List<AspectInterTypeDeclaration> aspectInterTypeDeclaration,
			Variable var, Expression expr) {
		super(start, end);
		this.aspectInterTypeDeclarations = aspectInterTypeDeclaration;
		this.var = var;
		this.expr = expr;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void accept(Visitor visitor){
		((PHPAspectVisitor)visitor).visit(this);
	}

	public void childrenAccept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void toString(StringBuffer buffer, String tab) {
		buffer.append(tab).append(TAB).append("<InterTypeFieldDeclaration");
		appendInterval(buffer);
		buffer.append(">\n");
			for(AspectInterTypeDeclaration a: aspectInterTypeDeclarations){
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
	
	public List<AspectInterTypeDeclaration> getAspectInterTypeDeclarations(){
		return aspectInterTypeDeclarations;
	}
	
	public Variable getVariable(){
		return var;
	}
	
	public Expression getExpression(){
		return expr;
	}
}
