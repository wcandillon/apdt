package org.phpaspect.internal.core.weaver;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.phpaspect.core.weaver.Pointcut;

public class Weaver {
    
    private final static IPath weavedFolder = new Path("weaved");
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

	public synchronized Weaver weave(IProject project, IProgressMonitor monitor) throws CoreException
	{
	    //Create the weaved directory
	    IFolder root = project.getFolder(weavedFolder);
	    if(root.exists())
	    {
            root.delete(IResource.KEEP_HISTORY, monitor);
	    }
	    root.create(IResource.KEEP_HISTORY, true, monitor);
        //Load project's aspects
	    AspectFilesVisitor aspectVisitor = new AspectFilesVisitor();
        project.accept(aspectVisitor);
        pointcuts = aspectVisitor.getPointcuts();
        //Copy php project
        project.accept(new CopySourceFilesVisitor(project.getName(), monitor));
        //Weave php files
	    root.accept(new PHPFilesVisitor(monitor, pointcuts));
		return this;
	}
}
