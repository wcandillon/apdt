package org.phpaspect.apdt.internal.core.builder;

import java.util.Map;

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
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.core.documentModel.validate.PHPProblemsValidator;
import org.eclipse.php.internal.core.phpModel.javacup.runtime.Symbol;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;
import org.phpaspect.apdt.internal.core.parser.PHPAspectLexer;
import org.phpaspect.apdt.internal.core.parser.PHPAspectParser;
import org.phpaspect.apdt.internal.core.parser.PHPAspectParserFactory;

public class PHPAspectBuilder extends IncrementalProjectBuilder {

	class PHPAspectDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				checkAspect(resource);
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				checkAspect(resource);
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class PHPAspectResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			checkAspect(resource);
			//return true to continue visiting children.
			return true;
		}
	}

//	class PHPAspectErrorHandler extends DefaultHandler {
//		
//		private IFile file;
//
//		public PHPAspectErrorHandler(IFile file) {
//			this.file = file;
//		}
//
//		private void addMarker(SAXParseException e, int severity) {
//			PHPAspectBuilder.this.addMarker(file, e.getMessage(), e
//					.getLineNumber(), severity);
//		}
//
//		public void error(SAXParseException exception) throws SAXException {
//			addMarker(exception, IMarker.SEVERITY_ERROR);
//		}
//
//		public void fatalError(SAXParseException exception) throws SAXException {
//			addMarker(exception, IMarker.SEVERITY_ERROR);
//		}
//
//		public void warning(SAXParseException exception) throws SAXException {
//			addMarker(exception, IMarker.SEVERITY_WARNING);
//		}
//	}

	public static final String BUILDER_ID = "org.phpaspect.apdt.core.PHPAspectBuilder";
	private static String PHPASPECT_PROBLEM_MARKER_TYPE = APDTCorePlugin.PLUGIN_ID + ".phpaspectproblemmarker";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	void checkAspect(IResource resource){
		if (resource instanceof IFile && resource.getName().endsWith(".ap")) {
			PHPAspectParser parser = null;
			IFile file = (IFile) resource;
			deleteMarkers(file);
			try {
				PHPAspectLexer scanner = new PHPAspectLexer(file.getContents());
				parser = new PHPAspectParser(scanner);
				parser.parse();
				//PHPAspectParserFactory.create(file).parse();
			} catch (CoreException e) {
				// TODO do something ?
				e.printStackTrace();
			} catch (Exception e){
				addMarker(file, parser.getErrorMessage(), parser.getCurrentLine(), IMarker.SEVERITY_ERROR);
				
			}
		}
	}

	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(PHPASPECT_PROBLEM_MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new PHPAspectResourceVisitor());
		} catch (CoreException e) {
			
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new PHPAspectDeltaVisitor());
	}
}
