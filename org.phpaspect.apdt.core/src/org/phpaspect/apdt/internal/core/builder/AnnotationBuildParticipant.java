package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.internal.core.builder.BuildProblemReporter;
import org.eclipse.dltk.internal.core.util.ResourceSourceModule;
import org.eclipse.php.internal.core.compiler.ast.parser.PHPSourceParserFactory;
import org.phpaspect.apdt.internal.core.visitor.AspectVisitor;

public class AnnotationBuildParticipant implements IBuildParticipant {

	public AnnotationBuildParticipant(IScriptProject project) {}

	public void build(IBuildContext context) throws CoreException {
		char[] fileName = context.getFile().getName().toCharArray();
		PHPSourceParserFactory parser = new PHPSourceParserFactory();
		IFile file = context.getFile();
		ISourceModule moduleSource = new ResourceSourceModule(file, file.getLocationURI());
		ModuleDeclaration module = (ModuleDeclaration) SourceParserUtil.parse(moduleSource, new BuildProblemReporter(file));
		AspectVisitor visitor = new AspectVisitor(context);
		try {
			module.traverse(visitor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
