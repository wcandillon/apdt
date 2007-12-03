package org.phpaspect.apdt.internal.debug.ui.launching;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class PHPAspectApplicationFileViewerFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return isValidParent(parentElement, element)
				&& isNotAspectDirectory(parentElement, element);
	}

	private boolean isNotAspectDirectory(Object parentElement, Object element) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidParent(Object parentElement, Object element) {
		if(parentElement instanceof IProject
				&& element instanceof IContainer
				&& ((IContainer)element).getName() == "weaved"){
			IContainer parent = (IContainer)parentElement;
			System.err.println(parent.getName());
			return true;
		}
		return false;
	}
}
