package org.phpaspect.weaver.internal.core.compiler.ast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.php.internal.core.ast.nodes.ASTNode;
import org.eclipse.php.internal.core.ast.nodes.Program;
import org.eclipse.php.internal.core.ast.scanner.AstLexer;
import org.eclipse.php.internal.core.ast.scanner.PhpAstLexer5;
import org.eclipse.php.internal.core.ast.scanner.PhpAstParser5;
import org.phpaspect.weaver.internal.core.ast.scanner.PHPAspectLexer;
import org.phpaspect.weaver.internal.core.compiler.ast.parser.PHPAspectParser;
import org.phpaspect.weaver.internal.core.compiler.ast.visitor.PHPAspectVisitor;
import org.phpaspect.weaver.java_cup.runtime.Scanner;
import org.phpaspect.weaver.visitor.impl.CodeBuilder;

import com.thoughtworks.xstream.XStream;

public class ASTGenerator {

    private static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

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
            return (char)(name.charAt(0)+32)+name.substring(1);
    }
    
    public static String getXMLAstFromPHP(InputStream phpSource) throws Exception{
            return getXMLAstFromPHP(new InputStreamReader(phpSource));
    }
    
    public static String getXMLAstFromPHP(URI phpSource) throws Exception{
            Reader phpSourceReader = new FileReader(phpSource.getPath());
            return getXMLAstFromPHP(phpSourceReader);
    }

    public static Program getAstFromPHP(URI phpSource) throws Exception{
            Reader phpSourceReader = new FileReader(phpSource.getPath());
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
            return XML_DECLARATION+xstream.toXML(ast);
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
            Reader aspectReader = new FileReader(aspect.getPath());
            return getXMLAstFromPHPAspect(aspectReader);
    }

    public static Program getAstFromPHPAspect(URI aspect) throws Exception{
            Reader aspectReader = new FileReader(aspect.getPath());
            return getAstFromPHPAspect(aspectReader);
    }
    
    public static String getXMLAstFromPHPAspect(File aspect) throws Exception{
            Reader phpSourceReader = new FileReader(aspect.getPath());
            return getXMLAstFromPHPAspect(phpSourceReader);
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
            return XML_DECLARATION+xstream.toXML(ast);
    }
    
    public static Program getAstFromPHPAspect(Reader aspect) throws Exception{
            Scanner lexer = (Scanner) new PHPAspectLexer(aspect);
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
            return XML_DECLARATION+xstream.toXML(ast);
    }
    
    public static String getPHPFromAst(Program ast){
            CodeBuilder unparser = new CodeBuilder();
            unparser.visit(ast);
            String phpSource = unparser.buffer.toString();
            return phpSource;
    }
    
    public static String getPHPFromAst(String xml){
            return getPHPFromAst(getAstFromXML(xml));
    }
    
    public static String getPHPFromAst(Reader xml){
            return getPHPFromAst(getAstFromXML(xml));
    }
    
    public static String getPHPFromAst(InputStream xml){
            return getPHPFromAst(getAstFromXML(xml));
    }
    
    public static void printPHPFromAst(String filename, Program ast){
            try {
                    Writer outFile = new FileWriter(filename, false);
                    outFile.write(getPHPFromAst(ast));
                    outFile.close();
            }catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
}