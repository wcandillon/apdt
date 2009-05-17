package org.phpaspect.internal.core.weaver;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.php.internal.core.compiler.ast.parser.PHPSourceParserFactory;
import org.eclipse.php.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHP;
import org.phpaspect.apdt.internal.core.APDTNature;
import org.phpaspect.apdt.internal.core.visitor.AspectVisitor;

public class Weaver {
    
    private static IPath weavedFolder;
    private static Weaver instance = null;
    
    private Weaver(){}
    
    public static Weaver getInstance()
    {
        if(instance == null)
        {
            instance = new Weaver();
        }
        return instance;
    }
    
    private boolean isAspectFile(IFile file)
    {
        try {
            IContentDescription description = file.getContentDescription();
            if(description != null)
            {
                return description.getContentType().getId().equals(APDTNature.CONTENT_TYPE);    
            }
        } catch (CoreException e) {
            //do nothing...
        }
        return false;
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

    private class CopySourceFilesVisitor implements IResourceVisitor {
        
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
    
    private class AspectFilesVisitor implements IResourceVisitor {

        private ISourceParser parser;
        
        public AspectFilesVisitor()
        {
            parser = new PHPSourceParserFactory().createSourceParser();
        }
        
        private char[] getContent(IFile file)
        {
            String content = "";
            try {
                InputStream in = file.getContents();
                while(in.available() > 0)
                {
                    content += (char)in.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toCharArray();
        }
        
        public boolean visit(IResource resource) throws CoreException {
            if(resource instanceof IFile)
            {
                IFile file = (IFile)resource;
                if(isAspectFile(file))
                {
                    char[] content = getContent(file);
                    ModuleDeclaration module = parser.parse(file.getName().toCharArray(), content, null);
                    AspectVisitor visitor = new AspectVisitor(content);
                    try {
                        module.traverse(visitor);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
    }
    
    private class PHPFilesVisitor implements IResourceVisitor {

        public boolean visit(IResource resource) throws CoreException {
            if(resource instanceof IFile)
            {
                IFile file = (IFile)resource;
                if(isPHPFile(file))
                {
                    
                }
            }
            return true;
        }    
    }

	public synchronized Weaver weave(IProject project, IProgressMonitor monitor) throws CoreException
	{
	    weavedFolder = new Path("weaved");
	    //Create the weaved directory
	    IFolder root = project.getFolder(weavedFolder);
	    if(root.exists())
	    {
            root.delete(IResource.KEEP_HISTORY, monitor);
	    }
	    root.create(IResource.KEEP_HISTORY, true, monitor);
        //Load project's aspects
        project.accept(new AspectFilesVisitor());
        //Copy php project
        project.accept(new CopySourceFilesVisitor(project.getName(), monitor));
        //Weave php files
	    root.accept(new PHPFilesVisitor());
		return this;
	}
}
