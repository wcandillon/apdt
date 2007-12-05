package org.phpaspect.apdt.internal.core.builder;

import java.net.URI;
import java.util.*;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPMarker;
import org.eclipse.php.internal.core.project.options.PHPProjectOptions;
import org.eclipse.php.core.project.build.IPHPBuilderExtension;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;
import org.phpaspect.apdt.internal.core.builder.PHPAspectNature;
import org.phpaspect.weaver.impl.PHPAspectWeaver;
import org.phpaspect.weaver.parser.PHPAspectLexer;
import org.phpaspect.weaver.parser.PHPAspectParser;

public class PHPAspectBuilderExtension implements IPHPBuilderExtension {

	public PHPAspectBuilderExtension() {
	}
	
	public boolean isEnabled() {
		return true;
	}

	public void startupOnInitialize(IncrementalProjectBuilder builder) {
		//Initialization logic...
	}

	public void clean(IncrementalProjectBuilder builder, IProgressMonitor monitor) throws CoreException {
		cleanBuild(builder.getProject());
	}

	public IProject[] build(IncrementalProjectBuilder builder, int kind, Map args, IProgressMonitor monitor) throws CoreException {

		if (kind == IncrementalProjectBuilder.FULL_BUILD) {
			fullBuild(builder, monitor);
			return null;
		}
		
		IResourceDelta delta = builder.getDelta(builder.getProject());
		if (delta == null) {
			return null;
		}

		buildDelta(delta, monitor);
		
		return null;
	}

	private void fullBuild(IncrementalProjectBuilder builder, IProgressMonitor monitor) {
		try {
			IProject project = builder.getProject();
			project.accept(new PHPAspectResourceVisitor(monitor));
		} catch (CoreException e) {
			APDTCorePlugin.log(e);
			return;
		}  
	}

	private void buildDelta(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new PHPAspectDeltaVisitor());
	}

	private void cleanBuild(IProject project) {
		try {
			if (project.hasNature(PHPAspectNature.NATURE_ID)) {
				project.getFolder("weaved").delete(true, null);
				project.getFolder("weaved").create(true, true, null);
				getWeaver(project).clear();
			}
		} catch (CoreException e) {
			APDTCorePlugin.log(e);
			return;
		}
	}
	
	public static final String BUILDER_ID = "org.phpaspect.apdt.core.PHPAspectBuilderExtension";
	private static String PHPASPECT_PROBLEM_MARKER_TYPE = APDTCorePlugin.PLUGIN_ID + ".phpaspectproblemmarker";
	// used to examine if a file is php associated
	private static final IContentTypeManager CONTENT_TYPE_MANAGER = Platform.getContentTypeManager();
	//private static final String MARKER_TYPE = "org.phpaspect.apdt.core.aspectProblem";
	private static Map<String, PHPAspectWeaver> weavers = new HashMap<String, PHPAspectWeaver>();
	
	private void addMarker(IFile file, String message, int lineNumber,
			int severity) {
		try {
			IMarker marker = file.createMarker(PHPASPECT_PROBLEM_MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (lineNumber == -1) {
				lineNumber = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		} catch (CoreException e) {
			
		}
	}

	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(PHPASPECT_PROBLEM_MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}
	
	private boolean isPHPAspectFile(IFile file) {
		final int numSegments = file.getFullPath().segmentCount();
		final String filename = file.getFullPath().segment(numSegments - 1);
		final IContentType contentType = CONTENT_TYPE_MANAGER.getContentType(APDTCorePlugin.PLUGIN_ID + ".phpaspectsource");
		if (contentType.isAssociatedWith(filename)) {
			return true;
		}
		return false;
	}
	
	private boolean isPHPFile(IFile file){
		final int numSegments = file.getFullPath().segmentCount();
		final String filename = file.getFullPath().segment(numSegments - 1);
		final IContentType contentType = CONTENT_TYPE_MANAGER.getContentType(PHPCorePlugin.ID + ".phpsource");
		if (contentType.isAssociatedWith(filename)) {
			return true;
		}
		return false;		
	}
	
	private void weave(IFile file){
		PHPAspectWeaver weaver = getWeaver(file);
		if(isPHPAspectFile(file)){
			validate(file);
			weaver.addAspect(file.getLocationURI());
			try {
				weaver.generateAspectEntities();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(isPHPFile(file)){
			IPath targetFile = copyToWeavedDirectory(file);
			weaver.weave(URIUtil.toURI(targetFile));
		}
	}
	
	public PHPAspectWeaver getWeaver(IResource resource) {
		IProject project = resource.getProject();
		String projectName = project.getName();
		if(weavers.get(projectName) == null){
			URI aspectPath = project.getFolder("weaved")
									.getFolder("_aspects").getLocationURI();
			weavers.put(projectName,
							new PHPAspectWeaver(aspectPath));
		}
		return weavers.get(projectName);
	}

	private void validate(IFile file) {
		PHPAspectParser parser = null;
		deleteMarkers(file);
		try {
			PHPAspectLexer scanner = new PHPAspectLexer(file.getContents());
			parser = new PHPAspectParser(scanner);
			parser.parse();
		} catch (CoreException e) {
				// TODO do something ?
				e.printStackTrace();
		} catch (Exception e){
			List<PHPMarker> markers = parser.getPhpErrorMarkers(); 
			for(PHPMarker marker: markers){
				addMarker(file, marker.getDescription(), marker.getUserData().getStopLine(), IMarker.SEVERITY_ERROR);
			}
		}
	}
	
	private IPath copyToWeavedDirectory(IResource resource){
		if(resource instanceof IFile && isPHPAspectFile((IFile)resource)){
			return null;
		}
		IPath binPath = resource.getProject().getFolder("weaved").getFullPath();
		IPath destination = binPath.append(resource.getProjectRelativePath());
		try {
			resource.copy(destination, true, null);
		} catch (CoreException e) {
			e.getMessage();
		}
		return destination;
	}
	
	private void removeFromWeavedDirectory(IResource resource){
		IProject project = resource.getProject();
		IResource destination;
		
		if(resource instanceof IFolder){
			destination = project.getFolder("weaved").getFolder(resource.getProjectRelativePath());
		}else{
			destination = project.getFolder("weaved").getFile(resource.getProjectRelativePath());
		}
		
		try {
			destination.delete(true, null);
		} catch (CoreException e) {
			e.getMessage();
		}			
	}
	
	class PHPAspectDeltaVisitor implements IResourceDeltaVisitor {
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) {
			switch (delta.getResource().getType()) {
				//only process files with PHP content type
				case IResource.FILE:
					processFileDelta(delta);
					return false;
				case IResource.FOLDER:
					processFolderDelta(delta);
					return true;
					//only process projects with PHP nature
				case IResource.PROJECT:
					return processProjectDelta(delta);

				default:
					return true;
			}
		}
		
		private void processFolderDelta(IResourceDelta folderDelta) {
			IFolder folder = (IFolder) folderDelta.getResource();
			
			switch(folderDelta.getKind()){
				case IResourceDelta.ADDED:
					copyToWeavedDirectory(folder);
					break;
				case IResourceDelta.REMOVED:
					removeFromWeavedDirectory(folder);
					break;
			}
		}

		private void processFileDelta(IResourceDelta fileDelta) {
			//System.out.println("processFileDelta");
			IFile file = (IFile) fileDelta.getResource();

			switch (fileDelta.getKind()) {
				case IResourceDelta.ADDED:
					copyToWeavedDirectory(file);
					break;
				case IResourceDelta.CHANGED:
					if (isPHPAspectFile(file)){
						weave(file);
						//validate(file);
					}else{
						
					}
					break;
				case IResourceDelta.REMOVED:
					removeFromWeavedDirectory(file);
					break;
			}
		}
		
		private boolean processProjectDelta(IResourceDelta projectDelta) {
			IProject project = (IProject) projectDelta.getResource();
			try {
				if (!project.hasNature(PHPAspectNature.NATURE_ID)) {
					return false;
				}
			} catch (CoreException e) {
				APDTCorePlugin.log(e);
				return false;
			}
			return true;
		}
	}	
	
	public class PHPAspectResourceVisitor implements IResourceVisitor {

		private IProgressMonitor monitor;

		public PHPAspectResourceVisitor(IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		public boolean visit(IResource resource) {
			if(monitor.isCanceled()){
				return false;
			}
			// parse each PHP file with the parserFacade which adds it to
			// the model
			if (resource.getType() == IResource.FILE) {
				handle((IFile) resource);
				return false;
			}

			if (resource.getType() == IResource.PROJECT) {
				return handle((IProject) resource);
			}

			return true;
		}

		private boolean handle(IProject project) {
			/* //check if the project contains PHP
			if (PHPWorkspaceModelManager.getInstance().getModelForProject(project, true) == null) {
				return false;
			}
			*/
			PHPProjectOptions projectOptions = PHPProjectOptions.forProject(project);
			projectOptions.validateIncludePath();
			return true;
		}

		private void handle(IFile file) {
			if (monitor.isCanceled()) {
				return;
			}
			
			if (!isPHPAspectFile(file)) {
				return;
			}
			
			monitor.subTask(NLS.bind("Parsing: {0} ...", file.getFullPath().toPortableString()));
			weave(file);
			//validate(file); 

		}
	}
}