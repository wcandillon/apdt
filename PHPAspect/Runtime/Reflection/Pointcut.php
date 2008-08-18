<?php
require_once 'Runtime/Reflection/PointcutExpression.php';

interface Pointcut extends Reflector {

	/**
	 * The declared name of the pointcut.
	 * @return string
	 */
	public function getName();
	
	/**
	 * The modifiers associated with the pointcut declaration.
	 * @return int
	 */
	public function getModifiers();
	
	/**
	 * The pointcut parameters.
	 * @return ReflectionParameter[]
	 */
	public function getParameters();
	
	/**
	 * Number of pointcut parameters.
	 * @return integer
	 */
    public function getNumberOfParameters();
    
	/**
	 * Number of pointcut required parameters.
	 * @return integer
	 */  
    public function getNumberOfRequiredParameters();
    
    /**
	 * The pointcut expression associated with this pointcut.
     * @return PointcutExpression
     */
    public function getPointcutExpression();
}
?>