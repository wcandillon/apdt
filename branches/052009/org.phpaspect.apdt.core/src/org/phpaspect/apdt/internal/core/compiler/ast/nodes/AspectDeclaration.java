package org.phpaspect.apdt.internal.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.references.TypeReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.php.internal.core.compiler.ast.nodes.ClassDeclaration;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPDocBlock;

public class AspectDeclaration extends ClassDeclaration {
	
	private boolean persistent;

	public AspectDeclaration(int start, int end, int nameStart, int nameEnd,
			int modifier, String className, TypeReference superClass,
			List<TypeReference> interfaces, boolean persistent, Block body, PHPDocBlock phpDoc) {
		super(start, end, nameStart, nameEnd, modifier, className, superClass,
				interfaces, body, phpDoc);
		this.setPersistent(persistent);
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	public boolean isPersistent() {
		return persistent;
	}

}
