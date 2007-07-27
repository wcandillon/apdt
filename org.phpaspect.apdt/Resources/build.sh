jflex-1.4.1/bin/jflex completion_scanner.flex
java -jar java-cup-11a.jar -expect 4 -parser PHPAspectParser -symbols PHPAspectSymbols  php_parser.cup
cp PHPAspectParser.java PHPAspectSymbols.java CompletionLexerPHPAspect.java ../src/org/phpaspect/apdt/core/model/parser/
