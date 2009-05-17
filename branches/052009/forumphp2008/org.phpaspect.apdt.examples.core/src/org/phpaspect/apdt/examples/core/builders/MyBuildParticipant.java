package org.phpaspect.apdt.examples.core.builders;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.php.internal.core.compiler.ast.parser.PHPSourceParserFactory;

public class MyBuildParticipant implements IBuildParticipant {

    public MyBuildParticipant(IScriptProject project) {
        //Do nothing...
    }

    public void build(IBuildContext context) throws CoreException {
        char[] filename = context.getFile().getName().toCharArray();
        ISourceParser parser = new PHPSourceParserFactory();
        ModuleDeclaration module = parser.parse(filename, context.getContents(), context.getProblemReporter());
        ASTVisitor visitor1 = new NoEval(context);
        ASTVisitor visitor2 = new XSSProtection(context);
        try {
            module.traverse(visitor1);
            module.traverse(visitor2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
