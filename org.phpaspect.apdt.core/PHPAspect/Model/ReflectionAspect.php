<?php
require_once 'PHPAspect/addendum/annotations.php';

abstract class ReflectionAspect extends ReflectionAnnotatedClass implements Reflector
{
	public function __construct($className)
	{
		parent::__construct($className);
		if(!$this->isSubclassOf(new ReflectionAnnotatedClass('Aspect')))
		{
			throw new ReflectionException($className . " isn't an aspect");
		}
	}
	
	private function isCodeAdvice(ReflectionAnnotatedMethod $method)
	{
		return $method->hasAnnotation('Advice');	
	}
	
	public function getCodeAdvice($name)
	{
		$codeAdvice = $this->getMethod($name);
		if($this->isCodeAdvice($codeAdvice))
		{
			return new ReflectionCodeAdvice($this, $codeAdvice);
		} else {
			return null;
		}
	}
	
	public function getCodeAdvices($filter = null)
	{
		$codeAdvices = array();
		$methods = $this->getMethods($filter);
		foreach($methods as $method)
		{
			if($this->isCodeAdvice($method))
			{
				$codeAdvices[] = new ReflectionCodeAdvice($this, $method);
			}
		}
		return $codeAdvices;
	}
}
?>