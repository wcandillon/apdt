package org.phpaspect.weaver.test.parser;

import java.io.Reader;
import java.io.StringReader;

import org.phpaspect.weaver.ast.nodes.AST;

import junit.framework.TestCase;

public class TestAST extends TestCase{
	
	public void testAspectDeclaration() throws Exception{
		checkAST("<?php aspect class Foo{} ?>");
	}

	private void checkAST(String source) throws Exception{
		checkAST(source, true);
	}
	
	private void checkAST(String source, boolean shouldFail) throws Exception {
		Reader reader = new StringReader(source);
		AST ast = new AST(reader, "PHPAspect", false);
		ast.parser().parse();
		//assertTrue((symbol == null) == shouldFail);
	}
}