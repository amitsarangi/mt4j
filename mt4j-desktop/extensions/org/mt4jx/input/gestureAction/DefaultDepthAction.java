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

import org.mt4j.components.MTComponent;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.input.gestureAction.ICollisionAction;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.rotate3DProcessor.Cluster3DExt;
import org.mt4j.util.math.Vector3D;
import org.mt4jx.input.inputProcessors.componentProcessors.depthProcessor.DepthGestureEvent;

public class DefaultDepthAction implements IGestureEventListener,ICollisionAction {

	private IMTComponent3D dragDepthTarget;
	
	private boolean gestureAborted = false;
	private MTGestureEvent lastEvent;
	
	public DefaultDepthAction(IMTComponent3D dragDepthObject)
	{
		this.dragDepthTarget = dragDepthObject;			
	}
	
	public boolean processGestureEvent(MTGestureEvent ge) {
		DepthGestureEvent depthEv;
		if(ge instanceof DepthGestureEvent)
		{
			lastEvent = ge;
			depthEv = (DepthGestureEvent)ge;
		}
		else
		{
			return false;
		}
		
		switch(depthEv.getId())
		{
			case MTGestureEvent.GESTURE_STARTED:
			{
				if (dragDepthTarget instanceof MTComponent){
					MTComponent baseComp = (MTComponent)dragDepthTarget;	
					baseComp.sendToFront();					
					
				}
				Vector3D zVector = new Vector3D(0.0f,0.0f,depthEv.getTranslationVect().z);
				
				if(!(dragDepthTarget instanceof Cluster3DExt))
				{
					dragDepthTarget.translateGlobal(zVector);
				}else
				{
					//only move children, not cluster itself
					//cause it should stay on the floor
					Cluster3DExt cl = (Cluster3DExt)dragDepthTarget;
					for(MTComponent comp : cl.getChildren())
					{
						if(!(comp instanceof MTPolygon))
						{
							comp.translateGlobal(zVector);							
						}
					}
				}
				break;
			}
			case MTGestureEvent.GESTURE_UPDATED:
			{
				Vector3D zVector = new Vector3D(0.0f,0.0f,depthEv.getTranslationVect().z);
				
				if(!(dragDepthTarget instanceof Cluster3DExt)&&!gestureAborted)
				{
					dragDepthTarget.translateGlobal(zVector);					
				}else
				{
					//only move children, not cluster itself
					//cause it should stay on the floor
					Cluster3DExt cl = (Cluster3DExt)dragDepthTarget;
					//remove
															
					cl.translateGlobal(zVector);
					//remove end
					/*for(MTComponent comp : cl.getChildren())
					{
						if(!(comp instanceof MTPolygon))
						{
							comp.translateGlobal(zVector);
						}
					}*/
				}
				break;
			}
			case MTGestureEvent.GESTURE_ENDED:
				break;
			default:
				break;
		}
		return true;
	}

	public boolean gestureAborted() {
		return this.gestureAborted;		
	}

	public void setGestureAborted(boolean aborted) {
		this.gestureAborted = aborted;
	}

	public MTGestureEvent getLastEvent() {
		return this.lastEvent;
	}

}
