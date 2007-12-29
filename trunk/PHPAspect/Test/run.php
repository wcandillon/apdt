<?php
/*
aspect Singleton{

    public $instances = array();

    pointcut singleton:new(Foo(*)) || new(Bar(*));

    around(): singleton{
        $className = $thisJoinPoint->getClassName();
        if(!isset($thisAspect->instances[$className])){
            $thisAspect->instances[$className] = proceed();
        }
        return $thisAspect->instances[$className];
    }

    public function Foo,Bar::__clone(){
        throw new Exception('Clone of '.get_class($this).' is not  allowed.', E_USER_ERROR);
    }
}
*/
class ConstructionJoinPoint extends JoinPoint{
	
	private $className;
	private $args;
	
	public function __construct($className, $args){
		$this->className = $className;
		$this->args		 = $args;
	}
	
	public function proceed(){
		$obj = new ReflectionClass($this->className);
		return $obj->newInstanceArgs($this->args);
	}
	
	public function getClassName(){ return $this->className; }
	public function getArgs(){ return $this->args; }
}

class Weaver{
	
	public static function newInstance($ids, $className, $args=array()){
		
		$thisJoinPoint = new ConstructionJoinPoint($className, $args);
		
		foreach($this->getBeforeAdvices($ids) as $advice){
			$advice->invoke($thisJoinPoint);
		}

		if(!is_empty($advices = $this->getAroundAdvices($ids))){
			foreach($advices as $advice){
				$thisJoinPoint = $advice->invoke($thisJoinPoint);
			}
		}else{
			$thisJoinPoint = $thisJoinPoint->proceed();
		}
		
		foreach($this->getAfterAdvices($ids) as $advice){
			$advice->invoke($returnValue);
		}
		
		return $thisJoinPoint;
	}
}
abstract class Aspect{
	private $advices = array();
}
final class Singleton{
	
	private static $aspectInstance = null;
	private $instances = array();
	
	public static function getAspectInstance(){
		if(self::$aspectInstance == null){
			self::$aspectInstance = new Singleton();
		}
		return self::$aspectInstance;
	}
	
	private function __construct(){
		//On enregistre les callbacks sur les advices de l'aspect	
	}
	
	public function advice0($thisJoinPoint){
        $className = $thisJoinPoint->getClassName();
        if(!isset($this->instances[$className])){
            $this->instances[$className] = $thisJoinPoint->proceed();
        }
        return $this->instances[$className];		
	}
	//public function 
}

class Foo{
    public function showInstance(){
        var_dump($this);
    }
}

class Bar{
    private $state;

    public function __construct($state=false){
        $this->state = $state;
    }

    public function showInstance(){
        var_dump($this);
    }
}

$a = Weaver::newInstance(array(1), "Foo");
$a->showInstance();
$b = Weaver::newInstance(array(1), "Foo");
$bar = 'Bar';
$c = Weaver::newInstance(array(1), $bar, array(true));
$c->showInstance();
$d = Weaver::newInstance(array(1), "Bar", array(false));
$d->showInstance();
?>