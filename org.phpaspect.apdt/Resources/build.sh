jflex-1.3.5/bin/jflex completion_scanner.flex
/opt/ibm/java2-i386-50/bin/java -classpath ../../org.eclipse.php.core/bin  org.eclipse.php.internal.core.phpModel.javacup.Main -expect 1 -parser PHPAspectParser -symbols PHPAspectSymbols -package org.eclipse.php.internal.core.phpModel < php_parser.cup
php -r "include 'file_replace.php';file_replace('java_cup', 'org.eclipse.php.internal.core.phpModel.javacup', 'PHPAspectParser.java'); "
mv PHPAspectParser.java PHPAspectSymbols.java PHPAspectCompletionLexer.java ../src/org/phpaspect/apdt/core/parser/
