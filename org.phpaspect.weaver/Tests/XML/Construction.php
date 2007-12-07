<?php
$foo = new Foo();
$bar = 'Bar';
$bar = new Bar(new Foo, new Bar(1, new $bar));
$foo = new $bar();

function foo(){
	return new Foo();
}
?>