<?php
require_once 'BillingService.ap';

require_once 'Playlist.php';

$song = new Song("Kris Kringle Was A Cat Thief");
$song2 = new Song("Rock n Roll McDonald's");
$favorites = new Playlist("Wesley Willis - Greatest Hits");
$songs = array();
$songs[] = $song;
$songs[] = $song2;
$favorites->setSongs($songs);
$favorites->play();
$song->showLyrics();
?>