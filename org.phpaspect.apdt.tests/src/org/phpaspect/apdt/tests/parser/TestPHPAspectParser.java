package org.phpaspect.apdt.tests.parser;

import java.io.FileReader;

import org.eclipse.php.internal.core.phpModel.javacup.runtime.Symbol;
import org.eclipse.php.internal.core.phpModel.parser.CompletionLexer;
import org.eclipse.php.internal.core.phpModel.parser.ParserClientComposite;
import org.phpaspect.apdt.core.parser.PHPAspectCompletionLexer;
import org.phpaspect.apdt.core.parser.PHPAspectParser;
import org.phpaspect.apdt.core.parser.PHPAspectParserClient;

import junit.framework.TestCase;

public class TestPHPAspectParser extends TestCase {

	public void testParseHelloWorld(){
		try {
			FileReader inputFile = new FileReader("scripts/helloworld.php");
			CompletionLexer lexer = new PHPAspectCompletionLexer(inputFile);
			PHPAspectParser parser = new PHPAspectParser(lexer);
			ParserClientComposite parserClient = new ParserClientComposite();
			parserClient.add(new PHPAspectParserClient());
			parser.setParserClient(parserClient);
			Symbol result = parser.parse();
			System.out.print("Parse state:");
			System.out.println(result);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
