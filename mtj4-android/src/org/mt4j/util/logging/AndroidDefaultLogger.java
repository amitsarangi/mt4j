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


import android.util.Log;

/**
 * A logger which uses androids default logging methods to do the logging.
 * The get/setLevel() method isnt supported with this logger.
 */
public class AndroidDefaultLogger implements ILogger {
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new android default logger.
	 */
	public AndroidDefaultLogger(){	}
	
	/**
	 * Instantiates a new android default logger.
	 *
	 * @param name the name
	 */
	private AndroidDefaultLogger(String name){
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.logging.ILogger#setLevel(int)
	 */
	public void setLevel(int level) {
		//TODO
		/*
		 You can change the default level by setting a system property: 'setprop log.tag.<YOUR_LOG_TAG> <LEVEL>' 
		 Where level is either VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT, or SUPPRESS. 
		 SUPPRESS will turn off all logging for your tag. 
		 You can also create a local.prop file that with the following in it: 'log.tag.<YOUR_LOG_TAG>=<LEVEL>' 
		 and place that in /data/local.prop.
		 'setprop log.tag.<YOUR_LOG_TAG> <LEVEL>'
		 */
		
//		switch (level) {
//		case OFF:
//			this.logger.setLevel(Level.OFF); 
//			break;
//		case ALL:
//			this.logger.setLevel(Level.ALL); 
//			break;
//		case INFO:
//			this.logger.setLevel(Level.INFO); 
//			break;
//		case DEBUG:
//			this.logger.setLevel(Level.CONFIG);  //TODO which level matches debug?
//			break;
//		case WARN:
//			this.logger.setLevel(Level.WARNING); 
//			break;
//		case ERROR:
//			this.logger.setLevel(Level.SEVERE); 
//			break;
//		default:
//			break;
//		}
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.logging.ILogger#info(java.lang.Object)
	 */
	public void info(Object message) {
		Log.i(name, message.toString());
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.logging.ILogger#debug(java.lang.Object)
	 */
	public void debug(Object message) {
		Log.d(name, message.toString());
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.logging.ILogger#warn(java.lang.Object)
	 */
	public void warn(Object message) {
		Log.w(name, message.toString());
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.logging.ILogger#error(java.lang.Object)
	 */
	public void error(Object message) {
		Log.e(name, message.toString());
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.logging.ILogger#createNew(java.lang.String)
	 */
	public ILogger createNew(String name) {
		return new AndroidDefaultLogger(name);
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.logging.ILogger#getLevel()
	 */
	public int getLevel() {
//		Level level = this.logger.getLevel();
//		if (level.equals(Level.OFF)){
//			return ILogger.OFF;
//		}else if (level.equals(Level.ALL)){
			return ILogger.ALL;
//		}else if (level.equals(Level.INFO)){
//			return ILogger.INFO;
//		}else if (level.equals(Level.CONFIG)){
//			return ILogger.DEBUG;
//		}else if (level.equals(Level.WARNING)){
//			return ILogger.WARN;
//		}else if (level.equals(Level.SEVERE)){
//			return ILogger.ERROR;
//		}else{
//			return -1;
//		}
	}

}