package org.mt4j;

import java.lang.reflect.Method;

import org.mt4j.input.AndroidInputManager;
import org.mt4j.input.ISurfaceTouchListener;
import org.mt4j.sceneManagement.IPreDrawAction;
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
import android.view.MotionEvent;



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
		
		MT4jSettings.getInstance().windowHeight = this.sketchHeight();
		MT4jSettings.getInstance().windowWidth = this.sketchWidth();
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
		
		AniAnimation.init(this); //Initialize Ani animation library
		
		//Create a new inputsourcePool
		if (getInputManager() == null){ //only set the default inputManager if none is set yet
			this.setInputManager(new AndroidInputManager(this, true));
		}
		
//		orientation(LANDSCAPE); //FIXME TEST!!
		
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
	
	
//	@Override
//	public boolean surfaceTouchEvent(MotionEvent event) {
//		logger.info("recieved MotionEvent : " + event);
//		return super.surfaceTouchEvent(event);
//	}
	
	
//	public static final boolean multiTouchSupported;
//	private static Method m_getPointerCount;
//	private static Method m_getPointerId;
//	private static Method m_getPressure;
//	private static Method m_getHistoricalX;
//	private static Method m_getHistoricalY;
//	private static Method m_getHistoricalPressure;
//	private static Method m_getX;
//	private static Method m_getY;
//	private static int ACTION_POINTER_UP = 6;
//	private static int ACTION_POINTER_INDEX_SHIFT = 8;
//
//	static {
//		boolean succeeded = false;
//		try {
//			// Android 2.0.1 stuff:
//			m_getPointerCount = MotionEvent.class.getMethod("getPointerCount");
//			m_getPointerId = MotionEvent.class.getMethod("getPointerId", Integer.TYPE);
//			m_getPressure = MotionEvent.class.getMethod("getPressure", Integer.TYPE);
//			m_getHistoricalX = MotionEvent.class.getMethod("getHistoricalX", Integer.TYPE, Integer.TYPE);
//			m_getHistoricalY = MotionEvent.class.getMethod("getHistoricalY", Integer.TYPE, Integer.TYPE);
//			m_getHistoricalPressure = MotionEvent.class.getMethod("getHistoricalPressure", Integer.TYPE, Integer.TYPE);
//			m_getX = MotionEvent.class.getMethod("getX", Integer.TYPE);
//			m_getY = MotionEvent.class.getMethod("getY", Integer.TYPE);
//			succeeded = true;
//		} catch (Exception e) {
//			Log.e("MultiTouchController", "static initializer failed", e);
//		}
//		multiTouchSupported = succeeded;
//		if (multiTouchSupported) {
//			// Android 2.2+ stuff (the original Android 2.2 consts are declared above,
//			// and these actions aren't used previous to Android 2.2):
//			try {
//				ACTION_POINTER_UP = MotionEvent.class.getField("ACTION_POINTER_UP").getInt(null);
//				ACTION_POINTER_INDEX_SHIFT = MotionEvent.class.getField("ACTION_POINTER_INDEX_SHIFT").getInt(null);
//			} catch (Exception e) {
//			}
//		}
//	}
//
//	// ------------------------------------------------------------------------------------
//	/**
//	 * Time in ms required after a change in event status (e.g. putting down or lifting off the second finger) before events actually do anything --
//	 * helps eliminate noisy jumps that happen on change of status
//	 */
//	private static final long EVENT_SETTLE_TIME_INTERVAL = 20;
//
//	/**
//	 * The biggest possible abs val of the change in x or y between multitouch events (larger dx/dy events are ignored) -- helps eliminate jumps in
//	 * pointer position on finger 2 up/down.
//	 */
//	private static final float MAX_MULTITOUCH_POS_JUMP_SIZE = 30.0f;
//
//	/**
//	 * The biggest possible abs val of the change in multitouchWidth or multitouchHeight between multitouch events (larger-jump events are ignored) --
//	 * helps eliminate jumps in pointer position on finger 2 up/down.
//	 */
//	private static final float MAX_MULTITOUCH_DIM_JUMP_SIZE = 40.0f;
//
//	/** The smallest possible distance between multitouch points (used to avoid div-by-zero errors and display glitches) */
//	private static final float MIN_MULTITOUCH_SEPARATION = 30.0f;

	/** The max number of touch points that can be present on the screen at once */
	public static final int MAX_TOUCH_POINTS = 20;

	/** Generate tons of log entries for debugging */
	public static final boolean DEBUG = true;
	
	// ----------------------------------------------------------------------------------------------------------------------
	
	private static final float[] xVals = new float[MAX_TOUCH_POINTS];
	private static final float[] yVals = new float[MAX_TOUCH_POINTS];
	private static final float[] pressureVals = new float[MAX_TOUCH_POINTS];
	private static final int[] pointerIds = new int[MAX_TOUCH_POINTS];
	
	// ----------------------------------------------------------------------------------------------------------------------

	/** No touch points down. */
	private static final int MODE_NOTHING = 0;

	/** One touch point down, dragging an object. */
	private static final int MODE_DRAG = 1;

	/** Two or more touch points down, stretching/rotating an object using the first two touch points. */
	private static final int MODE_PINCH = 2;

	/** Current drag mode */
	private int mMode = MODE_NOTHING;

	// ----------------------------------------------------------------------------------------------------------------------

	private boolean handleSingleTouchEvents = false;

	///////////////////////////////////////////////////////
	private static final int INVALID_POINTER_ID = -1;

	// The ‘active pointer’ is the one currently moving our object.
	private int mActivePointerId = INVALID_POINTER_ID;

	private float mPosX;
	private float mPosY;

	private float mLastTouchX;
	private float mLastTouchY;


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
		
		
//		switch (action & MotionEvent.ACTION_MASK) {
//		case MotionEvent.ACTION_DOWN: { //this is only the case when the 1st cursor enters (?)
//			final float x = ev.getX();
//			final float y = ev.getY();
//
//			mLastTouchX = x;
//			mLastTouchY = y;
//
//			// Save the ID of this pointer
//			mActivePointerId = ev.getPointerId(0);
//			break;
//		}
//
//		case MotionEvent.ACTION_MOVE: {
//			// Find the index of the active pointer and fetch its position
//			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
//			final float x = ev.getX(pointerIndex);
//			final float y = ev.getY(pointerIndex);
//
//			final float dx = x - mLastTouchX;
//			final float dy = y - mLastTouchY;
//
//			mPosX += dx;
//			mPosY += dy;
//
//			mLastTouchX = x;
//			mLastTouchY = y;
//
//			break;
//		}
//
//		case MotionEvent.ACTION_UP: {
//			mActivePointerId = INVALID_POINTER_ID;
//			break;
//		}
//
//		case MotionEvent.ACTION_CANCEL: {
//			mActivePointerId = INVALID_POINTER_ID;
//			break;
//		}
//
//		case MotionEvent.ACTION_POINTER_UP: {
//			// Extract the index of the pointer that left the touch sensor
//			final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) 
//			>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
//			final int pointerId = ev.getPointerId(pointerIndex);
//			if (pointerId == mActivePointerId) {
//				// This was our active pointer going up. Choose a new
//				// active pointer and adjust accordingly.
//				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
//				mLastTouchX = ev.getX(newPointerIndex);
//				mLastTouchY = ev.getY(newPointerIndex);
//				mActivePointerId = ev.getPointerId(newPointerIndex);
//			}
//			break;
//		}
//		}

		return true;

		
		
		
		
		
//		try {
//			int pointerCount = multiTouchSupported ? (Integer) m_getPointerCount.invoke(event) : 1;
//			if (DEBUG)
//				Log.i("MultiTouch", "Got here 1 - " + multiTouchSupported + " " + mMode + " " + handleSingleTouchEvents + " " + pointerCount);
//			if (mMode == MODE_NOTHING && !handleSingleTouchEvents && pointerCount == 1)
//				// Not handling initial single touch events, just pass them on
//				return false;
//			if (DEBUG)
//				Log.i("MultiTouch", "Got here 2");
//
//			// Handle history first (we sometimes get history with ACTION_MOVE events)
//			int action = event.getAction();
//			int histLen = event.getHistorySize() / pointerCount;
//			for (int histIdx = 0; histIdx <= histLen; histIdx++) {
//				// Read from history entries until histIdx == histLen, then read from current event
//				boolean processingHist = histIdx < histLen;
//				if (!multiTouchSupported || pointerCount == 1) {
//					// Use single-pointer methods -- these are needed as a special case (for some weird reason) even if
//					// multitouch is supported but there's only one touch point down currently -- event.getX(0) etc. throw
//					// an exception if there's only one point down.
//					if (DEBUG)
//						Log.i("MultiTouch", "Got here 3");
//					xVals[0] = processingHist ? event.getHistoricalX(histIdx) : event.getX();
//					yVals[0] = processingHist ? event.getHistoricalY(histIdx) : event.getY();
//					pressureVals[0] = processingHist ? event.getHistoricalPressure(histIdx) : event.getPressure();
//				} else {
//					// Read x, y and pressure of each pointer
//					if (DEBUG)
//						Log.i("MultiTouch", "Got here 4");
//					int numPointers = Math.min(pointerCount, MAX_TOUCH_POINTS);
//					
//					if (DEBUG && pointerCount > MAX_TOUCH_POINTS){
//						Log.i("MultiTouch", "Got more pointers than MAX_TOUCH_POINTS");
//					}
//					
//					for (int ptrIdx = 0; ptrIdx < numPointers; ptrIdx++) {
//						int ptrId = (Integer) m_getPointerId.invoke(event, ptrIdx);
//						pointerIds[ptrIdx] = ptrId;
//						// N.B. if pointerCount == 1, then the following methods throw an array index out of range exception,
//						// and the code above is therefore required not just for Android 1.5/1.6 but also for when there is
//						// only one touch point on the screen -- pointlessly inconsistent :(
//						xVals[ptrIdx] = (Float) (processingHist ? m_getHistoricalX.invoke(event, ptrIdx, histIdx) : m_getX.invoke(event, ptrIdx));
//						yVals[ptrIdx] = (Float) (processingHist ? m_getHistoricalY.invoke(event, ptrIdx, histIdx) : m_getY.invoke(event, ptrIdx));
//						pressureVals[ptrIdx] = (Float) (processingHist ? m_getHistoricalPressure.invoke(event, ptrIdx, histIdx) : m_getPressure
//								.invoke(event, ptrIdx));
//					}
//				}
//				// Decode event
//				decodeTouchEvent(pointerCount, xVals, yVals, pressureVals, pointerIds, //
//						/* action = */processingHist ? MotionEvent.ACTION_MOVE : action, //
//						/* down = */processingHist ? true : action != MotionEvent.ACTION_UP //
//								&& (action & ((1 << ACTION_POINTER_INDEX_SHIFT) - 1)) != ACTION_POINTER_UP //
//								&& action != MotionEvent.ACTION_CANCEL, //
//						processingHist ? event.getHistoricalEventTime(histIdx) : event.getEventTime());
//			}
//
//			return true;
//		} catch (Exception e) {
//			// In case any of the introspection stuff fails (it shouldn't)
//			Log.e("MultiTouchController", "onTouchEvent() failed", e);
//			return false;
//		}
	}
	
	
	/** The current touch point */
	private PointInfo mCurrPt;

	/** The previous touch point */
	private PointInfo mPrevPt;

	/** Fields extracted from mCurrPt */
	private float mCurrPtX, mCurrPtY, mCurrPtDiam, mCurrPtWidth, mCurrPtHeight, mCurrPtAng;


	private void decodeTouchEvent(int pointerCount, float[] x, float[] y, float[] pressure, int[] pointerIds, int action, boolean down, long eventTime) {
		if (DEBUG)
			Log.i("MultiTouch", "Got here 5 - " + pointerCount + " " + action + " " + down);

		// Swap curr/prev points
		PointInfo tmp = mPrevPt;
		mPrevPt = mCurrPt;
		mCurrPt = tmp;
		// Overwrite old prev point
		mCurrPt.set(pointerCount, x, y, pressure, pointerIds, action, down, eventTime);
		
		
//		multiTouchController();
	}
	
	/** A class that packages up all MotionEvent information with all derived multitouch information (if available) */
	public static class PointInfo {
		// Multitouch information
		private int numPoints;
		private float[] xs = new float[MAX_TOUCH_POINTS];
		private float[] ys = new float[MAX_TOUCH_POINTS];
		private float[] pressures = new float[MAX_TOUCH_POINTS];
		private int[] pointerIds = new int[MAX_TOUCH_POINTS];

		// Midpoint of pinch operations
		private float xMid, yMid, pressureMid;

		// Width/diameter/angle of pinch operations
		private float dx, dy, diameter, diameterSq, angle;

		// Whether or not there is at least one finger down (isDown) and/or at least two fingers down (isMultiTouch)
		private boolean isDown, isMultiTouch;

		// Whether or not these fields have already been calculated, for caching purposes
		private boolean diameterSqIsCalculated, diameterIsCalculated, angleIsCalculated;

		// Event action code and event time
		private int action;
		private long eventTime;

		// -------------------------------------------------------------------------------------------------------------------------------------------

		/** Set all point info */
		private void set(int numPoints, float[] x, float[] y, float[] pressure, int[] pointerIds, int action, boolean isDown, long eventTime) {
			if (DEBUG)
				Log.i("MultiTouch", "Got here 8 - " + +numPoints + " " + x[0] + " " + y[0] + " " + (numPoints > 1 ? x[1] : x[0]) + " "
						+ (numPoints > 1 ? y[1] : y[0]) + " " + action + " " + isDown);
			this.eventTime = eventTime;
			this.action = action;
			this.numPoints = numPoints;
			for (int i = 0; i < numPoints; i++) {
				this.xs[i] = x[i];
				this.ys[i] = y[i];
				this.pressures[i] = pressure[i];
				this.pointerIds[i] = pointerIds[i];
			}
			this.isDown = isDown;
			this.isMultiTouch = numPoints >= 2;

			if (isMultiTouch) {
				xMid = (x[0] + x[1]) * .5f;
				yMid = (y[0] + y[1]) * .5f;
				pressureMid = (pressure[0] + pressure[1]) * .5f;
				dx = Math.abs(x[1] - x[0]);
				dy = Math.abs(y[1] - y[0]);

			} else {
				// Single-touch event
				xMid = x[0];
				yMid = y[0];
				pressureMid = pressure[0];
				dx = dy = 0.0f;
			}
			// Need to re-calculate the expensive params if they're needed
			diameterSqIsCalculated = diameterIsCalculated = angleIsCalculated = false;
		}

		/**
		 * Copy all fields from one PointInfo class to another. PointInfo objects are volatile so you should use this if you want to keep track of the
		 * last touch event in your own code.
		 */
		public void set(PointInfo other) {
			this.numPoints = other.numPoints;
			for (int i = 0; i < numPoints; i++) {
				this.xs[i] = other.xs[i];
				this.ys[i] = other.ys[i];
				this.pressures[i] = other.pressures[i];
				this.pointerIds[i] = other.pointerIds[i];
			}
			this.xMid = other.xMid;
			this.yMid = other.yMid;
			this.pressureMid = other.pressureMid;
			this.dx = other.dx;
			this.dy = other.dy;
			this.diameter = other.diameter;
			this.diameterSq = other.diameterSq;
			this.angle = other.angle;
			this.isDown = other.isDown;
			this.action = other.action;
			this.isMultiTouch = other.isMultiTouch;
			this.diameterIsCalculated = other.diameterIsCalculated;
			this.diameterSqIsCalculated = other.diameterSqIsCalculated;
			this.angleIsCalculated = other.angleIsCalculated;
			this.eventTime = other.eventTime;
		}

		// -------------------------------------------------------------------------------------------------------------------------------------------

		/** True if number of touch points >= 2. */
		public boolean isMultiTouch() {
			return isMultiTouch;
		}

		/** Difference between x coords of touchpoint 0 and 1. */
		public float getMultiTouchWidth() {
			return isMultiTouch ? dx : 0.0f;
		}

		/** Difference between y coords of touchpoint 0 and 1. */
		public float getMultiTouchHeight() {
			return isMultiTouch ? dy : 0.0f;
		}

		/** Fast integer sqrt, by Jim Ulery. Much faster than Math.sqrt() for integers. */
		private int julery_isqrt(int val) {
			int temp, g = 0, b = 0x8000, bshft = 15;
			do {
				if (val >= (temp = (((g << 1) + b) << bshft--))) {
					g += b;
					val -= temp;
				}
			} while ((b >>= 1) > 0);
			return g;
		}

		/** Calculate the squared diameter of the multitouch event, and cache it. Use this if you don't need to perform the sqrt. */
		public float getMultiTouchDiameterSq() {
			if (!diameterSqIsCalculated) {
				diameterSq = (isMultiTouch ? dx * dx + dy * dy : 0.0f);
				diameterSqIsCalculated = true;
			}
			return diameterSq;
		}

		/** Calculate the diameter of the multitouch event, and cache it. Uses fast int sqrt but gives accuracy to 1/16px. */
		public float getMultiTouchDiameter() {
			if (!diameterIsCalculated) {
				if (!isMultiTouch) {
					diameter = 0.0f;
				} else {
					// Get 1/16 pixel's worth of subpixel accuracy, works on screens up to 2048x2048
					// before we get overflow (at which point you can reduce or eliminate subpix
					// accuracy, or use longs in julery_isqrt())
					float diamSq = getMultiTouchDiameterSq();
					diameter = (diamSq == 0.0f ? 0.0f : (float) julery_isqrt((int) (256 * diamSq)) / 16.0f);
					// Make sure diameter is never less than dx or dy, for trig purposes
					if (diameter < dx)
						diameter = dx;
					if (diameter < dy)
						diameter = dy;
				}
				diameterIsCalculated = true;
			}
			return diameter;
		}

		/**
		 * Calculate the angle of a multitouch event, and cache it. Actually gives the smaller of the two angles between the x axis and the line
		 * between the two touchpoints, so range is [0,Math.PI/2]. Uses Math.atan2().
		 */
		public float getMultiTouchAngle() {
			if (!angleIsCalculated) {
				if (!isMultiTouch)
					angle = 0.0f;
				else
					angle = (float) Math.atan2(ys[1] - ys[0], xs[1] - xs[0]);
				angleIsCalculated = true;
			}
			return angle;
		}

		// -------------------------------------------------------------------------------------------------------------------------------------------

		/** Return the total number of touch points */
		public int getNumTouchPoints() {
			return numPoints;
		}

		/** Return the X coord of the first touch point if there's only one, or the midpoint between first and second touch points if two or more. */
		public float getX() {
			return xMid;
		}

		/** Return the array of X coords -- only the first getNumTouchPoints() of these is defined. */
		public float[] getXs() {
			return xs;
		}

		/** Return the X coord of the first touch point if there's only one, or the midpoint between first and second touch points if two or more. */
		public float getY() {
			return yMid;
		}

		/** Return the array of Y coords -- only the first getNumTouchPoints() of these is defined. */
		public float[] getYs() {
			return ys;
		}

		/**
		 * Return the array of pointer ids -- only the first getNumTouchPoints() of these is defined. These don't have to be all the numbers from 0 to
		 * getNumTouchPoints()-1 inclusive, numbers can be skipped if a finger is lifted and the touch sensor is capable of detecting that that
		 * particular touch point is no longer down. Note that a lot of sensors do not have this capability: when finger 1 is lifted up finger 2
		 * becomes the new finger 1.  However in theory these IDs can correct for that.  Convert back to indices using MotionEvent.findPointerIndex().
		 */
		public int[] getPointerIds() {
			return pointerIds;
		}

		/** Return the pressure the first touch point if there's only one, or the average pressure of first and second touch points if two or more. */
		public float getPressure() {
			return pressureMid;
		}

		/** Return the array of pressures -- only the first getNumTouchPoints() of these is defined. */
		public float[] getPressures() {
			return pressures;
		}

		// -------------------------------------------------------------------------------------------------------------------------------------------

		public boolean isDown() {
			return isDown;
		}

		public int getAction() {
			return action;
		}

		public long getEventTime() {
			return eventTime;
		}
	}

	// ------------------------------------------------------------------------------------


	
}
