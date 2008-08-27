package org.phpaspect.weaver;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;

public interface JoinPoint {

	public enum Kind { CONSTRUCTOR_CALL, CONSTRUCTOR_EXECUTION, EXCEPTION_HANDLER,
                       FIELD_GET, FIELD_SET, METHOD_CALL, STATIC_METHOD_CALL, METHOD_EXECUTION };

    public Kind getKind();
 
    public SourceLocation getSourceLocation();

    public ASTNode getNode();

    public String toLongString();

    public String toString();

    public String toShortString();
}
