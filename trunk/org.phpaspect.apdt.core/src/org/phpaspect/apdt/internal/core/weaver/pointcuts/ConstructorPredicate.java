package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.ClassInstanceCreation;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.phpaspect.apdt.core.weaver.AbstractPointcut;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.Pointcut;

public class ConstructorPredicate extends AbstractPointcut
{

	private String type;
	
	public ConstructorPredicate(String name, String type)
	{
		super(name);
		this.type = type.replace("*", ".*");
	}

	@Override
	public Pointcut clone()
	{
		return new ConstructorPredicate(name, type);
	}

	@Override
	public boolean match(AST ast, Joinpoint jp)
	{
		if (jp.getKind() == Joinpoint.Kind.CONSTRUCTOR_CALL)
		{
			ClassInstanceCreation instance = (ClassInstanceCreation)jp.getNode();
			Expression name = instance.getClassName().getName();
			if(name.isStaticScalar())
			{
				if(name.toString().matches(type))
				{
					runtimeAssertion = ast.newScalar("true");
					matched = true;
					return true; 
				}
			}
		}
		return false;
	}
}
