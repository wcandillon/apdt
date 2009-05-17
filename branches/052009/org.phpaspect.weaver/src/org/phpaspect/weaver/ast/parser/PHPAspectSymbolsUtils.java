package org.phpaspect.weaver.ast.parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PHPAspectSymbolsUtils {
	
	private static Map<Integer, String> tokens =
								new HashMap<Integer, String>();
	
	private static Map<String, String> errorsTable =
								new HashMap<String, String>();

	static{
		initErrorsTable();
		
		Field[] fields = PHPAspectSymbols.class.getDeclaredFields();
		for(int i=0; i<fields.length; i++){
			try {
				tokens.put(fields[i].getInt(null), fields[i].getName());
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
	}

	public static String getTokenName(int id){
		return tokens.get(id);
	}
	
	public static String getTokenValue(String name){
		return errorsTable.get(name);
	}
	
	public static String getTokenValue(int id){
		return getTokenValue(getTokenName(id));
	}
	
	private static void initErrorsTable() {
		errorsTable.put("T_INC", "++");
		errorsTable.put("T_DEC", "--");
		errorsTable.put("T_IS_IDENTICAL", "===");
		errorsTable.put("T_IS_NOT_IDENTICAL", "!==");
		errorsTable.put("T_IS_EQUAL", "==");
		errorsTable.put("T_IS_NOT_EQUAL", "!=");
		errorsTable.put("T_IS_SMALLER_OR_EQUAL", "<=+");
		errorsTable.put("T_IS_GREATER_OR_EQUAL", ">=+");
		errorsTable.put("T_PLUS_EQUAL", "+=");
		errorsTable.put("T_MINUS_EQUAL", "-=");
		errorsTable.put("T_MUL_EQUAL", "*=");
		errorsTable.put("T_DIV_EQUAL", "/=");
		errorsTable.put("T_CONCAT_EQUAL", ".=");
		errorsTable.put("T_MOD_EQUAL", "%=");
		errorsTable.put("T_SL_EQUAL", "<<=");
		errorsTable.put("T_SR_EQUAL", ">>=");
		errorsTable.put("T_AND_EQUAL", "&=");
		errorsTable.put("T_OR_EQUAL", "|+");
		errorsTable.put("T_XOR_EQUAL", "^=");
		errorsTable.put("T_BOOLEAN_OR", "||");
		errorsTable.put("T_BOOLEAN_AND", "&&");
		errorsTable.put("T_LOGICAL_OR", "OR");
		errorsTable.put("T_LOGICAL_AND", "AND");
		errorsTable.put("T_LOGICAL_XOR", "XOR");
		errorsTable.put("T_SL", "<<");
		errorsTable.put("T_SR", ">>");

		errorsTable.put("T_SEMICOLON", ";");
		errorsTable.put("T_NEKUDOTAIM", ":");
		errorsTable.put("T_COMMA", ",");
		errorsTable.put("T_NEKUDA", ".");
		errorsTable.put("T_OPEN_RECT", "[");
		errorsTable.put("T_CLOSE_RECT", "]");
		errorsTable.put("T_OPEN_PARENTHESE", "(");
		errorsTable.put("T_CLOSE_PARENTHESE", ")");

		errorsTable.put("T_OR", "|");
		errorsTable.put("T_KOVA", "^");
		errorsTable.put("T_REFERENCE", "&");
		errorsTable.put("T_PLUS", "+");
		errorsTable.put("T_MINUS", "-");
		errorsTable.put("T_DIV", "/");
		errorsTable.put("T_TIMES", "*");
		errorsTable.put("T_EQUAL", "=");
		errorsTable.put("T_PRECENT", "%");
		errorsTable.put("T_NOT", "!");
		errorsTable.put("T_TILDA", "~");
		errorsTable.put("T_DOLLAR", "$");
		errorsTable.put("T_RGREATER", "<");
		errorsTable.put("T_LGREATER", ">");
		errorsTable.put("T_QUESTION_MARK", "?");
		errorsTable.put("T_AT", "@");

		errorsTable.put("T_EXIT", "exit");
		errorsTable.put("T_FUNCTION", "function");
		errorsTable.put("T_CONST", "const");
		errorsTable.put("T_RETURN", "return");
		errorsTable.put("T_IF", "if");
		errorsTable.put("T_ELSEIF", "elseif");
		errorsTable.put("T_ENDIF", "endif");
		errorsTable.put("T_ELSE", "else");
		errorsTable.put("T_WHILE", "while");
		errorsTable.put("T_ENDWHILE", "endwhile");
		errorsTable.put("T_DO", "do");
		errorsTable.put("T_FOR", "for");
		errorsTable.put("T_ENDFOR", "endfor");
		errorsTable.put("T_FOREACH", "foreach");
		errorsTable.put("T_ENDFOREACH", "endforeach");
		errorsTable.put("T_AS", "as");
		errorsTable.put("T_SWITCH", "switch");
		errorsTable.put("T_ENDSWITCH", "endswitch");
		errorsTable.put("T_CASE", "case");
		errorsTable.put("T_DEFAULT", "default");
		errorsTable.put("T_BREAK", "break");
		errorsTable.put("T_CONTINUE", "continue");
		errorsTable.put("T_ECHO", "echo");
		errorsTable.put("T_PRINT", "print");
		errorsTable.put("T_CLASS", "class");
		errorsTable.put("T_TRY", "try");
		errorsTable.put("T_CATCH", "catch");
		errorsTable.put("T_THROW", "throw");
		errorsTable.put("T_INSTANCEOF", "instanceof");
		errorsTable.put("T_INTERFACE", "interface");
		errorsTable.put("T_IMPLEMENTS", "implements");
		errorsTable.put("T_ABSTRACT", "abstract");
		errorsTable.put("T_FINAL", "final");
		errorsTable.put("T_PRIVATE", "private");
		errorsTable.put("T_PROTECTED", "protected");
		errorsTable.put("T_PUBLIC", "public");
		errorsTable.put("T_EXTENDS", "extends");
		errorsTable.put("T_NEW", "new");
		errorsTable.put("T_EVAL", "eval");
		errorsTable.put("T_INCLUDE", "include");
		errorsTable.put("T_INCLUDE_ONCE", "include_once");
		errorsTable.put("T_REQUIRE", "require");
		errorsTable.put("T_REQUIRE_ONCE", "require_once");
		errorsTable.put("T_USE", "use");
		errorsTable.put("T_GLOBAL", "global");
		errorsTable.put("T_ISSET", "isset");
		errorsTable.put("T_EMPTY", "empty");
		errorsTable.put("T_STATIC", "static");
		errorsTable.put("T_UNSET", "unset");
		errorsTable.put("T_LIST", "array");
		errorsTable.put("T_VAR", "var");
		errorsTable.put("T_DECLARE", "declare");
		errorsTable.put("T_ENDDECLARE", "enddeclare");
		errorsTable.put("T_OBJECT_OPERATOR", "->");
		errorsTable.put("T_PAAMAYIM_NEKUDOTAYIM", "::");
		errorsTable.put("T_CURLY_CLOSE", "}");
		errorsTable.put("T_CURLY_OPEN", "{");
		errorsTable.put("T_DOUBLE_ARROW", "=>");
		errorsTable.put("T_DOLLAR_OPEN_CURLY_BRACES", "${");
		
		errorsTable.put("T_ASPECT", "aspect");
		errorsTable.put("T_PERSESSION", "perSession");
		errorsTable.put("T_FROM", "from");
		errorsTable.put("T_BEFORE", "before");
		errorsTable.put("T_AROUND", "around");
		errorsTable.put("T_AFTER", "after");
		errorsTable.put("T_POINTCUT", "pointcut");
		errorsTable.put("T_PARENTS", "parents");
	}
}
