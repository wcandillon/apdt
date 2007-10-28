package org.phpaspect.apdt.internal.core.documentModel.loader;

import org.eclipse.php.internal.core.documentModel.loader.PHPDocumentLoader;
import org.eclipse.wst.sse.core.internal.ltk.parser.RegionParser;
import org.eclipse.wst.sse.core.internal.provisional.document.IEncodedDocument;
import org.eclipse.wst.sse.core.internal.text.BasicStructuredDocument;
import org.phpaspect.apdt.internal.core.documentModel.parser.PHPAspectSourceParser;
import org.phpaspect.apdt.internal.core.documentModel.parser.PHPAspectStructuredDocumentReParser;

public class PHPAspectDocumentLoader extends PHPDocumentLoader {
	
	protected IEncodedDocument newEncodedDocument() {
		IEncodedDocument doc = super.newEncodedDocument();
		assert doc instanceof BasicStructuredDocument;
		((BasicStructuredDocument) doc).setReParser(new PHPAspectStructuredDocumentReParser());

		//doc.setPreferredLineDelimiter( "\n" );
		return doc;
	}
	
	public RegionParser getParser() {
		PHPAspectSourceParser parser = new PHPAspectSourceParser();
		// for the "static HTML" case, we need to initialize
		// Blocktags here.
		addHTMLishTag(parser, "script"); //$NON-NLS-1$
		addHTMLishTag(parser, "style"); //$NON-NLS-1$
		return parser;
	}


}
