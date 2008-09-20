package org.phpaspect.apdt.debug.ui;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class APDTDebugUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.phpaspect.apdt.debug.ui";

	// The shared instance
	private static APDTDebugUI plugin;
	
	/**
	 * The constructor
	 */
	public APDTDebugUI() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static APDTDebugUI getDefault() {
		return plugin;
	}

}
