package org.phpaspect.weaver.ast.nodes;

import java.util.List;

public class InterTypeFieldsDeclaration extends FieldsDeclaration {
	
	private String interType;
	private boolean subType;
	
	public InterTypeFieldsDeclaration(int start, int end, AST ast, int modifier, String interType, boolean subType, List variablesAndDefaults) {
		super(start, end, ast, modifier, variablesAndDefaults);
		this.setInterType(interType);
		this.setSubType(subType);
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

	public void setSubType(boolean subType) {
		this.subType = subType;
	}

	public boolean isSubType() {
		return subType;
	}	
}
