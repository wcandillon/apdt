package org.phpaspect.weaver.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.phpaspect.weaver.Weaver;
import org.phpaspect.weaver.parser.ASTGenerator;
import org.phpaspect.weaver.xslt.XSLTProcessor;

public class PHPAspectWeaver implements Weaver {
	
	private List<URI> aspects  = new ArrayList<URI>();
	private List<URI> phpFiles = new ArrayList<URI>();
	
	private URI runtimePath;
	
	private boolean verbose = false;
	
	public PHPAspectWeaver(){
		//Do nothing...
	}
	
	public PHPAspectWeaver(boolean verbose){
		setVerbose(verbose);
	}
	
	public PHPAspectWeaver(URI runtimePath){
		setRuntimePath(runtimePath);
	}
	
	public PHPAspectWeaver(URI runtimePath, boolean verbose){
		setVerbose(verbose);
		setRuntimePath(runtimePath);
	}
	
	public Weaver addAspect(URI aspect) {
		if(isAspect(aspect)){
			aspects.add(aspect);
		}
		return this;
	}


	public Weaver addAspects(List<URI> aspects) {
		for(URI aspect: aspects){
			addAspect(aspect);
		}
		return this;
	}
	
	public Weaver addPHPFile(URI phpFile) {
		if(isPHPFile(phpFile)){
			phpFiles.add(phpFile);
		}
		return this;
	}

	public Weaver addPHPFiles(List<URI> phpFiles) {
		for(URI phpFile: phpFiles){
			addPHPFile(phpFile);
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public List<URI> getAspects() {
		return (List<URI>)((ArrayList<URI>)aspects).clone();
	}

	public List<URI> getPHPFiles() {
		List<URI> clone = (List<URI>)((ArrayList<URI>)aspects).clone();
		return clone;
	}

	public Weaver setRuntimePath(URI runtimePath) {
		this.runtimePath = runtimePath;
		return this;
	}
	
	public URI getRuntimePath(){
		return runtimePath;
	}

	public boolean getVerbose() {
		return verbose;
	}

	public Weaver setVerbose(boolean verbose) {
		this.verbose = verbose;
		return this;
	}

	public Weaver weave() {
		for(URI aspect: aspects){
			weave(aspect);
		}
		return this;
	}
	
	private boolean isAspect(URI aspect){
		return getFileExtension(aspect).equals("ap");
	}
	
	private boolean isPHPFile(URI phpFile){
		return getFileExtension(phpFile).equals("php");
	}
	
	private String getFileExtension(URI uri){
		String filename = getAspectName(uri);
		String extension = filename.substring(filename.lastIndexOf('.')+1);
		return extension;
	}
	
	private String getAspectName(URI uri){
		String[] segments = uri.getPath().split("/");
		String filename = segments[segments.length-1];
		return filename;
	}

	public Weaver clear() {
		aspects  = new ArrayList<URI>();
		phpFiles = new ArrayList<URI>();
		return this;
	}

	public Weaver weave(URI phpFile) {
		// TODO Auto-generated method stub
		return this;
	}

	public Weaver removeAspect(URI aspect) {
		aspects.remove(aspect);
		return this;
	}

	public Weaver removeAspects(List<URI> aspects) {
		aspects.removeAll(aspects);
		return this;
	}

	public Weaver removePHPFile(URI phpFile) {
		phpFiles.remove(phpFile);
		return this;
	}

	public Weaver removePHPFiles(List<URI> phpFiles) {
		phpFiles.removeAll(phpFiles);
		return this;
	}

	public Weaver generateAspectEntities(URI runtimePath) throws Exception {
		setRuntimePath(runtimePath);
		return generateAspectEntities();
	}

	public Weaver generateAspectEntities() throws Exception {
		for(URI aspect: aspects){
			String aspectName = getAspectName(aspect);
			String aspectFileName = aspectName.substring(0, aspectName.lastIndexOf('.'));
			String path = runtimePath.getPath()+"/"+aspectFileName+".php";
			//OutputStream target = new FileOutputStream(path, false);
			
			//target.createNewFile();
			
			XSLTProcessor xslt = new XSLTProcessor();
			String xml = ASTGenerator.getXMLAstFromPHPAspect(aspect);
			
			xslt.setSource(new StringReader(xml));
			xslt.setStylesheet(XSLTProcessor.TO_CLASS);
			Writer output = new StringWriter();
			xslt.setOutput(output);
			xslt.setOutputMethod("xml");
			xslt.transform();

			ASTGenerator.printPHPFromAst(path, ASTGenerator.getAstFromXML(output.toString()));
		}
		return this;
	}	
}