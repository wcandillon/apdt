package org.phpaspect.apdt.internal.core.annotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.phpaspect.apdt.internal.core.parser.antlr.AnnotationVisitor;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectCommonTree;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectCommonTreeAdaptator;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectLexer;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectParser;
import org.phpaspect.apdt.internal.core.parser.antlr.PHPAspectParser.annotation_return;
import org.phpaspect.core.weaver.Pointcut;

abstract public class AnnotationParser {

    public static List<Pointcut> getPointcuts(String id, String comment)
    {
        List<Pointcut> pointcuts = new LinkedList<Pointcut>();
        BufferedReader buffer = new BufferedReader(new StringReader(comment));
        try {
            String line;
            while((line = buffer.readLine()) != null)
            {
                int start = line.indexOf('@');
                int end = line.lastIndexOf(')');
                if(start == -1 || end == -1) continue;
                String annotation = line.substring(start, end+1);
                System.err.println(annotation);
                CharStream content = new ANTLRStringStream(annotation);
                PHPAspectLexer lexer = new PHPAspectLexer(content);
                PHPAspectParser parser = new PHPAspectParser(new CommonTokenStream(lexer));
                parser.setTreeAdaptor(new PHPAspectCommonTreeAdaptator());
                try {
                     annotation_return retValue = parser.annotation();
                     PHPAspectCommonTree tree = (PHPAspectCommonTree)retValue.getTree();
                     AnnotationVisitor visitor = new AnnotationVisitor(id);
                     tree.accept(visitor);
                     pointcuts.add(visitor.getPointcut());
                     
                } catch (RecognitionException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pointcuts;
    }
}
