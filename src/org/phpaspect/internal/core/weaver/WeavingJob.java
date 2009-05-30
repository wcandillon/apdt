package org.phpaspect.internal.core.weaver;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class WeavingJob extends Job {

	private IProject project;
	
	public WeavingJob(String name, IProject project) {
		super(name);
		this.project = project;
		setUser(true);
		setPriority(Job.SHORT);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			Weaver.getInstance().weave(project, monitor);
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

}
