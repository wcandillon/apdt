package org.phpaspect.apdt.model;

import java.io.Reader;

import org.eclipse.php.internal.core.phpModel.parser.PHPParserManager;
import org.eclipse.php.internal.core.phpModel.parser.PhpParser;
import org.eclipse.php.internal.core.phpModel.parser.CompletionLexer;
import org.phpaspect.apdt.core.parser.PHPAspectCompletionLexer;
import org.phpaspect.apdt.core.parser.PHPAspectParser;

public class PHPAspectParserManager extends PHPParserManager{
	protected CompletionLexer createCompletionLexer(Reader reader) {
		return new PHPAspectCompletionLexer(reader);
	}

	protected PhpParser createPhpParser() {
		return new PHPAspectParser();
	}
}
