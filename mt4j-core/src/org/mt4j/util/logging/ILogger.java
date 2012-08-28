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
package org.mt4j.util.logging;

/**
 * The Interface ILogger.
 */
public interface ILogger {
	
	/** The Constant OFF. */
	public static final int OFF = 0;
	
	/** The Constant ALL. */
	public static final int ALL = 1;
	
	/** The Constant INFO. */
	public static final int INFO = 2;
	
	/** The Constant DEBUG. */
	public static final int DEBUG = 3;
	
	/** The Constant WARN. */
	public static final int WARN = 4;
	
	/** The Constant ERROR. */
	public static final int ERROR = 5;
	
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level);
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel();
	
	/**
	 * Info.
	 *
	 * @param msg the msg
	 */
	public void info(Object msg);

	/**
	 * Debug.
	 *
	 * @param msg the msg
	 */
	public void debug(Object msg);
	
	/**
	 * Warn.
	 *
	 * @param msg the msg
	 */
	public void warn(Object msg);
	
	/**
	 * Error.
	 *
	 * @param msg the msg
	 */
	public void error(Object msg);

	/**
	 * Creates the new.
	 *
	 * @param name the name
	 * @return the i logger
	 */
	public ILogger createNew(String name);
	
	//public void setOutputChannel(int channel);

}

