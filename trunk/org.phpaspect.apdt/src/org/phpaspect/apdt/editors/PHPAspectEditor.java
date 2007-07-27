package org.phpaspect.apdt.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class PHPAspectEditor extends TextEditor {

	private ColorManager colorManager;

	public PHPAspectEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
