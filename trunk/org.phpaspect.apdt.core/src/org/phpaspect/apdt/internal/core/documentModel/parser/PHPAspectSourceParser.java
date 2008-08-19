package org.phpaspect.apdt.internal.core.documentModel.parser;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.php.internal.core.documentModel.parser.PHPTokenizer;
import org.eclipse.php.internal.core.documentModel.parser.PhpSourceParser;
import org.eclipse.wst.sse.core.internal.ltk.parser.BlockTokenizer;
import org.eclipse.wst.sse.core.internal.ltk.parser.RegionParser;

public class PHPAspectSourceParser extends PhpSourceParser {

	private IProject project;

	public PHPAspectSourceParser(){
		super();
		IResource resource = (IResource) editFile.get();
		if(resource instanceof IProject){
			project = (IProject)resource;
		} else if(resource instanceof IFile){
			project = ((IFile)resource).getProject();
		}
	}
	
	/*
	 * Change the Tokenizer used by the PHPSourceParser to make it PHPAspect aware
	 */
	public BlockTokenizer getTokenizer() {
		if (fTokenizer == null) {
			PHPAspectTokenizer phpTokenizer = new PHPAspectTokenizer();
			phpTokenizer.setProject(project);
			fTokenizer = phpTokenizer;
		}
		return fTokenizer;
	}

	public RegionParser newInstance() {
		PHPAspectSourceParser newInstance = new PHPAspectSourceParser();
		PHPAspectTokenizer tokenizer = (PHPAspectTokenizer)getTokenizer().newInstance();
		tokenizer.setProject(project);
		newInstance.setTokenizer(tokenizer);
		return newInstance;
	}
}
