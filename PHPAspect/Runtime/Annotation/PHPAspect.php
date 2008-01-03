<?php

require_once 'Runtime/Annotation/PHPAspectAnnotation.php';

class PHPAspect extends PHPAspectAnnotation{
	
	protected $persistent = false;

	public function isPersistent(){
		return $this->persistent;
	}
	
	public function getAnnotationTarget(){
		return self::TARGET_CLASS;
	}
}
?>