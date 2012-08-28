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
 * A logger which doesent to anything. Use this logger if you dont want anything to be logged
 * and for better runtime performance of an application.
 */
public class AndroidDummyLogger implements ILogger {
		
		/** The name. */
		private String name;
		
		/**
		 * Instantiates a new android dummy logger.
		 */
		public AndroidDummyLogger(){	}
		
		/**
		 * Instantiates a new android dummy logger.
		 *
		 * @param name the name
		 */
		private AndroidDummyLogger(String name){
			this.name = name;
		}
		
		/* (non-Javadoc)
		 * @see org.mt4j.util.logging.ILogger#setLevel(int)
		 */
		public void setLevel(int level) {
		}

		/* (non-Javadoc)
		 * @see org.mt4j.util.logging.ILogger#info(java.lang.Object)
		 */
		public void info(Object message) {
		}

		/* (non-Javadoc)
		 * @see org.mt4j.util.logging.ILogger#debug(java.lang.Object)
		 */
		public void debug(Object message) {
		}

		/* (non-Javadoc)
		 * @see org.mt4j.util.logging.ILogger#warn(java.lang.Object)
		 */
		public void warn(Object message) {
		}

		/* (non-Javadoc)
		 * @see org.mt4j.util.logging.ILogger#error(java.lang.Object)
		 */
		public void error(Object message) {
		}

		/* (non-Javadoc)
		 * @see org.mt4j.util.logging.ILogger#createNew(java.lang.String)
		 */
		public ILogger createNew(String name) {
			return new AndroidDummyLogger(name);
		}

		/* (non-Javadoc)
		 * @see org.mt4j.util.logging.ILogger#getLevel()
		 */
		public int getLevel() {
			return -1;
		}

	}
