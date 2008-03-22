package org.phpaspect.weaver.generators;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.phpaspect.weaver.internal.core.compiler.ast.visitor.PHPAspectVisitor;

public class GeneratePHPAspectSchema {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SchemaDocument sdoc = SchemaDocument.Factory.newInstance();
		
		SchemaDocument.Schema schemaElement  = sdoc.addNewSchema();
		schemaElement.setTargetNamespace("http://phpaspect.org/ast");
		Method[] methods = PHPAspectVisitor.class.getMethods();
		for(int i=0; i < methods.length; i++){
			if(methods[i].getName() == "visit"){
				String nodeName = methods[i].getParameterTypes()[0].toString();
				nodeName = nodeName.substring(nodeName.lastIndexOf('.') + 1);
				schemaElement.addNewElement().setName(nodeName);
				//TopLevelComplexType nodeElement = schemaElement.addNewComplexType();
				//ExplicitGroup sequence = nodeElement.addNewSequence();
				//nodeElement.setName(nodeName);
//                Class nodeType = methods[i].getParameterTypes()[0];
//                Field[] fields = nodeType.getDeclaredFields();
//                for(int j=0; j<fields.length; j++){
//                	String fieldName = fields[j].getName();
//                	LocalElement el = sequence.addNewElement();
//                	el.setName(fieldName);
//                }
			}
		}
//		TopLevelComplexType program = schemaElement.addNewComplexType();
//		program.setName("program");
//		statement =
//		ComplexContent statements = program.addNewComplexContent();
//		statements.setName("statements");
//		
//		ComplexContent aspectDeclaration = 
//		LocalElement localElem = sequence.addNewElement();
//		localElem.setName("myElement");
		
		System.out.println("Schema:\n" + sdoc.toString());
	}

}