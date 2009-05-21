package org.phpaspect.apdt.internal.ui.wizards;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class NewAspectWizard extends NewSourceModuleWizard{

	@Override
	protected NewSourceModulePage createNewSourceModulePage() {
		return new NewAspectWizardPage();
	}

}
