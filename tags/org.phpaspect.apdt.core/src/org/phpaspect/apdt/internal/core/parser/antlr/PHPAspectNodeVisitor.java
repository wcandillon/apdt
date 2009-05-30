package org.phpaspect.apdt.internal.core.parser.antlr;

public interface PHPAspectNodeVisitor {
    void beginVisit(PHPAspectCommonTree node);
    void endVisit(PHPAspectCommonTree node);
}
