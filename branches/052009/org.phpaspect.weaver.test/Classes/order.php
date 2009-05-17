<?php
class Order{

    private $items = array();  
    private $amount = 0;
    
    public function addItem($reference, $quantity){
      $this->items[] = array($reference, $quantity);
      $this->amount += $quantity*Catalog::getPrice($reference);
    }
    
    public function getAmount(){
      return $this->amount;
    }
}
 
class Catalog{
  private static $priceList = array('Largo Winch' => 9.31,
                          'Asterix' => 8.46, 'XIII' => 8.70);
 
  public static function getPrice($reference){
    return self::$priceList[$reference];
  }
}

try
{
	$myOrder = new Order;
	$myOrder->addItem('Largo Winch', 1);
	$myOrder->addItem('Asterix', 2);
} catch(IllegalArgumentException $e) {
	echo $e->getMessage()+"\n"
}
?>