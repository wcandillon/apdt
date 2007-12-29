<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
<!ENTITY newline "
">
]>
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- Create a true boolean value -->
    <xsl:template name="create_true">
        <scalar stringValue="true" scalarType="2" />        
    </xsl:template>
    
    <!-- Create a false boolean value -->
    <xsl:template name="create_false">
        <scalar stringValue="false" scalarType="2" />
    </xsl:template>
    
    <!-- Create a null value -->
    <xsl:template name="create_null">
        <scalar stringValue="null" scalarType="2" />        
    </xsl:template>
    
    <!-- Create a value -->
    <xsl:template name="create_value">
        <xsl:param name="value" />
        <xsl:param name="scalarType" />
        <xsl:element name="scalar">
            <xsl:attribute name="stringValue"><xsl:value-of select="$value" /></xsl:attribute>
            <xsl:attribute name="scalarType"><xsl:value-of select="$scalarType" /></xsl:attribute>
        </xsl:element>        
    </xsl:template>
    
    <!-- Create an interger -->
    <xsl:template name="create_integer">
        <xsl:param name="value" />
        <xsl:call-template name="create_value">
            <xsl:with-param name="value"><xsl:value-of select="$value" /></xsl:with-param>
            <xsl:with-param name="scalarType">0</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <!-- Create a float -->
    <xsl:template name="create_float">
        <xsl:param name="value" />
        <xsl:call-template name="create_value">
            <xsl:with-param name="value"><xsl:value-of select="$value" /></xsl:with-param>
            <xsl:with-param name="scalarType">1</xsl:with-param>
        </xsl:call-template>        
    </xsl:template>
    
    <!-- Create a string -->
    <xsl:template name="create_string">
        <xsl:param name="value" />
        <xsl:call-template name="create_value">
            <xsl:with-param name="value"><xsl:value-of select="$value" /></xsl:with-param>
            <xsl:with-param name="scalarType">2</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <!-- Create a PHPDoc annotation -->
    <xsl:template name="create_annotation">
        <xsl:param name="name" />
        <xsl:param name="value" />
        <aspectAnnotations>
            <annotations>
                <xsl:element name="aspectAnnotation">
                    <xsl:attribute name="name">
                        <xsl:value-of select="$name" />                     
                    </xsl:attribute>
                    <xsl:attribute name="value">
                        <xsl:value-of select="$value" />                     
                    </xsl:attribute>
                </xsl:element>
            </annotations>
        </aspectAnnotations>        
    </xsl:template>
    
    <!-- Generate a require_once() node -->
    <xsl:template name="create_require_once">
        <xsl:param name="path" />
        <expressionStatement>
            <expr class="include" includeType="1">
                <xsl:element name="expr">
                    <xsl:attribute name="class">scalar</xsl:attribute>
                    <xsl:attribute name="stringValue"><xsl:value-of select="$path" /></xsl:attribute>
                    <xsl:attribute name="scalarType">2</xsl:attribute>
                </xsl:element>
            </expr>
        </expressionStatement>
    </xsl:template>
    
    <!-- Generate an attribute -->
    <xsl:template name="create_attribute">
        <xsl:param name="modifier" />
        <xsl:param name="name" />
        <xsl:param name="value"><null /></xsl:param>
        <xsl:element name="fieldsDeclaration">
            <xsl:attribute name="modifier"><xsl:value-of select="$modifier" /></xsl:attribute>
            <variableNames>
                <variable isDollared="true">
                    <xsl:element name="variableName">
                        <xsl:attribute name="class">identifier</xsl:attribute>
                        <xsl:attribute name="name"><xsl:value-of select="$name"></xsl:value-of></xsl:attribute>
                    </xsl:element>
                </variable>
                <initialValues>
                    <xsl:copy-of select="$value" />
                </initialValues>
            </variableNames>            
        </xsl:element>
    </xsl:template>
    
    <!-- Generate a method -->
    <xsl:template name="create_method">
        <xsl:param name="modifier" />
        <xsl:param name="isReference">false</xsl:param>
        <xsl:param name="name" />
        <xsl:param name="params" />
        <xsl:param name="body" />
        <xsl:element name="methodDeclaration">
            <xsl:attribute name="modifier"><xsl:value-of select="$modifier" /></xsl:attribute>
            <xsl:element name="function">
                <xsl:attribute name="isReference"><xsl:value-of select="$isReference" /></xsl:attribute>
                <xsl:element name="functionName">
                    <xsl:attribute name="name"><xsl:value-of select="$name" /></xsl:attribute>
                </xsl:element>
                <formalParameters>
                    <xsl:copy-of select="$params" />
                </formalParameters>
                <body isCurly="true">
                        <xsl:copy-of select="$body" />
                </body>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:transform>