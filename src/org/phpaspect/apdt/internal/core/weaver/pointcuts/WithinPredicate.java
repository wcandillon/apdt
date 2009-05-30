package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.phpaspect.apdt.core.weaver.AbstractPointcut;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.Pointcut;

public class WithinPredicate extends AbstractPointcut
{

	private String within;
	
	public WithinPredicate(String name, String within)
	{
		super(name);
		this.within = within;
	}

	@Override
	public Pointcut clone()
	{
		return new WithinPredicate(name, within);
	}

	@Override
	public boolean match(AST ast, Joinpoint jp)
	{
		String within = jp.getSourceLocation().getWithinType();
		assert within != null;
		if(within.matches(this.within))
		{
			matched = true;
			runtimeAssertion = ast.newScalar("true");
			return true;
		}
		return false;
	}

}
