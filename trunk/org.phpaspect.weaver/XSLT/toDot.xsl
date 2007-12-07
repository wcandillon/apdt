<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE stylesheet [
    <!ENTITY xsl "http://www.w3.org/1999/XSL/Transform">
    <!ENTITY tab "    ">
    <!ENTITY newline "
">
    <!ENTITY indent "    ">
]>
<xsl:stylesheet version="1.0" xmlns="http://www.w3.org/1999/XSL/Transform"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="text" indent="no"/>
    <xsl:template match="*">
        <xsl:if test="not(parent::*)">
          <xsl:text>digraph Parse_Tree {
</xsl:text>
        </xsl:if>
        <xsl:if test="parent::*">
          <xsl:call-template name="generateRelation">
            <xsl:with-param name="parent" select="parent::*"/>
            <xsl:with-param name="child" select="."/>
          </xsl:call-template>
          <xsl:call-template name="generateLabel">
            <xsl:with-param name="node" select="."/>
          </xsl:call-template>
        </xsl:if>

        <xsl:if test="normalize-space(text())!=''">
          <xsl:value-of select="generate-id()"/>
          <xsl:text> -&gt; </xsl:text>
          <xsl:value-of select="@id"/>
          <xsl:text>;
    </xsl:text>
          <xsl:value-of select="@id"/>
          <xsl:text> [label="</xsl:text>
          <xsl:value-of select="translate(text(), '&quot;', ' ')"/>
          <xsl:text>"];
    </xsl:text>
        </xsl:if>

        <xsl:for-each select="child::*">
          <xsl:call-template name="generateRelation">
            <xsl:with-param name="parent" select="parent::*"/>
            <xsl:with-param name="child" select="."/>
          </xsl:call-template>

          <xsl:call-template name="generateLabel">
            <xsl:with-param name="node" select="."/>
          </xsl:call-template>

          <xsl:if test="parent::*[not(parent::*)]">
            <xsl:call-template name="generateLabel">
              <xsl:with-param name="node" select="parent::*"/>
            </xsl:call-template>
          </xsl:if>

          <xsl:if test="normalize-space(text())!=''">
            <xsl:value-of select="generate-id()"/>
            <xsl:text> -&gt; </xsl:text>
            <xsl:value-of select="@id"/>
            <xsl:text>;
    </xsl:text>
            <xsl:value-of select="@id"/>
            <xsl:text> [label="</xsl:text>
            <xsl:value-of select="translate(text(), '&quot;', ' ')"/>
            <xsl:text>"];
    </xsl:text>
          </xsl:if>

          <xsl:apply-templates select="child::*"/>
        </xsl:for-each>
        <xsl:if test="not(parent::*)">
          <xsl:text>
}</xsl:text>
        </xsl:if>
    </xsl:template>

    <xsl:template name="generateLabel">
      <xsl:param name="node"/>
      <xsl:value-of select="generate-id($node)"/>
      <xsl:text> [label="</xsl:text>
      <xsl:value-of select="local-name($node)"/>
      <xsl:text>"];
    </xsl:text>
    </xsl:template>

    <xsl:template name="generateRelation">
      <xsl:param name="parent"/>
      <xsl:param name="child"/>
      <xsl:value-of select="generate-id($parent)"/>
      <xsl:text> -&gt; </xsl:text>
      <xsl:value-of select="generate-id($child)"/>
      <xsl:text>;
    </xsl:text>
    </xsl:template>
</xsl:stylesheet>