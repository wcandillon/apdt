package org.phpaspect.apdt.internal.core.documentModel.loader;

import org.eclipse.php.internal.core.documentModel.loader.PHPDocumentLoader;
import org.eclipse.php.internal.core.documentModel.loader.PHPModelLoader;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;

public class PHPAspectModelLoader extends PHPModelLoader {

	public IDocumentLoader getDocumentLoader() {
		if (documentLoaderInstance == null) {
			documentLoaderInstance = new PHPAspectDocumentLoader();
		}
		return documentLoaderInstance;
	}

	public IModelLoader newInstance() {
		return new PHPModelLoader();
	}
}
