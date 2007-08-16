package org.phpaspect.apdt.test.core.parser;

import java.io.IOException;

import org.phpaspect.apdt.internal.core.parser.PHPAspectParserFactory;

import junit.framework.TestCase;

public class TestPHPAspectParser extends TestCase {
	
	public void testAspectDeclaration(){
		try {
			PHPAspectParserFactory.create("aspects/declarations/simple.ap").parse();
			assertTrue(true);
		} catch (IOException e) {
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
