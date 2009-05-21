// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 PHPAspectLexer.g 2009-05-21 18:15:53

package org.phpaspect.apdt.internal.core.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PHPAspectLexer extends APDTLexer {
    public static final int LPARENTHESE=10;
    public static final int WS=8;
    public static final int NEW=14;
    public static final int PAAMAYIM_NEKUDOTAYIM=15;
    public static final int LABEL=25;
    public static final int OBJECT_OPERATOR=16;
    public static final int MIXIN=24;
    public static final int OR=18;
    public static final int AFTER=23;
    public static final int DOT=9;
    public static final int AND=17;
    public static final int INTLIT=5;
    public static final int EOF=-1;
    public static final int STRING_LITERAL=7;
    public static final int CHARLIT=6;
    public static final int CALL=13;
    public static final int BEFORE=21;
    public static final int PLUS=20;
    public static final int DIGIT=4;
    public static final int RPARENTHESE=11;
    public static final int AROUND=22;
    public static final int NOT=19;
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

    // $ANTLR start "PAAMAYIM_NEKUDOTAYIM"
    public final void mPAAMAYIM_NEKUDOTAYIM() throws RecognitionException {
        try {
            int _type = PAAMAYIM_NEKUDOTAYIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:44:22: ( '::' )
            // PHPAspectLexer.g:44:24: '::'
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
            // PHPAspectLexer.g:45:17: ( '->' )
            // PHPAspectLexer.g:45:19: '->'
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
            // PHPAspectLexer.g:46:5: ( '&&' )
            // PHPAspectLexer.g:46:7: '&&'
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
            // PHPAspectLexer.g:47:4: ( '||' )
            // PHPAspectLexer.g:47:6: '||'
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
            // PHPAspectLexer.g:48:5: ( '!' )
            // PHPAspectLexer.g:48:7: '!'
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
            // PHPAspectLexer.g:49:6: ( '+' )
            // PHPAspectLexer.g:49:8: '+'
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
            // PHPAspectLexer.g:50:8: ( 'Before' )
            // PHPAspectLexer.g:50:10: 'Before'
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
            // PHPAspectLexer.g:51:8: ( 'Around' )
            // PHPAspectLexer.g:51:10: 'Around'
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
            // PHPAspectLexer.g:52:7: ( 'After' )
            // PHPAspectLexer.g:52:9: 'After'
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
            // PHPAspectLexer.g:53:7: ( 'Mixin' )
            // PHPAspectLexer.g:53:9: 'Mixin'
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

    // $ANTLR start "LABEL"
    public final void mLABEL() throws RecognitionException {
        try {
            int _type = LABEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspectLexer.g:57:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '*' ) ( '*' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // PHPAspectLexer.g:57:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '*' ) ( '*' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( input.LA(1)=='*'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // PHPAspectLexer.g:57:32: ( '*' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
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

    public void mTokens() throws RecognitionException {
        // PHPAspectLexer.g:1:8: ( DIGIT | INTLIT | CHARLIT | STRING_LITERAL | WS | DOT | LPARENTHESE | RPARENTHESE | AT | CALL | NEW | PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR | AND | OR | NOT | PLUS | BEFORE | AROUND | AFTER | MIXIN | LABEL )
        int alt6=22;
        alt6 = dfa6.predict(input);
        switch (alt6) {
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
                // PHPAspectLexer.g:1:89: PAAMAYIM_NEKUDOTAYIM
                {
                mPAAMAYIM_NEKUDOTAYIM(); 

                }
                break;
            case 13 :
                // PHPAspectLexer.g:1:110: OBJECT_OPERATOR
                {
                mOBJECT_OPERATOR(); 

                }
                break;
            case 14 :
                // PHPAspectLexer.g:1:126: AND
                {
                mAND(); 

                }
                break;
            case 15 :
                // PHPAspectLexer.g:1:130: OR
                {
                mOR(); 

                }
                break;
            case 16 :
                // PHPAspectLexer.g:1:133: NOT
                {
                mNOT(); 

                }
                break;
            case 17 :
                // PHPAspectLexer.g:1:137: PLUS
                {
                mPLUS(); 

                }
                break;
            case 18 :
                // PHPAspectLexer.g:1:142: BEFORE
                {
                mBEFORE(); 

                }
                break;
            case 19 :
                // PHPAspectLexer.g:1:149: AROUND
                {
                mAROUND(); 

                }
                break;
            case 20 :
                // PHPAspectLexer.g:1:156: AFTER
                {
                mAFTER(); 

                }
                break;
            case 21 :
                // PHPAspectLexer.g:1:162: MIXIN
                {
                mMIXIN(); 

                }
                break;
            case 22 :
                // PHPAspectLexer.g:1:168: LABEL
                {
                mLABEL(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\1\25\7\uffff\2\24\6\uffff\3\24\3\uffff\7\24\1\44\4\24\1"+
        "\51\1\uffff\4\24\1\uffff\2\24\1\60\1\61\1\62\1\63\4\uffff";
    static final String DFA6_eofS =
        "\64\uffff";
    static final String DFA6_minS =
        "\1\11\1\60\7\uffff\1\141\1\145\6\uffff\1\145\1\146\1\151\3\uffff"+
        "\1\154\1\167\1\146\1\157\1\164\1\170\1\154\1\52\1\157\1\165\1\145"+
        "\1\151\1\52\1\uffff\1\162\1\156\1\162\1\156\1\uffff\1\145\1\144"+
        "\4\52\4\uffff";
    static final String DFA6_maxS =
        "\1\174\1\71\7\uffff\1\141\1\145\6\uffff\1\145\1\162\1\151\3\uffff"+
        "\1\154\1\167\1\146\1\157\1\164\1\170\1\154\1\172\1\157\1\165\1\145"+
        "\1\151\1\172\1\uffff\1\162\1\156\1\162\1\156\1\uffff\1\145\1\144"+
        "\4\172\4\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\2\uffff\1\14\1\15\1\16\1"+
        "\17\1\20\1\21\3\uffff\1\26\1\1\1\2\15\uffff\1\13\4\uffff\1\12\6"+
        "\uffff\1\24\1\25\1\22\1\23";
    static final String DFA6_specialS =
        "\64\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\4\2\uffff\1\4\22\uffff\1\4\1\17\1\3\3\uffff\1\15\1\2\1\6"+
            "\1\7\1\24\1\20\1\uffff\1\14\1\5\1\uffff\12\1\1\13\5\uffff\1"+
            "\10\1\22\1\21\12\24\1\23\15\24\4\uffff\1\24\1\uffff\2\24\1\11"+
            "\12\24\1\12\14\24\1\uffff\1\16",
            "\12\26",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\27",
            "\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\31",
            "\1\33\13\uffff\1\32",
            "\1\34",
            "",
            "",
            "",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\24\5\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\24\5\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "",
            "\1\56",
            "\1\57",
            "\1\24\5\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\24\5\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\24\5\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\24\5\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( DIGIT | INTLIT | CHARLIT | STRING_LITERAL | WS | DOT | LPARENTHESE | RPARENTHESE | AT | CALL | NEW | PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR | AND | OR | NOT | PLUS | BEFORE | AROUND | AFTER | MIXIN | LABEL );";
        }
    }
 

}