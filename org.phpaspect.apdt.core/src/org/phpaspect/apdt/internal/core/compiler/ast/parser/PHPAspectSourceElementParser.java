package org.phpaspect.apdt.internal.core.compiler.ast.parser;

import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.phpaspect.apdt.internal.core.builder.PHPAspectNature;

public class PHPAspectSourceElementParser extends AbstractSourceElementParser {

	private char[] contents;
	private char[] filename;

	public void parseSourceModule(char[] contents, ISourceModuleInfo astCache, char[] filename) {
		this.contents = contents;
		this.filename = filename;
		super.parseSourceModule(contents, astCache, filename);
	}

	protected SourceElementRequestVisitor createVisitor() {
		return new PHPAspectSourceElementRequestor(getRequestor(), contents, filename);
	}

	protected String getNatureId() {
		return PHPAspectNature.NATURE_ID;
	}
}
