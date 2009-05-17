package org.phpaspect.internal.core.weaver;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.php.internal.core.compiler.ast.parser.PHPSourceParserFactory;
import org.phpaspect.apdt.internal.core.APDTNature;
import org.phpaspect.apdt.internal.core.visitor.AspectVisitor;
import org.phpaspect.core.weaver.Pointcut;

public class AspectFilesVisitor implements IResourceVisitor {

    private ISourceParser parser;
    private List<Pointcut> pointcuts = new LinkedList<Pointcut>();
    
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
                pointcuts.addAll(visitor.getPointcuts());
            }
        }
        return true;
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

	public List<Pointcut> getPointcuts() {
		return pointcuts;
	}
}
