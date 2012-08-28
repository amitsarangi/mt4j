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
package org.mt4j.input.gestureAction;

import org.mt4j.components.MTComponent;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.arcballProcessor.ArcBallGestureEvent;

/**
 * The Class DefaultArcballAction.
 * 
 * @author Christopher Ruff
 */
public class DefaultArcballAction implements IGestureEventListener {
	
	/** The target. */
	private IMTComponent3D target;
	
	/** The use custom target. */
	private boolean useCustomTarget;
	
	/**
	 * Instantiates a new default arcball action.
	 */
	public DefaultArcballAction(){
		this.useCustomTarget = false;
	}
	
	/**
	 * Instantiates a new default arcball action.
	 * 
	 * @param customTarget the target
	 */
	public DefaultArcballAction(IMTComponent3D customTarget){
		this.target = customTarget;
		this.useCustomTarget = true;
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.input.inputProcessors.IGestureEventListener#processGestureEvent(org.mt4j.input.inputProcessors.MTGestureEvent)
	 */
	public boolean processGestureEvent(MTGestureEvent g) {
		ArcBallGestureEvent aEvt =  (ArcBallGestureEvent)g;
		
		if (!useCustomTarget)
			target = aEvt.getTarget(); 
		
		if (target instanceof MTComponent){
			((MTComponent)target).sendToFront();
		}
		
		if (aEvt.getId() == ArcBallGestureEvent.GESTURE_UPDATED){
			if (target instanceof MTComponent){
				MTComponent comp = (MTComponent)target;
				comp.transform(aEvt.getTransformationMatrix());
			}
		}
		return false;
	}

}
