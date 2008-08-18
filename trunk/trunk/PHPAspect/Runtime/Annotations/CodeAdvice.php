<?php
require_once ('addendum/annotations.php');

abstract class CodeAdvice extends Annotation {

	
	public function getAnnotationTarget(){
		return self::TARGET_METHOD;
	}
}

?>
