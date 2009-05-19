package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.php.internal.core.compiler.ast.parser.PHPSourceParserFactory;
import org.phpaspect.apdt.internal.core.parser.antlr.AnnotationVisitor;
import org.phpaspect.apdt.internal.core.visitor.AspectVisitor;

public class AnnotationBuildParticipant implements IBuildParticipant {

	public AnnotationBuildParticipant(IScriptProject project) {}

	public void build(IBuildContext context) throws CoreException {
		char[] fileName = context.getFile().getName().toCharArray();
		IProblemReporter reporter = context.getProblemReporter();
		PHPSourceParserFactory parser = new PHPSourceParserFactory();
		ModuleDeclaration module = parser.parse(fileName, context.getContents(), new NullProblemReporter());
		AspectVisitor visitor = new AspectVisitor(context);
		try {
			module.traverse(visitor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
