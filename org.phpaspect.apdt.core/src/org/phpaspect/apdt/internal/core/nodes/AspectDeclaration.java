package org.phpaspect.apdt.internal.core.nodes;

import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.Block;
import org.eclipse.php.internal.core.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.ast.nodes.Identifier;

public class AspectDeclaration extends ClassDeclaration {

	public AspectDeclaration(int start, int end, int modifier,
			Identifier className, Identifier superClass, List interfaces,
			Block body) {
		super(start, end, modifier, className, superClass, interfaces, body);
		// TODO Auto-generated constructor stub
	}

}
