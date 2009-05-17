// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 PHPAspect.g 2009-05-17 17:41:35

package org.phpaspect.apdt.internal.core.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class PHPAspectParser extends Parser {
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
    // PHPAspect.g:29:1: annotation : AT advice LPARENTHESE pointcut RPARENTHESE -> ^( ANNOTATION advice pointcut ) ;
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
            // PHPAspect.g:30:3: ( AT advice LPARENTHESE pointcut RPARENTHESE -> ^( ANNOTATION advice pointcut ) )
            // PHPAspect.g:30:5: AT advice LPARENTHESE pointcut RPARENTHESE
            {
            AT1=(CommonToken)match(input,AT,FOLLOW_AT_in_annotation79);  
            stream_AT.add(AT1);

            pushFollow(FOLLOW_advice_in_annotation81);
            advice2=advice();

            state._fsp--;

            stream_advice.add(advice2.getTree());
            LPARENTHESE3=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_annotation83);  
            stream_LPARENTHESE.add(LPARENTHESE3);

            pushFollow(FOLLOW_pointcut_in_annotation85);
            pointcut4=pointcut();

            state._fsp--;

            stream_pointcut.add(pointcut4.getTree());
            RPARENTHESE5=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_annotation87);  
            stream_RPARENTHESE.add(RPARENTHESE5);



            // AST REWRITE
            // elements: advice, pointcut
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (PHPAspectCommonTree)adaptor.nil();
            // 31:4: -> ^( ANNOTATION advice pointcut )
            {
                // PHPAspect.g:31:7: ^( ANNOTATION advice pointcut )
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
    // PHPAspect.g:34:1: advice : ( BEFORE | AROUND | AFTER );
    public final PHPAspectParser.advice_return advice() throws RecognitionException {
        PHPAspectParser.advice_return retval = new PHPAspectParser.advice_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken set6=null;

        PHPAspectCommonTree set6_tree=null;

        try {
            // PHPAspect.g:35:3: ( BEFORE | AROUND | AFTER )
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
    // PHPAspect.g:41:1: pointcut : or_joinpoint ;
    public final PHPAspectParser.pointcut_return pointcut() throws RecognitionException {
        PHPAspectParser.pointcut_return retval = new PHPAspectParser.pointcut_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        PHPAspectParser.or_joinpoint_return or_joinpoint7 = null;



        try {
            // PHPAspect.g:42:2: ( or_joinpoint )
            // PHPAspect.g:42:4: or_joinpoint
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            pushFollow(FOLLOW_or_joinpoint_in_pointcut141);
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
    // PHPAspect.g:45:1: or_joinpoint : pt1= and_joinpoint ( OR pt2= and_joinpoint -> ^( OR $pt1 $pt2) )* ;
    public final PHPAspectParser.or_joinpoint_return or_joinpoint() throws RecognitionException {
        PHPAspectParser.or_joinpoint_return retval = new PHPAspectParser.or_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken OR8=null;
        PHPAspectParser.and_joinpoint_return pt1 = null;

        PHPAspectParser.and_joinpoint_return pt2 = null;


        PHPAspectCommonTree OR8_tree=null;
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_and_joinpoint=new RewriteRuleSubtreeStream(adaptor,"rule and_joinpoint");
        try {
            // PHPAspect.g:46:2: (pt1= and_joinpoint ( OR pt2= and_joinpoint -> ^( OR $pt1 $pt2) )* )
            // PHPAspect.g:46:4: pt1= and_joinpoint ( OR pt2= and_joinpoint -> ^( OR $pt1 $pt2) )*
            {
            pushFollow(FOLLOW_and_joinpoint_in_or_joinpoint155);
            pt1=and_joinpoint();

            state._fsp--;

            stream_and_joinpoint.add(pt1.getTree());
            // PHPAspect.g:46:22: ( OR pt2= and_joinpoint -> ^( OR $pt1 $pt2) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // PHPAspect.g:46:23: OR pt2= and_joinpoint
            	    {
            	    OR8=(CommonToken)match(input,OR,FOLLOW_OR_in_or_joinpoint158);  
            	    stream_OR.add(OR8);

            	    pushFollow(FOLLOW_and_joinpoint_in_or_joinpoint162);
            	    pt2=and_joinpoint();

            	    state._fsp--;

            	    stream_and_joinpoint.add(pt2.getTree());


            	    // AST REWRITE
            	    // elements: OR, pt1, pt2
            	    // token labels: 
            	    // rule labels: pt1, retval, pt2
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_pt1=new RewriteRuleSubtreeStream(adaptor,"rule pt1",pt1!=null?pt1.tree:null);
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_pt2=new RewriteRuleSubtreeStream(adaptor,"rule pt2",pt2!=null?pt2.tree:null);

            	    root_0 = (PHPAspectCommonTree)adaptor.nil();
            	    // 46:44: -> ^( OR $pt1 $pt2)
            	    {
            	        // PHPAspect.g:46:47: ^( OR $pt1 $pt2)
            	        {
            	        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
            	        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_OR.nextNode(), root_1);

            	        adaptor.addChild(root_1, stream_pt1.nextTree());
            	        adaptor.addChild(root_1, stream_pt2.nextTree());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }

            	    retval.tree = root_0;
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
    // PHPAspect.g:49:1: and_joinpoint : pt1= not_joinpoint ( AND pt2= not_joinpoint -> ^( AND $pt1 $pt2) )* ;
    public final PHPAspectParser.and_joinpoint_return and_joinpoint() throws RecognitionException {
        PHPAspectParser.and_joinpoint_return retval = new PHPAspectParser.and_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken AND9=null;
        PHPAspectParser.not_joinpoint_return pt1 = null;

        PHPAspectParser.not_joinpoint_return pt2 = null;


        PHPAspectCommonTree AND9_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
        RewriteRuleSubtreeStream stream_not_joinpoint=new RewriteRuleSubtreeStream(adaptor,"rule not_joinpoint");
        try {
            // PHPAspect.g:50:2: (pt1= not_joinpoint ( AND pt2= not_joinpoint -> ^( AND $pt1 $pt2) )* )
            // PHPAspect.g:50:4: pt1= not_joinpoint ( AND pt2= not_joinpoint -> ^( AND $pt1 $pt2) )*
            {
            pushFollow(FOLLOW_not_joinpoint_in_and_joinpoint189);
            pt1=not_joinpoint();

            state._fsp--;

            stream_not_joinpoint.add(pt1.getTree());
            // PHPAspect.g:50:22: ( AND pt2= not_joinpoint -> ^( AND $pt1 $pt2) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // PHPAspect.g:50:23: AND pt2= not_joinpoint
            	    {
            	    AND9=(CommonToken)match(input,AND,FOLLOW_AND_in_and_joinpoint192);  
            	    stream_AND.add(AND9);

            	    pushFollow(FOLLOW_not_joinpoint_in_and_joinpoint196);
            	    pt2=not_joinpoint();

            	    state._fsp--;

            	    stream_not_joinpoint.add(pt2.getTree());


            	    // AST REWRITE
            	    // elements: AND, pt1, pt2
            	    // token labels: 
            	    // rule labels: pt1, retval, pt2
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_pt1=new RewriteRuleSubtreeStream(adaptor,"rule pt1",pt1!=null?pt1.tree:null);
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_pt2=new RewriteRuleSubtreeStream(adaptor,"rule pt2",pt2!=null?pt2.tree:null);

            	    root_0 = (PHPAspectCommonTree)adaptor.nil();
            	    // 50:45: -> ^( AND $pt1 $pt2)
            	    {
            	        // PHPAspect.g:50:48: ^( AND $pt1 $pt2)
            	        {
            	        PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
            	        root_1 = (PHPAspectCommonTree)adaptor.becomeRoot(stream_AND.nextNode(), root_1);

            	        adaptor.addChild(root_1, stream_pt1.nextTree());
            	        adaptor.addChild(root_1, stream_pt2.nextTree());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }

            	    retval.tree = root_0;
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
    // PHPAspect.g:53:1: not_joinpoint : (not= NOT )? joinpoint -> ^( JOINPOINT joinpoint $not) ;
    public final PHPAspectParser.not_joinpoint_return not_joinpoint() throws RecognitionException {
        PHPAspectParser.not_joinpoint_return retval = new PHPAspectParser.not_joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken not=null;
        PHPAspectParser.joinpoint_return joinpoint10 = null;


        PHPAspectCommonTree not_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleSubtreeStream stream_joinpoint=new RewriteRuleSubtreeStream(adaptor,"rule joinpoint");
        try {
            // PHPAspect.g:54:2: ( (not= NOT )? joinpoint -> ^( JOINPOINT joinpoint $not) )
            // PHPAspect.g:54:5: (not= NOT )? joinpoint
            {
            // PHPAspect.g:54:5: (not= NOT )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NOT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // PHPAspect.g:54:6: not= NOT
                    {
                    not=(CommonToken)match(input,NOT,FOLLOW_NOT_in_not_joinpoint226);  
                    stream_NOT.add(not);


                    }
                    break;

            }

            pushFollow(FOLLOW_joinpoint_in_not_joinpoint230);
            joinpoint10=joinpoint();

            state._fsp--;

            stream_joinpoint.add(joinpoint10.getTree());


            // AST REWRITE
            // elements: not, joinpoint
            // token labels: not
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_not=new RewriteRuleTokenStream(adaptor,"token not",not);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (PHPAspectCommonTree)adaptor.nil();
            // 54:26: -> ^( JOINPOINT joinpoint $not)
            {
                // PHPAspect.g:54:29: ^( JOINPOINT joinpoint $not)
                {
                PHPAspectCommonTree root_1 = (PHPAspectCommonTree)adaptor.nil();
                root_1 = (PHPAspectCommonTree)adaptor.becomeRoot((PHPAspectCommonTree)adaptor.create(JOINPOINT, "JOINPOINT"), root_1);

                adaptor.addChild(root_1, stream_joinpoint.nextTree());
                adaptor.addChild(root_1, stream_not.nextNode());

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
    // $ANTLR end "not_joinpoint"

    public static class joinpoint_return extends ParserRuleReturnScope {
        PHPAspectCommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "joinpoint"
    // PHPAspect.g:58:1: joinpoint : ( CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE -> ^( CALL $type resolution $method) | LPARENTHESE or_joinpoint RPARENTHESE -> ^( PARENTHESE or_joinpoint ) );
    public final PHPAspectParser.joinpoint_return joinpoint() throws RecognitionException {
        PHPAspectParser.joinpoint_return retval = new PHPAspectParser.joinpoint_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken type=null;
        CommonToken method=null;
        CommonToken CALL11=null;
        CommonToken LPARENTHESE12=null;
        CommonToken RPARENTHESE14=null;
        CommonToken LPARENTHESE15=null;
        CommonToken RPARENTHESE17=null;
        PHPAspectParser.resolution_return resolution13 = null;

        PHPAspectParser.or_joinpoint_return or_joinpoint16 = null;


        PHPAspectCommonTree type_tree=null;
        PHPAspectCommonTree method_tree=null;
        PHPAspectCommonTree CALL11_tree=null;
        PHPAspectCommonTree LPARENTHESE12_tree=null;
        PHPAspectCommonTree RPARENTHESE14_tree=null;
        PHPAspectCommonTree LPARENTHESE15_tree=null;
        PHPAspectCommonTree RPARENTHESE17_tree=null;
        RewriteRuleTokenStream stream_LPARENTHESE=new RewriteRuleTokenStream(adaptor,"token LPARENTHESE");
        RewriteRuleTokenStream stream_LABEL=new RewriteRuleTokenStream(adaptor,"token LABEL");
        RewriteRuleTokenStream stream_RPARENTHESE=new RewriteRuleTokenStream(adaptor,"token RPARENTHESE");
        RewriteRuleTokenStream stream_CALL=new RewriteRuleTokenStream(adaptor,"token CALL");
        RewriteRuleSubtreeStream stream_or_joinpoint=new RewriteRuleSubtreeStream(adaptor,"rule or_joinpoint");
        RewriteRuleSubtreeStream stream_resolution=new RewriteRuleSubtreeStream(adaptor,"rule resolution");
        try {
            // PHPAspect.g:59:2: ( CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE -> ^( CALL $type resolution $method) | LPARENTHESE or_joinpoint RPARENTHESE -> ^( PARENTHESE or_joinpoint ) )
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
                    // PHPAspect.g:59:4: CALL LPARENTHESE type= LABEL resolution method= LABEL RPARENTHESE
                    {
                    CALL11=(CommonToken)match(input,CALL,FOLLOW_CALL_in_joinpoint254);  
                    stream_CALL.add(CALL11);

                    LPARENTHESE12=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint256);  
                    stream_LPARENTHESE.add(LPARENTHESE12);

                    type=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint260);  
                    stream_LABEL.add(type);

                    pushFollow(FOLLOW_resolution_in_joinpoint262);
                    resolution13=resolution();

                    state._fsp--;

                    stream_resolution.add(resolution13.getTree());
                    method=(CommonToken)match(input,LABEL,FOLLOW_LABEL_in_joinpoint266);  
                    stream_LABEL.add(method);

                    RPARENTHESE14=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint268);  
                    stream_RPARENTHESE.add(RPARENTHESE14);



                    // AST REWRITE
                    // elements: resolution, method, CALL, type
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
                    // 60:2: -> ^( CALL $type resolution $method)
                    {
                        // PHPAspect.g:60:5: ^( CALL $type resolution $method)
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
                    // PHPAspect.g:61:4: LPARENTHESE or_joinpoint RPARENTHESE
                    {
                    LPARENTHESE15=(CommonToken)match(input,LPARENTHESE,FOLLOW_LPARENTHESE_in_joinpoint288);  
                    stream_LPARENTHESE.add(LPARENTHESE15);

                    pushFollow(FOLLOW_or_joinpoint_in_joinpoint290);
                    or_joinpoint16=or_joinpoint();

                    state._fsp--;

                    stream_or_joinpoint.add(or_joinpoint16.getTree());
                    RPARENTHESE17=(CommonToken)match(input,RPARENTHESE,FOLLOW_RPARENTHESE_in_joinpoint292);  
                    stream_RPARENTHESE.add(RPARENTHESE17);



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
                    // 62:2: -> ^( PARENTHESE or_joinpoint )
                    {
                        // PHPAspect.g:62:5: ^( PARENTHESE or_joinpoint )
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
    // PHPAspect.g:65:1: resolution : ( PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR );
    public final PHPAspectParser.resolution_return resolution() throws RecognitionException {
        PHPAspectParser.resolution_return retval = new PHPAspectParser.resolution_return();
        retval.start = input.LT(1);

        PHPAspectCommonTree root_0 = null;

        CommonToken set18=null;

        PHPAspectCommonTree set18_tree=null;

        try {
            // PHPAspect.g:66:3: ( PAAMAYIM_NEKUDOTAYIM | OBJECT_OPERATOR )
            // PHPAspect.g:
            {
            root_0 = (PHPAspectCommonTree)adaptor.nil();

            set18=(CommonToken)input.LT(1);
            if ( (input.LA(1)>=PAAMAYIM_NEKUDOTAYIM && input.LA(1)<=OBJECT_OPERATOR) ) {
                input.consume();
                adaptor.addChild(root_0, (PHPAspectCommonTree)adaptor.create(set18));
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


 

    public static final BitSet FOLLOW_AT_in_annotation79 = new BitSet(new long[]{0x0000000000038000L});
    public static final BitSet FOLLOW_advice_in_annotation81 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LPARENTHESE_in_annotation83 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_pointcut_in_annotation85 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RPARENTHESE_in_annotation87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_advice0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_joinpoint_in_pointcut141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_joinpoint_in_or_joinpoint155 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_OR_in_or_joinpoint158 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_and_joinpoint_in_or_joinpoint162 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_not_joinpoint_in_and_joinpoint189 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_AND_in_and_joinpoint192 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_not_joinpoint_in_and_joinpoint196 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_NOT_in_not_joinpoint226 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_joinpoint_in_not_joinpoint230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_joinpoint254 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint256 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint260 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_resolution_in_joinpoint262 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LABEL_in_joinpoint266 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPARENTHESE_in_joinpoint288 = new BitSet(new long[]{0x0000000000042100L});
    public static final BitSet FOLLOW_or_joinpoint_in_joinpoint290 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RPARENTHESE_in_joinpoint292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_resolution0 = new BitSet(new long[]{0x0000000000000002L});

}