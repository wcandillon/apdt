package org.phpaspect.weaver.test;

import org.phpaspect.weaver.test.parser.TestAST;
import org.phpaspect.weaver.test.weaver.TestAspectGeneration;
import org.phpaspect.weaver.test.weaver.TestWeaver;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PHPAspectTestRunner {
	public static Test suite() {
		TestSuite suite = new TestSuite("org.phpaspect.weaver.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestWeaver.class);
		suite.addTestSuite(TestAST.class);
		suite.addTestSuite(TestAspectGeneration.class);
		//$JUnit-END$
		return suite;
	}
}
