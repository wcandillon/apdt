package org.phpaspect.weaver.ast.nodes;

import java.util.List;

public class InterTypeFieldsDeclaration extends FieldsDeclaration {
	
	private String interType;
	
	public InterTypeFieldsDeclaration(int start, int end, AST ast, int modifier, String interType, List variablesAndDefaults) {
		super(start, end, ast, modifier, variablesAndDefaults);
		this.setInterType(interType);
	}
	
	public InterTypeFieldsDeclaration(AST ast) {
		super(ast);
	}

	public void setInterType(String interType) {
		this.interType = interType;
	}

	public String getInterType() {
		return interType;
	}	
}
