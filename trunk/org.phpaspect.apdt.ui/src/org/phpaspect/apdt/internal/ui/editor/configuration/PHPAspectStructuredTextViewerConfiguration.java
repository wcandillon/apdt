package org.phpaspect.apdt.internal.ui.editor.configuration;

import org.eclipse.php.internal.ui.editor.configuration.PHPStructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.phpaspect.apdt.internal.ui.editor.LineStyleProviderForPHPAspect;

public class PHPAspectStructuredTextViewerConfiguration extends
PHPStructuredTextViewerConfiguration {
	
	private LineStyleProvider fLineStyleProvider;

	public LineStyleProvider getLineStyleProvider() {
		if (fLineStyleProvider == null) {
			fLineStyleProvider = new LineStyleProviderForPHPAspect();
		}
		return fLineStyleProvider;
	}
}