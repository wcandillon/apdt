# Hello World #

Consider a virtual cart application. There is only two classes: the Order which contains items and the Catalog class which contains all purchasable items. The following code is very simple.

```
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
 
$myOrder = new Order;
$myOrder->addItem('Largo Winch', 1);
$myOrder->addItem('Asterix', 2);
?>
```

However, in a real-world shopping application, this transfer method seems far from adequate. The cart need to be persistent, we must check if the entered references are correct, if the user is properly authenticated. Finally, we must log the operation to the system log. And so on. Not only all these crosscutting concerns would polluate our previous source code in term of code modularization but it also would make code evolution very diffucult: If you want to add a crosscutting concerns into a small php application (about 200 classes for instance), you would have to review manually each method from your 200 classes.

So we want to add crosscutting concerns to our virtual cart without touching the previous listing. First technical concern a logging aspect : we would like to log every item added and then the total amound of the command.

```
<?php 
require_once 'PHPAspect/Model/Aspect.php';

class Log extends Aspect
{
	/**
	 * @After(new(Order))
	 */
	public function logNewOrder()
	{
		echo "New Order\n";	
	}
	
	/**
	 * @After(call(Order->addItem))
	 */
	public function logTotalAmount(Joinpoint $thisJoinpoint)
	{	
		printf("Total amount of the cart : %.2f euros\n",
    		$thisJoinpoint->getSource()->getAmount());
	}
	
	/**
	 * @After(exec(Order::addItem))
	 */
	public function logAddItem(Joinpoint $thisJoinpoint){
	        list($reference, $quantity) = $thisJoinpoint->getArgs();
    	        printf("%d %s added to the cart\n", $quantity, $reference);
	}
}
?>
```

You can create this aspect using the _new Aspect_ creation wizard.

![http://sites.google.com/site/phpaspect/Home/newaspect.png](http://sites.google.com/site/phpaspect/Home/newaspect.png)![http://sites.google.com/site/phpaspect/Home/logaspect.png](http://sites.google.com/site/phpaspect/Home/logaspect.png)

Every Aspect have to be a subtype of the _Aspect_ abstract class. This class gives you the aspect reflection capabilities.
The anatomy of an Aspect is a class with extra annotations.
Pointcut and advice declarations can be made using the _@Before_, _@After_ and _@Around_  annotations.
For instance, _@After(call(Order->addItem))_ intercept all calls to the _addItem_ method in an instance of Order.

To weave the project, use the shortcut in the PHPAspect action set or the popup menu.

![http://sites.google.com/site/phpaspect/Home/converttophp.png](http://sites.google.com/site/phpaspect/Home/converttophp.png)

Once the project woven, it contains a folder named _weaved_ in the project's root that contains the woven program.

![http://sites.google.com/site/phpaspect/Home/phpexplorer.png](http://sites.google.com/site/phpaspect/Home/phpexplorer.png)

After weaving, the execution result is the following :

![http://sites.google.com/site/phpaspect/Home/output.png](http://sites.google.com/site/phpaspect/Home/output.png)