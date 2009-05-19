// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 PHPAspect.g 2009-05-18 22:43:19

package org.phpaspect.apdt.internal.core.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class PHPAspectParser extends APDTParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ANNOTATION", "ADVICE", "OPERATOR", "PARENTHESE", "NOT", "OR", "AND", "JOINPOINT", "AT", "LPARENTHESE", "RPARENTHESE", "BEFORE", "AROUND", "AFTER", "CALL", "LABEL", "PAAMAYIM_NEKUDOTAYIM", "OBJECT_OPERATOR", "DIGIT", "INTLIT", "CHARLIT", "STRING_LITERAL", "WS", "DOT"
    };
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
    public static final int DIGIT=22;
    public static final int BEFORE=15;
    public static final int OPERATOR=6;
    public static final int RPARENTHESE=14;
    public static final int PARENTHESE=7;
    public static final int NOT=8;
    public static final int AROUND=16;
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
    public String getGrammarFileName() { return "PHPAspect.g"; }


    public static class annotation_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotation"
    // PHPAspect.g:30:1: annotation : AT advice LPARENTHESE pointcut RPARENTHESE -> ^( ANNOTATION advice pointcut ) ;
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
            // PHPAspect.g:31:3: ( AT advice LPARENTHESE pointcut RPARENTHESE -> ^( ANNOTATION advice pointcut ) )
            // PHPAspect.g:31:5: AT advice LPARENTHESE pointcut RPARENTHESE
            {
            AT1=(CommonToken)match(input,AT,FOLLOW_AT_in_annotation84);  
            stream_AT.add(AT1);

            pushFollow(FOLLOW_advice_in_annotation86);
            advice2=advice();

            state._fsp--;

            stream_advice.add(advice2.getTree());
            LPARENTHESE3=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_annotation88);  
            stream_LPARENTHESE.add(LPARENTHESE3);

            pushFollow(FOLLOW_pointcut_in_annotation90);
            pointcut4=pointcut();

            state._fsp--;

            stream_pointcut.add(pointcut4.getTree());
            RPARENTHESE5=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_annotation92);  
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
            // 32:4: -> ^( ANNOTATION advice pointcut )
            {
                // PHPAspect.g:32:7: ^( ANNOTATION advice pointcut )
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
    // PHPAspect.g:35:1: advice : ( BEFORE | AROUND | AFTER );
    public final PHPAspectParser.advice_return advice() throws RecognitionException {
        PHPAspectParser.advice_return retval = new PHPAspectParser.advice_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken set6=null;

        PHPAspectCommonTree set6_tree=null;

        try {
            // PHPAspect.g:36:3: ( BEFORE | AROUND | AFTER )
            // PHPAspect.g:
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
    // PHPAspect.g:42:1: pointcut : or_joinpoint ;
    public final PHPAspectParser.pointcut_return pointcut() throws RecognitionException {
        PHPAspectParser.pointcut_return retval = new PHPAspectParser.pointcut_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        PHPAspectParser.or_joinpoint_return or_joinpoint7 = null;



        try {
            // PHPAspect.g:43:2: ( or_joinpoint )
            // PHPAspect.g:43:4: or_joinpoint
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            pushFollow(FOLLOW_or_joinpoint_in_pointcut146);
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
    // PHPAspect.g:46:1: or_joinpoint : pt1= and_joinpoint ( OR pt2= and_joinpoint )* ;
    public final PHPAspectParser.or_joinpoint_return or_joinpoint() throws RecognitionException {
        PHPAspectParser.or_joinpoint_return retval = new PHPAspectParser.or_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken OR8=null;
        PHPAspectParser.and_joinpoint_return pt1 = null;

        PHPAspectParser.and_joinpoint_return pt2 = null;


        PHPAspectCommonTree OR8_tree=null;

        try {
            // PHPAspect.g:47:2: (pt1= and_joinpoint ( OR pt2= and_joinpoint )* )
            // PHPAspect.g:47:4: pt1= and_joinpoint ( OR pt2= and_joinpoint )*
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            pushFollow(FOLLOW_and_joinpoint_in_or_joinpoint160);
            pt1=and_joinpoint();

            state._fsp--;

            adaptor.addChild(root_0, pt1.getTree());
            // PHPAspect.g:47:22: ( OR pt2= and_joinpoint )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // PHPAspect.g:47:23: OR pt2= and_joinpoint
            	    {
            	    OR8=(CommonToken)match(input,OR,FOLLOW_OR_in_or_joinpoint163); 
            	    OR8_tree = (PHPAspectCommonTree)adaptor.create(OR8);
            	    root_0 = (PHPAspectCommonTree)adaptor.becomeRoot(OR8_tree, root_0);

            	    pushFollow(FOLLOW_and_joinpoint_in_or_joinpoint168);
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
    // PHPAspect.g:50:1: and_joinpoint : pt1= not_joinpoint ( AND pt2= not_joinpoint )* ;
    public final PHPAspectParser.and_joinpoint_return and_joinpoint() throws RecognitionException {
        PHPAspectParser.and_joinpoint_return retval = new PHPAspectParser.and_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken AND9=null;
        PHPAspectParser.not_joinpoint_return pt1 = null;

        PHPAspectParser.not_joinpoint_return pt2 = null;


        PHPAspectCommonTree AND9_tree=null;

        try {
            // PHPAspect.g:51:2: (pt1= not_joinpoint ( AND pt2= not_joinpoint )* )
            // PHPAspect.g:51:4: pt1= not_joinpoint ( AND pt2= not_joinpoint )*
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            pushFollow(FOLLOW_not_joinpoint_in_and_joinpoint183);
            pt1=not_joinpoint();

            state._fsp--;

            adaptor.addChild(root_0, pt1.getTree());
            // PHPAspect.g:51:22: ( AND pt2= not_joinpoint )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // PHPAspect.g:51:23: AND pt2= not_joinpoint
            	    {
            	    AND9=(CommonToken)match(input,AND,FOLLOW_AND_in_and_joinpoint186); 
            	    AND9_tree = (PHPAspectCommonTree)adaptor.create(AND9);
            	    root_0 = (PHPAspectCommonTree)adaptor.becomeRoot(AND9_tree, root_0);

            	    pushFollow(FOLLOW_not_joinpoint_in_and_joinpoint191);
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
    // PHPAspect.g:54:1: not_joinpoint : ( NOT )? joinpoint ;
    public final PHPAspectParser.not_joinpoint_return not_joinpoint() throws RecognitionException {
        PHPAspectParser.not_joinpoint_return retval = new PHPAspectParser.not_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken NOT10=null;
        PHPAspectParser.joinpoint_return joinpoint11 = null;


        PHPAspectCommonTree NOT10_tree=null;

        try {
            // PHPAspect.g:55:2: ( ( NOT )? joinpoint )
            // PHPAspect.g:55:5: ( NOT )? joinpoint
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            // PHPAspect.g:55:5: ( NOT )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NOT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // PHPAspect.g:55:6: NOT
                    {
                    NOT10=(CommonToken)match(input,NOT,FOLLOW_NOT_in_not_joinpoint207); 
                    NOT10_tree = (PHPAspectCommonTree)adaptor.create(NOT10);
                    root_0 = (PHPAspectCommonTree)adaptor.becomeRoot(NOT10_tree, root_0);


                    }
                    break;

            }

            pushFollow(FOLLOW_joinpoint_in_not_joinpoint212);
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
    // PHPAspect.g:59:1: joinpoint : ( CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE -> ^( CALL $type resolution $method) | LPARENTHESE or_joinpoint RPARENTHESE -> ^( PARENTHESE or_joinpoint ) );
    public final PHPAspectParser.joinpoint_return joinpoint() throws RecognitionException {
        PHPAspectParser.joinpoint_return retval = new PHPAspectParser.joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken type=null;
        CommonToken method=null;
        CommonToken CALL12=null;
        CommonToken LPARENTHESE13=null;
        CommonToken RPARENTHESE15=null;
        CommonToken LPARENTHESE16=null;
        CommonToken RPARENTHESE18=null;
        PHPAspectParser.resolution_return resolution14 = null;

        PHPAspectParser.or_joinpoint_return or_joinpoint17 = null;


        PHPAspectCommonTree type_tree=null;
        PHPAspectCommonTree method_tree=null;
        PHPAspectCommonTree CALL12_tree=null;
        PHPAspectCommonTree LPARENTHESE13_tree=null;
        PHPAspectCommonTree RPARENTHESE15_tree=null;
        PHPAspectCommonTree LPARENTHESE16_tree=null;
        PHPAspectCommonTree RPARENTHESE18_tree=null;
        RewriteRuleTokenStream stream_LPARENTHESE=new RewriteRuleTokenStream(adaptor,"token LPARENTHESE");
        RewriteRuleTokenStream stream_LABEL=new RewriteRuleTokenStream(adaptor,"token LABEL");
        RewriteRuleTokenStream stream_RPARENTHESE=new RewriteRuleTokenStream(adaptor,"token RPARENTHESE");
        RewriteRuleTokenStream stream_CALL=new RewriteRuleTokenStream(adaptor,"token CALL");
        RewriteRuleSubtreeStream stream_or_joinpoint=new RewriteRuleSubtreeStream(adaptor,"rule or_joinpoint");
        RewriteRuleSubtreeStream stream_resolution=new RewriteRuleSubtreeStream(adaptor,"rule resolution");
        try {
            // PHPAspect.g:60:2: ( CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE -> ^( CALL $type resolution $method) | LPARENTHESE or_joinpoint RPARENTHESE -> ^( PARENTHESE or_joinpoint ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==CALL) ) {
                alt4=1;
            }
            else if ( (LA4_0==LPARENTHESE) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // PHPAspect.g:60:4: CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE
                    {
                    CALL12=(CommonToken)match(input,CALL,FOLLOW_CALL_in_joinpoint225);  
                    stream_CALL.add(CALL12);

                    LPARENTHESE13=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint227);  
                    stream_LPARENTHESE.add(LPARENTHESE13);

                    type=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint231);  
                    stream_LABEL.add(type);

                    pushFollow(FOLLOW_resolution_in_joinpoint233);
                    resolution14=resolution();

                    state._fsp--;

                    stream_resolution.add(resolution14.getTree());
                    method=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint237);  
                    stream_LABEL.add(method);

                    RPARENTHESE15=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint239);  
                    stream_RPARENTHESE.add(RPARENTHESE15);



                    // AST REWRITE
                    // elements: method, type, CALL, resolution
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
                    // 61:2: -> ^( CALL $type resolution $method)
                    {
                        // PHPAspect.g:61:5: ^( CALL $type resolution $method)
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
                    // PHPAspect.g:62:4: LPARENTHESE or_joinpoint RPARENTHESE
                    {
                    LPARENTHESE16=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint259);  
                    stream_LPARENTHESE.add(LPARENTHESE16);

                    pushFollow(FOLLOW_or_joinpoint_in_joinpoint261);
                    or_joinpoint17=or_joinpoint();

                    state._fsp--;

                    stream_or_joinpoint.add(or_joinpoint17.getTree());
                    RPARENTHESE18=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint263);  
                    stream_RPARENTHESE.add(RPARENTHESE18);



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
                        // PHPAspect.g:63:5: ^( PARENTHESE or_joinpoint )
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
    // PHPAspect.g:66:1: resolution : ( PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR );
    public final PHPAspectParser.resolution_return resolution() throws RecognitionException {
        PHPAspectParser.resolution_return retval = new PHPAspectParser.resolution_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken set19=null;

        PHPAspectCommonTree set19_tree=null;

        try {
            // PHPAspect.g:67:3: ( PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR )
            // PHPAspect.g:
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            set19=(CommonToken)input.LT(1);
            if ( (input.LA(1)>=PAAMAYIM_NEKUDOTAYIM && input.LA(1)<=OBJECT_OPERATOR) ) {
                input.consume();
                adaptor.addChild(root_0, (PHPAspectCommonTree)adaptor.create(set19));
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


 

    public static final BitSet FOLLOW_AT_in_annotation84 = new BitSet(new long[]{0x0000000000038000L});
    public static final BitSet FOLLOW_advice_in_annotation86 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LPARENTHESE_in_annotation88 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_pointcut_in_annotation90 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RPARENTHESE_in_annotation92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_advice0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_joinpoint_in_pointcut146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_joinpoint_in_or_joinpoint160 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_OR_in_or_joinpoint163 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_and_joinpoint_in_or_joinpoint168 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_not_joinpoint_in_and_joinpoint183 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_AND_in_and_joinpoint186 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_not_joinpoint_in_and_joinpoint191 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_NOT_in_not_joinpoint207 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_joinpoint_in_not_joinpoint212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_joinpoint225 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint227 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint231 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_resolution_in_joinpoint233 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint237 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint259 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_or_joinpoint_in_joinpoint261 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_resolution0 = new BitSet(new long[]{0x0000000000000002L});

}