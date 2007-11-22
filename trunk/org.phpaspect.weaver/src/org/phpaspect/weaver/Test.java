package org.phpaspect.weaver;

import java.io.StringReader;
import java.lang.reflect.Method;

import java_cup.runtime.Symbol;

import org.eclipse.php.internal.core.ast.nodes.Program;
import org.phpaspect.weaver.parser.PHPAspectParser;
import org.phpaspect.weaver.parser.PHPAspectLexer;
import org.phpaspect.weaver.parser.visitor.PHPAspectVisitor;

import com.thoughtworks.xstream.XStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xstream = new XStream();
		Method[] methods = PHPAspectVisitor.class.getMethods();
		for(int i=0; i<methods.length; i++){
			Class node = methods[i].getParameterTypes()[0];
			xstream.alias(getNodeName(node.toString()), node);
		}
		PHPAspectLexer lexer = new PHPAspectLexer(new StringReader("<?php aspect Foo extends Bar{ public $foo;} ?>"));
		PHPAspectParser parser = new PHPAspectParser(lexer);
		Program ast = null;
		try {
			ast = (Program)parser.parse().value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(xstream.toXML(ast).toString());
	}
	
	private static String getNodeName(String className){
		String[] str = className.split("\\.");
		return str[str.length-1];
	}
}