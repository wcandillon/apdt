package org.phpaspect.weaver.parser.nodes;

import java.util.List;

import org.eclipse.php.internal.core.ast.nodes.Identifier;
import org.eclipse.php.internal.core.ast.nodes.Statement;
import org.eclipse.php.internal.core.ast.visitor.Visitor;

public class AspectInterTypeParentDeclaration extends Statement {
	
	private Identifier className;
	private Identifier superClass;
	private List interfaces;
	
	public AspectInterTypeParentDeclaration(int start, int end, Identifier className,
												Identifier superClass, List interfaces){
		super(start, end);
		this.className = className;
		this.superClass = superClass;
		this.interfaces = interfaces;
	}

	public Identifier getClassName(){
		return className;
	}
	
	public Identifier getSuperClass(){
		return superClass;
	}
	
	public List getInterfaces(){
		return interfaces;
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void childrenAccept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void toString(StringBuffer buffer, String tab) {
		// TODO Auto-generated method stub

	}

	public void traverseBottomUp(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public void traverseTopDown(Visitor visitor) {
		// TODO Auto-generated method stub

	}

}
