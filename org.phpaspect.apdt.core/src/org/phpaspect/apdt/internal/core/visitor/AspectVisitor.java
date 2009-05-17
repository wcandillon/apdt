package org.phpaspect.apdt.internal.core.visitor;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.php.internal.core.compiler.ast.nodes.*;
import org.eclipse.php.internal.core.compiler.ast.visitor.PHPASTVisitor;
import org.phpaspect.apdt.internal.core.annotations.AnnotationParser;
import org.phpaspect.core.weaver.Pointcut;

public class AspectVisitor extends PHPASTVisitor {

    private String aspectName;
    private List<Pointcut> pointcuts = new LinkedList<Pointcut>();
    private char[] content;
    
    public AspectVisitor(char[] content) {
        this.content = content;
    }
    
    public List<Pointcut> getPointcuts()
    {
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
                List<Pointcut> pointcuts = AnnotationParser.getPointcuts(id, docBlock);
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
