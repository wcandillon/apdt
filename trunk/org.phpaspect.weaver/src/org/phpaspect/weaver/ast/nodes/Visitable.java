/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zend and IBM - Initial implementation
 *******************************************************************************/
package org.phpaspect.weaver.ast.nodes;

import org.phpaspect.weaver.ast.visitor.Visitor;

/**
 * Supporting the visitor pattern to the Abstract Syntax Tree (AST) nodes
 */
public interface Visitable {

	public static final String TAB = "\t"; //$NON-NLS-1$

	/**
	 * Visit only the actual node
	 * @param visitor
	 */
	public void accept(Visitor visitor);

	/**
	 * Visit the children of the actual node (without visiting the actual)
	 * @param visitor
	 */
	public void childrenAccept(Visitor visitor);

	/**
	 * Visit the actual node then go down to visit the children nodes
	 * @param visitor
	 */
	public void traverseTopDown(Visitor visitor);

	/**
	 * Visit the children nodes then go up to the actual node
	 * @param visitor
	 */
	public void traverseBottomUp(Visitor visitor);

	/**
	 * Buffers the actual node information
	 * @param buffer - buffer to write the content to
	 * @param tab - indentation
	 */
	public void toString(StringBuffer buffer, String tab);
}
