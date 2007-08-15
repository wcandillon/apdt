package org.phpaspect.apdt.internal.core.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.php.internal.core.phpModel.parser.PHPLanguageManager;
import org.eclipse.php.internal.core.phpModel.parser.php5.PHP5LanguageManager;

public class PHPAspectLanguageManagerProvider{

	private static final PHPAspectLanguageManagerProvider instance = new PHPAspectLanguageManagerProvider(); 
	private PHPAspectLanguageManagerProvider() { }
	
	//available models for phpAspect
	private final static Map<PHPVersion, PHPLanguageManager> models = new HashMap<PHPVersion, PHPLanguageManager>();
	
	static {
		models.put(PHPVersion.PHPASPECT, new PHPAspectLanguageManager());
		models.put(PHPVersion.PHP5, new PHP5LanguageManager());
	}
	
	/**
	 * @return language provider instance
	 */
	public static PHPAspectLanguageManagerProvider instance() {
		return instance;
	}

	// get the relevant language model 
	public PHPLanguageManager getPHPLanguageManager(PHPVersion m) {
		assert m == PHPVersion.PHPASPECT || m == PHPVersion.PHP5;
		return models.get(m);
	}
}