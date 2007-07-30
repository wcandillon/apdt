<?php
/*
 * file_replace — Replace all occurrences of the search string with the replacement string
 * mixed str_replace ( mixed $search, mixed $replace, mixed $subject [, int &$count] )
 */
function file_replace($search, $replace, $file){
	$fileContent = file_get_contents($file);
	$newFileContent = str_replace($search, $replace, $fileContent);
	return file_put_contents($file, $newFileContent);
}
?>