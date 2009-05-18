package org.phpaspect.internal.core.weaver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.phpaspect.apdt.core.APDTCorePlugin;
import org.phpaspect.core.weaver.Pointcut;

public class Weaver {
    
    private final static IPath weavedFolder = new Path("weaved");
    private final static IPath runtimeFolder = weavedFolder.append("PHPAspect");
    private static Weaver instance = null;
    
    private List<Pointcut> pointcuts;
    
    private Weaver(){}
    
    public static Weaver getInstance()
    {
        if(instance == null)
        {
            instance = new Weaver();
        }
        return instance;
    }

	public synchronized Weaver weave(IProject project, IProgressMonitor monitor) throws CoreException, IOException
	{
	    //Create the weaved directory
	    IFolder root = project.getFolder(weavedFolder);
	    if(root.exists())
	    {
            root.delete(IResource.KEEP_HISTORY, monitor);
	    }
	    root.create(IResource.KEEP_HISTORY, true, monitor);
	    project.getFolder(runtimeFolder).create(IResource.KEEP_HISTORY, true, monitor);
        //Load project's aspects
	    AspectFilesVisitor aspectVisitor = new AspectFilesVisitor(monitor);
        project.accept(aspectVisitor);
        pointcuts = aspectVisitor.getPointcuts();
        //Copy php project
        project.accept(new CopySourceFilesVisitor(project.getName(), monitor));
        //Weave php files
	    root.accept(new PHPFilesVisitor(monitor, pointcuts));
	    //Copy the PHPAspect runtime in the weaved folder
	    String bundleLocation = APDTCorePlugin.getDefault().getBundle().getLocation();
	    bundleLocation = bundleLocation.substring(10);
	    URL runtime = null;
		try {
			runtime = new URL(bundleLocation+"PHPAspect/");
		} catch (MalformedURLException e1) {
			throw new IOException("Couldn't build bundle location: "+bundleLocation);
		}
	    File src = null;
	    try {
	    	src = new File(runtime.toURI());
	    } catch (URISyntaxException e) {
	    	src = new File(runtime.getPath());
		}
	    try {
			Utils.copyFiles(src, project.getFile(runtimeFolder).getLocation().toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO: is it necessary
	    root.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		return this;
	}
}
