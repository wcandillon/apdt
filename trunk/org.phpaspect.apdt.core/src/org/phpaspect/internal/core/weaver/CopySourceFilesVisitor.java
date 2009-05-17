package org.phpaspect.internal.core.weaver;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class CopySourceFilesVisitor implements IResourceVisitor {
    
    private IProgressMonitor monitor;
    private String prefix;
    
    public CopySourceFilesVisitor(String projectName, IProgressMonitor monitor)
    {
        this.prefix = "/"+projectName+"/weaved/";
        this.monitor = monitor;
    }
    
    public boolean visit(IResource resource) throws CoreException {
        try {
            String path = resource.getProjectRelativePath().toString();
            resource.copy(new Path(prefix+path), IResource.KEEP_HISTORY, monitor);
        } catch (Exception e) {
            //do nothing...
        }
        return true;
    }
}