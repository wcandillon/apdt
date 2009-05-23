package org.phpaspect.apdt.internal.ui.wizards;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.php.internal.core.project.PHPNature;

public class NewAspectWizardPage extends NewSourceModulePage {
	
	@Override
	protected String getRequiredNature() {
		return PHPNature.ID;
	}

	@Override
	protected String getPageTitle() {
		return "New Aspect";
	}

	@Override
	protected String getPageDescription() {
		return "This wizard creates a new Aspect for a PHPAspect project";
	}

	@Override
	protected String getFileContent() {
		String text = getFileText();
		String className = Character.toUpperCase(text.charAt(0))
				+ text.substring(1);
		return "<?php\nrequire_once 'PHPAspect/Model/Aspect.php';\n\nclass " + className + " extends Aspect\n{\n}\n?>"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	protected String getFileName() {
		final String fileText = getFileText();
		return fileText + ".ap"; //$NON-NLS-1$
	}
}
