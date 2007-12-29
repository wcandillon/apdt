<?php

require_once 'Weaver.php';

class PHPAspectWeaver implements Weaver{

	private $aspects = array();
	
	private static $instance;
	
	private function __construct(){
		$declaredClasses = get_declared_classes();
		
		foreach($declaredClasses as $declaredClass){
			$reflectionClass = new ReflectionClass($declaredClass);
			if($reflectionClass->implementsInterface('Aspect')
				&& $reflectionClass->isInstantiable()){
				$this->aspects[] = $reflectionClass->getMethod('getInstance')->invoke();
			}
		}
	}

	public static function getInstance(){
		if(self::$instance == null){
			self::$instance = new self();
		}
		return self::$instance;
	}

	public function newInstance($ids=array(), $className, $args=array()){
		
	}
}
?>
