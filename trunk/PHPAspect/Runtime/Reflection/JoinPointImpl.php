<?php
require_once 'Runtime/Reflection/JoinPoint.php';

class JoinPointImpl implements JoinPoint{

	private $kind;
	private $source;
	private $target;
	private $filename;
	private $line;
	
	public function __construct($kind, Reflector $source, Reflector $target, $filename, $line){
		
		$this->source = $source;
		$this->target = $target;
		$this->filename = $filename;
		$this->line = $line;
		
		switch($kind){
			case CONSTRUCTOR_CALL:
				$this->kind = CONSTRUCTOR_CALL;
				break;
			case METHOD_CALL:
				$this->kind = METHOD_CALL;
				break;
			case METHOD_EXECUTION:
				$this->kind = METHOD_EXECUTION;
				break;
			case FIELD_GET:
				$this->kind = FIELD_GET;
				break;
			case FIELD_SET:
				$this->kind = FIELD_SET;
				break;
			case EXCEPTION_HANDLER:
				$this->kind = EXCEPTION_HANDLER;
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
	
	}
	
	/**
	 * 
	 * @see JoinPoint::getKind()
	 */
	public function getKind() {
	
	}
	
	/**
	 * 
	 * @see JoinPoint::getLine()
	 */
	public function getLine() {
	
	}
	
	/**
	 * 
	 * @see JoinPoint::getSignature()
	 */
	public function getSignature() {
	
	}
	
	/**
	 * 
	 * @see JoinPoint::getTarget()
	 */
	public function getTarget() {
	
	}
	
	public function getSource(){
		
	}
	
	/**
	 * 
	 * @see JoinPoint::getThis()
	 */
	public function getThis() {
	
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
	
	public static function export(){
		
	}
}

?>
