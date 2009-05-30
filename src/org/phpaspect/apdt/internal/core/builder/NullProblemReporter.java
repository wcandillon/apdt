package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class NullProblemReporter implements IProblemReporter {

	public void reportProblem(IProblem problem) {
		//do nothing...
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		return null;
	}

}
