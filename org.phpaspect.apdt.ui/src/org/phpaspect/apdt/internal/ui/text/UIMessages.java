package org.phpaspect.apdt.internal.ui.text;

import org.eclipse.osgi.util.NLS;

public class UIMessages extends NLS {
	private static final String BUNDLE_NAME= UIMessages.class.getName();
	
	private UIMessages() {
		// Do not instantiate
	}
	
	/* JOBS */
	public static String utils_refresh_explorer_job;
	/* Wizard for creating new aspects */
	public static String NewAspectCreationWizard_title;
	public static String NewAspectCreationWizardPage_title;
	public static String NewAspectCreationWizardPage_description;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, UIMessages.class);
	}
}
