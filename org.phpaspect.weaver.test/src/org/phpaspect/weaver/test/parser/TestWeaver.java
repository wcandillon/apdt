package org.phpaspect.weaver.test.parser;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.php.test.infra.project.TestProject;

import org.phpaspect.weaver.impl.MethodInvocationPredicate;
import org.phpaspect.weaver.visitor.WeaverVisitor;

import junit.framework.TestCase;

public class TestWeaver extends TestCase{

	public void testSimpleWeaving() throws Exception{
		Reader file = new FileReader(new File("Classes/simple.php"));
		StringBuffer content = new StringBuffer();
		while(file.ready()){
			content.append((char)file.read());
		}
		TestProject project = new TestProject();
		IResource script = project.createScript("simple.php", content.toString());
        WeaverVisitor weaver = new WeaverVisitor((IFile)script, "simple.php");
        weaver.addPointcut(new MethodInvocationPredicate("Foo", "bar"));
        String wovenProgram = weaver.weave();
        System.out.println("woven program:");
        System.out.println(wovenProgram);
        project.dispose();
	}
}
