<?php
require_once 'PHPAspect/Model/Joinpoints/Joinpoint.php';
require_once 'PHPAspect/Model/Joinpoints/MethodInvocationJoinpoint.php';
require_once 'PHPAspect/Model/Joinpoints/ConstructorInvocationJoinpoint.php';
require_once 'PHPAspect/Model/Joinpoints/MethodExecutionJoinpoint.php';
require_once 'PHPAspect/AspectRegistry.php';
require_once 'PHPAspect/InvalidCodeAdviceException.php';

class JoinpointRegistry
{
	private static $joinpoint;
	
	public static function set(Joinpoint $joinpoint)
	{
		self::$joinpoint = $joinpoint;
	}
	
	public static function get()
	{
		return self::$joinpoint;
	}
	
	public static function clear()
	{
		self::$joinpoint = null;
	}
}
function isTypeMatching($object, $pattern)
{
	return eregi($pattern, get_class($object));
}

function proceed()
{
	$thisJoinpoint =  JoinpointRegistry::get();
	if($thisJoinpoint instanceof Joinpoint)
	{
		return $thisJoinpoint->invoke();
	}
}

function dispatch(array $advices, Joinpoint $jp, array $predicates = null)
{
	JoinpointRegistry::set($jp);
	$returnValue = null;
	$before = array();
	$around = array();
	$after  = array();
	
	$registry = AspectRegistry::getInstance();
	foreach ($advices as $advice)
	{
		list($aspect, $codeAdvice) = explode(':', $advice);
		if(!class_exists($aspect))
		{
			throw new Exception('Cannot find a definition of '.$aspect.'.');
		}
		$aspect = $registry->getAspect($aspect);
		$codeAdviceReflection = $aspect->getAspect()->getCodeAdvice($codeAdvice);
		if($codeAdviceReflection == null)
		{
			throw new InvalidCodeAdviceException($codeAdvice . " doesn't exists");
		}
		assert($codeAdviceReflection instanceof ReflectionCodeAdvice);
		if($codeAdviceReflection->isBefore())
		{
			$before[] = $codeAdviceReflection;
		}
		if($codeAdviceReflection->isAround())
		{
			$around[] = $codeAdviceReflection;
		}
		if($codeAdviceReflection->isAfter())
		{
			$after[] = $codeAdviceReflection;
		}
	}
	
	foreach ($before as $codeAdvice)
	{
		$id = $codeAdvice->getDeclaringAspect()->getName().':'.$codeAdvice->getName();
		if($predicates == null || $predicates[$id])
		{
			$aspectName = $codeAdvice->getDeclaringClass()->getName();
			$aspect = $registry->getAspect($aspectName);
			$codeAdvice->invoke($aspect, $jp);
		}
	}
	
	if(count($around))
	{
		foreach ($around as $codeAdvice)
		{	
			$id = $codeAdvice->getDeclaringAspect()->getName().':'.$codeAdvice->getName();
			if($predicates == null || $predicates[$id])
			{
				$aspectName = $codeAdvice->getDeclaringClass()->getName();
				$aspect = $registry->getAspect($aspectName);
				$returnValue = $codeAdvice->invoke($aspect, $jp);
			}
		}
	} else {
		$returnValue = $jp->invoke();
	}
	
	$jp->setTarget($returnValue);
	
	foreach ($after as $codeAdvice)
	{
		$id = $codeAdvice->getDeclaringAspect()->getName().':'.$codeAdvice->getName();
		if($predicates == null || $predicates[$id])
		{
			$aspectName = $codeAdvice->getDeclaringClass()->getName();
			$aspect = $registry->getAspect($aspectName);
			$codeAdvice->invoke($aspect, $jp);
		}
	}
	JoinpointRegistry::clear();
	return $returnValue;
}
?>