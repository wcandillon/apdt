package org.phpaspect.apdt.internal.ui.filters;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class WeavedSourceFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parent, Object element) {
		if(parent instanceof IProject && element instanceof IFolder &&
				((IFolder)element).getName().equals("weaved")){
			return false;
		}
		return true;
	}

}
