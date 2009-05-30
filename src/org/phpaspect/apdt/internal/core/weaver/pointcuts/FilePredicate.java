package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.phpaspect.apdt.core.weaver.AbstractPointcut;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.Pointcut;

public class FilePredicate extends AbstractPointcut {

	private String file;
	
	public FilePredicate(String name, String file) {
		super(name);
		this.file = file.substring(1, file.length()-1).replace("*", ".*");
	}

	@Override
	public Pointcut clone() {
		return new FilePredicate(name, file);
	}

	@Override
	public boolean match(AST ast, Joinpoint jp) {
		boolean m = jp.getSourceLocation().getFileName().matches(file);
		if(m)
		{
			matched = true;
			return true;
		}
		return false;
	}

}
