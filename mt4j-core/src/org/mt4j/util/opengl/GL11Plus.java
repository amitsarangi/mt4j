package org.mt4j.util.opengl;

import java.nio.Buffer;

public interface GL11Plus  extends GL11, GL20 {
	//Added for backwards compatibility
	public static final int GL_LINE_STIPPLE = 0x0B24;
	public static final int GL_COMPILE = 0x1300;
	public static final int GL_COMPILE_AND_EXECUTE = 4865;
	public static final int GL_QUADS = 0x0007; 
	
	public int glGenLists(int id);

	public void glNewList(int id, int mode);
	
	public void glCallList(int id);

	public void glCallLists(int arg0, int arg1, Buffer arg2);

	public void glEndList();
	
	public void glDeleteLists(int list, int range);
	

	public void glPushAttrib(int att);
	
	public void glPopAttrib();
	
	
	public void glLineStipple(int t, short stipple);

	public void glTexImage1D(
			int target, 
			int level, 
			int internalFormat, 
			int width, 
			int border, 
			int format, 
			int type, 
			Buffer data);

	public void glTexSubImage1D(
			int target, 
			int level, 
			int xoffset,
			int width, 
			int format, 
			int type, 
			Buffer pixels);
	
	public void glGetTexImage(
			int  	target, 
			int  	level, 
			int  	format, 
			int  	type, 
			Buffer	img);
	

	public void glColorMaterial(int face, int mode);
	
	
	public boolean isExtensionAvailable(String name);
	

	public void setSwapInterval(int interval);

}
