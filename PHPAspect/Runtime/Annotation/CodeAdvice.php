<?php
require_once ('Runtime/Annotation/PHPAspectAnnotation.php');

abstract class CodeAdvice extends PHPAspectAnnotation {

	
	public function getAnnotationTarget(){
		return self::TARGET_METHOD;
	}
}

?>
