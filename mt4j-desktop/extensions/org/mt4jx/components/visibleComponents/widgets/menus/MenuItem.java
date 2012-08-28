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
package org.mt4jx.components.visibleComponents.widgets.menus;

import org.mt4j.input.inputProcessors.IGestureEventListener;

import processing.core.PImage;

public class MenuItem {
	private String menuText = "";
	private short type = 0;
	private PImage menuImage = null;
	private IGestureEventListener gestureListener = null;
	
	
	public static short TEXT = 1;
	public static short PICTURE = 2;
	public static short NONE = 0;
	
	public MenuItem(String text, IGestureEventListener al) {
		this.type = TEXT;
		this.menuText = text;
		this.gestureListener = al;
	}
	public MenuItem(PImage image, IGestureEventListener al) {
		this.type = PICTURE;
		this.menuImage = image;
		this.gestureListener = al;
	}
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
		this.type = TEXT;
	}
	public PImage getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(PImage menuImage) {
		this.menuImage = menuImage;
		this.type = PICTURE;
	}
	public short getType() {
		return type;
	}
	public IGestureEventListener getGestureListener() {
		return gestureListener;
	}
	public void setGestureListener(IGestureEventListener gestureListener) {
		this.gestureListener = gestureListener;
	}
	
	
	
}
