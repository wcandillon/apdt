package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.core.project.options.includepath.IncludePathVariableManager;

public class PHPAspectNature implements IProjectNature {

	/**
	 * ID of this project nature
	 */
	public static final String NATURE_ID = "org.phpaspect.apdt.core.phpAspectNature";

	private IProject project;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	public void configure() throws CoreException {
		//We create the weaved directory
		final IFolder folder = project.getFolder("weaved");
		if(folder.exists()){
			folder.delete(true, null);
		}
		folder.create(true, true, null);
		
		//TODO:We add the correct include paths (check if the pear one is not already added
		//IPath path = Path.fromOSString("/usr/lib/php5");
		//IncludePathVariableManager.instance().putVariable("PHPASPECT_RUNTIME", path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	public void deconfigure() throws CoreException {
		//Remove the weaved files
		final IFolder folder = project.getFolder("weaved");
		folder.delete(true, null);
		
		//TODO: Remove the PHPAspect include paths...
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#getProject()
	 */
	public IProject getProject() {
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject(IProject project) {
		this.project = project;
	}
}