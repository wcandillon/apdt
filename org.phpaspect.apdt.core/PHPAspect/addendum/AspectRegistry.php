<?php
class AspectRegistry
{
	private static $instance = null;
	
	private $aspects = array();
	
	public static function getInstance()
	{
		if(self::$instance == null)
		{
			self::$instance = new self();
		}
		return self::$instance;
	}
	
	private function AspectRegistry()
	{
    	$classes = get_declared_classes();
    	foreach($classes as $class)
    	{
    		if(is_subclass_of($class, 'Aspect'))
			{
    			$aspect = new $class();
				assert($aspect != null);            
				$aspectName = $aspect->getAspect()->getName();
				$this->aspects[$aspectName] = $aspect->getAspect()->newInstanceArgs();
			}
    	}
	}
	
	public function getAspects()
	{
		return $this->aspects;
	}
	
	public function getAspect($name)
	{
		if(isset($this->aspects[$name]))
		{
			return $this->aspects[$name];
		} else {
			return null;
		}
	}
}
?>