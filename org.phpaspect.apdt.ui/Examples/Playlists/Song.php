<?php
class Song
{
  private $name;

  public function __construct($name)
  {
    $this->name = $name;
  }
  
  public function getName()
  {
    return $this->name;
  }

  public function play()
  {
    echo "Playing song " . $this->getName() . "\n";
  }
  
  public function showLyrics()
  {
    echo "Displaying lyrics for " . $this->getName() . "\n";
  } 
}
