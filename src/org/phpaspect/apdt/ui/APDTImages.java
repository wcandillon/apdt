package org.phpaspect.apdt.ui;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.PluginImagesHelper;
import org.eclipse.jface.resource.ImageDescriptor;

public class APDTImages {
	private static final PluginImagesHelper helper = new PluginImagesHelper(
			APDTUiPlugin.getDefault().getBundle(), new Path("/icons")); //$NON-NLS-1$

	public static final ImageDescriptor OVR_ASPECT_FILE = helper
			.createUnManaged(PluginImagesHelper.T_OVR, "aspect_ovr.gif"); //$NON-NLS-1$


	public static final ImageDescriptor DESC_WIZBAN_ASPECT_FILE = helper
			.createUnManaged(PluginImagesHelper.T_WIZBAN, "newaspect_wiz.gif"); //$NON-NLS-1$
	
	public static final ImageDescriptor DESC_WIZBAN_ASPECT_PROJECT = helper
			.createUnManaged(PluginImagesHelper.T_WIZBAN, "newphpaspectprj_wiz_big.gif"); //$NON-NLS-1$
}
