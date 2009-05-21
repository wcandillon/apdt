package org.phpaspect.apdt.internal.ui.actions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public abstract class SelectionUtils {
	
	public static List<IProject> getSelectedProjects(ISelection selection)
	{
		return getSelectedProjects(selection, null);
	}
	
    @SuppressWarnings("unchecked")
    public static List<IProject> getSelectedProjects(ISelection selection, String natureId)
    {
            List<IProject> projectList = new LinkedList<IProject>();
            if(selection instanceof IStructuredSelection)
            {
        for (Iterator it = ((IStructuredSelection) selection).iterator(); it
                        .hasNext();) {
                Object element = it.next();
                IResource resource = null;
                if (element instanceof IResource) {
                    resource = (IResource) element;
                } else if (element instanceof IAdaptable) {
                    resource = (IResource) ((IAdaptable) element)
                                        .getAdapter(IResource.class);
                }
                if (resource != null) {
                    IProject project = resource.getProject();
                    if(!projectList.contains(project))
                    {
                    	if(natureId != null)
                    	{
                    		try {
								if(project.hasNature(natureId))
								{
									projectList.add(project);
								}
							} catch (CoreException e) {
								e.printStackTrace();
							}
                    	} else {
                            projectList.add(project);
                    	}
                    }
                }
        }
            }
            return projectList;
    }
}
