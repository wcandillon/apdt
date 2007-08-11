package org.phpaspect.apdt.internal.core.parser;

import org.eclipse.php.internal.core.phpModel.javacup.runtime.lr_parser;

public class PHPAspectParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5100759835488006732L;

	private int unexpected;
	private int expected;
	private int line;
	private int column;
	private String fileName;
	
	public PHPAspectParseException(lr_parser parser){
	
	}
	
	public int getUnexpected() {
		return unexpected;
	}
	
	public int getExpected() {
		return expected;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String getFileName() {
		return fileName;
	}
	

}
