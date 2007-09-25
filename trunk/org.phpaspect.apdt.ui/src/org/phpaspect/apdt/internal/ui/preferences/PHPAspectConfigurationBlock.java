package org.phpaspect.apdt.internal.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.core.util.preferences.Key;
import org.eclipse.php.internal.ui.preferences.IStatusChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;
import org.phpaspect.apdt.internal.core.preferences.CorePreferenceConstants.Keys;

public class PHPAspectConfigurationBlock{

	private static final Key PREF_PHPASPECT_INTERPRETER = getKey(Keys.PHPASPECT_INTERPRETER);
	private IStatusChangeListener newStatusChangedListener;
	private IProject project;
	private IWorkbenchPreferenceContainer container;
	private Text containerText;

	public PHPAspectConfigurationBlock(
			IStatusChangeListener newStatusChangedListener, IProject project,
			IWorkbenchPreferenceContainer container) {
		
		this.newStatusChangedListener = newStatusChangedListener;
		this.project = project;
		this.container = container;
	}

	private static Key[] getKeys() {
		return new Key[]{PREF_PHPASPECT_INTERPRETER};
	}

	private static Key getKey(String key) {
		return new Key(APDTCorePlugin.PLUGIN_ID, key);
	}

	public Control createPreferenceContent(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Folder:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		
		return container;
	}

	public boolean hasProjectSpecificOptions(IProject project) {
		return false;
	}
	
	private void dialogChanged() {
		// TODO Auto-generated method stub
		
	}
}
