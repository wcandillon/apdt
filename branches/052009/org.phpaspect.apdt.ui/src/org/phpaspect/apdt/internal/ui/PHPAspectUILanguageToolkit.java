package org.phpaspect.apdt.internal.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.phpaspect.apdt.internal.core.builder.PHPAspectLanguageToolkit;

public class PHPAspectUILanguageToolkit extends AbstractDLTKUILanguageToolkit {

	protected AbstractUIPlugin getUIPLugin() {
		return APDTUiPlugin.getDefault();
	}

	public IDLTKLanguageToolkit getCoreToolkit() {
		return PHPAspectLanguageToolkit.getDefault();
	}
}
