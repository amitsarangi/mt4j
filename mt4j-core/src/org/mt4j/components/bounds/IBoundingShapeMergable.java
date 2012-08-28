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
package org.mt4j.components.bounds;

import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.util.math.Matrix;

/**
 * The Interface IBoundingShapeMergable.
 */
public interface IBoundingShapeMergable extends IBoundingShape {

//	/*
	/**
 * Merge.
 *
 * @param shape the shape
 * @return the i bounding shape mergable
 */
public IBoundingShapeMergable merge(IBoundingShape shape);
	
	/**
	 * Transform.
	 *
	 * @param transformMatrix the transform matrix
	 * @return the i bounding shape
	 */
	public IBoundingShape transform(Matrix transformMatrix);
	
	/**
	 * Gets the peer component.
	 *
	 * @return the peer component
	 */
	public MTComponent getPeerComponent();
	
	/**
	 * Sets the peer component.
	 *
	 * @param peerComponent the new peer component
	 */
	public void setPeerComponent(MTComponent peerComponent);
	
	/**
	 * Gets the bounds transformed.
	 *
	 * @param transformSpace the transform space
	 * @return the bounds transformed
	 */
	public IBoundingShapeMergable getBoundsTransformed(TransformSpace transformSpace);
//	*/
}
