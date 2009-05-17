package org.phpaspect.apdt.internal.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.php.internal.ui.wizards.PHPIncludePathPage;
import org.eclipse.php.internal.ui.wizards.PHPProjectCreationWizard;
import org.eclipse.ui.IWorkbench;
import org.phpaspect.apdt.internal.ui.actions.TogglePHPAspectNatureAction;
import org.phpaspect.apdt.internal.ui.icons.APDTPluginImages;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "mpe". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class PHPAspectProjectCreationWizard extends PHPProjectCreationWizard {

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("PHPAspect Project");
		setDefaultPageImageDescriptor(APDTPluginImages.DESC_WIZBAN_NEW_PROJECT);
	}
	
	protected boolean addDeafaultPages() {
		addPage(basePage = new PHPAspectProjectWizardBasePage(getDataModel(), "page1")); //$NON-NLS-1$
		addPage(includePathPage = new PHPIncludePathPage(getDataModel(), "page2")); //$NON-NLS-1$
		return true;
	}
	
	protected void postPerformFinish() throws InvocationTargetException{
		super.postPerformFinish();
		TogglePHPAspectNatureAction.toggleNature(createdProject);
	}
}