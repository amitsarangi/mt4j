
package org.mt4j.util.opengl;

import javax.media.opengl.GL;


/**
 * This class stores the parameters for a texture: target, internal format, minimization filter
 * and magnification filter.
 * Original file Copyright (c) by Andrés Colubri
 */
public class GLTextureParameters implements GLConstants 
{

	//http://gregs-blog.com/2008/01/17/opengl-texture-filter-parameters-explained/

	/**
	 * Creates an instance of GLTextureParameters, setting all the parameters to default values.
	 */
	public GLTextureParameters(){
		target = NORMAL;
		format = COLOR;

		//Texture filtering
		minFilter = LINEAR_MIPMAP_NEAREST; //Per default enable mip mapping, bi-linear filtering, scaled down textures
		magFilter = LINEAR;
//		minFilter = NEAREST_MIPMAP_NEAREST; //ugly..
//		magFilter = LINEAR_MIPMAP_LINEAR;
//		minFilter = LINEAR;
//		magFilter = LINEAR;

		//Texture wrap mode
		this.wrap_s = GL.GL_REPEAT;
		this.wrap_t = GL.GL_REPEAT;
//		this.wrap_s = GL.GL_CLAMP;
//		this.wrap_t = GL.GL_CLAMP;
//		//newer, not supported everywhere?
//		this.wrap_s = GL.GL_CLAMP_TO_EDGE;
//		this.wrap_t = GL.GL_CLAMP_TO_EDGE;

	}
	
    /** Texture target. */
    public int target;
	
    /** Texture internal format. */
    public int format;
	
    /** Texture minimization filter. */
    public int minFilter;
	
    /** Texture magnification filter. */
    public int magFilter;	
    
    public int wrap_s;
    
    public int wrap_t;
}

