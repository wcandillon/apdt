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
		if(!super.endVisit(s)){
			return false;
		}
		if(s.getClass().equals(AspectDeclaration.class)){
			return envisit((AspectDeclaration) s);
		}
		if(s.getClass().equals(AspectAnnotation.class)){
			return envisit((AspectAnnotation) s);
		}
		if(s.getClass().equals(AspectAnnotations.class)){
			return envisit((AspectAnnotations) s);
		}
		if(s.getClass().equals(AspectCodeAdviceDeclaration.class)){
			return envisit((AspectCodeAdviceDeclaration) s);
		}
		if(s.getClass().equals(AspectConstructionJoinpoint.class)){
			return envisit((AspectConstructionJoinpoint) s);
		}
		if(s.getClass().equals(AspectInterTypeDeclaration.class)){
			return envisit((AspectInterTypeDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeFieldDeclaration.class)){
			return envisit((AspectInterTypeFieldDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeMethodDeclaration.class)){
			return envisit((AspectInterTypeMethodDeclaration) s);
		}
		if(s.getClass().equals(AspectInterTypeParentDeclaration.class)){
			return envisit((AspectInterTypeParentDeclaration) s);
		}
		if(s.getClass().equals(AspectPointcutDeclaration.class)){
			return envisit((AspectPointcutDeclaration) s);
		}
		if(s.getClass().equals(AspectPointcutParameter.class)){
			return envisit((AspectPointcutParameter) s);
		}
		if(s.getClass().equals(AspectPointcutReference.class)){
			return envisit((AspectPointcutReference) s);
		}
		return true;
	}
	
	public boolean visit(ASTNode s) throw Exception{
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