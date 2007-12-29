<?php

require_once 'Runtime/Reflection/InvalidJoinPointException.php';

interface JoinPoint extends Reflector{

	const CONSTRUCTOR_CALL = 'constructor-call';

	const METHOD_CALL = 'method-call';

	const METHOD_EXECUTION = 'method-execution';

	const FIELD_GET = 'field-get';

	const FIELD_SET = 'field-set';

	const EXCEPTION_HANDLER = 'exception-handler';

	public function __toString();

	public function toString();

	public function toLongString();

	public function toShortString();

	public function getSignature();

	public function getThis();

	public function getSource();

	public function getTarget();

	public function getFileName();

	public function getLine();

	public function getKind();
}
?>