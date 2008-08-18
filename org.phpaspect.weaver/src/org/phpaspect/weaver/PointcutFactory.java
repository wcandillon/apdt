package org.phpaspect.weaver;

import org.phpaspect.weaver.impl.PointcutImpl;

public class PointcutFactory {
	public static Pointcut createPointcut(){
		return new PointcutImpl();
	}
}
