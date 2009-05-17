package org.phpaspect.apdt.internal.debug.core.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.php.internal.debug.core.IPHPDebugConstants;
import org.eclipse.php.internal.debug.core.launching.PHPExecutableLaunchDelegate;

public class PHPAspectLaunchDelegate extends PHPExecutableLaunchDelegate {
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		//launch.setAttribute(PHPCoreConstants.ATTR_FILE, arg1)
		System.out.println(launch.getAttribute(IPHPDebugConstants.ATTR_FILE_FULL_PATH));
		String fileNameString = configuration.getAttribute(IPHPDebugConstants.ATTR_FILE_FULL_PATH, (String) null);
		super.launch(configuration, mode, launch, monitor);
	}
}
