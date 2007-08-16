package org.phpaspect.apdt.internal.core.documentModel.handler;

import org.eclipse.php.internal.core.documentModel.handler.PHPModelHandler;
import org.eclipse.php.internal.core.documentModel.loader.PHPDocumentLoader;
import org.eclipse.wst.sse.core.internal.document.IDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.phpaspect.apdt.internal.core.documentModel.loader.PHPAspectDocumentLoader;

public class PHPAspectModelHandler extends PHPModelHandler{

	public IDocumentLoader getDocumentLoader() {
		return new PHPAspectDocumentLoader();
	}

}
