package org.phpaspect.internal.core.weaver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.phpaspect.apdt.core.APDTCorePlugin;

public abstract class PHPAspectRuntime {
	private static final String FILES_PATH = "/"; //$NON-NLS-1$	
	
	private static final String PHPASPECT_FOLDER = "PHPAspect/"; //$NON-NLS-1$	
	private static final String ASPECT_REGISTRY = "PHPAspect/AspectRegistry.php"; //$NON-NLS-1$	
	private static final String DISPATCHER = "PHPAspect/Dispatcher.php"; //$NON-NLS-1$	
	private static final String INVALID_CODE_ADVICE_EXCEPTION = "PHPAspect/InvalidCodeAdviceException.php"; //$NON-NLS-1$	
	
	private static final String ADDENDUM_FOLDER = "PHPAspect/addendum"; //$NON-NLS-1$	
	private static final String ANNOTATIONS = "PHPAspect/addendum/annotations.php"; //$NON-NLS-1$	
	
	private static final String ANNOTATIONS_FOLDER = "PHPAspect/addendum/annotations"; //$NON-NLS-1$	
	private static final String ANNOTATIONS_PARSER = "PHPAspect/addendum/annotations/annotation_parser.php"; //$NON-NLS-1$	
	private static final String DOC_COMMENT = "PHPAspect/addendum/annotations/doc_comment.php"; //$NON-NLS-1$	
	
	private static final String MODEL_FOLDER = "PHPAspect/Model"; //$NON-NLS-1$	
	private static final String ASPECT = "PHPAspect/Model/Aspect.php"; //$NON-NLS-1$	
	private static final String IASPECT = "PHPAspect/Model/IAspect.php"; //$NON-NLS-1$	
	private static final String REFLECTION_ASPECT = "PHPAspect/Model/ReflectionAspect.php"; //$NON-NLS-1$	
	private static final String REFLECTION_CODE_ADVICE = "PHPAspect/Model/ReflectionCodeAdvice.php"; //$NON-NLS-1$	
	private static final String SINGLETON = "PHPAspect/Model/Singleton.php"; //$NON-NLS-1$	
	
	private static final String PHPASPECT_ANNOTATIONS_FOLDER = "PHPAspect/Model/Annotations"; //$NON-NLS-1$	
	private static final String AFTER = "PHPAspect/Model/Annotations/After.php"; //$NON-NLS-1$	
	private static final String AROUND = "PHPAspect/Model/Annotations/Around.php"; //$NON-NLS-1$	
	private static final String BEFORE = "PHPAspect/Model/Annotations/Before.php"; //$NON-NLS-1$	
	private static final String CODE_ADVICE = "PHPAspect/Model/Annotations/CodeAdvice.php"; //$NON-NLS-1$	
	
	private static final String JOINPOINTS_FOLDER = "PHPAspect/Model/Joinpoints"; //$NON-NLS-1$	
	private static final String ABSTRACT_JOINPOINT = "PHPAspect/Model/Joinpoints/AbstractJoinpoint.php"; //$NON-NLS-1$	
	private static final String CONSTRUCTOR_INVOCATION_JOINPOINT = "PHPAspect/Model/Joinpoints/ConstructorInvocationJoinpoint.php"; //$NON-NLS-1$	
	private static final String JOINPOINT = "PHPAspect/Model/Joinpoints/Joinpoint.php"; //$NON-NLS-1$	
	private static final String METHOD_EXECUTION_JOINPOINT = "PHPAspect/Model/Joinpoints/MethodExecutionJoinpoint.php"; //$NON-NLS-1$	
	private static final String METHOD_INVOCATION_JOINPOINT = "PHPAspect/Model/Joinpoints/MethodInvocationJoinpoint.php"; //$NON-NLS-1$	
	
	private PHPAspectRuntime(){}
	
	public static void run(IProject project)
	{
		try {
			createFolder(project, FILES_PATH, PHPASPECT_FOLDER);
			createFile(project, FILES_PATH, ASPECT_REGISTRY);
			createFile(project, FILES_PATH, DISPATCHER);
			createFile(project, FILES_PATH, INVALID_CODE_ADVICE_EXCEPTION);
			
			createFolder(project, FILES_PATH, ADDENDUM_FOLDER);
			createFile(project, FILES_PATH, ANNOTATIONS);
			
			createFolder(project, FILES_PATH, ANNOTATIONS_FOLDER);
			createFile(project, FILES_PATH, ANNOTATIONS_PARSER);
			createFile(project, FILES_PATH, DOC_COMMENT);
			
			createFolder(project, FILES_PATH, MODEL_FOLDER);
			createFile(project, FILES_PATH, ASPECT);
			createFile(project, FILES_PATH, IASPECT);
			createFile(project, FILES_PATH, REFLECTION_ASPECT);
			createFile(project, FILES_PATH, REFLECTION_CODE_ADVICE);
			createFile(project, FILES_PATH, SINGLETON);

			createFolder(project, FILES_PATH, PHPASPECT_ANNOTATIONS_FOLDER);
			createFile(project, FILES_PATH, AFTER);
			createFile(project, FILES_PATH, AROUND);
			createFile(project, FILES_PATH, BEFORE);
			createFile(project, FILES_PATH, CODE_ADVICE);

			createFolder(project, FILES_PATH, JOINPOINTS_FOLDER);
			createFile(project, FILES_PATH, ABSTRACT_JOINPOINT);
			createFile(project, FILES_PATH, CONSTRUCTOR_INVOCATION_JOINPOINT);
			createFile(project, FILES_PATH, JOINPOINT);
			createFile(project, FILES_PATH, METHOD_EXECUTION_JOINPOINT);
			createFile(project, FILES_PATH, METHOD_INVOCATION_JOINPOINT);
		} catch (Exception e) {
			//TODO: replace with logger
			e.printStackTrace();
		}
	}
	
	private static IFile createFile(final IProject project, String filePath, String fileName) throws IOException, CoreException {
		URL demoFileURL = FileLocator.find(Platform.getBundle(APDTCorePlugin.PLUGIN_ID), new Path(filePath + fileName), null); 
		demoFileURL = FileLocator.resolve(demoFileURL);
		IPath p = project.getFile("weaved").getFullPath();
		p = p.append(fileName);
		final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
		InputStream inputStream = (InputStream) demoFileURL.getContent();
		file.create(inputStream, true, null);
		return file;
	}

	private static IFolder createFolder(final IProject project, String filePath, String folderName) throws IOException, CoreException {		
		URL demoFileURL = FileLocator.find(Platform.getBundle(APDTCorePlugin.PLUGIN_ID), new Path(filePath + folderName), null);
		demoFileURL = FileLocator.resolve(demoFileURL);
		IPath p = project.getFile("weaved").getFullPath();
		p = p.append(folderName);
		final IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(p);
		folder.create(true, true, null);
		return folder;
	}
}
