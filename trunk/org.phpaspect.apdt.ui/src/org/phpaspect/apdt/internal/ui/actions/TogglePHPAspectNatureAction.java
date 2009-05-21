package org.phpaspect.apdt.internal.ui.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.*;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.phpaspect.apdt.internal.core.APDTNature;

public class TogglePHPAspectNatureAction implements  IObjectActionDelegate {

        private ISelection selection;

        /*
         * (non-Javadoc)
         *
         * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
         */
        public void run(IAction action) {
        	List<IProject> projects = SelectionUtils.getSelectedProjects(selection);
        	for(IProject project: projects)
        	{
                toggleNature(project);
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
        public static void toggleNature(IProject project) {
                try {
                        IProjectDescription description = project.getDescription();
                        String[] natures = description.getNatureIds();

                        for (int i = 0; i < natures.length; ++i) {
                                if (APDTNature.NATURE_ID.equals(natures[i])) {
                                        // Remove the nature
                                        String[] newNatures = new String[natures.length - 1];
                                        System.arraycopy(natures, 0, newNatures, 0, i);
                                        System.arraycopy(natures, i + 1, newNatures, i,
                                                        natures.length - i - 1);
                                        description.setNatureIds(newNatures);
                                        project.setDescription(description, null);
                                        //We refreshing the PHP Explorer..
                                        RefreshPackageExplorer.refreshJob();
                                        return;
                                }
                        }
                        // Add the nature
                        //We put the PHPAspect as the first element for the icon decoration
                        String[] newNatures = new String[natures.length + 1];
                        System.arraycopy(natures, 0, newNatures, 1, natures.length);
                        newNatures[0] = APDTNature.NATURE_ID;
                        description.setNatureIds(newNatures);
                        project.setDescription(description, null);
                        //We refreshing the PHP Explorer..
                        RefreshPackageExplorer.refreshJob();
                } catch (CoreException e) {
                        //TODO: reenforcement
                        e.printStackTrace();
                }
        }
}