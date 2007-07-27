jflex-1.3.5/bin/jflex completion_scanner.flex
java -jar java-cup-11a.jar -expect 4 -parser PHPAspectParser -symbols PHPAspectSymbols  php_parser.cup
mv PHPAspectParser.java PHPAspectSymbols.java CompletionLexerPHPAspect.java ../src/org/phpaspect/apdt/core/parser/
