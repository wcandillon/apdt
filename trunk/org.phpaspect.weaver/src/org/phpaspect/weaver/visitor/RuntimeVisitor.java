package org.phpaspect.weaver.visitor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import org.phpaspect.weaver.ast.nodes.*;
import org.phpaspect.weaver.ast.rewrite.ASTRewrite;
import org.phpaspect.weaver.ast.visitor.AbstractVisitor;

public class RuntimeVisitor extends AbstractVisitor{

	private ASTParser parser;
	private Program program;
	private AST ast;
	private ASTRewrite rewriter;
	private IDocument document = new Document();
	private String currentPHPVersion;
	private String fileName;
	
	public RuntimeVisitor(IFile file, String fileName) throws Exception {
		currentPHPVersion = "PHPAspect";
		this.fileName = fileName;
	    StringBuffer source = new StringBuffer();
	    InputStreamReader in = new InputStreamReader(file.getContents());
	    while(in.ready()){
	    	source.append((char)in.read());
	    }
	    document.set(source.toString());
		parser = ASTParser.newParser(new InputStreamReader(file.getContents()), currentPHPVersion, false, DLTKCore.createSourceModuleFrom(file));
		program = parser.createAST(new NullProgressMonitor());
		ast = program.getAST();
		rewriter = ASTRewrite.create(ast);
	}

	public String generate() {
		program.accept(this);
		Map<String, String> options = new HashMap<String, String>();
		TextEdit textEdit = rewriter.rewriteAST(document, options);
		try {
			textEdit.apply(document);
		} catch (BadLocationException e) {
			System.err.println("Couldn't weave the source code.");
			System.err.println("Reason: "+e.getMessage());
		}
		return document.get();
	}
	
	
	public void endVisit(Program program){ 
		//List of the includes for PHPAspect Runtime
		List<Statement> includes = new  LinkedList<Statement>();
		//require_once 'Runtime/Aspect.php';
		Expression aspectFile = ast.newScalar("'Runtime/Aspect.php'");
	    includes.add(ast.newExpressionStatement(ast.newInclude(aspectFile, Include.IT_REQUIRE_ONCE)));
	    //Add the includes in front of the statement list
	    List<Statement> statements = program.statements();
	    Statement firstStatement = ASTNode.copySubtree(ast, statements.get(0));
	    includes.add(firstStatement);
		Block PHPAspectRuntimeImports = ast.newBlock(includes);
	    rewriter.replace(statements.get(0), PHPAspectRuntimeImports, null);
	}
	
	public void endVisit(AspectDeclaration ap){
		//ast.newClassDeclaration(modifier, className, superClass, interfaces, body)
		ClassDeclaration classDeclaration = ast.newClassDeclaration(ap.getModifier(), ap.getName().getName(), ap.getSuperClass().getName(), ap.interfaces(), ap.getBody());
		//classDeclaration.set
		rewriter.replace(ap, classDeclaration, null);
	}	
}