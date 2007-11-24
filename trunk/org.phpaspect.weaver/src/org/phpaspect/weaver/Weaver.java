package org.phpaspect.weaver;

import java.net.URI;
import java.util.List;

public interface Weaver {
	
	public Weaver addAspect(URI aspect);
	
	public Weaver addAspects(List<URI> aspects);
	
	public Weaver addPHPFiles(List<URI> phpFiles);
	
	public Weaver addPHPFile(URI phpFile);
	
	public Weaver setRuntimePath(URI runtimePath);
	
	public Weaver setVerbose(boolean verbose);
	
	public boolean getVerbose();
	
	public URI getRuntimePath();
	
	public List<URI> getPHPFiles();
	
	public List<URI> getAspects();
	
	public Weaver weave();
}
