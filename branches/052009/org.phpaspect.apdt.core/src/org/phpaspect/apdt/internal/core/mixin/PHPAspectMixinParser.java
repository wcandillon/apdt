package org.phpaspect.apdt.internal.core.mixin;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinParser;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;

public class PHPAspectMixinParser implements IMixinParser {

	private IMixinRequestor requestor;
	
	public void parserSourceModule(boolean signature, ISourceModule module) {
		assert requestor != null;
		ElementInfo info = null;
		requestor.reportElement(info);
	}

	public void setRequirestor(IMixinRequestor requestor) {
		this.setRequestor(requestor);
	}

	public void setRequestor(IMixinRequestor requestor) {
		this.requestor = requestor;
	}

	public IMixinRequestor getRequestor() {
		return requestor;
	}
}