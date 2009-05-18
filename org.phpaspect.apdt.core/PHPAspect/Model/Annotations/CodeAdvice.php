<?php
require_once 'PHPAspect/addendum/annotations.php';

abstract class CodeAdvice extends Annotation 
{
	public $value;
	
	public function getPointcut()
	{
		return $this->value;
	}
}
?>