<?php
require_once 'Runtime/Reflection/JoinPoint.php';

class JoinPointImpl implements JoinPoint{

	private $kind;
	private $source;
	private $target;
	private $args = array();
	private $filename;
	private $line;
	
	public function __construct($kind, $source, Reflector $target, array $args, $filename, $line){
		$this->source = $source;
		$this->target = $target;
		$this->args = $args;
		$this->filename = $filename;
		$this->line = $line;
		
		switch($kind){
			case self::CONSTRUCTOR_CALL:
				$this->kind = self::CONSTRUCTOR_CALL;
				break;
			case self::METHOD_CALL:
				$this->kind = self::METHOD_CALL;
				break;
			case self::METHOD_EXECUTION:
				$this->kind = self::METHOD_EXECUTION;
				break;
			case self::FIELD_GET:
				$this->kind = self::FIELD_GET;
				break;
			case self::FIELD_SET:
				$this->kind = self::FIELD_SET;
				break;
			case self::EXCEPTION_HANDLER:
				$this->kind = self::EXCEPTION_HANDLER;
				break;
			default:
				throw new InvalidJoinPointException('Invalid PHPAspect JoinPoint detected');
		}
	}
	
	/**
	 * 
	 * @see JoinPoint::getFileName()
	 */
	public function getFileName() {
		return $this->filename;
	}
	
	/**
	 * 
	 * @see JoinPoint::getKind()
	 */
	public function getKind() {
		return $this->kind;
	}
	
	/**
	 * 
	 * @see JoinPoint::getLine()
	 */
	public function getLine() {
		return $this->line;
	}
	
	/**
	 * 
	 * @see JoinPoint::getTarget()
	 */
	public function getTarget() {
		return $this->target;
	}
	
	public function getSource(){
		return $this->source;
	}
	
	/**
	 * 
	 * @see JoinPoint::getThis()
	 */
	public function getThis() {
		return $this->source;
	}
	
	/**
	 * 
	 * @see JoinPoint::toLongString()
	 */
	public function toLongString() {
	
	}
	
	/**
	 * 
	 * @see JoinPoint::toShortString()
	 */
	public function toShortString() {
	
	}
	
	/**
	 * 
	 * @see JoinPoint::toString()
	 */
	public function toString() {
	
	}
	
	/**
	 * 
	 * @see JoinPoint::__toString()
	 */
	public function __toString() {
	
	}
	
	public function invoke(){
		$this->target->invokeArgs($this->source, $this->args);
	}
	
	public static function export(){
		
	}
}

?>
