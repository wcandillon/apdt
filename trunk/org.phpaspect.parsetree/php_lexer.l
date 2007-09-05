
/*
   +----------------------------------------------------------------------+
   | Zend Engine                                                          |
   +----------------------------------------------------------------------+
   | Copyright (c) 1998-2006 Zend Technologies Ltd. (http://www.zend.com) |
   +----------------------------------------------------------------------+
   | This source file is subject to version 2.00 of the Zend license,     |
   | that is bundled with this package in the file LICENSE, and is        |
   | available through the world-wide-web at the following url:           |
   | http://www.zend.com/license/2_00.txt.                                |
   | If you did not receive a copy of the Zend license and are unable to  |
   | obtain it through the world-wide-web, please send a note to          |
   | license@zend.com so we can mail you a copy immediately.              |
   +----------------------------------------------------------------------+
   | Authors: Andi Gutmans <andi@zend.com>                                |
   |          Zeev Suraski <zeev@zend.com>                                |
   +----------------------------------------------------------------------+
*/

package org.phpaspect.parsetree;

import org.eclipse.php.internal.core.ast.nodes.Comment;
import org.eclipse.php.internal.core.phpModel.parser.StateStack;
import java.util.*;

%%

%byaccj

%class PHPAspectLexer
%public
%unicode
%line

%implements PHPAspectParserTokens

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
%state ST_DOC_COMMENT
%state ST_ONE_LINE_COMMENT

%{
	private final List commentList = new LinkedList();
	private String heredoc = null;
    private boolean asp_tags = false;
    private boolean short_tags_allowed = true;
    private StateStack stack = new StateStack();
    //private char yy_old_buffer[] = new char[YY_BUFFERSIZE];
    private int yy_old_pushbackPos;
    private int commentStartPosition;
    private int lineno;
    
    public void resetCommentList() {
		commentList.clear();
	}
	
	public List getCommentList() {
		return commentList;
	}	
	
	private void addComment(int type) {
		int leftPosition = getTokenStartPosition();
		Comment comment = new Comment(commentStartPosition, leftPosition + getTokenLength(), type);
		commentList.add(comment);
	}	
	
	public void setUseAspTagsAsPhp(boolean useAspTagsAsPhp) {
		asp_tags = useAspTagsAsPhp;
	}
	
    private void pushState(int state) {
        stack.pushStack(YYINITIAL);
        yybegin(state);
    }

    private void popState() {
        yybegin(stack.popStack());
    }

    public int getCurrentLine() {
        return yyline;
    }

    private int getTokenStartPosition() {
        return zzStartRead - zzPushbackPos;
    }

    private int getTokenLength() {
    	return zzMarkedPos - zzStartRead;
    }

    public int getLength() {
        return zzEndRead - zzPushbackPos;
    }
    
    private void handleCommentStart() {
		commentStartPosition = getTokenStartPosition();
	}
	
	private void handleLineCommentEnd() {
         addComment(Comment.TYPE_SINGLE_LINE);
    }
    
    private void handleMultilineCommentEnd() {
    	addComment(Comment.TYPE_MULTILINE);
    }

    private void handlePHPDocEnd() {
		addComment(Comment.TYPE_PHPDOC);
    }
    
    private void handleVarComment() {
    	commentStartPosition = zzStartRead;
    	addComment(Comment.TYPE_MULTILINE);
    }
%}

LNUM=[0-9]+
DNUM=([0-9]*[\.][0-9]+)|([0-9]+[\.][0-9]*)
EXPONENT_DNUM=(({LNUM}|{DNUM})[eE][+-]?{LNUM})
HNUM="0x"[0-9a-fA-F]+
LABEL=[a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]*
WHITESPACE=[ \n\r\t]+
TABS_AND_SPACES=[ \t]*
ESCAPED_AND_WHITESPACE=[\n\t\r #'.:;,()|\^&+-/*=%!~<>?@]+
ANY_CHAR=(.|[\n])
NEWLINE=("\r"|"\n"|"\r\n")

%%

<ST_IN_SCRIPTING>"exit" {
	return T_EXIT;
}

<ST_IN_SCRIPTING>"die" {
	return T_EXIT;
}

<ST_IN_SCRIPTING>"function" {
	return T_FUNCTION;
}

<ST_IN_SCRIPTING>"const" {
	return T_CONST;
}

<ST_IN_SCRIPTING>"return" {
	return T_RETURN;
}

<ST_IN_SCRIPTING>"try" {
	return T_TRY;
}

<ST_IN_SCRIPTING>"catch" {
	return T_CATCH;
}

<ST_IN_SCRIPTING>"throw" {
	return T_THROW;
}

<ST_IN_SCRIPTING>"if" {
	return T_IF;
}

<ST_IN_SCRIPTING>"elseif" {
	return T_ELSEIF;
}

<ST_IN_SCRIPTING>"endif" {
	return T_ENDIF;
}

<ST_IN_SCRIPTING>"else" {
	return T_ELSE;
}

<ST_IN_SCRIPTING>"while" {
	return T_WHILE;
}

<ST_IN_SCRIPTING>"endwhile" {
	return T_ENDWHILE;
}

<ST_IN_SCRIPTING>"do" {
	return T_DO;
}

<ST_IN_SCRIPTING>"for" {
	return T_FOR;
}

<ST_IN_SCRIPTING>"endfor" {
	return T_ENDFOR;
}

<ST_IN_SCRIPTING>"foreach" {
	return T_FOREACH;
}

<ST_IN_SCRIPTING>"endforeach" {
	return T_ENDFOREACH;
}

<ST_IN_SCRIPTING>"declare" {
	return T_DECLARE;
}

<ST_IN_SCRIPTING>"enddeclare" {
	return T_ENDDECLARE;
}

<ST_IN_SCRIPTING>"instanceof" {
	return T_INSTANCEOF;
}

<ST_IN_SCRIPTING>"as" {
	return T_AS;
}

<ST_IN_SCRIPTING>"switch" {
	return T_SWITCH;
}

<ST_IN_SCRIPTING>"endswitch" {
	return T_ENDSWITCH;
}

<ST_IN_SCRIPTING>"case" {
	return T_CASE;
}

<ST_IN_SCRIPTING>"default" {
	return T_DEFAULT;
}

<ST_IN_SCRIPTING>"break" {
	return T_BREAK;
}

<ST_IN_SCRIPTING>"continue" {
	return T_CONTINUE;
}

<ST_IN_SCRIPTING>"echo" {
	return T_ECHO;
}

<ST_IN_SCRIPTING>"print" {
	return T_PRINT;
}

<ST_IN_SCRIPTING>"class" {
	return T_CLASS;
}

<ST_IN_SCRIPTING>"aspect" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_ASPECT;
}

<ST_IN_SCRIPTING>"before" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_BEFORE;
}

<ST_IN_SCRIPTING>"around" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_AROUND;
}

<ST_IN_SCRIPTING>"after" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_AFTER;
}

<ST_IN_SCRIPTING>"pointcut" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_POINTCUT;
}

<ST_IN_SCRIPTING>"exec" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_EXEC;
}

<ST_IN_SCRIPTING>"call" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_CALL;
}

<ST_IN_SCRIPTING>"write" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_SET;
}

<ST_IN_SCRIPTING>"read" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_GET;
}

<ST_IN_SCRIPTING>"this" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_THIS;
}

<ST_IN_SCRIPTING>"within" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_WITHIN;
}

<ST_IN_SCRIPTING>"file" {
        /* added by William Candillon <wcandillon@php.net> */
	return T_FILE;
}

<ST_IN_SCRIPTING>"interface" {
	return T_INTERFACE;
}

<ST_IN_SCRIPTING>"extends" {
	return T_EXTENDS;
}

<ST_IN_SCRIPTING>"implements" {
	return T_IMPLEMENTS;
}

<ST_IN_SCRIPTING,ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"->" {
	pushState(ST_LOOKING_FOR_PROPERTY);
	return T_OBJECT_OPERATOR;
}

<ST_LOOKING_FOR_PROPERTY>{LABEL} {
	popState();
	return T_STRING;
}

<ST_LOOKING_FOR_PROPERTY>{ANY_CHAR} {
    yypushback(yylength());
    popState();
}

<ST_IN_SCRIPTING>"::" {
	return T_PAAMAYIM_NEKUDOTAYIM;
}

<ST_IN_SCRIPTING>"new" {
	return T_NEW;
}

<ST_IN_SCRIPTING>"clone" {
	return T_CLONE;
}

<ST_IN_SCRIPTING>"var" {
	return T_VAR;
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("int"|"integer"){TABS_AND_SPACES}")" {
	return T_INT_CAST;
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("real"|"double"|"float"){TABS_AND_SPACES}")" {
	return T_DOUBLE_CAST;
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"string"{TABS_AND_SPACES}")" {
	return T_STRING_CAST;
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"array"{TABS_AND_SPACES}")" {
	return T_ARRAY_CAST;
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"object"{TABS_AND_SPACES}")" {
	return T_OBJECT_CAST;
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("bool"|"boolean"){TABS_AND_SPACES}")" {
	return T_BOOL_CAST;
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("unset"){TABS_AND_SPACES}")" {
	return T_UNSET_CAST;
}

<ST_IN_SCRIPTING>"eval" {
	return T_EVAL;
}

<ST_IN_SCRIPTING>"include" {
	return T_INCLUDE;
}

<ST_IN_SCRIPTING>"include_once" {
	return T_INCLUDE_ONCE;
}

<ST_IN_SCRIPTING>"require" {
	return T_REQUIRE;
}

<ST_IN_SCRIPTING>"require_once" {
	return T_REQUIRE_ONCE;
}

<ST_IN_SCRIPTING>"use" {
	return T_USE;
}

<ST_IN_SCRIPTING>"global" {
	return T_GLOBAL;
}

<ST_IN_SCRIPTING>"isset" {
	return T_ISSET;
}

<ST_IN_SCRIPTING>"empty" {
	return T_EMPTY;
}

<ST_IN_SCRIPTING>"__halt_compiler" {
	return T_HALT_COMPILER;
}

<ST_IN_SCRIPTING>"static" {
	return T_STATIC;
}

<ST_IN_SCRIPTING>"abstract" {
	return T_ABSTRACT;
}

<ST_IN_SCRIPTING>"final" {
	return T_FINAL;
}

<ST_IN_SCRIPTING>"private" {
	return T_PRIVATE;
}

<ST_IN_SCRIPTING>"protected" {
	return T_PROTECTED;
}

<ST_IN_SCRIPTING>"public" {
	return T_PUBLIC;
}

<ST_IN_SCRIPTING>"unset" {
	return T_UNSET;
}

<ST_IN_SCRIPTING>"=>" {
	return T_DOUBLE_ARROW;
}

<ST_IN_SCRIPTING>"list" {
	return T_LIST;
}

<ST_IN_SCRIPTING>"array" {
	return T_ARRAY;
}

<ST_IN_SCRIPTING>"++" {
	return T_INC;
}

<ST_IN_SCRIPTING>"--" {
	return T_DEC;
}

<ST_IN_SCRIPTING>"===" {
	return T_IS_IDENTICAL;
}

<ST_IN_SCRIPTING>"!==" {
	return T_IS_NOT_IDENTICAL;
}

<ST_IN_SCRIPTING>"==" {
	return T_IS_EQUAL;
}

<ST_IN_SCRIPTING>"!="|"<>" {
	return T_IS_NOT_EQUAL;
}

<ST_IN_SCRIPTING>"<=" {
	return T_IS_SMALLER_OR_EQUAL;
}

<ST_IN_SCRIPTING>">=" {
	return T_IS_GREATER_OR_EQUAL;
}

<ST_IN_SCRIPTING>"+=" {
	return T_PLUS_EQUAL;
}

<ST_IN_SCRIPTING>"-=" {
	return T_MINUS_EQUAL;
}

<ST_IN_SCRIPTING>"*=" {
	return T_MUL_EQUAL;
}

<ST_IN_SCRIPTING>"/=" {
	return T_DIV_EQUAL;
}

<ST_IN_SCRIPTING>".=" {
	return T_CONCAT_EQUAL;
}

<ST_IN_SCRIPTING>"%=" {
	return T_MOD_EQUAL;
}

<ST_IN_SCRIPTING>"<<=" {
	return T_SL_EQUAL;
}

<ST_IN_SCRIPTING>">>=" {
	return T_SR_EQUAL;
}

<ST_IN_SCRIPTING>"&=" {
	return T_AND_EQUAL;
}

<ST_IN_SCRIPTING>"|=" {
	return T_OR_EQUAL;
}

<ST_IN_SCRIPTING>"^=" {
	return T_XOR_EQUAL;
}

<ST_IN_SCRIPTING>"||" {
	return T_BOOLEAN_OR;
}

<ST_IN_SCRIPTING>"&&" {
	return T_BOOLEAN_AND;
}

<ST_IN_SCRIPTING>"0R" {
	return T_LOGICAL_OR;
}

<ST_IN_SCRIPTING>"AND" {
	return T_LOGICAL_AND;
}

<ST_IN_SCRIPTING>"XOR" {
	return T_LOGICAL_XOR;
}

<ST_IN_SCRIPTING>"<<" {
	return T_SL;
}

<ST_IN_SCRIPTING>">>" {
	return T_SR;
}

// TOKENS
<ST_IN_SCRIPTING> {
    ";"                     {return yytext().charAt(0);}
    ":"                     {return yytext().charAt(0);}
    ","                     {return yytext().charAt(0);}
    "."                     {return yytext().charAt(0);}
    "["                     {return yytext().charAt(0);}
    "]"                     {return yytext().charAt(0);}
    "("                     {return yytext().charAt(0);}
    ")"                     {return yytext().charAt(0);}
    "|"                     {return yytext().charAt(0);}
    "^"                     {return yytext().charAt(0);}
    "&"                     {return yytext().charAt(0);}
    "+"                     {return yytext().charAt(0);}
    "-"                     {return yytext().charAt(0);}
    "/"                     {return yytext().charAt(0);}
    "*"                     {return yytext().charAt(0);}
    "="                     {return yytext().charAt(0);}
    "%"                     {return yytext().charAt(0);}
    "!"                     {return yytext().charAt(0);}
    "~"                     {return yytext().charAt(0);}
    "$"                     {return yytext().charAt(0);}
    "<"                     {return yytext().charAt(0);}
    ">"                     {return yytext().charAt(0);}
    "?"                     {return yytext().charAt(0);}
    "@"                     {return yytext().charAt(0);}
}


<ST_IN_SCRIPTING>"{" {
	pushState(ST_IN_SCRIPTING);
	return '{';
}


<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"${" {
	pushState(ST_LOOKING_FOR_VARNAME);
	return T_DOLLAR_OPEN_CURLY_BRACES;
}


<ST_IN_SCRIPTING>"}" {
	/* This is a temporary fix which is dependant on flex and it's implementation */
    if (!stack.isEmpty()) {
        popState();
    }
	return '}';
}


<ST_LOOKING_FOR_VARNAME>{LABEL} {
	popState();
	pushState(ST_IN_SCRIPTING);
	return T_STRING_VARNAME;
}


<ST_LOOKING_FOR_VARNAME>{ANY_CHAR} {
	yypushback(yylength());
	popState();
	pushState(ST_IN_SCRIPTING);
}


<ST_IN_SCRIPTING>{LNUM} {
	return T_LNUMBER;
}

<ST_IN_SCRIPTING>{HNUM} {
	return T_LNUMBER;
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>{LNUM}|{HNUM} { /* treat numbers (almost) as strings inside encapsulated strings */
	return T_NUM_STRING;
}

<ST_IN_SCRIPTING>{DNUM}|{EXPONENT_DNUM} {
	return T_DNUMBER;
}

<ST_IN_SCRIPTING>"__CLASS__" {
	return T_CLASS_C;
}

<ST_IN_SCRIPTING>"__FUNCTION__" {
	return T_FUNC_C;
}

<ST_IN_SCRIPTING>"__METHOD__" {
	return T_METHOD_C;
}

<ST_IN_SCRIPTING>"__LINE__" {
	return T_LINE;
}

<ST_IN_SCRIPTING>"__FILE__" {
	return T_FILE;
}

<YYINITIAL>(([^<]|"<"[^?%s<]){1,400})|"<s"|"<" {
	return T_INLINE_HTML;
}

<YYINITIAL>"<?"|"<script"{WHITESPACE}+"language"{WHITESPACE}*"="{WHITESPACE}*("php"|"\"php\""|"\'php\'"){WHITESPACE}*">" {
	if (short_tags_allowed || yylength()>2) { /* yylength()>2 means it's not <? but <script> */
		/* no copying - intentional */
		yybegin(ST_IN_SCRIPTING);
		//return T_OPEN_TAG;
	} else {
		return T_INLINE_HTML;
	}
}

<YYINITIAL>"<%="|"<?=" {
	if ((yytext().charAt(1)=='%' && asp_tags) || (yytext().charAt(1)=='?' && short_tags_allowed)) {
		yybegin(ST_IN_SCRIPTING);
		//return T_OPEN_TAG_WITH_ECHO;
		//return T_ECHO;
	} else {
		return T_INLINE_HTML;
	}
}


<YYINITIAL>"<%" {
	if(asp_tags){
		yybegin(ST_IN_SCRIPTING);
		//return T_OPEN_TAG;
	} else {
		return T_INLINE_HTML;
	}
}

<YYINITIAL>"<?php"([ \t]|{NEWLINE}) {
	yybegin(ST_IN_SCRIPTING);
	//return T_OPEN_TAG;
}

<ST_IN_SCRIPTING,ST_DOUBLE_QUOTES,ST_HEREDOC,ST_BACKQUOTE>"$"{LABEL} {
	return T_VARIABLE;
}

<ST_IN_SCRIPTING>{LABEL} {
	return T_STRING;
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>{LABEL} {
	return T_STRING;
}


<ST_IN_SCRIPTING>{WHITESPACE} {
	//return T_WHITESPACE;
}


<ST_IN_SCRIPTING>"#"|"//" {
	yybegin(ST_ONE_LINE_COMMENT);
	//yymore();
}

<ST_ONE_LINE_COMMENT>"?"|"%"|">" {
	//yymore();
}

<ST_ONE_LINE_COMMENT>[^\n\r?%>]+{ANY_CHAR} {
	String yytext = yytext();
	switch (yytext.charAt(yytext.length() - 1)) {
		case '?':
		case '%':
		case '>':
			yypushback(1);
			break;
		default:
			handleLineCommentEnd();
			yybegin(ST_IN_SCRIPTING);
	}
//	yymore();
}

<ST_ONE_LINE_COMMENT>{NEWLINE} {
	yybegin(ST_IN_SCRIPTING);
	lineno++;
	//return T_COMMENT;
}

<ST_ONE_LINE_COMMENT>"?>"|"%>" {
    if (asp_tags || yytext().charAt(0)!='%') { /* asp comment? */
	    handleLineCommentEnd();
        yypushback(yylength());
		yybegin(ST_IN_SCRIPTING);
		//return T_COMMENT;
	} 
}

<ST_IN_SCRIPTING>"/**"{WHITESPACE} {
	commentStartPosition = lineno;
	yybegin(ST_DOC_COMMENT);
	//yymore();
}

<ST_IN_SCRIPTING>"/*" {
	commentStartPosition = lineno;
	yybegin(ST_COMMENT);
	//yymore();
}


<ST_COMMENT,ST_DOC_COMMENT>[^*]+ {
	//yymore();
}

<ST_DOC_COMMENT>"*/" {
	yybegin(ST_IN_SCRIPTING);
	return T_DOC_COMMENT;
}

<ST_COMMENT>"*/" {
	yybegin(ST_IN_SCRIPTING);
	//return T_COMMENT;
}

<ST_COMMENT,ST_DOC_COMMENT>"*" {
	//yymore();
}

<ST_IN_SCRIPTING>("?>"|"</script"{WHITESPACE}*">"){NEWLINE}? {
	yybegin(YYINITIAL);
	//return T_CLOSE_TAG;  /* implicit ';' at php-end tag */
}


<ST_IN_SCRIPTING>"%>"{NEWLINE}? {
	if (asp_tags) {
		yybegin(YYINITIAL);
		//return T_CLOSE_TAG;  /* implicit ';' at php-end tag */
	} else {
		return yytext().charAt(0);
	}
}


<ST_IN_SCRIPTING>([\"]([^$\"\\]|("\\".))*[\"]) {
	/*register char *s, *t;
	char *end;
	 convert escape sequences 
	s = t = estrndup(yytext+1, yylength()-2);;
	end = s+yylength()-2;*/
	return T_CONSTANT_ENCAPSED_STRING;
}


<ST_IN_SCRIPTING>([']([^'\\]|("\\".))*[']) {

	/*register char *s, *t;
	char *end;


	 convert escape sequences 
	s = t = estrndup(yytext+1, yylength()-2);
	end = s+yylength()-2;
	while (s<end) {
		if (*s=='\\') {
			s++;
			if (s>=end) {
				continue;
			}
			switch(*s) {
				case '\\':
				case '\'':
					*t++ = *s;
					end--;
					break;
				default:
					*t++ = '\\';
					*t++ = *s;
					break;
			}
			s++;
		} else {
			*t++ = *s++;
		}
	}
	*t = 0;
*/
	return T_CONSTANT_ENCAPSED_STRING;
}


<ST_IN_SCRIPTING>[\"] {
	yybegin(ST_DOUBLE_QUOTES);
	return '\"';
}


<ST_IN_SCRIPTING>"<<<"{TABS_AND_SPACES}{LABEL}{NEWLINE} {
	yybegin(ST_HEREDOC);
	return T_START_HEREDOC;
}


<ST_IN_SCRIPTING>[`] {
	yybegin(ST_BACKQUOTE);
	return '`';
}


<ST_IN_SCRIPTING>['] {
	yybegin(ST_SINGLE_QUOTE);
	return '\'';
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
        return T_END_HEREDOC;
    } else {
        return T_STRING;
    }
}


<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>{ESCAPED_AND_WHITESPACE} {
	return T_ENCAPSED_AND_WHITESPACE;
}

<ST_SINGLE_QUOTE>([^'\\]|\\[^'\\])+ {
	return T_ENCAPSED_AND_WHITESPACE;
}

<ST_DOUBLE_QUOTES>[`]+ {
	return T_ENCAPSED_AND_WHITESPACE;
}


<ST_BACKQUOTE>[\"]+ {
	return T_ENCAPSED_AND_WHITESPACE;
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"$"[^a-zA-Z_\x7f-\xff{] {
    if (yylength() == 2) {
        yypushback(1);
    }
	return T_CHARACTER;
}

// ENCAPSED_TOKENS
<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC> {
    "["     {return yytext().charAt(0);}

    "]"     {return yytext().charAt(0);}

    "$"     {return yytext().charAt(0);}
    
    "{"     {return yytext().charAt(0);}
    
    "}"     {return yytext().charAt(0);}
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\{" {
	return T_STRING;
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"{$" {
	pushState(ST_IN_SCRIPTING);
	yypushback(yylength()-1);
	return T_CURLY_OPEN;
}


<ST_SINGLE_QUOTE>"\\'" {
	return T_CHARACTER;
}

<ST_SINGLE_QUOTE>"\\\\" {
	return T_CHARACTER;
}

<ST_DOUBLE_QUOTES>"\\\"" {
	return T_CHARACTER;
}

<ST_BACKQUOTE>"\\`" {
	return T_CHARACTER;
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\"[0-7]{1,3} {
	return T_CHARACTER;
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\x"[0-9A-Fa-f]{1,2} {
	return T_CHARACTER;
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"\\"{ANY_CHAR} {
	switch (yytext().charAt(1)) {
		case 'n': break;
		case 't': break;
		case 'r': break;
		case '\\': break;
		case '$': break;
		default:
			return T_BAD_CHARACTER;
			//break;
	}
	return T_CHARACTER;
}


<ST_HEREDOC>[\"'`]+ {
	return T_ENCAPSED_AND_WHITESPACE;
}


<ST_DOUBLE_QUOTES>[\"] {
	yybegin(ST_IN_SCRIPTING);
	return '\"';
}


<ST_BACKQUOTE>[`] {
	yybegin(ST_IN_SCRIPTING);
	return '`';
}


<ST_SINGLE_QUOTE>['] {
	yybegin(ST_IN_SCRIPTING);
	return '\'';
}


<ST_DOUBLE_QUOTES,ST_BACKQUOTE,YYINITIAL,ST_IN_SCRIPTING,ST_LOOKING_FOR_PROPERTY><<EOF>> {
	return 0;
}

<ST_COMMENT,ST_DOC_COMMENT><<EOF>> {
	System.out.println("Unterminated comment starting line "+commentStartPosition);
	return 0;
}

<ST_IN_SCRIPTING,YYINITIAL,ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_SINGLE_QUOTE,ST_HEREDOC>{ANY_CHAR} {
	System.out.println("Unexpected character");
	//System.out.println("Unexpected character in input:  '%c' (ASCII=%d) state=%d", yytext().charAt(0), yytext().charAt(0), YYSTATE);
}
