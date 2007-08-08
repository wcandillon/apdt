package org.phpaspect.apdt.internal.ui.text;

import org.eclipse.osgi.util.NLS;

public class UIMessages extends NLS {
	private static final String BUNDLE_NAME= UIMessages.class.getName();
	
	private UIMessages() {
		// Do not instantiate
	}
	
	public static String apdt_version;
	public static String utils_refresh_explorer_job;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, UIMessages.class);
	}
}
