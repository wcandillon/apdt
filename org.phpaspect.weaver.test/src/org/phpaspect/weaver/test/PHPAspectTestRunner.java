package org.phpaspect.weaver.test;

import org.phpaspect.weaver.test.parser.TestAST;
import org.phpaspect.weaver.test.weaver.TestWeaver;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PHPAspectTestRunner {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.phpaspect.weaver.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestAST.class);
		suite.addTestSuite(TestWeaver.class);
		//$JUnit-END$
		return suite;
	}

}
