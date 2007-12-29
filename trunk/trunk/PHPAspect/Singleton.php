<?php

require_once('Runtime/Aspect.php');

class Singleton implements Aspect{
	
	public function getReflector(){
		return new ReflectionAspect('Singleton');
	}
	
	public function getInstance(){
		return new self();
	}
}
?>