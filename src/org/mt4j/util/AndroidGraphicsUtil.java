package org.mt4j.util;

import org.mt4j.IMTApplication;
import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL11Plus;
import org.mt4j.util.opengl.GL20;

import processing.core.PGraphics;
import processing.core.PGraphicsAndroid3D;
import processing.core.PMatrix3D;

public class AndroidGraphicsUtil implements IGraphicsUtil {
	private final IMTApplication app;

	public AndroidGraphicsUtil(IMTApplication app){
		this.app = app;
	}

	public PGraphics getPGraphics(){
		return app.getPGraphics();
	}
	
	public PMatrix3D getModelView() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).modelview;
	}
	
	public PMatrix3D getModelViewInv() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).modelviewInv;
	}
	
	@Override
	public float getCameraFOV() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraFOV;
	}

	@Override
	public float getCameraAspect() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraAspect;
	}

	@Override
	public float getCameraNear() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraNear;
	}

	@Override
	public float getCameraFar() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).cameraFar;
	}

	public GL10 getGL() {
		return app.getGL10(); 
	}
	
	@Override
	public GL11 getGL11() {
		return app.getGL11(); 
	}

	@Override
	public GL20 getGL20() {
		return app.getGL20(); 
	}

	public GL10 beginGL() {
//		((PGraphicsAndroid3D)app.getPGraphics()).beginGL();
		app.beginGL();
		return app.getGL10();
	}
    
    public void endGL(){
//    	((PGraphicsAndroid3D)app.getPGraphics()).endGL();
    	app.endGL();
    }

	@Override
	public int getPlatform() {
		return IGraphicsUtil.ANDROID;
	}

	@Override
	public PMatrix3D getCamera() {
		return ((PGraphicsAndroid3D)app.getPGraphics()).camera;
	}

	@Override
	public GL11Plus getGL11Plus() {
		return app.getGL11Plus();
	}

	
}
