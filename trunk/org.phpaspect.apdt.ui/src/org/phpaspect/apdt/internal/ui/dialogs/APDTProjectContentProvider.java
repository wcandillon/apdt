package org.phpaspect.apdt.internal.ui.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.phpaspect.apdt.internal.core.APDTNature;

public class APDTProjectContentProvider implements ITreeContentProvider {

	public APDTProjectContentProvider()
	{
		super();
	}

	public Object[] getChildren(Object parent)
	{
		List<IProject> projects = new LinkedList<IProject>();
		if(parent instanceof IContainer)
		{
			try {
				IContainer container = (IContainer)parent;
				IResource[] members = container.members();
				for (IResource member : members) {
					if (member instanceof IContainer && member.isAccessible()) {
						if (member instanceof IProject) { // show only PHP projects
							IProject project = (IProject) member;
							if (project.hasNature(APDTNature.NATURE_ID)) {
								projects.add(project);
							}
						}
					}
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return projects.toArray();
	}

	public Object getParent(Object element)
	{
		if (element instanceof IResource)
		{
			return ((IResource) element).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element)
	{
		return getChildren(element).length > 0;
	}

	public Object[] getElements(Object inputElement)
	{
		return getChildren(inputElement);
	}

	public void dispose() {}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}
}
