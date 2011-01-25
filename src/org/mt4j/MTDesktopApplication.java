package org.mt4j;

import javax.media.opengl.GL;

import org.mt4j.util.opengl.JoglGL10;
import org.mt4j.util.opengl.JoglGL11;
import org.mt4j.util.opengl.JoglGL20Plus;

import processing.opengl.PGraphicsOpenGL;


public abstract class MTDesktopApplication extends MTApplication {
	private static final long serialVersionUID = 1L;

	protected void loadGL(){
		//FIXME TEST!!
		String version = ((PGraphicsOpenGL)g).gl.glGetString(GL.GL_VERSION);
		logger.info("OpenGL Version: " + version);
        int major = Integer.parseInt("" + version.charAt(0));
        int minor = Integer.parseInt("" + version.charAt(2));
        
        this.gl11Supported = false;
        this.gl20Supported = false;
        if (major >= 2) {
//                JoglGL20 jogl20 = new JoglGL20(((PGraphicsOpenGL)g).gl);
        		JoglGL20Plus jogl20 = new JoglGL20Plus(((PGraphicsOpenGL)g).gl);
                iGL20 = jogl20;
                //FIXME ADDED
                iGL10  = jogl20;
                iGL11 = jogl20;
                iGL11Plus = jogl20;
                glCommon = iGL20;
                this.gl20Supported = true;
                this.gl11Supported = true;
                this.gl11PlusSupported = true;
        } else {
                if (major == 1 && minor < 5) {
                        iGL10 = new JoglGL10(((PGraphicsOpenGL)g).gl);
                } else {
                        iGL11 = new JoglGL11(((PGraphicsOpenGL)g).gl);
                        iGL10 = iGL11;
                        this.gl11Supported = true;
                }
                glCommon = iGL10;
        }
	}
	
//	public GL getGL(){
//		return ((PGraphicsOpenGL)g).gl;
//	}
//	
//	public GL beginGL(){
//		return ((PGraphicsOpenGL)g).beginGL();
//	}
//	
//	public void endGL(){
//		((PGraphicsOpenGL)g).endGL();
//	}

}
