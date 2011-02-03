package org.mt4j.util;

import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL11Plus;
import org.mt4j.util.opengl.GL20;
import org.mt4j.util.opengl.IGLU;

import processing.core.PGraphics;
import processing.core.PMatrix;
import processing.core.PMatrix3D;

public class GraphicsUtil {
	//TODO dont make static? maybe we have different swing windows with different gl contexts?
	
	private static IGraphicsUtil graphicsUtil;

	public static PGraphics getPGraphics() {
		return graphicsUtil.getPGraphics();
	}

	public static PMatrix3D getModelView() {
		return graphicsUtil.getModelView();
	}

	public static PMatrix3D getModelViewInv() {
		return graphicsUtil.getModelViewInv();
	}
	
	public static PMatrix3D getCamera() {
		return graphicsUtil.getCamera();
	}
	
	public static float getCameraFOV() {
		return graphicsUtil.getCameraFOV();
	}

	public static float getCameraAspect() {
		return graphicsUtil.getCameraAspect();
	}

	public static float getCameraNear() {
		return graphicsUtil.getCameraNear();
	}

	public static float getCameraFar() {
		return graphicsUtil.getCameraFar();
	}
	
	public static GL10 getGL(){
		return graphicsUtil.getGL();
	}
	
	public static GL11 getGL11(){
		return graphicsUtil.getGL11();
	}
	
	public static GL20 getGL20(){
		return graphicsUtil.getGL20();
	}

	public static GL10 beginGL() {
		return graphicsUtil.beginGL();
	}

	public static void endGL() {
		graphicsUtil.endGL();
	}
	
	public static void setGraphicsUtilProvider(IGraphicsUtil graphicsUtilitiy){
		graphicsUtil = graphicsUtilitiy;
	}
	
	public static boolean isDesktop(){
		return graphicsUtil.getPlatform() == IGraphicsUtil.DESKTOP;
	}
	
	public static boolean isAndroid(){
		return graphicsUtil.getPlatform() == IGraphicsUtil.ANDROID;
	}

		
	public static GL11Plus getGL11Plus() {
		return graphicsUtil.getGL11Plus();
	}
	
	public static IGLU getGLU(){
		return graphicsUtil.getGLU();
	}

	public static PMatrix getProjection() {
		return graphicsUtil.getProjection();
	}
	
	public static boolean isBigEndian(){
		return graphicsUtil.isBigEndian();
	}



}
