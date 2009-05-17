package org.phpaspect.apdt.internal.debug.ui.launching;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.php.internal.debug.ui.launching.LaunchUtil;
import org.eclipse.php.internal.debug.ui.launching.LaunchUtilities;
import org.eclipse.php.internal.debug.ui.launching.PHPExecutableLaunchTab;
import org.eclipse.swt.widgets.Text;

public class PHPAspectLaunchTab extends PHPExecutableLaunchTab {
	protected void handleChangeFileToDebug(final Text textField) {
		IFile file = null;
		final IResource resource = PHPAspectLaunchUtilities.getFileFromDialog(null, getShell(), LaunchUtil.getFileExtensions(), LaunchUtil.getRequiredNatures(), true);
		if (resource instanceof IFile)
			file = (IFile) resource;
		if (file != null) {
			textField.setText(file.getFullPath().toString());
			String fileName = ""; //$NON-NLS-1$
			IPath location = file.getLocation();
			if (location != null) {
				fileName = location.toString();
			} else if (resource.getLocationURI() != null) {
				fileName = resource.getLocationURI().toString();
			} else {
				fileName = resource.getFullPath().toString();
			}
			textField.setData(fileName);
		}
	}
}
