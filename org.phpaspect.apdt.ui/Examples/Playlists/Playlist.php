<?php
require_once 'Song.php';

class PlayList
{
	private $name;
	private $songs = array();
	
	public function __construct($name)
	{
		$this->name = $name;
	}
	
	public function setSongs(array $songs)
	{
		$this->songs = $songs;
		return $this;
	}
	
	public function getName()
	{
		return $this->name;
	}
	
	public function play()
	{
		echo "Playing ".$this->name . "\n";
		foreach($this->songs as $song)
		{
			$song->play();
		}
	}
}
?>