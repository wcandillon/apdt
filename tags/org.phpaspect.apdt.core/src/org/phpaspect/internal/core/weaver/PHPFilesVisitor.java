package org.phpaspect.internal.core.weaver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.php.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHP;
import org.phpaspect.apdt.core.weaver.Pointcut;
import org.phpaspect.apdt.internal.core.visitor.WeaverVisitor;

public class PHPFilesVisitor implements IResourceVisitor {
	
	private IProgressMonitor monitor;
	private List<Pointcut> pointcuts;
    
    public PHPFilesVisitor(IProgressMonitor monitor, List<Pointcut> pointcuts) {
		this.monitor = new SubProgressMonitor(monitor, 2);
		this.pointcuts = pointcuts;
	}
    
    public boolean visit(IResource resource) throws CoreException {
        if(resource instanceof IFile)
        {
            IFile file = (IFile)resource;
            if(isPHPFile(file))
            {
				try {
					monitor.beginTask("Weave "+file.getName(), 1);
					setIncludePath(file);
					WeaverVisitor weaver = new WeaverVisitor(file, pointcuts);
	            	String content = weaver.weave();
	            	InputStream in = 
	            		new ByteArrayInputStream(content.getBytes());
	            	file.setContents(in, IResource.KEEP_HISTORY, monitor);
	            	monitor.done();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
        return true;
    }

	private void setIncludePath(IFile file) {
		String content = "<?php require_once 'PHPAspect/Dispatcher.php'; ?>\n";
		try {
			InputStream in = file.getContents();
			while(in.available() > 0)
			{
				content += (char)in.read();
			}
			in = new ByteArrayInputStream(content.getBytes());
			file.setContents(in, IResource.KEEP_HISTORY, monitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isPHPFile(IFile file)
    {
        try {
            IContentDescription description = file.getContentDescription();
            if(description != null)
            {
                return description.getContentType().getId().equals(ContentTypeIdForPHP.ContentTypeID_PHP);    
            }
        } catch (CoreException e) {
            //do nothing...
        }
        return false;
    }
}