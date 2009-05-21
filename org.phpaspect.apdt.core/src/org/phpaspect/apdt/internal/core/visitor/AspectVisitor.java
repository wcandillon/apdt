package org.phpaspect.apdt.internal.core.visitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.php.internal.core.compiler.ast.nodes.*;
import org.eclipse.php.internal.core.compiler.ast.visitor.PHPASTVisitor;
import org.phpaspect.apdt.internal.core.parser.antlr.*;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectParser.annotation_return;
import org.phpaspect.core.weaver.Pointcut;

public class AspectVisitor extends PHPASTVisitor {

    private String aspectName;
    private List<Pointcut> pointcuts = new LinkedList<Pointcut>();
    private char[] content;
	private IBuildContext context = null;
 
    
    public AspectVisitor(IBuildContext context) {
    	this(context.getContents());
    	this.context = context;
	}
    
    public AspectVisitor(char[] content)
    {
    	this.content = content;
    }
    
    public List<Pointcut> getPointcuts()
    {
    	return pointcuts;
    }
    
    public List<Pointcut> getPointcuts(String id, String comment, int sourceStart)
    {
        List<Pointcut> pointcuts = new LinkedList<Pointcut>();
        BufferedReader buffer = new BufferedReader(new StringReader(comment));
        try {
            String line;
            while((line = buffer.readLine()) != null)
            {
            	sourceStart += line.toCharArray().length;
                int start = line.indexOf('@');
                int end = line.lastIndexOf(')');
                if((start == -1 || end == -1)) continue;
                if(!line.substring(start).startsWith("@Before(") &&
                		!line.substring(start).startsWith("@Around(") &&
                		!line.substring(start).startsWith("@After(")) continue;
                String annotation = line.substring(start, end+1);
                CharStream content = new ANTLRStringStream(annotation); 
            	int s = sourceStart-line.toCharArray().length+line.indexOf('@');
                PHPAspectLexer lexer = new PHPAspectLexer(content);
                lexer.setContext(context, s);
                PHPAspectParser parser = new PHPAspectParser(new CommonTokenStream(lexer));
                parser.setContext(context, s);
                parser.setTreeAdaptor(new PHPAspectCommonTreeAdaptator());
                try {
                     annotation_return retValue = parser.annotation();
                     PHPAspectCommonTree tree = (PHPAspectCommonTree)retValue.getTree();
                     AnnotationVisitor visitor = new AnnotationVisitor(id);
                     tree.accept(visitor);
                     pointcuts.add(visitor.getPointcut());
                } catch (RecognitionException e1) {
                	e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pointcuts;
    }
    
    @Override
    public boolean visit(ClassDeclaration classDeclaration) {
        aspectName = classDeclaration.getName().toString();
        return true;
    }

    @Override
    public boolean visit(PHPMethodDeclaration methodDeclaration) {
        if(aspectName != null)
        {
            PHPDocBlock comment = methodDeclaration.getPHPDoc();
            if(comment != null)
            {
            	int start = comment.sourceStart();
            	int end = comment.sourceEnd();
                String docBlock = String.valueOf(content).substring(start, end);
                String id = aspectName+":"+methodDeclaration.getName();
                List<Pointcut> pointcuts = getPointcuts(id, docBlock, start);
                this.pointcuts.addAll(pointcuts);
            }
        }
        return false;
    }

	@Override
    public boolean endvisit(ClassDeclaration classDeclaration)
    {
        aspectName = null;
        return true;
    }
}
