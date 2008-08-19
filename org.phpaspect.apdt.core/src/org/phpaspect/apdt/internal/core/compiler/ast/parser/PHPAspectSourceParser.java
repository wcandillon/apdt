package org.phpaspect.apdt.internal.core.compiler.ast.parser;

import java.io.Reader;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.php.internal.core.compiler.ast.parser.AbstractPHPSourceParser;
import org.phpaspect.weaver.ast.scanner.PHPAspectLexer;

public class PHPAspectSourceParser extends AbstractPHPSourceParser {
	
	public PHPAspectSourceParser() {
		super();
	}

	public PHPAspectSourceParser(String fileName) {
		super(fileName);
	}

	public ModuleDeclaration parse(Reader in, IProblemReporter reporter) throws Exception {
		PHPAspectLexer lexer = new PHPAspectLexer(in);
		PHPAspectParser parser = new PHPAspectParser(lexer);
		parser.setProblemReporter(reporter);
		return parse(parser);
	}

}
