<?php
require_once 'Runtime/Annotations/Before.php';
require_once 'Runtime/Annotations/Around.php';
require_once 'Runtime/Annotations/After.php';
require_once 'Runtime/Annotations/Id.php';

class PHPAspectDispatcher{

	private static $instance = null;
	private $before = array();
	private $around = array();
	private $after = array();
	
	//TODO: check for @Id
	//TODO: check if the joinpoint is valid name(JoinPoint $jp, ...)
	private function __construct(){
		$declaredClasses = get_declared_classes();
		foreach ($declaredClasses as $declaredClass){
			$reflectionClass = new ReflectionAnnotatedClass($declaredClass);
			if($reflectionClass->implementsInterface('Aspect') ){//&& $reflectionClass->isInstantiable()){
				$aspectInstance = $reflectionClass->getMethod('getInstance')->invoke(null);
				$methods = $reflectionClass->getMethods();
				foreach($methods as $method){
					if($method->hasAnnotation('Before') && $method->hasAnnotation('Id')){
						$this->before[$method->getAnnotation('Id')->getValue()] = array($aspectInstance, $method);
					}else if($method->hasAnnotation('Around') && $method->hasAnnotation('Id')){
						$this->around[$method->getAnnotation('Id')->getValue()] = array($aspectInstance, $method);
					}else if($method->hasAnnotation('After') && $method->hasAnnotation('Id')){
						$this->after[$method->getAnnotation('Id')->getValue()] = array($aspectInstance, $method);
					}
				}
			}
		}
	}

	public static function getInstance(){
		if(self::$instance == null){
			self::$instance = new self();
		}
		return self::$instance;
	}
	
	public function getBeforeAdvices(){
		return $this->before;
	}
	
	public function getAroundAdvices(){
		return $this->around;
	}
	public function getAfterAdvices(){
		return $this->after;
	}
}

function PHPAspect_match($object, $pattern){
	return eregi($pattern, get_class($object));
}

function PHPAspect_dispatch(array $ids, JoinPoint $jp, array $predicates=array()){
	$return = null;
	//Before
	$before = PHPAspectDispatcher::getInstance()->getBeforeAdvices();
	foreach($before as $id=>$advice){
		if(in_array($id, $ids) && (!isset($predicates[$id])) || (isset($predicates[$id]) && $predicates[$id])){
			$advice[1]->invoke($advice[0], $jp);
		}
	}
	//Around
	$around = PHPAspectDispatcher::getInstance()->getAroundAdvices();
	if(count($around) > 0){
		foreach($around as $id=>$advice){
			if(in_array($id, $ids) && (!isset($predicates[$id])) || (isset($predicates[$id]) && $predicates[$id])){
				$advice[1]->invoke($advice[0], $jp);
			}
		}
	} else {
		$return = $jp->invoke();
	}
	//After
	$after = PHPAspectDispatcher::getInstance()->getAfterAdvices();
	foreach($after as $id=>$advice){
		if(in_array($id, $ids) && (!isset($predicates[$id])) || (isset($predicates[$id]) && $predicates[$id])){
			$advice[1]->invoke($advice[0], $jp);
		}
	}
	return $return;
}
?>