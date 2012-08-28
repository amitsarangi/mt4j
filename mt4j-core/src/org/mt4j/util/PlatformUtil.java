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
 * The Class PlatformUtil.
 */
public class PlatformUtil {
	//TODO dont make static? maybe we have different swing windows with different gl contexts?
	
	/** The graphics util. */
	private static IPlatformUtil graphicsUtil;

	/**
	 * Gets the p graphics.
	 *
	 * @return the p graphics
	 */
	public static PGraphics getPGraphics() {
		return graphicsUtil.getPGraphics();
	}

	/**
	 * Gets the model view.
	 *
	 * @return the model view
	 */
	public static PMatrix3D getModelView() {
		return graphicsUtil.getModelView();
	}

	/**
	 * Gets the model view inv.
	 *
	 * @return the model view inv
	 */
	public static PMatrix3D getModelViewInv() {
		return graphicsUtil.getModelViewInv();
	}
	
	/**
	 * Gets the camera.
	 *
	 * @return the camera
	 */
	public static PMatrix3D getCamera() {
		return graphicsUtil.getCamera();
	}
	
	/**
	 * Gets the camera fov.
	 *
	 * @return the camera fov
	 */
	public static float getCameraFOV() {
		return graphicsUtil.getCameraFOV();
	}

	/**
	 * Gets the camera aspect.
	 *
	 * @return the camera aspect
	 */
	public static float getCameraAspect() {
		return graphicsUtil.getCameraAspect();
	}

	/**
	 * Gets the camera near.
	 *
	 * @return the camera near
	 */
	public static float getCameraNear() {
		return graphicsUtil.getCameraNear();
	}

	/**
	 * Gets the camera far.
	 *
	 * @return the camera far
	 */
	public static float getCameraFar() {
		return graphicsUtil.getCameraFar();
	}
	
	/**
	 * Gets the gL.
	 *
	 * @return the gL
	 */
	public static GL10 getGL(){
		return graphicsUtil.getGL();
	}
	
	/**
	 * Gets the g l11.
	 *
	 * @return the g l11
	 */
	public static GL11 getGL11(){
		return graphicsUtil.getGL11();
	}
	
	/**
	 * Gets the g l20.
	 *
	 * @return the g l20
	 */
	public static GL20 getGL20(){
		return graphicsUtil.getGL20();
	}

	/**
	 * Begin gl.
	 *
	 * @return the g l10
	 */
	public static GL10 beginGL() {
		return graphicsUtil.beginGL();
	}

	/**
	 * End gl.
	 */
	public static void endGL() {
		graphicsUtil.endGL();
	}
	
	/**
	 * Sets the graphics util provider.
	 *
	 * @param graphicsUtilitiy the new graphics util provider
	 */
	public static void setGraphicsUtilProvider(IPlatformUtil graphicsUtilitiy){
		graphicsUtil = graphicsUtilitiy;
	}
	
	/**
	 * Checks if is desktop.
	 *
	 * @return true, if is desktop
	 */
	public static boolean isDesktop(){
		return graphicsUtil.getPlatform() == IPlatformUtil.DESKTOP;
	}
	
	/**
	 * Checks if is android.
	 *
	 * @return true, if is android
	 */
	public static boolean isAndroid(){
		return graphicsUtil.getPlatform() == IPlatformUtil.ANDROID;
	}
	
	/**
	 * Checks if is nPOT texture supported.
	 *
	 * @return true, if is nPOT texture supported
	 */
	public static boolean isNPOTTextureSupported(){
		return graphicsUtil.isNPOTTextureSupported();
	}

		
	/**
	 * Gets the g l11 plus.
	 *
	 * @return the g l11 plus
	 */
	public static GL11Plus getGL11Plus() {
		return graphicsUtil.getGL11Plus();
	}
	
	/**
	 * Gets the gLU.
	 *
	 * @return the gLU
	 */
	public static IGLU getGLU(){
		return graphicsUtil.getGLU();
	}

	/**
	 * Gets the projection.
	 *
	 * @return the projection
	 */
	public static PMatrix3D getProjection() {
		return graphicsUtil.getProjection();
	}
	
	/**
	 * Checks if is big endian.
	 *
	 * @return true, if is big endian
	 */
	public static boolean isBigEndian(){
		return graphicsUtil.isBigEndian();
	}

	
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
	public static void setModelView(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
     ){
		graphicsUtil.setModelView(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}
	
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
	public static void setModelViewInv(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
	){
		graphicsUtil.setModelViewInv(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}
			
	
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
	public static void setCamera(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
	){
		graphicsUtil.setCamera(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}
	
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
	public static void setCameraInv(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
	){
		graphicsUtil.setCameraInv(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}



}
