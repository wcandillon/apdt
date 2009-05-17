package org.phpaspect.apdt.internal.core.parser.antlr;

import java.util.Stack;

import org.phpaspect.apdt.internal.core.pointcuts.*;
import org.phpaspect.core.weaver.Pointcut;

public class AnnotationVisitor implements NodeVisitor{

    private String id;
    private Pointcut pointcut = null;
    private Stack<Pointcut> stack = new Stack<Pointcut>();
    
    public AnnotationVisitor(String id)
    {
        this.id = '\''+id+'\'';
    }
    
    public Pointcut getPointcut()
    {
        return pointcut;
    }
    
    public void beginVisit(PHPAspectCommonTree node) {}

    public void endVisit(PHPAspectCommonTree node) {
        int kind = node.getType();
        switch(kind)
        {
        	case PHPAspectParser.ANNOTATION:
        		assert stack.size() == 1;
        		pointcut = stack.pop();
        	break;
            case PHPAspectParser.CALL:
                assert id != null;
                String type = node.getChild(0).toString();
                String method = node.getChild(2).toString();
                if(node.getChild(1).toString().equals("->"))
                {
                    stack.push(new MethodInvocationPredicate(id, type, method));
                } else {
                    stack.push(new StaticMethodInvocationPredicate(id, type, method));
                }
            break;
            case PHPAspectParser.PARENTHESE:
        		assert stack.size() == 1;
                stack.push(new ParenthesisPointcut(stack.pop()));
            break;
            case PHPAspectParser.NOT:
        		assert stack.size() == 1;
                stack.push(new NotPointcut(stack.pop()));
            break;
            case PHPAspectParser.AND:
            {
                assert stack.size() == 2;
                Pointcut pt1 = stack.pop();
                Pointcut pt2 = stack.pop();
                stack.push(new AndPointcut(pt1, pt2));
            }
            break;
            case PHPAspectParser.OR:
            {
                assert stack.size() == 2;
                Pointcut pt1 = stack.pop();
                Pointcut pt2 = stack.pop();
                stack.push(new OrPointcut(pt1, pt2));
            }
            break;
        }
    }

}
