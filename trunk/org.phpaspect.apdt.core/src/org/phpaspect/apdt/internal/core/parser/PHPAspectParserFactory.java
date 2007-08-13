package org.phpaspect.apdt.internal.core.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import org.phpaspect.apdt.internal.core.parser.PHPAspectLexer;
import org.phpaspect.apdt.internal.core.parser.PHPAspectParser;

public class PHPAspectParserFactory {

	private static PHPAspectLexer phpAspectLexer = new PHPAspectLexer(new StringReader(""));
	private static PHPAspectParser phpAspectParser = new PHPAspectParser();
	
	public static PHPAspectParser create(IFile file) throws CoreException, IOException{
		return getParser(new InputStreamReader(file.getContents()));
	}

	public static PHPAspectParser create(File file) throws IOException{
		return getParser(new FileReader(file));
	}

	public static PHPAspectParser create(String fileName) throws IOException{
		return create(new File(fileName));
	}
	
	public static PHPAspectParser getParser(Reader reader) throws IOException{
		phpAspectLexer.yyreset(reader);
		phpAspectLexer.resetCommentList();
		phpAspectParser.setScanner(phpAspectLexer);
		return phpAspectParser;
	}
}