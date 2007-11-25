package org.phpaspect.weaver.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.phpaspect.weaver.Weaver;

public class PHPAspectWeaver implements Weaver {

	private List<URI> aspects  = new ArrayList<URI>();
	private List<URI> phpFiles = new ArrayList<URI>();
	
	private URI runtimePath;
	
	private boolean verbose = false;
	
	public PHPAspectWeaver(){
		
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
		aspects.add(aspect);
		return this;
	}


	public Weaver addAspects(List<URI> aspects) {
		this.aspects.addAll(aspects);
		return this;
	}
	
	public Weaver addPHPFile(URI phpFile) {
		phpFiles.add(phpFile);
		return this;
	}

	public Weaver addPHPFiles(List<URI> phpFiles) {
		this.phpFiles.addAll(phpFiles);
		return this;
	}

	public List<URI> getAspects() {
		return aspects;
	}

	public List<URI> getPHPFiles() {
		return phpFiles;
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
		
		return this;
	}
	
	private boolean isAspect(URI aspect){
		return false;
	}
	
	private boolean isPHPFile(URI phpFile){
		return false;
	}
	
}
