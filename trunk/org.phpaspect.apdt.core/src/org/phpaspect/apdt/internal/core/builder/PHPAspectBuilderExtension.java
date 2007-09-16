package org.phpaspect.apdt.internal.core.builder;

import java.util.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.php.internal.core.phpModel.parser.PHPCodeDataFactory;
import org.eclipse.php.internal.core.phpModel.phpElementData.IPHPMarker;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPMarker;
import org.eclipse.php.internal.core.phpModel.phpElementData.UserData;
import org.eclipse.php.internal.core.project.options.PHPProjectOptions;
import org.eclipse.php.core.project.build.IPHPBuilderExtension;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;
import org.phpaspect.apdt.internal.core.builder.PHPAspectNature;
import org.phpaspect.apdt.internal.core.parser.PHPAspectLexer;
import org.phpaspect.apdt.internal.core.parser.PHPAspectParser;
import org.phpaspect.apdt.internal.core.parser.PHPAspectSymbols;
import org.phpaspect.apdt.internal.core.parser.PHPAspectSymbolsUtils;


public class PHPAspectBuilderExtension implements IPHPBuilderExtension {
	
	private static List<PHPMarker> markers = new ArrayList<PHPMarker>();
	private static IFile workingFile = null;

	public PHPAspectBuilderExtension() {
	}
	
	public boolean isEnabled() {
		return true;
	}

	public void startupOnInitialize(IncrementalProjectBuilder builder) {
		
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
			if (!project.hasNature(PHPAspectNature.NATURE_ID)) {
				return;
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
			markers = new ArrayList<PHPMarker>();
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
 	
	

	
	private void validate(IFile file) {
		PHPAspectParser parser = null;
		deleteMarkers(file);
		try {
			workingFile = file;
			PHPAspectLexer scanner = new PHPAspectLexer(file.getContents());
			parser = new PHPAspectParser(scanner);
			parser.parse();
				//PHPAspectParserFactory.create(file).parse();
		} catch (CoreException e) {
				// TODO do something ?
				e.printStackTrace();
		} catch (Exception e){
			for(PHPMarker marker: markers){
				addMarker(file, marker.getDescription(), marker.getUserData().getStopLine(), IMarker.SEVERITY_ERROR);
			}
			//addMarker(file, parser.getErrorMessage(), parser.getCurrentLine(), IMarker.SEVERITY_ERROR);
				
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
					if (isPHPAspectFile(file)) 
						validate(file);
					break;
				case IResourceDelta.REMOVED:
					// removed automatically from the validator, no need to enforce
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

			validate(file); 

		}
	}
	
	public static void handleSyntaxError(int currToken, String currText, short[] rowOfProbe, int startPosition, int endPosition, int lineNumber) {
		String unexpectedString = "";
		boolean addUnexpected;

		if (currToken == PHPAspectSymbols.EOF) {
			addUnexpected = true;
			unexpectedString = "End of File";
			startPosition = --endPosition;
		} else if (currToken == PHPAspectSymbols.T_CONSTANT_ENCAPSED_STRING) {
			addUnexpected = true;
			endPosition = startPosition + currText.trim().length();
			unexpectedString = "String";
		} else {
			addUnexpected = currText != null && currText.trim().length() > 0;
			if (addUnexpected) {
				unexpectedString = currText.trim();
				endPosition = startPosition + unexpectedString.length();
				unexpectedString = '\'' + unexpectedString + '\'';
			}
		}

		//IntList list = new IntList();
		List list = new ArrayList();
		for (int probe = 0; probe < rowOfProbe.length; probe += 2) {
			int curr = rowOfProbe[probe];
			String value = getConstantValue(curr);
			if (value != null && !value.equals("")) {
				list.add(value);
			}
		}
		int listSize = list.size();
		if (listSize > 3) {
			listSize = 0;
		}

		String description = "";

		if (!addUnexpected) {
			switch (listSize) {
				case 0:
					description = "Syntax Error";
					break;
				case 1:
					description = "Syntax Error: expecting: " + list.get(0);
					break;
				case 2:
					description = "Syntax Error: expecting: " + list.get(0) + " or " + list.get(1);
					break;
				case 3:
					description = "Syntax Error: expecting: " + list.get(0) + " or " + list.get(1) + " or " + list.get(2);
					break;
			}
		} else {
			switch (listSize) {
				case 0:
					description = "Syntax Error: unexpected " + unexpectedString;
					break;
				case 1:
					description = "Syntax Error: unexpected " + unexpectedString + ", expecting: " + list.get(0);
					break;
				case 2:
					description = "Syntax Error: unexpected " + unexpectedString + ", expecting: " + list.get(0) + " or " + list.get(1);
					break;
				case 3:
					description = "Syntax Error: unexpected " + unexpectedString + ", expecting: " + list.get(0) + " or " + list.get(1) + " or " + list.get(2);
					break;
			}
		}
		UserData userData = PHPCodeDataFactory.createUserData(workingFile.getName(), startPosition, endPosition, startPosition, lineNumber);
		markers.add(new PHPMarker(IPHPMarker.ERROR, description, userData));
	}
	
	private static String getConstantValue(int tag) {
		String rv = PHPAspectSymbolsUtils.getTokenName(tag);
		if (rv != null) {
			return '\'' + rv + '\'';
		}
		if (tag == PHPAspectSymbols.T_STRING) {
			return "Identifier";
		}
		if (tag == PHPAspectSymbols.T_VARIABLE) {
			return "Variable";
		}

		return null;
	}
}