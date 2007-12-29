<?php
/**
 * @Aspect
 */
require_once "PHPAspect/Runtime/Aspect.php";
require_once "PHPAspect/Runtime/Aspects.php";

class Singleton extends AbstractSingleton implements DesignPattern{
	
	private static $instance;
	
	private function __construct(){
	
	}
	
	/**
     * @advice
     */
	public static function getInstance(){
		if(self::$instance == null){
			self::$instance = new self();
		}
		return self::$instance;
	}
}
?>