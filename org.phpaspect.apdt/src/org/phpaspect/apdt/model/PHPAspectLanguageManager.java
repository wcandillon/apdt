package org.phpaspect.apdt.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.core.phpModel.parser.IPhpModel;
import org.eclipse.php.internal.core.phpModel.parser.PHPLanguageManager;
import org.eclipse.php.internal.core.phpModel.parser.PHPLanguageModel;
import org.eclipse.php.internal.core.phpModel.parser.PHPParserManager;
import org.eclipse.php.internal.core.phpModel.parser.PHPUserModel;
import org.eclipse.php.internal.core.phpModel.parser.ParserClient;
import org.eclipse.php.internal.core.phpModel.parser.php5.PHP5DefaultParserClient;

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
		return new PHP5DefaultParserClient(userModel, project);
	}
}
