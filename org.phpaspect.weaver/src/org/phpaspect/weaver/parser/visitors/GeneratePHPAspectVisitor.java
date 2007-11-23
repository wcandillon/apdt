package org.phpaspect.weaver.parser.visitors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneratePHPAspectVisitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String NODES_PATH = args[0];
		final String VISITOR_PATH = args[1];
		
		StringBuffer generatedCode = new StringBuffer();
		generatedCode.append("package org.phpaspect.weaver.parser.visitors;\n\n");
		generatedCode.append("import org.eclipse.php.internal.core.ast.visitor.Visitor;\n");
		generatedCode.append("import org.phpaspect.weaver.parser.nodes.*;\n\n");
		generatedCode.append("public interface PHPAspectVisitor extends Visitor {\n");
		
		File[] nodes = new File(NODES_PATH).listFiles();
		for(int j=0; j<nodes.length; j++){
			if(nodes[j].getName().startsWith("Aspect")){
				String className = nodes[j].getName();
				className = className.substring(0, className.length()-5);
				generatedCode.append("    public void visit("+className+" a"+className.substring(1)+");\n");
			}
		}
		
		generatedCode.append("}");
		
		try {
			FileWriter phpAspectVisitor = new FileWriter(VISITOR_PATH);
			phpAspectVisitor.write(generatedCode.toString());
			phpAspectVisitor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
