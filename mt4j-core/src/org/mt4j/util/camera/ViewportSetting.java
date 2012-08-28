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
package org.mt4j.util.camera;

/**
 * The Class ViewportSetting.
 */
public class ViewportSetting{
	
	/** The width. */
	int width;
	
	/** The height. */
	int height;
	
	/** The start x. */
	float startX;
	
	/** The start y. */
	float startY;
	
	/**
	 * Instantiates a new viewport setting.
	 * 
	 * @param startX the start x
	 * @param startY the start y
	 * @param width the width
	 * @param height the height
	 */
	public ViewportSetting(float startX, float startY, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.startX = startX;
		this.startY = startY;
		
		
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the start x.
	 * 
	 * @return the start x
	 */
	public float getStartX() {
		return startX;
	}

	/**
	 * Gets the start y.
	 * 
	 * @return the start y
	 */
	public float getStartY() {
		return startY;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
}
