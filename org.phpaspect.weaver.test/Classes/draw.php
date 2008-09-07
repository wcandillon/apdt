<?php
class Display{}

abstract class Shape
{
	abstract public function moveBy($x, $y);
}

class Point extends Shape
{
	private $x, $y;
	
	public function __construct(){}
	
	public function getX(){ return $this->x; }
	public function getY(){ return $this->y; }
	public function setX($x){ $this->x = $x; }
	public function setY($y){ $this->y = $y; }

	public function moveBy($dx, $dy)
	{
		$this->x += $dx;
		$this->y += $dy;
	}
}

class Line extends Shape
{
	private $p1, $p2;
	
	public function __construct(){}
	
	public function getP1(){ return $this->p1; }
	public function getP2(){ return $this->p2; }
	public function setP1(Point $p1){ $this->p1 = $p1; }
	public function setP2(Point $p2){ $this->p2 = $p2; }
	
	public function moveBy($x, $y){
		$this->p1->moveBy($x, $y);
		$this->p2->moveBy($x, $y);
	}
}

$p1 = new Point();
$p1->setX(0);
$p1->setY(0);

$p2 = new Point();
$p2->setX(100);
$p2->setY(0);

$p3 = new Point();
$p3->setX(50);
$p3->setY(100);

$l1 = new Line();
$l1->setP1($p1);
$l1->setP2($p2);

$l2 = new Line();
$l2->setP1($p1);
$l2->setP2($p3);

$l3 = new Line();
$l3->setP1($p2);
$l3->setP2($p3);
?>