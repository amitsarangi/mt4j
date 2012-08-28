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
package org.mt4jx.input.gestureAction;

import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.util.camera.Icamera;
import org.mt4jx.components.visibleComponents.widgets.MTDepthHelper;
import processing.core.PApplet;


public class CreateDragHelperAction implements IGestureEventListener{
	
	private PApplet pApplet;
	
	private MTCanvas canvas;
	
	private Icamera cam;
	
	private MTComponent targetComponent;
	
	private MTDepthHelper depthHelper;

	/**
	 * to scale to the correct size after zooming 
	 * the distance between camera and near plane 
	 * wihtout zoom is needed
	 */
	private float zDistanceWithoutZoom;
		
	public CreateDragHelperAction(PApplet v_pApplet,MTCanvas v_canvas,Icamera v_cam,MTComponent v_targetComponent)
	{
		this.pApplet = v_pApplet;
		this.canvas = v_canvas;
		this.cam = v_cam;
		this.targetComponent = v_targetComponent;
		this.zDistanceWithoutZoom = cam.getFrustum().getZValueOfNearPlane();
	    
	}
		
	public boolean processGestureEvent(MTGestureEvent ge) {
		
		if(ge instanceof DragEvent)
		{
			DragEvent evt = (DragEvent)ge;			
			switch (evt.getId()) {
			case MTGestureEvent.GESTURE_STARTED:
				depthHelper = new MTDepthHelper(pApplet,targetComponent,cam,canvas);				
				canvas.addChild(depthHelper);				
				break;
			case MTGestureEvent.GESTURE_UPDATED:				
				break;
			case MTGestureEvent.GESTURE_ENDED:				
				deleteDepthHelper();
				break;
			}
			
		}
		return false;
	}	

	private void deleteDepthHelper()
	{
		canvas.removeChild(depthHelper);
	}
	
	
	
	

}
