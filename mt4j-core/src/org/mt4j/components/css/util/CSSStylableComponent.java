/***********************************************************************
*   MT4j Copyright (c) 2008 - 2012, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
*
*   This file is part of MT4j.
*
*   MT4j is free software: you can redistribute it and/or modify
*   it under the terms of the GNU Lesser General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   MT4j is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
*   GNU Lesser General Public License for more details.
*
*   You should have received a copy of the GNU Lesser General Public License
*   along with MT4j.  If not, see <http://www.gnu.org/licenses/>.
*
************************************************************************/
package org.mt4j.components.css.util;

/**
 * The Interface CSSStylableComponent.
 */
public interface CSSStylableComponent {
	
	/**
	 * Checks if the Component is CSS styled.
	 *
	 * @return true, if is cSS styled
	 */
	public boolean isCSSStyled();
	
	/**
	 * Enables the CSS (if everything is right).
	 */
	public void enableCSS();
	
	/**
	 * Disable the CSS.
	 */
	public void disableCSS();
	
	/**
	 * Appl�es the (global) style sheets.
	 */
	public void applyStyleSheet();
	
	/**
	 * Gets the css helper.
	 *
	 * @return the css helper
	 */
	public CSSHelper getCssHelper();
	
	
	/**
	 * Checks if css is force disabled.
	 *
	 * @return true, if is css force disabled
	 */
	public boolean isCssForceDisabled();
	
	/**
	 * Sets the css force disable.
	 *
	 * @param cssForceDisabled the new css force disable
	 */
	public void setCssForceDisable(boolean cssForceDisabled);
}
