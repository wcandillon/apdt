package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.php.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHP;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;
import org.phpaspect.apdt.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHPAspect;

public class PHPAspectLanguageToolkit extends AbstractLanguageToolkit {

	private final static String PHPASPECT_LANGUAGE = "PHPAspect";
	private static PHPAspectLanguageToolkit toolkit = new PHPAspectLanguageToolkit();

	protected String getCorePluginID() {
		return APDTCorePlugin.PLUGIN_ID;
	}

	public String[] getLanguageFileExtensions() {
		IContentType type = Platform.getContentTypeManager().getContentType(ContentTypeIdForPHPAspect.ContentTypeID_PHPAspect);
		return type.getFileSpecs(IContentType.FILE_EXTENSION_SPEC);
	}

	public String getLanguageName() {
		return PHPASPECT_LANGUAGE;
	}

	public String getNatureId() {
		return PHPAspectNature.NATURE_ID;
	}

	public String getLanguageContentType() {
		return ContentTypeIdForPHPAspect.ContentTypeID_PHPAspect;
	}

	public static IDLTKLanguageToolkit getDefault() {
		return toolkit;
	}
}
