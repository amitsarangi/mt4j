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
package org.mt4j.input;

/**
 * The listener interface for receiving IKey events.
 * The class that is interested in processing a IKey
 * event implements this interface. When
 * the Key event occurs, that object's appropriate
 * method is invoked.
 *
 * @see IKeyEvent
 */
public interface IKeyListener{
	
	/**
	 * Key pressed.
	 *
	 * @param key the key
	 * @param keyCode the key code
	 */
	public void keyPressed(char key, int keyCode);
	
	/**
	 * Key rleased.
	 *
	 * @param key the key
	 * @param keyCode the key code
	 */
	public void keyRleased(char key, int keyCode);
	
}
