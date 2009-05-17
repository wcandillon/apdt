package org.phpaspect.apdt.internal.core.parser.antlr;

import java.util.Stack;

import org.phpaspect.apdt.internal.core.pointcuts.*;
import org.phpaspect.core.weaver.Pointcut;

public class AnnotationVisitor implements NodeVisitor{

    private String id;
    private Pointcut pointcut;
    private Stack<Pointcut> stack = new Stack<Pointcut>();
    
    public AnnotationVisitor(String id)
    {
        this.id = id;
    }
    
    public Pointcut getPointcut()
    {
        return pointcut;
    }
    
    public void beginVisit(PHPAspectCommonTree node) {
        int type = node.getType();
    }

    public void endVisit(PHPAspectCommonTree node) {
        int kind = node.getType();
        switch(kind)
        {
            case PHPAspectParser.CALL:
                assert id != null;
                String type = node.getChild(0).toString();
                String method = node.getChild(2).toString();
                if(node.getChild(1).toString().equals("->"))
                {
                    pointcut = new MethodInvocationPredicate(id, type, method);
                } else {
                    pointcut = new StaticMethodInvocationPredicate(id, type, method);
                }
            break;
            case PHPAspectParser.PARENTHESE:
                assert pointcut != null;
                pointcut = new ParenthesisPointcut(pointcut);
            break;
            case PHPAspectParser.NOT:
                assert pointcut != null;
                pointcut = new NotPointcut(pointcut);
            break;
            case PHPAspectParser.AND:
            {
                assert stack.size() == 2;
                Pointcut pt1 = stack.pop();
                Pointcut pt2 = stack.pop();
                pointcut = new AndPointcut(pt1, pt2);
            }
            break;
            case PHPAspectParser.OR:
            {
                assert stack.size() == 2;
                Pointcut pt1 = stack.pop();
                Pointcut pt2 = stack.pop();
                pointcut = new OrPointcut(pt1, pt2);
            }
            break;
        }
    }

}
