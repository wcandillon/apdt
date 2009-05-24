parser grammar PHPAspectParser;

options {
output = AST;
ASTLabelType=PHPAspectCommonTree;
TokenLabelType=CommonToken;
superClass='APDTParser';
tokenVocab=PHPAspectLexer;
}

tokens {
ANNOTATION;
PARENTHESE;
MIXIN;
}

@header {
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
: pt1=and_joinpoint (OR^ pt2=and_joinpoint)*
;

and_joinpoint
: pt1=not_joinpoint (AND^ pt2=not_joinpoint)*
;

not_joinpoint
: (NOT^)? joinpoint
;

//[2]
joinpoint
: CALL LPARENTHESE type=LABEL resolution method=LABEL RPARENTHESE
	-> ^(CALL $type resolution $method)
| NEW LPARENTHESE type=LABEL RPARENTHESE
	-> ^(NEW $type)
| FILE LPARENTHESE string=STRING RPARENTHESE
	-> ^(FILE $string)
| WITHIN LPARENTHESE type=LABEL RPARENTHESE
	-> ^(WITHIN $type)
| EXEC LPARENTHESE type=LABEL PAAMAYIM_NEKUDOTAYIM method=LABEL RPARENTHESE
	-> ^(EXEC $type $method)
| LPARENTHESE or_joinpoint RPARENTHESE
	-> ^(PARENTHESE or_joinpoint)
| MIXIN LPARENTHESE type=LABEL LPARENTHESE
	-> ^(MIXIN type)
;

resolution
: PAAMAYIM_NEKUDOTAYIM
| OBJECT_OPERATOR
;