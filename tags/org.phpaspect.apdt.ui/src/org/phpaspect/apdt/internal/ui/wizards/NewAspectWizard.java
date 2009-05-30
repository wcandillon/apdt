package org.phpaspect.apdt.internal.ui.wizards;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;
import org.phpaspect.apdt.ui.APDTImages;

public class NewAspectWizard extends NewSourceModuleWizard{

	public static final String WIZARD_ID = "org.phpaspect.apdt.ui.newaspect"; //$NON-NLS-1$

	public NewAspectWizard() {
		setDefaultPageImageDescriptor(APDTImages.DESC_WIZBAN_ASPECT_FILE);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle("Creates a new aspect");
	}
	
	@Override
	protected NewSourceModulePage createNewSourceModulePage() {
		return new NewAspectWizardPage();
	}

}
