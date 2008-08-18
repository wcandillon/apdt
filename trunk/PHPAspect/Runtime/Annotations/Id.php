<?php
require_once ('addendum/annotations.php');

class Id extends Annotation {
	
	public function getAnnotationTarget(){
		return self::TARGET_METHOD;
	}
}

?>