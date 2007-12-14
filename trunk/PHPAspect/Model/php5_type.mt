<%
metamodel http://www.eclipse.org/emf/2002/Ecore
%>

<%script type="ecore.EClass" name="php5_interface" file="<%ePackage.name%>/<%name%>.php"%>
<?php
/*******************************************************************************
 * Copyright (c) 2006-2007 William Candillon.
 * All rights reserved.
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution and is available at
 * http://eclipse.org/legal/epl-v10.html
 *
 * @author William Candillon <wcandillon@elv.telecom-lille1.eu>
 * @category   PHP
 * @package    PHPAspect/<%ePackage.name%>
 * @author     William Candillon <wcandillon@elv.telecom-lille1.eu>
 * @license    http://eclipse.org/legal/epl-v10.html EPL
 * @version    1.0.0
 * @link       http://phpaspect.org
 *******************************************************************************/
interface <%name%>{
<%for (eAllOperations){%>

    /**
     * <%eAnnotations.source%>
     * @return <%eType.name%>
     */
<%}%>
}
?>

