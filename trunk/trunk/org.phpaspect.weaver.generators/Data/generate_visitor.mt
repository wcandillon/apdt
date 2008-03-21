<%
metamodel http://www.eclipse.org/emf/2002/Ecore
%>
<%script type="ecore.EPackage" name="generate_visitor" file="Data/PHPAspectVisitor.java"%>
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.php.internal.core.compiler.ast.visitor.PHPASTVisitor;
import org.phpaspect.weaver.internal.core.compiler.ast.nodes.*;

public abstract class PHPAspectVisitor extends PHPASTVisitor{
	<%for (eAllContents()){%>
	public boolean endVisit(<%name%> s) throws Exception {
		endvisitGeneral(s);
		return false;
	}
	
	public boolean visit(<%name%> s) throws Exception{
		return visitGeneral(s);
	}
	<%}%>

	public boolean endVisit(ASTNode s) throws Exception{
		if(!super.endVisit(s)){
			return false;
		}
		<%for (eAllContents()){%>
		if(s.getClass().equals(<%name%>.class)){
			return envisit((<%name%>) s);
		}
		<%}%>
		return true;
	}
	
	public boolean visit(ASTNode s) throw Exception{
		if(!super.visit(s)){
			return false;
		}
		<%for (eAllContents()){%>
		if(s.getClass().equals(<%name%>.class)){
			return visit((<%name%>) s);
		}
		<%}%>
		return true;
	}
}

