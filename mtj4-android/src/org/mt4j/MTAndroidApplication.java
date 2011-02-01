package org.mt4j;

import org.mt4j.input.AndroidInputManager;
import org.mt4j.util.AndroidGraphicsUtil;
import org.mt4j.util.GraphicsUtil;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.animation.ani.AniAnimation;
import org.mt4j.util.logging.AndroidDefaultLogger;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;
import org.mt4j.util.opengl.AndroidGL10;

import processing.core.PGraphicsAndroid3D;
import android.util.Log;



public abstract class MTAndroidApplication extends MTApplication{
	static{
//		//Initialize Loggin facilities  - IMPORTANT TO DO THIS ASAP!//////
//		MTLoggerFactory.setLoggerProvider(new AndroidDefaultLogger()); 
//		logger = MTLoggerFactory.getLogger(MTAndroidApplication.class.getName());
//		logger.setLevel(ILogger.INFO);
	}
	
	public MTAndroidApplication(){
		super();
		Log.i(this.getClass().getSimpleName(), "MTAndroidApplication() constructor CALLED");
	}
	
	
	@Override
	public void setup() {
		Log.i(this.getClass().getSimpleName(), "SETUP CALLED");
		
		//Initialize Loggin facilities  - IMPORTANT TO DO THIS ASAP!//////
		MTLoggerFactory.setLoggerProvider(new AndroidDefaultLogger()); 
		logger = MTLoggerFactory.getLogger(MTAndroidApplication.class.getName());
		logger.setLevel(ILogger.INFO);
		
		//TODO
		// Applet size - size() must be the first command in setup() method
//		this.size(MT4jSettings.getInstance().getWindowWidth(), MT4jSettings.getInstance().getWindowHeight(), A3D);
		
		//TOGGLES ALWAYS ON TOP MODE
		//this.frame.setAlwaysOnTop(true);
		
//		//INIT LOGGING /////////////
//		MTLoggerFactory.setLoggerProvider(new Log4jLogger()); //FIXME TEST
////		MTLoggerFactory.setLoggerProvider(new JavaLogger()); //FIXME TEST
//		logger = MTLoggerFactory.getLogger(MTApplication.class.getName());
//		logger.setLevel(ILogger.INFO);
//		logger.debug("-> setup called");
		////////////////////
		
		//Add default font factories /////////////
//		FontManager.getInstance().registerFontFactory(".ttf", new TTFontFactory());
		//////////////////////
		
		/////////////////////// //FIXME TEST
		GraphicsUtil.setGraphicsUtilProvider(new AndroidGraphicsUtil(this));
		///////////////////////
		
		// Save this applets rendering thread for reference
		this.renderThread = Thread.currentThread();
		
		logger.info("MT4j window dimensions: \"" + MT4jSettings.getInstance().getWindowWidth() + " X " +  MT4jSettings.getInstance().getWindowHeight() + "\"");
		
		//Set background color
		background(150);
		
		//Set the framerate
	    frameRate(MT4jSettings.getInstance().getMaxFrameRate());
	    logger.info("Maximum framerate: \"" + MT4jSettings.getInstance().getMaxFrameRate() + "\"");
	    
//	    //FIXME TODO add in settings.txt?
//	    hint(MTApplication.DISABLE_OPENGL_ERROR_REPORT);
		
		MT4jSettings.getInstance().programStartTime = System.currentTimeMillis();
		
		this.loadGL();
		
		//Create a new inputsourcePool
		if (getInputManager() == null){ //only set the default inputManager if none is set yet
			this.setInputManager(new AndroidInputManager(this, true));
		}
		
		AniAnimation.init(this); //Initialize Ani animation library
		
		//Call startup at the end of setup(). Should be overridden in extending classes
		this.startUp();
	}

	
	protected int sketchWidth = 200;
	protected int sketchHeight = 300;
	
	
	public int sketchWidth() {
//		return screenWidth;
		return screenWidth;
	}

	public int sketchHeight() {
//		return sketchHeight;
		return screenHeight;
	}

	public String sketchRenderer() {
		return A3D; 
	}
	
	

	
	//TODO get gl version and create correct androidGL
	
	protected void loadGL(){
		/*
		String version = ((PGraphicsAndroid3D)g).gl.glGetString(GL10.GL_VERSION);
		logger.info("OpenGL Version: " + version);
        int major = Integer.parseInt("" + version.charAt(0));
        int minor = Integer.parseInt("" + version.charAt(2));
        */
		
        this.gl11Supported = false;
        this.gl20Supported = false;
//        if (major >= 2) {
//        		AndroidGL20 agl20 = new AndroidGL20(((PGraphicsAndroid3D)g).gl);
//                iGL20 = agl20;
//                //FIXME ADDED
////                iGL10  = jogl20;
////                iGL11 = jogl20;
////                iGL11Plus = jogl20;
//                glCommon = agl20;
//                this.gl20Supported = true;
////                this.gl11Supported = true;
////                this.gl11PlusSupported = true;
//        } else {
//                if (major == 1 && minor < 5) {
                        iGL10 = new AndroidGL10(((PGraphicsAndroid3D)g).gl);
//                } else {
//                        iGL11 = new AndroidGL11(((PGraphicsAndroid3D)g).gl);
//                        iGL10 = iGL11;
//                        this.gl11Supported = true;
//                }
                glCommon = iGL10;
//        }
	}


	
}
