package org.phpaspect.weaver.test.parser;

import java.io.File;

import org.custommonkey.xmlunit.XMLTestCase;
import org.phpaspect.weaver.internal.core.compiler.ast.ASTGenerator;

public class TestPHPAspectParser extends XMLTestCase {

	public void testAspectSimpleDeclaration() throws Exception{
		String xml = ASTGenerator.getXMLAstFromPHPAspect(new File("Aspects/AspectSimpleDeclaration.ap"));
		assertXpathExists("//aspectdeclaration/ref[@fName='AspectSimpleDeclaration']", xml);
	}

	public void testAspectAttributes() throws Exception{
		String xml = ASTGenerator.getXMLAstFromPHPAspect(new File("Aspects/AspectAttributes.ap"));
        assertXpathExists("//phpfielddeclaration[@modifiers='16']/ref[@fName='$attr1']", xml);
        assertXpathExists("//phpfielddeclaration[@modifiers='32']/ref[@fName='$attr2']", xml);
        assertXpathExists("//phpfielddeclaration[@modifiers='64']/ref[@fName='$attr3']", xml);
        assertXpathExists("//phpfielddeclaration[@modifiers='16']/expression[@fLiteralValue='null'][../ref/@fName='$attr4']", xml);
        assertXpathExists("//phpfielddeclaration[@modifiers='160']/expression/arrayelement[../../ref/@fName='$attr5']", xml);
        assertXpathExists("//phpfielddeclaration[@modifiers='64']/expression[@fLiteralValue='\"foo\"'][../ref/@fName='$attr6']", xml);
        assertXpathExists("//phpfielddeclaration[@modifiers='64']/expression[@fLiteralValue='\"bar\"'][../ref/@fName='$foo']", xml);
        assertXpathExists("//phpfielddeclaration[@modifiers='64']/expression[@fLiteralValue='3.14'][../ref/@fName='$bar']", xml);
	}

	public void testAspectMethods() throws Exception{
		String xml = ASTGenerator.getXMLAstFromPHPAspect(new File("Aspects/AspectMethods.ap"));
		assertXpathExists("//phpmethoddeclaration[@modifiers='64'][@declaringTypeName='AspectMethods']/ref[@fName='foo']", xml);
		String xpath = "//phpmethoddeclaration[@modifiers='65'][@declaringTypeName='AspectMethods']/ref[@fName='bar']" +
						"/../arguments/formalparameter[simplereference/@fName='Foo']"+
						"[variablereference/@fName='$foo'][initialization/@fLiteralValue='\"foo\"']";
		assertXpathExists(xpath, xml);	
	}		
}
