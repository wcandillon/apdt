package org.phpaspect.apdt.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.references.ConstantReference;

public class InterTypeConstantReference extends ConstantReference {

	private String interType;
	
	public InterTypeConstantReference(String interType, DLTKToken token) {
		super(token);
		this.setInterType(interType);
	}
	
	public InterTypeConstantReference(int start, int end, String interType, String name) {
		super(start, end, name);
		this.setInterType(interType);
	}

	public void setInterType(String interType) {
		this.interType = interType;
	}

	public String getInterType() {
		return interType;
	}

}
