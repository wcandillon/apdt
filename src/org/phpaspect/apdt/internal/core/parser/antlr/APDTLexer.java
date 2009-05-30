package org.phpaspect.apdt.internal.core.parser.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.builder.IBuildContext;

public abstract class APDTLexer extends Lexer {

	private IBuildContext context;
	private int sourceStart = 0;
	private int lineNo = 1;
	
	public APDTLexer()
	{
		super();
	}
	
	public APDTLexer(CharStream input, RecognizerSharedState state)
	{
		super(input, state);
	}

	public APDTLexer setContext(IBuildContext context, int sourceStart)
	{
		this.context = context;
		this.sourceStart = sourceStart;
		return this;
	}
	
	@Override
	public void reportError(RecognitionException e) {
		if(context != null)
		{
			if(lineNo == 1)
			{
				char[] content = context.getContents();
				for(int i=0; i < sourceStart; i++)
				{
					if(content[i] == '\n')
					{
						lineNo++;
					}
				}
			}
			String filename = context.getFile().getName();
			String message = getErrorMessage(e, getTokenNames());
			int start = sourceStart+e.charPositionInLine;
			int end = start+1;
			IProblem problem = new DefaultProblem(filename, message, IProblem.Syntax,
					new String[0], ProblemSeverities.Error, start+1, end+1, lineNo);
			context.getProblemReporter().reportProblem(problem);
		} else {
			super.reportError(e);
		}
	}
}
