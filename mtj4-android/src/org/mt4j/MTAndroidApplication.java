/***********************************************************************
*   MT4j Copyright (c) 2008 - 2012, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
*
*   This file is part of MT4j.
*
*   MT4j is free software: you can redistribute it and/or modify
*   it under the terms of the GNU Lesser General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   MT4j is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
*   GNU Lesser General Public License for more details.
*
*   You should have received a copy of the GNU Lesser General Public License
*   along with MT4j.  If not, see <http://www.gnu.org/licenses/>.
*
************************************************************************/
package org.mt4j;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.mt4j.audio.AndroidAudio;
import org.mt4j.input.AndroidInputManager;
import org.mt4j.input.ISurfaceTouchListener;
import org.mt4j.input.ISystemButtonListener;
import org.mt4j.util.AndroidPlatformUtil;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.PlatformUtil;
import org.mt4j.util.animation.ani.AniAnimation;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.fontFactories.AngelCodeFontFactory;
import org.mt4j.util.gdx.AndroidFiles;
import org.mt4j.util.logging.AndroidDefaultLogger;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;
import org.mt4j.util.opengl.AndroidGL10;
import org.mt4j.util.opengl.AndroidGL11;

import processing.core.PGraphicsAndroid3D;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/*
 Android notes:
  * at the moment OpenGL ES Version 1.1 is supported/used
  * put all resources in the asset folder -> asset subfolders are not supported for the most part
  * fonts dont support dynamic sizes -> at createFont, use the font size which is specified in the .fnt font file's name
    - to create new fonts, use the BMFont tool (Set the output to .png - tga seems to have problem, also only use 1 page and to avoid artifacts at scaling add a little padding)
  
  
  
 */

/**
 * The Class MTAndroidApplication.
 * Parts taken from the great LibGDX library (http://libgdx.badlogicgames.com/)
 */
public abstract class MTAndroidApplication extends AbstractMTApplication{
	
	/** The scene changed listeners. */
//	private List<ISystemButtonListener> iSystemButtonListeners;
	
	private List<ISystemButtonListener> iSystemButtonListeners = new ArrayList<ISystemButtonListener>();
	
	/** The input method manager. */
	private InputMethodManager inputMethodManager;

	private Vibrator vibrator;
	
	private SensorManager manager;
	
	private boolean compassAvailable = false;
	
	private float azimuth = 0;
	private float pitch = 0;
	private float roll = 0;
	private float inclination = 0;
//	private final Orientation nativeOrientation = Orientation.Portrait;
	private Orientation nativeOrientation;
	public boolean accelerometerAvailable = false;
	
	final float[] R = new float[9];
	final float[] orientation = new float[3];
	
	private final float[] magneticFieldValues = new float[3];
	private final float[] accelerometerValues = new float[3];
	private boolean useAccelerometer;
	private boolean useCompass;
	
	private SensorEventListener accelerometerListener;
	private SensorEventListener compassListener;
	
	public int maxSimultaneousSounds = 16;
	
	public enum Orientation {
		Landscape, Portrait
	}
	
	//	iSystemButtonListeners = new ArrayList<ISystemButtonListener>();
	
	/**
	 * Instantiates a new mT android application.
	 */
	public MTAndroidApplication(){
		super();
		
		// Initialize Loggin facilities  - IMPORTANT TO DO THIS ASAP! \\
		MTLoggerFactory.setLoggerProvider(new AndroidDefaultLogger()); 
		logger = MTLoggerFactory.getLogger(MTAndroidApplication.class.getName());
		logger.setLevel(ILogger.INFO);
		
		/*
		int rotation = getRotation();
		if (((rotation == 0 || rotation == 180) && (MT4jSettings.getInstance().getWindowWidth() >= MT4jSettings.getInstance().getWindowHeight()))
				|| 
			((rotation == 90 || rotation == 270) && (MT4jSettings.getInstance().getWindowWidth() <= MT4jSettings.getInstance().getWindowHeight()))) 
		{
			nativeOrientation = Orientation.Landscape;
		} else {
			nativeOrientation = Orientation.Portrait;
		}
		*/
	}
	
	
	/* (non-Javadoc)
	 * @see processing.core.PApplet#setup()
	 */
	@Override
	public void setup() {
//		Log.i(this.getClass().getSimpleName(), "SETUP CALLED");
		
//		 orientation(LANDSCAPE); //TODO make configurable
//		 orientation(PORTRAIT); //TODO make configurable
		
		// Default font settings and factories \\
		FontManager.DEFAULT_FONT = "arial20.fnt";
		FontManager.DEFAULT_FONT_SIZE = 16;
		FontManager.DEFAULT_FONT_FILL_COLOR = MTColor.BLACK;
		FontManager.DEFAULT_FONT_STROKE_COLOR = MTColor.BLACK;
		FontManager.DEFAULT_FONT_ANTIALIASING = true;
		//FontManager.getInstance().registerFontFactory(".ttf", new TTFontFactory());
		FontManager.getInstance().registerFontFactory(".fnt", new AngelCodeFontFactory());
		//////////////////////
		
		/////////////////////// 
		PlatformUtil.setGraphicsUtilProvider(new AndroidPlatformUtil(this));
		///////////////////////
		
		//Set to use our own OpenGL rendering by default
		MT4jSettings.getInstance().renderer = MT4jSettings.OPENGL_MODE;
		
		//Set all default paths to the "" (asset folder) path because we cant use the assetmanager 
		//if we use subfolders (no path separators allowed) but have to load the files/set permissions ourselves :( 
		//TODO -> is there another way to handle this?
		MT4jSettings.DEFAULT_3D_MODEL_PATH = "";
		MT4jSettings.DEFAULT_DATA_FOLDER_PATH = "";
		MT4jSettings.DEFAULT_FONT_PATH = "";
		MT4jSettings.DEFAULT_IMAGES_PATH = "";
		MT4jSettings.DEFAULT_SETTINGS_PATH = "";
		MT4jSettings.DEFAULT_SVG_PATH = "";
		MT4jSettings.DEFAULT_VIDEOS_PATH = "";
		
		MT4jSettings.getInstance().windowHeight = this.sketchHeight();
		MT4jSettings.getInstance().windowWidth = this.sketchWidth();
		logger.info("MT4j window dimensions: \"" + MT4jSettings.getInstance().getWindowWidth() + " x " +  MT4jSettings.getInstance().getWindowHeight() + "\"");
		
		// Save this applets rendering thread for reference
		this.renderThread = Thread.currentThread();
		
		//Set background color
		background(150);
		
		//Set the framerate
	    frameRate(MT4jSettings.getInstance().getMaxFrameRate());
	    logger.info("Maximum framerate: \"" + MT4jSettings.getInstance().getMaxFrameRate() + "\"");
	    
//	    hint(MTApplication.DISABLE_OPENGL_ERROR_REPORT);
		
		MT4jSettings.getInstance().programStartTime = System.currentTimeMillis();
		
		//FIXME USE? -> put in Config?
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		
		//Initialize and get OpenGL context
		this.loadGL();
		
		 //Initialize Ani animation library with application instance
		AniAnimation.init(this);
		
		//Create a new inputsourcePool
		if (getInputManager() == null){ //only set the default inputManager if none is set yet
			this.setInputManager(new AndroidInputManager(this, true));
		}
		
		//Get System Services
		vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
		inputMethodManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
		
//		/*
		//Stuff for getting the native orientation at app start
		int rotation = getRotation();
		if (((rotation == 0 || rotation == 180) && (MT4jSettings.getInstance().getWindowWidth() >= MT4jSettings.getInstance().getWindowHeight()))
				|| 
			((rotation == 90 || rotation == 270) && (MT4jSettings.getInstance().getWindowWidth() <= MT4jSettings.getInstance().getWindowHeight()))) 
		{
			nativeOrientation = Orientation.Landscape;
		} else {
			nativeOrientation = Orientation.Portrait;
		}
		
		//Register sensonr listeners that save changes in the correspondent variables (accelleration, rotation, compass)
//		registerSensorListeners();
//		*/
		
		//Initialize Audio
		if (!disableAudio) 
			audio = new AndroidAudio(this, maxSimultaneousSounds);
		
		//Initialize Files 
		files = new AndroidFiles(this.getAssets(), this.getFilesDir().getAbsolutePath());
		
		//Call startup at the end of setup(). Should be overridden in extending classes
		this.startUp();
	}
	
	@Override
	public void exit() {
		if (audio != null)
			((AndroidAudio)audio).dispose();
		
		super.exit();
	}

	
	/**
	 * Tries to hide the status bar. Adapted from libGDX library.
	 */
	protected void hideStatusBar () {
		View rootView = getWindow().getDecorView();

		try {
			Method m = View.class.getMethod("setSystemUiVisibility", int.class);
			m.invoke(rootView, 0x0);
			m.invoke(rootView, 0x1);
		} catch (Exception e) {
			logger.warn("Can't hide status bar");
			logger.warn(e);
		}
	}
	
	

	public void vibrate (int milliseconds) {
		vibrator.vibrate(milliseconds);
	}

	public void vibrate (long[] pattern, int repeat) {
		vibrator.vibrate(pattern, repeat);
	}

	public void cancelVibrate () {
		vibrator.cancel();
	}


	public float getAccelerometerX () {
		return accelerometerValues[0];
	}

	public float getAccelerometerY () {
		return accelerometerValues[1];
	}

	public float getAccelerometerZ () {
		return accelerometerValues[2];
	}


	private void updateOrientation () {
		if (SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues)) {
			SensorManager.getOrientation(R, orientation);
			azimuth = (float)Math.toDegrees(orientation[0]);
			pitch = (float)Math.toDegrees(orientation[1]);
			roll = (float)Math.toDegrees(orientation[2]);
		}
	}

	/** Returns the rotation matrix describing the devices rotation as per <a href=
	 * "http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])"
	 * >SensorManager#getRotationMatrix(float[], float[], float[], float[])</a>. Does not manipulate the matrix if the platform
	 * does not have an accelerometer.
	 * @param matrix */
	public void getRotationMatrix (float[] matrix) {
		SensorManager.getRotationMatrix(matrix, null, accelerometerValues, magneticFieldValues);
	}

	public float getAzimuth () {
		if (!compassAvailable) 
			return 0;

		updateOrientation();
		return azimuth;
	}

	public float getPitch () {
		if (!compassAvailable) 
			return 0;

		updateOrientation();
		return pitch;
	}

	public float getRoll () {
		if (!compassAvailable) 
			return 0;

		updateOrientation();
		return roll;
	}

	void registerSensorListeners () { //TODO this is never called atm
		if (useAccelerometer) {
			manager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
			if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
				accelerometerAvailable = false;
			} else {
				Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
				accelerometerListener = new SensorListener(this.nativeOrientation, this.accelerometerValues, this.magneticFieldValues);
				accelerometerAvailable = manager.registerListener(accelerometerListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
			}
		} else
			accelerometerAvailable = false;

		if (useCompass) {
			if (manager == null) manager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
			Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
			if (sensor != null) {
				compassAvailable = accelerometerAvailable;
				if (compassAvailable) {
					compassListener = new SensorListener(this.nativeOrientation, this.accelerometerValues, this.magneticFieldValues);
					compassAvailable = manager.registerListener(compassListener, sensor, SensorManager.SENSOR_DELAY_GAME);
				}
			} else {
				compassAvailable = false;
			}
		} else
			compassAvailable = false;
		logger.info("sensor listener setup");
	}

	void unregisterSensorListeners () {
		if (manager != null) {
			if (accelerometerListener != null) {
				manager.unregisterListener(accelerometerListener);
				accelerometerListener = null;
			}
			if (compassListener != null) {
				manager.unregisterListener(compassListener);
				compassListener = null;
			}
			manager = null;
		}
		logger.info("sensor listener tear down");
	}


	public int getRotation () {
		int orientation = 0;

		if (this instanceof Activity) {
			orientation = ((Activity)this).getWindowManager().getDefaultDisplay().getOrientation();
		} else {
			orientation = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
		}

		switch (orientation) {
		case Surface.ROTATION_0:
			return 0;
		case Surface.ROTATION_90:
			return 90;
		case Surface.ROTATION_180:
			return 180;
		case Surface.ROTATION_270:
			return 270;
		default:
			return 0;
		}
	}

	

	/** Our implementation of SensorEventListener. Because Android doesn't like it when we register more than one Sensor to a single
	 * SensorEventListener, we add one of these for each Sensor. Could use an anonymous class, but I don't see any harm in
	 * explicitly defining it here. Correct me if I am wrong. */
	private class SensorListener implements SensorEventListener {
		final float[] accelerometerValues;
		final float[] magneticFieldValues;
		final Orientation nativeOrientation;

		SensorListener (Orientation nativeOrientation, float[] accelerometerValues, float[] magneticFieldValues) {
			this.accelerometerValues = accelerometerValues;
			this.magneticFieldValues = magneticFieldValues;
			this.nativeOrientation = nativeOrientation;
		}

		@Override
		public void onAccuracyChanged (Sensor arg0, int arg1) {

		}

		@Override
		public void onSensorChanged (SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				if (nativeOrientation == Orientation.Portrait) {
					System.arraycopy(event.values, 0, accelerometerValues, 0, accelerometerValues.length);
				} else {
					accelerometerValues[0] = event.values[1];
					accelerometerValues[1] = -event.values[0];
					accelerometerValues[2] = event.values[2];
				}
			}
			if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				System.arraycopy(event.values, 0, magneticFieldValues, 0, magneticFieldValues.length);
			}
		}
	}

	
	

	
	/**
	 * Load gl.
	 */
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
	


	/** The touch listener. */
	private ISurfaceTouchListener touchListener;
	
	/**
	 * Sets the touch listener.
	 *
	 * @param listener the new touch listener
	 */
	public void setTouchListener(ISurfaceTouchListener listener){
		this.touchListener = listener;
	}
	
	/**
	 * Gets the touch listener.
	 *
	 * @return the touch listener
	 */
	public ISurfaceTouchListener getTouchListener(){
		return this.touchListener;
	}
	
	///////////////////////////////////////////////////////
	
	/**
	 * Process incoming touch events.
	 *
	 * @param event the event
	 * @return true, if successful
	 */
	@Override
	public boolean surfaceTouchEvent(MotionEvent event) {
		super.surfaceTouchEvent(event);
		
		final MotionEvent mEvent = event;
		if (touchListener != null){
			touchListener.onTouchEvent(mEvent);
		}
		return true;
	}

	
	//Dont override, because loadImage(img) which already creates a GLTexture (MTApplication) will call loadImage(img, null) and again try to create
	//a GLTexture..we just shouldnt use loadImage(img, params) directly..
//	@Override
//	public PImage loadImage(String filename, Object params) {
//		if (MT4jSettings.getInstance().isOpenGlMode()){
//			return new GLTexture(this, super.loadImage(filename, params));
//		}else{
//			return super.loadImage(filename, params);
//		}
//	}

	
//	protected int sketchWidth = 200;
//	protected int sketchHeight = 300;
	
	
	/* (non-Javadoc)
	 * @see processing.core.PApplet#sketchWidth()
	 */
	@Override
	public int sketchWidth() {
//		return screenWidth;
		return screenWidth;
//		return 600;
	}

	/* (non-Javadoc)
	 * @see processing.core.PApplet#sketchHeight()
	 */
	@Override
	public int sketchHeight() {
//		return sketchHeight;
		return screenHeight;
//		return 1024;
	}

	/* (non-Javadoc)
	 * @see processing.core.PApplet#sketchRenderer()
	 */
	@Override
	public String sketchRenderer() {
		return A3D; 
	}
	
	
	/* (non-Javadoc)
	 * @see processing.core.PApplet#sketchColordepth()
	 */
	@Override
	public String sketchColordepth() { 
//		return super.sketchColordepth(); //To get default color, depth and stencil bit depths
//		return HIGH_COLOR_DEPTH;
		return "8:8:8:8:24:8"; //To get a stencil buffer
	}
	
	///////////////////////////////////////////////
	/*
	@Override
	protected void onStop() {
		System.out.println("On Stop");
		//at least on android even if stopping the mt4j app, the fontmanager seems to be kept with fonts cached, but textures destroyed already?
		FontManager.getInstance().clearCache(); 
		
		super.onStop();
	}
	*/
	
	/*
	public void onBackPressed() {
		super.onBackPressed();
		
		//do whatever you want here, or nothing
		System.out.println("Back pressed");
		//TODO go through SystemButtonListeners and call -> in app only the one in the current scene should be called
		
		for (ISystemButtonListener listener : iSystemButtonListeners){
			listener.onBackPressed();
		}
	}
	*/
	
	/*
	@Override
	protected void onPause() {
		System.out.println("On Pause");
//		Iscene cs = getCurrentScene();
//		if (cs != null){
//			cs.onPause();  //TODO we would have to introduce a Android Scene class.. better do with listeners then..
//		}
		
		for (ISystemButtonListener listener : iSystemButtonListeners){
			listener.onPause();
		}
		
		super.onPause();
	}
	*/
	
//	/*
	@Override
	protected void onPause() {
		logger.debug("onPause()");
		
		this.unregisterSensorListeners();
		
		if (isFinishing()) {
			//clear resources/caches -> app is destroyed actually (sure?) //TODO
		}
		
		//TODO pause audio/music=
		
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		logger.debug("onResume()");
		
		this.registerSensorListeners();
		
		//TODO resume audio/music=
		
		super.onResume();
	}
//	*/
	
//	@Override
//	protected void onRestart() {
//		super.onRestart();
//		
//		for (ISystemButtonListener listener : iSystemButtonListeners){
//			listener.onRestart();
//		}
//
	/*
	@Override
	protected void onResume() {
		super.onResume();
		
		System.out.println("On Resume");
		for (ISystemButtonListener listener : iSystemButtonListeners){
			listener.onResume();
		}
	}
		}
	*/
	
	/*
	public void keyPressed() {
		super.keyPressed();
	
		if (key == CODED) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				keyCode = 1;  // don't quit by default
			}
		}
	}
	*/
	

	public int getVersion () {
		return Integer.parseInt(android.os.Build.VERSION.SDK);
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	public void onBackPressed() {
		//do whatever you want here, or nothing
	}

	/* (non-Javadoc)
	 * @see org.mt4j.AbstractMTApplication#keyPressed()
	 */
	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				keyCode = 1;  // don't quit by default
			}
		}
		super.keyPressed();
	}

	
	/**
	 * Gets the surface view.
	 *
	 * @return the surface view
	 */
	public SurfaceView getSurfaceView(){
		return surfaceView;
	}
	
	
	/**
	 * Gets the input method manager.
	 *
	 * @return the input method manager
	 */
	public InputMethodManager getInputMethodManager(){
		return inputMethodManager;
	}
	
	/**
	 * Show soft input.
	 *
	 * @param view the view
	 */
	public void showSoftInput(View view){
		getInputMethodManager().showSoftInput(view, InputMethodManager.SHOW_FORCED/* | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS*/); // .SHOW_FORCED);
	}
	
	/**
	 * Hide soft input.
	 *
	 * @param view the view
	 */
	public void hideSoftInput(View view){
		getInputMethodManager().hideSoftInputFromWindow(view.getWindowToken(), 0); //Hide keyboard
	}

	
}
