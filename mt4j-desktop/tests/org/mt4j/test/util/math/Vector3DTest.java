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
package org.mt4j.test.util.math;

import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.math.Vector3D;

import junit.framework.TestCase;

public class Vector3DTest extends TestCase {
	
	public void testVector3D(){
		Vector3D a = new Vector3D(1,2,3);
		Vector3D b = new Vector3D(3,2,1);
		Vector3D c = new Vector3D(1,0,0);
		Vector3D d = new Vector3D(0,1,0);
		
		assertTrue(new Vector3D(5,6,7).equalsVector(new Vector3D(5,6,7)));
		assertTrue(new Vector3D(5,6,7).equalsVectorWithTolerance(new Vector3D(4.8f, 6,6.9f), 0.2f));
		
		assertTrue("Vector3D getAdded test" , a.getAdded(b).equalsVector(new Vector3D(4,4,4)));
		assertTrue("Vector3D getAdded test" , a.getSubtracted(b).equalsVector(new Vector3D(-2,0,2)));
		assertTrue("Vector3D addLocal(), subtractLocal() test" , new Vector3D(a).subtractLocal(b).addLocal(b).equalsVector(a));
		assertTrue(a.equalsVectorWithTolerance(new Vector3D(0.8f, 2,2.9f), 0.2f));
		
		System.out.println(c.angleBetween(d) * ToolsMath.RAD_TO_DEG);
		assertTrue(c.angleBetween(d) * ToolsMath.RAD_TO_DEG == 90 );
		
		Vector3D e = new Vector3D(c);
		e.rotateZ(90 * ToolsMath.DEG_TO_RAD);
		e.rotateZ(-90 * ToolsMath.DEG_TO_RAD);
		assertTrue(e.equalsVector(c));
	}

}
