package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.phpaspect.core.weaver.AbstractPointcut;
import org.phpaspect.core.weaver.JoinPoint;
import org.phpaspect.core.weaver.Pointcut;

public class StaticMethodInvocationPredicate extends AbstractPointcut {

    public StaticMethodInvocationPredicate(String id, String type, String method) {
        this(id, type, method, false);
    }
    
    public StaticMethodInvocationPredicate(String id, String type, String method, boolean subType) {
        super(id);
    }

    @Override
    public Pointcut clone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean match(AST ast, JoinPoint jp) {
        // TODO Auto-generated method stub
        return false;
    }

}
