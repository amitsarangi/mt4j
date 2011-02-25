package org.mt4j;

import org.mt4j.input.AndroidInputManager;
import org.mt4j.input.ISurfaceTouchListener;
import org.mt4j.util.AndroidGraphicsUtil;
import org.mt4j.util.GraphicsUtil;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.animation.ani.AniAnimation;
import org.mt4j.util.logging.AndroidDefaultLogger;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;
import org.mt4j.util.opengl.AndroidGL10;
import org.mt4j.util.opengl.AndroidGL11;
import org.mt4j.util.opengl.GLTexture;

import processing.core.PGraphicsAndroid3D;
import processing.core.PImage;
import processing.core.PTexture.Parameters;
import android.util.Log;
import android.view.MotionEvent;



public abstract class MTAndroidApplication extends MTApplication{
	
	public MTAndroidApplication(){
		super();
		Log.i(this.getClass().getSimpleName(), "MTAndroidApplication() constructor CALLED");
	}
	
	
	@Override
	public void setup() {
		Log.i(this.getClass().getSimpleName(), "SETUP CALLED");
		
		//Initialize Loggin facilities  - IMPORTANT TO DO THIS ASAP!//////
		MTLoggerFactory.setLoggerProvider(new AndroidDefaultLogger()); 
//		MTLoggerFactory.setLoggerProvider(new AndroidDummyLogger());
		logger = MTLoggerFactory.getLogger(MTAndroidApplication.class.getName());
		logger.setLevel(ILogger.INFO);
		
		//Add default font factories /////////////
//		FontManager.getInstance().registerFontFactory(".ttf", new TTFontFactory());
		//////////////////////
		
		/////////////////////// //FIXME TEST
		GraphicsUtil.setGraphicsUtilProvider(new AndroidGraphicsUtil(this));
		///////////////////////
		
		// Save this applets rendering thread for reference
		this.renderThread = Thread.currentThread();
		
		MT4jSettings.getInstance().windowHeight = this.sketchHeight();
		MT4jSettings.getInstance().windowWidth = this.sketchWidth();
		logger.info("MT4j window dimensions: \"" + MT4jSettings.getInstance().getWindowWidth() + " x " +  MT4jSettings.getInstance().getWindowHeight() + "\"");
		
		//Set background color
		background(150);
		
		//Set the framerate
	    frameRate(MT4jSettings.getInstance().getMaxFrameRate());
	    logger.info("Maximum framerate: \"" + MT4jSettings.getInstance().getMaxFrameRate() + "\"");
	    
//	    hint(MTApplication.DISABLE_OPENGL_ERROR_REPORT);
		
		MT4jSettings.getInstance().programStartTime = System.currentTimeMillis();
		
		this.loadGL();
		
		AniAnimation.init(this); //Initialize Ani animation library
		
		//Create a new inputsourcePool
		if (getInputManager() == null){ //only set the default inputManager if none is set yet
			this.setInputManager(new AndroidInputManager(this, true));
		}
		
//		orientation(LANDSCAPE); //FIXME TEST!!
		
		//Call startup at the end of setup(). Should be overridden in extending classes
		this.startUp();
	}

	
//	protected int sketchWidth = 200;
//	protected int sketchHeight = 300;
	
	
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
	
	
	
	protected void loadGL(){
		/*
		String version = ((PGraphicsAndroid3D)g).gl.glGetString(GL10.GL_VERSION);
		logger.info("OpenGL Version: " + version);
        int major = Integer.parseInt("" + version.charAt(0));
        int minor = Integer.parseInt("" + version.charAt(2));
        */
		
        this.gl11Supported = false;
        this.gl20Supported = false;
        
        PGraphicsAndroid3D pg3D = (PGraphicsAndroid3D)this.g;

        if (pg3D.gl11xp != null || pg3D.gl11x != null || pg3D.gl11 != null){
        	iGL11 = new AndroidGL11(pg3D.gl);
        	iGL10 = iGL11;
        	glCommon = iGL11;
        	gl11PlusSupported = true;
        	logger.info("OpenGL profile 1.1 supported");
        }else{
        	iGL10 = new AndroidGL10(((PGraphicsAndroid3D)g).gl);
        	glCommon = iGL10;
        	logger.info("OpenGL profile 1.0 supported");
        }
        
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
//                        iGL10 = new AndroidGL10(((PGraphicsAndroid3D)g).gl);
//                } else {
//                        iGL11 = new AndroidGL11(((PGraphicsAndroid3D)g).gl);
//                        iGL10 = iGL11;
//                        this.gl11Supported = true;
//                }
//                glCommon = iGL10;
//        }
	}
	


	private ISurfaceTouchListener touchListener;
	
	public void setTouchListener(ISurfaceTouchListener listener){
		this.touchListener = listener;
	}
	
	public ISurfaceTouchListener getTouchListener(){
		return this.touchListener;
	}
	
	///////////////////////////////////////////////////////
	
	/** Process incoming touch events */
	@Override
	public boolean surfaceTouchEvent(MotionEvent event) {
		super.surfaceTouchEvent(event);
		
		final MotionEvent mEvent = event;
		if (touchListener != null){
			touchListener.onTouchEvent(mEvent);
		}
		return true;
	}

	
	@Override
	public PImage loadImage(String filename, int sampling) {
		if (MT4jSettings.getInstance().isOpenGlMode()){
			return new GLTexture(this, super.loadImage(filename, sampling));
		}else{
			return super.loadImage(filename, sampling);
		}
	}
	
	@Override
	public PImage loadImage(String filename, Parameters params) {
		if (MT4jSettings.getInstance().isOpenGlMode()){
			return new GLTexture(this, super.loadImage(filename, params));
		}else{
			return super.loadImage(filename, params);
		}
	}

	
}
