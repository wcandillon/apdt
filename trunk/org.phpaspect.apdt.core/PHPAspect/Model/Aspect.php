<?php
require_once 'PHPAspect/Model/ReflectionAspect.php';

class Aspect extends ReflectionAspect
{
	public function __construct($className)
	{
		parent::__construct($className);
	}
}
?>