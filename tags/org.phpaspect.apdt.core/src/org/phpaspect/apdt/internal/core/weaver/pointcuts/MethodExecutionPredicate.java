package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.MethodDeclaration;
import org.phpaspect.apdt.core.weaver.AbstractPointcut;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.Pointcut;

public class MethodExecutionPredicate extends AbstractPointcut {

	protected String type;
	protected String method;
	
	public MethodExecutionPredicate(String name, String type, String method) {
		super(name);
		this.type = type.replace("*", ".*");
		this.method = method.replace("*", ".*");
	}

	@Override
	public Pointcut clone() {
		return new MethodExecutionPredicate(name, type, method);
	}

	@Override
	public boolean match(AST ast, Joinpoint jp) {
		if (jp.getKind() == Joinpoint.Kind.METHOD_EXECUTION){
			MethodDeclaration method = (MethodDeclaration)jp.getNode();
			if(method.getFunction().getFunctionName().getName().matches(this.method))
			{
				ASTNode parent = method.getParent().getParent();
				if(parent instanceof ClassDeclaration)
				{
					ClassDeclaration clazz = (ClassDeclaration)parent;
					boolean m = clazz.getName().getName().matches(type);
					if(m)
					{
						runtimeAssertion = ast.newScalar("true");
						matched = true;
						return true;
					}
				}
				return true;
			}
		}
		return false;
	}

}
