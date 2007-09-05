package org.phpaspect.apdt.internal.core.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class PHPAspectBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "org.phpaspect.apdt.core.PHPAspectBuilder";
	
	//TODO: Deactivating the PHPAspectBuilderExtension if this builder is deactivated
	protected IProject[] build(int arg0, Map arg1, IProgressMonitor arg2)
			throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
