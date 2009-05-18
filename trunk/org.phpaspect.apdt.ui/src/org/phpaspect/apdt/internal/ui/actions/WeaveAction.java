package org.phpaspect.apdt.internal.ui.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.phpaspect.apdt.core.APDTCorePlugin;
import org.phpaspect.apdt.ui.APDTUiPlugin;
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
			} catch (Exception e) {
				Shell shell = APDTUiPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog.openError(shell, "Weaving error", e.getMessage());
				e.printStackTrace();
			}
        }
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
