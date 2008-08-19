package org.phpaspect.apdt.internal.core.compiler.ast.parser;

import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;

public class PHPAspectSourceElementRequestor extends SourceElementRequestVisitor {

	public PHPAspectSourceElementRequestor(ISourceElementRequestor requestor,
			char[] contents, char[] filename) {
		super(requestor);
	}

}
