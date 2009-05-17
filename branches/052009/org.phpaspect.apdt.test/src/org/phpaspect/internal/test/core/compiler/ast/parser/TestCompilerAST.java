package org.phpaspect.internal.test.core.compiler.ast.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.php.internal.core.compiler.ast.nodes.ASTError;

import org.phpaspect.apdt.internal.core.compiler.ast.parser.PHPAspectParser;

import org.phpaspect.weaver.ast.scanner.PHPAspectLexer;

/**
 * Testing AST creation for PHPAspect
 */
public class TestCompilerAST extends TestCase {
	
	public void testAspectDeclaration() throws Exception {
		Reader source = new FileReader(new File("aspects/simple.ap"));
		List<ASTError> errors = testAspect(source);
		assertTrue(errors.size() == 0);
	}
	
	public void testAspectSyntaxError() throws Exception{
		Reader reader = new FileReader(new File("errors/error1.ap"));
		List<ASTError> errors = testAspect(reader);
		printErrors("errors/error1.ap", errors);
		List<ASTError> expectedErrors = new LinkedList<ASTError>();
		expectedErrors.add(new ASTError(6, 11));
		compareErrors(errors, expectedErrors);
	}
	
	private void compareErrors(List<ASTError> errors, List<ASTError> expectedErrors) {
		assertTrue(errors.size() == expectedErrors.size());
		for(int i=0; i<errors.size(); i++)
		{
			assertTrue(errors.get(i).sourceStart() == expectedErrors.get(i).sourceStart());
			assertTrue(errors.get(i).sourceEnd() == expectedErrors.get(i).sourceEnd());
		}
	}

	private List<ASTError> testAspect(Reader reader) throws Exception{
		PHPAspectLexer lexer = new PHPAspectLexer(reader);
		PHPAspectParser parser = new PHPAspectParser(lexer);
		parser.parse();
		List<ASTError> errors = parser.getModuleDeclaration().getAllErrors();
		return errors;
	}
	
	private void printErrors(String fileName, List<ASTError> errors) throws IOException
	{
		for(ASTError error: errors){
			System.err.print("Syntax error, unexpected ");
			char[] source = new char[error.sourceEnd()-error.sourceStart()];
			Reader sourceReader = new FileReader(new File(fileName));
			String str = "\n";
			while(sourceReader.ready())
			{
				str += (char)sourceReader.read();
			}
			System.err.println("\nStart: "+error.sourceStart());
			System.err.println("End: "+error.sourceEnd());
			str.getChars(error.sourceStart()+1, error.sourceEnd()+1, source, 0);
			System.err.println(source);
		}
	}
}