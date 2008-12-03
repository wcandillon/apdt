package org.phpaspect.apdt.examples.core.builders;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPCallExpression;

public class XSSProtection extends ASTVisitor {

    private static List<String> variables = new LinkedList<String>();
    private static String filter = "pecl_filter";
    private IBuildContext context;
    
    static{
        //Sensitive filters
        variables.add("$_POST");
        variables.add("$_GET");
        variables.add("$_SERVER");
        variables.add("$_ENV");
    }
    
    public XSSProtection(IBuildContext context) {
        this.context = context;
    }
    
    public boolean visitGeneral(ASTNode node) throws Exception {
        if(node instanceof PHPCallExpression){
            PHPCallExpression expr = (PHPCallExpression)node;
            System.err.println(expr.getName());
            if(expr.getName().equals(filter)){
                return false;
            }
        }
        return true;
    }
    
    public void endvisitGeneral(ASTNode node) throws Exception {
        if(node instanceof VariableReference){
            endvisit((VariableReference) node);
        }
    }
    
    public void endvisit(VariableReference node){
        if(variables.contains(node.getName())){
            String filename = context.getFile().getName();
            String message = "Unsafe use of "+node.getName();
            IProblem problem = new DefaultProblem(filename, message, IProblem.ForbiddenReference, new String[0], ProblemSeverities.Error,
                                   node.sourceStart(), node.sourceEnd(), 0);
            context.getProblemReporter().reportProblem(problem);
            
        }
    }

}
