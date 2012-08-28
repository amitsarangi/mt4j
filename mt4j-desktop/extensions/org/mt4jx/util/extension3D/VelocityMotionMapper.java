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
package org.mt4jx.util.extension3D;



public class VelocityMotionMapper implements MotionMapper {

	private float velocity = 0.0f;
	private long timeStart=0,timeStop=0;
	private long timeFirstStart = 0;
	private float lengthStart=0.0f,lengthStop=0.0f;
	private static int counter = 0;
	private float currentLength = 0.0f;
	private int velocityFactor = 0;
	
	public VelocityMotionMapper(int velocityFactor)
	{
		this.velocityFactor = velocityFactor;
	}
	
	public float calcCurrentValue() {
		
		long currentTime = System.currentTimeMillis();
		
		return velocity*velocityFactor*(currentTime-timeFirstStart);
		
	}

	public void setLengthRange(float min, float max) {
				
	}

	public void updateCurrentLength(float currentLength) {
		
		//if velocity isnt calculated til now, take the values for start and end
		//v = delta x / delta t
		if(timeStart==0)
		{
			timeStart = System.currentTimeMillis();
			if(timeFirstStart==0)
			{
				timeFirstStart = timeStart;
			}
			lengthStart = currentLength;
		}else
		{
			timeStop = System.currentTimeMillis();
			if(timeStop!=timeStart)
			{
				lengthStop = currentLength;
				calcVelocity();		
			}				
		}
		
		this.currentLength = currentLength;
		
	}
	
	private void reset()
	{
		timeStart = 0;
		timeStop = 0;
		lengthStart = 0;
		lengthStop = 0;
	}
	
	private void calcVelocity()
	{
		velocity = (lengthStop - lengthStart)/(timeStop-timeStart);		
	}

}
