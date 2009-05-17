<?php
require_once 'addendum/annotations.php';

abstract class CodeAdvice extends Annotation 
{
	public $value;
	
	public function getPointcut()
	{
		return $this->value;
	}
}
?>