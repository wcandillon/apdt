package org.phpaspect.weaver.introductions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.SingleFieldDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Variable;
import org.phpaspect.weaver.Introduction;
import org.phpaspect.weaver.ast.nodes.InterTypeFieldsDeclaration;

public class AttributeIntroduction implements Introduction {

	private InterTypeFieldsDeclaration interType;
	
	public AttributeIntroduction(InterTypeFieldsDeclaration interTypeFieldsDeclaration){
		this.setInterTypeFieldsDeclaration(interTypeFieldsDeclaration);
	}
	
	public ASTNode getIntroduction(AST ast, ClassDeclaration classDeclaration) {
		List<SingleFieldDeclaration> variables = new LinkedList<SingleFieldDeclaration>();
		org.phpaspect.weaver.ast.nodes.Variable[] vars = interType.getVariableNames();
		org.phpaspect.weaver.ast.nodes.Expression[] exprs = interType.getInitialValues();
		for(int i=0; i<vars.length; i++)
		{
			Variable var = ast.newVariable(vars[i].getName().toString());
			Expression expr = ast.newScalar(exprs[i].toString());
			variables.add(ast.newSingleFieldDeclaration(var, expr));
		}
		return ast.newFieldsDeclaration(interType.getModifier(), variables);
	}

	public boolean match(ClassDeclaration classDeclaration) {
		return classDeclaration.getName().getName().matches(interType.getInterType());
	}

	public void setInterTypeFieldsDeclaration(InterTypeFieldsDeclaration interTypeFieldsDeclaration) {
		this.interType = interTypeFieldsDeclaration;
	}

	public InterTypeFieldsDeclaration getInterTypeFieldsDeclaration() {
		return interType;
	}

}
