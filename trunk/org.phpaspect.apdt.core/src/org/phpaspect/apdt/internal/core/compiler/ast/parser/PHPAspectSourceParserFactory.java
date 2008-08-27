package org.phpaspect.apdt.internal.core.compiler.ast.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class PHPAspectSourceParserFactory extends AbstractSourceParser implements ISourceParserFactory, ISourceParser {

	public ISourceParser createSourceParser() {
		return this;
	}

	public ModuleDeclaration parse(char[] fileName, char[] source, IProblemReporter reporter) {
		PHPAspectSourceParser parser = createParser(new String(fileName));
		return parser.parse(fileName, source, reporter);
	}

	protected PHPAspectSourceParser createParser(String fileName) {
		return new PHPAspectSourceParser(fileName);
	}
}
