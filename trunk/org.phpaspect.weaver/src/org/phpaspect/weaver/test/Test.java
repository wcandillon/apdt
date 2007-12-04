package org.phpaspect.weaver.test;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.TransformerFactoryImpl;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

public class Test {

	private static TransformerFactoryImpl factory = new TransformerFactoryImpl();
	/**
	 * @param args
	 * @throws SaxonApiException 
	 */
	public static void main(String[] args) throws SaxonApiException {			
        Processor proc = new Processor(false);
        XsltCompiler comp = proc.newXsltCompiler();
        XsltExecutable exp = comp.compile(new StreamSource(new File("/home/wcandillon/workspace/org.phpaspect.weaver/XSLT/toDot.xsl")));
        XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new File("/home/wcandillon/workspace/org.phpaspect.weaver/Tests/Singleton.xml")));
        Serializer out = new Serializer();
        out.setOutputProperty(Serializer.Property.METHOD, "text");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        out.setOutputFile(new File("Singleton.ap"));
        XsltTransformer trans = exp.load();
        trans.setInitialContextNode(source);
        trans.setDestination(out);
        trans.transform();
        System.err.println("Output written to Singleton.ap");

		
//		UnparseVisitor unparseVisitor = new UnparseVisitor();
//		unparseVisitor.visit(newAst);
//		System.out.println(UnparseVisitor.buffer);
	}
}