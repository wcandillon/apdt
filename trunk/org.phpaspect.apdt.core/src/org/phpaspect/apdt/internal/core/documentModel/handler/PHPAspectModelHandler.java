package org.phpaspect.apdt.internal.core.documentModel.handler;

import org.eclipse.php.internal.core.documentModel.encoding.PHPDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.document.IDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.ltk.modelhandler.AbstractModelHandler;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.phpaspect.apdt.internal.core.documentModel.loader.PHPAspectDocumentLoader;
import org.phpaspect.apdt.internal.core.documentModel.loader.PHPAspectModelLoader;
import org.phpaspect.apdt.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHPAspect;

public class PHPAspectModelHandler extends AbstractModelHandler{

	/**
	 * Needs to match what's in plugin registry. 
	 * In fact, can be overwritten at run time with 
	 * what's in registry! (so should never be 'final')
	 */
	private static String ModelHandlerID = "org.phpaspect.apdt.core.documentModel.handler"; //$NON-NLS-1$

	public IDocumentLoader getDocumentLoader() {
		return new PHPAspectDocumentLoader();
	}
	
	public PHPAspectModelHandler() {
		super();
		setId(ModelHandlerID);
		setAssociatedContentTypeId(ContentTypeIdForPHPAspect.ContentTypeID_PHPAspect);
	}
	
	public IModelLoader getModelLoader() {
		return new PHPAspectModelLoader();
	}

	public IDocumentCharsetDetector getEncodingDetector() {
		return new PHPDocumentCharsetDetector();
	}
}
