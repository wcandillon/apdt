package org.phpaspect.apdt.internal.core.weaver.pointcuts;

import org.eclipse.php.internal.core.ast.nodes.AST;
import org.phpaspect.apdt.core.weaver.AbstractPointcut;
import org.phpaspect.apdt.core.weaver.Joinpoint;
import org.phpaspect.apdt.core.weaver.Pointcut;

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
    public boolean match(AST ast, Joinpoint jp) {
        // TODO Auto-generated method stub
        return false;
    }

}
