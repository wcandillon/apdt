package org.phpaspect.apdt.internal.core.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.osgi.util.NLS;
import org.eclipse.php.core.project.build.IPHPBuilderExtension;
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.core.project.PHPNature;
import org.eclipse.php.internal.core.project.options.PHPProjectOptions;
import org.eclipse.php.internal.debug.core.preferences.PHPexeItem;
import org.eclipse.php.internal.debug.core.preferences.PHPexes;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;

public class PHPBeautifierExtension implements IPHPBuilderExtension {

	private static final IContentTypeManager CONTENT_TYPE_MANAGER = Platform.getContentTypeManager();

	final public static URL PHP_BEAUTIFIER = 
								APDTCorePlugin.getDefault().getBundle()
								.getEntry("Resources/PHP_Beautifier-0.1.13/scripts/php_beautifier");
	
	public IProject[] build(IncrementalProjectBuilder builder, int kind,
			Map args, IProgressMonitor monitor) throws CoreException {

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

	public void clean(IncrementalProjectBuilder builder,
			IProgressMonitor monitor) throws CoreException {
		//Nothing to do here

	}

	public boolean isEnabled() {
		return !(PHPexes.getInstance().getAllItems().length == 0);
	}

	public void startupOnInitialize(IncrementalProjectBuilder builder) {
		//Nothing to do here...
	}

	private void fullBuild(IncrementalProjectBuilder builder, IProgressMonitor monitor) {
		try {
			IProject project = builder.getProject();
			project.accept(new PHPBeautifierResourceVisitor(monitor));
		} catch (CoreException e) {
			APDTCorePlugin.log(e);
			return;
		}  
	}

	private void buildDelta(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new PHPBeautifierDeltaVisitor());
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
	
	private void beautify(IFile file) {
		try {
			// gets the first php executable 
			String phpExe = getPhpExecutable();
			
			String compilerFilename = getFilename();
			
			// construct args for execution
			String[] args = {phpExe, compilerFilename, "-f", file.getLocation().toOSString(),
							file.getLocation().toOSString()};

			// run the php code and wait for results
			Process p = Runtime.getRuntime().exec(args);
			p.waitFor();

			// return the results from the process  
			BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
			//return output.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getPhpExecutable(){
		PHPexeItem[] phpExes = PHPexes.getInstance().getAllItems();
		String phpExe = phpExes[2].getExecutable().toString();
		return phpExe;
	}
	
	private static String getFilename() throws IOException, Exception {
		final URL resolve = FileLocator.resolve(PHP_BEAUTIFIER);

		IPath path = new Path(resolve.getFile());
		final String compilerFilename = path.toOSString();
		return compilerFilename;
	}
	
	class PHPBeautifierDeltaVisitor implements IResourceDeltaVisitor {
		
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

					//only process projects with PHP nature
				case IResource.PROJECT:
					return processProjectDelta(delta);

				default:
					return true;
			}
		}
		
		private void processFileDelta(IResourceDelta fileDelta) {
			//System.out.println("processFileDelta");
			IFile file = (IFile) fileDelta.getResource();

			switch (fileDelta.getKind()) {
				case IResourceDelta.ADDED:
				case IResourceDelta.CHANGED:
					if (isPHPFile(file)) 
						beautify(file);
					break;
				case IResourceDelta.REMOVED:
					// removed automatically from the validator, no need to enforce
					break;
			}
		}

		private boolean processProjectDelta(IResourceDelta projectDelta) {
			IProject project = (IProject) projectDelta.getResource();
			try {
				if (!project.hasNature(PHPNature.ID)) {
					return false;
				}
			} catch (CoreException e) {
				APDTCorePlugin.log(e);
				return false;
			}
			return true;
		}
	}	
	
	public class PHPBeautifierResourceVisitor implements IResourceVisitor {

		private IProgressMonitor monitor;

		public PHPBeautifierResourceVisitor(IProgressMonitor monitor) {
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
			
			if (!isPHPFile(file)) {
				return;
			}
			monitor.subTask(NLS.bind("Parsing: {0} ...", file.getFullPath().toPortableString()));

			beautify(file); 

		}
	}
}
