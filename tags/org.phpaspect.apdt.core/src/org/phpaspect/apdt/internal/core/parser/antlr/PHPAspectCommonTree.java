package org.phpaspect.apdt.internal.core.parser.antlr;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class PHPAspectCommonTree extends CommonTree {

    protected int start;
    protected int stop;
    
    public PHPAspectCommonTree() {
        super();
    }

    public PHPAspectCommonTree(Token t) {
        super(t);
    }

    public void accept(PHPAspectNodeVisitor visitor) {
        visitor.beginVisit(this);
        for (int i = 0; i < getChildCount(); i++) {
            PHPAspectCommonTree child = (PHPAspectCommonTree)getChild(i);
            child.accept(visitor);
        }
        visitor.endVisit(this);
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    public void setStop(int stop) {
        this.stop = stop;
    }
    public int getStart() {
        return start;
    }
    public int getStop() {
        return stop;
    }
    
    @Override
    public PHPAspectCommonTree getChild(int i) {
        if (children == null || i >= children.size()) {
            return null;
        }
        return (PHPAspectCommonTree)children.get(i);
    }
    
    @Override
    public CommonToken getToken() {
        return (CommonToken)token;
    }
    
    public boolean isError() {
        return false;
    }
}
