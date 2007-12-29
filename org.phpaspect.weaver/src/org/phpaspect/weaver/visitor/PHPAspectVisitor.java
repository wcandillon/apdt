package org.phpaspect.weaver.visitor;

import org.eclipse.php.internal.core.ast.visitor.Visitor;
import org.phpaspect.weaver.parser.nodes.*;

public interface PHPAspectVisitor extends Visitor {
    public void visit(AspectInterTypeDeclaration aspectInterTypeDeclaration);
    public void visit(AspectDeclaration aspectDeclaration);
    public void visit(AspectPointcutDeclaration aspectPointcutDeclaration);
    public void visit(AspectInterTypeFieldsDeclaration aspectInterTypeFieldsDeclaration);
    public void visit(AspectInterTypeFieldDeclaration aspectInterTypeFieldDeclaration);
    public void visit(AspectAnnotations aspectAnnotations);
    public void visit(AspectPointcutParameter aspectPointcutParameter);
    public void visit(AspectAnnotation aspectAnnotation);
    public void visit(AspectPointcutReference aspectPointcutReference);
    public void visit(AspectCodeAdviceDeclaration aspectCodeAdviceDeclaration);
    public void visit(AspectInterTypeMethodDeclaration aspectInterTypeMethodDeclaration);
    public void visit(AspectConstructionJoinpoint aspectConstructionJoinpoint);
    public void visit(AspectInterTypeParentDeclaration aspectInterTypeParentDeclaration);
}