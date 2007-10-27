package org.phpaspect.apdt.internal.core.documentModel.parser.regions;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.core.documentModel.parser.PhpLexer;
import org.eclipse.php.internal.core.documentModel.parser.regions.PhpScriptRegion;

public class PHPAspectRegion extends PhpScriptRegion {

	public PHPAspectRegion(String newContext, int startOffset,
			IProject project, PhpLexer phpLexer) {
		super(newContext, startOffset, project, phpLexer);
		// TODO Auto-generated constructor stub
	}

}
