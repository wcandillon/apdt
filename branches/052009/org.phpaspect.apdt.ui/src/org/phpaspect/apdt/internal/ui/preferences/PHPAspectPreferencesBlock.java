package org.phpaspect.apdt.internal.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.php.internal.ui.preferences.AbstractPHPPreferencePageBlock;
import org.eclipse.swt.widgets.Composite;

public class PHPAspectPreferencesBlock extends AbstractPHPPreferencePageBlock {

	private static final String PHPASPECT_PAGE_ID = "org.phpaspect.apdt.ui.preferences.PHPAspectPreferences";
	private PreferencePage propertyPage;
	
	public void initializeValues(PreferencePage propertyPage) {
		this.propertyPage = propertyPage;
		//this.propertyPage.
	}

	public void performApply(boolean isProjectSpecific) {
		// TODO Auto-generated method stub

	}

	public boolean performCancel() {
		// TODO Auto-generated method stub
		return false;
	}

	public void performDefaults() {
		// TODO Auto-generated method stub

	}

	public boolean performOK(boolean isProjectSpecific) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setCompositeAddon(Composite parent) {
		// TODO Auto-generated method stub

	}

}
