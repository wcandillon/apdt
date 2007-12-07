package org.phpaspect.weaver.xslt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URI;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.Destination;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltTransformer;

public class XSLTProcessor{

	public static final Processor XSLT_PROC = new Processor(false);	
	public static final XsltTransformer TO_CLASS = newXsltTransformer("workspace/org.phpaspect.weaver/XSLT/toClass.xsl");
	
	private XdmNode source;
	private XsltTransformer stylesheet;
	private Serializer destination = new Serializer();
	
	public XSLTProcessor(){
		
	}
	
	public static XsltTransformer newXsltTransformer(String stylesheet) {
		try {
			return XSLT_PROC.newXsltCompiler()
							.compile(new StreamSource(stylesheet))
							.load();
		} catch (SaxonApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public XSLTProcessor setSource(XdmNode source){
		this.source = source;
		return this;
	}
	
	public XSLTProcessor setSource(Source source){
		try {
			setSource(XSLT_PROC.newDocumentBuilder().build(source));
		} catch (SaxonApiException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public XSLTProcessor setSource(URI source){
		try {
			setSource(new FileReader(source.getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public XSLTProcessor setSource(String source){
		return setSource(new StringReader(source));
	}
	
	public XSLTProcessor setSource(Reader source){
		return setSource(new StreamSource(source, "xmlsource"));
	}
	
	public XdmNode getSource(){
		return source;
	}
	
	public XsltTransformer getStylesheet(){
		return stylesheet;
	}
	
	public XSLTProcessor setStylesheet(URI stylesheet){
		return setStylesheet(stylesheet.toString());
	}
	
	public XSLTProcessor setStylesheet(String stylesheet){
		return setStylesheet(XSLTProcessor.newXsltTransformer(stylesheet));
	}
	
	public XSLTProcessor setStylesheet(XsltTransformer stylesheet){
		this.stylesheet = stylesheet;
		return this;
	}
	
	public Destination getDestination(){
		return destination;
	}

	public XSLTProcessor setOutputMethod(String method){
		destination.setOutputProperty(Serializer.Property.METHOD, method);
		return this;
	}
	
	public XSLTProcessor setOutputIdent(boolean indent){
		destination.setOutputProperty(Serializer.Property.INDENT, indent?"yes":"no");
		return this;
	}
	
	public XSLTProcessor setOutput(OutputStream output){
		destination.setOutputStream(output);
		return this;
	}
	
	public XSLTProcessor setOutput(String output){
		destination.setOutputFile(new File(output));
		return this;
	}
	
	public XSLTProcessor setOutput(File output){
		destination.setOutputFile(output);
		return this;
	}
	
	public XSLTProcessor setOutput(Writer output){
		destination.setOutputWriter(output);
		return this;
	}
	
	public XSLTProcessor transform() throws SaxonApiException{
		stylesheet.setInitialContextNode(source);
		stylesheet.setDestination(destination);
		stylesheet.transform();
		return this;
	}
}