package org.phpaspect.apdt.internal.ui.actions;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.phpaspect.apdt.internal.core.APDTNature;
import org.phpaspect.apdt.internal.ui.dialogs.APDTProjectContentProvider;
import org.phpaspect.apdt.ui.APDTUiPlugin;
import org.phpaspect.internal.core.weaver.WeavingJob;

public class WeaveAction implements IObjectActionDelegate, IWorkbenchWindowActionDelegate {

    private List<IProject> projects = null;
	private IWorkbenchWindow window;
    
	public void setActivePart(IAction action, IWorkbenchPart targetPart){}

	public void run(IAction action) {
		boolean emptySelection = false;
		if(projects.size() == 0)
		{
			emptySelection = true;
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			ListSelectionDialog dlg = new ListSelectionDialog(window.getShell(), root, new APDTProjectContentProvider(), new WorkbenchLabelProvider(), "Select a project to weave:");
			dlg.setTitle("PHPAspect Project Selection");
			dlg.open();
			projects = SelectionUtils.getSelectedProjects(dlg.getResult(), APDTNature.NATURE_ID);
		}
        for(IProject project: projects)
        {
        	try {
				IMarker[] markers = project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
				if(markers.length > 0)
				{
					Shell shell = APDTUiPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
					boolean confirm = MessageDialog.openConfirm(shell, "PHPAspect weaver",
							"The project "+project.getName()+" contains some errors.\nwould you like  to weave it anyways?");
					if(!confirm)
					{
						continue;
					}
				}
				Job job = new WeavingJob("PHPAspect weaving", project);
				job.schedule();
			} catch (CoreException e) {
				e.printStackTrace();
			}
        }
        if(emptySelection)
        {
        	projects.clear();
        }
	}

	public void selectionChanged(IAction action, ISelection selection) {
		projects = SelectionUtils.getSelectedProjects(selection, APDTNature.NATURE_ID);
	}

	public void dispose() {}

	public void init(IWorkbenchWindow window)
	{
		this.window = window;
		ISelection selection = window.getSelectionService().getSelection();
		projects = SelectionUtils.getSelectedProjects(selection, APDTNature.NATURE_ID);
	}

}
