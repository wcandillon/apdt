package org.phpaspect.weaver.test;

import org.phpaspect.weaver.test.parser.TestPHPAspectParser;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PHPAspectTestRunner {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.phpaspect.weaver.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestPHPAspectParser.class);
		//$JUnit-END$
		return suite;
	}

}
