// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 PHPAspectParser.g 2009-05-24 10:44:57

package org.phpaspect.apdt.internal.core.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class PHPAspectParser extends APDTParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DIGIT", "INTLIT", "CHARLIT", "STRING_LITERAL", "WS", "DOT", "LPARENTHESE", "RPARENTHESE", "AT", "CALL", "NEW", "EXEC", "PAAMAYIM_NEKUDOTAYIM", "OBJECT_OPERATOR", "AND", "OR", "NOT", "PLUS", "BEFORE", "AROUND", "AFTER", "MIXIN", "FILE", "WITHIN", "LABEL", "STRING", "ANNOTATION", "PARENTHESE"
    };
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
    public static final int CALL=13;
    public static final int CHARLIT=6;
    public static final int ANNOTATION=30;
    public static final int DIGIT=4;
    public static final int PLUS=21;
    public static final int BEFORE=22;
    public static final int RPARENTHESE=11;
    public static final int PARENTHESE=31;
    public static final int NOT=20;
    public static final int AROUND=23;
    public static final int AT=12;

    // delegates
    // delegators


        public PHPAspectParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PHPAspectParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return PHPAspectParser.tokenNames; }
    public String getGrammarFileName() { return "PHPAspectParser.g"; }


    public static class annotation_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotation"
    // PHPAspectParser.g:22:1: annotation : AT advice LPARENTHESE pointcut RPARENTHESE -> ^( ANNOTATION advice pointcut ) ;
    public final PHPAspectParser.annotation_return annotation() throws RecognitionException {
        PHPAspectParser.annotation_return retval = new PHPAspectParser.annotation_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken AT1=null;
        CommonToken LPARENTHESE3=null;
        CommonToken RPARENTHESE5=null;
        PHPAspectParser.advice_return advice2 = null;

        PHPAspectParser.pointcut_return pointcut4 = null;


        PHPAspectCommonTree AT1_tree=null;
        PHPAspectCommonTree LPARENTHESE3_tree=null;
        PHPAspectCommonTree RPARENTHESE5_tree=null;
        RewriteRuleTokenStream stream_LPARENTHESE=new RewriteRuleTokenStream(adaptor,"token LPARENTHESE");
        RewriteRuleTokenStream stream_RPARENTHESE=new RewriteRuleTokenStream(adaptor,"token RPARENTHESE");
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleSubtreeStream stream_pointcut=new RewriteRuleSubtreeStream(adaptor,"rule pointcut");
        RewriteRuleSubtreeStream stream_advice=new RewriteRuleSubtreeStream(adaptor,"rule advice");
        try {
            // PHPAspectParser.g:23:1: ( AT advice LPARENTHESE pointcut RPARENTHESE -> ^( ANNOTATION advice pointcut ) )
            // PHPAspectParser.g:23:3: AT advice LPARENTHESE pointcut RPARENTHESE
            {
            AT1=(CommonToken)match(input,AT,FOLLOW_AT_in_annotation65);  
            stream_AT.add(AT1);

            pushFollow(FOLLOW_advice_in_annotation67);
            advice2=advice();

            state._fsp--;

            stream_advice.add(advice2.getTree());
            LPARENTHESE3=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_annotation69);  
            stream_LPARENTHESE.add(LPARENTHESE3);

            pushFollow(FOLLOW_pointcut_in_annotation71);
            pointcut4=pointcut();

            state._fsp--;

            stream_pointcut.add(pointcut4.getTree());
            RPARENTHESE5=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_annotation73);  
            stream_RPARENTHESE.add(RPARENTHESE5);



            // AST REWRITE
            // elements: pointcut, advice
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (PHPAspectCommonTree)adaptor.nil();
            // 24:1: -> ^( ANNOTATION advice pointcut )
            {
                // PHPAspectParser.g:24:4: ^( ANNOTATION advice pointcut )
                {
                PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                root_1 = (PHPAspectCommonTree)adaptor.becomeRoot((PHPAspectCommonTree)adaptor.create(ANNOTATION, "ANNOTATION"), root_1);

                adaptor.addChild(root_1, stream_advice.nextTree());
                adaptor.addChild(root_1, stream_pointcut.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "annotation"

    public static class advice_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "advice"
    // PHPAspectParser.g:27:1: advice : ( BEFORE | AROUND | AFTER );
    public final PHPAspectParser.advice_return advice() throws RecognitionException {
        PHPAspectParser.advice_return retval = new PHPAspectParser.advice_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken set6=null;

        PHPAspectCommonTree set6_tree=null;

        try {
            // PHPAspectParser.g:28:1: ( BEFORE | AROUND | AFTER )
            // PHPAspectParser.g:
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            set6=(CommonToken)input.LT(1);
            if ( (input.LA(1)>=BEFORE && input.LA(1)<=AFTER) ) {
                input.consume();
                adaptor.addChild(root_0, (PHPAspectCommonTree)adaptor.create(set6));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "advice"

    public static class pointcut_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcut"
    // PHPAspectParser.g:34:1: pointcut : or_joinpoint ;
    public final PHPAspectParser.pointcut_return pointcut() throws RecognitionException {
        PHPAspectParser.pointcut_return retval = new PHPAspectParser.pointcut_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        PHPAspectParser.or_joinpoint_return or_joinpoint7 = null;



        try {
            // PHPAspectParser.g:35:1: ( or_joinpoint )
            // PHPAspectParser.g:35:3: or_joinpoint
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            pushFollow(FOLLOW_or_joinpoint_in_pointcut110);
            or_joinpoint7=or_joinpoint();

            state._fsp--;

            adaptor.addChild(root_0, or_joinpoint7.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "pointcut"

    public static class or_joinpoint_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "or_joinpoint"
    // PHPAspectParser.g:38:1: or_joinpoint : pt1= and_joinpoint ( OR pt2= and_joinpoint )* ;
    public final PHPAspectParser.or_joinpoint_return or_joinpoint() throws RecognitionException {
        PHPAspectParser.or_joinpoint_return retval = new PHPAspectParser.or_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken OR8=null;
        PHPAspectParser.and_joinpoint_return pt1 = null;

        PHPAspectParser.and_joinpoint_return pt2 = null;


        PHPAspectCommonTree OR8_tree=null;

        try {
            // PHPAspectParser.g:39:1: (pt1= and_joinpoint ( OR pt2= and_joinpoint )* )
            // PHPAspectParser.g:39:3: pt1= and_joinpoint ( OR pt2= and_joinpoint )*
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            pushFollow(FOLLOW_and_joinpoint_in_or_joinpoint121);
            pt1=and_joinpoint();

            state._fsp--;

            adaptor.addChild(root_0, pt1.getTree());
            // PHPAspectParser.g:39:21: ( OR pt2= and_joinpoint )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // PHPAspectParser.g:39:22: OR pt2= and_joinpoint
            	    {
            	    OR8=(CommonToken)match(input,OR,FOLLOW_OR_in_or_joinpoint124); 
            	    OR8_tree = (PHPAspectCommonTree)adaptor.create(OR8);
            	    root_0 = (PHPAspectCommonTree)adaptor.becomeRoot(OR8_tree, root_0);

            	    pushFollow(FOLLOW_and_joinpoint_in_or_joinpoint129);
            	    pt2=and_joinpoint();

            	    state._fsp--;

            	    adaptor.addChild(root_0, pt2.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "or_joinpoint"

    public static class and_joinpoint_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_joinpoint"
    // PHPAspectParser.g:42:1: and_joinpoint : pt1= not_joinpoint ( AND pt2= not_joinpoint )* ;
    public final PHPAspectParser.and_joinpoint_return and_joinpoint() throws RecognitionException {
        PHPAspectParser.and_joinpoint_return retval = new PHPAspectParser.and_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken AND9=null;
        PHPAspectParser.not_joinpoint_return pt1 = null;

        PHPAspectParser.not_joinpoint_return pt2 = null;


        PHPAspectCommonTree AND9_tree=null;

        try {
            // PHPAspectParser.g:43:1: (pt1= not_joinpoint ( AND pt2= not_joinpoint )* )
            // PHPAspectParser.g:43:3: pt1= not_joinpoint ( AND pt2= not_joinpoint )*
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            pushFollow(FOLLOW_not_joinpoint_in_and_joinpoint142);
            pt1=not_joinpoint();

            state._fsp--;

            adaptor.addChild(root_0, pt1.getTree());
            // PHPAspectParser.g:43:21: ( AND pt2= not_joinpoint )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // PHPAspectParser.g:43:22: AND pt2= not_joinpoint
            	    {
            	    AND9=(CommonToken)match(input,AND,FOLLOW_AND_in_and_joinpoint145); 
            	    AND9_tree = (PHPAspectCommonTree)adaptor.create(AND9);
            	    root_0 = (PHPAspectCommonTree)adaptor.becomeRoot(AND9_tree, root_0);

            	    pushFollow(FOLLOW_not_joinpoint_in_and_joinpoint150);
            	    pt2=not_joinpoint();

            	    state._fsp--;

            	    adaptor.addChild(root_0, pt2.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_joinpoint"

    public static class not_joinpoint_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "not_joinpoint"
    // PHPAspectParser.g:46:1: not_joinpoint : ( NOT )? joinpoint ;
    public final PHPAspectParser.not_joinpoint_return not_joinpoint() throws RecognitionException {
        PHPAspectParser.not_joinpoint_return retval = new PHPAspectParser.not_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken NOT10=null;
        PHPAspectParser.joinpoint_return joinpoint11 = null;


        PHPAspectCommonTree NOT10_tree=null;

        try {
            // PHPAspectParser.g:47:1: ( ( NOT )? joinpoint )
            // PHPAspectParser.g:47:3: ( NOT )? joinpoint
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            // PHPAspectParser.g:47:3: ( NOT )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NOT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // PHPAspectParser.g:47:4: NOT
                    {
                    NOT10=(CommonToken)match(input,NOT,FOLLOW_NOT_in_not_joinpoint162); 
                    NOT10_tree = (PHPAspectCommonTree)adaptor.create(NOT10);
                    root_0 = (PHPAspectCommonTree)adaptor.becomeRoot(NOT10_tree, root_0);


                    }
                    break;

            }

            pushFollow(FOLLOW_joinpoint_in_not_joinpoint167);
            joinpoint11=joinpoint();

            state._fsp--;

            adaptor.addChild(root_0, joinpoint11.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "not_joinpoint"

    public static class joinpoint_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "joinpoint"
    // PHPAspectParser.g:51:1: joinpoint : ( CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE -> ^( CALL $type resolution $method) | NEW LPARENTHESE type= LABEL RPARENTHESE -> ^( NEW $type) | FILE LPARENTHESE string= STRING RPARENTHESE -> ^( FILE $string) | WITHIN LPARENTHESE type= LABEL RPARENTHESE -> ^( WITHIN $type) | EXEC LPARENTHESE type= LABEL PAAMAYIM_NEKUDOTAYIM method= LABEL RPARENTHESE -> ^( EXEC $type $method) | LPARENTHESE or_joinpoint RPARENTHESE -> ^( PARENTHESE or_joinpoint ) | MIXIN LPARENTHESE type= LABEL LPARENTHESE -> ^( MIXIN type ) );
    public final PHPAspectParser.joinpoint_return joinpoint() throws RecognitionException {
        PHPAspectParser.joinpoint_return retval = new PHPAspectParser.joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken type=null;
        CommonToken method=null;
        CommonToken string=null;
        CommonToken CALL12=null;
        CommonToken LPARENTHESE13=null;
        CommonToken RPARENTHESE15=null;
        CommonToken NEW16=null;
        CommonToken LPARENTHESE17=null;
        CommonToken RPARENTHESE18=null;
        CommonToken FILE19=null;
        CommonToken LPARENTHESE20=null;
        CommonToken RPARENTHESE21=null;
        CommonToken WITHIN22=null;
        CommonToken LPARENTHESE23=null;
        CommonToken RPARENTHESE24=null;
        CommonToken EXEC25=null;
        CommonToken LPARENTHESE26=null;
        CommonToken PAAMAYIM_NEKUDOTAYIM27=null;
        CommonToken RPARENTHESE28=null;
        CommonToken LPARENTHESE29=null;
        CommonToken RPARENTHESE31=null;
        CommonToken MIXIN32=null;
        CommonToken LPARENTHESE33=null;
        CommonToken LPARENTHESE34=null;
        PHPAspectParser.resolution_return resolution14 = null;

        PHPAspectParser.or_joinpoint_return or_joinpoint30 = null;


        PHPAspectCommonTree type_tree=null;
        PHPAspectCommonTree method_tree=null;
        PHPAspectCommonTree string_tree=null;
        PHPAspectCommonTree CALL12_tree=null;
        PHPAspectCommonTree LPARENTHESE13_tree=null;
        PHPAspectCommonTree RPARENTHESE15_tree=null;
        PHPAspectCommonTree NEW16_tree=null;
        PHPAspectCommonTree LPARENTHESE17_tree=null;
        PHPAspectCommonTree RPARENTHESE18_tree=null;
        PHPAspectCommonTree FILE19_tree=null;
        PHPAspectCommonTree LPARENTHESE20_tree=null;
        PHPAspectCommonTree RPARENTHESE21_tree=null;
        PHPAspectCommonTree WITHIN22_tree=null;
        PHPAspectCommonTree LPARENTHESE23_tree=null;
        PHPAspectCommonTree RPARENTHESE24_tree=null;
        PHPAspectCommonTree EXEC25_tree=null;
        PHPAspectCommonTree LPARENTHESE26_tree=null;
        PHPAspectCommonTree PAAMAYIM_NEKUDOTAYIM27_tree=null;
        PHPAspectCommonTree RPARENTHESE28_tree=null;
        PHPAspectCommonTree LPARENTHESE29_tree=null;
        PHPAspectCommonTree RPARENTHESE31_tree=null;
        PHPAspectCommonTree MIXIN32_tree=null;
        PHPAspectCommonTree LPARENTHESE33_tree=null;
        PHPAspectCommonTree LPARENTHESE34_tree=null;
        RewriteRuleTokenStream stream_PAAMAYIM_NEKUDOTAYIM=new RewriteRuleTokenStream(adaptor,"token PAAMAYIM_NEKUDOTAYIM");
        RewriteRuleTokenStream stream_WITHIN=new RewriteRuleTokenStream(adaptor,"token WITHIN");
        RewriteRuleTokenStream stream_LPARENTHESE=new RewriteRuleTokenStream(adaptor,"token LPARENTHESE");
        RewriteRuleTokenStream stream_LABEL=new RewriteRuleTokenStream(adaptor,"token LABEL");
        RewriteRuleTokenStream stream_FILE=new RewriteRuleTokenStream(adaptor,"token FILE");
        RewriteRuleTokenStream stream_RPARENTHESE=new RewriteRuleTokenStream(adaptor,"token RPARENTHESE");
        RewriteRuleTokenStream stream_MIXIN=new RewriteRuleTokenStream(adaptor,"token MIXIN");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_NEW=new RewriteRuleTokenStream(adaptor,"token NEW");
        RewriteRuleTokenStream stream_EXEC=new RewriteRuleTokenStream(adaptor,"token EXEC");
        RewriteRuleTokenStream stream_CALL=new RewriteRuleTokenStream(adaptor,"token CALL");
        RewriteRuleSubtreeStream stream_or_joinpoint=new RewriteRuleSubtreeStream(adaptor,"rule or_joinpoint");
        RewriteRuleSubtreeStream stream_resolution=new RewriteRuleSubtreeStream(adaptor,"rule resolution");
        try {
            // PHPAspectParser.g:52:1: ( CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE -> ^( CALL $type resolution $method) | NEW LPARENTHESE type= LABEL RPARENTHESE -> ^( NEW $type) | FILE LPARENTHESE string= STRING RPARENTHESE -> ^( FILE $string) | WITHIN LPARENTHESE type= LABEL RPARENTHESE -> ^( WITHIN $type) | EXEC LPARENTHESE type= LABEL PAAMAYIM_NEKUDOTAYIM method= LABEL RPARENTHESE -> ^( EXEC $type $method) | LPARENTHESE or_joinpoint RPARENTHESE -> ^( PARENTHESE or_joinpoint ) | MIXIN LPARENTHESE type= LABEL LPARENTHESE -> ^( MIXIN type ) )
            int alt4=7;
            switch ( input.LA(1) ) {
            case CALL:
                {
                alt4=1;
                }
                break;
            case NEW:
                {
                alt4=2;
                }
                break;
            case FILE:
                {
                alt4=3;
                }
                break;
            case WITHIN:
                {
                alt4=4;
                }
                break;
            case EXEC:
                {
                alt4=5;
                }
                break;
            case LPARENTHESE:
                {
                alt4=6;
                }
                break;
            case MIXIN:
                {
                alt4=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // PHPAspectParser.g:52:3: CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE
                    {
                    CALL12=(CommonToken)match(input,CALL,FOLLOW_CALL_in_joinpoint177);  
                    stream_CALL.add(CALL12);

                    LPARENTHESE13=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint179);  
                    stream_LPARENTHESE.add(LPARENTHESE13);

                    type=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint183);  
                    stream_LABEL.add(type);

                    pushFollow(FOLLOW_resolution_in_joinpoint185);
                    resolution14=resolution();

                    state._fsp--;

                    stream_resolution.add(resolution14.getTree());
                    method=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint189);  
                    stream_LABEL.add(method);

                    RPARENTHESE15=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint191);  
                    stream_RPARENTHESE.add(RPARENTHESE15);



                    // AST REWRITE
                    // elements: method, resolution, CALL, type
                    // token labels: type, method
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_type=new RewriteRuleTokenStream(adaptor,"token type",type);
                    RewriteRuleTokenStream stream_method=new RewriteRuleTokenStream(adaptor,"token method",method);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PHPAspectCommonTree)adaptor.nil();
                    // 53:2: -> ^( CALL $type resolution $method)
                    {
                        // PHPAspectParser.g:53:5: ^( CALL $type resolution $method)
                        {
                        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_CALL.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_type.nextNode());
                        adaptor.addChild(root_1, stream_resolution.nextTree());
                        adaptor.addChild(root_1, stream_method.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // PHPAspectParser.g:54:3: NEW LPARENTHESE type= LABEL RPARENTHESE
                    {
                    NEW16=(CommonToken)match(input,NEW,FOLLOW_NEW_in_joinpoint210);  
                    stream_NEW.add(NEW16);

                    LPARENTHESE17=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint212);  
                    stream_LPARENTHESE.add(LPARENTHESE17);

                    type=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint216);  
                    stream_LABEL.add(type);

                    RPARENTHESE18=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint218);  
                    stream_RPARENTHESE.add(RPARENTHESE18);



                    // AST REWRITE
                    // elements: type, NEW
                    // token labels: type
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_type=new RewriteRuleTokenStream(adaptor,"token type",type);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PHPAspectCommonTree)adaptor.nil();
                    // 55:2: -> ^( NEW $type)
                    {
                        // PHPAspectParser.g:55:5: ^( NEW $type)
                        {
                        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_NEW.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_type.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // PHPAspectParser.g:56:3: FILE LPARENTHESE string= STRING RPARENTHESE
                    {
                    FILE19=(CommonToken)match(input,FILE,FOLLOW_FILE_in_joinpoint232);  
                    stream_FILE.add(FILE19);

                    LPARENTHESE20=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint234);  
                    stream_LPARENTHESE.add(LPARENTHESE20);

                    string=(CommonToken)match(input,STRING,FOLLOW_STRING_in_joinpoint238);  
                    stream_STRING.add(string);

                    RPARENTHESE21=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint240);  
                    stream_RPARENTHESE.add(RPARENTHESE21);



                    // AST REWRITE
                    // elements: FILE, string
                    // token labels: string
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_string=new RewriteRuleTokenStream(adaptor,"token string",string);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PHPAspectCommonTree)adaptor.nil();
                    // 57:2: -> ^( FILE $string)
                    {
                        // PHPAspectParser.g:57:5: ^( FILE $string)
                        {
                        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_FILE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_string.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // PHPAspectParser.g:58:3: WITHIN LPARENTHESE type= LABEL RPARENTHESE
                    {
                    WITHIN22=(CommonToken)match(input,WITHIN,FOLLOW_WITHIN_in_joinpoint254);  
                    stream_WITHIN.add(WITHIN22);

                    LPARENTHESE23=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint256);  
                    stream_LPARENTHESE.add(LPARENTHESE23);

                    type=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint260);  
                    stream_LABEL.add(type);

                    RPARENTHESE24=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint262);  
                    stream_RPARENTHESE.add(RPARENTHESE24);



                    // AST REWRITE
                    // elements: WITHIN, type
                    // token labels: type
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_type=new RewriteRuleTokenStream(adaptor,"token type",type);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PHPAspectCommonTree)adaptor.nil();
                    // 59:2: -> ^( WITHIN $type)
                    {
                        // PHPAspectParser.g:59:5: ^( WITHIN $type)
                        {
                        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_WITHIN.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_type.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // PHPAspectParser.g:60:3: EXEC LPARENTHESE type= LABEL PAAMAYIM_NEKUDOTAYIM method= LABEL RPARENTHESE
                    {
                    EXEC25=(CommonToken)match(input,EXEC,FOLLOW_EXEC_in_joinpoint276);  
                    stream_EXEC.add(EXEC25);

                    LPARENTHESE26=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint278);  
                    stream_LPARENTHESE.add(LPARENTHESE26);

                    type=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint282);  
                    stream_LABEL.add(type);

                    PAAMAYIM_NEKUDOTAYIM27=(CommonToken)match(input,PAAMAYIM_NEKUDOTAYIM,FOLLOW_PAAMAYIM_NEKUDOTAYIM_in_joinpoint284);  
                    stream_PAAMAYIM_NEKUDOTAYIM.add(PAAMAYIM_NEKUDOTAYIM27);

                    method=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint288);  
                    stream_LABEL.add(method);

                    RPARENTHESE28=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint290);  
                    stream_RPARENTHESE.add(RPARENTHESE28);



                    // AST REWRITE
                    // elements: method, type, EXEC
                    // token labels: type, method
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_type=new RewriteRuleTokenStream(adaptor,"token type",type);
                    RewriteRuleTokenStream stream_method=new RewriteRuleTokenStream(adaptor,"token method",method);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PHPAspectCommonTree)adaptor.nil();
                    // 61:2: -> ^( EXEC $type $method)
                    {
                        // PHPAspectParser.g:61:5: ^( EXEC $type $method)
                        {
                        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_EXEC.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_type.nextNode());
                        adaptor.addChild(root_1, stream_method.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // PHPAspectParser.g:62:3: LPARENTHESE or_joinpoint RPARENTHESE
                    {
                    LPARENTHESE29=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint307);  
                    stream_LPARENTHESE.add(LPARENTHESE29);

                    pushFollow(FOLLOW_or_joinpoint_in_joinpoint309);
                    or_joinpoint30=or_joinpoint();

                    state._fsp--;

                    stream_or_joinpoint.add(or_joinpoint30.getTree());
                    RPARENTHESE31=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint311);  
                    stream_RPARENTHESE.add(RPARENTHESE31);



                    // AST REWRITE
                    // elements: or_joinpoint
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PHPAspectCommonTree)adaptor.nil();
                    // 63:2: -> ^( PARENTHESE or_joinpoint )
                    {
                        // PHPAspectParser.g:63:5: ^( PARENTHESE or_joinpoint )
                        {
                        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot((PHPAspectCommonTree)adaptor.create(PARENTHESE, "PARENTHESE"), root_1);

                        adaptor.addChild(root_1, stream_or_joinpoint.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // PHPAspectParser.g:64:3: MIXIN LPARENTHESE type= LABEL LPARENTHESE
                    {
                    MIXIN32=(CommonToken)match(input,MIXIN,FOLLOW_MIXIN_in_joinpoint324);  
                    stream_MIXIN.add(MIXIN32);

                    LPARENTHESE33=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint326);  
                    stream_LPARENTHESE.add(LPARENTHESE33);

                    type=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint330);  
                    stream_LABEL.add(type);

                    LPARENTHESE34=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint332);  
                    stream_LPARENTHESE.add(LPARENTHESE34);



                    // AST REWRITE
                    // elements: type, MIXIN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PHPAspectCommonTree)adaptor.nil();
                    // 65:2: -> ^( MIXIN type )
                    {
                        // PHPAspectParser.g:65:5: ^( MIXIN type )
                        {
                        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_MIXIN.nextNode(), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "joinpoint"

    public static class resolution_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "resolution"
    // PHPAspectParser.g:68:1: resolution : ( PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR );
    public final PHPAspectParser.resolution_return resolution() throws RecognitionException {
        PHPAspectParser.resolution_return retval = new PHPAspectParser.resolution_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken set35=null;

        PHPAspectCommonTree set35_tree=null;

        try {
            // PHPAspectParser.g:69:1: ( PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR )
            // PHPAspectParser.g:
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            set35=(CommonToken)input.LT(1);
            if ( (input.LA(1)>=PAAMAYIM_NEKUDOTAYIM && input.LA(1)<=OBJECT_OPERATOR) ) {
                input.consume();
                adaptor.addChild(root_0, (PHPAspectCommonTree)adaptor.create(set35));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (PHPAspectCommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (PHPAspectCommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "resolution"

    // Delegated rules


 

    public static final BitSet FOLLOW_AT_in_annotation65 = new BitSet(new long[]{0x0000000001C00000L});
    public static final BitSet FOLLOW_advice_in_annotation67 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_annotation69 = new BitSet(new long[]{0x000000000E10E400L});
    public static final BitSet FOLLOW_pointcut_in_annotation71 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RPARENTHESE_in_annotation73 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_advice0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_joinpoint_in_pointcut110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_joinpoint_in_or_joinpoint121 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_OR_in_or_joinpoint124 = new BitSet(new long[]{0x000000000E10E400L});
    public static final BitSet FOLLOW_and_joinpoint_in_or_joinpoint129 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_not_joinpoint_in_and_joinpoint142 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_AND_in_and_joinpoint145 = new BitSet(new long[]{0x000000000E10E400L});
    public static final BitSet FOLLOW_not_joinpoint_in_and_joinpoint150 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_NOT_in_not_joinpoint162 = new BitSet(new long[]{0x000000000E10E400L});
    public static final BitSet FOLLOW_joinpoint_in_not_joinpoint167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_joinpoint177 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint179 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint183 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_resolution_in_joinpoint185 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint189 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_joinpoint210 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint212 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint216 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_joinpoint232 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint234 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_joinpoint238 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WITHIN_in_joinpoint254 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint256 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint260 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_joinpoint276 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint278 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint282 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_PAAMAYIM_NEKUDOTAYIM_in_joinpoint284 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint288 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint307 = new BitSet(new long[]{0x000000000E10E400L});
    public static final BitSet FOLLOW_or_joinpoint_in_joinpoint309 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXIN_in_joinpoint324 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint326 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint330 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_resolution0 = new BitSet(new long[]{0x0000000000000002L});

}