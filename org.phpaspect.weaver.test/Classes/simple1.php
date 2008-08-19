<?php
class Foo{
  public function fuebar(){
    return true;
  }
}

class Bar{
  public function fuebar(){
    return true;
  }
}

$foo = new Foo();
$bar = new Bar();
$foo->fuebar();
$bar->fuebar();
$unknown->fuebar();
?>