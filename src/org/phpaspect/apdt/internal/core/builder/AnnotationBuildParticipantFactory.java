package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantFactory;

public class AnnotationBuildParticipantFactory implements IBuildParticipantFactory {
	public IBuildParticipant createBuildParticipant(IScriptProject project)
			throws CoreException {
		return new AnnotationBuildParticipant(project);
	}
}