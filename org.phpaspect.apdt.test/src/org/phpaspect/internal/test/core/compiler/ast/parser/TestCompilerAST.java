package org.phpaspect.internal.test.core.compiler.ast.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.eclipse.php.internal.core.compiler.ast.nodes.ASTError;
import org.eclipse.php.test.infra.testcase.HeadlessTest;
import org.eclipse.php.test.infra.testcase.PhpTestCase;

import org.junit.Test;

import org.phpaspect.apdt.internal.core.compiler.ast.parser.PHPAspectParser;

import org.phpaspect.weaver.ast.scanner.PHPAspectLexer;

/**
 * Testing AST creation for PHPAspect
 */
//@HeadlessTest
public class TestCompilerAST extends PhpTestCase {
	
	@Test
	public void testAspectDeclaration() throws Exception {
		String str = "<?php aspect Foo{ public $foo = 3;" +
				" 				public function bar(){" +
				"					return true;" +
				" 				} " +
				"			}" +
				"//foo";
		testAspect(str);
	}
	
	private void testAspect(String str) throws Exception{
		Reader reader = new StringReader(str);
		PHPAspectLexer lexer = new PHPAspectLexer(reader);
		PHPAspectParser parser = new PHPAspectParser(lexer);
		parser.parse();
		List<ASTError> errors = parser.getModuleDeclaration().getAllErrors();
		for(ASTError error: errors){
			System.err.print("Syntax error, unexpected ");
			char[] source = new char[error.sourceEnd()-error.sourceStart()];
			str.getChars(error.sourceStart(), error.sourceEnd(), source, 0);
			System.err.println(source);
		}
	}
}
