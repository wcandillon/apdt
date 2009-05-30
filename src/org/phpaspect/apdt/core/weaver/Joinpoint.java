package org.phpaspect.apdt.core.weaver;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;

public interface Joinpoint
{
	public enum Kind
	{
		CONSTRUCTOR_CALL("ConstructorInvocationJoinpoint"),
		METHOD_CALL("MethodInvocationJoinpoint"),
		STATIC_METHOD_CALL("MethodInvocationJoinpoint"),
		METHOD_EXECUTION("MethodExecutionJoinpoint");

		private String name;
		
		Kind(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return name;
		}
	};

    public Kind getKind();
 
    public SourceLocation getSourceLocation();

    public ASTNode getNode();

    public String toLongString();

    public String toString();
}