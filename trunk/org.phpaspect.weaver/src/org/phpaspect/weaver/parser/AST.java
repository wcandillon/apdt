package org.phpaspect.weaver.parser;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.URI;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Program;
import org.eclipse.php.internal.core.ast.parser.AstLexer;
import org.eclipse.php.internal.core.ast.parser.PhpAstLexer5;
import org.eclipse.php.internal.core.ast.parser.PhpAstParser5;
import org.phpaspect.weaver.visitor.PHPAspectVisitor;

import com.thoughtworks.xstream.XStream;

public class AST{
	
	public static enum XMLEncoding {UTF8, UTF16, ISO_8859_1, UNICODE};
	
	private static XStream xstream = new XStream();
	
	static{
		Method[] methods = PHPAspectVisitor.class.getMethods();
		for(int i=0; i<methods.length; i++){
			Class node = methods[i].getParameterTypes()[0];
			xstream.alias(getNodeName(node.toString()), node);
		}
		xstream.omitField(ASTNode.class, "parent");
		xstream.useAttributeFor(int.class);
		xstream.useAttributeFor(boolean.class);
		xstream.useAttributeFor(String.class);
	}
	
	private static String getNodeName(String nodeName){
		String[] str = nodeName.split("\\.");
		String name = str[str.length-1];
		return name;
	}
	
	public static String getXMLAstFromPHP(InputStream phpSource) throws Exception{
		return getXMLAstFromPHP(new InputStreamReader(phpSource));
	}
	
	public static String getXMLAstFromPHP(URI phpSource) throws Exception{
		Reader phpSourceReader = new FileReader(phpSource.toString());
		return getXMLAstFromPHP(phpSourceReader);
	}

	public static Program getAstFromPHP(URI phpSource) throws Exception{
		Reader phpSourceReader = new FileReader(phpSource.toString());
		return getAstFromPHP(phpSourceReader);
	}
	
	public static String getXMLAstFromPHP(File phpSource) throws Exception{
		Reader phpSourceReader = new FileReader(phpSource.getPath());
		return getXMLAstFromPHP(phpSourceReader);
		
	}
	
	public static Program getAstFromPHP(InputStream phpSource) throws Exception{
		return getAstFromPHP(new InputStreamReader(phpSource));
	}
	public static Program getAstFromPHP(File phpSource) throws Exception{
		Reader phpSourceReader = new FileReader(phpSource.getPath());
		return getAstFromPHP(phpSourceReader);
	}
	
	public static String getXMLAstFromPHP(String phpSource) throws Exception{
		Reader phpSourceReader = new StringReader(phpSource);
		return getXMLAstFromPHP(phpSourceReader);
	}
	
	public static Program getAstFromPHP(String phpSource) throws Exception{
		Reader phpSourceReader = new StringReader(phpSource);
		return getAstFromPHP(phpSourceReader);
	}
	
	public static String getXMLAstFromPHP(Reader phpSource) throws Exception{
		Program ast = getAstFromPHP(phpSource);
		return xstream.toXML(ast);
	}
	
	public static Program getAstFromPHP(Reader phpSource) throws Exception{
		AstLexer lexer = new PhpAstLexer5(phpSource);
		PhpAstParser5 parser = new PhpAstParser5(lexer);
		return (Program)parser.parse().value;
	}
	
	public static String getXMLAstFromPHPAspect(InputStream aspect) throws Exception{
		return getXMLAstFromPHPAspect(new InputStreamReader(aspect));
	}
	
	public static String getXMLAstFromPHPAspect(URI aspect) throws Exception{
		Reader aspectReader = new FileReader(aspect.toString());
		return getXMLAstFromPHPAspect(aspectReader);
	}

	public static Program getAstFromPHPAspect(URI aspect) throws Exception{
		Reader aspectReader = new FileReader(aspect.toString());
		return getAstFromPHPAspect(aspectReader);
	}
	
	public static String getXMLAstFromPHPAspect(File aspect) throws Exception{
		Reader phpSourceReader = new FileReader(aspect.getPath());
		return getXMLAstFromPHP(phpSourceReader);
	}
	
	public static Program getAstFromPHPAspect(InputStream aspect) throws Exception{
		return getAstFromPHPAspect(new InputStreamReader(aspect));
	}
	
	public static Program getAstFromPHPAspect(File aspect) throws Exception{
		Reader aspectReader = new FileReader(aspect.getPath());
		return getAstFromPHPAspect(aspectReader);
	}
	
	public static String getXMLAstFromPHPAspect(String aspect) throws Exception{
		Reader aspectReader = new StringReader(aspect);
		return getXMLAstFromPHPAspect(aspectReader);
	}
	
	public static Program getAstFromPHPAspect(String aspect) throws Exception{
		Reader aspectReader = new StringReader(aspect);
		return getAstFromPHPAspect(aspectReader);
	}
	
	public static String getXMLAstFromPHPAspect(Reader aspect) throws Exception{
		Program ast = getAstFromPHPAspect(aspect);
		return xstream.toXML(ast);
	}
	
	public static Program getAstFromPHPAspect(Reader aspect) throws Exception{
		PHPAspectLexer   lexer = new PHPAspectLexer(aspect);
		PHPAspectParser parser = new PHPAspectParser(lexer);
		return (Program)parser.parse().value;
	}
	
	public static Program getAstFromXML(String xml){
		return getAstFromXML(new StringReader(xml));
	}
	
	public static Program getAstFromXML(InputStream xml){
		return getAstFromXML(new InputStreamReader(xml));
	}
	
	public static Program getAstFromXML(Reader xml){
		return (Program)xstream.fromXML(xml);
	}
	
	public static String getXMLAstFromAst(Program ast){
		return xstream.toXML(ast);
	}
}