package org.phpaspect.weaver.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.phpaspect.weaver.XMLWeaver;
import org.phpaspect.weaver.parser.ASTGenerator;

public class PHPAspectXMLWeaver implements XMLWeaver{
	
	private List<Source> aspects = new ArrayList<Source>();
	
	public static final Source TO_CLASS = new StreamSource("XSLT/toClass.xsl");
	
	public PHPAspectXMLWeaver(){
		
	}
	
	public XMLWeaver loadAspect(URI aspect){
		//String xslt = transfromAspecToXSLT(aspect);
		aspects.add(new StreamSource(aspect.toString()));
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