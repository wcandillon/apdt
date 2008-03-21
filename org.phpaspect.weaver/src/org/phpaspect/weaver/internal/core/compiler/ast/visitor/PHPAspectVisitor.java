package org.phpaspect.weaver.internal.core.compiler.ast.visitor;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.php.internal.core.compiler.ast.visitor.PHPASTVisitor;
import org.phpaspect.weaver.internal.core.compiler.ast.nodes.*;

public abstract class PHPAspectVisitor extends PHPASTVisitor{
	public boolean endVisit(AspectDeclaration s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectDeclaration s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectAnnotation s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectAnnotation s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectAnnotations s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectAnnotations s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectCodeAdviceDeclaration s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectCodeAdviceDeclaration s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectConstructionJoinpoint s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectConstructionJoinpoint s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectInterTypeDeclaration s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectInterTypeDeclaration s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectInterTypeFieldDeclaration s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectInterTypeFieldDeclaration s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectInterTypeMethodDeclaration s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectInterTypeMethodDeclaration s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectInterTypeParentDeclaration s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectInterTypeParentDeclaration s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectPointcutDeclaration s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectPointcutDeclaration s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectPointcutParameter s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectPointcutParameter s) throws Exception{
		return visitGeneral(s);
	}
	public boolean endVisit(AspectPointcutReference s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(AspectPointcutReference s) throws Exception{
		return visitGeneral(s);
	}

	public boolean endVisit(ASTNode s) throws Exception{
		if(!super.endvisit(s)){
			return false;
		}
		if(s.getClass().equals(AspectDeclaration.class)){
			return endvisit((AspectDeclaration) s);
		}
		if(s.getClass().equals(AspectAnnotation.class)){
			return endvisit((AspectAnnotation) s);
		}
		if(s.getClass().equals(AspectAnnotations.class)){
			return endvisit((AspectAnnotations) s);
		}
		if(s.getClass().equals(AspectCodeAdviceDeclaration.class)){
			return endvisit((AspectCodeAdviceDeclaration) s);
		}
		if(s.getClass().equals(AspectConstructionJoinpoint.class)){
			return endvisit((AspectConstructionJoinpoint) s);
		}
		if(s.getClass().equals(AspectInterTypeDeclaration.class)){
			return endvisit((AspectInterTypeDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeFieldDeclaration.class)){
			return endvisit((AspectInterTypeFieldDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeMethodDeclaration.class)){
			return endvisit((AspectInterTypeMethodDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeParentDeclaration.class)){
			return endvisit((AspectInterTypeParentDeclaration) s);
		}
		if(s.getClass().equals(AspectPointcutDeclaration.class)){
			return endvisit((AspectPointcutDeclaration) s);
		}
		if(s.getClass().equals(AspectPointcutParameter.class)){
			return endvisit((AspectPointcutParameter) s);
		}
		if(s.getClass().equals(AspectPointcutReference.class)){
			return endvisit((AspectPointcutReference) s);
		}
		return true;
	}
	
	public boolean visit(ASTNode s) throws Exception{
		if(!super.visit(s)){
			return false;
		}
		if(s.getClass().equals(AspectDeclaration.class)){
			return visit((AspectDeclaration) s);
		}
		if(s.getClass().equals(AspectAnnotation.class)){
			return visit((AspectAnnotation) s);
		}
		if(s.getClass().equals(AspectAnnotations.class)){
			return visit((AspectAnnotations) s);
		}
		if(s.getClass().equals(AspectCodeAdviceDeclaration.class)){
			return visit((AspectCodeAdviceDeclaration) s);
		}
		if(s.getClass().equals(AspectConstructionJoinpoint.class)){
			return visit((AspectConstructionJoinpoint) s);
		}
		if(s.getClass().equals(AspectInterTypeDeclaration.class)){
			return visit((AspectInterTypeDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeFieldDeclaration.class)){
			return visit((AspectInterTypeFieldDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeMethodDeclaration.class)){
			return visit((AspectInterTypeMethodDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeParentDeclaration.class)){
			return visit((AspectInterTypeParentDeclaration) s);
		}
		if(s.getClass().equals(AspectPointcutDeclaration.class)){
			return visit((AspectPointcutDeclaration) s);
		}
		if(s.getClass().equals(AspectPointcutParameter.class)){
			return visit((AspectPointcutParameter) s);
		}
		if(s.getClass().equals(AspectPointcutReference.class)){
			return visit((AspectPointcutReference) s);
		}
		return true;
	}
}