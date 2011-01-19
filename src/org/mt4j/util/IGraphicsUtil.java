package org.mt4j.util;

import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GL11;
import org.mt4j.util.opengl.GL20;

import processing.core.PGraphics;
import processing.core.PMatrix3D;

public interface IGraphicsUtil {
	
public PGraphics getPGraphics();
	
	public PMatrix3D getModelView();
	
	public PMatrix3D getModelViewInv();
	
	public GL10 getGL();

	public GL10 beginGL();
    
    public void endGL();

	public GL11 getGL11();

	public GL20 getGL20();

}
