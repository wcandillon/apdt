<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
    <!ENTITY xsl "http://www.w3.org/1999/XSL/Transform">
    <!ENTITY separator1 ":">
    <!ENTITY separator2 "&#10;">
    <!ENTITY separator3 " ">
    <!ENTITY newline "
">
    <!ENTITY indent "    ">
    <!ENTITY quote '"'>
]>
<xsl:stylesheet version="1.0" xmlns="&xsl;" xmlns:xsl="&xsl;">
    <xsl:output method="text" />
	<xsl:template match="statements">
		<xsl:text>&lt;?php require_once 'PHPAspect/Runtime/Aspect.php'; class </xsl:text>
		<xsl:value-of select="name" />

		<xsl:text>{</xsl:text>
		<xsl:value-of select="//php:class_statement" />
		<xsl:text>}&newline;?&gt;</xsl:text>
<php:implements_list>
</php:implements_list>
	</xsl:template>
</xsl:stylesheet>