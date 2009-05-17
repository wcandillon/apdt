package org.phpaspect.weaver.test.weaver;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.php.test.infra.project.TestProject;

import org.phpaspect.weaver.pointcuts.MethodInvocationPredicate;
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
        weaver.addPointcut(new MethodInvocationPredicate("Foo", "bar", false));
        String wovenProgram = weaver.weave();
        System.out.println("woven program:");
        System.out.println(wovenProgram);
        project.dispose();
	}
	
	public void testSimple1Weaving() throws Exception{
		Reader file = new FileReader(new File("Classes/simple1.php"));
		StringBuffer content = new StringBuffer();
		while(file.ready()){
			content.append((char)file.read());
		}
		TestProject project = new TestProject();
		IResource script = project.createScript("simple1.php", content.toString());
        WeaverVisitor weaver = new WeaverVisitor((IFile)script, "simple1.php");
        weaver.addPointcut(new MethodInvocationPredicate("Foo", "fuebar"));
        String wovenProgram = weaver.weave();
        //System.out.println("woven program:");
        //System.out.println(wovenProgram);
        project.dispose();
	}
	
	public void testOrderWeaving() throws Exception{
		Reader file = new FileReader(new File("Classes/order.php"));
		StringBuffer content = new StringBuffer();
		while(file.ready()){
			content.append((char)file.read());
		}
		TestProject project = new TestProject();
		IResource script = project.createScript("order.php", content.toString());
        WeaverVisitor weaver = new WeaverVisitor((IFile)script, "order.php");
        //weaver.addPointcut(new MethodInvocationPredicate("Foo", "fuebar"));
        String wovenProgram = weaver.weave();
        System.out.println("woven program:");
        System.out.println(wovenProgram);
        project.dispose();
	}
}
