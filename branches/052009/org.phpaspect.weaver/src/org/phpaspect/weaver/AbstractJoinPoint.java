package org.phpaspect.weaver;

public abstract class AbstractJoinPoint implements JoinPoint {

	protected Kind kind;
	protected SourceLocation sourceLocation;
	
	public AbstractJoinPoint(Kind kind, SourceLocation sourceLocation){
		this.kind = kind;
		this.sourceLocation = sourceLocation;
	}
	
	public Kind getKind() {
		return kind;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
}
