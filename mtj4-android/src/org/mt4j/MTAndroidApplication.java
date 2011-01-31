package org.mt4j;

import org.mt4j.input.AndroidInputManager;
import org.mt4j.util.AndroidGraphicsUtil;
import org.mt4j.util.GraphicsUtil;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.animation.ani.AniAnimation;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;



public abstract class MTAndroidApplication extends MTApplication{
	static{
		//Initialize Loggin facilities  - IMPORTANT TO DO THIS ASAP!//////
//		MTLoggerFactory.setLoggerProvider(new Log4jLogger()); //FIXME TEST
		//TODO android logger
//		MTLoggerFactory.setLoggerProvider(new JavaLogger()); //FIXME TEST
		logger = MTLoggerFactory.getLogger(MTApplication.class.getName());
		logger.setLevel(ILogger.INFO);
	}
	
	
	@Override
	public void setup() {
		//TODO
		// Applet size - size() must be the first command in setup() method
		this.size(MT4jSettings.getInstance().getWindowWidth(), MT4jSettings.getInstance().getWindowHeight(), A3D);
		
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
		
		
		//Check if OS 32/64 Bit
		String bit = System.getProperty("sun.arch.data.model");
		logger.info("Platform: \"" + System.getProperty("os.name") + "\" -> Version: \"" + System.getProperty("os.version") +  "\" -> JVM Bit: \"" + bit + "\""); 
		MT4jSettings.getInstance().architecture = bit.contains("64")? MT4jSettings.ARCHITECTURE_64_BIT : MT4jSettings.ARCHITECTURE_32_BIT;
		
		// Save this applets rendering thread for reference
		this.renderThread = Thread.currentThread();
		
		logger.info("MT4j window dimensions: \"" + MT4jSettings.getInstance().getWindowWidth() + " X " +  MT4jSettings.getInstance().getWindowHeight() + "\"");
		
		//Set background color
		background(150);
		
		//Set the framerate
	    frameRate(MT4jSettings.getInstance().getMaxFrameRate());
	    logger.info("Maximum framerate: \"" + MT4jSettings.getInstance().getMaxFrameRate() + "\"");
	    
	    //FIXME TODO add in settings.txt?
	    hint(MTApplication.DISABLE_OPENGL_ERROR_REPORT);
		
		MT4jSettings.getInstance().programStartTime = System.currentTimeMillis();

		
		//Create a new inputsourcePool
		if (getInputManager() == null){ //only set the default inputManager if none is set yet
			this.setInputManager(new AndroidInputManager(this, true));
		}
		
		AniAnimation.init(this); //Initialize Ani animation library
		
		/*
		* Resizable Window test
		* Problems:
		* - all textures, shaders etc get destroyed because a new gl context is created
		* - cursor coordiantes are calculated wrong? we prolly have to update Papplet width/height 
		frame.setResizable(true);
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				if(e.getSource() == frame) { 
					frame.setSize(frame.getWidth(), minHeight); 
				}
			}
		} );
		*/ 
		
		//Call startup at the end of setup(). Should be overridden in extending classes
		this.startUp();
	}
	
	@Override
	public int sketchWidth() {
		// TODO Auto-generated method stub
		return super.sketchWidth();
	}
	
	@Override
	public int sketchHeight() {
		// TODO Auto-generated method stub
		return super.sketchHeight();
	}
	
	protected void loadGL(){
		//TODO
	}


	
}
