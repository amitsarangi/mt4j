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
 * The listener interface for receiving ISystemButton events.
 * The class that is interested in processing a ISystemButton
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addISystemButtonListener<code> method. When
 * the ISystemButton event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ISystemButtonEvent
 */
public interface ISystemButtonListener {
	
	/**
	 * On back pressed.
	 */
	public void onBackPressed();
	
	/**
	 * On pause.
	 */
	public void onPause();
	
	/**
	 * On restart.
	 */
	public void onRestart();
	
	/**
	 * On resume.
	 */
	public void onResume();
	

}
