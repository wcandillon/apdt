package org.phpaspect.apdt.core.weaver;

public abstract class AbstractJoinpoint implements Joinpoint
{
	protected Kind kind;
	protected SourceLocation sourceLocation;
	
	public AbstractJoinpoint(Kind kind, SourceLocation sourceLocation)
	{
		this.kind = kind;
		this.sourceLocation = sourceLocation;
	}
	
	public Kind getKind() {
		return kind;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	
	public String toLongString() {
		return toString()+" at "+sourceLocation;
	}
}

