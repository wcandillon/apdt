<?php
interface Joinpoint {
	
	const BEFORE = 0;
	const AROUND = 2;
	const AFTER  = 4;
	
	public function setAdvice($codeAdvice);
	public function getAdvice();
	public function isBefore();
	public function isAround();
	public function isAfter();
	public function getSource();
	public function setTarget($target);
	public function getTarget();
	public function getArgs();
	public function getFileName();
	public function getLineNumber(); 
}
?>