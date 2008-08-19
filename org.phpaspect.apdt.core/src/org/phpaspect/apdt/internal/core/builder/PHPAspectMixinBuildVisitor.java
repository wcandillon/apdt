package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.php.core.PHPMixinBuildVisitorExtension;
import org.eclipse.php.internal.core.ast.nodes.*;

public class PHPAspectMixinBuildVisitor extends PHPMixinBuildVisitorExtension {
	public boolean visit(Expression expression) throws Exception {
		System.out.println("===============================");
		return true;
	}
}
