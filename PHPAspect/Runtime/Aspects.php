<?php
abstract class Aspects{
    
    private static $aspects = array();
    
    public function getAspect($aspectName){
        $methodReflection = new ReflectionMethod($aspectName, 'getInstance');     
    }
}
?>