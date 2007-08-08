package org.phpaspect.apdt.internal.ui.actions;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.phpaspect.apdt.internal.core.builder.PHPAspectNature;


public class TogglePHPAspectNatureAction implements IObjectActionDelegate {

	private ISelection selection;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			for (Iterator it = ((IStructuredSelection) selection).iterator(); it
					.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element)
							.getAdapter(IProject.class);
				}
				if (project != null) {
					toggleNature(project);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
	 *      org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * Toggles sample nature on a project
	 * 
	 * @param project
	 *            to have sample nature added or removed
	 */
	private void toggleNature(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();

			for (int i = 0; i < natures.length; ++i) {
				if (PHPAspectNature.NATURE_ID.equals(natures[i])) {
					// Remove the nature
					String[] newNatures = new String[natures.length - 1];
					System.arraycopy(natures, 0, newNatures, 0, i);
					System.arraycopy(natures, i + 1, newNatures, i,
							natures.length - i - 1);
					description.setNatureIds(newNatures);
					project.setDescription(description, null);
					//We refreshing the PHP Explorer..
					//APDTUtils.refreshPackageExplorer();				
					return;
				}
			}

			// Add the nature
			//We put the PHPAspect as the first element for the icon decoration
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 1, natures.length);
			newNatures[0] = PHPAspectNature.NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
			//We refreshing the PHP Explorer..
			//APDTUtils.refreshPackageExplorer();
		} catch (CoreException e) {
			//TODO: reenforcement
			e.printStackTrace();
		}
	}
	
	private void addPHPAspectNature(IProject project) throws CoreException{
		//TODO: Implement
		//TODO: Adding the runtime Library to the project.
		//TODO: Check the version of PHP, generating an error popup for PHP 4 projects
	}
	
	private void removePHPAspectNature(IProject project) throws CoreException{
		//TODO:removeMarkerOnReferencingProjects (see AJDTUtils)
	}
	
	//TODO: Using a job to refresh the explorer part instead
//	private void refreshExplorerPart(){
//	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//	if (page != null) {
//	    ExplorerPart explorerPart = (ExplorerPart) page.findView("org.eclipse.php.ui.explorer");
//	    if (explorerPart != null) {
//	        // Do something
//	    }
//	}
//		IWorkbenchPartSite site = Workbench.getInstance().getActiveWorkbenchWindow().getPartService().getActivePart().getSite();
//		if (site instanceof ViewSite) {
//			ViewSite viewSite = (ViewSite) site;
//			IWorkbenchPart part = viewSite.getPart();
//			if (part instanceof ExplorerPart) {
//				ExplorerPart explorer = (ExplorerPart) part;
//				explorer.getViewer().refresh();
//			}
//		}
//	}

}
