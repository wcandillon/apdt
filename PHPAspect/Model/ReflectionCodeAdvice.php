<?php
require_once 'PHPAspect/addendum/annotations.php';

class ReflectionCodeAdvice extends ReflectionAnnotatedMethod {
	
	private $declaringAspect = null;
	
	public function __construct(ReflectionAspect $declaringAspect, ReflectionAnnotatedMethod $method)
	{
		$this->declaringAspect = $declaringAspect;
		parent::__construct($declaringAspect->getName(), $method->getName());
		if(!$this->getDeclaringClass()->isSubclassOf(new ReflectionAnnotatedClass('Aspect')))
		{
			throw new ReflectionException($declaringAspect->getName() . " isn't an aspect");
		}
	}
	
	/**
	 * Returns true if the code advice can be executed before a particular pointcut is matched
	 *
	 * @return boolean
	 */
	public function isBefore()
	{
		return $this->hasAnnotation('Before');
	}

	/**
	 * Returns true if the code advice can be executed around a particular pointcut that is matched
	 *
	 * @return boolean
	 */
	public function isAround()
	{
		return $this->hasAnnotation('Around');
	}
	
	/**
	 * Returns true if the code advice can be executed after a particular pointcut is matched
	 *
	 * @return boolean
	 */
	public function isAfter()
	{
		return $this->hasAnnotation('After');
	}
	
	/**
	 * Get declaring aspect
	 */
	function getDeclaringAspect()
	{
		return $this->declaringAspect;
	}
}
?>