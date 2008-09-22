package org.phpaspect.apdt.internal.core.documentModel.provisional.contenttype;

import org.phpaspect.apdt.internal.core.APDTCorePlugin;

public class ContentTypeIdForPHPAspect {
	/**
	 * The value of the contenttype id field must match what is specified in
	 * plugin.xml file. Note: this value is intentially set with default
	 * protected method so it will not be inlined.
	 */
	public final static String ContentTypeID_PHPAspect = getConstantString();

	/**
	 * Don't allow instantiation.
	 */
	private ContentTypeIdForPHPAspect() {
		super();
	}

	static String getConstantString() {
		return APDTCorePlugin.PLUGIN_ID + ".phpaspectsource"; //$NON-NLS-1$
	}
}