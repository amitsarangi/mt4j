package org.mt4j.util.opengl;

import java.nio.Buffer;

public interface GL11Plus extends GL11 {
	public static final int GL_LINE_STIPPLE = 0x0B24;
	
	//Added for backwards compatibility
	public void glCallList(int id);

	public void glCallLists(int arg0, int arg1, Buffer arg2);

	public int glGenLists(int id);
	
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
	
	public void glNewList(int id, int mode);

	public void glEndList();
	
	public void glDeleteLists(int list, int range);

}
