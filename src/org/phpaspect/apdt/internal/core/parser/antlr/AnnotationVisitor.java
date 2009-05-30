package org.phpaspect.apdt.internal.core.parser.antlr;

import java.util.Stack;

import org.phpaspect.apdt.core.weaver.Mixin;
import org.phpaspect.apdt.core.weaver.Pointcut;
import org.phpaspect.apdt.internal.core.weaver.pointcuts.*;

public class AnnotationVisitor implements PHPAspectNodeVisitor{

    private String id;
    private Pointcut pointcut = null;
    private Mixin mixin = null;
    private Stack<Pointcut> stack = new Stack<Pointcut>();
    
    public AnnotationVisitor(String id)
    {
        this.id = '\''+id+'\'';
    }
    
    public Pointcut getPointcut()
    {
        return pointcut;
    }
    
    public boolean isPointcut()
    {
    	return pointcut != null;
    }
    
    public boolean isMixin()
    {
    	return mixin != null;
    }
    
    public void beginVisit(PHPAspectCommonTree node) {}

    public void endVisit(PHPAspectCommonTree node) {
        int kind = node.getType();
        switch(kind)
        {
        	case PHPAspectParser.ANNOTATION:
        	{
        		assert stack.size() == 1;
        		pointcut = stack.pop();
        	}
        	break;
            case PHPAspectParser.CALL:
            {
                assert id != null;
                String type = node.getChild(0).toString();
                String method = node.getChild(2).toString();
                if(node.getChild(1).toString().equals("->"))
                {
                    stack.push(new MethodInvocationPredicate(id, type, method));
                } else {
                    stack.push(new StaticMethodInvocationPredicate(id, type, method));
                }
            }
            break;
            case PHPAspectParser.NEW:
            {
            	assert id != null;
            	String type = node.getChild(0).toString();
            	stack.push(new ConstructorPredicate(id, type));
            }
            break;
            case PHPAspectParser.EXEC:
            {
            	assert id != null;
            	String type = node.getChild(0).toString();
            	String method = node.getChild(1).toString();
            	stack.push(new MethodExecutionPredicate(id, type, method));
            }
            break;
            case PHPAspectParser.FILE:
            {
            	assert id != null;
            	String file = node.getChild(0).toString();
            	stack.push(new FilePredicate(id, file));
            }
            break;
            case PHPAspectParser.WITHIN:
            {
            	String within = node.getChild(0).toString();
            	stack.push(new WithinPredicate(id, within));
            }
            case PHPAspectParser.PARENTHESE:
            {
        		assert stack.size() == 1;
                stack.push(new ParenthesisPointcut(stack.pop()));
            }
            break;
            case PHPAspectParser.NOT:
            {
        		assert stack.size() == 1;
                stack.push(new NotPointcut(stack.pop()));
            }
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
