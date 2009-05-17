grammar PHPAspect;

options {
output = AST;
ASTLabelType=PHPAspectCommonTree;
TokenLabelType=CommonToken;
}

tokens {
ANNOTATION;
ADVICE;
OPERATOR;
PARENTHESE;
NOT;
OR;
AND;
JOINPOINT;
}

@header {
package org.phpaspect.apdt.internal.core.parser.antlr;
}

@lexer::header {
package org.phpaspect.apdt.internal.core.parser.antlr;
}

//[1]
annotation
  : AT advice LPARENTHESE pointcut RPARENTHESE
  	-> ^(ANNOTATION advice pointcut)
  ;
  
advice
  : BEFORE
  | AROUND
  | AFTER
  ;
 
//[2]
pointcut
 : or_joinpoint
 ;
 
or_joinpoint
 : pt1=and_joinpoint (OR pt2=and_joinpoint -> ^(OR $pt1 $pt2))*
 ;

and_joinpoint
 : pt1=not_joinpoint (AND pt2=not_joinpoint -> ^(AND $pt1 $pt2))*
 ;
 
not_joinpoint
 :  (not=NOT)? joinpoint -> ^(JOINPOINT joinpoint $not)
 ;
 
//[2]
joinpoint
 : CALL LPARENTHESE type=LABEL resolution method=LABEL RPARENTHESE
	-> ^(CALL $type resolution $method)
 | LPARENTHESE or_joinpoint RPARENTHESE
	-> ^(PARENTHESE or_joinpoint)
 ;
 
resolution
  : PAAMAYIM_NEKUDOTAYIM
  | OBJECT_OPERATOR
  ;

// Literals
DIGIT
  : '0'..'9'
  ;

INTLIT 
  : (DIGIT)+
  ;
  
CHARLIT
  : '\''! . '\''!
  ;

// string literals
STRING_LITERAL
  : '"'!
    ( '"' '"'!
    | ~('"'|'\n'|'\r')
    )*
    ( '"'!
    | // nothing -- write error message
    )
   ;

WS: (' '|'\n'|'\r'|'\t')+ {$channel=HIDDEN;} ; // ignore whitespace 
  
// Operators
DOT        : '.'   ;
LPARENTHESE     : '('   ;
RPARENTHESE     : ')'   ;
AT    : '@'   ;
CALL       : 'call';
PAAMAYIM_NEKUDOTAYIM : '::';
OBJECT_OPERATOR : '->';
AND : '&&';
OR : '||';
NOT : '!';
BEFORE : 'Before';
AROUND : 'Around';
AFTER : 'After';

//[a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]*
LABEL
  : ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
  ;
