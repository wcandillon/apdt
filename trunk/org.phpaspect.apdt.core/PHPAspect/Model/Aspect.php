<?php
require_once 'PHPAspect/Model/IAspect.php';
require_once 'PHPAspect/Model/ReflectionAspect.php';

abstract class Aspect implements IAspect
{
	private $aspect = null;
	
	public function getAspect()
	{
		if($this->aspect == null)
		{
			$this->aspect = new ReflectionAspect(get_class($this));
		}
		return $this->aspect;
	}
}
?>