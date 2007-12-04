package org.phpaspect.weaver.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.phpaspect.weaver.Weaver;
import org.phpaspect.weaver.XMLWeaver;

public class PHPAspectWeaver implements Weaver {

	private XMLWeaver xmlWeaver = new PHPAspectXMLWeaver();
	
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
		if(getFileExtension(aspect) == "ap"){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isPHPFile(URI phpFile){
		if(getFileExtension(phpFile) == "php"){
			return true;
		}else{
			return false;
		}
	}
	
	private String getFileExtension(URI uri){
		String[] segments = uri.getPath().split("/");
		String filename = segments[segments.length-1];
		return filename.substring(filename.lastIndexOf('.')+1);
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

	public Weaver generateAspectEntities(URI runtimePath) {
		setRuntimePath(runtimePath);
		return generateAspectEntities();
	}

	public Weaver generateAspectEntities() {
		for(URI aspect: aspects){
			xmlWeaver.toString();
		}
		return this;
	}	
}