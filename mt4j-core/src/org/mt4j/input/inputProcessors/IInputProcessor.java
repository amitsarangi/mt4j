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
package org.mt4j.input.inputProcessors;

import org.mt4j.input.inputData.MTInputEvent;


/**
 * The Interface IInputProcessor.
 * 
 * @author Christopher Ruff
 */
public interface IInputProcessor {
	
//	/**
//	 * Gets the motion locking priority.
//	 * 
//	 * @return the locking priority
//	 */
//	public int getLockPriority();
	
	
//	/**
//	 * Process input evt implementation.
//	 * 
//	 * @param inputEvent the input event
//	 */
//	abstract public void processInputEvtImpl(MTInputEvent inputEvent);
	
	/**
	 * Process input evt.
	 * 
	 * @param inputEvent the input event
	 */
	abstract public boolean processInputEvent(MTInputEvent inputEvent);
	
	
	/**
	 * Checks if is disabled.
	 * 
	 * @return true, if is disabled
	 */
	public boolean isDisabled();

	/**
	 * Sets the disabled.
	 * 
	 * @param disabled the new disabled
	 */
	public void setDisabled(boolean disabled);


}
