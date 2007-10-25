package org.phpaspect.apdt.internal.core.documentModel.parser.regions;

import org.eclipse.php.internal.core.documentModel.parser.regions.PHPRegionTypes;

public interface PHPAspectRegionTypes extends PHPRegionTypes {
	static final String PHP_ASPECT = "PHP_ASPECT";
	static final String PHP_PERSESSION = "PHP_PERSESSION";
	static final String PHP_BEFORE = "PHP_BEFORE";
	static final String PHP_AROUND = "PHP_AROUND";
	static final String PHP_AFTER = "PHP_AFTER";
	static final String PHP_POINTCUT = "PHP_POINTCUT";	
}