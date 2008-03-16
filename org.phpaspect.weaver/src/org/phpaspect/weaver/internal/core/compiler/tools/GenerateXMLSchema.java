package org.phpaspect.weaver.internal.core.compiler.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.util.XMLWriter;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

public class GenerateXMLSchema{
	
	public static void Main(String[] args){
		try {
			generateSchema("phpaspect_ast.xsd");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generateSchema(String file) throws FileNotFoundException, UnsupportedEncodingException{
		OutputStream out = new FileOutputStream(file);
		XMLWriter xml = new XMLWriter(out);
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("xmlns:xs", "http://www.w3.org/2001/XMLSchema");
		parameters.put("targetNamespace", "http://phpaspect.org/ast");
		parameters.put("xmlns", "http://phpaspect.org/ast");
		parameters.put("elementFormDefault", "qualified");
		xml.startTag("xs:schema", parameters);
		
		Method[] methods = PHPAspectVisitor.class.getMethods();
		for(int i=0; i<methods.length; i++){
			Class node = methods[i].getParameterTypes()[0];
			//xstream.alias(getNodeName(node.toString()), node);
		}
		
		xml.endTag("xs:schema");
		
		System.out.println(xml.toString());
//		xstream.omitField(ASTNode.class, "parent");
//		xstream.useAttributeFor(int.class);
//		xstream.useAttributeFor(boolean.class);
//		xstream.useAttributeFor(String.class);
//	}
	}
}
