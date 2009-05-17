package org.phpaspect.apdt.internal.ui.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.phpaspect.internal.core.weaver.Weaver;

public class WeaveAction implements IObjectActionDelegate {

	private ISelection selection;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {}

	public void run(IAction action) {
        List<IProject> projects = SelectionUtils.getSelectedProjects(selection);
        for(IProject project: projects)
        {
        	try {
				Weaver.getInstance().weave(project, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
