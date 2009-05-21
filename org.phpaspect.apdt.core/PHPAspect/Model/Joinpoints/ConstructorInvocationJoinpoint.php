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
		if($this->target instanceof ReflectionAnnotatedClass)
		{
			$class = $this->target;
		} else {
			$class = new ReflectionAnnotatedClass($this->target);
		}
		
		if(count($this->args) == 0)
		{
			$instance = $class->newInstanceArgs();	
		} else {
			$instance = $class->newInstanceArgs($this->args);	
		}
		return $instance;
	}
}
?>