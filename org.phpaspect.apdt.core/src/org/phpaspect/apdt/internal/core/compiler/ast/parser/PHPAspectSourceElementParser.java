package org.phpaspect.apdt.internal.core.compiler.ast.parser;

import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.phpaspect.apdt.internal.core.builder.PHPAspectNature;

public class PHPAspectSourceElementParser extends AbstractSourceElementParser {

	protected String getNatureId() {
		return PHPAspectNature.NATURE_ID;
	}
}
