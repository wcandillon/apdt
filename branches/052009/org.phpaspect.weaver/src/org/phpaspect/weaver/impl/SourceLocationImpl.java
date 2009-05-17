package org.phpaspect.weaver.impl;

import org.phpaspect.weaver.SourceLocation;

public class SourceLocationImpl implements SourceLocation {

	protected String fileName;
	protected String withinType;
	
	public SourceLocationImpl(String fileName, String withinType){
		this.fileName = fileName;
		this.withinType = withinType;
	}

	public String getWithinType() {
		return withinType;
	}

	public String getFileName() {
		return fileName;
	}
}
