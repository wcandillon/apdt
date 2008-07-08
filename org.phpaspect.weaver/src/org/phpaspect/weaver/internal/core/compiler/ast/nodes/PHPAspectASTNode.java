package org.phpaspect.weaver.internal.core.compiler.ast.nodes;

import org.eclipse.dltk.ast.ASTNode;

public abstract class PHPAspectASTNode extends ASTNode {
	
	public PHPAspectASTNode(int start, int end) {
		super(start, end);
	}

	public static final int ASPECT_DECLARATION = 63;
	public static final int ASPECT_IDENTIFIER  = 64;
	public static final int ASPECT_INTERTYPE_FIELDS_DECLARATION = 65;
	public static final int ASPECT_INTERTYPE_FIELD_DECLARATION = 66;
	public static final int ASPECT_INTERTYPE_METHOD_DECLARATION = 67;
}