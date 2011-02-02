package org.mt4j.util;

import javax.media.opengl.glu.GLU;

import org.mt4j.IMTApplication;
import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL11Plus;
import org.mt4j.util.opengl.GL20;
import org.mt4j.util.opengl.IGLU;
import org.mt4j.util.opengl.JoglGLU;

import processing.core.PGraphics;
import processing.core.PGraphics3D;
import processing.core.PMatrix;
import processing.core.PMatrix3D;
import processing.opengl.PGraphicsOpenGL;

public class DesktopGraphicsUtil implements IGraphicsUtil {
	private final IMTApplication app;
	private final JoglGLU joglGLU;

	public DesktopGraphicsUtil(IMTApplication app){
		this.app = app;
		this.joglGLU = new JoglGLU(new GLU());
	}

	public PGraphics getPGraphics(){
		return app.getPGraphics();
	}
	
	public PMatrix3D getModelView() {
		return ((PGraphics3D)app.getPGraphics()).modelview;
	}
	
	public PMatrix3D getModelViewInv() {
		return ((PGraphics3D)app.getPGraphics()).modelviewInv;
	}
	
	public PMatrix3D getCamera() {
		return ((PGraphics3D)app.getPGraphics()).camera;
	}
	
	public float getCameraFOV() {
		return ((PGraphics3D)app.getPGraphics()).cameraFOV;
	}

	public float getCameraAspect() {
		return ((PGraphics3D)app.getPGraphics()).cameraAspect;
	}

	public float getCameraNear() {
		return ((PGraphics3D)app.getPGraphics()).cameraNear;
	}

	public float getCameraFar() {
		return ((PGraphics3D)app.getPGraphics()).cameraFar;
	}

//	public GL10 getGL() {
//		return ((PGraphicsOpenGL)app.getPGraphics()).gl;
//	}
//
//	public GL10 beginGL() {
//		((PGraphicsOpenGL)app.getPGraphics()).beginGL();
//		return app.getGLCommon();
//	}
//    
//    public void endGL(){
//    	((PGraphicsOpenGL)app.getPGraphics()).endGL();
//    }
	
	public GL10 getGL() {
		return app.getGL10(); //FIXME DOES THE CAST TO kronos.GL10 work!??
	}
	
	public GL11 getGL11() {
		return app.getGL11(); 
	}

	public GL20 getGL20() {
		return app.getGL20(); 
	}

	public GL10 beginGL() {
		((PGraphicsOpenGL)app.getPGraphics()).beginGL();
//		app.beginGL();
		return app.getGL10();
	}
    
    public void endGL(){
    	((PGraphicsOpenGL)app.getPGraphics()).endGL();
//    	app.endGL();
    }
    
    
    public int getPlatform() {
		return IGraphicsUtil.DESKTOP;
	}

	public GL11Plus getGL11Plus() {
		return app.getGL11Plus();
	}

	@Override
	public IGLU getGLU() {
		return this.joglGLU;
	}

	@Override
	public PMatrix getProjection() {
		return ((PGraphicsOpenGL)app.getPGraphics()).projection;
	}

	

}
