<?php
require_once 'PHPAspect/AspectRegistry.php';

function isTypeMatching($object, $pattern)
{
	return eregi($pattern, get_class($object));
}

function dispatch(array $advices, Joinpoint $jp, array $predicates = null)
{
	$returnValue = null;
	$before = array();
	$around = array();
	$after  = array();
	
	$registry = AspectRegistry::getInstance();
	foreach ($advices as $advice)
	{
		list($aspect, $codeAdvice) = explode(':', $advice);
		$aspect = $registry->getAspect($aspect);
		$codeAdviceReflection = $aspect->getCode($codeAdvice);
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
		if($predicates == null || $codeAdvice[$id])
		{
			$aspectName = $codeAdvice->getName();
			$aspect = $registry->getAspect($aspectName);
			$codeAdvice->invoke($aspect, $jp);
		}
	}
	
	$codeAdvices = $registry->getAroundCodeAdvices();
	if(count($codeAdvices))
	{
		foreach ($around as $codeAdvice)
		{
			$aspectName = $codeAdvice->getName();
			$aspect = $registry->getAspect($aspectName);
			$returnValue = $codeAdvice->invoke($aspect, $jp);
		}
	} else {
		$returnValue = $jp->invoke();
	}
	$codeAdvices = $registry->getAfterCodeAdvices();
	foreach ($after as $codeAdvice)
	{
		$aspectName = $codeAdvice->getName();
		$aspect = $registry->getAspect($aspectName);
		$codeAdvice->invoke($aspect, $jp);
	}
	return $returnValue;
}
?>