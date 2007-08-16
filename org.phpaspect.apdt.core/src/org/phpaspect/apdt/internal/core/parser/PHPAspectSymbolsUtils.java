package org.phpaspect.apdt.internal.core.parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PHPAspectSymbolsUtils {
	
	private static Map<Integer, String> tokens =
								new HashMap<Integer, String>();

	static{
		Field[] fields = PHPAspectSymbols.class.getDeclaredFields();
		for(int i=0; i<fields.length; i++){
			try {
				tokens.put(fields[i].getInt(null), fields[i].getName());
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
	}

	public static String getTokenName(int id){
		return tokens.get(id);
	}
}
