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

import org.mt4j.input.MTEvent;

/**
 * The Class AnimationUpdateEvent.
 * @author Christopher Ruff
 */
public class AnimationUpdateEvent extends MTEvent {
	
	/** The delta time. */
	private long deltaTime;
	
	/**
	 * Instantiates a new animation update event.
	 * 
	 * @param source the source
	 * @param deltaTime the delta time
	 */
	public AnimationUpdateEvent(Object source, long deltaTime) {
		super(source);
		
		this.deltaTime = deltaTime;
	}

	/**
	 * Gets the delta time.
	 * 
	 * @return the delta time
	 */
	public long getDeltaTime() {
		return deltaTime;
	}

	/**
	 * Sets the delta time.
	 * 
	 * @param deltaTime the new delta time
	 */
	public void setDeltaTime(long deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	

}
