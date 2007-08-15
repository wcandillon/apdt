package org.phpaspect.apdt.internal.core.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.core.phpModel.parser.IPhpModel;
import org.eclipse.php.internal.core.phpModel.parser.PHPLanguageManager;
import org.eclipse.php.internal.core.phpModel.parser.PHPLanguageModel;
import org.eclipse.php.internal.core.phpModel.parser.PHPParserManager;
import org.eclipse.php.internal.core.phpModel.parser.PHPUserModel;
import org.eclipse.php.internal.core.phpModel.parser.ParserClient;

public class PHPAspectLanguageManager implements PHPLanguageManager {

	private static final String PHPASPECT_FUNCTIONS_PATH = "Resources/phpAspectFunctions.php";

	private PHPLanguageModel languageModel;

	public PHPAspectLanguageManager(){
		languageModel = new PHPAspectLanguageModel(this);
	}
	
	public PHPParserManager createPHPParserManager() {
		return new PHPAspectParserManager();
	}

	public ParserClient createParserClient(PHPUserModel userModel,
			IProject project) {
		return new PHPAspectParserClient(userModel, project);
	}

	public IPhpModel getModel() {
		return languageModel;
	}

	public String getPHPFunctionPath() {
		return PHPASPECT_FUNCTIONS_PATH;
	}

}
