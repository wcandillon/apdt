<?php
    /* vim: set expandtab tabstop=4 shiftwidth=4: */
    // +----------------------------------------------------------------------+
    // | PHP version 5                                                        |
    // +----------------------------------------------------------------------+
    // | Copyright (c) 1997-2004 The PHP Group                                |
    // +----------------------------------------------------------------------+
    // | This source file is subject to version 3.0 of the PHP license,       |
    // | that is bundled with this package in the file LICENSE, and is        |
    // | available through the world-wide-web at the following url:           |
    // | http://www.php.net/license/3_0.txt.                                  |
    // | If you did not receive a copy of the PHP license and are unable to   |
    // | obtain it through the world-wide-web, please send a note to          |
    // | license@php.net so we can mail you a copy immediately.               |
    // +----------------------------------------------------------------------+
    // | Authors: Claudio Bustos <cdx@users.sourceforge.net>                  |
    // |          Jens Bierkandt <schtorch@users.sourceforge.net>             |
    // +----------------------------------------------------------------------+
    //
    // $Id:
    require_once "PHPUnit.php";
    //require_once "PHP/Beautifier.php";
	if (file_exists('../Beautifier.php')) {
		include_once '../Beautifier.php';
	} else {
		include_once "PHP/Beautifier.php";
	}
    class Beautifier_StreamWrapper_Tarz extends PHPUnit_TestCase {
        var $sFile;
        var $sFileBz2;
        function Beautifier_StreamWrapper_Tarz($name) {
            $this->PHPUnit_TestCase($name);
            $this->sFile = dirname(__FILE__) ."/Beautifier.tar.gz";
            $this->sFileBz2 = dirname(__FILE__) ."/Beautifier.tar.bz2";
        }
        function setUp() {
        }
        function testopen() {
            $this->assertTrue(@fopen('tarz://'.$this->sFile."#Beautifier.php", 'r'));
            $this->assertfalse(@fopen('tarz://'.$this->sFile."#package2.xml", 'r'));
        }
        function testclose() {
            $fp = @fopen('tarz://'.$this->sFile."#Beautifier.php", 'r');
            $this->assertTrue(fclose($fp));
        }
        /**
        * In PHP5RcX, stream_feof was broken.
        * So, this function test use of feof, do loop (fread doc),
        * file_get_contents and file
        */
        function testread() {
            $oTar = new Archive_Tar($this->sFile, 'gz');
            $sExpected = $oTar->extractInString('Beautifier.php');
            unset($oTar);
            $sActual = '';
            $fp = @fopen('tarz://'.$this->sFile."#Beautifier.php", 'rb');
            $this->assertTrue($fp);
            if ($fp) {
                while (!feof($fp)) {
                    $sBuffer = fread($fp, 8192);
                    $sActual.= $sBuffer;
                }
            }
            $this->assertTrue($sExpected == $sActual, 'fread');
            $sActual = '';
            rewind($fp);
            do {
                $data = fread($fp, 8192);
                if (strlen($data) == 0) {
                    break;
                }
                $sActual.= $data;
            }
            while (true);
            fclose($fp);
            $this->assertTrue($sExpected == $sActual, 'do');
            $sActual = file_get_contents('tarz://'.$this->sFile."#Beautifier.php");
            $this->assertTrue($sExpected == $sActual, 'file_get_contents');
            $sActual = implode("", file('tarz://'.$this->sFile."#Beautifier.php"));
            $this->assertTrue($sExpected == $sActual, 'file');
        }
        function testBz2() {
            $oTar = new Archive_Tar($this->sFileBz2, 'bz2');
            $sExpected = $oTar->extractInString('Beautifier.php');
            unset($oTar);
            $sActual = file_get_contents('tarz://'.$this->sFileBz2."#Beautifier.php");
            $this->assertTrue($sExpected == $sActual, 'file_get_contents');
        }
        function testOpenDir() {
            $this->assertTrue($oDir = opendir('tarz://'.$this->sFile));
        }
        function testReadDir() {
            if ($dh = opendir('tarz://'.$this->sFile)) {
                while (($file = readdir($dh)) !== false) {
                    $this->assertTrue($fp = fopen('tarz://'.$this->sFile.'#'.$file, 'r'));
                    $this->assertTrue(fclose($fp));
                }
                closedir($dh);
            }
        }
    }
    $suite = new PHPUnit_TestSuite('Beautifier_StreamWrapper_Tarz');
    $result = PHPUnit::run($suite);
    echo $result->toString();
?>