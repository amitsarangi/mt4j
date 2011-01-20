package org.mt4j.util;

import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL20;

import processing.core.PGraphics;
import processing.core.PMatrix3D;

public interface IGraphicsUtil {
	
public PGraphics getPGraphics();
	public static final int DESKTOP = 0;
	public static final int ANDROID = 1;
	
	public PMatrix3D getModelView();
	
	public PMatrix3D getModelViewInv();
	
	public PMatrix3D getCamera();
	
	public float getCameraFOV();
	
	public float getCameraAspect();
	
	public float getCameraNear();
	
	public float getCameraFar();
	
	public GL10 getGL();

	public GL10 beginGL();
    
    public void endGL();

	public GL11 getGL11();

	public GL20 getGL20();
	
	public int getPlatform();

}
