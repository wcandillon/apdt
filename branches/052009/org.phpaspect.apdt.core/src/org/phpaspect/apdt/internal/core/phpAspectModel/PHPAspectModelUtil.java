package org.phpaspect.apdt.internal.core.phpAspectModel;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.php.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHP;
import org.phpaspect.apdt.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHPAspect;

public class PHPAspectModelUtil {

	public static boolean isPhpAspectFile(final IFile file) {
		IContentDescription contentDescription = null;
		if (!file.exists()) {
			return hasPhpAspectExtention(file);
		}
		try {
			contentDescription = file.getContentDescription();
		} catch (final CoreException e) {
			return hasPhpAspectExtention(file);
		}

		if (contentDescription == null) {
			return hasPhpAspectExtention(file);
		}

		return ContentTypeIdForPHPAspect.ContentTypeID_PHPAspect.equals(contentDescription.getContentType().getId());
	}

	public static boolean hasPhpAspectExtention(final IFile file) {
		final String fileName = file.getName();
		final int index = fileName.lastIndexOf('.');
		if (index == -1) {
			return false;
		}
		String extension = fileName.substring(index + 1);
		final IContentType type = Platform.getContentTypeManager().getContentType(ContentTypeIdForPHPAspect.ContentTypeID_PHPAspect);
		final String[] validExtensions = type.getFileSpecs(IContentType.FILE_EXTENSION_SPEC);
		for (String validExtension : validExtensions) {
			if (extension.equalsIgnoreCase(validExtension)) {
				return true;
			}
		}
		return false;
	}

}
