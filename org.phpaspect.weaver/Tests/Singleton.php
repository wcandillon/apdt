<?php
class Foo{

	private static $instance;
	
	private function __construct(){
		//..
	}
	
	public static function getInstance(){
		if(self::$instance == null){
			return new self;
		}
		return self::$instance;
	}
}
?>