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
package org.mt4j.input.inputSources;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.mt4j.AbstractMTApplication;
import org.mt4j.input.inputData.ActiveCursorPool;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputData.MTFingerInputEvt;
import org.mt4j.util.MT4jSettings;

import com.alderstone.multitouch.mac.touchpad.Finger;
import com.alderstone.multitouch.mac.touchpad.FingerState;
import com.alderstone.multitouch.mac.touchpad.TouchpadObservable;

/**
 * Input source for Mac OS X Trackpads.
 * Uses the library from http://kenai.com/projects/macmultitouch
 * 
 * @author Florian Thalmann
 */
public class MacTrackpadSource extends AbstractInputSource implements Observer {

	/** The tpo. */
	private TouchpadObservable tpo;
	
	/** The window height. */
	private int windowWidth, windowHeight;
	
	/** The finger id to cursor id. */
	private Map<Integer, Long> fingerIdToCursorId;
	
	/**
	 * Instantiates a new mac trackpad source.
	 *
	 * @param mtApp the mt app
	 */
	public MacTrackpadSource(AbstractMTApplication mtApp) {
		super(mtApp);
		this.tpo = TouchpadObservable.getInstance();
		this.tpo.addObserver(this);
		
		this.windowWidth = MT4jSettings.getInstance().getWindowWidth();
		this.windowHeight = MT4jSettings.getInstance().getWindowHeight();
		
		this.fingerIdToCursorId = new HashMap<Integer, Long>();
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable obj, Object arg) {
		Finger finger = (Finger) arg;
		int fingerID = finger.getID();
		
		ActiveCursorPool cursorPool = ActiveCursorPool.getInstance();
		int inputID;
		Long cursorID = fingerIdToCursorId.get(fingerID);
		InputCursor cursor = (cursorID != null)? cursorPool.getActiveCursorByID(cursorID) : null;
		
		if (finger.getState() == FingerState.PRESSED) {
			if (cursor == null) { //new finger
				cursor = new InputCursor();
				fingerIdToCursorId.put(fingerID, cursor.getId());
				cursorPool.putActiveCursor(cursor.getId(), cursor);
				inputID = MTFingerInputEvt.INPUT_STARTED;
			} else { //updated finger
				inputID = MTFingerInputEvt.INPUT_UPDATED;
			}
		} else { //removed finger
			if (cursorID != null){
				cursorPool.removeCursor(cursorID);
			}
			fingerIdToCursorId.remove(fingerID);
			inputID = MTFingerInputEvt.INPUT_ENDED;
		}
		
		int absoluteX = Math.round(finger.getX()*this.windowWidth);
		int absoluteY = Math.round((1-finger.getY())*this.windowHeight);
		this.enqueueInputEvent(new MTFingerInputEvt(this, absoluteX, absoluteY, inputID, cursor));
	}

	/**
	 * Update.
	 */
	public void update() {}

}
