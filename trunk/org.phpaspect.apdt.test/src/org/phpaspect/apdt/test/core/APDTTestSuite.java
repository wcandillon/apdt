package org.phpaspect.apdt.test.core;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.phpaspect.internal.test.core.compiler.ast.parser.TestCompilerAST;

public class APDTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("org.phpaspect.apdt.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestCompilerAST.class);
		//$JUnit-END$
		return suite;
	}
}