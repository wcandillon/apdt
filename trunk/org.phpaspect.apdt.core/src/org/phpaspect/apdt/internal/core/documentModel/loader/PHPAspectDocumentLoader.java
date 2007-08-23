package org.phpaspect.apdt.internal.core.documentModel.loader;

import org.eclipse.php.internal.core.documentModel.loader.PHPDocumentLoader;
import org.eclipse.php.internal.core.documentModel.parser.PhpSourceParser;
import org.eclipse.wst.sse.core.internal.ltk.parser.RegionParser;
import org.phpaspect.apdt.internal.core.parser.PHPAspectSourceParser;

public class PHPAspectDocumentLoader extends PHPDocumentLoader {
	public RegionParser getParser() {
		PHPAspectSourceParser parser = new PHPAspectSourceParser();
		// for the "static HTML" case, we need to initialize
		// Blocktags here.
		addHTMLishTag(parser, "script"); //$NON-NLS-1$
		addHTMLishTag(parser, "style"); //$NON-NLS-1$
		return parser;
	}


}
