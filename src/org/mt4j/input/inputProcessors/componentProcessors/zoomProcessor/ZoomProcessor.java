/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009 C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
 *  
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ***********************************************************************/
package org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputData.MTFingerInputEvt;
import org.mt4j.input.inputProcessors.IInputProcessor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractCursorProcessor;
import org.mt4j.util.math.Vector3D;

import processing.core.PApplet;

/**
 * The Class ZoomProcessor.
 * Multitouch background zoom gesture. This will change the scene's CAMERA if the DefaultZoomAction gesture listener is added to the canvas.
 * Fires ZoomEvent gesture events.
 * <br><strong>NOTE:</strong> Should only be used in combination with a MTCanvas component. 
 * @author Christopher Ruff
 */
public class ZoomProcessor extends AbstractCursorProcessor {
	
	/** The zoom detect radius. */
	private float zoomDetectRadius;
	
	/** The old distance. */
	private float oldDistance;
	
	/** The applet. */
	private PApplet applet;

	/** The unused cursors. */
	private List<InputCursor> unUsedCursors;
	
	/** The locked cursors. */
	private List<InputCursor> lockedCursors;
	
	/**
	 * Instantiates a new zoom processor.
	 * 
	 * @param graphicsContext the graphics context
	 */
	public ZoomProcessor(PApplet graphicsContext){
		this(graphicsContext, graphicsContext.width/2);
	}
	
	/**
	 * Instantiates a new zoom processor.
	 * 
	 * @param graphicsContext the graphics context
	 * @param zoomDetectRadius the zoom detect radius
	 */
	public ZoomProcessor(PApplet graphicsContext, float zoomDetectRadius){
		this.applet = graphicsContext;
		this.unUsedCursors 	= new ArrayList<InputCursor>();
		this.lockedCursors 	= new ArrayList<InputCursor>();
		this.zoomDetectRadius = zoomDetectRadius;
		this.setLockPriority(2);
	}

	
	@Override
	public void cursorStarted(InputCursor c, MTFingerInputEvt positionEvent) {
		IMTComponent3D comp = positionEvent.getTarget();
		if (lockedCursors.size() >= 2){ //scale with 2 fingers already in progress
			unUsedCursors.add(c);
			logger.debug(this.getName() + " has already enough cursors for this gesture - adding to unused ID:" + c.getId());
		}else{ //no scale in progress yet
			if (unUsedCursors.size() == 1){
				logger.debug(this.getName() + " has already has 1 unused cursor - we can try start gesture! used with ID:" + unUsedCursors.get(0).getId() + " and new cursor ID:" + c.getId());
				InputCursor otherCursor = unUsedCursors.get(0);
				
				//See if we can obtain a lock on both cursors
				if (this.canLock(otherCursor, c)){
					float newDistance = Vector3D.distance(otherCursor.getPosition(), c.getPosition());
					if (newDistance < zoomDetectRadius) {
						this.oldDistance = newDistance;
						this.getLock(otherCursor, c);
						lockedCursors.add(otherCursor);
						lockedCursors.add(c);
						unUsedCursors.remove(otherCursor);
						logger.debug(this.getName() + " we could lock both cursors! And fingers in zoom distance!");
						this.fireGestureEvent(new ZoomEvent(this, MTGestureEvent.GESTURE_DETECTED, comp, c, otherCursor, 0f, comp.getViewingCamera() ));
					}else{
						logger.debug(this.getName() + " cursors not close enough to start gesture. Distance: " + newDistance);
					}
				}else{
					logger.debug(this.getName() + " we could NOT lock both cursors!");
					unUsedCursors.add(c);	
				}
			}else{
				logger.debug(this.getName() + " we didnt have a unused cursor previously to start gesture now");
				unUsedCursors.add(c);
			}
		}
	}

	@Override
	public void cursorUpdated(InputCursor c, MTFingerInputEvt positionEvent) {
		IMTComponent3D comp = positionEvent.getTarget();
		if (lockedCursors.size() == 2 && lockedCursors.contains(c)){
			InputCursor firstCursor = lockedCursors.get(0);
			InputCursor secondCursor = lockedCursors.get(1);
			float fingerDistance = Vector3D.distance(firstCursor.getPosition(), secondCursor.getPosition());
			float camZoomAmount = fingerDistance - oldDistance;
			oldDistance = fingerDistance;
			if (c.equals(firstCursor)){
				this.fireGestureEvent(new ZoomEvent(this, MTGestureEvent.GESTURE_UPDATED, comp, firstCursor, secondCursor, camZoomAmount, comp.getViewingCamera()));
			}else{
				this.fireGestureEvent(new ZoomEvent(this, MTGestureEvent.GESTURE_UPDATED, comp, firstCursor, secondCursor, camZoomAmount, comp.getViewingCamera()));
			}
		}
	}

	@Override
	public void cursorEnded(InputCursor c,	MTFingerInputEvt positionEvent) {
		IMTComponent3D comp = positionEvent.getTarget();
		logger.debug(this.getName() + " INPUT_ENDED RECIEVED - cursor: " + c.getId());
		
		if (lockedCursors.size() == 2 && lockedCursors.contains(c)){
			InputCursor leftOverCursor = (lockedCursors.get(0).equals(c))? lockedCursors.get(1) : lockedCursors.get(0);
			
			lockedCursors.remove(c);
			if (unUsedCursors.size() > 0){ //Check if there are other cursors we could use for scaling if one was removed
				InputCursor futureCursor = getFarthestFreeCursorTo(leftOverCursor);
				float newDistance = Vector3D.distance(leftOverCursor.getPosition(),	futureCursor.getPosition());
				if (newDistance < zoomDetectRadius) {//Check if other cursor is in distance 
					this.oldDistance = newDistance;
					this.getLock(futureCursor);
					unUsedCursors.remove(futureCursor);
					lockedCursors.add(futureCursor);
					logger.debug(this.getName() + " we could lock another cursor! (ID:" + futureCursor.getId() +")");
					logger.debug(this.getName() + " continue with different cursors (ID: " + futureCursor.getId() + ")" + " " + "(ID: " + leftOverCursor.getId() + ")");
				}else{
					this.endGesture(c, leftOverCursor, comp);
				}
			}else{ //no more unused cursors on comp - End scale
				this.endGesture(c, leftOverCursor, comp);
			}
			this.unLock(c); 
		}else{ //cursor was not a scaling involved cursor
			if (unUsedCursors.contains(c)){
				unUsedCursors.remove(c);
			}
		}
	}
	
	
	/**
	 * End gesture.
	 * 
	 * @param inputEndedcursor the input ended cursor
	 * @param leftOvercursor the left over cursor
	 * @param comp the comp
	 */
	private void endGesture(InputCursor inputEndedCursor, InputCursor leftOverCursor, IMTComponent3D comp){
		lockedCursors.clear();
		unUsedCursors.add(leftOverCursor);
		this.unLock(leftOverCursor);
		this.fireGestureEvent(new ZoomEvent(this, MTGestureEvent.GESTURE_ENDED, comp, inputEndedCursor, leftOverCursor, 0f, comp.getViewingCamera()));
	}
	
	
	
	@Override
	public void cursorLocked(InputCursor c, IInputProcessor lockingProcessor) {
		if (lockingProcessor instanceof AbstractComponentProcessor){
			logger.debug(this.getName() + " Recieved cursor LOCKED by (" + ((AbstractComponentProcessor)lockingProcessor).getName()  + ") - cursor ID: " + c.getId());
		}else{
			logger.debug(this.getName() + " Recieved cursor LOCKED by higher priority signal - cursor ID: " + c.getId());
		}
		
		if (lockedCursors.contains(c)){ //cursors was used here! -> we have to stop the gesture
			//put all used cursors in the unused cursor list and clear the usedcursorlist
			unUsedCursors.addAll(lockedCursors); 
			lockedCursors.clear();
			this.fireGestureEvent(new ZoomEvent(this, MTGestureEvent.GESTURE_ENDED, c.getCurrentTarget(), lockedCursors.get(0), lockedCursors.get(1), 0f, c.getCurrentTarget().getViewingCamera()));
			logger.debug(this.getName() + " cursor:" + c.getId() + " cursor LOCKED. Was an active cursor in this gesture - we therefor have to stop this gesture!");
		}
	}

	
	
	@Override
	public void cursorUnlocked(InputCursor c) {
		logger.debug(this.getName() + " Recieved UNLOCKED signal for cursor ID: " + c.getId());
		
		if (lockedCursors.size() >= 2){ //we dont need the unlocked cursor, gesture still in progress
			return;
		}
		
		if (unUsedCursors.contains(c)){ //should always be true here!?
			if (unUsedCursors.size() >= 2){ //we can try to resume the gesture
				InputCursor firstCursor = unUsedCursors.get(0);
				InputCursor secondCursor = unUsedCursors.get(1);

				//See if we can obtain a lock on both cursors
				if (this.canLock(firstCursor, secondCursor)){
					float newDistance = Vector3D.distance(firstCursor.getPosition(), secondCursor.getPosition());
					if (newDistance < zoomDetectRadius) {//Check if other cursor is in distance 
						this.oldDistance = newDistance;
						this.getLock(firstCursor, secondCursor);

						unUsedCursors.remove(firstCursor);
						unUsedCursors.remove(secondCursor);
						lockedCursors.add(firstCursor);
						lockedCursors.add(secondCursor);
						logger.debug(this.getName() + " we could lock cursors: " + firstCursor.getId() +", " + secondCursor.getId());
						logger.debug(this.getName() + " continue with different cursors (ID: " + firstCursor.getId() + ")" + " " + "(ID: " + secondCursor.getId() + ")");
						this.fireGestureEvent(new ZoomEvent(this, MTGestureEvent.GESTURE_DETECTED, c.getCurrentTarget(), firstCursor, secondCursor, 0f, c.getCurrentTarget().getViewingCamera() ));
					}else{
						logger.debug(this.getName() + " distance was too great between cursors: " + firstCursor.getId() +", " + secondCursor.getId() + " distance: " + newDistance);
					}
				}else{
					logger.debug(this.getName() + " we could NOT lock cursors: " + firstCursor.getId() +", " + secondCursor.getId());
				}
			}
		}else{
			logger.error(this.getName() + "hmmm - investigate why is cursor not in unusedList?");
		}
	}
	
	
	@Override
	public String getName() {
		return "Zoom Processor";
	}
	

}
