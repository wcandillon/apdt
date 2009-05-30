package org.phpaspect.apdt.internal.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.IWorkbenchWizard;
import org.phpaspect.apdt.internal.ui.wizards.NewAspectWizard;

public class NewAspectAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	
	public void dispose() {}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		IWorkbenchWizard newAspect = new NewAspectWizard();
		WizardDialog dialog = new WizardDialog(window.getShell(), newAspect);
		dialog.create();
		dialog.open();
	}

	public void selectionChanged(IAction action, ISelection selection) {}
}
