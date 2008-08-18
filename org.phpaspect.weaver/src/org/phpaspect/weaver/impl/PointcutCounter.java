package org.phpaspect.weaver.impl;

public class PointcutCounter {

	private static int id = -1;
	
	public static int getId(){
		return id++;
	}
}
