// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 PHPAspectLexer.g 2009-05-24 10:44:57

package org.phpaspect.apdt.internal.core.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PHPAspectLexer extends APDTLexer {
    public static final int WITHIN=27;
    public static final int LPARENTHESE=10;
    public static final int WS=8;
    public static final int STRING=29;
    public static final int NEW=14;
    public static final int PAAMAYIM_NEKUDOTAYIM=16;
    public static final int LABEL=28;
    public static final int OBJECT_OPERATOR=17;
    public static final int MIXIN=25;
    public static final int OR=19;
    public static final int AFTER=24;
    public static final int DOT=9;
    public static final int EXEC=15;
    public static final int AND=18;
    public static final int FILE=26;
    public static final int INTLIT=5;
    public static final int EOF=-1;
    public static final int STRING_LITERAL=7;
    public static final int CHARLIT=6;
    public static final int CALL=13;
    public static final int BEFORE=22;
    public static final int PLUS=21;
    public static final int DIGIT=4;
    public static final int RPARENTHESE=11;
    public static final int AROUND=23;
    public static final int NOT=20;
    public static final int AT=12;

    // delegates
    // delegators

    public PHPAspectLexer() {;} 
    public PHPAspectLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PHPAspectLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "PHPAspectLexer.g"; }

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:13:3: ( '0' .. '9' )
            // PHPAspectLexer.g:13:5: '0' .. '9'
            {
            matchRange('0','9'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "INTLIT"
    public final void mINTLIT() throws RecognitionException {
        try {
            int _type = INTLIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:17:3: ( ( DIGIT )+ )
            // PHPAspectLexer.g:17:5: ( DIGIT )+
            {
            // PHPAspectLexer.g:17:5: ( DIGIT )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // PHPAspectLexer.g:17:6: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTLIT"

    // $ANTLR start "CHARLIT"
    public final void mCHARLIT() throws RecognitionException {
        try {
            int _type = CHARLIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:21:3: ( '\\'' . '\\'' )
            // PHPAspectLexer.g:21:5: '\\'' . '\\''
            {
            match('\''); 
            matchAny(); 
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHARLIT"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:26:3: ( '\"' ( '\"' '\"' | ~ ( '\"' | '\\n' | '\\r' ) )* ( '\"' | ) )
            // PHPAspectLexer.g:26:5: '\"' ( '\"' '\"' | ~ ( '\"' | '\\n' | '\\r' ) )* ( '\"' | )
            {
            match('\"'); 
            // PHPAspectLexer.g:27:5: ( '\"' '\"' | ~ ( '\"' | '\\n' | '\\r' ) )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\"') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='\"') ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='!')||(LA2_0>='#' && LA2_0<='\uFFFF')) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // PHPAspectLexer.g:27:7: '\"' '\"'
            	    {
            	    match('\"'); 
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // PHPAspectLexer.g:28:7: ~ ( '\"' | '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // PHPAspectLexer.g:30:5: ( '\"' | )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\"') ) {
                alt3=1;
            }
            else {
                alt3=2;}
            switch (alt3) {
                case 1 :
                    // PHPAspectLexer.g:30:7: '\"'
                    {
                    match('\"'); 

                    }
                    break;
                case 2 :
                    // PHPAspectLexer.g:32:5: 
                    {
                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:35:3: ( ( ' ' | '\\n' | '\\r' | '\\t' )+ )
            // PHPAspectLexer.g:35:5: ( ' ' | '\\n' | '\\r' | '\\t' )+
            {
            // PHPAspectLexer.g:35:5: ( ' ' | '\\n' | '\\r' | '\\t' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\t' && LA4_0<='\n')||LA4_0=='\r'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // PHPAspectLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:38:12: ( '.' )
            // PHPAspectLexer.g:38:14: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "LPARENTHESE"
    public final void mLPARENTHESE() throws RecognitionException {
        try {
            int _type = LPARENTHESE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:39:17: ( '(' )
            // PHPAspectLexer.g:39:19: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPARENTHESE"

    // $ANTLR start "RPARENTHESE"
    public final void mRPARENTHESE() throws RecognitionException {
        try {
            int _type = RPARENTHESE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:40:17: ( ')' )
            // PHPAspectLexer.g:40:19: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPARENTHESE"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:41:4: ( '@' )
            // PHPAspectLexer.g:41:6: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "CALL"
    public final void mCALL() throws RecognitionException {
        try {
            int _type = CALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:42:6: ( 'call' )
            // PHPAspectLexer.g:42:8: 'call'
            {
            match("call"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CALL"

    // $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:43:5: ( 'new' )
            // PHPAspectLexer.g:43:7: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEW"

    // $ANTLR start "EXEC"
    public final void mEXEC() throws RecognitionException {
        try {
            int _type = EXEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:44:6: ( 'exec' )
            // PHPAspectLexer.g:44:8: 'exec'
            {
            match("exec"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXEC"

    // $ANTLR start "PAAMAYIM_NEKUDOTAYIM"
    public final void mPAAMAYIM_NEKUDOTAYIM() throws RecognitionException {
        try {
            int _type = PAAMAYIM_NEKUDOTAYIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:45:22: ( '::' )
            // PHPAspectLexer.g:45:24: '::'
            {
            match("::"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PAAMAYIM_NEKUDOTAYIM"

    // $ANTLR start "OBJECT_OPERATOR"
    public final void mOBJECT_OPERATOR() throws RecognitionException {
        try {
            int _type = OBJECT_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:46:17: ( '->' )
            // PHPAspectLexer.g:46:19: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OBJECT_OPERATOR"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:47:5: ( '&&' )
            // PHPAspectLexer.g:47:7: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:48:4: ( '||' )
            // PHPAspectLexer.g:48:6: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:49:5: ( '!' )
            // PHPAspectLexer.g:49:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:50:6: ( '+' )
            // PHPAspectLexer.g:50:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "BEFORE"
    public final void mBEFORE() throws RecognitionException {
        try {
            int _type = BEFORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:51:8: ( 'Before' )
            // PHPAspectLexer.g:51:10: 'Before'
            {
            match("Before"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BEFORE"

    // $ANTLR start "AROUND"
    public final void mAROUND() throws RecognitionException {
        try {
            int _type = AROUND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:52:8: ( 'Around' )
            // PHPAspectLexer.g:52:10: 'Around'
            {
            match("Around"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AROUND"

    // $ANTLR start "AFTER"
    public final void mAFTER() throws RecognitionException {
        try {
            int _type = AFTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:53:7: ( 'After' )
            // PHPAspectLexer.g:53:9: 'After'
            {
            match("After"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AFTER"

    // $ANTLR start "MIXIN"
    public final void mMIXIN() throws RecognitionException {
        try {
            int _type = MIXIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:54:7: ( 'Mixin' )
            // PHPAspectLexer.g:54:9: 'Mixin'
            {
            match("Mixin"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MIXIN"

    // $ANTLR start "FILE"
    public final void mFILE() throws RecognitionException {
        try {
            int _type = FILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:55:6: ( 'file' )
            // PHPAspectLexer.g:55:8: 'file'
            {
            match("file"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FILE"

    // $ANTLR start "WITHIN"
    public final void mWITHIN() throws RecognitionException {
        try {
            int _type = WITHIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:56:8: ( 'within' )
            // PHPAspectLexer.g:56:10: 'within'
            {
            match("within"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WITHIN"

    // $ANTLR start "LABEL"
    public final void mLABEL() throws RecognitionException {
        try {
            int _type = LABEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:60:1: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '*' ) ( '*' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // PHPAspectLexer.g:60:3: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '*' ) ( '*' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( input.LA(1)=='*'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // PHPAspectLexer.g:60:30: ( '*' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='*'||(LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // PHPAspectLexer.g:
            	    {
            	    if ( input.LA(1)=='*'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LABEL"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:64:1: ( '\"' ( . )* '\"' )
            // PHPAspectLexer.g:64:3: '\"' ( . )* '\"'
            {
            match('\"'); 
            // PHPAspectLexer.g:64:6: ( . )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\"') ) {
                    alt6=2;
                }
                else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // PHPAspectLexer.g:64:6: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    public void mTokens() throws RecognitionException {
        // PHPAspectLexer.g:1:8: ( DIGIT | INTLIT | CHARLIT | STRING_LITERAL | WS | DOT | LPARENTHESE | RPARENTHESE | AT | CALL | NEW | EXEC | PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR | AND | OR | NOT | PLUS | BEFORE | AROUND | AFTER | MIXIN | FILE | WITHIN | LABEL | STRING )
        int alt7=26;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // PHPAspectLexer.g:1:10: DIGIT
                {
                mDIGIT(); 

                }
                break;
            case 2 :
                // PHPAspectLexer.g:1:16: INTLIT
                {
                mINTLIT(); 

                }
                break;
            case 3 :
                // PHPAspectLexer.g:1:23: CHARLIT
                {
                mCHARLIT(); 

                }
                break;
            case 4 :
                // PHPAspectLexer.g:1:31: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;
            case 5 :
                // PHPAspectLexer.g:1:46: WS
                {
                mWS(); 

                }
                break;
            case 6 :
                // PHPAspectLexer.g:1:49: DOT
                {
                mDOT(); 

                }
                break;
            case 7 :
                // PHPAspectLexer.g:1:53: LPARENTHESE
                {
                mLPARENTHESE(); 

                }
                break;
            case 8 :
                // PHPAspectLexer.g:1:65: RPARENTHESE
                {
                mRPARENTHESE(); 

                }
                break;
            case 9 :
                // PHPAspectLexer.g:1:77: AT
                {
                mAT(); 

                }
                break;
            case 10 :
                // PHPAspectLexer.g:1:80: CALL
                {
                mCALL(); 

                }
                break;
            case 11 :
                // PHPAspectLexer.g:1:85: NEW
                {
                mNEW(); 

                }
                break;
            case 12 :
                // PHPAspectLexer.g:1:89: EXEC
                {
                mEXEC(); 

                }
                break;
            case 13 :
                // PHPAspectLexer.g:1:94: PAAMAYIM_NEKUDOTAYIM
                {
                mPAAMAYIM_NEKUDOTAYIM(); 

                }
                break;
            case 14 :
                // PHPAspectLexer.g:1:115: OBJECT_OPERATOR
                {
                mOBJECT_OPERATOR(); 

                }
                break;
            case 15 :
                // PHPAspectLexer.g:1:131: AND
                {
                mAND(); 

                }
                break;
            case 16 :
                // PHPAspectLexer.g:1:135: OR
                {
                mOR(); 

                }
                break;
            case 17 :
                // PHPAspectLexer.g:1:138: NOT
                {
                mNOT(); 

                }
                break;
            case 18 :
                // PHPAspectLexer.g:1:142: PLUS
                {
                mPLUS(); 

                }
                break;
            case 19 :
                // PHPAspectLexer.g:1:147: BEFORE
                {
                mBEFORE(); 

                }
                break;
            case 20 :
                // PHPAspectLexer.g:1:154: AROUND
                {
                mAROUND(); 

                }
                break;
            case 21 :
                // PHPAspectLexer.g:1:161: AFTER
                {
                mAFTER(); 

                }
                break;
            case 22 :
                // PHPAspectLexer.g:1:167: MIXIN
                {
                mMIXIN(); 

                }
                break;
            case 23 :
                // PHPAspectLexer.g:1:173: FILE
                {
                mFILE(); 

                }
                break;
            case 24 :
                // PHPAspectLexer.g:1:178: WITHIN
                {
                mWITHIN(); 

                }
                break;
            case 25 :
                // PHPAspectLexer.g:1:185: LABEL
                {
                mLABEL(); 

                }
                break;
            case 26 :
                // PHPAspectLexer.g:1:191: STRING
                {
                mSTRING(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\1\30\1\uffff\1\34\5\uffff\3\27\6\uffff\5\27\3\uffff\2\34"+
        "\2\uffff\11\27\1\34\1\27\1\62\7\27\1\72\1\uffff\1\73\4\27\1\100"+
        "\1\27\2\uffff\2\27\1\104\1\105\1\uffff\1\27\1\107\1\110\2\uffff"+
        "\1\111\3\uffff";
    static final String DFA7_eofS =
        "\112\uffff";
    static final String DFA7_minS =
        "\1\11\1\60\1\uffff\1\0\5\uffff\1\141\1\145\1\170\6\uffff\1\145\1"+
        "\146\3\151\3\uffff\2\0\2\uffff\1\154\1\167\1\145\1\146\1\157\1\164"+
        "\1\170\1\154\1\164\1\0\1\154\1\52\1\143\1\157\1\165\1\145\1\151"+
        "\1\145\1\150\1\52\1\uffff\1\52\1\162\1\156\1\162\1\156\1\52\1\151"+
        "\2\uffff\1\145\1\144\2\52\1\uffff\1\156\2\52\2\uffff\1\52\3\uffff";
    static final String DFA7_maxS =
        "\1\174\1\71\1\uffff\1\uffff\5\uffff\1\141\1\145\1\170\6\uffff\1"+
        "\145\1\162\3\151\3\uffff\2\uffff\2\uffff\1\154\1\167\1\145\1\146"+
        "\1\157\1\164\1\170\1\154\1\164\1\uffff\1\154\1\172\1\143\1\157\1"+
        "\165\1\145\1\151\1\145\1\150\1\172\1\uffff\1\172\1\162\1\156\1\162"+
        "\1\156\1\172\1\151\2\uffff\1\145\1\144\2\172\1\uffff\1\156\2\172"+
        "\2\uffff\1\172\3\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\3\1\uffff\1\5\1\6\1\7\1\10\1\11\3\uffff\1\15\1\16\1\17"+
        "\1\20\1\21\1\22\5\uffff\1\31\1\1\1\2\2\uffff\1\4\1\32\24\uffff\1"+
        "\13\7\uffff\1\12\1\14\4\uffff\1\27\3\uffff\1\25\1\26\1\uffff\1\23"+
        "\1\24\1\30";
    static final String DFA7_specialS =
        "\3\uffff\1\0\26\uffff\1\3\1\2\13\uffff\1\1\42\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\4\2\uffff\1\4\22\uffff\1\4\1\20\1\3\3\uffff\1\16\1\2\1\6"+
            "\1\7\1\27\1\21\1\uffff\1\15\1\5\1\uffff\12\1\1\14\5\uffff\1"+
            "\10\1\23\1\22\12\27\1\24\15\27\4\uffff\1\27\1\uffff\2\27\1\11"+
            "\1\27\1\13\1\25\7\27\1\12\10\27\1\26\3\27\1\uffff\1\17",
            "\12\31",
            "",
            "\12\33\1\35\2\33\1\35\24\33\1\32\uffdd\33",
            "",
            "",
            "",
            "",
            "",
            "\1\36",
            "\1\37",
            "\1\40",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\41",
            "\1\43\13\uffff\1\42",
            "\1\44",
            "\1\45",
            "\1\46",
            "",
            "",
            "",
            "\42\35\1\47\uffdd\35",
            "\12\33\1\35\2\33\1\35\24\33\1\32\uffdd\33",
            "",
            "",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\12\33\1\35\2\33\1\35\24\33\1\32\uffdd\33",
            "\1\61",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\101",
            "",
            "",
            "\1\102",
            "\1\103",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\1\106",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            "\1\27\5\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( DIGIT | INTLIT | CHARLIT | STRING_LITERAL | WS | DOT | LPARENTHESE | RPARENTHESE | AT | CALL | NEW | EXEC | PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR | AND | OR | NOT | PLUS | BEFORE | AROUND | AFTER | MIXIN | FILE | WITHIN | LABEL | STRING );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_3 = input.LA(1);

                        s = -1;
                        if ( (LA7_3=='\"') ) {s = 26;}

                        else if ( ((LA7_3>='\u0000' && LA7_3<='\t')||(LA7_3>='\u000B' && LA7_3<='\f')||(LA7_3>='\u000E' && LA7_3<='!')||(LA7_3>='#' && LA7_3<='\uFFFF')) ) {s = 27;}

                        else if ( (LA7_3=='\n'||LA7_3=='\r') ) {s = 29;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA7_39 = input.LA(1);

                        s = -1;
                        if ( (LA7_39=='\"') ) {s = 26;}

                        else if ( ((LA7_39>='\u0000' && LA7_39<='\t')||(LA7_39>='\u000B' && LA7_39<='\f')||(LA7_39>='\u000E' && LA7_39<='!')||(LA7_39>='#' && LA7_39<='\uFFFF')) ) {s = 27;}

                        else if ( (LA7_39=='\n'||LA7_39=='\r') ) {s = 29;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA7_27 = input.LA(1);

                        s = -1;
                        if ( (LA7_27=='\"') ) {s = 26;}

                        else if ( ((LA7_27>='\u0000' && LA7_27<='\t')||(LA7_27>='\u000B' && LA7_27<='\f')||(LA7_27>='\u000E' && LA7_27<='!')||(LA7_27>='#' && LA7_27<='\uFFFF')) ) {s = 27;}

                        else if ( (LA7_27=='\n'||LA7_27=='\r') ) {s = 29;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA7_26 = input.LA(1);

                        s = -1;
                        if ( (LA7_26=='\"') ) {s = 39;}

                        else if ( ((LA7_26>='\u0000' && LA7_26<='!')||(LA7_26>='#' && LA7_26<='\uFFFF')) ) {s = 29;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}