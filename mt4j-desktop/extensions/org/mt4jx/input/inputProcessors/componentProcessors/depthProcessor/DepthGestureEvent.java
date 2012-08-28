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
package org.mt4jx.input.inputProcessors.componentProcessors.depthProcessor;

import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.IInputProcessor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.util.math.Vector3D;

public class DepthGestureEvent extends MTGestureEvent {

	private InputCursor dragCursor;
	private InputCursor depthCursor;
	
	private Vector3D from;
	private Vector3D to;
		
	private Vector3D translationVect;
	
	public DepthGestureEvent(IInputProcessor source,int id,IMTComponent3D targetComponent,InputCursor depthCursor,Vector3D translationVect)
	{
		super(source,id,targetComponent);	
	
		this.depthCursor = depthCursor;
			
		this.depthCursor = depthCursor;
		
		this.translationVect = translationVect;
	}




	public InputCursor getDepthCursor() {
		return depthCursor;
	}
	
	public Vector3D getFrom() {
		return from;
	}
	
	public Vector3D getTo() {
		return to;
	}
	
	public Vector3D getTranslationVect() {
		return translationVect;
	}
	
	
}
