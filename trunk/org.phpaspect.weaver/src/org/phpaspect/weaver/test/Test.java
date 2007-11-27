package org.phpaspect.weaver.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.Destination;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltTransformer;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {	

		Processor p = new Processor(true);
		try {
			XsltTransformer transformer =
				p.newXsltCompiler().compile(new StreamSource("xslt/toWrite.xsl")).load();
			transformer.setSource(new StreamSource("xslt"));
			transformer.setDestination((Destination) new StreamSource("xslt"));
			transformer.transform();
		} catch (SaxonApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		UnparseVisitor unparseVisitor = new UnparseVisitor();
//		unparseVisitor.visit(newAst);
//		System.out.println(UnparseVisitor.buffer);
	}
	
	private static String getNodeName(String className){
		String[] str = className.split("\\.");
		return str[str.length-1];
	}
}