package org.phpaspect.weaver.impl;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.eclipse.php.internal.core.ast.nodes.Expression;
import org.phpaspect.weaver.JoinPoint;
import org.phpaspect.weaver.Pointcut;

public class PointcutImpl implements Pointcut {

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean match(JoinPoint jp) {
		// TODO Auto-generated method stub
		return false;
	}

	public Expression getRuntimeAssertion() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean match(AST ast, JoinPoint jp) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Pointcut clone(){
		return null;
	}

}
