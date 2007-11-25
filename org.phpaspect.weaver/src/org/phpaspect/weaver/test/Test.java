package org.phpaspect.weaver.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		File file;
		try {
			file = new File(new URI("../XSLT/toClass.xsl"));
			if(file.canRead()){
				System.out.println("loool");
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		Processor p = new Processor(true);
//		XsltCompiler xslt = p.newXsltCompiler();
//
//		try {
//			XsltTransformer transformer =
//				p.newXsltCompiler().compile(new StreamSource("xslt/toWrite.xsl")).load();
//			transformer.setSource(new StreamSource("xslt"));
//			transformer.setDestination((Destination) new StreamSource("xslt"));
//			transformer.transform();
//		} catch (SaxonApiException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		UnparseVisitor unparseVisitor = new UnparseVisitor();
//		unparseVisitor.visit(newAst);
//		System.out.println(UnparseVisitor.buffer);
	}
	
	private static String getNodeName(String className){
		String[] str = className.split("\\.");
		return str[str.length-1];
	}
}