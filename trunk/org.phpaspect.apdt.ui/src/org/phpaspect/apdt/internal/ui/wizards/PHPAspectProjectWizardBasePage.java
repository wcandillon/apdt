package org.phpaspect.apdt.internal.ui.wizards;

import org.eclipse.php.internal.core.preferences.CorePreferenceConstants.Keys;
import org.eclipse.php.internal.ui.preferences.PHPVersionConfigurationBlock;
import org.eclipse.php.internal.ui.wizards.PHPProjectWizardBasePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class PHPAspectProjectWizardBasePage extends PHPProjectWizardBasePage {

	public PHPAspectProjectWizardBasePage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
		setTitle("New PHPAspect Project");
		setDescription("Create a PHPAspect Project");
		dataModel.setStringProperty(Keys.PHP_VERSION, "php5");
	}
	
	protected void createProjectOptionsGroup(Composite parent) {
		//Nothing to do here...
	}
	
	public void setProjectOptionInModel(IDataModel model) {
		//Nothing to do here...		
	}

	public PHPVersionConfigurationBlock getPHPVersionBlock() {
		return null;
	}
}
