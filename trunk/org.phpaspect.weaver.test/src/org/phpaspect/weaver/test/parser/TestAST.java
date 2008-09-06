package org.phpaspect.weaver.test.parser;

import java.io.FileReader;
import java.io.Reader;

import org.phpaspect.weaver.ast.nodes.AST;
import org.phpaspect.weaver.ast.nodes.Program;

import junit.framework.TestCase;

public class TestAST extends TestCase{
	
	public void testAspectDeclaration() throws Exception{
		checkAST(new FileReader("Aspects/SimpleDeclaration.ap"));
	}
	
	public void testWrongAspectDeclaration() throws Exception{
		checkAST(new FileReader("Aspects/WrongDeclaration.ap"), true);
	}
	
	public void testAspectAttributes() throws Exception{
		checkAST(new FileReader("Aspects/AspectAttributes.ap"));
	}
	
	public void testAspectMethods() throws Exception{
		checkAST(new FileReader("Aspects/AspectMethods.ap"));
	}
	
	public void testAspectIntroduction() throws Exception{
		checkAST(new FileReader("Aspects/AspectIntroduction.ap"));
	}
	
	public void testAspectMethodInvocationJoinPoint() throws Exception{
		checkAST(new FileReader("Aspects/AspectMethodInvocationJoinPoint.ap"));
	}
	
	public void testAspectTestPointcut() throws Exception{
		checkAST(new FileReader("Aspects/AspectTestPointcut.ap"));
	}

	private void checkAST(Reader source) throws Exception{
		checkAST(source, false);
	}
	
	private void checkAST(Reader source, boolean shouldFail) throws Exception {
		AST ast = new AST(source, "PHPAspect", false);
		Object value = ast.parser().parse().value;
		if(shouldFail){
			assertTrue(value instanceof Boolean);
		}else{
			assertTrue(value instanceof Program);
		}
	}
}