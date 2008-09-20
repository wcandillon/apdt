package org.phpaspect.apdt.internal.core.builder;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.php.core.PHPMixinBuildVisitorExtension;
import org.phpaspect.apdt.internal.core.compiler.ast.nodes.AspectDeclaration;

public class PHPAspectMixinBuildVisitor extends PHPMixinBuildVisitorExtension {

	public boolean visitGeneral(ASTNode node) throws Exception {
		if(node instanceof AspectDeclaration){
			AspectDeclaration aspectDeclaration = (AspectDeclaration)node;
			System.err.println("aspect declaration");
		}
		System.err.println("=============================================");
		return false;
	}
}