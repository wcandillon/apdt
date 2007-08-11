package org.phpaspect.apdt.internal.core.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class PHPAspectParserFactory {
	
	public static PHPAspectParser create(IFile file) throws CoreException{
		PHPAspectLexer scanner = new PHPAspectLexer(file.getContents());
		return new PHPAspectParser(scanner);
	}
	
	public static PHPAspectParser create(File file) throws FileNotFoundException{
		Reader reader = new FileReader(file);
		PHPAspectLexer scanner = new PHPAspectLexer(reader);
		return new PHPAspectParser(scanner);
	}
	
	public static PHPAspectParser create(String fileName) throws FileNotFoundException{
		return create(new File(fileName));
	}
}