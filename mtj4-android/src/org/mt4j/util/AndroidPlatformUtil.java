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

import org.mt4j.IMTApplication;
import org.mt4j.util.opengl.AndroidGLU;
import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL11Plus;
import org.mt4j.util.opengl.GL20;
import org.mt4j.util.opengl.IGLU;

import processing.core.P5ProtectedAccessProxy;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PGraphicsAndroid3D;
import processing.core.PMatrix3D;

/**
 * The Class AndroidPlatformUtil.
 */
public class AndroidPlatformUtil implements IPlatformUtil {
	
	/** The app. */
	private final IMTApplication app;
	
	/** The android glu. */
	private AndroidGLU androidGLU;
	
	/** The p5 protected access proxy. */
	private P5ProtectedAccessProxy p5ProtectedAccessProxy;

	/**
	 * Instantiates a new android platform util.
	 *
	 * @param app the app
	 */
	public AndroidPlatformUtil(IMTApplication app){
		this.app = app;
		this.androidGLU = new AndroidGLU();
		
		this.p5ProtectedAccessProxy = new P5ProtectedAccessProxy((PApplet) app); //FIXME use PApplet instead of IMTApplication everywhere!?
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getPGraphics()
	 */
	public PGraphics getPGraphics(){
		return app.getPGraphics();
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getModelView()
	 */
	public PMatrix3D getModelView() {
//		return ((PGraphicsAndroid3D)app.getPGraphics()).modelview;
		return this.p5ProtectedAccessProxy.getModelView();
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getModelViewInv()
	 */
	public PMatrix3D getModelViewInv() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).modelviewInv;
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getCameraFOV()
	 */
	public float getCameraFOV() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraFOV;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getCameraAspect()
	 */
	public float getCameraAspect() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraAspect;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getCameraNear()
	 */
	public float getCameraNear() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraNear;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getCameraFar()
	 */
	public float getCameraFar() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraFar;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getGL()
	 */
	public GL10 getGL() {
		return app.getGL10(); 
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getGL11()
	 */
	public GL11 getGL11() {
		return app.getGL11(); 
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getGL20()
	 */
	public GL20 getGL20() {
		return app.getGL20(); 
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#beginGL()
	 */
	public GL10 beginGL() {
		((PGraphicsAndroid3D)app.getPGraphics()).beginGL();
//		app.beginGL();
		return app.getGL10();
	}
    
    /* (non-Javadoc)
     * @see org.mt4j.util.IPlatformUtil#endGL()
     */
    public void endGL(){
    	((PGraphicsAndroid3D)app.getPGraphics()).endGL();
//    	app.endGL();
    }

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getPlatform()
	 */
	public int getPlatform() {
		return IPlatformUtil.ANDROID;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getCamera()
	 */
	public PMatrix3D getCamera() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).camera;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getGL11Plus()
	 */
	public GL11Plus getGL11Plus() {
		return app.getGL11Plus();
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getGLU()
	 */
	@Override
	public IGLU getGLU() {
		return androidGLU;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#getProjection()
	 */
	@Override
	public PMatrix3D getProjection() {
//		return ((PGraphicsAndroid3D)app.getPGraphics()).projection;
		return p5ProtectedAccessProxy.getProjection();
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#isBigEndian()
	 */
	@Override
	public boolean isBigEndian() {
		return PGraphicsAndroid3D.BIG_ENDIAN;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#isNPOTTextureSupported()
	 */
	@Override
	public boolean isNPOTTextureSupported() {
		return false;
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#setModelView(float, float, float, float, float, float, float, float, float, float, float, float, float, float, float, float)
	 */
	public void setModelView(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    ){
		this.p5ProtectedAccessProxy.setModelView(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#setModelViewInv(float, float, float, float, float, float, float, float, float, float, float, float, float, float, float, float)
	 */
	public void setModelViewInv(float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    ){
		this.p5ProtectedAccessProxy.setModelViewInv(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}
			
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#setCamera(float, float, float, float, float, float, float, float, float, float, float, float, float, float, float, float)
	 */
	public void setCamera(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    ){
		this.p5ProtectedAccessProxy.setCamera(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.IPlatformUtil#setCameraInv(float, float, float, float, float, float, float, float, float, float, float, float, float, float, float, float)
	 */
	public void setCameraInv(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    ){
		this.p5ProtectedAccessProxy.setCameraInv(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}

	
}
