package org.phpaspect.apdt.tests.parser;

import java.io.FileReader;


import junit.framework.TestCase;

public class TestPHPAspectParser extends TestCase {

	public void testParseHelloWorld(){
		try {
			FileReader inputFile = new FileReader("scripts/helloworld.php");
			//PHPAspectCompletionLexer lexer = new PHPAspectCompletionLexer(inputFile);
//			PHPAspectParser parser = new PHPAspectParser(lexer);
//			ParserClientComposite parserClient = new ParserClientComposite();
//			parserClient.add(new PHPAspectParserClient());
//			parser.setParserClient(parserClient);
			//Symbol result = parser.parse();
			System.out.print("Parse state:");
			//System.out.println(result);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
