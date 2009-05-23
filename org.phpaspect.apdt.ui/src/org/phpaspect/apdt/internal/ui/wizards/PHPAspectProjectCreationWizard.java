package org.phpaspect.apdt.internal.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.ui.PHPUIMessages;
import org.eclipse.php.internal.ui.util.PHPPluginImages;
import org.eclipse.php.internal.ui.wizards.PHPProjectCreationWizard;
import org.eclipse.ui.INewWizard;
import org.phpaspect.apdt.internal.ui.actions.TogglePHPAspectNatureAction;
import org.phpaspect.apdt.ui.APDTImages;

@SuppressWarnings("restriction")
public class PHPAspectProjectCreationWizard extends PHPProjectCreationWizard
		implements INewWizard {
	
	public static final String WIZARD_ID = "org.phpaspect.apdt.ui.newproject"; //$NON-NLS-1$

	public PHPAspectProjectCreationWizard() {
		super();
		setDefaultPageImageDescriptor(APDTImages.DESC_WIZBAN_ASPECT_PROJECT);
		setWindowTitle("PHPAspect Project");
	}

	public void addPages()
	{
		super.addPages();
		
		fFirstPage.setTitle("PHPAspect Project");
		fFirstPage.setDescription("new PHPAspect Project");
		
		fSecondPage.setTitle("PHPAspect Include Path");
		fSecondPage.setDescription("Configure PHPAspect Include Path");
		
		fThirdPage.setTitle("PHPAspect Build Path");
		fThirdPage.setDescription("Configure PHPAspect Build Path");
	}
	
	public boolean performFinish()
	{
		boolean finished = super.performFinish();
		if(finished)
		{
			IProject project = fLastPage.getScriptProject().getProject();
			TogglePHPAspectNatureAction.toggleNature(project);
		}
		return finished;
	}
}
