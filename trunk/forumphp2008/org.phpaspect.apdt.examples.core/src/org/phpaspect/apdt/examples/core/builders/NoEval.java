package org.phpaspect.apdt.examples.core.builders;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.php.core.PHPMixinBuildVisitorExtension;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPCallExpression;

public class NoEval extends ASTVisitor {

    private IBuildContext context;
    
    public NoEval(IBuildContext context) {
        this.context = context;
    }

    public void endvisitGeneral(ASTNode node) throws Exception{
        if(node instanceof PHPCallExpression){
            endvisit((PHPCallExpression) node);
        }
    }
    
    public void endvisit(PHPCallExpression node){
        String name = node.getName();
        if(name.equals("eval")){
            String filename = context.getFile().getName();
            String message = "References to eval are forbidden.";
            IProblem problem = new DefaultProblem(filename, message, IProblem.ForbiddenReference, new String[0], ProblemSeverities.Error,
                                   node.sourceStart(), node.sourceEnd(), 0);
            context.getProblemReporter().reportProblem(problem);
        }
    }
}
