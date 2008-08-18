package org.phpaspect.weaver.impl;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.SourceLocation;

public class JoinPointImpl implements JoinPoint {

	protected ASTNode node;
	protected Kind kind;
	protected SourceLocation sourceLocation;
	
	public JoinPointImpl(Kind kind, SourceLocation sourceLocation, ASTNode node){
		this.kind = kind;
		this.sourceLocation = sourceLocation;
		this.node = node;
	}
	
	public Kind getKind() {
		return kind;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public ASTNode getNode() {
		return node;
	}

	public String toLongString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return null;
	}

	public String toShortString() {
		// TODO Auto-generated method stub
		return null;
	}
}
