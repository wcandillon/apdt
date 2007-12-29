<?xml version="1.0" ?>
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:include href="ast_utils.xsl" />
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="aspectDeclaration">
        <program>
            <statements>
                <xsl:call-template name="create_require_once">
                    <xsl:with-param name="path">&#34;PHPAspect/Runtime/Aspect.php&#34;</xsl:with-param>
                </xsl:call-template>
                <xsl:copy-of select="parent::statements/expressionStatement[expr/@class='include']"/>
                <!-- @PHPAspect -->
                <xsl:call-template name="create_annotation">
                    <xsl:with-param name="name">PHPAspect</xsl:with-param>
                </xsl:call-template>
                <!-- Class declaration -->
                <xsl:element name="classDeclaration">
                    <xsl:attribute name="modifier">
                        <xsl:value-of select="@modifier"/>
                    </xsl:attribute>
                    <xsl:copy-of select="name"/>
                    <xsl:copy-of select="superClass"/>
                    <!-- implements Aspect -->
                    <interfaces>
                        <identifier name="Aspect"/>
                        <xsl:copy-of select="interfaces/identifier"/>
                    </interfaces>
                    <body isCurly="true">
                        <statements>
                            <xsl:copy-of select="//fieldsDeclaration"/>
                            <xsl:copy-of select="//methodDeclaration"/>
                            <!-- Code advices -->
                            <xsl:for-each select="//aspectCodeAdviceDeclaration">
                                <xsl:call-template name="create_method">
                                    <xsl:with-param name="modifier">1</xsl:with-param>
                                    <xsl:with-param name="isReference">false</xsl:with-param>
                                    <xsl:with-param name="name">codeAdvice_<xsl:value-of select="generate-id(.)"></xsl:value-of></xsl:with-param>
                                    <xsl:with-param name="params">
                                        <formalParameter isMandatory="false">
                                            <parameterType name="JoinPoint"/>
                                            <parameterName class="variable" isDollared="true">
                                                <variableName class="identifier" name="thisJoinPoint" />
                                            </parameterName>
                                        </formalParameter>
                                    </xsl:with-param>
                                    <xsl:with-param name="body"><xsl:copy-of select="body/statements" /></xsl:with-param>
                                </xsl:call-template>
                            </xsl:for-each>
                            <fieldsDeclaration modifier="10">
                                <variableNames>
                                    <variable isDollared="true">
                                        <variableName class="identifier" name="__instance" start="42" length="10"/>
                                    </variable>
                                </variableNames>
                                <initialValues>
                                    <null/>
                                </initialValues>
                            </fieldsDeclaration>
                            <fieldsDeclaration modifier="10">
                                <variableNames>
                                    <variable isDollared="true">
                                        <variableName class="identifier" name="__reflector" />
                                    </variable>
                                </variableNames>
                                <initialValues>
                                    <null/>
                                </initialValues>
                            </fieldsDeclaration>
                            <methodDeclaration modifier="2" start="89" length="32">
                                <function isReference="false" start="97" length="24">
                                    <functionName name="__construct" start="106" length="11"/>
                                    <formalParameters/>
                                    <body isCurly="true" start="119" length="2">
                                        <statements/>
                                    </body>
                                </function>
                            </methodDeclaration>
                            <methodDeclaration modifier="1" start="125" length="139">
                                <function isReference="false" start="132" length="132">
                                    <functionName name="getInstance" start="141" length="11"/>
                                    <formalParameters/>
                                    <body isCurly="true" start="154" length="110">
                                        <statements>
                                            <ifStatement start="158" length="72">
                                                <condition class="infixExpression" operator="2" start="161" length="25">
                                                    <right class="scalar" stringValue="null" scalarType="2" start="182" length="4"/>
                                                    <left class="staticFieldAccess" start="161" length="17">
                                                        <field isDollared="true" start="167" length="11">
                                                            <variableName class="identifier" name="__instance" start="168" length="10"/>
                                                        </field>
                                                        <className name="self" start="161" length="4"/>
                                                    </left>
                                                </condition>
                                                <trueStatement class="block" isCurly="true" start="187" length="43">
                                                    <statements>
                                                        <expressionStatement start="192" length="31">
                                                            <expr class="assignment" operator="0" start="192" length="30">
                                                                <variable class="staticFieldAccess" start="192" length="17">
                                                                    <field isDollared="true" start="198" length="11">
                                                                        <variableName class="identifier" name="__instance" start="199" length="10"/>
                                                                    </field>
                                                                    <className name="self" start="192" length="4"/>
                                                                </variable>
                                                                <value class="classInstanceCreation" start="212" length="10">
                                                                    <className start="216" length="4">
                                                                        <className class="identifier" name="self" start="216" length="4"/>
                                                                    </className>
                                                                    <ctorParams/>
                                                                </value>
                                                            </expr>
                                                        </expressionStatement>
                                                    </statements>
                                                </trueStatement>
                                            </ifStatement>
                                            <returnStatement start="236" length="25">
                                                <expr class="staticFieldAccess" start="243" length="17">
                                                    <field isDollared="true" start="249" length="11">
                                                        <variableName class="identifier" name="__instance" start="250" length="10"/>
                                                    </field>
                                                    <className name="self" start="243" length="4"/>
                                                </expr>
                                            </returnStatement>
                                        </statements>
                                    </body>
                                </function>
                            </methodDeclaration>
                            <methodDeclaration modifier="1" start="268" length="155">
                                <function isReference="false" start="275" length="148">
                                    <functionName name="getReflector" start="284" length="12"/>
                                    <formalParameters/>
                                    <body isCurly="true" start="298" length="125">
                                        <statements>
                                            <ifStatement start="302" length="86">
                                                <condition class="infixExpression" operator="2" start="305" length="26">
                                                    <right class="scalar" stringValue="null" scalarType="2" start="327" length="4"/>
                                                    <left class="staticFieldAccess" start="305" length="18">
                                                        <field isDollared="true" start="311" length="12">
                                                            <variableName class="identifier" name="__reflector" start="312" length="11"/>
                                                        </field>
                                                        <className name="self" start="305" length="4"/>
                                                    </left>
                                                </condition>
                                                <trueStatement class="block" isCurly="true" start="332" length="56">
                                                    <statements>
                                                        <expressionStatement start="337" length="44">
                                                            <expr class="assignment" operator="0" start="337" length="43">
                                                                <variable class="staticFieldAccess" start="337" length="18">
                                                                    <field isDollared="true" start="343" length="12">
                                                                        <variableName class="identifier" name="__reflector" start="344" length="11"/>
                                                                    </field>
                                                                    <className name="self" start="337" length="4"/>
                                                                </variable>
                                                                <value class="classInstanceCreation" start="358" length="22">
                                                                    <className start="362" length="16">
                                                                        <className class="identifier" name="ReflectionAspect" start="362" length="16"/>
                                                                    </className>
                                                                    <ctorParams/>
                                                                </value>
                                                            </expr>
                                                        </expressionStatement>
                                                    </statements>
                                                </trueStatement>
                                            </ifStatement>
                                            <returnStatement start="394" length="26">
                                                <expr class="staticFieldAccess" start="401" length="18">
                                                    <field isDollared="true" start="407" length="12">
                                                        <variableName class="identifier" name="__reflector" start="408" length="11"/>
                                                    </field>
                                                    <className name="self" start="401" length="4"/>
                                                </expr>
                                            </returnStatement>
                                        </statements>
                                    </body>
                                </function>
                            </methodDeclaration>
                            <methodDeclaration modifier="1" start="427" length="106">
                                <function isReference="false" start="434" length="99">
                                    <functionName name="__clone" start="443" length="7"/>
                                    <formalParameters/>
                                    <body isCurly="true" start="452" length="81">
                                        <statements>
                                            <throwStatement start="456" length="74">
                                                <expr class="classInstanceCreation" start="462" length="67">
                                                    <className start="466" length="26">
                                                        <className class="identifier" name="CloneNotSupportedException" start="466" length="26"/>
                                                    </className>
                                                    <ctorParams>
                                                        <scalar stringValue="&apos;Clonning an aspect is not allowed&apos;" scalarType="2" start="493" length="35"/>
                                                    </ctorParams>
                                                </expr>
                                            </throwStatement>
                                        </statements>
                                    </body>
                                </function>
                            </methodDeclaration>
                        </statements>
                    </body>
                </xsl:element>
            </statements>
            <comments class="tree-map">
                <no-comparator/>
            </comments>
        </program>
    </xsl:template>
</xsl:transform>