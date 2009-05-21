<?php
require_once 'PHPAspect/Model/AbstractJoinpoint.php';

class MethodInvocationJoinpoint extends AbstractJoinpoint {
	
	public function __construct($source, $method, array $arguments, $fileName, $lineNo)
	{
		$method = new ReflectionAnnotatedMethod($source, $method);
		parent::__construct($source, $method, $arguments, $fileName, $lineNo);
	}
	
	public function invoke()
	{
		return $this->target->invokeArgs($this->source, $this->args);
	}
}

?>