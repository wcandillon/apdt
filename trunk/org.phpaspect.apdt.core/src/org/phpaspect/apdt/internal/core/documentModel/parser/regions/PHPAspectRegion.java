package org.phpaspect.apdt.internal.core.documentModel.parser.regions;

import java.io.IOException;
import java.io.Reader;
import java.util.ListIterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.php.internal.core.documentModel.parser.PhpLexer;
import org.eclipse.php.internal.core.documentModel.parser.Scanner.LexerState;
import org.phpaspect.apdt.internal.core.APDTCorePlugin;
import org.phpaspect.apdt.internal.core.documentModel.parser.regions.PHPAspectRegion;
import org.eclipse.php.internal.core.documentModel.parser.regions.IPhpScriptRegion;
import org.eclipse.php.internal.core.documentModel.parser.regions.PhpTokenContainer;
import org.eclipse.php.internal.core.project.properties.handlers.UseAspTagsHandler;
import org.eclipse.wst.sse.core.internal.parser.ForeignRegion;
import org.eclipse.wst.sse.core.internal.provisional.events.StructuredDocumentEvent;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xml.core.internal.Logger;

import org.phpaspect.apdt.internal.core.documentModel.parser.PHPAspectLexer;

public class PHPAspectRegion extends ForeignRegion implements IPhpScriptRegion{

	private static final String PHP_SCRIPT = "PHP Script";
	private final PhpTokenContainer tokensContaier = new PhpTokenContainer();
	private final IProject project;

	// true when the last reparse action is full reparse
	public boolean isFullReparsed;

	public PHPAspectRegion(String newContext, int startOffset, IProject project, PhpLexer phpLexer) {
		super(newContext, startOffset, 0, 0, PHP_SCRIPT);

		this.project = project;
		completeReparse(phpLexer);
	}

	/**
	 * returns the php token type in the given offset 
	 * @param offset
	 * @throws BadLocationException 
	 */
	public final String getPhpTokenType(int offset) throws BadLocationException {
		final ITextRegion tokenForOffset = getPhpToken(offset);
		return tokenForOffset == null ? null : tokenForOffset.getType();
	}

	/**
	 * returns the php token in the given offset
	 * @param offset
	 * @throws BadLocationException 
	 */
	public final ITextRegion getPhpToken(int offset) throws BadLocationException {
		return tokensContaier.getToken(offset);
	}

	/**
	 * returns the php token in the given offset
	 * @param offset
	 * @throws BadLocationException 
	 */
	public final ITextRegion[] getPhpTokens(int offset, int length) throws BadLocationException {
		return tokensContaier.getTokens(offset, length);
	}

	/**
	 * @param offset
	 * @return the internal partition of the given offset
	 * @throws BadLocationException
	 */
	public String getPartition(int offset) throws BadLocationException {
		return tokensContaier.getPartitionType(offset);
	}

	/**
	 *  
	 * @param offset
	 * @return true if the given offset is in line comment
	 * @throws BadLocationException
	 */
	public boolean isLineComment(int offset) throws BadLocationException {
		final LexerState lexState = tokensContaier.getState(offset);
		return lexState != null && lexState.getTopState() == PhpLexer.ST_PHP_LINE_COMMENT;
	}

	@SuppressWarnings("restriction")
	public StructuredDocumentEvent updateRegion(Object requester, IStructuredDocumentRegion flatnode, String changes, int requestStart, int lengthToReplace) {
		isFullReparsed = true;
		try {
			final int offset = requestStart - flatnode.getStartOffset() - getStart();

			// support the <?php case
			if (offset < 4) {
				return null;
			}
			// checks for odd quotes
			final String deletedText = lengthToReplace == 0 ? "" : flatnode.getParentDocument().get(requestStart, lengthToReplace);
			final int length = changes.length();
			if (startQuoted(deletedText) || startQuoted(changes)) {
				return null;
			}

			// get the region to re-parse
			final ITextRegion tokenStart = tokensContaier.getToken(offset == 0 ? 0 : offset - 1);
			final int oldEndOffset = offset + lengthToReplace;
			final ITextRegion tokenEnd = tokensContaier.getToken(oldEndOffset);
			int newTokenOffset = tokenStart.getStart();
			
			if (isHereDoc(tokenStart)) {
				return null;
			}

			// get start and end states
			final LexerState startState = tokensContaier.getState(newTokenOffset);
			final LexerState endState = tokensContaier.getState(tokenEnd.getEnd() + 1);

			final PhpTokenContainer newContainer = new PhpTokenContainer();
			final PhpLexer phpLexer = getPhpLexer(project, new DocumentReader(flatnode, changes, requestStart, lengthToReplace, newTokenOffset), startState);

			Object state = startState;
			try {
				String yylex = phpLexer.getNextToken();
				int yylength;
				final int toOffset = offset + length;
				while (yylex != null && newTokenOffset <= toOffset && yylex != PHPAspectRegionTypes.PHP_CLOSETAG) {
					yylength = phpLexer.getLength();
					newContainer.addLast(yylex, newTokenOffset, yylength, yylength, state);
					newTokenOffset += yylength;
					state = phpLexer.createLexicalStateMemento();
					yylex = phpLexer.getNextToken();
				}
				if (yylex == PHPAspectRegionTypes.WHITESPACE) {
					yylength = phpLexer.getLength();
					newContainer.adjustWhitespace(yylex, newTokenOffset, yylength, yylength, state);
				}
			} catch (IOException e) {
				Logger.logException(e);
			}

			// if the fast reparser couldn't lex - - reparse all
			if (newContainer.isEmpty()) {
				return null;
			}

			// if the two streams end with the same lexer sate - 
			// 1. replace the regions
			// 2. adjust next regions start location
			// 3. update state changes
			final int size = length - lengthToReplace;
			final int end = newContainer.getPhpTokens()[newContainer.getPhpTokens().length-1].getEnd();
			//final int end = newContainer.getLastToken().getEnd();

			if (!state.equals(endState) || tokenEnd.getEnd() + size != end) {
				return null;
			}

			// 1. replace the regions
			final ListIterator oldIterator = tokensContaier.removeTokensSubList(tokenStart, tokenEnd);
			ITextRegion[] newTokens = newContainer.getPhpTokens(); // now, add the new ones
			for (int i = 0; i < newTokens.length; i++) {
				oldIterator.add(newTokens[i]);
			}

			// 2. adjust next regions start location
			while (oldIterator.hasNext()) {
				final ITextRegion adjust = (ITextRegion) oldIterator.next();
				adjust.adjustStart(size);
			}

			// 3. update state changes
			tokensContaier.updateStateChanges(newContainer, tokenStart.getStart(), end);
			isFullReparsed = false;

			return super.updateRegion(requester, flatnode, changes, requestStart, lengthToReplace);

		} catch (BadLocationException e) {
			APDTCorePlugin.log(e);
			return null; // causes to full reparse in this case
		} 
	}

	/**
	 * Reparses the region given the 
	 * @param doc
	 * @param start
	 * @param length
	 */
	public void completeReparse(IDocument doc, int start, int length) {
		PhpLexer lexer = getPhpLexer(project, new BlockDocumentReader(doc, start, length), null);
		completeReparse(lexer);
	}

	private final boolean isHereDoc(final ITextRegion tokenStart) {
		if (tokenStart.getType() == PHPAspectRegionTypes.PHP_TOKEN) {
			try {
				final ITextRegion token = tokensContaier.getToken(tokenStart.getStart() - 1);
				return (token.getType() == PHPAspectRegionTypes.PHP_OPERATOR && token.getLength() == 2);
			} catch (BadLocationException e) {
				// never happens
				assert false;
			}
		}
		return false;
	}

	private boolean startQuoted(final String text) {
		final int length = text.length();
		if (length == 0) {
			return false;
		}

		boolean isOdd = false;
		for (int index = 0; index < length; index++) {
			final char charAt = text.charAt(index);
			if (charAt == '"' || charAt == '\'') {
				isOdd = !isOdd;
			}
		}
		return isOdd;
	}

	/**
	 * Performing a fully parse process to php script
	 * @param newText
	 */
	public void completeReparse(PhpLexer lexer) {
		setPhpTokens(lexer);
	}

	/**
	 * @param script 
	 * @return a list of php tokens
	 */
	private void setPhpTokens(PhpLexer lexer) {
		setLength(0);
		setTextLength(0);
		
		isFullReparsed = true;
		assert lexer != null;

		int start = 0;
		this.tokensContaier.getModelForCreation();
		this.tokensContaier.reset();
		try {
			Object state = lexer.createLexicalStateMemento();
			String yylex = lexer.getNextToken();
			int yylength = 0;
			while (yylex != null && yylex != PHPAspectRegionTypes.PHP_CLOSETAG) {
				yylength = lexer.getLength();
				this.tokensContaier.addLast(yylex, start, yylength, yylength, state);
				start += yylength;
				state = lexer.createLexicalStateMemento();
				yylex = lexer.getNextToken();
			}
			adjustLength(start);
			adjustTextLength(start);

		} catch (IOException e) {
			Logger.logException(e);
		} finally {
			this.tokensContaier.releaseModelFromCreation();
		}
	}


	/**
	 * Returns a stream that represents the new text
	 * We have three regions:
	 * 1) the php region before the change
	 * 2) the change
	 * 3) the php region after the region without the deleted text
	 * @param flatnode
	 * @param change
	 * @param requestStart
	 * @param lengthToReplace
	 * @param newTokenOffset
	 */
	public class DocumentReader extends Reader {

		private static final String BAD_LOCATION_ERROR = "Bad location error ";
		
		final private IStructuredDocument parent;
		final private int startPhpRegion;
		final private int endPhpRegion;
		final private int changeLength;
		final private String change;
		final private int requestStart;
		final private int lengthToReplace;
		
		private int index;
		private int internalIndex = 0;
		
		public DocumentReader(final IStructuredDocumentRegion flatnode, final String change, final int requestStart, final int lengthToReplace, final int newTokenOffset) {
			this.parent = flatnode.getParentDocument();
			this.startPhpRegion = flatnode.getStart() + getStart();
			this.endPhpRegion = startPhpRegion + getLength();
			this.changeLength = change.length();
			this.index = startPhpRegion + newTokenOffset;
			this.change = change; 
			this.requestStart = requestStart;
			this.lengthToReplace = lengthToReplace;
		}

		public int read() throws IOException {
			try {
				// state 1) 
				if (index < requestStart) {
					return parent.getChar(index++);
				} // state 2)
				if (internalIndex < changeLength) {
					return change.charAt(internalIndex++);
				} 
				// skip the delted text
				if (index < requestStart + lengthToReplace) {
					index = requestStart + lengthToReplace;
				}
				// state 3)
				return index < endPhpRegion ? parent.getChar(index++) : -1;

			} catch (BadLocationException e) {
				throw new IOException(DocumentReader.BAD_LOCATION_ERROR);
			}
		}

		public int read(char[] b, int off, int len) throws IOException {
			if (b == null) {
			    throw new NullPointerException();
			} else if ((off < 0) || (off > b.length) || (len < 0) ||
				   ((off + len) > b.length) || ((off + len) < 0)) {
			    throw new IndexOutOfBoundsException();
			} else if (len == 0) {
			    return 0;
			}

			int c = read();
			if (c == -1) {
			    return -1;
			}
			b[off] = (char)c;

			int i = 1;
			try {
			    for (; i < len ; i++) {
				c = read();
				if (c == -1) {
				    break;
				}
				if (b != null) {
				    b[off + i] = (char)c;
				}
			    }
			} catch (IOException ee) {
			}
			return i;
		}

		public void close() throws IOException {
		}
	}

	/**
	 * Returns a stream that represents the document
	 * @param StructuredDocument
	 * @param start
	 * @param length
	 */
	public class BlockDocumentReader extends Reader {

		private static final String BAD_LOCATION_ERROR = "Bad location error ";
		
		final private IDocument parent;
		private int startPhpRegion;
		final private int endPhpRegion;
	
		public BlockDocumentReader(final IDocument parent, final int startPhpRegion, final int length) {
			this.parent = parent;
			this.startPhpRegion = startPhpRegion;
			this.endPhpRegion = startPhpRegion + length;
		}

		public int read() throws IOException {
			try {
				return startPhpRegion < endPhpRegion ? parent.getChar(startPhpRegion++) : -1;
			} catch (BadLocationException e) {
				throw new IOException(BAD_LOCATION_ERROR + startPhpRegion);
			}
		}

		public int read(char[] b, int off, int len) throws IOException {
			if (b == null) {
			    throw new NullPointerException();
			} else if ((off < 0) || (off > b.length) || (len < 0) ||
				   ((off + len) > b.length) || ((off + len) < 0)) {
			    throw new IndexOutOfBoundsException();
			} else if (len == 0) {
			    return 0;
			}

			int c = read();
			if (c == -1) {
			    return -1;
			}
			b[off] = (char)c;

			int i = 1;
			try {
			    for (; i < len ; i++) {
				c = read();
				if (c == -1) {
				    break;
				}
				if (b != null) {
				    b[off + i] = (char)c;
				}
			    }
			} catch (IOException ee) {
			}
			return i;
		}

		public void close() throws IOException {
		}
	}
	/**
	 * @param project
	 * @param stream
	 * @return a new lexer for the given project with the given stream
	 */
	private static PhpLexer getPhpLexer(IProject project, Reader stream, LexerState startState) {
		PhpLexer lexer = new PHPAspectLexer(stream);
		lexer.initialize(PhpLexer.ST_PHP_IN_SCRIPTING);
		lexer.setPatterns(project);

		// set the wanted state
		if (startState != null) {
			startState.restoreState(lexer);
		}
		lexer.setAspTags(UseAspTagsHandler.useAspTagsAsPhp(project));
		return lexer;
	}

	/**
	 * @param project
	 * @param stream
	 * @return a new lexer for the given project with the given stream
	 */
	public static PhpLexer getPhpLexer(IProject project, java.io.Reader reader, char[] buffer, int[] parameters) {
		PhpLexer lexer = new PHPAspectLexer(reader);
		lexer.initialize(parameters[6]);
		lexer.reset(reader, buffer, parameters);
		lexer.setPatterns(project);

		lexer.setAspTags(UseAspTagsHandler.useAspTagsAsPhp(project));
		return lexer;
	}

	/**
	 * @see IPhpScriptRegion#isFullReparsed()
	 */
	public boolean isFullReparsed() {
		return isFullReparsed;
	}

	/**
	 * @see IPhpScriptRegion#setFullReparsed(boolean)
	 */
	public void setFullReparsed(boolean isFullReparse) {
		isFullReparsed = isFullReparse;
	}
}