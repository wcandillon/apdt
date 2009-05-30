<?php
require_once 'Singleton.ap';

class Foo{}
class Bar{}
$foo1 = new Foo();
$foo2 = new Foo();
$foo3 = new Foo();
$bar1 = new Bar();
$bar2 = new Bar();
$bar3 = new Bar();
var_dump($foo1, $foo2, $foo3, $bar1, $bar2, $bar3);