package org.phpaspect.apdt.internal.ui.actions;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.phpaspect.apdt.core.APDTCorePlugin;
import org.phpaspect.apdt.internal.core.APDTNature;
import org.phpaspect.apdt.ui.APDTUiPlugin;
import org.phpaspect.internal.core.weaver.WeavingJob;

public class WeaveAction implements IObjectActionDelegate {

	private ISelection selection;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {}

	public void run(IAction action) {
        List<IProject> projects = SelectionUtils.getSelectedProjects(selection);
        for(IProject project: projects)
        {
        	try {
				if(project.hasNature(APDTNature.NATURE_ID))
				{
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
				}
				Job job = new WeavingJob("PHPAspect weaving", project);
				job.schedule();
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
