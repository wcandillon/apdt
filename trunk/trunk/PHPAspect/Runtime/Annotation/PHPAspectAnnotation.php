<?php

require_once 'annotations.php';

abstract class PHPAspectAnnotation extends Annotation {
		/**
	     * annotation is applicable for classes
	     */
	    const TARGET_CLASS    = 1;
	    /**
	     * annotation is applicable for properties
	     */
	    const TARGET_PROPERTY = 2;
	    /**
	     * annotation is applicable for methods
	     */
	    const TARGET_METHOD   = 4;
	    /**
	     * annotation is applicable for functions
	     */
	    const TARGET_FUNCTION = 8;
	    /**
	     * annotation is applicable for parameters
	     */
	    const TARGET_PARAM    = 16;
	    /**
	     * annotation is applicable for classes, properties, methods and functions
	     */
	    const TARGET_ALL      = 31;

	   	public function getAnnotationTarget(){
	   		return self::TARGET_ALL;
	   	}
}

?>
