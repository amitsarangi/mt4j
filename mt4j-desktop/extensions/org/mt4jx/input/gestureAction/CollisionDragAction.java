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
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;

public class CollisionDragAction implements IGestureEventListener {

	private IMTComponent3D dragTarget;
	private boolean useCustomTarget;	
	private boolean gestureAborted = false;
	
	public CollisionDragAction(){
		this.useCustomTarget = false;
	}
	
	public CollisionDragAction(IMTComponent3D dragTarget){
		this.dragTarget = dragTarget;
		this.useCustomTarget = true;
	}	
	
	public boolean processGestureEvent(MTGestureEvent ge) {
		
		if (ge instanceof DragEvent){
				DragEvent dragEvent = (DragEvent)ge;
				
				if (!useCustomTarget)
					dragTarget = dragEvent.getTarget(); 
				
				switch (dragEvent.getId()) {
				case MTGestureEvent.GESTURE_STARTED:
					//Put target on top -> draw on top of others
					if (dragTarget instanceof MTComponent){
						MTComponent baseComp = (MTComponent)dragTarget;
						
						baseComp.sendToFront();
						
						/*
						//End all animations of the target
						Animation[] animations = AnimationManager.getInstance().getAnimationsForTarget(dragTarget);
						for (int i = 0; i < animations.length; i++) {
							Animation animation = animations[i];
							animation.stop();
						}
						*/
					}
					dragTarget.translateGlobal(dragEvent.getTranslationVect());
					break;
				case MTGestureEvent.GESTURE_UPDATED:
					if(!isGestureAborted())
					{
						dragTarget.translateGlobal(dragEvent.getTranslationVect());						
					}
					break;
				case MTGestureEvent.GESTURE_ENDED:
					break;
				default:
					break;
				}
			}
			return false;
	}

	public void setGestureAborted(boolean gestureAborted) {
		this.gestureAborted = gestureAborted;
	}

	public boolean isGestureAborted() {
		return gestureAborted;
	}
}

