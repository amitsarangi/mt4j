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

import org.mt4j.MTAndroidApplication;
import org.mt4j.input.inputSources.AndroidMTInputSource;

/**
 * The Class AndroidInputManager.
 */
public class AndroidInputManager extends InputManager {
	
	/**
	 * Instantiates a new android input manager.
	 *
	 * @param pa the pa
	 */
	public AndroidInputManager(MTAndroidApplication pa) {
		this(pa, true);
	}
	
	/**
	 * Instantiates a new android input manager.
	 *
	 * @param pa the pa
	 * @param registerDefaultSources the register default sources
	 */
	public AndroidInputManager(MTAndroidApplication pa, boolean registerDefaultSources) {
		super(pa, registerDefaultSources);
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.input.InputManager#registerDefaultInputSources()
	 */
	@Override
	protected void registerDefaultInputSources() {
		super.registerDefaultInputSources();
		
		if (app instanceof MTAndroidApplication){
			this.registerInputSource(new AndroidMTInputSource((MTAndroidApplication)app));	
		}
	}



}
