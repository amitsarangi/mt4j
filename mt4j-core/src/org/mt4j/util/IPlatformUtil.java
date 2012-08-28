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
package org.mt4j.util;

import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL11Plus;
import org.mt4j.util.opengl.GL20;
import org.mt4j.util.opengl.IGLU;

import processing.core.PGraphics;
import processing.core.PMatrix3D;

/**
 * The Interface IPlatformUtil.
 */
public interface IPlatformUtil {
	
/**
 * Gets the p graphics.
 *
 * @return the p graphics
 */
public PGraphics getPGraphics();
	
	/** The Constant DESKTOP. */
	public static final int DESKTOP = 0;
	
	/** The Constant ANDROID. */
	public static final int ANDROID = 1;
	
	/**
	 * Gets the model view.
	 *
	 * @return the model view
	 */
	public PMatrix3D getModelView();
	
	/**
	 * Gets the model view inv.
	 *
	 * @return the model view inv
	 */
	public PMatrix3D getModelViewInv();
	
	/**
	 * Gets the camera.
	 *
	 * @return the camera
	 */
	public PMatrix3D getCamera();
	
	/**
	 * Gets the camera fov.
	 *
	 * @return the camera fov
	 */
	public float getCameraFOV();
	
	/**
	 * Gets the camera aspect.
	 *
	 * @return the camera aspect
	 */
	public float getCameraAspect();
	
	/**
	 * Gets the camera near.
	 *
	 * @return the camera near
	 */
	public float getCameraNear();
	
	/**
	 * Gets the camera far.
	 *
	 * @return the camera far
	 */
	public float getCameraFar();
	
	/**
	 * Gets the gL.
	 *
	 * @return the gL
	 */
	public GL10 getGL();

	/**
	 * Begin gl.
	 *
	 * @return the g l10
	 */
	public GL10 beginGL();
    
    /**
     * End gl.
     */
    public void endGL();

	/**
	 * Gets the g l11.
	 *
	 * @return the g l11
	 */
	public GL11 getGL11();

	/**
	 * Gets the g l20.
	 *
	 * @return the g l20
	 */
	public GL20 getGL20();
	
	/**
	 * Gets the platform.
	 *
	 * @return the platform
	 */
	public int getPlatform();

	/**
	 * Gets the g l11 plus.
	 *
	 * @return the g l11 plus
	 */
	public GL11Plus getGL11Plus();

	/**
	 * Gets the gLU.
	 *
	 * @return the gLU
	 */
	public IGLU getGLU();

	/**
	 * Gets the projection.
	 *
	 * @return the projection
	 */
	public PMatrix3D getProjection();

	/**
	 * Checks if is big endian.
	 *
	 * @return true, if is big endian
	 */
	public boolean isBigEndian();

	/**
	 * Checks if is nPOT texture supported.
	 *
	 * @return true, if is nPOT texture supported
	 */
	public boolean isNPOTTextureSupported();
	
	
	
	
	/**
	 * Sets the model view.
	 *
	 * @param m00 the m00
	 * @param m01 the m01
	 * @param m02 the m02
	 * @param m03 the m03
	 * @param m10 the m10
	 * @param m11 the m11
	 * @param m12 the m12
	 * @param m13 the m13
	 * @param m20 the m20
	 * @param m21 the m21
	 * @param m22 the m22
	 * @param m23 the m23
	 * @param m30 the m30
	 * @param m31 the m31
	 * @param m32 the m32
	 * @param m33 the m33
	 */
	public void setModelView(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    );
	
	/**
	 * Sets the model view inv.
	 *
	 * @param m00 the m00
	 * @param m01 the m01
	 * @param m02 the m02
	 * @param m03 the m03
	 * @param m10 the m10
	 * @param m11 the m11
	 * @param m12 the m12
	 * @param m13 the m13
	 * @param m20 the m20
	 * @param m21 the m21
	 * @param m22 the m22
	 * @param m23 the m23
	 * @param m30 the m30
	 * @param m31 the m31
	 * @param m32 the m32
	 * @param m33 the m33
	 */
	public void setModelViewInv(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    );
			
	
	/**
	 * Sets the camera.
	 *
	 * @param m00 the m00
	 * @param m01 the m01
	 * @param m02 the m02
	 * @param m03 the m03
	 * @param m10 the m10
	 * @param m11 the m11
	 * @param m12 the m12
	 * @param m13 the m13
	 * @param m20 the m20
	 * @param m21 the m21
	 * @param m22 the m22
	 * @param m23 the m23
	 * @param m30 the m30
	 * @param m31 the m31
	 * @param m32 the m32
	 * @param m33 the m33
	 */
	public void setCamera(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    );
	
	/**
	 * Sets the camera inv.
	 *
	 * @param m00 the m00
	 * @param m01 the m01
	 * @param m02 the m02
	 * @param m03 the m03
	 * @param m10 the m10
	 * @param m11 the m11
	 * @param m12 the m12
	 * @param m13 the m13
	 * @param m20 the m20
	 * @param m21 the m21
	 * @param m22 the m22
	 * @param m23 the m23
	 * @param m30 the m30
	 * @param m31 the m31
	 * @param m32 the m32
	 * @param m33 the m33
	 */
	public void setCameraInv(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    );

}
