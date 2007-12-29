<?php
class InterType{

	private $identifier;
	
	private $subtype = false;
	
	public function __construct($identifier){
		if(substr($identifier, -1) == '+'){
			$this->subtype = true;
			$this->identifier = substr($identifier, 0, -1);
		}else{
			$this->identifier = $identifier;
		}
	}
	
	public function getIdentifier(){
		return $this->identifier;
	}
	
	public function isSubType(){
		return $this->subtype;
	}
	
	public function has($type){
		if(gettype($type) == 'Object' && $subtype){
			
		}else{
			
		}
	}
}
?>