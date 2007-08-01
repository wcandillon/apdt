/*******************************************************************************
 * Copyright (c) 2006 Zend Corporation and IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zend and IBM - Initial implementation
 *******************************************************************************/
package org.eclipse.php.internal.core.phpModel.parser.phpAspect;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.core.phpModel.parser.*;


public class PHPAspectLanguageManager implements PHPLanguageManager {

	private static final String PHPASPECT_FUNCTIONS_PATH = "Resources/phpAspectFunctions.php";

	private PHPLanguageModel languageModel;

	public PHPAspectLanguageManager() {
		languageModel = new PHPAspectLanguageModel(this);
	}

	public IPhpModel getModel() {
		return languageModel;
	}

	public PHPParserManager createPHPParserManager() {
		return new PHPAspectParserManager();
	}

	public String getPHPFunctionPath() {
		return PHPASPECT_FUNCTIONS_PATH;
	}

	public ParserClient createParserClient(PHPUserModel userModel, IProject project) {
		return new PHPAspectDefaultParserClient(userModel, project);
	}

}
