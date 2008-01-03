<?php
require_once 'Runtime/Reflection/ReflectionAspect.php';

abstract class PHPAspectRuntime{

	public static function getAspect($aspect){
		return new ReflectionAspect($aspect);
	}
	
	public static function hasAspect($aspect){
		try{
			new ReflectionAspect($aspect);
		}catch(ExceptionReflection $e){
			return false;
		}
		return true;
	}
}
?>