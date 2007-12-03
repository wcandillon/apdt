package org.phpaspect.apdt.internal.debug.core.launching;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.php.internal.core.PHPCoreConstants;
import org.eclipse.php.internal.debug.core.launching.PHPExecutableLaunchDelegate;

public class PHPAspectLaunchDelegate extends PHPExecutableLaunchDelegate {
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		//launch.setAttribute(PHPCoreConstants.ATTR_FILE, arg1)
		System.out.println(launch.getAttribute(PHPCoreConstants.ATTR_FILE));
		String fileNameString = configuration.getAttribute(PHPCoreConstants.ATTR_FILE, (String) null);
		super.launch(configuration, mode, launch, monitor);
	}
}
