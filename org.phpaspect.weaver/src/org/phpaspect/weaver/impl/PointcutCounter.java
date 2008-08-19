package org.phpaspect.weaver.impl;

public class PointcutCounter {

	private static int id = 0;
	
	public static int getId(){
		return ++id;
	}
}
