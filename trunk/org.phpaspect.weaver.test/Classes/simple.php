<?php
;

class Logging implements Aspect{

	private static $instance = null;
	
	private function __construct(){}
	
	public static function getInstance(){
		if(self::$instance == null){
			self::$instance = new Logging();	
		}
		return self::$instance;
	}
	
	/**
	 * @Before
	 * @Id(10)
	 */
	public function testBefore(JoinPoint $jp){
		echo "====";
	}
}

class Foo
{
	private $foo;

	public function bar($arg1, $arg2)
	{
		return "bar";
	}
}

class Fueabar
{
	public function bar(){
		return true;
	}
}

$foo = new Foo();
echo $foo->bar(true, time());
$foo = new Fueabar();
$foo->bar();
$bar->bar();
$bar->foo();
?>