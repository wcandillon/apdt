/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JFlex 1.3.5                                                             *
 * Copyright (C) 1998-2001  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License. See the file      *
 * COPYRIGHT for more information.                                         *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *
 *                                                                         *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA                 *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package JFlex;


/**
 * Stores a regular expression of rules section in a JFlex-specification.
 *
 * This base class has no content other than its type. 
 *
 * @author Gerwin Klein
 * @version JFlex 1.3.5, $Revision: 1.12 $, $Date: 2001/10/08 10:08:03 $
 */
public class RegExp {
  
  /**
   * The type of the regular expression. This member will be
   * filled with values from class sym.java (generated by cup)
   */
  int type;
  

  /**
   * Create a new regular expression of the specified type.
   *
   * @param type   a value from the cup generated class sym.
   *
   * @see JFlex.sym
   */
  public RegExp(int type) {
    this.type = type;    
  }

  

  /**
   * Returns a String-representation of this regular expression
   * with the specified indentation.
   *
   * @param tab   a String that should contain only space characters and
   *              that is inserted in front of standard String-representation
   *              pf this object.
   */
  public String print(String tab) {
    return tab+toString();
  }


  /**
   * Returns a String-representation of this regular expression
   */
  public String toString() {
    return "type = "+type;
  }
}
