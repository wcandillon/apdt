package org.phpaspect.weaver.introductions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.phpaspect.weaver.Introduction;
import org.phpaspect.weaver.ast.nodes.InterTypeClassConstantDeclaration;

public class ConstantIntroduction implements Introduction {

	private InterTypeClassConstantDeclaration interType;
	
	public ConstantIntroduction(InterTypeClassConstantDeclaration introduction){
		this.setIntroduction(introduction);
	}
	
	public ASTNode getIntroduction(AST ast, ClassDeclaration classDeclaration) {
		List<Identifier> names = new LinkedList<Identifier>();
		List<Expression> initializers = new LinkedList<Expression>();
		List<org.phpaspect.weaver.ast.nodes.Identifier> lNames = interType.names();
		List<org.phpaspect.weaver.ast.nodes.Expression> lInitializers = interType.initializers();
		for(org.phpaspect.weaver.ast.nodes.Identifier id: lNames)
		{
			names.add(ast.newIdentifier(id.getName()));
		}
		for(org.phpaspect.weaver.ast.nodes.Expression ini: lInitializers)
		{
			initializers.add(ast.newScalar(ini.toString()));
		}
		return ast.newClassConstantDeclaration(names, initializers);
	}

	public boolean match(ClassDeclaration classDeclaration) {
		return classDeclaration.getName().getName().matches(interType.getInterType());
	}

	public void setIntroduction(InterTypeClassConstantDeclaration introduction) {
		this.interType = introduction;
	}

	public InterTypeClassConstantDeclaration getIntroduction() {
		return interType;
	}
}
