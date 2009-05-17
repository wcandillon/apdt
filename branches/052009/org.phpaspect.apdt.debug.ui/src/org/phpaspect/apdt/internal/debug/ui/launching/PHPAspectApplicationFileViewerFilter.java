package org.phpaspect.apdt.internal.debug.ui.launching;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class PHPAspectApplicationFileViewerFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return isWeavedRessource(parentElement, element)
				&& isNotAspectDirectory(parentElement, element);
	}

	private boolean isNotAspectDirectory(Object parentElement, Object element) {
		return !(element instanceof IContainer
					&& ((IContainer)element).getName().equals("_aspects")
					&& ((IContainer)parentElement).getName().equals("weaved"));
	}

	private boolean isWeavedRessource(Object parentElement, Object element) {
		return 	element instanceof IProject
				||(parentElement instanceof IProject
						&& element instanceof IContainer
						&& ((IContainer)element).getName().equals("weaved"))
				|| !(parentElement instanceof IProject);
	}
}
