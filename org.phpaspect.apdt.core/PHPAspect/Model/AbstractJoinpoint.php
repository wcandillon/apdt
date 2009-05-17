<?php
require_once 'PHPAspect/addendum/annotations.php';
require_once 'PHPAspect/Model/Joinpoint.php';

abstract class AbstractJoinPoint implements Joinpoint
{	
	protected $codeAdvice;
	
	protected $source;
	
	protected $target;
	
	protected $args = array();
	
	protected $fileName;
	
	protected $lineNumber;
	
	public function __construct($source, $target, array $args, $fileName, $lineNumber)
	{
		$this->source     = $source;
		$this->target     = $target;
		$this->args       = $args;
		$this->fileName   = $fileName;
		$this->lineNumber = $lineNumber;
	}
	
	public function setAdvice($codeAdvice)
	{
		$this->codeAdvice = $codeAdvice;
	}

	public function getAdvice()
	{
		return $this->codeAdvice;	
	}
	
	public function isBefore()
	{
		return $this->codeAdvice == BEFORE;
	}
	
	public function isAround()
	{
		return $this->codeAdvice == AROUND;
	}
	
	public function isAfter()
	{
		return $this->codeAdvice == AFTER;
	}
		
	public function getSource()
	{
		return $this->source;
	}
	
	public function getTarget()
	{
		return $this->target;
	}
	
	public function getArgs()
	{
		return $this->args;
	}
	
	public function getFileName()
	{
		return $this->fileName;
	}
	
	public function getLineNumber()
	{
		return $this->lineNumber;
	}
}

?>