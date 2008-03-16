package org.phpaspect.weaver.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import org.phpaspect.weaver.internal.core.compiler.ast.ASTGenerator;

public class TestXMLGeneration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File aspect = new File("Tests/Singleton.ap");
			BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
			String xml = ASTGenerator.getXMLAstFromPHPAspect(aspect);
		    writer.write(xml);
		    writer.close();
		    System.err.println("done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
