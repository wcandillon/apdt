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

import java.io.Reader;

import org.eclipse.php.internal.core.phpModel.parser.CompletionLexer;
import org.eclipse.php.internal.core.phpModel.parser.PHPParserManager;
import org.eclipse.php.internal.core.phpModel.parser.PhpParser;


public class PHPAspectParserManager extends PHPParserManager {

	protected CompletionLexer createCompletionLexer(Reader reader) {
		return new PHPAspectCompletionLexer(reader);
	}

	protected PhpParser createPhpParser() {
		return new PHPAspectParser();
	}

}
