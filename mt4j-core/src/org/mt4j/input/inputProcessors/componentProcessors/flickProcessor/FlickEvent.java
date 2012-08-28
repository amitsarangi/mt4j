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
package org.mt4j.input.inputProcessors.componentProcessors.flickProcessor;

import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputProcessors.IInputProcessor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.util.math.Vector3D;

/**
 * The Class FlickEvent.
 */
public class FlickEvent extends MTGestureEvent {
	
	/** The direction. */
	private FlickDirection direction;
	
	/** The is flick. */
	private boolean isFlick;
	
	/**
	 * The Enum FlickDirection.
	 */
	public enum FlickDirection{
		 
 		/** The WEST. */
 		WEST,
		 
 		/** The NORT h_ west. */
 		NORTH_WEST,
		 
 		/** The NORTH. */
 		NORTH,
		 
 		/** The NORT h_ east. */
 		NORTH_EAST,
		 
 		/** The EAST. */
 		EAST,
		 
 		/** The SOUT h_ east. */
 		SOUTH_EAST,
		 
 		/** The SOUTH. */
 		SOUTH,
		 
 		/** The SOUT h_ west. */
 		SOUTH_WEST, 
		 
 		/** The UNDETERMINED. */
 		UNDETERMINED,
	}
	

	/**
	 * Instantiates a new flick event.
	 *
	 * @param source the source
	 * @param id the id
	 * @param targetComponent the target component
	 * @param direction the direction
	 * @param isFlickComplete the is flick complete
	 */
	public FlickEvent(IInputProcessor source, int id, IMTComponent3D targetComponent, FlickDirection direction, boolean isFlickComplete) {
		super(source, id, targetComponent);
		this.direction = direction;
		this.isFlick = isFlickComplete;
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public FlickDirection getDirection() {
		return direction;
	}

	/**
	 * Checks if is flick.
	 *
	 * @return true, if is flick
	 */
	public boolean isFlick() {
		return isFlick;
	}
	

}
