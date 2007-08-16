package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.php.internal.core.project.options.PHPProjectOptions;

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
		
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();
		boolean hadPHPBuilder = false;
		
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(PHPAspectBuilder.BUILDER_ID)) {
				return;
			}
			//We're replacing the PHP Builder by the PHPAspect one
			if(commands[i].getBuilderName().equals(PHPProjectOptions.BUILDER_ID)){
				hadPHPBuilder = true;
				commands[i] = desc.newCommand();
				commands[i].setBuilderName(PHPAspectBuilder.BUILDER_ID);
			}
		}
		desc.setBuildSpec(commands);
		
		//If not PHP builder has been detected we're adding the PHPAspect one anyways
		if(!hadPHPBuilder){
			project.setDescription(desc, null);
			ICommand[] newCommands = new ICommand[commands.length + 1];
			System.arraycopy(commands, 0, newCommands, 0, commands.length);
			ICommand command = desc.newCommand();
			command.setBuilderName(PHPAspectBuilder.BUILDER_ID);
			newCommands[newCommands.length - 1] = command;
			desc.setBuildSpec(newCommands);	
		}
		
		project.setDescription(desc, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	public void deconfigure() throws CoreException {
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();

		for (int i = 0; i < commands.length; ++i) {
			//We're replacing the PHPAspect Builder by the PHP one
			//TODO: What'if the PHPAspect builder was added and isn't replacing any PHP Builder ?
			if (commands[i].getBuilderName().equals(PHPAspectBuilder.BUILDER_ID)) {
				commands[i] = description.newCommand();
				commands[i].setBuilderName(PHPProjectOptions.BUILDER_ID);
			}
		}
		description.setBuildSpec(commands);
		project.setDescription(description, null);
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