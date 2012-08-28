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
package org.mt4j.util.animation;

/**
 * The Interface IAnimation.
 */
public interface IAnimation {

	/**
	 * Start the animation
	 */
	public abstract void start();

	/**
	 * Stop the animation
	 */
	public abstract void stop();

	/**
	 * Adds the animation listener.
	 *
	 * @param listener the listener
	 * @return the i animation
	 */
	public abstract IAnimation addAnimationListener(IAnimationListener listener);

	/**
	 * Removes the animation listener.
	 * 
	 * @param listener the listener
	 */
	public abstract void removeAnimationListener(IAnimationListener listener);

	/**
	 * Gets the delta.
	 *
	 * @return the delta
	 */
	public abstract float getDelta();

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public abstract float getValue();
	
	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public Object getTarget();

	/**
	 * Gets the animation listeners.
	 *
	 * @return the animation listeners
	 */
	public abstract IAnimationListener[] getAnimationListeners();

}