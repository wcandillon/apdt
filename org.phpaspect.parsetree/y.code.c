//### This file created by BYACC 1.8(/Java extension  1.14)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 1 "php_parser.y"

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

/* $Id: php_parser.y,v 1.1 2007/01/19 21:41:16 wcandillon Exp $ */

/* 
 * LALR shift/reduce conflicts and how they are resolved:
 *
 * - 2 shift/reduce conflicts due to the dangeling elseif/else ambiguity.  Solved by shift.
 * - 1 shift/reduce conflict due to arrays within encapsulated strings. Solved by shift. 
 * - 1 shift/reduce conflict due to objects within encapsulated strings.  Solved by shift.
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
//#line 54 "y.code.c"




public class PHPAspectParserTable
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class PHPAspectParserTableVal is defined in PHPAspectParserTableVal.java


String   yytext;//user variable to return contextual strings
PHPAspectParserTableVal yyval; //used to return semantic vals from action routines
PHPAspectParserTableVal yylval;//the 'lval' (result) I got from yylex()
PHPAspectParserTableVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new PHPAspectParserTableVal[YYSTACKSIZE];
  yyval=new PHPAspectParserTableVal();
  yylval=new PHPAspectParserTableVal();
  valptr=-1;
}
void val_push(PHPAspectParserTableVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
PHPAspectParserTableVal val_pop()
{
  if (valptr<0)
    return new PHPAspectParserTableVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
PHPAspectParserTableVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new PHPAspectParserTableVal();
  return valstk[ptr];
}
//#### end semantic value section ####
public final static short T_INCLUDE=257;
public final static short T_INCLUDE_ONCE=258;
public final static short T_EVAL=259;
public final static short T_REQUIRE=260;
public final static short T_REQUIRE_ONCE=261;
public final static short T_LOGICAL_OR=262;
public final static short T_LOGICAL_XOR=263;
public final static short T_LOGICAL_AND=264;
public final static short T_PRINT=265;
public final static short T_PLUS_EQUAL=266;
public final static short T_MINUS_EQUAL=267;
public final static short T_MUL_EQUAL=268;
public final static short T_DIV_EQUAL=269;
public final static short T_CONCAT_EQUAL=270;
public final static short T_MOD_EQUAL=271;
public final static short T_AND_EQUAL=272;
public final static short T_OR_EQUAL=273;
public final static short T_XOR_EQUAL=274;
public final static short T_SL_EQUAL=275;
public final static short T_SR_EQUAL=276;
public final static short T_BOOLEAN_OR=277;
public final static short T_BOOLEAN_AND=278;
public final static short T_IS_EQUAL=279;
public final static short T_IS_NOT_EQUAL=280;
public final static short T_IS_IDENTICAL=281;
public final static short T_IS_NOT_IDENTICAL=282;
public final static short T_IS_SMALLER_OR_EQUAL=283;
public final static short T_IS_GREATER_OR_EQUAL=284;
public final static short T_SL=285;
public final static short T_SR=286;
public final static short T_INSTANCEOF=287;
public final static short T_INC=288;
public final static short T_DEC=289;
public final static short T_INT_CAST=290;
public final static short T_DOUBLE_CAST=291;
public final static short T_STRING_CAST=292;
public final static short T_ARRAY_CAST=293;
public final static short T_OBJECT_CAST=294;
public final static short T_BOOL_CAST=295;
public final static short T_UNSET_CAST=296;
public final static short T_NEW=297;
public final static short T_CLONE=298;
public final static short T_EXIT=299;
public final static short T_IF=300;
public final static short T_ELSEIF=301;
public final static short T_ELSE=302;
public final static short T_ENDIF=303;
public final static short T_LNUMBER=304;
public final static short T_DNUMBER=305;
public final static short T_STRING=306;
public final static short T_STRING_VARNAME=307;
public final static short T_VARIABLE=308;
public final static short T_NUM_STRING=309;
public final static short T_INLINE_HTML=310;
public final static short T_CHARACTER=311;
public final static short T_BAD_CHARACTER=312;
public final static short T_ENCAPSED_AND_WHITESPACE=313;
public final static short T_CONSTANT_ENCAPSED_STRING=314;
public final static short T_ECHO=315;
public final static short T_DO=316;
public final static short T_WHILE=317;
public final static short T_ENDWHILE=318;
public final static short T_FOR=319;
public final static short T_ENDFOR=320;
public final static short T_FOREACH=321;
public final static short T_ENDFOREACH=322;
public final static short T_DECLARE=323;
public final static short T_ENDDECLARE=324;
public final static short T_AS=325;
public final static short T_SWITCH=326;
public final static short T_ENDSWITCH=327;
public final static short T_CASE=328;
public final static short T_DEFAULT=329;
public final static short T_BREAK=330;
public final static short T_CONTINUE=331;
public final static short T_FUNCTION=332;
public final static short T_CONST=333;
public final static short T_RETURN=334;
public final static short T_TRY=335;
public final static short T_CATCH=336;
public final static short T_THROW=337;
public final static short T_USE=338;
public final static short T_GLOBAL=339;
public final static short T_STATIC=340;
public final static short T_ABSTRACT=341;
public final static short T_FINAL=342;
public final static short T_PRIVATE=343;
public final static short T_PROTECTED=344;
public final static short T_PUBLIC=345;
public final static short T_VAR=346;
public final static short T_UNSET=347;
public final static short T_ISSET=348;
public final static short T_EMPTY=349;
public final static short T_HALT_COMPILER=350;
public final static short T_CLASS=351;
public final static short T_INTERFACE=352;
public final static short T_EXTENDS=353;
public final static short T_IMPLEMENTS=354;
public final static short T_OBJECT_OPERATOR=355;
public final static short T_DOUBLE_ARROW=356;
public final static short T_LIST=357;
public final static short T_ARRAY=358;
public final static short T_CLASS_C=359;
public final static short T_METHOD_C=360;
public final static short T_FUNC_C=361;
public final static short T_LINE=362;
public final static short T_FILE=363;
public final static short T_COMMENT=364;
public final static short T_DOC_COMMENT=365;
public final static short T_OPEN_TAG=366;
public final static short T_OPEN_TAG_WITH_ECHO=367;
public final static short T_CLOSE_TAG=368;
public final static short T_WHITESPACE=369;
public final static short T_START_HEREDOC=370;
public final static short T_END_HEREDOC=371;
public final static short T_DOLLAR_OPEN_CURLY_BRACES=372;
public final static short T_CURLY_OPEN=373;
public final static short T_PAAMAYIM_NEKUDOTAYIM=374;
public final static short T_ASPECT=375;
public final static short T_BEFORE=376;
public final static short T_AROUND=377;
public final static short T_AFTER=378;
public final static short T_POINTCUT=379;
public final static short T_EXEC=380;
public final static short T_CALL=381;
public final static short T_SET=382;
public final static short T_GET=383;
public final static short T_THIS=384;
public final static short T_WITHIN=385;
public final static short YYERRCODE=256;
final static int YYTABLESIZE=11115;
final static short YYFINAL=1;
final static short YYMAXTOKEN=385;
extern short yylhs[];
extern short yylen[];
extern short yydefred[];
extern short yydgoto[];
extern short yysindex[];
extern short yyrindex[];
extern short yygindex[];
extern short yytable[];
extern short yycheck[];
#if YYDEBUG
extern char *yyname[];
extern char *yyrule[];
#endif
//#line 1029 "php_parser.y"


private static void yy_check() {
	try {
		FileReader fileReader = new FileReader("/home/wcandillon/apdt/workspace/org.phpaspect.parsetree/src/org/phpaspect/parsetree/PHPAspectParser.java");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		String table = null;
		while(line != null){
			if(line.equals("/*yycheck = new short[] {")){
				line = bufferedReader.readLine();
				while(!line.equals("};")){
					table += line;
					line = bufferedReader.readLine();
				}
				String[] values = table.split(",");
				yycheck = new short[values.length];
				for(int i=0; i<values.length; i++){
					yycheck[i] = Short.parseShort(values[i].trim());
 				}
				return;
			}
		}
	} catch (FileNotFoundException e) {
		System.err.println("File not found");
	} catch (IOException e) {
		System.err.println("I/O error");
	}
}

private void yyerror(String string) {
	System.out.println(string);
}

/* a reference to the lexer object */
private PHPAspectLexer lexer;

/* interface to the lexer */
private int yylex () {
  int yyl_return = -1;
  try {
    yyl_return = lexer.yylex();
  }
  catch (IOException e) {
    System.err.println("IO error :"+e);
  }
  return yyl_return;
}

/* lexer is created in the constructor */
public PHPAspectParser(Reader r) {
  lexer = new PHPAspectLexer(r);
}
//#line 330 "y.code.c"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 2:
//#line 154 "php_parser.y"
{ zend_do_extended_info(TSRMLS_C); }
break;
case 3:
//#line 154 "php_parser.y"
{ HANDLE_INTERACTIVE(); }
break;
case 6:
//#line 161 "php_parser.y"
{ zend_do_early_binding(TSRMLS_C); }
break;
case 7:
//#line 162 "php_parser.y"
{ zend_do_early_binding(TSRMLS_C); }
break;
case 8:
//#line 163 "php_parser.y"
{ REGISTER_MAIN_LONG_CONSTANT("__COMPILER_HALT_OFFSET__", zend_get_scanned_file_offset(TSRMLS_C), CONST_CS); YYACCEPT; }
break;
case 11:
//#line 172 "php_parser.y"
{ zend_do_extended_info(TSRMLS_C); }
break;
case 12:
//#line 172 "php_parser.y"
{ HANDLE_INTERACTIVE(); }
break;
case 17:
//#line 181 "php_parser.y"
{ zend_error(E_COMPILE_ERROR, "__HALT_COMPILER() can only be used from the outermost scope"); }
break;
case 18:
//#line 186 "php_parser.y"
{ zend_do_ticks(TSRMLS_C); }
break;
case 20:
//#line 191 "php_parser.y"
{ zend_do_if_cond(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 21:
//#line 191 "php_parser.y"
{ zend_do_if_after_statement(&val_peek(2), 1 TSRMLS_CC); }
break;
case 22:
//#line 191 "php_parser.y"
{ zend_do_if_end(TSRMLS_C); }
break;
case 23:
//#line 192 "php_parser.y"
{ zend_do_if_cond(&val_peek(2), &val_peek(1) TSRMLS_CC); }
break;
case 24:
//#line 192 "php_parser.y"
{ zend_do_if_after_statement(&val_peek(3), 1 TSRMLS_CC); }
break;
case 25:
//#line 192 "php_parser.y"
{ zend_do_if_end(TSRMLS_C); }
break;
case 26:
//#line 193 "php_parser.y"
{ val_peek(1).u.opline_num = get_next_op_number(CG(active_op_array));  }
break;
case 27:
//#line 193 "php_parser.y"
{ zend_do_while_cond(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 28:
//#line 193 "php_parser.y"
{ zend_do_while_end(&val_peek(6), &val_peek(2) TSRMLS_CC); }
break;
case 29:
//#line 194 "php_parser.y"
{ val_peek(0).u.opline_num = get_next_op_number(CG(active_op_array));  zend_do_do_while_begin(TSRMLS_C); }
break;
case 30:
//#line 194 "php_parser.y"
{ val_peek(0).u.opline_num = get_next_op_number(CG(active_op_array)); }
break;
case 31:
//#line 194 "php_parser.y"
{ zend_do_do_while_end(&val_peek(8), &val_peek(4), &val_peek(2) TSRMLS_CC); }
break;
case 32:
//#line 198 "php_parser.y"
{ zend_do_free(&val_peek(1) TSRMLS_CC); val_peek(0).u.opline_num = get_next_op_number(CG(active_op_array)); }
break;
case 33:
//#line 200 "php_parser.y"
{ zend_do_extended_info(TSRMLS_C); zend_do_for_cond(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 34:
//#line 202 "php_parser.y"
{ zend_do_free(&val_peek(1) TSRMLS_CC); zend_do_for_before_statement(&val_peek(6), &val_peek(3) TSRMLS_CC); }
break;
case 35:
//#line 203 "php_parser.y"
{ zend_do_for_end(&val_peek(5) TSRMLS_CC); }
break;
case 36:
//#line 204 "php_parser.y"
{ zend_do_switch_cond(&val_peek(1) TSRMLS_CC); }
break;
case 37:
//#line 204 "php_parser.y"
{ zend_do_switch_end(&val_peek(0) TSRMLS_CC); }
break;
case 38:
//#line 205 "php_parser.y"
{ zend_do_brk_cont(ZEND_BRK, NULL TSRMLS_CC); }
break;
case 39:
//#line 206 "php_parser.y"
{ zend_do_brk_cont(ZEND_BRK, &val_peek(1) TSRMLS_CC); }
break;
case 40:
//#line 207 "php_parser.y"
{ zend_do_brk_cont(ZEND_CONT, NULL TSRMLS_CC); }
break;
case 41:
//#line 208 "php_parser.y"
{ zend_do_brk_cont(ZEND_CONT, &val_peek(1) TSRMLS_CC); }
break;
case 42:
//#line 209 "php_parser.y"
{ zend_do_return(NULL, 0 TSRMLS_CC); }
break;
case 43:
//#line 210 "php_parser.y"
{ zend_do_return(&val_peek(1), 0 TSRMLS_CC); }
break;
case 44:
//#line 211 "php_parser.y"
{ zend_do_return(&val_peek(1), 1 TSRMLS_CC); }
break;
case 48:
//#line 215 "php_parser.y"
{ zend_do_echo(&val_peek(0) TSRMLS_CC); }
break;
case 49:
//#line 216 "php_parser.y"
{ zend_do_free(&val_peek(1) TSRMLS_CC); }
break;
case 50:
//#line 217 "php_parser.y"
{ zend_error(E_COMPILE_ERROR,"use: Not yet supported. Please use include_once() or require_once()");  zval_dtor(&val_peek(1).u.constant); }
break;
case 52:
//#line 219 "php_parser.y"
{ zend_do_foreach_begin(&val_peek(2), &val_peek(1), &val_peek(0), 1 TSRMLS_CC); }
break;
case 53:
//#line 220 "php_parser.y"
{ zend_do_foreach_fetch(&val_peek(4), &val_peek(3), &val_peek(0) TSRMLS_CC); }
break;
case 54:
//#line 221 "php_parser.y"
{ zend_do_foreach_cont(&val_peek(8), &val_peek(4), &val_peek(2), &val_peek(1) TSRMLS_CC); }
break;
case 55:
//#line 222 "php_parser.y"
{ zend_do_foreach_end(&val_peek(10), &val_peek(6) TSRMLS_CC); }
break;
case 56:
//#line 223 "php_parser.y"
{ zend_do_foreach_begin(&val_peek(2), &val_peek(1), &val_peek(0), 0 TSRMLS_CC); }
break;
case 57:
//#line 224 "php_parser.y"
{ zend_do_foreach_fetch(&val_peek(4), &val_peek(3), &val_peek(0) TSRMLS_CC); }
break;
case 58:
//#line 225 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_foreach_cont(&val_peek(8), &val_peek(4), &val_peek(2), &val_peek(1) TSRMLS_CC); }
break;
case 59:
//#line 226 "php_parser.y"
{ zend_do_foreach_end(&val_peek(10), &val_peek(6) TSRMLS_CC); }
break;
case 60:
//#line 227 "php_parser.y"
{ val_peek(0).u.opline_num = get_next_op_number(CG(active_op_array)); zend_do_declare_begin(TSRMLS_C); }
break;
case 61:
//#line 227 "php_parser.y"
{ zend_do_declare_end(&val_peek(5) TSRMLS_CC); }
break;
case 63:
//#line 229 "php_parser.y"
{ zend_do_try(&val_peek(0) TSRMLS_CC); }
break;
case 64:
//#line 230 "php_parser.y"
{ zend_initialize_try_catch_element(&val_peek(6) TSRMLS_CC); }
break;
case 65:
//#line 231 "php_parser.y"
{ zend_do_first_catch(&val_peek(2) TSRMLS_CC); }
break;
case 66:
//#line 232 "php_parser.y"
{ zend_do_begin_catch(&val_peek(11), &val_peek(3), &val_peek(1), 1 TSRMLS_CC); }
break;
case 67:
//#line 233 "php_parser.y"
{ zend_do_end_catch(&val_peek(15) TSRMLS_CC); }
break;
case 68:
//#line 234 "php_parser.y"
{ zend_do_mark_last_catch(&val_peek(11), &val_peek(0) TSRMLS_CC); }
break;
case 69:
//#line 235 "php_parser.y"
{ zend_do_throw(&val_peek(1) TSRMLS_CC); }
break;
case 70:
//#line 240 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 71:
//#line 241 "php_parser.y"
{ yyval.u.opline_num = -1; }
break;
case 72:
//#line 245 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 73:
//#line 246 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 74:
//#line 251 "php_parser.y"
{ yyval.u.opline_num = get_next_op_number(CG(active_op_array)); }
break;
case 75:
//#line 251 "php_parser.y"
{ zend_do_begin_catch(&val_peek(5), &val_peek(3), &val_peek(1), 0 TSRMLS_CC); }
break;
case 76:
//#line 251 "php_parser.y"
{ zend_do_end_catch(&val_peek(9) TSRMLS_CC); }
break;
case 79:
//#line 261 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_UNSET, 0 TSRMLS_CC); zend_do_unset(&val_peek(0) TSRMLS_CC); }
break;
case 80:
//#line 265 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 81:
//#line 266 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 82:
//#line 271 "php_parser.y"
{ zend_do_ticks(TSRMLS_C); }
break;
case 83:
//#line 275 "php_parser.y"
{ zend_do_ticks(TSRMLS_C); }
break;
case 84:
//#line 280 "php_parser.y"
{ yyval.op_type = ZEND_RETURN_VAL; }
break;
case 85:
//#line 281 "php_parser.y"
{ yyval.op_type = ZEND_RETURN_REF; }
break;
case 86:
//#line 286 "php_parser.y"
{ val_peek(0).u.opline_num = CG(zend_lineno); }
break;
case 87:
//#line 286 "php_parser.y"
{ zend_do_begin_function_declaration(&val_peek(3), &val_peek(0), 0, val_peek(1).op_type, NULL TSRMLS_CC); }
break;
case 88:
//#line 287 "php_parser.y"
{ zend_do_end_function_declaration(&val_peek(10) TSRMLS_CC); }
break;
case 89:
//#line 292 "php_parser.y"
{ zend_do_begin_class_declaration(&val_peek(2), &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 90:
//#line 296 "php_parser.y"
{ zend_do_end_class_declaration(&val_peek(7), &val_peek(6) TSRMLS_CC); }
break;
case 91:
//#line 298 "php_parser.y"
{ zend_do_begin_class_declaration(&val_peek(1), &val_peek(0), NULL TSRMLS_CC); }
break;
case 92:
//#line 302 "php_parser.y"
{ zend_do_end_class_declaration(&val_peek(6), &val_peek(5) TSRMLS_CC); }
break;
case 141:
//#line 408 "php_parser.y"
{ yyval.u.opline_num = CG(zend_lineno); yyval.u.EA.type = 0; }
break;
case 142:
//#line 409 "php_parser.y"
{ yyval.u.opline_num = CG(zend_lineno); yyval.u.EA.type = ZEND_ACC_EXPLICIT_ABSTRACT_CLASS; }
break;
case 143:
//#line 410 "php_parser.y"
{ yyval.u.opline_num = CG(zend_lineno); yyval.u.EA.type = ZEND_ACC_FINAL_CLASS; }
break;
case 144:
//#line 414 "php_parser.y"
{ yyval.op_type = IS_UNUSED; }
break;
case 145:
//#line 415 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 146:
//#line 419 "php_parser.y"
{ yyval.u.opline_num = CG(zend_lineno); yyval.u.EA.type = ZEND_ACC_INTERFACE; }
break;
case 151:
//#line 433 "php_parser.y"
{ zend_do_implements_interface(&val_peek(0) TSRMLS_CC); }
break;
case 152:
//#line 434 "php_parser.y"
{ zend_do_implements_interface(&val_peek(0) TSRMLS_CC); }
break;
case 153:
//#line 438 "php_parser.y"
{ yyval.op_type = IS_UNUSED; }
break;
case 154:
//#line 439 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 155:
//#line 444 "php_parser.y"
{ zend_check_writable_variable(&val_peek(0)); yyval = val_peek(0); }
break;
case 156:
//#line 445 "php_parser.y"
{ zend_check_writable_variable(&val_peek(0)); yyval = val_peek(0);  yyval.u.EA.type |= ZEND_PARSED_REFERENCE_VARIABLE; }
break;
case 163:
//#line 467 "php_parser.y"
{ zend_do_declare_stmt(&val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 164:
//#line 468 "php_parser.y"
{ zend_do_declare_stmt(&val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 165:
//#line 473 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 166:
//#line 474 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 167:
//#line 475 "php_parser.y"
{ yyval = val_peek(2); }
break;
case 168:
//#line 476 "php_parser.y"
{ yyval = val_peek(2); }
break;
case 169:
//#line 481 "php_parser.y"
{ yyval.op_type = IS_UNUSED; }
break;
case 170:
//#line 482 "php_parser.y"
{ zend_do_extended_info(TSRMLS_C);  zend_do_case_before_statement(&val_peek(3), &val_peek(2), &val_peek(1) TSRMLS_CC); }
break;
case 171:
//#line 482 "php_parser.y"
{ zend_do_case_after_statement(&yyval, &val_peek(4) TSRMLS_CC); yyval.op_type = IS_CONST; }
break;
case 172:
//#line 483 "php_parser.y"
{ zend_do_extended_info(TSRMLS_C);  zend_do_default_before_statement(&val_peek(2), &val_peek(1) TSRMLS_CC); }
break;
case 173:
//#line 483 "php_parser.y"
{ zend_do_case_after_statement(&yyval, &val_peek(3) TSRMLS_CC); yyval.op_type = IS_CONST; }
break;
case 179:
//#line 502 "php_parser.y"
{ zend_do_if_cond(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 180:
//#line 502 "php_parser.y"
{ zend_do_if_after_statement(&val_peek(2), 0 TSRMLS_CC); }
break;
case 182:
//#line 508 "php_parser.y"
{ zend_do_if_cond(&val_peek(2), &val_peek(1) TSRMLS_CC); }
break;
case 183:
//#line 508 "php_parser.y"
{ zend_do_if_after_statement(&val_peek(3), 0 TSRMLS_CC); }
break;
case 190:
//#line 531 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(0), 0 TSRMLS_CC); yyval.op_type = IS_CONST; yyval.u.constant.value.lval=1; yyval.u.constant.type=IS_LONG; INIT_PZVAL(&yyval.u.constant); zend_do_receive_arg(ZEND_RECV, &tmp, &yyval, NULL, &val_peek(1), &val_peek(0), 0 TSRMLS_CC); }
break;
case 191:
//#line 532 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(0), 0 TSRMLS_CC); yyval.op_type = IS_CONST; yyval.u.constant.value.lval=1; yyval.u.constant.type=IS_LONG; INIT_PZVAL(&yyval.u.constant); zend_do_receive_arg(ZEND_RECV, &tmp, &yyval, NULL, &val_peek(2), &val_peek(0), 1 TSRMLS_CC); }
break;
case 192:
//#line 533 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(2), 0 TSRMLS_CC); yyval.op_type = IS_CONST; yyval.u.constant.value.lval=1; yyval.u.constant.type=IS_LONG; INIT_PZVAL(&yyval.u.constant); zend_do_receive_arg(ZEND_RECV_INIT, &tmp, &yyval, &val_peek(0), &val_peek(4), &val_peek(2), 1 TSRMLS_CC); }
break;
case 193:
//#line 534 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(2), 0 TSRMLS_CC); yyval.op_type = IS_CONST; yyval.u.constant.value.lval=1; yyval.u.constant.type=IS_LONG; INIT_PZVAL(&yyval.u.constant); zend_do_receive_arg(ZEND_RECV_INIT, &tmp, &yyval, &val_peek(0), &val_peek(3), &val_peek(2), 0 TSRMLS_CC); }
break;
case 194:
//#line 535 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(0), 0 TSRMLS_CC); yyval=val_peek(3); yyval.u.constant.value.lval++; zend_do_receive_arg(ZEND_RECV, &tmp, &yyval, NULL, &val_peek(1), &val_peek(0), 0 TSRMLS_CC); }
break;
case 195:
//#line 536 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(0), 0 TSRMLS_CC); yyval=val_peek(4); yyval.u.constant.value.lval++; zend_do_receive_arg(ZEND_RECV, &tmp, &yyval, NULL, &val_peek(2), &val_peek(0), 1 TSRMLS_CC); }
break;
case 196:
//#line 537 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(2), 0 TSRMLS_CC); yyval=val_peek(6); yyval.u.constant.value.lval++; zend_do_receive_arg(ZEND_RECV_INIT, &tmp, &yyval, &val_peek(0), &val_peek(4), &val_peek(2), 1 TSRMLS_CC); }
break;
case 197:
//#line 538 "php_parser.y"
{ znode tmp;  fetch_simple_variable(&tmp, &val_peek(2), 0 TSRMLS_CC); yyval=val_peek(5); yyval.u.constant.value.lval++; zend_do_receive_arg(ZEND_RECV_INIT, &tmp, &yyval, &val_peek(0), &val_peek(3), &val_peek(2), 0 TSRMLS_CC); }
break;
case 198:
//#line 543 "php_parser.y"
{ yyval.op_type = IS_UNUSED; }
break;
case 199:
//#line 544 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 200:
//#line 545 "php_parser.y"
{ yyval.op_type = IS_CONST; yyval.u.constant.type=IS_NULL;}
break;
case 201:
//#line 550 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 202:
//#line 551 "php_parser.y"
{ yyval.u.constant.value.lval = 0; }
break;
case 203:
//#line 556 "php_parser.y"
{ yyval.u.constant.value.lval = 1;  zend_do_pass_param(&val_peek(0), ZEND_SEND_VAL, yyval.u.constant.value.lval TSRMLS_CC); }
break;
case 204:
//#line 557 "php_parser.y"
{ yyval.u.constant.value.lval = 1;  zend_do_pass_param(&val_peek(0), ZEND_SEND_VAR, yyval.u.constant.value.lval TSRMLS_CC); }
break;
case 205:
//#line 558 "php_parser.y"
{ yyval.u.constant.value.lval = 1;  zend_do_pass_param(&val_peek(0), ZEND_SEND_REF, yyval.u.constant.value.lval TSRMLS_CC); }
break;
case 206:
//#line 559 "php_parser.y"
{ yyval.u.constant.value.lval=val_peek(2).u.constant.value.lval+1;  zend_do_pass_param(&val_peek(0), ZEND_SEND_VAL, yyval.u.constant.value.lval TSRMLS_CC); }
break;
case 207:
//#line 560 "php_parser.y"
{ yyval.u.constant.value.lval=val_peek(2).u.constant.value.lval+1;  zend_do_pass_param(&val_peek(0), ZEND_SEND_VAR, yyval.u.constant.value.lval TSRMLS_CC); }
break;
case 208:
//#line 561 "php_parser.y"
{ yyval.u.constant.value.lval=val_peek(3).u.constant.value.lval+1;  zend_do_pass_param(&val_peek(0), ZEND_SEND_REF, yyval.u.constant.value.lval TSRMLS_CC); }
break;
case 209:
//#line 565 "php_parser.y"
{ zend_do_fetch_global_variable(&val_peek(0), NULL, ZEND_FETCH_GLOBAL_LOCK TSRMLS_CC); }
break;
case 210:
//#line 566 "php_parser.y"
{ zend_do_fetch_global_variable(&val_peek(0), NULL, ZEND_FETCH_GLOBAL_LOCK TSRMLS_CC); }
break;
case 211:
//#line 571 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 212:
//#line 572 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 213:
//#line 573 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 214:
//#line 578 "php_parser.y"
{ zend_do_fetch_static_variable(&val_peek(0), NULL, ZEND_FETCH_STATIC TSRMLS_CC); }
break;
case 215:
//#line 579 "php_parser.y"
{ zend_do_fetch_static_variable(&val_peek(2), &val_peek(0), ZEND_FETCH_STATIC TSRMLS_CC); }
break;
case 216:
//#line 580 "php_parser.y"
{ zend_do_fetch_static_variable(&val_peek(0), NULL, ZEND_FETCH_STATIC TSRMLS_CC); }
break;
case 217:
//#line 581 "php_parser.y"
{ zend_do_fetch_static_variable(&val_peek(2), &val_peek(0), ZEND_FETCH_STATIC TSRMLS_CC); }
break;
case 220:
//#line 593 "php_parser.y"
{ CG(access_type) = val_peek(0).u.constant.value.lval; }
break;
case 225:
//#line 603 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_ABSTRACT; }
break;
case 226:
//#line 604 "php_parser.y"
{ yyval.u.constant.value.lval = 0;	}
break;
case 227:
//#line 608 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 228:
//#line 609 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_PUBLIC; }
break;
case 229:
//#line 613 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_PUBLIC; }
break;
case 230:
//#line 614 "php_parser.y"
{ yyval = val_peek(0);  if (!(yyval.u.constant.value.lval & ZEND_ACC_PPP_MASK)) { yyval.u.constant.value.lval |= ZEND_ACC_PUBLIC; } }
break;
case 231:
//#line 618 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 232:
//#line 619 "php_parser.y"
{ yyval.u.constant.value.lval = zend_do_verify_access_types(&val_peek(1), &val_peek(0)); }
break;
case 233:
//#line 623 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_PUBLIC; }
break;
case 234:
//#line 624 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_PROTECTED; }
break;
case 235:
//#line 625 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_PRIVATE; }
break;
case 236:
//#line 626 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_STATIC; }
break;
case 237:
//#line 627 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_ABSTRACT; }
break;
case 238:
//#line 628 "php_parser.y"
{ yyval.u.constant.value.lval = ZEND_ACC_FINAL; }
break;
case 239:
//#line 632 "php_parser.y"
{ zend_do_declare_property(&val_peek(0), NULL, CG(access_type) TSRMLS_CC); }
break;
case 240:
//#line 633 "php_parser.y"
{ zend_do_declare_property(&val_peek(2), &val_peek(0), CG(access_type) TSRMLS_CC); }
break;
case 241:
//#line 634 "php_parser.y"
{ zend_do_declare_property(&val_peek(0), NULL, CG(access_type) TSRMLS_CC); }
break;
case 242:
//#line 635 "php_parser.y"
{ zend_do_declare_property(&val_peek(2), &val_peek(0), CG(access_type) TSRMLS_CC); }
break;
case 243:
//#line 639 "php_parser.y"
{ zend_do_declare_class_constant(&val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 244:
//#line 640 "php_parser.y"
{ zend_do_declare_class_constant(&val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 245:
//#line 644 "php_parser.y"
{ zend_do_echo(&val_peek(0) TSRMLS_CC); }
break;
case 246:
//#line 645 "php_parser.y"
{ zend_do_echo(&val_peek(0) TSRMLS_CC); }
break;
case 247:
//#line 650 "php_parser.y"
{ yyval.op_type = IS_CONST;  yyval.u.constant.type = IS_BOOL;  yyval.u.constant.value.lval = 1; }
break;
case 248:
//#line 651 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 249:
//#line 655 "php_parser.y"
{ zend_do_free(&val_peek(1) TSRMLS_CC); }
break;
case 250:
//#line 655 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 251:
//#line 656 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 252:
//#line 660 "php_parser.y"
{ zend_do_list_init(TSRMLS_C); }
break;
case 253:
//#line 660 "php_parser.y"
{ zend_do_list_end(&yyval, &val_peek(0) TSRMLS_CC); }
break;
case 254:
//#line 661 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_W, 0 TSRMLS_CC); zend_do_assign(&yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 255:
//#line 662 "php_parser.y"
{ zend_check_writable_variable(&val_peek(3)); zend_do_end_variable_parse(BP_VAR_W, 0 TSRMLS_CC); zend_do_end_variable_parse(BP_VAR_W, 0 TSRMLS_CC); zend_do_assign_ref(&yyval, &val_peek(3), &val_peek(0) TSRMLS_CC); }
break;
case 256:
//#line 663 "php_parser.y"
{ zend_error(E_STRICT, "Assigning the return value of new by reference is deprecated");  zend_check_writable_variable(&val_peek(4)); zend_do_extended_fcall_begin(TSRMLS_C); zend_do_begin_new_object(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 257:
//#line 663 "php_parser.y"
{ zend_do_end_new_object(&val_peek(4), &val_peek(3), &val_peek(0) TSRMLS_CC); zend_do_extended_fcall_end(TSRMLS_C); zend_do_end_variable_parse(BP_VAR_W, 0 TSRMLS_CC); zend_do_assign_ref(&yyval, &val_peek(6), &val_peek(4) TSRMLS_CC); }
break;
case 258:
//#line 664 "php_parser.y"
{ zend_do_extended_fcall_begin(TSRMLS_C); zend_do_begin_new_object(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 259:
//#line 664 "php_parser.y"
{ zend_do_end_new_object(&yyval, &val_peek(3), &val_peek(0) TSRMLS_CC); zend_do_extended_fcall_end(TSRMLS_C);}
break;
case 260:
//#line 665 "php_parser.y"
{ zend_do_clone(&yyval, &val_peek(0) TSRMLS_CC); }
break;
case 261:
//#line 666 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_ADD, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 262:
//#line 667 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_SUB, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 263:
//#line 668 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_MUL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 264:
//#line 669 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_DIV, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 265:
//#line 670 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_CONCAT, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 266:
//#line 671 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_MOD, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 267:
//#line 672 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_BW_AND, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 268:
//#line 673 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_BW_OR, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 269:
//#line 674 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_BW_XOR, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 270:
//#line 675 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_SL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 271:
//#line 676 "php_parser.y"
{ zend_check_writable_variable(&val_peek(2)); zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); zend_do_binary_assign_op(ZEND_ASSIGN_SR, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 272:
//#line 677 "php_parser.y"
{ zend_do_post_incdec(&yyval, &val_peek(1), ZEND_POST_INC TSRMLS_CC); }
break;
case 273:
//#line 678 "php_parser.y"
{ zend_do_pre_incdec(&yyval, &val_peek(0), ZEND_PRE_INC TSRMLS_CC); }
break;
case 274:
//#line 679 "php_parser.y"
{ zend_do_post_incdec(&yyval, &val_peek(1), ZEND_POST_DEC TSRMLS_CC); }
break;
case 275:
//#line 680 "php_parser.y"
{ zend_do_pre_incdec(&yyval, &val_peek(0), ZEND_PRE_DEC TSRMLS_CC); }
break;
case 276:
//#line 681 "php_parser.y"
{ zend_do_boolean_or_begin(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 277:
//#line 681 "php_parser.y"
{ zend_do_boolean_or_end(&yyval, &val_peek(3), &val_peek(0), &val_peek(2) TSRMLS_CC); }
break;
case 278:
//#line 682 "php_parser.y"
{ zend_do_boolean_and_begin(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 279:
//#line 682 "php_parser.y"
{ zend_do_boolean_and_end(&yyval, &val_peek(3), &val_peek(0), &val_peek(2) TSRMLS_CC); }
break;
case 280:
//#line 683 "php_parser.y"
{ zend_do_boolean_or_begin(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 281:
//#line 683 "php_parser.y"
{ zend_do_boolean_or_end(&yyval, &val_peek(3), &val_peek(0), &val_peek(2) TSRMLS_CC); }
break;
case 282:
//#line 684 "php_parser.y"
{ zend_do_boolean_and_begin(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 283:
//#line 684 "php_parser.y"
{ zend_do_boolean_and_end(&yyval, &val_peek(3), &val_peek(0), &val_peek(2) TSRMLS_CC); }
break;
case 284:
//#line 685 "php_parser.y"
{ zend_do_binary_op(ZEND_BOOL_XOR, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 285:
//#line 686 "php_parser.y"
{ zend_do_binary_op(ZEND_BW_OR, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 286:
//#line 687 "php_parser.y"
{ zend_do_binary_op(ZEND_BW_AND, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 287:
//#line 688 "php_parser.y"
{ zend_do_binary_op(ZEND_BW_XOR, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 288:
//#line 689 "php_parser.y"
{ zend_do_binary_op(ZEND_CONCAT, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 289:
//#line 690 "php_parser.y"
{ zend_do_binary_op(ZEND_ADD, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 290:
//#line 691 "php_parser.y"
{ zend_do_binary_op(ZEND_SUB, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 291:
//#line 692 "php_parser.y"
{ zend_do_binary_op(ZEND_MUL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 292:
//#line 693 "php_parser.y"
{ zend_do_binary_op(ZEND_DIV, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 293:
//#line 694 "php_parser.y"
{ zend_do_binary_op(ZEND_MOD, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 294:
//#line 695 "php_parser.y"
{ zend_do_binary_op(ZEND_SL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 295:
//#line 696 "php_parser.y"
{ zend_do_binary_op(ZEND_SR, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 296:
//#line 697 "php_parser.y"
{ val_peek(1).u.constant.value.lval=0; val_peek(1).u.constant.type=IS_LONG; val_peek(1).op_type = IS_CONST; INIT_PZVAL(&val_peek(1).u.constant); zend_do_binary_op(ZEND_ADD, &yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 297:
//#line 698 "php_parser.y"
{ val_peek(1).u.constant.value.lval=0; val_peek(1).u.constant.type=IS_LONG; val_peek(1).op_type = IS_CONST; INIT_PZVAL(&val_peek(1).u.constant); zend_do_binary_op(ZEND_SUB, &yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 298:
//#line 699 "php_parser.y"
{ zend_do_unary_op(ZEND_BOOL_NOT, &yyval, &val_peek(0) TSRMLS_CC); }
break;
case 299:
//#line 700 "php_parser.y"
{ zend_do_unary_op(ZEND_BW_NOT, &yyval, &val_peek(0) TSRMLS_CC); }
break;
case 300:
//#line 701 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_IDENTICAL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 301:
//#line 702 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_NOT_IDENTICAL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 302:
//#line 703 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_EQUAL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 303:
//#line 704 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_NOT_EQUAL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 304:
//#line 705 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_SMALLER, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 305:
//#line 706 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_SMALLER_OR_EQUAL, &yyval, &val_peek(2), &val_peek(0) TSRMLS_CC); }
break;
case 306:
//#line 707 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_SMALLER, &yyval, &val_peek(0), &val_peek(2) TSRMLS_CC); }
break;
case 307:
//#line 708 "php_parser.y"
{ zend_do_binary_op(ZEND_IS_SMALLER_OR_EQUAL, &yyval, &val_peek(0), &val_peek(2) TSRMLS_CC); }
break;
case 308:
//#line 709 "php_parser.y"
{ zend_do_instanceof(&yyval, &val_peek(2), &val_peek(0), 0 TSRMLS_CC); }
break;
case 309:
//#line 710 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 310:
//#line 711 "php_parser.y"
{ zend_do_begin_qm_op(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 311:
//#line 712 "php_parser.y"
{ zend_do_qm_true(&val_peek(1), &val_peek(3), &val_peek(0) TSRMLS_CC); }
break;
case 312:
//#line 713 "php_parser.y"
{ zend_do_qm_false(&yyval, &val_peek(0), &val_peek(5), &val_peek(2) TSRMLS_CC); }
break;
case 313:
//#line 714 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 314:
//#line 715 "php_parser.y"
{ zend_do_cast(&yyval, &val_peek(0), IS_LONG TSRMLS_CC); }
break;
case 315:
//#line 716 "php_parser.y"
{ zend_do_cast(&yyval, &val_peek(0), IS_DOUBLE TSRMLS_CC); }
break;
case 316:
//#line 717 "php_parser.y"
{ zend_do_cast(&yyval, &val_peek(0), IS_STRING TSRMLS_CC); }
break;
case 317:
//#line 718 "php_parser.y"
{ zend_do_cast(&yyval, &val_peek(0), IS_ARRAY TSRMLS_CC); }
break;
case 318:
//#line 719 "php_parser.y"
{ zend_do_cast(&yyval, &val_peek(0), IS_OBJECT TSRMLS_CC); }
break;
case 319:
//#line 720 "php_parser.y"
{ zend_do_cast(&yyval, &val_peek(0), IS_BOOL TSRMLS_CC); }
break;
case 320:
//#line 721 "php_parser.y"
{ zend_do_cast(&yyval, &val_peek(0), IS_NULL TSRMLS_CC); }
break;
case 321:
//#line 722 "php_parser.y"
{ zend_do_exit(&yyval, &val_peek(0) TSRMLS_CC); }
break;
case 322:
//#line 723 "php_parser.y"
{ zend_do_begin_silence(&val_peek(0) TSRMLS_CC); }
break;
case 323:
//#line 723 "php_parser.y"
{ zend_do_end_silence(&val_peek(2) TSRMLS_CC); yyval = val_peek(0); }
break;
case 324:
//#line 724 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 325:
//#line 725 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 326:
//#line 726 "php_parser.y"
{ zend_do_shell_exec(&yyval, &val_peek(1) TSRMLS_CC); }
break;
case 327:
//#line 727 "php_parser.y"
{ zend_do_print(&yyval, &val_peek(0) TSRMLS_CC); }
break;
case 328:
//#line 731 "php_parser.y"
{ val_peek(0).u.opline_num = zend_do_begin_function_call(&val_peek(1) TSRMLS_CC); }
break;
case 329:
//#line 733 "php_parser.y"
{ zend_do_end_function_call(&val_peek(4), &yyval, &val_peek(1), 0, val_peek(3).u.opline_num TSRMLS_CC); zend_do_extended_fcall_end(TSRMLS_C); }
break;
case 330:
//#line 734 "php_parser.y"
{ zend_do_begin_class_member_function_call(&val_peek(3), &val_peek(1) TSRMLS_CC); }
break;
case 331:
//#line 736 "php_parser.y"
{ zend_do_end_function_call(NULL, &yyval, &val_peek(1), 1, 1 TSRMLS_CC); zend_do_extended_fcall_end(TSRMLS_C);}
break;
case 332:
//#line 737 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_R, 0 TSRMLS_CC); zend_do_begin_class_member_function_call(&val_peek(3), &val_peek(1) TSRMLS_CC); }
break;
case 333:
//#line 739 "php_parser.y"
{ zend_do_end_function_call(NULL, &yyval, &val_peek(1), 1, 1 TSRMLS_CC); zend_do_extended_fcall_end(TSRMLS_C);}
break;
case 334:
//#line 740 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_R, 0 TSRMLS_CC); zend_do_begin_dynamic_function_call(&val_peek(1) TSRMLS_CC); }
break;
case 335:
//#line 742 "php_parser.y"
{ zend_do_end_function_call(&val_peek(4), &yyval, &val_peek(1), 0, 1 TSRMLS_CC); zend_do_extended_fcall_end(TSRMLS_C);}
break;
case 336:
//#line 746 "php_parser.y"
{ zend_do_fetch_class(&yyval, &val_peek(0) TSRMLS_CC); }
break;
case 337:
//#line 750 "php_parser.y"
{ zend_do_fetch_class(&yyval, &val_peek(0) TSRMLS_CC); }
break;
case 338:
//#line 751 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_R, 0 TSRMLS_CC); zend_do_fetch_class(&yyval, &val_peek(0) TSRMLS_CC); }
break;
case 339:
//#line 756 "php_parser.y"
{ zend_do_push_object(&val_peek(1) TSRMLS_CC); }
break;
case 340:
//#line 757 "php_parser.y"
{ zend_do_push_object(&val_peek(0) TSRMLS_CC); zend_do_declare_implicit_property(TSRMLS_C); }
break;
case 341:
//#line 758 "php_parser.y"
{ zend_do_pop_object(&yyval TSRMLS_CC); yyval.u.EA.type = ZEND_PARSED_MEMBER; }
break;
case 342:
//#line 759 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 345:
//#line 770 "php_parser.y"
{ zend_do_push_object(&val_peek(0) TSRMLS_CC); zend_do_declare_implicit_property(TSRMLS_C); }
break;
case 346:
//#line 774 "php_parser.y"
{ memset(&yyval, 0, sizeof(znode)); yyval.op_type = IS_UNUSED; }
break;
case 347:
//#line 775 "php_parser.y"
{ memset(&yyval, 0, sizeof(znode)); yyval.op_type = IS_UNUSED; }
break;
case 348:
//#line 776 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 349:
//#line 781 "php_parser.y"
{ yyval.u.constant.value.lval=0; }
break;
case 350:
//#line 782 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 351:
//#line 787 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 352:
//#line 788 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 353:
//#line 789 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 354:
//#line 790 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 355:
//#line 791 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 356:
//#line 792 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 357:
//#line 793 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 358:
//#line 794 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 359:
//#line 799 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 360:
//#line 800 "php_parser.y"
{ zend_do_fetch_constant(&yyval, NULL, &val_peek(0), ZEND_CT TSRMLS_CC); }
break;
case 361:
//#line 801 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 362:
//#line 802 "php_parser.y"
{ zval minus_one;  minus_one.type = IS_LONG; minus_one.value.lval = -1;  mul_function(&val_peek(0).u.constant, &val_peek(0).u.constant, &minus_one TSRMLS_CC);  yyval = val_peek(0); }
break;
case 363:
//#line 803 "php_parser.y"
{ yyval = val_peek(1); yyval.u.constant.type = IS_CONSTANT_ARRAY; }
break;
case 364:
//#line 804 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 365:
//#line 808 "php_parser.y"
{ zend_do_fetch_constant(&yyval, &val_peek(2), &val_peek(0), ZEND_CT TSRMLS_CC); }
break;
case 366:
//#line 812 "php_parser.y"
{ zend_do_fetch_constant(&yyval, NULL, &val_peek(0), ZEND_RT TSRMLS_CC); }
break;
case 367:
//#line 813 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 368:
//#line 814 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 369:
//#line 815 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 370:
//#line 816 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 371:
//#line 817 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 372:
//#line 818 "php_parser.y"
{ yyval = val_peek(1); zend_do_end_heredoc(TSRMLS_C); }
break;
case 373:
//#line 823 "php_parser.y"
{ yyval.op_type = IS_CONST; INIT_PZVAL(&yyval.u.constant); array_init(&yyval.u.constant); }
break;
case 374:
//#line 824 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 377:
//#line 833 "php_parser.y"
{ zend_do_add_static_array_element(&yyval, &val_peek(2), &val_peek(0)); }
break;
case 378:
//#line 834 "php_parser.y"
{ zend_do_add_static_array_element(&yyval, NULL, &val_peek(0)); }
break;
case 379:
//#line 835 "php_parser.y"
{ yyval.op_type = IS_CONST; INIT_PZVAL(&yyval.u.constant); array_init(&yyval.u.constant); zend_do_add_static_array_element(&yyval, &val_peek(2), &val_peek(0)); }
break;
case 380:
//#line 836 "php_parser.y"
{ yyval.op_type = IS_CONST; INIT_PZVAL(&yyval.u.constant); array_init(&yyval.u.constant); zend_do_add_static_array_element(&yyval, NULL, &val_peek(0)); }
break;
case 381:
//#line 840 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 382:
//#line 841 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 383:
//#line 846 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_R, 0 TSRMLS_CC); yyval = val_peek(0); }
break;
case 384:
//#line 851 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_W, 0 TSRMLS_CC); yyval = val_peek(0); }
break;
case 385:
//#line 852 "php_parser.y"
{ zend_check_writable_variable(&val_peek(1)); }
break;
case 386:
//#line 856 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_RW, 0 TSRMLS_CC); yyval = val_peek(0); }
break;
case 387:
//#line 857 "php_parser.y"
{ zend_check_writable_variable(&val_peek(1)); }
break;
case 388:
//#line 861 "php_parser.y"
{ zend_do_push_object(&val_peek(1) TSRMLS_CC); }
break;
case 389:
//#line 862 "php_parser.y"
{ zend_do_push_object(&val_peek(0) TSRMLS_CC); }
break;
case 390:
//#line 863 "php_parser.y"
{ zend_do_pop_object(&yyval TSRMLS_CC); yyval.u.EA.type = val_peek(6).u.EA.type | (val_peek(0).u.EA.type ? val_peek(0).u.EA.type : val_peek(1).u.EA.type); }
break;
case 391:
//#line 864 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 392:
//#line 868 "php_parser.y"
{ yyval.u.EA.type = val_peek(0).u.EA.type; }
break;
case 393:
//#line 869 "php_parser.y"
{ yyval.u.EA.type = 0; }
break;
case 394:
//#line 874 "php_parser.y"
{ zend_do_push_object(&val_peek(0) TSRMLS_CC); }
break;
case 395:
//#line 874 "php_parser.y"
{ yyval.u.EA.type = val_peek(0).u.EA.type; }
break;
case 396:
//#line 878 "php_parser.y"
{ zend_do_pop_object(&val_peek(0) TSRMLS_CC); zend_do_begin_method_call(&val_peek(0) TSRMLS_CC); }
break;
case 397:
//#line 880 "php_parser.y"
{ zend_do_end_function_call(&val_peek(3), &yyval, &val_peek(1), 1, 1 TSRMLS_CC); zend_do_extended_fcall_end(TSRMLS_C);
			  zend_do_push_object(&yyval TSRMLS_CC); yyval.u.EA.type = ZEND_PARSED_METHOD_CALL; }
break;
case 398:
//#line 882 "php_parser.y"
{ zend_do_declare_implicit_property(TSRMLS_C); yyval.u.EA.type = ZEND_PARSED_MEMBER; }
break;
case 399:
//#line 886 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 400:
//#line 887 "php_parser.y"
{ zend_do_indirect_references(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 401:
//#line 891 "php_parser.y"
{ yyval = val_peek(0); zend_do_fetch_static_member(&yyval, &val_peek(2) TSRMLS_CC); }
break;
case 402:
//#line 896 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 403:
//#line 897 "php_parser.y"
{ zend_do_begin_variable_parse(TSRMLS_C); yyval = val_peek(0); yyval.u.EA.type = ZEND_PARSED_FUNCTION_CALL; }
break;
case 404:
//#line 902 "php_parser.y"
{ yyval = val_peek(0); yyval.u.EA.type = ZEND_PARSED_VARIABLE; }
break;
case 405:
//#line 903 "php_parser.y"
{ zend_do_indirect_references(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); yyval.u.EA.type = ZEND_PARSED_VARIABLE; }
break;
case 406:
//#line 904 "php_parser.y"
{ yyval = val_peek(0); yyval.u.EA.type = ZEND_PARSED_STATIC_MEMBER; }
break;
case 407:
//#line 908 "php_parser.y"
{ fetch_array_dim(&yyval, &val_peek(3), &val_peek(1) TSRMLS_CC); }
break;
case 408:
//#line 909 "php_parser.y"
{ fetch_string_offset(&yyval, &val_peek(3), &val_peek(1) TSRMLS_CC); }
break;
case 409:
//#line 910 "php_parser.y"
{ zend_do_begin_variable_parse(TSRMLS_C); fetch_simple_variable(&yyval, &val_peek(0), 1 TSRMLS_CC); }
break;
case 410:
//#line 915 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 411:
//#line 916 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 412:
//#line 920 "php_parser.y"
{ yyval.op_type = IS_UNUSED; }
break;
case 413:
//#line 921 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 414:
//#line 926 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 415:
//#line 927 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_R, 0 TSRMLS_CC); }
break;
case 416:
//#line 927 "php_parser.y"
{ znode tmp_znode;  zend_do_pop_object(&tmp_znode TSRMLS_CC);  zend_do_fetch_property(&yyval, &tmp_znode, &val_peek(1) TSRMLS_CC);}
break;
case 417:
//#line 931 "php_parser.y"
{ fetch_array_dim(&yyval, &val_peek(3), &val_peek(1) TSRMLS_CC); }
break;
case 418:
//#line 932 "php_parser.y"
{ fetch_string_offset(&yyval, &val_peek(3), &val_peek(1) TSRMLS_CC); }
break;
case 419:
//#line 933 "php_parser.y"
{ znode tmp_znode;  zend_do_pop_object(&tmp_znode TSRMLS_CC);  zend_do_fetch_property(&yyval, &tmp_znode, &val_peek(0) TSRMLS_CC);}
break;
case 420:
//#line 937 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 421:
//#line 938 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 422:
//#line 942 "php_parser.y"
{ yyval.u.constant.value.lval = 1; }
break;
case 423:
//#line 943 "php_parser.y"
{ yyval.u.constant.value.lval++; }
break;
case 426:
//#line 953 "php_parser.y"
{ zend_do_add_list_element(&val_peek(0) TSRMLS_CC); }
break;
case 427:
//#line 954 "php_parser.y"
{ zend_do_new_list_begin(TSRMLS_C); }
break;
case 428:
//#line 954 "php_parser.y"
{ zend_do_new_list_end(TSRMLS_C); }
break;
case 429:
//#line 955 "php_parser.y"
{ zend_do_add_list_element(NULL TSRMLS_CC); }
break;
case 430:
//#line 960 "php_parser.y"
{ zend_do_init_array(&yyval, NULL, NULL, 0 TSRMLS_CC); }
break;
case 431:
//#line 961 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 432:
//#line 965 "php_parser.y"
{ zend_do_add_array_element(&yyval, &val_peek(0), &val_peek(2), 0 TSRMLS_CC); }
break;
case 433:
//#line 966 "php_parser.y"
{ zend_do_add_array_element(&yyval, &val_peek(0), NULL, 0 TSRMLS_CC); }
break;
case 434:
//#line 967 "php_parser.y"
{ zend_do_init_array(&yyval, &val_peek(0), &val_peek(2), 0 TSRMLS_CC); }
break;
case 435:
//#line 968 "php_parser.y"
{ zend_do_init_array(&yyval, &val_peek(0), NULL, 0 TSRMLS_CC); }
break;
case 436:
//#line 969 "php_parser.y"
{ zend_do_add_array_element(&yyval, &val_peek(0), &val_peek(3), 1 TSRMLS_CC); }
break;
case 437:
//#line 970 "php_parser.y"
{ zend_do_add_array_element(&yyval, &val_peek(0), NULL, 1 TSRMLS_CC); }
break;
case 438:
//#line 971 "php_parser.y"
{ zend_do_init_array(&yyval, &val_peek(0), &val_peek(3), 1 TSRMLS_CC); }
break;
case 439:
//#line 972 "php_parser.y"
{ zend_do_init_array(&yyval, &val_peek(0), NULL, 1 TSRMLS_CC); }
break;
case 440:
//#line 976 "php_parser.y"
{ zend_do_end_variable_parse(BP_VAR_R, 0 TSRMLS_CC);  zend_do_add_variable(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 441:
//#line 977 "php_parser.y"
{ zend_do_add_string(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 442:
//#line 978 "php_parser.y"
{ zend_do_add_string(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 443:
//#line 979 "php_parser.y"
{ zend_do_add_string(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 444:
//#line 980 "php_parser.y"
{ zend_do_add_char(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 445:
//#line 981 "php_parser.y"
{ zend_do_add_string(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 446:
//#line 982 "php_parser.y"
{ val_peek(0).u.constant.value.lval = (long) '['; zend_do_add_char(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 447:
//#line 983 "php_parser.y"
{ val_peek(0).u.constant.value.lval = (long) ']'; zend_do_add_char(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 448:
//#line 984 "php_parser.y"
{ val_peek(0).u.constant.value.lval = (long) '{'; zend_do_add_char(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 449:
//#line 985 "php_parser.y"
{ val_peek(0).u.constant.value.lval = (long) '}'; zend_do_add_char(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 450:
//#line 986 "php_parser.y"
{ znode tmp;  val_peek(0).u.constant.value.lval = (long) '-';  zend_do_add_char(&tmp, &val_peek(1), &val_peek(0) TSRMLS_CC);  val_peek(0).u.constant.value.lval = (long) '>'; zend_do_add_char(&yyval, &tmp, &val_peek(0) TSRMLS_CC); }
break;
case 451:
//#line 987 "php_parser.y"
{ zend_do_init_string(&yyval TSRMLS_CC); }
break;
case 452:
//#line 994 "php_parser.y"
{ zend_do_begin_variable_parse(TSRMLS_C); fetch_simple_variable(&yyval, &val_peek(0), 1 TSRMLS_CC); }
break;
case 453:
//#line 995 "php_parser.y"
{ zend_do_begin_variable_parse(TSRMLS_C); }
break;
case 454:
//#line 995 "php_parser.y"
{ fetch_array_begin(&yyval, &val_peek(4), &val_peek(1) TSRMLS_CC); }
break;
case 455:
//#line 996 "php_parser.y"
{ zend_do_begin_variable_parse(TSRMLS_C); fetch_simple_variable(&val_peek(1), &val_peek(2), 1 TSRMLS_CC); zend_do_fetch_property(&yyval, &val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 456:
//#line 997 "php_parser.y"
{ zend_do_begin_variable_parse(TSRMLS_C);  fetch_simple_variable(&yyval, &val_peek(1), 1 TSRMLS_CC); }
break;
case 457:
//#line 998 "php_parser.y"
{ zend_do_begin_variable_parse(TSRMLS_C);  fetch_array_begin(&yyval, &val_peek(4), &val_peek(2) TSRMLS_CC); }
break;
case 458:
//#line 999 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 459:
//#line 1004 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 460:
//#line 1005 "php_parser.y"
{ yyval = val_peek(0); }
break;
case 461:
//#line 1006 "php_parser.y"
{ fetch_simple_variable(&yyval, &val_peek(0), 1 TSRMLS_CC); }
break;
case 462:
//#line 1011 "php_parser.y"
{ yyval = val_peek(1); }
break;
case 463:
//#line 1012 "php_parser.y"
{ zend_do_isset_or_isempty(ZEND_ISEMPTY, &yyval, &val_peek(1) TSRMLS_CC); }
break;
case 464:
//#line 1013 "php_parser.y"
{ zend_do_include_or_eval(ZEND_INCLUDE, &yyval, &val_peek(0) TSRMLS_CC); }
break;
case 465:
//#line 1014 "php_parser.y"
{ zend_do_include_or_eval(ZEND_INCLUDE_ONCE, &yyval, &val_peek(0) TSRMLS_CC); }
break;
case 466:
//#line 1015 "php_parser.y"
{ zend_do_include_or_eval(ZEND_EVAL, &yyval, &val_peek(1) TSRMLS_CC); }
break;
case 467:
//#line 1016 "php_parser.y"
{ zend_do_include_or_eval(ZEND_REQUIRE, &yyval, &val_peek(0) TSRMLS_CC); }
break;
case 468:
//#line 1017 "php_parser.y"
{ zend_do_include_or_eval(ZEND_REQUIRE_ONCE, &yyval, &val_peek(0) TSRMLS_CC); }
break;
case 469:
//#line 1021 "php_parser.y"
{ zend_do_isset_or_isempty(ZEND_ISSET, &yyval, &val_peek(0) TSRMLS_CC); }
break;
case 470:
//#line 1022 "php_parser.y"
{ zend_do_boolean_and_begin(&val_peek(1), &val_peek(0) TSRMLS_CC); }
break;
case 471:
//#line 1022 "php_parser.y"
{ znode tmp; zend_do_isset_or_isempty(ZEND_ISSET, &tmp, &val_peek(0) TSRMLS_CC); zend_do_boolean_and_end(&yyval, &val_peek(3), &tmp, &val_peek(2) TSRMLS_CC); }
break;
case 472:
//#line 1026 "php_parser.y"
{ zend_do_fetch_constant(&yyval, &val_peek(2), &val_peek(0), ZEND_RT TSRMLS_CC); }
break;
//#line 1971 "y.code.c"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
