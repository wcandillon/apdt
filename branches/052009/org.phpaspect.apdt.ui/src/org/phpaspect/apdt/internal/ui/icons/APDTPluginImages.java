package org.phpaspect.apdt.internal.ui.icons;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.phpaspect.apdt.internal.ui.APDTUiPlugin;

public class APDTPluginImages {
	
	private static URL baseURL;
	
	static{
		APDTPluginImages.baseURL = APDTUiPlugin.getDefault().getBundle().getEntry("/icons/");
	}
	
	public static final ImageDescriptor DESC_WIZBAN_NEW_ASPECT  = create("newaspect_wiz.gif");
	public static final ImageDescriptor DESC_WIZBAN_NEW_PROJECT = create("newphpaspectprj_wiz_big.gif");

	private static ImageDescriptor create(String iconName) {
		try {
			return ImageDescriptor.createFromURL(makeIconFileURL(iconName));
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}
	
	private static URL makeIconFileURL(String name) throws MalformedURLException {
		if (baseURL == null)
			throw new MalformedURLException();
		return new URL(baseURL, name);
	}
}
