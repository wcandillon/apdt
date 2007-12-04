package org.phpaspect.weaver.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XsltExecutable;

import org.phpaspect.weaver.XMLWeaver;
import org.phpaspect.weaver.parser.ASTGenerator;

public class PHPAspectXMLWeaver implements XMLWeaver{
	
	private List<Source> aspects = new ArrayList<Source>();
	
	public static final XsltExecutable TO_CLASS = newXsltExecutable("XSLT/toClass.xsl");
	
	public PHPAspectXMLWeaver(){
		
	}
	
	private static XsltExecutable newXsltExecutable(String filename) {
		try {
			return new Processor(false).newXsltCompiler()
						.compile(new StreamSource(filename));
		} catch (SaxonApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	public XMLWeaver loadAspect(URI aspect){
		//String xslt = transfromAspecToXSLT(aspect);
		//aspects.add(new StreamSource(aspect.toString()));
		return this;
	}
	
	private String transfromAspecToXSLT(URI aspect) {
		// TODO Auto-generated method stub
		return null;
	}

	public XMLWeaver clearAspects(){
		aspects = new ArrayList<Source>();
		return this;
	}
	
	
	public String transformAspecttoClass(URI aspect) throws Exception{
		return transformAspectToClass(ASTGenerator.getXMLAstFromPHPAspect(aspect));
	}

	public String transformAspectToClass(String aspect){
		return null;
	}
	
	public String transformAspectToXSLT(URI aspect) throws Exception{
		return transformAspectToXSLT(ASTGenerator.getXMLAstFromPHPAspect(aspect));
	}
	
	public String transformAspectToXSLT(String aspect){
		return null;
	}
	
	public String weavePHPSource(URI phpSource){
		return null;
	}
}