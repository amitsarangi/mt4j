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
package org.mt4j.components.visibleComponents.widgets;


import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.util.MTColor;

import processing.core.PApplet;

/**
 * The Class MTListCell.
 */
public class MTListCell 
//extends MTRectangle{
extends MTClipRectangle{

	 
	 
	 /**
 	 * Instantiates a new mT list cell.
 	 *
 	 * @param width the width
 	 * @param height the height
 	 * @param applet the applet
	 * @deprecated constructor will be deleted! Please , use the constructor with the PApplet instance as the first parameter.
 	 */
 	public MTListCell(float width, float height, PApplet applet) {
		 this(applet, width, height);
	 }

	/**
	 * Instantiates a new mT list cell.
	 *
	 * @param applet the applet
	 * @param width the width
	 * @param height the height
	 */
	public MTListCell(PApplet applet, float width, float height) {
		super(applet, 0, 0, 0, width, height);
		this.setStrokeColor(new MTColor(0,0,0));
		this.setComposite(true);
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.components.visibleComponents.shapes.AbstractShape#setDefaultGestureActions()
	 */
	@Override
	protected void setDefaultGestureActions() {
//		this.registerInputProcessor(new DragProcessor(getRenderer()));
//		this.addGestureListener(DragProcessor.class, new DefaultDragAction());
		
//		DragProcessor dp = new DragProcessor(getRenderer());
////		dp.setLockPriority(1.0f);
//		registerInputProcessor(dp);
////		dp.setBubbledEventsEnabled(true);
		
		DragProcessor dp = new DragProcessor(getRenderer());
//		dp.setLockPriority(0.9f);
		registerInputProcessor(dp);
		dp.setBubbledEventsEnabled(true);
	}
	
	@Override
	public void registerInputProcessor(AbstractComponentProcessor inputProcessor) {
		super.registerInputProcessor(inputProcessor);
		
		inputProcessor.setBubbledEventsEnabled(true);
	}
}
