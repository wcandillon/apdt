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
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.phpaspect.apdt.core.APDTCorePlugin;
import org.phpaspect.apdt.core.weaver.Pointcut;

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
	    //Load project's aspects
	    AspectFilesVisitor aspectVisitor = new AspectFilesVisitor(monitor);
        project.accept(aspectVisitor);
        pointcuts = aspectVisitor.getPointcuts();
        //Copy php project
        project.accept(new CopySourceFilesVisitor(project.getName(), monitor));
        //Weave php files
	    root.accept(new PHPFilesVisitor(monitor, pointcuts));
	    //Copy PHP aspect runtime
	    PHPAspectRuntime.run(project);
	    root.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		return this;
	}
}
