// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 PHPAspect.g 2009-05-18 22:43:19

package org.phpaspect.apdt.internal.core.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PHPAspectLexer extends Lexer {
    public static final int LPARENTHESE=13;
    public static final int ADVICE=5;
    public static final int JOINPOINT=11;
    public static final int WS=26;
    public static final int PAAMAYIM_NEKUDOTAYIM=20;
    public static final int OBJECT_OPERATOR=21;
    public static final int LABEL=19;
    public static final int OR=9;
    public static final int AFTER=17;
    public static final int DOT=27;
    public static final int AND=10;
    public static final int INTLIT=23;
    public static final int EOF=-1;
    public static final int STRING_LITERAL=25;
    public static final int CHARLIT=24;
    public static final int CALL=18;
    public static final int ANNOTATION=4;
    public static final int BEFORE=15;
    public static final int DIGIT=22;
    public static final int RPARENTHESE=14;
    public static final int OPERATOR=6;
    public static final int PARENTHESE=7;
    public static final int AROUND=16;
    public static final int NOT=8;
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
    public String getGrammarFileName() { return "PHPAspect.g"; }

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspect.g:73:3: ( '0' .. '9' )
            // PHPAspect.g:73:5: '0' .. '9'
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
            // PHPAspect.g:77:3: ( ( DIGIT )+ )
            // PHPAspect.g:77:5: ( DIGIT )+
            {
            // PHPAspect.g:77:5: ( DIGIT )+
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
            	    // PHPAspect.g:77:6: DIGIT
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
            // PHPAspect.g:81:3: ( '\\'' . '\\'' )
            // PHPAspect.g:81:5: '\\'' . '\\''
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
            // PHPAspect.g:86:3: ( '\"' ( '\"' '\"' | ~ ( '\"' | '\\n' | '\\r' ) )* ( '\"' | ) )
            // PHPAspect.g:86:5: '\"' ( '\"' '\"' | ~ ( '\"' | '\\n' | '\\r' ) )* ( '\"' | )
            {
            match('\"'); 
            // PHPAspect.g:87:5: ( '\"' '\"' | ~ ( '\"' | '\\n' | '\\r' ) )*
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
            	    // PHPAspect.g:87:7: '\"' '\"'
            	    {
            	    match('\"'); 
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // PHPAspect.g:88:7: ~ ( '\"' | '\\n' | '\\r' )
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

            // PHPAspect.g:90:5: ( '\"' | )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\"') ) {
                alt3=1;
            }
            else {
                alt3=2;}
            switch (alt3) {
                case 1 :
                    // PHPAspect.g:90:7: '\"'
                    {
                    match('\"'); 

                    }
                    break;
                case 2 :
                    // PHPAspect.g:92:5: 
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
            // PHPAspect.g:95:3: ( ( ' ' | '\\n' | '\\r' | '\\t' )+ )
            // PHPAspect.g:95:5: ( ' ' | '\\n' | '\\r' | '\\t' )+
            {
            // PHPAspect.g:95:5: ( ' ' | '\\n' | '\\r' | '\\t' )+
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
            	    // PHPAspect.g:
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
            // PHPAspect.g:98:12: ( '.' )
            // PHPAspect.g:98:14: '.'
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
            // PHPAspect.g:99:17: ( '(' )
            // PHPAspect.g:99:19: '('
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
            // PHPAspect.g:100:17: ( ')' )
            // PHPAspect.g:100:19: ')'
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
            // PHPAspect.g:101:7: ( '@' )
            // PHPAspect.g:101:9: '@'
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
            // PHPAspect.g:102:12: ( 'call' )
            // PHPAspect.g:102:14: 'call'
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

    // $ANTLR start "PAAMAYIM_NEKUDOTAYIM"
    public final void mPAAMAYIM_NEKUDOTAYIM() throws RecognitionException {
        try {
            int _type = PAAMAYIM_NEKUDOTAYIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspect.g:103:22: ( '::' )
            // PHPAspect.g:103:24: '::'
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
            // PHPAspect.g:104:17: ( '->' )
            // PHPAspect.g:104:19: '->'
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
            // PHPAspect.g:105:5: ( '&&' )
            // PHPAspect.g:105:7: '&&'
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
            // PHPAspect.g:106:4: ( '||' )
            // PHPAspect.g:106:6: '||'
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
            // PHPAspect.g:107:5: ( '!' )
            // PHPAspect.g:107:7: '!'
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

    // $ANTLR start "BEFORE"
    public final void mBEFORE() throws RecognitionException {
        try {
            int _type = BEFORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspect.g:108:8: ( 'Before' )
            // PHPAspect.g:108:10: 'Before'
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
            // PHPAspect.g:109:8: ( 'Around' )
            // PHPAspect.g:109:10: 'Around'
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
            // PHPAspect.g:110:7: ( 'After' )
            // PHPAspect.g:110:9: 'After'
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

    // $ANTLR start "LABEL"
    public final void mLABEL() throws RecognitionException {
        try {
            int _type = LABEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // PHPAspect.g:114:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // PHPAspect.g:114:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // PHPAspect.g:114:28: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // PHPAspect.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
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
        // PHPAspect.g:1:8: ( DIGIT | INTLIT | CHARLIT | STRING_LITERAL | WS | DOT | LPARENTHESE | RPARENTHESE | AT | CALL | PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR | AND | OR | NOT | BEFORE | AROUND | AFTER | LABEL )
        int alt6=19;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // PHPAspect.g:1:10: DIGIT
                {
                mDIGIT(); 

                }
                break;
            case 2 :
                // PHPAspect.g:1:16: INTLIT
                {
                mINTLIT(); 

                }
                break;
            case 3 :
                // PHPAspect.g:1:23: CHARLIT
                {
                mCHARLIT(); 

                }
                break;
            case 4 :
                // PHPAspect.g:1:31: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;
            case 5 :
                // PHPAspect.g:1:46: WS
                {
                mWS(); 

                }
                break;
            case 6 :
                // PHPAspect.g:1:49: DOT
                {
                mDOT(); 

                }
                break;
            case 7 :
                // PHPAspect.g:1:53: LPARENTHESE
                {
                mLPARENTHESE(); 

                }
                break;
            case 8 :
                // PHPAspect.g:1:65: RPARENTHESE
                {
                mRPARENTHESE(); 

                }
                break;
            case 9 :
                // PHPAspect.g:1:77: AT
                {
                mAT(); 

                }
                break;
            case 10 :
                // PHPAspect.g:1:80: CALL
                {
                mCALL(); 

                }
                break;
            case 11 :
                // PHPAspect.g:1:85: PAAMAYIM_NEKUDOTAYIM
                {
                mPAAMAYIM_NEKUDOTAYIM(); 

                }
                break;
            case 12 :
                // PHPAspect.g:1:106: OBJECT_OPERATOR
                {
                mOBJECT_OPERATOR(); 

                }
                break;
            case 13 :
                // PHPAspect.g:1:122: AND
                {
                mAND(); 

                }
                break;
            case 14 :
                // PHPAspect.g:1:126: OR
                {
                mOR(); 

                }
                break;
            case 15 :
                // PHPAspect.g:1:129: NOT
                {
                mNOT(); 

                }
                break;
            case 16 :
                // PHPAspect.g:1:133: BEFORE
                {
                mBEFORE(); 

                }
                break;
            case 17 :
                // PHPAspect.g:1:140: AROUND
                {
                mAROUND(); 

                }
                break;
            case 18 :
                // PHPAspect.g:1:147: AFTER
                {
                mAFTER(); 

                }
                break;
            case 19 :
                // PHPAspect.g:1:153: LABEL
                {
                mLABEL(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\1\22\7\uffff\1\21\5\uffff\2\21\3\uffff\10\21\1\40\3\21"+
        "\1\uffff\2\21\1\46\1\47\1\50\3\uffff";
    static final String DFA6_eofS =
        "\51\uffff";
    static final String DFA6_minS =
        "\1\11\1\60\7\uffff\1\141\5\uffff\1\145\1\146\3\uffff\1\154\1\146"+
        "\1\157\1\164\1\154\1\157\1\165\1\145\1\60\1\162\1\156\1\162\1\uffff"+
        "\1\145\1\144\3\60\3\uffff";
    static final String DFA6_maxS =
        "\1\174\1\71\7\uffff\1\141\5\uffff\1\145\1\162\3\uffff\1\154\1\146"+
        "\1\157\1\164\1\154\1\157\1\165\1\145\1\172\1\162\1\156\1\162\1\uffff"+
        "\1\145\1\144\3\172\3\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\uffff\1\13\1\14\1\15\1"+
        "\16\1\17\2\uffff\1\23\1\1\1\2\14\uffff\1\12\5\uffff\1\22\1\20\1"+
        "\21";
    static final String DFA6_specialS =
        "\51\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\4\2\uffff\1\4\22\uffff\1\4\1\16\1\3\3\uffff\1\14\1\2\1\6"+
            "\1\7\3\uffff\1\13\1\5\1\uffff\12\1\1\12\5\uffff\1\10\1\20\1"+
            "\17\30\21\4\uffff\1\21\1\uffff\2\21\1\11\27\21\1\uffff\1\15",
            "\12\23",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\24",
            "",
            "",
            "",
            "",
            "",
            "\1\25",
            "\1\27\13\uffff\1\26",
            "",
            "",
            "",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\1\41",
            "\1\42",
            "\1\43",
            "",
            "\1\44",
            "\1\45",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
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
            return "1:1: Tokens : ( DIGIT | INTLIT | CHARLIT | STRING_LITERAL | WS | DOT | LPARENTHESE | RPARENTHESE | AT | CALL | PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR | AND | OR | NOT | BEFORE | AROUND | AFTER | LABEL );";
        }
    }
 

}