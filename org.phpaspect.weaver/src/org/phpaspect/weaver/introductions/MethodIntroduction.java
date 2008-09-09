package org.phpaspect.weaver.introductions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Block;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.eclipse.php.internal.core.ast.nodes.FormalParameter;
import org.eclipse.php.internal.core.ast.nodes.FunctionDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.phpaspect.weaver.Introduction;
import org.phpaspect.weaver.ast.nodes.InterTypeMethodDeclaration;

public class MethodIntroduction implements Introduction {

	private InterTypeMethodDeclaration interType;
	
	public MethodIntroduction(InterTypeMethodDeclaration interTypeMethodDeclaration){
		this.setInterTypeMethodDeclaration(interTypeMethodDeclaration);
	}
	
	public ASTNode getIntroduction(AST ast, ClassDeclaration classDeclaration) {
		List<FormalParameter> formalParameters = new LinkedList<FormalParameter>();
		List<org.phpaspect.weaver.ast.nodes.FormalParameter> params = interType.getFunction().formalParameters();
		for(org.phpaspect.weaver.ast.nodes.FormalParameter param: params)
		{
			Expression defaultValue = ast.newScalar(param.getDefaultValue().toString());
			Identifier type = ast.newIdentifier(param.getParameterType().getName());
			Expression parameterName = ast.newIdentifier(param.getParameterNameIdentifier().getName());
			formalParameters.add(ast.newFormalParameter(type, parameterName, defaultValue, param.isMandatory()));
		}
		List<Statement> statements = new LinkedList<Statement>();
		
		Block body = ast.newBlock(statements);
		Identifier name = ast.newIdentifier(interType.getFunction().getFunctionName().getName());
		FunctionDeclaration function = ast.newFunctionDeclaration(name, formalParameters, body, interType.getFunction().isReference());
		return ast.newMethodDeclaration(interType.getModifier(), function);
	}

	public boolean match(ClassDeclaration classDeclaration) {
		return classDeclaration.getName().getName().matches(interType.getInterType());
	}

	public void setInterTypeMethodDeclaration(InterTypeMethodDeclaration interTypeMethodDeclaration) {
		this.interType = interTypeMethodDeclaration;
	}

	public InterTypeMethodDeclaration getInterTypeMethodDeclaration() {
		return interType;
	}

}
