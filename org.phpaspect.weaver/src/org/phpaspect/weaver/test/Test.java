package org.phpaspect.weaver.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.net.URI;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.php.internal.core.ast.nodes.Program;
import org.eclipse.php.internal.core.ast.visitor.CodeBuilder;
import org.phpaspect.weaver.internal.core.compiler.ast.ASTGenerator;
import org.phpaspect.weaver.xslt.XSLTProcessor;

import net.sf.saxon.TransformerFactoryImpl;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

public class Test {

	/**
	 * @param args
	 * @throws SaxonApiException 
	 */
	public static void main(String[] args) {
		try {
			String xml = ASTGenerator.getXMLAstFromPHPAspect(new URI("/home/wcandillon/apdt/wokspace/org.phpaspect.weaver/Tests/Singleton.ap"));
			System.out.println(xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		Source source = null;
		//source = new StreamSource(new File("/home/wcandillon/workspace/org.phpaspect.weaver/Tests/Singleton.xml"));
		source = new StreamSource(new StringReader(xml), "source");
		xslt.setSource(source);
		xslt.setStylesheet(XSLTProcessor.TO_CLASS);
		xslt.setOutput("/home/wcandillon/apdt/wokspace/Singleton.xml");
		xslt.setOutputMethod("xml");
		xslt.transform();*/
/*		
		try {
			Program ast = ASTGenerator.getAstFromXML(new FileReader("/home/wcandillon/workspace/org.phpaspect.weaver/Tests/Singleton.xml"));
			CodeBuilder unparser = new CodeBuilder();
			unparser.visit(ast);
			System.err.println(unparser.buffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}