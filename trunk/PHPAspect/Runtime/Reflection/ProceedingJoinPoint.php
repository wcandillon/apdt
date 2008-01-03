<?php
require_once 'Runtime/Reflection/JoinPoint.php';

interface ProceedingJoinPoint extends JoinPoint{

	public function proceed(array $args = array());
}
?>