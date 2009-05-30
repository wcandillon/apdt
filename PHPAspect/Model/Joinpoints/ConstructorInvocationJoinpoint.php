<?php
require_once 'PHPAspect/Model/Joinpoints/AbstractJoinpoint.php';

class ConstructorInvocationJoinpoint extends AbstractJoinpoint
{	
	public function __construct($source, $target, array $arguments, $fileName, $lineNo)
	{
		$target = new ReflectionAnnotatedClass($target);
		parent::__construct($source, $target, $arguments, $fileName, $lineNo);
	}
	
	public function invoke()
	{	
		if(count($this->args) == 0)
		{
			$instance = $this->target->newInstanceArgs();	
		} else {
			$instance = $this->target->newInstanceArgs($this->args);	
		}
		return $instance;
	}
}
?>