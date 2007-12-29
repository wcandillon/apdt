<?php

require_once('Runtime/Aspect.php');

class Singleton implements Aspect{
	
	private static $__instance;
	
	private static $__reflector;
	
	public function getInstance(){
		if(self::$__instance == null){
			self::$__instance = new self();
		}
		return self::$__instance;
	}
	
	public function getReflector(){
		if(self::$__reflector == null){
			self::$__reflector = new ReflectionAspect('Singleton');
		}
		return self::$__reflector;
	}
	
	public function __clone(){
		throw new CloneNotSupportedException("Cloning an aspect isn't allowed");
	}
}
?>