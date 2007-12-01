package org.phpaspect.apdt.internal.core.documentModel.parser.regions;

import org.eclipse.php.internal.core.documentModel.parser.regions.PHPRegionTypes;

public interface PHPAspectRegionTypes extends PHPRegionTypes {
	static final public String PHP_ASPECT = "PHP_ASPECT";
	static final public String PHP_PERSESSION = "PHP_PERSESSION";
	static final public String PHP_BEFORE = "PHP_BEFORE";
	static final public String PHP_AROUND = "PHP_AROUND";
	static final public String PHP_AFTER = "PHP_AFTER";
	static final public String PHP_POINTCUT = "PHP_POINTCUT";	
}