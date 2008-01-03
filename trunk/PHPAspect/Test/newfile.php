<?php
require_once 'Runtime/Annotation/PHPAspect.php';

class Singleton{
	
	/**
	 * @PHPAspect(persistent = true)
	 */
	final public static function foo(){
		return false;
	}
}

$r = new ReflectionMethod('Singleton','foo');
var_dump($r->getModifiers());
?>