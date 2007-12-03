package org.phpaspect.apdt.internal.debug.ui.launching;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.php.internal.debug.ui.PHPDebugUIMessages;
import org.eclipse.php.internal.debug.ui.launching.ApplicationFileSelectionDialog;
import org.eclipse.php.internal.debug.ui.launching.ResourceSorter;
import org.eclipse.swt.widgets.Shell;

public class PHPAspectLaunchUtilities {
	/**
	 * Returns a selected {@link IResource} from a user dialog. 
	 * 
	 * @param project The project to display
	 * @param shell A Shell
	 * @param fileExtensions	The required file extension
	 * @param requiredNatures	The required nature
	 * @param allowExternalFiles Allow selection from an external files that are currently opened in the editor
	 * @return A selected IResource
	 */
	public static IResource getFileFromDialog(IProject project, Shell shell, String[] fileExtensions, String[] requiredNatures, boolean allowExternalFiles) {
		PHPAspectLaunchUtilities inst = new PHPAspectLaunchUtilities();

		ApplicationFileSelectionDialog d = new ApplicationFileSelectionDialog(shell, inst.new WebLaunchLabelProvider(), PHPDebugUIMessages.LaunchUtilities_selectFile, PHPDebugUIMessages.LaunchUtilities_selectProjectFile, fileExtensions, requiredNatures, false, allowExternalFiles);

		if (project != null) {
			d.setInput(project);
			d.setInitialSelection(project);
		} else {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			d.setInput(root);
			d.setInitialSelection(root);
		}
		d.addFilter(new PHPAspectApplicationFileViewerFilter());
		d.setAllowMultiple(false);
		d.setComparator(new ResourceSorter(ResourceSorter.TYPE));
		d.open();

		if (d.getReturnCode() == Window.OK) {
			Object[] targetFiles = d.getResult();
			Object target = targetFiles[0];

			return (IResource) target;
		}

		return null;
	}
	
	public class WebLaunchLabelProvider extends org.eclipse.ui.model.WorkbenchLabelProvider {

		protected String decorateText(String input, Object element) {
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				String filename = file.getName();
				return filename;
			}
			return input;
		}
	}
}
