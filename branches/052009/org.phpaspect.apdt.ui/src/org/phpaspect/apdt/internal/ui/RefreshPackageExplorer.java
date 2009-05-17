package org.phpaspect.apdt.internal.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.internal.ui.scriptview.ScriptExplorerPart;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;
import org.phpaspect.apdt.internal.ui.text.UIMessages;

public class RefreshPackageExplorer extends UIJob{
	
	private static Job refreshJob;
	private static int previousExecutionTime;
	
	private RefreshPackageExplorer() {
		super(UIMessages.utils_refresh_explorer_job);
	}
	
	// reuse the same Job to avoid excessive updates
	private static Job getRefreshJob() {
		if (refreshJob == null) {
			refreshJob = new RefreshPackageExplorer();
		}
		return refreshJob;
	}


	public static void refreshJob() {
		int delay = 5*previousExecutionTime;
		if (delay < 250) {
			delay = 250;
		} else if (delay > 5000) {
			delay = 5000;
		}
		getRefreshJob().schedule(delay);
	}

	public IStatus runInUIThread(IProgressMonitor monitor) {
		long start = System.currentTimeMillis();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page != null) {
			ScriptExplorerPart explorerPart = (ScriptExplorerPart) page.findView("org.eclipse.php.ui.explorer");
		    if (explorerPart != null) {
		    	TreeViewer treeViewer = explorerPart.getTreeViewer();
		    	assert(treeViewer != null);
				treeViewer.refresh();
		    }
		}
		previousExecutionTime = (int)(System.currentTimeMillis() - start);
		return Status.OK_STATUS;
	}
}
