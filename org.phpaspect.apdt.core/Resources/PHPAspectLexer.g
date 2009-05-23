lexer grammar PHPAspectLexer;

options {
superClass='APDTLexer';
}

@header {
package org.phpaspect.apdt.internal.core.parser.antlr;
}

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
AT : '@'   ;
CALL : 'call';
NEW : 'new';
EXEC : 'exec';
PAAMAYIM_NEKUDOTAYIM : '::';
OBJECT_OPERATOR : '->';
AND : '&&';
OR : '||';
NOT : '!';
PLUS : '+';
BEFORE : 'Before';
AROUND : 'Around';
AFTER : 'After';
MIXIN : 'Mixin';

//[a-zA-Z_\x7f-\xff][a-zA-Z0-9_\x7f-\xff]*
LABEL
  : ('a'..'z'|'A'..'Z'|'_'|'*')('*'|'a'..'z'|'A'..'Z'|'_'|'0'..'9')*
  ;