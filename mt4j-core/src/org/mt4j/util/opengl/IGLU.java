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
package org.mt4j.util.opengl;

import java.nio.Buffer;

/**
 * The Interface IGLU.
 */
public interface IGLU {
	
	/**
	 * Glu build2 d mipmaps.
	 *
	 * @param target the target
	 * @param internalFormat the internal format
	 * @param width the width
	 * @param height the height
	 * @param format the format
	 * @param type the type
	 * @param data the data
	 * @return the int
	 */
	public int gluBuild2DMipmaps(
			int  	target, 
			int  	internalFormat, 
			int  	width, 
			int  	height, 
			int  	format, 
			int  	type, 
		 	Buffer 	data);

}
