package org.phpaspect.apdt.internal.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.php.internal.ui.editor.PHPStructuredEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.phpaspect.apdt.internal.core.documentModel.parser.PHPAspectSourceParser;

public class PHPAspectEditor extends PHPStructuredEditor {
	
	public static final String EDITOR_ID = "org.phpaspect.apdt.internal.ui.editor.PHPAspectEditor"; //$NON-NLS-1$

	public PHPAspectEditor(){
		super();
	}
	
	protected void doSetInput(IEditorInput input) throws CoreException {
		super.doSetInput(input);
//		IResource resource = ((FileEditorInput)input).getFile();
//		if(resource instanceof IFile){
//			PHPAspectSourceParser.editFile.set(resource);
//		}
	}
}
