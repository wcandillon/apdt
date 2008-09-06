package org.phpaspect.weaver.test.weaver;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.php.test.infra.project.TestProject;
import org.phpaspect.weaver.pointucts.MethodInvocationPredicate;
import org.phpaspect.weaver.visitor.RuntimeVisitor;
import org.phpaspect.weaver.visitor.WeaverVisitor;

import junit.framework.TestCase;

public class TestAspectGeneration extends TestCase {

	public void testSimpleAspect() throws Exception{
		Reader file = new FileReader(new File("Aspects/SimpleDeclaration.ap"));
		StringBuffer content = new StringBuffer();
		while(file.ready()){
			content.append((char)file.read());
		}
		TestProject project = new TestProject();
		IResource script = project.createScript("simple.php", content.toString());
		RuntimeVisitor visitor = new RuntimeVisitor((IFile)script, "simple.php");
        String runtimeAspect = visitor.generate();
        System.out.println("aspect runtime:");
        System.out.println(runtimeAspect);
        project.dispose();
	}
}
