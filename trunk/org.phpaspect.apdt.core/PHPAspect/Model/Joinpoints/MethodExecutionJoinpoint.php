<?php
require_once 'PHPAspect/Model/Joinpoints/AbstractJoinpoint.php';

class MethodExecutionJoinpoint extends AbstractJoinpoint
{	
	public function __construct($source, $method, array $arguments)
	{
		$method = new ReflectionAnnotatedMethod($source, '__'.$method);
		parent::__construct($source, $method, 
			$arguments, $method->getFileName(), $method->getStartLine());
	}
	
	public function invoke()
	{
		return $this->target->invokeArgs($this->source, $this->args);
	}
}
?>