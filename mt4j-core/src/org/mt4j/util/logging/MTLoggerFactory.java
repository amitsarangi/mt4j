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
 * A factory for creating MTLogger objects.
 */
public class MTLoggerFactory {
//	private static MTLoggerFactory instance;
	/** The logger. */
private static ILogger logger;
	
	/**
	 * Instantiates a new mT logger factory.
	 */
	private MTLoggerFactory(){}
	
//	public static MTLoggerFactory getInstance(){
//		if (instance == null){
//			instance = new MTLoggerFactory();
//		}
//		return instance;
//	}
	
	
	/**
 * Gets the logger.
 *
 * @param name the name
 * @return the logger
 */
public static ILogger getLogger(String name) {
		if (logger != null){
			return logger.createNew(name);
		}else{
			throw new NoLoggerProvidedException();
		}
	}
	
	
	/**
	 * Sets the logger provider.
	 *
	 * @param logger the new logger provider
	 */
	public static void setLoggerProvider(ILogger logger){
		MTLoggerFactory.logger = logger;
	}
	

}
