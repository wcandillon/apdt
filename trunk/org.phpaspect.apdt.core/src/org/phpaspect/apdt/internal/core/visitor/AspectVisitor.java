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
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.php.internal.core.compiler.ast.nodes.*;
import org.eclipse.php.internal.core.compiler.ast.visitor.PHPASTVisitor;
import org.phpaspect.apdt.internal.core.parser.antlr.AnnotationVisitor;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectCommonTree;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectCommonTreeAdaptator;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectLexer;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectParser;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectParser.annotation_return;
import org.phpaspect.core.weaver.Pointcut;

public class AspectVisitor extends PHPASTVisitor {

    private String aspectName;
    private List<Pointcut> pointcuts = new LinkedList<Pointcut>();
    private char[] content;
	private IBuildContext context;

    
    public AspectVisitor(char[] content) {
    	this(content, null);
    }
    
    public AspectVisitor(char[] content, IBuildContext context)
    {
    	this.content = content;
    	this.context = context;
    }
    
    public List<Pointcut> getPointcuts()
    {
        return pointcuts;
    }
    
    public List<Pointcut> getPointcuts(String id, String comment)
    {
        List<Pointcut> pointcuts = new LinkedList<Pointcut>();
        BufferedReader buffer = new BufferedReader(new StringReader(comment));
        try {
            String line;
            while((line = buffer.readLine()) != null)
            {
                int start = line.indexOf('@');
                int end = line.lastIndexOf(')');
                if(start == -1 || end == -1) continue;
                String annotation = line.substring(start, end+1);
                System.err.println(annotation);
                CharStream content = new ANTLRStringStream(annotation);
                PHPAspectLexer lexer = new PHPAspectLexer(content);
                PHPAspectParser parser = new PHPAspectParser(new CommonTokenStream(lexer));
                parser.setTreeAdaptor(new PHPAspectCommonTreeAdaptator());
                try {
                     annotation_return retValue = parser.annotation();
                     PHPAspectCommonTree tree = (PHPAspectCommonTree)retValue.getTree();
                     AnnotationVisitor visitor = new AnnotationVisitor(id);
                     tree.accept(visitor);
                     pointcuts.add(visitor.getPointcut());
                } catch (RecognitionException e1) {
                	if(context != null)
                	{
                		String filename = context.getFile().getName();
                		String message = parser.getErrorMessage(e1, parser.getTokenNames());
                		IProblem problem = new DefaultProblem(filename, message, IProblem.Syntax,
                				new String[1], ProblemSeverities.Error, start, end, 1);
                		context.getProblemReporter().reportProblem(problem);
                	} else {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                	}
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
                String docBlock = String.valueOf(content).substring(
                                                            comment.sourceStart(),
                                                            comment.sourceEnd()
                                                            );
                String id = aspectName+":"+methodDeclaration.getName();
                List<Pointcut> pointcuts = getPointcuts(id, docBlock);
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
