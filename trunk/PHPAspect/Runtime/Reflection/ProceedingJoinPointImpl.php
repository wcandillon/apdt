<?php

require_once ('Runtime/Reflection/ProceedingJoinPoint.php');
require_once ('Runtime/Reflection/JoinPoint.php');
require_once ('Runtime/Reflection/JoinPointImpl.php');


class ProceedingJoinPointImpl extends JoinPointImpl implements ProceedingJoinPoint{
	
	public function proceed(array $args = array()){
    	$this->getTarget()->invoke($args);
	}
}
?>
