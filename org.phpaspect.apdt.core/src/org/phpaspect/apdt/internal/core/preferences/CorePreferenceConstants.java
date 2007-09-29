package org.phpaspect.apdt.internal.core.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;

public class CorePreferenceConstants {
	public interface Keys{
		public static final String PHPASPECT_INTERPRETER = APDTCorePlugin.PLUGIN_ID+".phpAspectInterpreter";
	}
	
	public static IPreferenceStore getPreferenceStore() {
		return APDTCorePlugin.getDefault().getPreferenceStore();
	}
	
	public static void initializeDefaultValues() {
		IPreferenceStore store = getPreferenceStore();
		store.setDefault(Keys.PHPASPECT_INTERPRETER, "");
	}
	
	// Don't instantiate
	private CorePreferenceConstants() {
	}
}
