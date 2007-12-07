<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
<!ENTITY newline "
">
]>
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="aspectDeclaration">
        <program>
            <statements>
                <expressionStatement>
                    <expr class="include" includeType="1">
                        <expr class="scalar"
                            stringValue="&#34;PHPAspect/Runtime/Aspect.php&#34;"
                            scalarType="2"/>
                    </expr>
                </expressionStatement>
                <expressionStatement>
                    <expr class="include" includeType="1">
                        <expr class="scalar"
                            stringValue="&#34;PHPAspect/Runtime/Aspects.php&#34;"
                            scalarType="2"/>
                    </expr>
                </expressionStatement>
                <xsl:copy-of select="parent::statements/expressionStatement[expr/@class='include']"/>
                <xsl:element name="classDeclaration">
                    <xsl:attribute name="modifier">
                        <xsl:value-of select="@modifier"/>
                    </xsl:attribute>
                    <xsl:copy-of select="name"/>
                    <xsl:copy-of select="superClass"/>
                    <interfaces>
                        <identifier name="Aspect"/>
                        <xsl:copy-of select="interfaces/identifier"/>
                    </interfaces>
                    <body isCurly="true">
                        <statements>
                            <xsl:copy-of select="//fieldsDeclaration"/>
                            <xsl:copy-of select="//methodDeclaration"/>
                            <fieldsDeclaration modifier="10">
                                <variableNames>
                                    <variable isDollared="true">
                                        <variableName class="identifier" name="instance"/>
                                    </variable>
                                </variableNames>
                                <initialValues>
                                    <null/>
                                </initialValues>
                            </fieldsDeclaration>
                            <methodDeclaration modifier="2" start="173" length="36">
                                <function isReference="false" start="181" length="28">
                                    <functionName name="__construct" start="190" length="11"/>
                                    <formalParameters/>
                                    <body isCurly="true" start="203" length="6">
                                        <statements/>
                                    </body>
                                </function>
                            </methodDeclaration>
                            <methodDeclaration modifier="9" start="213" length="134">
                                <function isReference="false" start="227" length="120">
                                    <functionName name="getInstance" start="236" length="11"/>
                                    <formalParameters/>
                                    <body isCurly="true" start="249" length="98">
                                        <statements>
                                            <ifStatement start="253" length="65">
                                                <condition class="infixExpression" operator="2"
                                                  start="256" length="23">
                                                  <right class="scalar" stringValue="null"
                                                  scalarType="2" start="275" length="4"/>
                                                  <left class="staticFieldAccess" start="256"
                                                  length="15">
                                                  <field isDollared="true" start="262"
                                                  length="9">
                                                  <variableName class="identifier"
                                                  name="instance" start="263"
                                                  length="8"/>
                                                  </field>
                                                  <className name="self" start="256"
                                                  length="4"/>
                                                  </left>
                                                </condition>
                                                <trueStatement class="block" isCurly="true"
                                                  start="280" length="38">
                                                  <statements>
                                                  <expressionStatement start="285" length="29">
                                                  <expr class="assignment" operator="0"
                                                  start="285" length="28">
                                                  <variable class="staticFieldAccess"
                                                  start="285" length="15">
                                                  <field isDollared="true"
                                                  start="291" length="9">
                                                  <variableName
                                                  class="identifier"
                                                  name="instance"
                                                  start="292" length="8"/>
                                                  </field>
                                                  <className name="self"
                                                  start="285" length="4"/>
                                                  </variable>
                                                  <value class="classInstanceCreation"
                                                  start="303" length="10">
                                                  <className start="307"
                                                  length="4">
                                                  <className
                                                  class="identifier"
                                                  name="self" start="307"
                                                  length="4"/>
                                                  </className>
                                                  <ctorParams/>
                                                  </value>
                                                  </expr>
                                                  </expressionStatement>
                                                  </statements>
                                                </trueStatement>
                                            </ifStatement>
                                            <returnStatement start="321" length="23">
                                                <expr class="staticFieldAccess" start="328"
                                                  length="15">
                                                  <field isDollared="true" start="334" length="9">
                                                  <variableName class="identifier"
                                                  name="instance" start="335" length="8"/>
                                                  </field>
                                                  <className name="self" start="328" length="4"/>
                                                </expr>
                                            </returnStatement>
                                        </statements>
                                    </body>
                                </function>
                            </methodDeclaration>
                        </statements>
                    </body>
                </xsl:element>
            </statements>
            <xsl:copy-of select="ancestor::program/comments"/>
        </program>
    </xsl:template>
</xsl:transform>
