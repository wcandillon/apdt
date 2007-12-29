<?php
/*******************************************************************************
 * Copyright (c) 2006-2007 William Candillon.
 * All rights reserved.
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution and is available at
 * http://eclipse.org/legal/epl-v10.html
 *
 * @author William Candillon <wcandillon@elv.telecom-lille1.eu>
 * @package    PHPAspect/Annotation/ReflectionAspect
 * @author     William Candillon <wcandillon@elv.telecom-lille1.eu>
 * @license    http://eclipse.org/legal/epl-v10.html EPL
 * @version    1.0.0
 * @link       http://phpaspect.org
 *******************************************************************************/
require_once 'Runtime/Annotation/PHPAspect.php';
require_once 'Runtime/Annotation/InvalidAnnotationTargetException.php';

/**
 * The ReflectionAspect class lets you reverse-engineer aspects at runtime.
 *
 */
class ReflectionAspect extends ReflectionAnnotatedClass{
	
	/*
	 * @type boolean
	 */
	private $persistent;

	/**
	 * Constructor
	 * @throws ReflectionException
	 */
	public function __construct($aspect){
		//If the class isn't loaded
		try{
			parent::__construct($aspect);
		}catch(ReflectionException $e){
			throw new ReflectionException('Aspect '.substr($e->getMessage(), 5));
		}
		
		if(!$this->hasAnnotation('isAspect')){
			throw new ReflectionException('Aspect '.$this->getName().' does not exist');
		}
		
		$this->persistent = $this->getAnnotation('PHPAspect')->isPersistent();
	}
	
	public function getAnnotation($annotation){
		$annotation = parent::getAnnotation($annotation);
		if($annotation instanceof PHPAspectAnnotation &&
			!($annotation->getAnnotationTarget() & PHPAspectAnnotation::TARGET_CLASS)){
			throw new InvalidAnnotationTargetException($annotation->getName().' is not allowed on classes.');
		}
		return $annotation;
	}
	
	/**
	 * Return true if the Aspect is persistent through a session, false otherwise.
	 * @return boolean
	 */
	public function isPersistent(){
		return $this->persistent;
	}
}
?>