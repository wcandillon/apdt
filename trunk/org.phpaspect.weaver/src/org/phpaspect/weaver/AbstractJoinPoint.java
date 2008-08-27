package org.phpaspect.weaver;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;

public abstract class AbstractJoinPoint implements JoinPoint {

	protected ASTNode node;
	protected Kind kind;
	protected SourceLocation sourceLocation;
	
	public AbstractJoinPoint(Kind kind, SourceLocation sourceLocation, ASTNode node){
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
}
