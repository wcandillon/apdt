package org.phpaspect.apdt.test.core.parser;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.Assert;

import org.eclipse.php.test.infra.testcase.PhpTestCase;
import org.junit.Test;
import org.phpaspect.apdt.internal.core.parser.PHPAspectParserFactory;

public class TestPHPAspectParser extends PhpTestCase {
	
	@Test	
	public void testDeclaration1(){
		String str = "<?php aspect A{} ?>";
		parseMustSucceed(str);
	}
	
	@Test	
	public void testWrongDeclaration1(){
		String str = "<?php aspect A A{} ?>";
		parseMustFailed(str);
	}

	private void parseMustSucceed(String str){
		try {
			PHPAspectParserFactory.getParser(new StringReader(str)).parse();
		} catch (IOException e) {
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
		Assert.assertTrue(true);
	}
	
	private void parseMustFailed(String str){
		try {
			PHPAspectParserFactory.getParser(new StringReader(str)).parse();
		} catch (IOException e) {
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		Assert.assertTrue(false);
	}
}