package org.phpaspect.weaver;

import java.io.StringReader;
import java.lang.reflect.Method;

import java_cup.runtime.Symbol;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Program;
import org.phpaspect.weaver.parser.PHPAspectParser;
import org.phpaspect.weaver.parser.PHPAspectLexer;
import org.phpaspect.weaver.parser.visitors.PHPAspectVisitor;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xstream = new XStream();
		
		Method[] methods = PHPAspectVisitor.class.getMethods();
		for(int i=0; i<methods.length; i++){
			Class node = methods[i].getParameterTypes()[0];
			xstream.alias(getNodeName(node.toString()).toLowerCase(), node);
		}
		xstream.omitField(ASTNode.class, "parent");
		xstream.useAttributeFor(int.class);
		xstream.useAttributeFor(boolean.class);
		xstream.useAttributeFor(String.class);
		
		PHPAspectLexer lexer = new PHPAspectLexer(new StringReader("<?php aspect Foo extends Bar{ public $foo; before(): foo(){ echo 'lol'; }}?>"));
		PHPAspectParser parser = new PHPAspectParser(lexer);
		Program ast = null;
		try {
			ast = (Program)parser.parse().value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xml = xstream.toXML(ast);
		System.out.println(xml);
		Program newAst = (Program)xstream.fromXML(xml);
		
//		UnparseVisitor unparseVisitor = new UnparseVisitor();
//		unparseVisitor.visit(newAst);
//		System.out.println(UnparseVisitor.buffer);
	}
	
	private static String getNodeName(String className){
		String[] str = className.split("\\.");
		return str[str.length-1];
	}
}