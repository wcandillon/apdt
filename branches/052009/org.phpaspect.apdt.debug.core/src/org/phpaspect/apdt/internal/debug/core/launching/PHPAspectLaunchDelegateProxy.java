package org.phpaspect.apdt.internal.debug.core.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;
import org.eclipse.php.internal.debug.core.launching.PHPLaunchDelegateProxy;

public class PHPAspectLaunchDelegateProxy extends PHPLaunchDelegateProxy {

	protected ILaunchConfigurationDelegate2 getConfigurationDelegate(ILaunchConfiguration configuration) throws CoreException {
		launchConfigurationDelegate = (ILaunchConfigurationDelegate2)new PHPAspectLaunchDelegate();
		return launchConfigurationDelegate;
	}
}
