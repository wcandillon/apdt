package org.phpaspect.apdt.internal.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.ui.preferences.PropertyAndPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.phpaspect.apdt.internal.ui.APDTUiPlugin;
import org.phpaspect.apdt.internal.ui.text.UIMessages;

public class PHPAspectInterpreterPreferencePage extends
		PropertyAndPreferencePage {

	public static final String PREF_ID = "org.phpaspect.apdt.ui.preferences.PHPAspectInterpreterPreferencePage"; //$NON-NLS-1$
	public static final String PROP_ID = "org.phpaspect.apdt.ui.propertyPages.PHPAspectInterpreterPreferencePage"; //$NON-NLS-1$

	private PHPAspectConfigurationBlock configurationBlock;
	
	public void createControl(Composite parent) {
		IWorkbenchPreferenceContainer container = (IWorkbenchPreferenceContainer) getContainer();
		configurationBlock = new PHPAspectConfigurationBlock(getNewStatusChangedListener(), getProject(), container);
		super.createControl(parent);
	}
	
	public PHPAspectInterpreterPreferencePage() {
		setPreferenceStore(APDTUiPlugin.getDefault().getPreferenceStore());
		// only used when page is shown programatically
		setTitle(UIMessages.PHPAspectInterpreterPreferencePage_title);
	}
	
	@Override
	protected Control createPreferenceContent(Composite parent) {
		return configurationBlock.createPreferenceContent(parent);
	}

	@Override
	protected String getPreferencePageID() {
		return PREF_ID;
	}

	@Override
	protected String getPropertyPageID() {
		return PROP_ID;
	}

	@Override
	protected boolean hasProjectSpecificOptions(IProject project) {
		return false;
	}
	
	protected void enableProjectSpecificSettings(boolean useProjectSpecificSettings) {
		if (configurationBlock != null) {
			//configurationBlock.useProjectSpecificSettings(useProjectSpecificSettings);
		}
		super.enableProjectSpecificSettings(useProjectSpecificSettings);
	}
}
