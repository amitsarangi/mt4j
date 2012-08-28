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
package processing.core;

/**
 * The Class P5ProtectedAccessProxy.
 */
public class P5ProtectedAccessProxy {
	
	/** The app. */
	private PApplet app;
	
	/** The model view. */
	private PMatrix3D modelView;
	
	/** The a3d. */
	private PGraphicsAndroid3D a3d;
	
	/**
	 * Instantiates a new p5 protected access proxy.
	 *
	 * @param app the app
	 */
	public P5ProtectedAccessProxy(PApplet app){
		this.app = app;
		
		modelView = new PMatrix3D();
		
		a3d = (PGraphicsAndroid3D)app.g;
	}
	
	//TODO use in graphicsutil -> else we dont get the current matrix (whis is in glModelview)
	/**
	 * Gets the model view.
	 *
	 * @return the model view
	 */
	public PMatrix3D getModelView(){
		if (!a3d.modelviewUpdated) {
		      a3d.getModelviewMatrix();
		    }
//		a3d.copyGLArrayToPMatrix(a3d.glmodelview, modelView);
		return a3d.modelview;
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
	public void setModelView(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    ){
		
		a3d.modelview.set(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
		
//		a3d.glmodelview 
		
		a3d.copyPMatrixToGLArray(a3d.modelview, a3d.glmodelview);
		
		//FIXME DO SOMEWHER ELSE?! but neccessary !
		a3d.gl.glLoadMatrixf(a3d.glmodelview, 0);
		    if (a3d.usingGLMatrixStack) {
		    	a3d.modelviewStack.set(a3d.glmodelview);
		    }
		a3d.modelviewUpdated = true; //do?
		
//		a3d.camera; //wann wird die benutzt?
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
	public void setModelViewInv(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    ){
		a3d.modelviewInv.set(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
		a3d.copyPMatrixToGLArray(a3d.modelviewInv, a3d.glmodelviewInv);
	}
			
	
	//modelviewUpdated indicates whether the PMatrix3D modelview is coherent with the glModelView which is used internally

	//TODO modelViewInf, cameraInv
	
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
    ){
		
		a3d.camera.set(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
		
		//set camera to modelview and cameraInv to modelviewinv
		a3d.copyPMatrixToGLArray(a3d.camera, a3d.pcamera);
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
	public void setCameraInv(
			float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33
    ){
		
		a3d.cameraInv.set(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
		
		//set camera to modelview and cameraInv to modelviewinv
		
		a3d.copyPMatrixToGLArray(a3d.cameraInv, a3d.pcameraInv);
	}
	
	
	/**
	 * Gets the projection.
	 *
	 * @return the projection
	 */
	public PMatrix3D getProjection(){
		if (!a3d.projectionUpdated) {
		      a3d.getProjectionMatrix();
		    }
//		a3d.copyGLArrayToPMatrix(a3d.glmodelview, modelView);
		return a3d.projection;
	}
			
}
