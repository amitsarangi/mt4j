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
package org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor;


import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.IInputProcessor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeUtils.UnistrokeGesture;

/**
 * The Class UnistrokeEvent.
 */
public class UnistrokeEvent extends MTGestureEvent{
	
	/** The visualization. */
	private MTPolygon visualization;
	
	/** The gesture. */
	private UnistrokeGesture gesture;
	
	/** The cursor. */
	private InputCursor cursor;

	/**
	 * Instantiates a new unistroke event.
	 *
	 * @param source the source
	 * @param id the id
	 * @param targetComponent the target component
	 * @param visualization the visualization
	 * @param gesture the gesture
	 * @param inputCursor the input cursor
	 */
	public UnistrokeEvent(IInputProcessor source, int id, IMTComponent3D targetComponent, MTPolygon visualization, UnistrokeGesture gesture, InputCursor inputCursor) {
		super(source, id, targetComponent);
		this.visualization = visualization;
		this.gesture = gesture;
		this.cursor = inputCursor;
	}

	/**
	 * Gets the visualization.
	 *
	 * @return the visualization
	 */
	public MTPolygon getVisualization() {
		return this.visualization;
	}
	
	/**
	 * Gets the gesture.
	 *
	 * @return the gesture
	 */
	public UnistrokeGesture getGesture(){
		return this.gesture;
	}

	/**
	 * Gets the cursor.
	 *
	 * @return the cursor
	 */
	public InputCursor getCursor() {
		return cursor;
	}


}
