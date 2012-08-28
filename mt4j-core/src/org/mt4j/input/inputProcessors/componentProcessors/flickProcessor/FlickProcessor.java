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
package org.mt4j.input.inputProcessors.componentProcessors.flickProcessor;

import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.IInputProcessor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractCursorProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.flickProcessor.FlickEvent.FlickDirection;
import org.mt4j.util.math.Vector3D;


/**
 * The Class FlickProcessor.
 */
public class FlickProcessor extends AbstractCursorProcessor {
	
	/** The vel thresh hold. */
	private int velThreshHold;
	
	/** The flick time. */
	private int flickTime;
	
	/** The start pos. */
	private Vector3D startPos;
	
	/** The start time. */
	private long startTime;
	
	/** The flick velocity. */
	boolean flickVelocity;
	
	/** The west. */
	private Vector3D west = new Vector3D(-1,0,0);
	
	/** The north_west. */
	private Vector3D north_west = new Vector3D(-1,-1,0);
	
	/** The north. */
	private Vector3D north = new Vector3D(0,-1,0);
	
	/** The north_east. */
	private Vector3D north_east = new Vector3D(1,-1,0);
	
	/** The east. */
	private Vector3D east = new Vector3D(1,0,0);
	
	/** The south_east. */
	private Vector3D south_east = new Vector3D(1,1,0);
	
	/** The south. */
	private Vector3D south = new Vector3D(0,1,0);
	
	/** The south_west. */
	private Vector3D south_west = new Vector3D(-1,1,0);
	
	/** The directions. */
	private Vector3D[] directions = {west,north_west,north,north_east,east,south_east,south,south_west};

	/**
	 * Instantiates a new flick processor with default flicktime (300) and default velocity threshold (5).
	 */
	public FlickProcessor(){
		this(300,5);
	}
	
	/**
	 * Instantiates a new flick processor. 
	 *
	 * @param flickTime the flick time
	 * @param velocityThreshold the velocity threshold
	 */
	public FlickProcessor(int flickTime, int velocityThreshold){
		this.flickTime = flickTime;
		this.velThreshHold = velocityThreshold;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.input.inputProcessors.componentProcessors.AbstractCursorProcessor#cursorStarted(org.mt4j.input.inputData.InputCursor, org.mt4j.input.inputData.MTFingerInputEvt)
	 */
	@Override
	public void cursorStarted(InputCursor cursor, AbstractCursorInputEvt currentEvent) {
		InputCursor[] theLockedCursors = getLockedCursorsArray();
		//if gesture isnt started and no other cursor on comp is locked by higher priority gesture -> start gesture
		if (theLockedCursors.length == 0 && this.canLock(getCurrentComponentCursorsArray())){ 
				//Lock this cursor with our priority
				this.getLock(cursor);
				this.startPos = cursor.getPosition();
				this.startTime = currentEvent.getTimeStamp();
				this.flickVelocity = false;
//				logger.debug(this.getName() + " successfully locked cursor (id:" + cursor.getId() + ")");
				this.fireGestureEvent(new FlickEvent(this, MTGestureEvent.GESTURE_STARTED, cursor.getCurrentTarget(), FlickDirection.UNDETERMINED, false));
		}
	}

	/* (non-Javadoc)
	 * @see org.mt4j.input.inputProcessors.componentProcessors.AbstractCursorProcessor#cursorUpdated(org.mt4j.input.inputData.InputCursor, org.mt4j.input.inputData.MTFingerInputEvt)
	 */
	@Override
	public void cursorUpdated(InputCursor cursor, AbstractCursorInputEvt currentEvent) {
		if (getLockedCursors().contains(cursor)){
			/*
			long nowTime = currentEvent.getTimeStamp();
			long elapsedTime = nowTime - this.startTime;
			Vector3D nowPos = cursor.getPosition();
			
			float distanceTraveled = startPos.distance2D(nowPos);
			float nowVelocity = distanceTraveled / elapsedTime; //TODO prevent 0 divison
			System.out.println("Velocity:  " + nowVelocity + " (distance: " + distanceTraveled + ", millis: " + elapsedTime + ")");
			*/
			
			
//			Vector3D vel = cursor.getVelocityVector((int)elapsedTime);
			Vector3D vel = cursor.getVelocityVector(50);
			
//			Vector3D vel = cursor.getVelocityVector((int) (currentEvent.getTimeStamp() - this.startTime));
//			System.out.println("Veclocity: " + vel);
			
//			/*
			if (Math.abs(vel.x) > velThreshHold || Math.abs(vel.y) > velThreshHold){
				flickVelocity = true;
			}
//			*/
			
			//TODO we need to check the vector length but also check if the line is straight!
			long nowTime = currentEvent.getTimeStamp();
			long elapsedTime = nowTime - this.startTime;
			/*
			if (vel.length() > velThreshHold){ 
				flickVelocity = true;
			}
			*/
			
			//unlock the cursor to allow drag, for example, if the flick took too long to complete
			if (elapsedTime > flickTime){
				this.fireGestureEvent(new FlickEvent(this, MTGestureEvent.GESTURE_ENDED, cursor.getCurrentTarget(), FlickDirection.UNDETERMINED, false));
				this.unLock(cursor); 
			}else{
				this.fireGestureEvent(new FlickEvent(this, MTGestureEvent.GESTURE_UPDATED, cursor.getCurrentTarget(), FlickDirection.UNDETERMINED, false));
				
			}
			
//			System.out.println("Vel: " + vel );
		}
	}

	/* (non-Javadoc)
	 * @see org.mt4j.input.inputProcessors.componentProcessors.AbstractCursorProcessor#cursorEnded(org.mt4j.input.inputData.InputCursor, org.mt4j.input.inputData.MTFingerInputEvt)
	 */
	@Override
	public void cursorEnded(InputCursor cursor, AbstractCursorInputEvt currentEvent) {
//		logger.debug(this.getName() + " INPUT_ENDED RECIEVED - CURSOR: " + c.getId());
		if (getLockedCursors().contains(cursor)){ //Cursors was a actual gesture cursors
			//Check if we can resume the gesture with another cursor
			InputCursor[] availableCursors = getFreeComponentCursorsArray();
			if (availableCursors.length > 0 && this.canLock(getCurrentComponentCursorsArray())){ 
				InputCursor otherCursor = availableCursors[0]; 
				this.getLock(otherCursor);
			}else{
				long nowTime = currentEvent.getTimeStamp();
				long elapsedTime = nowTime - this.startTime;
//				System.out.println("Elapsed time: " + elapsedTime + " Needed time: " + flickTime);
				if (flickVelocity && elapsedTime <= flickTime){
//					System.out.println("Was a FLICK!");
					FlickDirection fd = getFlickDirection(cursor);
//					System.out.println("FlickDirection: " + fd);
					this.fireGestureEvent(new FlickEvent(this, MTGestureEvent.GESTURE_ENDED, cursor.getCurrentTarget(), fd, true));
				}else{
					this.fireGestureEvent(new FlickEvent(this, MTGestureEvent.GESTURE_ENDED, cursor.getCurrentTarget(), FlickDirection.UNDETERMINED, false));
				}
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see org.mt4j.input.inputProcessors.componentProcessors.AbstractCursorProcessor#cursorLocked(org.mt4j.input.inputData.InputCursor, org.mt4j.input.inputProcessors.IInputProcessor)
	 */
	@Override
	public void cursorLocked(InputCursor cursor, IInputProcessor lockingprocessor) {
//		if (lockingprocessor instanceof AbstractComponentProcessor){
//			logger.debug(this.getName() + " Recieved cursor LOCKED by (" + ((AbstractComponentProcessor)lockingprocessor).getName()  + ") - cursors ID: " + c.getId());
//		}else{
//			logger.debug(this.getName() + " Recieved cursor LOCKED by higher priority signal - cursors ID: " + cursor.getId());
//		}
		
		this.flickVelocity = false;
		this.fireGestureEvent(new FlickEvent(this, MTGestureEvent.GESTURE_CANCELED, cursor.getCurrentTarget(), FlickDirection.UNDETERMINED, false));
//		logger.debug(this.getName() + " cursors:" + c.getId() + " CURSOR LOCKED. Was an locked cursor in this gesture!");
	}

	/* (non-Javadoc)
	 * @see org.mt4j.input.inputProcessors.componentProcessors.AbstractCursorProcessor#cursorUnlocked(org.mt4j.input.inputData.InputCursor)
	 */
	@Override
	public void cursorUnlocked(InputCursor cursor) {
		//not resumable
	}
	

	/**
	 * Gets the flick direction.
	 *
	 * @param cursor the cursor
	 * @return the flick direction
	 */
	public FlickDirection getFlickDirection(InputCursor cursor){
		Vector3D vel = cursor.getVelocityVector(150);
		float angle = 370;
		FlickDirection flickDirection = FlickDirection.UNDETERMINED;
		for (Vector3D  direction : directions) {
			float newAngle = vel.angleBetween(direction);
			if (newAngle < angle){
				angle = newAngle;
				if (direction.equalsVector(west)) {
					flickDirection = FlickDirection.WEST;
				} 
				else if (direction.equalsVector(north_west)) {
					flickDirection = FlickDirection.NORTH_WEST;
				}
				else if (direction.equalsVector(north)) {
					flickDirection = FlickDirection.NORTH;
				}
				else if (direction.equalsVector(north_east)) {
					flickDirection = FlickDirection.NORTH_EAST;
				}
				else if (direction.equalsVector(east)) {
					flickDirection = FlickDirection.EAST;
				}
				else if (direction.equalsVector(south_east)) {
					flickDirection = FlickDirection.SOUTH_EAST;
				}
				else if (direction.equalsVector(south)) {
					flickDirection = FlickDirection.SOUTH;
				}
				else if (direction.equalsVector(south_west)) {
					flickDirection = FlickDirection.SOUTH_WEST;
				}
			}
		}
		return flickDirection;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor#getName()
	 */
	@Override
	public String getName() {
		return "Flick Processor";
	}

	public int getVelocityThreshHold() {
		return velThreshHold;
	}

	public void setVelocityThreshHold(int velThreshHold) {
		this.velThreshHold = velThreshHold;
	}

	public int getFlickTime() {
		return flickTime;
	}

	public void setFlickTime(int flickTime) {
		this.flickTime = flickTime;
	}
	
	

}
