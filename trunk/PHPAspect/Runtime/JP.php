<?php
abstract class JP{

	/**
	 * 
	 * @return boolean
	 */
	public static function match(JoinPoint $joinPoint, array $args){
		switch($joinPoint->getKind()){
			case JoinPoint::CONSTRUCTOR_CALL:
				return self::matchConstructorCall($joinPoint, $args[0], $args[1]);
			default:
				return false;
		}
	}
	
	public static function matchConstructorCall(JoinPoint $joinPoint, $className, array $args){
		return false;
	}
}
?>
