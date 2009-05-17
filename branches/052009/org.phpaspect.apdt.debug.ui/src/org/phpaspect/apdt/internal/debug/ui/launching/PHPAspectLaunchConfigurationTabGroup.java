package org.phpaspect.apdt.internal.debug.ui.launching;

import java.util.ArrayList;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.php.internal.debug.ui.launching.LaunchConfigurationsTabsRegistry;
import org.eclipse.php.internal.debug.ui.launching.PHPDebugArgumentsTab;
import org.eclipse.php.internal.debug.ui.launching.PHPExecutableLaunchTab;

public class PHPAspectLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {
	
	protected final String CONFIGURATION_TAB_GROUP_ID = "org.eclipse.php.debug.ui.launching.PHPAspectLaunchConfigurationTabGroup.phpaspect";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
	 *      java.lang.String)
	 */
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		AbstractLaunchConfigurationTab[] tabs = LaunchConfigurationsTabsRegistry.getLaunchTabs(CONFIGURATION_TAB_GROUP_ID, mode);
		ArrayList<ILaunchConfigurationTab> list = new ArrayList<ILaunchConfigurationTab>();
		if (tabs != null) {
			for (int i = 0; i < tabs.length; i++) {
				list.add(tabs[i]);
				tabs[i].setLaunchConfigurationDialog(dialog);
			}
		}
		if (list.isEmpty()) {
			PHPExecutableLaunchTab aTab = new PHPAspectLaunchTab();
			aTab.setLaunchConfigurationDialog(dialog);
			list.add(aTab);
		}

		// Add arguments, environment and common tabs to the tabs group.
		PHPDebugArgumentsTab argumentsTab = new PHPDebugArgumentsTab();
		argumentsTab.setLaunchConfigurationDialog(dialog);
		list.add(argumentsTab);

		EnvironmentTab environmentTab = new EnvironmentTab();
		environmentTab.setLaunchConfigurationDialog(dialog);
		list.add(environmentTab);
		
		CommonTab newTab = new CommonTab();
		newTab.setLaunchConfigurationDialog(dialog);
		list.add(newTab);
		
		ILaunchConfigurationTab[] allTabs = list.toArray(new ILaunchConfigurationTab[list.size()]);
		setTabs(allTabs);
	}
}
