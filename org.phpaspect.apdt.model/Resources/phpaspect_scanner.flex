/*******************************************************************************
 * Copyright (c) 2006 Zend Corporation and IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zend and IBM - Initial implementation
 *******************************************************************************/

package org.phpaspect.apdt.model;

import org.eclipse.php.internal.core.phpModel.javacup.runtime.Symbol;
import org.eclipse.php.internal.core.phpModel.parser.CompletionLexer;
import org.phpaspect.apdt.model.PHPAspectSymbols;
import org.eclipse.php.internal.core.util.collections.IntHashtable;
import org.eclipse.php.internal.core.phpModel.parser.ParserClient;
import org.eclipse.php.internal.core.phpModel.parser.StateStack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

%%

%class PHPAspectCompletionLexer
%extends CompletionLexer
%public
%unicode
%line

/* %cup */
%implements org.eclipse.php.internal.core.phpModel.javacup.runtime.Scanner
%function next_token
%type org.eclipse.php.internal.core.phpModel.javacup.runtime.Symbol
%eofval{
    return createSymbol(PHPAspectSymbols.EOF);
%eofval}
%eofclose

%caseless

%standalone
%state ST_IN_SCRIPTING
%state ST_DOUBLE_QUOTES
%state ST_SINGLE_QUOTE
%state ST_BACKQUOTE
%state ST_HEREDOC
%state ST_LOOKING_FOR_PROPERTY
%state ST_LOOKING_FOR_VARNAME
%state ST_COMMENT
%state ST_DOCBLOCK
%state ST_ONE_LINE_COMMENT
%{
    /** TODO, Do not forget to change yy_refill to the following code:
         private boolean yy_refill() throws java.io.IOException {

             // first: make room (if you can)
             if (yy_startRead > 0) {
                 char temp[] = yy_buffer;
                 //only if the new buffer will be changed then
                 //we have to keep the old copy
                 if (yy_endRead - yy_startRead > 0 || yy_startRead == yy_old_buffer.length) {
                     temp = yy_old_buffer;
                     yy_old_buffer = yy_buffer;
                     yy_old_pushbackPos = yy_pushbackPos;
                     duplicated_string_length = yy_endRead - yy_startRead;
                 }
                 System.arraycopy(yy_buffer, yy_startRead,
                         temp, 0,
                         yy_endRead - yy_startRead);
                 yy_buffer = temp;

                 // translate stored positions
                 yy_endRead -= yy_startRead;
                 yy_currentPos -= yy_startRead;
                 yy_markedPos -= yy_startRead;
                 yy_pushbackPos -= yy_startRead;
                 lastPhpDocStart = new Integer(lastPhpDocStart.intValue() - yy_startRead);
                 yy_startRead = 0;
             }

             // is the buffer big enough?
             if (yy_currentPos >= yy_buffer.length) {
                 // if not: blow it up
                 char newBuffer[] = new char[yy_currentPos * 2];
                 System.arraycopy(yy_buffer, 0, newBuffer, 0, yy_buffer.length);
                 yy_buffer = newBuffer;
                 newBuffer = new char[yy_currentPos * 2];
                 System.arraycopy(yy_old_buffer, 0, newBuffer, 0, yy_old_buffer.length);
                 System.arraycopy(yy_buffer, duplicated_string_length, newBuffer, yy_old_buffer.length, newBuffer.length - yy_old_buffer.length);
                 duplicated_string_length += newBuffer.length - yy_old_buffer.length;
                 yy_old_buffer = newBuffer;
             }

             // finally: fill the buffer with new input
             int numRead = yy_reader.read(yy_buffer, yy_endRead,
                     yy_buffer.length - yy_endRead);

             if (numRead < 0) {
                 return true;
             } else {
                 yy_endRead += numRead;
                 return false;
             }
         }
    */
    
    private String heredoc = null;
    private boolean asp_tags = false;
    private boolean short_tags_allowed = true;
    private StateStack stack = new StateStack();
    private ParserClient parserClient;
    private Pattern[] tasksPatterns;
    private char yy_old_buffer[] = new char[YY_BUFFERSIZE];
    private int yy_old_pushbackPos;
    private int duplicated_string_length;

	public void setUseAspTagsAsPhp(boolean useAspTagsAsPhp) {
		asp_tags = useAspTagsAsPhp;
	}
	
    private void pushState(int state) {
        stack.pushStack(yy_lexical_state);
        yybegin(state);
    }

    private void popState() {
        yybegin(stack.popStack());
    }

    public int getCurrentLine() {
        return yyline;
    }


    private int getTokenStartPosition() {
        return yy_startRead - yy_pushbackPos;
    }

    private int getTokenLength() {
        return yy_markedPos - yy_startRead;
    }

    public void setParserClient(ParserClient parserClient) {
        this.parserClient = parserClient;
    }

    public void setTasksPatterns(Pattern[] tasksPatterns){
    	this.tasksPatterns = tasksPatterns;
    }
    
    private void handlePHPStart() {
        if (parserClient != null) {
            int startPosition = getTokenStartPosition();
            parserClient.handlePHPStart(startPosition, startPosition + getTokenLength());
        }
    }

    private void handlePHPEnd() {
        if (parserClient != null) {
            int startPosition = getTokenStartPosition();
            parserClient.handlePHPEnd(startPosition, startPosition + getTokenLength());
        }
    }

    public int getLength() {
        return yy_endRead - yy_pushbackPos;
    }

    private IntHashtable phpDocs = new IntHashtable();
    private IntHashtable phpDocsStart = new IntHashtable();
    private String lastPhpDocText;
    private Integer lastPhpDocStart = new Integer(0);
    private Object[] phpDocValues = new Object [2];
    private String firstPhpDocText = null;
    private Integer firstPhpDocStart = null;
    private boolean noSymboles = true;

	private int commentStartPosition;
	private int commentStartLine;
	private StringBuffer comment = new StringBuffer();
	
	private void handlePHPDocStart() {
		handleCommentStart();
		lastPhpDocStart = new Integer(getTokenStartPosition());    
	}
		    
    private void handlePHPDocEnd() {
    	handleCommentEnd();
  	    lastPhpDocText = comment.toString();
        if (lastPhpDocText != null) {
            if (noSymboles &&((firstPhpDocStart == null)|| (firstPhpDocStart.compareTo(lastPhpDocStart) > 0))){
                    firstPhpDocText = lastPhpDocText;
                    firstPhpDocStart = lastPhpDocStart;
            }
        }
    }

    public Object[] getPHPDoc(int location) {
        phpDocValues[0] = phpDocs.get(location);
        phpDocValues[1] = phpDocsStart.get(location);
        return phpDocValues;
    }

   public Object[] getFirstPHPDoc() {
        phpDocValues[0] = firstPhpDocText;
        phpDocValues[1] = firstPhpDocStart;
        return phpDocValues;
    }

    private void appendTextToComment(){
    	comment.append(yy_buffer, yy_startRead, yy_markedPos-yy_startRead);
    }
    
    private void clearCommentBuffer(){
    	comment.delete(0,comment.length());
    }
    
	private void handleCommentStart() {
		commentStartPosition = yy_startRead;
		commentStartLine = yyline;
		clearCommentBuffer();
		appendTextToComment();
	}
	
	private void handleCommentEnd() {
		appendTextToComment();
		checkForTasks();
	}
	
    private void checkForTasks(){
		ArrayList matchers = createMatcherList();		
		int searchStartPosition = 0;
		int lineCnt = 0;
		int commentEndIndex = comment.length();
		//if its a multiline comment or a phpDoc no nead to search the */ in the end 
		if(comment.charAt(0)=='/' && comment.charAt(1)=='*'){
			commentEndIndex -=2;
		}
		Matcher matcher = getMinimalMatcher(matchers, searchStartPosition);
		while (matcher != null) {
			String taskName = matcher.group();
			int taskStartPositionInComment = matcher.start();
			int descriptionStartPositionInComment = matcher.end();			

			//counting the lines till the begining of the task
			lineCnt+= countNewLines(searchStartPosition, matcher.start());
			
			//calculating the endPosition of the task
			searchStartPosition = matcher.end();
			matcher = getMinimalMatcher(matchers, searchStartPosition);
			
			int endSearch = (matcher == null)? commentEndIndex : matcher.start();
			int taskEndPositionInComment = descriptionStartPositionInComment;
			while(taskEndPositionInComment < endSearch){
				char ch = comment.charAt(taskEndPositionInComment);
				if(ch == '\n' || ch == '\r'){
					break;
				}
				taskEndPositionInComment++;
			}

			String description = comment.substring(descriptionStartPositionInComment,taskEndPositionInComment);
			description = description.trim();
			parserClient.handleTask(taskName, description, commentStartPosition  - yy_pushbackPos + taskStartPositionInComment, commentStartPosition  - yy_pushbackPos + taskEndPositionInComment  - 1,commentStartLine + lineCnt);
		}
    }
    
    private int countNewLines(int startIndex, int endIndex) {
    	int lineCnt = 0;
		for(int i = startIndex; i < endIndex ; i++){
			char ch = comment.charAt(i);
			if(ch == '\n' || ch == '\r'){
				char oppositeCh = (ch == '\n') ? '\r' : '\n';
				lineCnt++;
				if(i + 1 < endIndex){
					ch = comment.charAt(i + 1);
					if(ch == oppositeCh){
						i++;
					}
				}
			}
		}
		return lineCnt;
    }

	private ArrayList createMatcherList() {
		ArrayList list = new ArrayList(tasksPatterns.length);
		for (int i = 0; i < tasksPatterns.length; i++) {
			list.add(i, tasksPatterns[i].matcher(comment));
		}
		return list;
	}

	private Matcher getMinimalMatcher(ArrayList matchers, int startPosition) {
		Matcher minimal = null;
		int size = matchers.size();
		for (int i = 0; i < size;) {
			Matcher tmp = (Matcher) matchers.get(i);
			if (tmp.find(startPosition)) {
				if (minimal == null || tmp.start() < minimal.start()) {
					minimal = tmp;
				}
				i++;
			} else {
				matchers.remove(i);
				size--;
			}
		}
		return minimal;
	}

    private Symbol createFullSymbol(int symbolNumber) {
        Symbol symbol = createSymbol(symbolNumber);
        symbol.value = yytext();
        return symbol;
    }

    private Symbol createSymbol(int symbolNumber) {
        int leftPosition = getTokenStartPosition();
        if (lastPhpDocText != null) {
            if(lastPhpDocText == firstPhpDocText) {
                if(symbolNumber == PHPAspectSymbols.T_FUNCTION ||
                        symbolNumber == PHPAspectSymbols.T_CLASS ||
                        symbolNumber == PHPAspectSymbols.T_DEFINE){
                    phpDocs.put(leftPosition, lastPhpDocText);
                    phpDocsStart.put(leftPosition, new Integer(lastPhpDocStart.intValue()));
                    firstPhpDocText = null;
                }
            } else {
                phpDocs.put(leftPosition, lastPhpDocText);
                phpDocsStart.put(leftPosition, new Integer(lastPhpDocStart.intValue()));
            }
            lastPhpDocText = null;
        }
        if(noSymboles && symbolNumber != PHPAspectSymbols.T_INLINE_HTML){	// T_INLINE_HTML doesn't infect the phpDoc 
        	noSymboles = false;        
        }
        return new Symbol(symbolNumber, leftPosition, leftPosition + getTokenLength());
    }

    public String createString(int startOffset, int endOffset) {
        int startPosition = startOffset + yy_pushbackPos;
        int length =  endOffset - startOffset;
        if(startPosition < 0){
            startPosition = startOffset + yy_old_pushbackPos;
            if(startPosition + length < yy_old_buffer.length){
            	if(startPosition < 0 || startPosition + length > yy_old_buffer.length){
                	return "";
                }
                return new String(yy_old_buffer, startPosition, length);
            }
            // meaning the string was splited between the two buffers
            int remainder = startPosition + length  + duplicated_string_length - yy_old_buffer.length;
            length -= remainder;
            if(startPosition < 0 || startPosition + length > yy_old_buffer.length || remainder > yy_buffer.length){
            	return "";
            }
            return (new String(yy_old_buffer, startPosition, length) + new String(yy_buffer, 0, remainder));
        }
    	if(startPosition < 0 || startPosition + length > yy_buffer.length){
        	return "";
        }
        return new String(yy_buffer, startPosition, length);
    }

%}

LNUM=[0-9]+
DNUM=([0-9]*[\.][0-9]+)|([0-9]+[\.][0-9]*)
EXPONENT_DNUM=(({LNUM}|{DNUM})[eE][+-]?{LNUM})
HNUM="0x"[0-9a-fA-F]+
LABEL=[a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]*
WHITESPACE=[ \n\r\t]+
TABS_AND_SPACES=[ \t]*
ESCAPED_AND_WHITESPACE=[\n\t\r #'.:;,()|\^&+-//*=%!~<>?@]+
ANY_CHAR=(.|[\n])
NEWLINE=("\r"|"\n"|"\r\n")

%%

<ST_IN_SCRIPTING>"exit" {
	return createSymbol(PHPAspectSymbols.T_EXIT);
}

<ST_IN_SCRIPTING>"die" {
	return createSymbol(PHPAspectSymbols.T_EXIT);
}

<ST_IN_SCRIPTING>"function"|"cfunction" {
	return createSymbol(PHPAspectSymbols.T_FUNCTION);
}

<ST_IN_SCRIPTING>"const" {
	return createSymbol(PHPAspectSymbols.T_CONST);
}

<ST_IN_SCRIPTING>"return" {
	return createSymbol(PHPAspectSymbols.T_RETURN);
}

<ST_IN_SCRIPTING>"try" {
	return createSymbol(PHPAspectSymbols.T_TRY);
}

<ST_IN_SCRIPTING>"catch" {
	return createSymbol(PHPAspectSymbols.T_CATCH);
}

<ST_IN_SCRIPTING>"throw" {
	return createSymbol(PHPAspectSymbols.T_THROW);
}

<ST_IN_SCRIPTING>"if" {
	return createSymbol(PHPAspectSymbols.T_IF);
}

<ST_IN_SCRIPTING>"elseif" {
	return createSymbol(PHPAspectSymbols.T_ELSEIF);
}

<ST_IN_SCRIPTING>"endif" {
	return createSymbol(PHPAspectSymbols.T_ENDIF);
}

<ST_IN_SCRIPTING>"else" {
	return createSymbol(PHPAspectSymbols.T_ELSE);
}

<ST_IN_SCRIPTING>"while" {
	return createSymbol(PHPAspectSymbols.T_WHILE);
}

<ST_IN_SCRIPTING>"endwhile" {
	return createSymbol(PHPAspectSymbols.T_ENDWHILE);
}

<ST_IN_SCRIPTING>"do" {
	return createSymbol(PHPAspectSymbols.T_DO);
}

<ST_IN_SCRIPTING>"for" {
	return createSymbol(PHPAspectSymbols.T_FOR);
}

<ST_IN_SCRIPTING>"endfor" {
	return createSymbol(PHPAspectSymbols.T_ENDFOR);
}

<ST_IN_SCRIPTING>"foreach" {
	return createSymbol(PHPAspectSymbols.T_FOREACH);
}

<ST_IN_SCRIPTING>"endforeach" {
	return createSymbol(PHPAspectSymbols.T_ENDFOREACH);
}

<ST_IN_SCRIPTING>"declare" {
	return createSymbol(PHPAspectSymbols.T_DECLARE);
}

<ST_IN_SCRIPTING>"enddeclare" {
	return createSymbol(PHPAspectSymbols.T_ENDDECLARE);
}

<ST_IN_SCRIPTING>"instanceof" {
	return createSymbol(PHPAspectSymbols.T_INSTANCEOF);
}

<ST_IN_SCRIPTING>"as" {
	return createSymbol(PHPAspectSymbols.T_AS);
}

<ST_IN_SCRIPTING>"switch" {
	return createSymbol(PHPAspectSymbols.T_SWITCH);
}

<ST_IN_SCRIPTING>"endswitch" {
	return createSymbol(PHPAspectSymbols.T_ENDSWITCH);
}

<ST_IN_SCRIPTING>"case" {
	return createSymbol(PHPAspectSymbols.T_CASE);
}

<ST_IN_SCRIPTING>"default" {
	return createSymbol(PHPAspectSymbols.T_DEFAULT);
}

<ST_IN_SCRIPTING>"break" {
	return createSymbol(PHPAspectSymbols.T_BREAK);
}

<ST_IN_SCRIPTING>"continue" {
	return createSymbol(PHPAspectSymbols.T_CONTINUE);
}

<ST_IN_SCRIPTING>"echo" {
	return createSymbol(PHPAspectSymbols.T_ECHO);
}

<ST_IN_SCRIPTING>"print" {
	return createSymbol(PHPAspectSymbols.T_PRINT);
}

<ST_IN_SCRIPTING>"class" {
	return createSymbol(PHPAspectSymbols.T_CLASS);
}

<ST_IN_SCRIPTING>"aspect" {
	return createSymbol(PHPAspectSymbols.T_ASPECT);
}

<ST_IN_SCRIPTING>"interface" {
	return createSymbol(PHPAspectSymbols.T_INTERFACE);
}

<ST_IN_SCRIPTING>"extends" {
	return createSymbol(PHPAspectSymbols.T_EXTENDS);
}

<ST_IN_SCRIPTING>"implements" {
	return createSymbol(PHPAspectSymbols.T_IMPLEMENTS);
}

<ST_IN_SCRIPTING,ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"->" {
    pushState(ST_LOOKING_FOR_PROPERTY);
    return createSymbol(PHPAspectSymbols.T_OBJECT_OPERATOR);
}

<ST_LOOKING_FOR_PROPERTY>{LABEL} {
    popState();
    return createFullSymbol(PHPAspectSymbols.T_STRING);
}

<ST_LOOKING_FOR_PROPERTY>{ANY_CHAR} {
    yypushback(yylength());
    popState();
}

<ST_IN_SCRIPTING>"::" {
	return createSymbol(PHPAspectSymbols.T_PAAMAYIM_NEKUDOTAYIM);
}

<ST_IN_SCRIPTING>"new" {
	return createSymbol(PHPAspectSymbols.T_NEW);
}

<ST_IN_SCRIPTING>"clone" {
	return createSymbol(PHPAspectSymbols.T_CLONE);
}

<ST_IN_SCRIPTING>"var" {
	return createSymbol(PHPAspectSymbols.T_VAR);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("int"|"integer"){TABS_AND_SPACES}")" {
	return createSymbol(PHPAspectSymbols.T_INT_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("real"|"double"|"float"){TABS_AND_SPACES}")" {
	return createSymbol(PHPAspectSymbols.T_DOUBLE_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"string"{TABS_AND_SPACES}")" {
	return createSymbol(PHPAspectSymbols.T_STRING_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"array"{TABS_AND_SPACES}")" {
	return createSymbol(PHPAspectSymbols.T_ARRAY_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"object"{TABS_AND_SPACES}")" {
	return createSymbol(PHPAspectSymbols.T_OBJECT_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("bool"|"boolean"){TABS_AND_SPACES}")" {
	return createSymbol(PHPAspectSymbols.T_BOOL_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("unset"){TABS_AND_SPACES}")" {
	return createSymbol(PHPAspectSymbols.T_UNSET_CAST);
}

<ST_IN_SCRIPTING>"eval" {
	return createSymbol(PHPAspectSymbols.T_EVAL);
}

<ST_IN_SCRIPTING>"include" {
	return createSymbol(PHPAspectSymbols.T_INCLUDE);
}

<ST_IN_SCRIPTING>"include_once" {
	return createSymbol(PHPAspectSymbols.T_INCLUDE_ONCE);
}

<ST_IN_SCRIPTING>"require" {
	return createSymbol(PHPAspectSymbols.T_REQUIRE);
}

<ST_IN_SCRIPTING>"require_once" {
	return createSymbol(PHPAspectSymbols.T_REQUIRE_ONCE);
}

<ST_IN_SCRIPTING>"use" {
	return createSymbol(PHPAspectSymbols.T_USE);
}

<ST_IN_SCRIPTING>"global" {
	return createSymbol(PHPAspectSymbols.T_GLOBAL);
}

<ST_IN_SCRIPTING>"isset" {
	return createSymbol(PHPAspectSymbols.T_ISSET);
}

<ST_IN_SCRIPTING>"empty" {
	return createSymbol(PHPAspectSymbols.T_EMPTY);
}

<ST_IN_SCRIPTING>"__halt_compiler" {
	return createSymbol(PHPAspectSymbols.T_HALT_COMPILER);
}
<ST_IN_SCRIPTING>"static" {
	return createSymbol(PHPAspectSymbols.T_STATIC);
}

<ST_IN_SCRIPTING>"abstract" {
	return createSymbol(PHPAspectSymbols.T_ABSTRACT);
}

<ST_IN_SCRIPTING>"final" {
	return createSymbol(PHPAspectSymbols.T_FINAL);
}

<ST_IN_SCRIPTING>"private" {
	return createSymbol(PHPAspectSymbols.T_PRIVATE);
}

<ST_IN_SCRIPTING>"protected" {
	return createSymbol(PHPAspectSymbols.T_PROTECTED);
}

<ST_IN_SCRIPTING>"public" {
	return createSymbol(PHPAspectSymbols.T_PUBLIC);
}

<ST_IN_SCRIPTING>"unset" {
	return createSymbol(PHPAspectSymbols.T_UNSET);
}

<ST_IN_SCRIPTING>"=>" {
	return createSymbol(PHPAspectSymbols.T_DOUBLE_ARROW);
}

<ST_IN_SCRIPTING>"list" {
	return createSymbol(PHPAspectSymbols.T_LIST);
}

<ST_IN_SCRIPTING>"array" {
	return createSymbol(PHPAspectSymbols.T_ARRAY);
}

<ST_IN_SCRIPTING>"++" {
	return createSymbol(PHPAspectSymbols.T_INC);
}

<ST_IN_SCRIPTING>"--" {
	return createSymbol(PHPAspectSymbols.T_DEC);
}

<ST_IN_SCRIPTING>"===" {
	return createSymbol(PHPAspectSymbols.T_IS_IDENTICAL);
}

<ST_IN_SCRIPTING>"!==" {
	return createSymbol(PHPAspectSymbols.T_IS_NOT_IDENTICAL);
}

<ST_IN_SCRIPTING>"==" {
	return createSymbol(PHPAspectSymbols.T_IS_EQUAL);
}

<ST_IN_SCRIPTING>"!="|"<>" {
	return createSymbol(PHPAspectSymbols.T_IS_NOT_EQUAL);
}

<ST_IN_SCRIPTING>"<=" {
	return createSymbol(PHPAspectSymbols.T_IS_SMALLER_OR_EQUAL);
}

<ST_IN_SCRIPTING>">=" {
	return createSymbol(PHPAspectSymbols.T_IS_GREATER_OR_EQUAL);
}

<ST_IN_SCRIPTING>"+=" {
	return createSymbol(PHPAspectSymbols.T_PLUS_EQUAL);
}

<ST_IN_SCRIPTING>"-=" {
	return createSymbol(PHPAspectSymbols.T_MINUS_EQUAL);
}

<ST_IN_SCRIPTING>"*=" {
	return createSymbol(PHPAspectSymbols.T_MUL_EQUAL);
}

<ST_IN_SCRIPTING>"/=" {
	return createSymbol(PHPAspectSymbols.T_DIV_EQUAL);
}

<ST_IN_SCRIPTING>".=" {
	return createSymbol(PHPAspectSymbols.T_CONCAT_EQUAL);
}

<ST_IN_SCRIPTING>"%=" {
	return createSymbol(PHPAspectSymbols.T_MOD_EQUAL);
}

<ST_IN_SCRIPTING>"<<=" {
	return createSymbol(PHPAspectSymbols.T_SL_EQUAL);
}

<ST_IN_SCRIPTING>">>=" {
	return createSymbol(PHPAspectSymbols.T_SR_EQUAL);
}

<ST_IN_SCRIPTING>"&=" {
	return createSymbol(PHPAspectSymbols.T_AND_EQUAL);
}

<ST_IN_SCRIPTING>"|=" {
	return createSymbol(PHPAspectSymbols.T_OR_EQUAL);
}

<ST_IN_SCRIPTING>"^=" {
	return createSymbol(PHPAspectSymbols.T_XOR_EQUAL);
}

<ST_IN_SCRIPTING>"||" {
	return createSymbol(PHPAspectSymbols.T_BOOLEAN_OR);
}

<ST_IN_SCRIPTING>"&&" {
	return createSymbol(PHPAspectSymbols.T_BOOLEAN_AND);
}

<ST_IN_SCRIPTING>"OR" {
	return createSymbol(PHPAspectSymbols.T_LOGICAL_OR);
}

<ST_IN_SCRIPTING>"AND" {
	return createSymbol(PHPAspectSymbols.T_LOGICAL_AND);
}

<ST_IN_SCRIPTING>"XOR" {
	return createSymbol(PHPAspectSymbols.T_LOGICAL_XOR);
}

<ST_IN_SCRIPTING>"<<" {
	return createSymbol(PHPAspectSymbols.T_SL);
}

<ST_IN_SCRIPTING>">>" {
	return createSymbol(PHPAspectSymbols.T_SR);
}

// TOKENS
<ST_IN_SCRIPTING> {
    ";"                     {return createSymbol(PHPAspectSymbols.T_SEMICOLON);}
    ":"                     {return createSymbol(PHPAspectSymbols.T_NEKUDOTAIM);}
    ","                     {return createSymbol(PHPAspectSymbols.T_COMMA);}
    "."                     {return createSymbol(PHPAspectSymbols.T_NEKUDA);}
    "["                     {return createSymbol(PHPAspectSymbols.T_OPEN_RECT);}
    "]"                     {return createSymbol(PHPAspectSymbols.T_CLOSE_RECT);}
    "("                     {return createSymbol(PHPAspectSymbols.T_OPEN_PARENTHESE);}
    ")"                     {return createSymbol(PHPAspectSymbols.T_CLOSE_PARENTHESE);}
    "|"                     {return createSymbol(PHPAspectSymbols.T_OR);}
    "^"                     {return createSymbol(PHPAspectSymbols.T_KOVA);}
    "&"                     {return createSymbol(PHPAspectSymbols.T_REFERENCE);}
    "+"                     {return createSymbol(PHPAspectSymbols.T_PLUS);}
    "-"                     {return createSymbol(PHPAspectSymbols.T_MINUS);}
    "/"                     {return createSymbol(PHPAspectSymbols.T_DIV);}
    "*"                     {return createSymbol(PHPAspectSymbols.T_TIMES);}
    "="                     {return createSymbol(PHPAspectSymbols.T_EQUAL);}
    "%"                     {return createSymbol(PHPAspectSymbols.T_PRECENT);}
    "!"                     {return createSymbol(PHPAspectSymbols.T_NOT);}
    "~"                     {return createSymbol(PHPAspectSymbols.T_TILDA);}
    "$"                     {return createSymbol(PHPAspectSymbols.T_DOLLAR);}
    "<"                     {return createSymbol(PHPAspectSymbols.T_RGREATER);}
    ">"                     {return createSymbol(PHPAspectSymbols.T_LGREATER);}
    "?"                     {return createSymbol(PHPAspectSymbols.T_QUESTION_MARK);}
    "@"                     {return createSymbol(PHPAspectSymbols.T_AT);}
}

<ST_IN_SCRIPTING>"{" {
    pushState(ST_IN_SCRIPTING);
    return createSymbol(PHPAspectSymbols.T_CURLY_OPEN);

}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"${" {
    pushState(ST_LOOKING_FOR_VARNAME);
    return createSymbol(PHPAspectSymbols.T_DOLLAR_OPEN_CURLY_BRACES);
}

<ST_IN_SCRIPTING>"}" {
	/* This is a temporary fix which is dependant on flex and it's implementation */
    if (!stack.isEmpty()) {
        popState();
    }
    return createSymbol(PHPAspectSymbols.T_CURLY_CLOSE);
}

<ST_LOOKING_FOR_VARNAME>{LABEL} {
    popState();
    pushState(ST_IN_SCRIPTING);
    return createFullSymbol(PHPAspectSymbols.T_STRING_VARNAME);
}

<ST_LOOKING_FOR_VARNAME>{ANY_CHAR} {
    yypushback(yylength());
    popState();
    pushState(ST_IN_SCRIPTING);
}

<ST_IN_SCRIPTING>{LNUM} {
    return createFullSymbol(PHPAspectSymbols.T_DNUMBER);
}

<ST_IN_SCRIPTING>{HNUM} {
    return createFullSymbol(PHPAspectSymbols.T_DNUMBER);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>{LNUM}|{HNUM} { /* treat numbers (almost) as strings inside encapsulated strings */
    return createSymbol(PHPAspectSymbols.T_NUM_STRING);
}

<ST_IN_SCRIPTING>{DNUM}|{EXPONENT_DNUM} {
    return createFullSymbol(PHPAspectSymbols.T_DNUMBER);
}

<ST_IN_SCRIPTING>"__CLASS__" {
    return createSymbol(PHPAspectSymbols.T_CLASS_C);
}

<ST_IN_SCRIPTING>"__FUNCTION__" {
    return createSymbol(PHPAspectSymbols.T_FUNC_C);
}

<ST_IN_SCRIPTING>"__METHOD__" {
    return createSymbol(PHPAspectSymbols.T_METHOD_C);
}

<ST_IN_SCRIPTING>"__LINE__" {
    return createSymbol(PHPAspectSymbols.T_LINE);
}

<ST_IN_SCRIPTING>"__FILE__" {
    return createSymbol(PHPAspectSymbols.T_FILE);
}

<YYINITIAL>(([^<]|"<"[^?%s<])+)|"<s"|"<" {
    return createSymbol(PHPAspectSymbols.T_INLINE_HTML);
}

<YYINITIAL>"<?"|"<script"{WHITESPACE}+"language"{WHITESPACE}*"="{WHITESPACE}*("php"|"\"php\""|"\'php\'"){WHITESPACE}*">" {
    if (short_tags_allowed || yylength()>2) { /* yyleng>2 means it's not <? but <script> */
        handlePHPStart();
        yybegin(ST_IN_SCRIPTING);
        //return T_OPEN_TAG;
    } else {
        return createSymbol(PHPAspectSymbols.T_INLINE_HTML);
    }
}

<YYINITIAL>"<%="|"<?=" {
    String text = yytext();
    if ((text.charAt(1)=='%' && asp_tags)
        || (text.charAt(1)=='?' && short_tags_allowed)) {
        handlePHPStart();
        yybegin(ST_IN_SCRIPTING);
        //return T_OPEN_TAG_WITH_ECHO;
    } else {
        return createSymbol(PHPAspectSymbols.T_INLINE_HTML);
    }
}

<YYINITIAL>"<%" {
    if (asp_tags) {
        handlePHPStart();
        yybegin(ST_IN_SCRIPTING);
		//return T_OPEN_TAG;
    } else {
        return createSymbol(PHPAspectSymbols.T_INLINE_HTML);
    }
}

<YYINITIAL>"<?php"([ \t]|{NEWLINE}) {
    handlePHPStart();
    yybegin(ST_IN_SCRIPTING);
	//return T_OPEN_TAG;
}

<ST_IN_SCRIPTING,ST_DOUBLE_QUOTES,ST_HEREDOC,ST_BACKQUOTE>"$"{LABEL} {
    return createFullSymbol(PHPAspectSymbols.T_VARIABLE);
}

<ST_IN_SCRIPTING>"define" {
    /* not a keyword, hust for recognize constans.*/
    return createFullSymbol(PHPAspectSymbols.T_DEFINE);
}

<ST_IN_SCRIPTING>{LABEL} {
    return createFullSymbol(PHPAspectSymbols.T_STRING);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>{LABEL} {
    return createFullSymbol(PHPAspectSymbols.T_STRING);
}

<ST_IN_SCRIPTING>{WHITESPACE} {
}

<ST_IN_SCRIPTING>"#"|"//" {
	handleCommentStart();
	yybegin(ST_ONE_LINE_COMMENT);
//	yymore();
}

<ST_ONE_LINE_COMMENT>"?"|"%"|">" {
appendTextToComment();
//	yymore();
}

<ST_ONE_LINE_COMMENT>[^\n\r?%>]*{ANY_CHAR} {
	String yytext = yytext();
	switch (yytext.charAt(yytext.length() - 1)) {
		case '?':
		case '%':
		case '>':
			yypushback(1);
			appendTextToComment();
			break;
		default:
			handleCommentEnd();
			yybegin(ST_IN_SCRIPTING);
	}
//	yymore();
}

<ST_ONE_LINE_COMMENT>{NEWLINE} {
	handleCommentEnd();
	yybegin(ST_IN_SCRIPTING);
	//return T_COMMENT;
}

<ST_ONE_LINE_COMMENT>"?>"|"%>" {
    if (asp_tags || yytext().charAt(0)!='%') { /* asp comment? */
	    handleCommentEnd();
        yypushback(yylength());
		yybegin(ST_IN_SCRIPTING);
		//return T_COMMENT;
	} else {
		appendTextToComment();
	}
}

<ST_IN_SCRIPTING>"/*"{WHITESPACE}*"@var"{WHITESPACE}("$"?){LABEL}{WHITESPACE}{LABEL}{WHITESPACE}?"*/" {
    return createFullSymbol(PHPAspectSymbols.T_VAR_COMMENT);
}

<ST_IN_SCRIPTING>"/**" {
handlePHPDocStart();
yybegin(ST_DOCBLOCK);
}

<ST_DOCBLOCK>"*/" {
     handlePHPDocEnd();
     yybegin(ST_IN_SCRIPTING);
}

<ST_DOCBLOCK>{NEWLINE} {
appendTextToComment();
}

<ST_DOCBLOCK>{ANY_CHAR} {
appendTextToComment();
}

<ST_IN_SCRIPTING>"/**/" {
}

<ST_IN_SCRIPTING>"/*" {
	handleCommentStart();
    yybegin(ST_COMMENT);
}

<ST_COMMENT>[^*]+ {
appendTextToComment();
}

<ST_COMMENT>"*/" {
	handleCommentEnd();
    yybegin(ST_IN_SCRIPTING);
}

<ST_COMMENT>"*" {
appendTextToComment();
//	yymore();
}

<ST_IN_SCRIPTING>("?>"|"</script"{WHITESPACE}*">"){NEWLINE}? {
    handlePHPEnd();
    yybegin(YYINITIAL);
    return createSymbol(PHPAspectSymbols.T_SEMICOLON);  /* implicit ';' at php-end tag */
}

<ST_IN_SCRIPTING>"%>"{NEWLINE}? {
    if (asp_tags) {
        handlePHPEnd();
        yybegin(YYINITIAL);
        return createSymbol(PHPAspectSymbols.T_SEMICOLON);  /* implicit ';' at php-end tag */
    } else {
        return createSymbol(PHPAspectSymbols.T_INLINE_HTML);
    }
}

<ST_IN_SCRIPTING>([\"]([^$\"\\]|("\\".))*[\"]) {
    return createFullSymbol(PHPAspectSymbols.T_CONSTANT_ENCAPSED_STRING);
}

<ST_IN_SCRIPTING>([']([^'\\]|("\\".))*[']) {
    return createFullSymbol(PHPAspectSymbols.T_CONSTANT_ENCAPSED_STRING);
}

<ST_IN_SCRIPTING>[\"] {
    yybegin(ST_DOUBLE_QUOTES);
    return createSymbol(PHPAspectSymbols.T_QUATE);
}

<ST_IN_SCRIPTING>"<<<"{TABS_AND_SPACES}{LABEL}{NEWLINE} {
    heredoc = yytext().substring(3).trim();    // for '<<<'
    yybegin(ST_HEREDOC);
    return createSymbol(PHPAspectSymbols.T_START_HEREDOC);
}

<ST_IN_SCRIPTING>[`] {
    yybegin(ST_BACKQUOTE);
    return createSymbol(PHPAspectSymbols.T_BACKQUATE);
}

<ST_IN_SCRIPTING>['] {
    yybegin(ST_SINGLE_QUOTE);
    return createSymbol(PHPAspectSymbols.T_SINGLE_QUATE);
}

<ST_HEREDOC>^{LABEL}(";")?{NEWLINE} {
    String text = yytext();
    int length = text.length();
    text = text.trim();
    boolean foundNP = false;
    if (text.endsWith(";")) {
        text = text.substring(0, text.length() - 1);
        foundNP = true;
    }
    if (text.equals(heredoc)) {
        if (foundNP) {
            yypushback(length - text.length());
        }
        heredoc = null;
        yybegin(ST_IN_SCRIPTING);
        return createSymbol(PHPAspectSymbols.T_END_HEREDOC);
    } else {
        return createFullSymbol(PHPAspectSymbols.T_STRING);
    }
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>{ESCAPED_AND_WHITESPACE} {
    return createSymbol(PHPAspectSymbols.T_ENCAPSED_AND_WHITESPACE);
}

<ST_SINGLE_QUOTE>([^'\\]|\\[^'\\])+ {
    return createSymbol(PHPAspectSymbols.T_ENCAPSED_AND_WHITESPACE);
}

<ST_DOUBLE_QUOTES>[`]+ {
    return createSymbol(PHPAspectSymbols.T_ENCAPSED_AND_WHITESPACE);
}

<ST_BACKQUOTE>[\"]+ {
    return createSymbol(PHPAspectSymbols.T_ENCAPSED_AND_WHITESPACE);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"$"[^a-zA-Z_\x7f-\xff{] {
    if (yylength() == 2) {
        yypushback(1);
    }
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

// ENCAPSED_TOKENS
<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC> {
    "["     {return createSymbol(PHPAspectSymbols.T_OPEN_RECT);}

    "]"     {return createSymbol(PHPAspectSymbols.T_CLOSE_RECT); }

    "$"     {return createSymbol(PHPAspectSymbols.T_DOLLAR); }
    
    "{"     {return createSymbol(PHPAspectSymbols.T_CURLY_OPEN); }
    
    "}"     {return createSymbol(PHPAspectSymbols.T_CURLY_CLOSE); }
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\{" {
	return createSymbol(PHPAspectSymbols.T_STRING);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"{$" {
    pushState(ST_IN_SCRIPTING);
    yypushback(yylength()-1);
    return createSymbol(PHPAspectSymbols.T_CURLY_OPEN_WITH_DOLAR);
}

<ST_SINGLE_QUOTE>"\\'" {
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

<ST_SINGLE_QUOTE>"\\\\" {
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

<ST_DOUBLE_QUOTES>"\\\"" {
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

<ST_BACKQUOTE>"\\`" {
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\"[0-7]{1,3} {
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\x"[0-9A-Fa-f]{1,2} {
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\"{ANY_CHAR} {
    switch (yytext().charAt(1)) {
        case 'n':
            break;
        case 't':
            break;
        case 'r':
            break;
        case '\\':
            break;
        case '$':
            break;
        default:
            return createSymbol(PHPAspectSymbols.T_BAD_CHARACTER);
    }
    return createSymbol(PHPAspectSymbols.T_CHARACTER);
}

<ST_HEREDOC>[\"'`]+ {
    return createSymbol(PHPAspectSymbols.T_ENCAPSED_AND_WHITESPACE);
}

<ST_DOUBLE_QUOTES>[\"] {
    yybegin(ST_IN_SCRIPTING);
    return createSymbol(PHPAspectSymbols.T_QUATE);
}

<ST_BACKQUOTE>[`] {
    yybegin(ST_IN_SCRIPTING);
    return createSymbol(PHPAspectSymbols.T_BACKQUATE);
}

<ST_SINGLE_QUOTE>['] {
    yybegin(ST_IN_SCRIPTING);
    return createSymbol(PHPAspectSymbols.T_SINGLE_QUATE);
}

<ST_IN_SCRIPTING,YYINITIAL,ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_SINGLE_QUOTE,ST_HEREDOC>{ANY_CHAR} {
	// do nothing
}
