package org.mt4j.util;

import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL20;

import processing.core.PGraphics;
import processing.core.PMatrix3D;

public class GraphicsUtil {
	
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
	

}
