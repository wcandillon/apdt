package org.phpaspect.apdt.internal.core.parser.antlr;

public interface NodeVisitor {
    void beginVisit(PHPAspectCommonTree node);
    void endVisit(PHPAspectCommonTree node);
}
