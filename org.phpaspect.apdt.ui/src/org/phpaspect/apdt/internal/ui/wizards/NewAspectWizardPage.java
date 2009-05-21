package org.phpaspect.apdt.internal.ui.wizards;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.phpaspect.apdt.internal.core.APDTNature;
import org.phpaspect.apdt.ui.APDTImages;

public class NewAspectWizardPage extends NewSourceModulePage {
	
	public NewAspectWizardPage()
	{
		setImageDescriptor(APDTImages.DESC_WIZBAN_ASPECT_FILE);
	}
	
	@Override
	protected String getRequiredNature() {
		return APDTNature.NATURE_ID;
	}

	@Override
	protected String getPageTitle() {
		return "New Aspect";
	}

	@Override
	protected String getPageDescription() {
		return "This wizard creates a new Aspect for PHPAspect project";
	}
}
