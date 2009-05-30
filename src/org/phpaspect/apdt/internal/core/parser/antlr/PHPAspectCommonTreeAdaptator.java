package org.phpaspect.apdt.internal.core.parser.antlr;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;

public class PHPAspectCommonTreeAdaptator extends CommonTreeAdaptor {

    public Object create(Token payload) {
        return new PHPAspectCommonTree(payload);
    }
    
    @Override
    public void setTokenBoundaries(Object t, Token startToken, Token stopToken) {
        if (t == null) return;
        int startTI = 0;
        int stopTI = 0;
        int startTS = 0;
        int stopTS = 0;
        if (startToken != null) {
            startTI = startToken.getTokenIndex();
            startTS = ((CommonToken)startToken).getStartIndex();
        }
        if (stopToken != null) {
            stopTI = stopToken.getTokenIndex();
            stopTS = ((CommonToken)stopToken).getStopIndex();
        }
        ((Tree)t).setTokenStartIndex(startTI);
        ((Tree)t).setTokenStopIndex(stopTI);
        if (t instanceof PHPAspectCommonTree) {
            PHPAspectCommonTree xct = (PHPAspectCommonTree) t;
            xct.setStart(startTS);
            xct.setStop(stopTS);
        }
    }
}
