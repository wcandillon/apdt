package org.phpaspect.apdt.internal.core.compiler.ast.parser;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.php.internal.core.PHPCoreConstants;
import org.eclipse.php.internal.core.compiler.ast.parser.AbstractPHPSourceParser;
import org.eclipse.php.internal.core.project.properties.handlers.PhpVersionProjectPropertyHandler;

public class PHPAspectSourceParserFactory extends AbstractSourceParser implements ISourceParserFactory, ISourceParser {

	public ISourceParser createSourceParser() {
		return this;
	}

	public ModuleDeclaration parse(char[] fileName, char[] source, IProblemReporter reporter) {
		AbstractPHPSourceParser parser = createParser(new String(fileName));
		return parser.parse(fileName, source, reporter);
	}

	protected AbstractPHPSourceParser createParser(String fileName) {
		return new PHPAspectSourceParser(fileName);
	}
}
