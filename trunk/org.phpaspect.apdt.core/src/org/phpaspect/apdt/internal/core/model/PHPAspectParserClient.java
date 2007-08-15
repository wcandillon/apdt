package org.phpaspect.apdt.internal.core.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.core.phpModel.parser.PHPUserModel;
import org.eclipse.php.internal.core.phpModel.parser.ParserClient;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPDocBlock;

public class PHPAspectParserClient implements ParserClient {

	public PHPAspectParserClient(PHPUserModel userModel, IProject project) {
		// TODO Auto-generated constructor stub
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void finishParsing(int lastPosition, int lastLine, long lastModified) {
		// TODO Auto-generated method stub

	}

	public void hadleClassDeclarationStarts(String className, int startPosition) {
		// TODO Auto-generated method stub

	}

	public void handleClassConstDeclaration(String constName,
			PHPDocBlock docInfo, int startPosition, int endPosition,
			int stopPosition) {
		// TODO Auto-generated method stub

	}

	public void handleClassDeclaration(String className, int modifier,
			String superClassName, String interfacesNames, PHPDocBlock docInfo,
			int startPosition, int stopPosition, int lineNumber) {
		// TODO Auto-generated method stub

	}

	public void handleClassDeclarationEnds(String className, int endPosition) {
		// TODO Auto-generated method stub

	}

	public void handleClassVariablesDeclaration(String variables, int modifier,
			PHPDocBlock docInfo, int startPosition, int endPosition,
			int stopPosition) {
		// TODO Auto-generated method stub

	}

	public void handleDefine(String name, String value, PHPDocBlock docInfo,
			int startPosition, int endPosition, int stopPosition) {
		// TODO Auto-generated method stub

	}

	public void handleError(String description, int startPosition,
			int endPosition, int lineNumber) {
		// TODO Auto-generated method stub

	}

	public void handleFunctionDeclaration(String functionName,
			boolean isClassFunction, int modifier, PHPDocBlock docInfo,
			int startPosition, int stopPosition, int lineNumber) {
		// TODO Auto-generated method stub

	}

	public void handleFunctionDeclarationEnds(String functionName,
			boolean isClassFunction, int endPosition) {
		// TODO Auto-generated method stub

	}

	public void handleFunctionDeclarationStarts(String functionName) {
		// TODO Auto-generated method stub

	}

	public void handleFunctionParameter(String classType, String variableName,
			boolean isReference, boolean isConst, String defaultValue,
			int startPosition, int endPosition, int stopPosition, int lineNumber) {
		// TODO Auto-generated method stub

	}

	public void handleGlobalVar(String variableName) {
		// TODO Auto-generated method stub

	}

	public void handleIncludedFile(String includingType,
			String includeFileName, PHPDocBlock docInfo, int startPosition,
			int endPosition, int stopPosition, int lineNumber) {
		// TODO Auto-generated method stub

	}

	public void handleObjectInstansiation(String variableName,
			String className, String ctorArrguments, int line,
			int startPosition, boolean isUserDocumentation) {
		// TODO Auto-generated method stub

	}

	public void handlePHPEnd(int startOffset, int endOffset) {
		// TODO Auto-generated method stub

	}

	public void handlePHPStart(int startOffset, int endOffset) {
		// TODO Auto-generated method stub

	}

	public void handleStaticVar(String variableName) {
		// TODO Auto-generated method stub

	}

	public void handleSyntaxError(int currToken, String currText,
			short[] rowOfProbe, int startPosition, int endPosition,
			int lineNumber) {
		// TODO Auto-generated method stub

	}

	public void handleTask(String taskName, String description,
			int startPosition, int endPosition, int lineNumber) {
		// TODO Auto-generated method stub

	}

	public void handleVariableName(String variableName, int line) {
		// TODO Auto-generated method stub

	}

	public void haveReturnValue() {
		// TODO Auto-generated method stub

	}

	public void setFirstDocBlock(PHPDocBlock docBlock) {
		// TODO Auto-generated method stub

	}

	public void startParsing(String fileName) {
		// TODO Auto-generated method stub

	}

}
