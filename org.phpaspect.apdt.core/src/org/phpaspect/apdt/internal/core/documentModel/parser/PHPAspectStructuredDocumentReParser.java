package org.phpaspect.apdt.internal.core.documentModel.parser;

import org.eclipse.php.internal.core.documentModel.parser.PhpStructuredDocumentReParser;
import org.eclipse.wst.sse.core.internal.provisional.events.StructuredDocumentEvent;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredTextReParser;

public class PHPAspectStructuredDocumentReParser extends
		PhpStructuredDocumentReParser {


	public PHPAspectStructuredDocumentReParser() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.xml.core.internal.parser.XMLStructuredDocumentReParser#newInstance()
	 */
	public IStructuredTextReParser newInstance() {
		return new PHPAspectStructuredDocumentReParser();
	}
	
	public StructuredDocumentEvent reparse() {
		final StructuredDocumentEvent documentEvent = super.reparse();
		return documentEvent;
	}
}
