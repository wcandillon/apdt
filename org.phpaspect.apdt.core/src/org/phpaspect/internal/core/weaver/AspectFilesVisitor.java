package org.phpaspect.internal.core.weaver;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.compiler.env.ModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.internal.core.builder.BuildProblemReporter;
import org.eclipse.dltk.internal.core.util.ResourceSourceModule;
import org.eclipse.php.internal.core.compiler.ast.parser.ASTUtils;
import org.eclipse.php.internal.core.compiler.ast.parser.PHPSourceParserFactory;
import org.phpaspect.apdt.core.weaver.Pointcut;
import org.phpaspect.apdt.internal.core.APDTNature;
import org.phpaspect.apdt.internal.core.visitor.AspectVisitor;

public class AspectFilesVisitor implements IResourceVisitor {

    private ISourceParser parser;
    private List<Pointcut> pointcuts = new LinkedList<Pointcut>();
	private IProgressMonitor monitor;
    
    public AspectFilesVisitor(IProgressMonitor monitor) {
		this.monitor = new SubProgressMonitor(monitor, 2);
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
            	monitor.beginTask("Loading aspect "+file.getName(), 1);
                char[] content = getContent(file);
				ISourceModule moduleSource = new ResourceSourceModule(file, file.getLocationURI());
				ModuleDeclaration module = (ModuleDeclaration) SourceParserUtil.parse(moduleSource, new BuildProblemReporter(file));
                AspectVisitor visitor = new AspectVisitor(content);
                try {
                    module.traverse(visitor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pointcuts.addAll(visitor.getPointcuts());
                monitor.done();
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
