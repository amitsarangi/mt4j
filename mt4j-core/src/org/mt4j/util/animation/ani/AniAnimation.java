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
package org.mt4j.util.animation.ani;

import org.mt4j.AbstractMTApplication;
import org.mt4j.util.animation.AbstractAnimation;
import org.mt4j.util.animation.AnimationEvent;

import de.looksgood.ani.Ani;
import de.looksgood.ani.AniConstants;

/**
 * The Class AniAnimation.
 */
public class AniAnimation extends AbstractAnimation /*implements AniConstants*/{
	
	/** The x. */
	public float x;
	
	/** The ani. */
	private AniAdapter ani;
	
	/** The Constant LINEAR. */
	public static final String LINEAR = AniConstants.LINEAR;
	
	/** The Constant QUAD_IN. */
	public static final String QUAD_IN = AniConstants.QUAD_IN;
	
	/** The Constant QUAD_OUT. */
	public static final String QUAD_OUT = AniConstants.QUAD_OUT;
	
	/** The Constant QUAD_IN_OUT. */
	public static final String QUAD_IN_OUT = AniConstants.QUAD_IN_OUT;
	
	/** The Constant CUBIC_IN. */
	public static final String CUBIC_IN = AniConstants.CUBIC_IN;
	
	/** The Constant CUBIC_IN_OUT. */
	public static final String CUBIC_IN_OUT = AniConstants.CUBIC_IN_OUT;
	
	/** The Constant CUBIC_OUT. */
	public static final String CUBIC_OUT = AniConstants.CUBIC_OUT;
	
	/** The Constant QUART_IN. */
	public static final String QUART_IN = AniConstants.QUART_IN;
	
	/** The Constant QUART_OUT. */
	public static final String QUART_OUT = AniConstants.QUART_OUT;
	
	/** The Constant QUART_IN_OUT. */
	public static final String QUART_IN_OUT = AniConstants.QUART_IN_OUT;
	
	/** The Constant QUINT_IN. */
	public static final String QUINT_IN = AniConstants.QUINT_IN;
	
	/** The Constant QUINT_OUT. */
	public static final String QUINT_OUT = AniConstants.QUINT_OUT;
	
	/** The Constant QUINT_IN_OUT. */
	public static final String QUINT_IN_OUT = AniConstants.QUINT_IN_OUT;
	
	/** The Constant SINE_IN. */
	public static final String SINE_IN = AniConstants.SINE_IN;
	
	/** The Constant SINE_OUT. */
	public static final String SINE_OUT = AniConstants.SINE_OUT;
	
	/** The Constant SINE_IN_OUT. */
	public static final String SINE_IN_OUT = AniConstants.SINE_IN_OUT;
	
	/** The Constant CIRC_IN. */
	public static final String CIRC_IN = AniConstants.CIRC_IN;
	
	/** The Constant CIRC_OUT. */
	public static final String CIRC_OUT = AniConstants.CIRC_OUT;
	
	/** The Constant CIRC_IN_OUT. */
	public static final String CIRC_IN_OUT = AniConstants.CIRC_IN_OUT;
	
	/** The Constant EXPO_IN. */
	public static final String EXPO_IN = AniConstants.EXPO_IN;
	
	/** The Constant EXPO_OUT. */
	public static final String EXPO_OUT = AniConstants.EXPO_OUT;
	
	/** The Constant EXPO_IN_OUT. */
	public static final String EXPO_IN_OUT = AniConstants.EXPO_IN_OUT;
	
	/** The Constant BACK_IN. */
	public static final String BACK_IN = AniConstants.BACK_IN;
	
	/** The Constant BACK_OUT. */
	public static final String BACK_OUT = AniConstants.BACK_OUT;
	
	/** The Constant BACK_IN_OUT. */
	public static final String BACK_IN_OUT = AniConstants.BACK_IN_OUT;
	
	/** The Constant BOUNCE_IN. */
	public static final String BOUNCE_IN = AniConstants.BOUNCE_IN;
	
	/** The Constant BOUNCE_OUT. */
	public static final String BOUNCE_OUT = AniConstants.BOUNCE_OUT;
	
	/** The Constant BOUNCE_IN_OUT. */
	public static final String BOUNCE_IN_OUT = AniConstants.BOUNCE_IN_OUT;
	
	/** The Constant ELASTIC_IN. */
	public static final String ELASTIC_IN = AniConstants.ELASTIC_IN;
	
	/** The Constant ELASTIC_OUT. */
	public static final String ELASTIC_OUT = AniConstants.ELASTIC_OUT;
	
	/** The Constant ELASTIC_IN_OUT. */
	public static final String ELASTIC_IN_OUT = AniConstants.ELASTIC_IN_OUT;
	
	/**
	 * Instantiates a new ani animation.
	 *
	 * @param from the from
	 * @param to the to
	 * @param theDuration the the duration
	 * @param animationTarget the animation target
	 */
	public AniAnimation(float from, float to, int theDuration, Object animationTarget) {
		this(from, to, theDuration, 0, 1, Ani.getDefaultEasing(), animationTarget);
	}
	
	/**
	 * Instantiates a new ani animation.
	 *
	 * @param from the from
	 * @param to the to
	 * @param theDuration the the duration
	 * @param repeatCount the repeat count
	 * @param animationTarget the animation target
	 */
	public AniAnimation(float from, float to, int theDuration, int repeatCount, Object animationTarget) {
		this(from, to, theDuration, 0, repeatCount, Ani.getDefaultEasing(), animationTarget);
	}
	
	/**
	 * Instantiates a new ani animation.
	 *
	 * @param from the from
	 * @param to the to
	 * @param theDuration the the duration
	 * @param theEasing the the easing
	 * @param animationTarget the animation target
	 */
	public AniAnimation(float from, float to, int theDuration, String theEasing, Object animationTarget) {
		this(from, to, theDuration, 0, 1, theEasing, animationTarget);
	}

	/**
	 * Instantiates a new ani animation.
	 *
	 * @param from the from
	 * @param to the to
	 * @param theDuration the the duration
	 * @param repeatCount the repeat count
	 * @param theEasing the the easing
	 * @param animationTarget the animation target
	 */
	public AniAnimation(float from, float to, int theDuration, int repeatCount, String theEasing, Object animationTarget) {
		this(from, to, theDuration, 0, repeatCount, theEasing, animationTarget);
	}
	
	/**
	 * Instantiates a new ani animation.
	 *
	 * @param from the from
	 * @param to the to
	 * @param theDuration the the duration
	 * @param theDelay the the delay
	 * @param repeatCount the repeat count
	 * @param theEasing the the easing
	 * @param animationTarget the animation target
	 */
	public AniAnimation(float from, float to, int theDuration, int theDelay, int repeatCount, String theEasing, Object animationTarget) {
		super(animationTarget);
//		this.setInterpolator(this);
		this.ani = new AniAdapter(this, from, to, (float)theDuration/1000f, (float)theDelay/1000f, "x", theEasing, animationTarget);
		this.ani.setBegin(from);
		if (repeatCount == -1){
			this.ani.repeat();
		}else{
			this.ani.repeat(repeatCount);	
		}
	}
	
	
	/**
	 * Inits the.
	 *
	 * @param pApplet the applet
	 */
	public static void init(AbstractMTApplication pApplet){
		Ani.init(pApplet);
	}
	

	/**
	 * Gets the ani.
	 *
	 * @return the ani
	 */
	protected AniAdapter getAni(){
		return this.ani;
	}

	
	/* (non-Javadoc)
	 * @see org.mt4j.util.animation.IAnimation#start()
	 */
	public void start(){
		this.ani.start();
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.animation.IAnimation#stop()
	 */
	public void stop(){
		this.ani.end();
	}
	
	/**
	 * Reverse.
	 */
	public void reverse(){
		this.getAni().reverse();
	}
	
	/**
	 * Sets the repeat.
	 *
	 * @param repeatCount the new repeat
	 */
	public void setRepeat(int repeatCount){
		this.getAni().noRepeat();
		if (repeatCount == -1){
			this.getAni().repeat();
		}else{
			this.getAni().repeat(repeatCount);	
		}
	}
	
	/**
	 * Gets the repeat.
	 *
	 * @return the repeat
	 */
	public int getRepeat() {
		return this.getAni().getRepeatCount();
	}
	
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.animation.AbstractAnimation#fireAnimationEvent(org.mt4j.util.animation.AnimationEvent)
	 */
	public void fireAnimationEvent(AnimationEvent ae){
		super.fireAnimationEvent(ae);
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.util.animation.IAnimation#getDelta()
	 */
	public float getDelta() {
		return this.getAni().getCurrentStepDelta();
	}

	/* (non-Javadoc)
	 * @see org.mt4j.util.animation.IAnimation#getValue()
	 */
	public float getValue() {
		return this.getAni().getPosition();
	}
	
	
	/**
	 * Restart.
	 */
	public void restart() {
		if (this.getAni().isPlaying())
			this.getAni().end();
		this.getAni().start();
	}


	/**
	 * Sets the trigger time.
	 *
	 * @param triggerTime the new trigger time
	 */
	public void setTriggerTime(long triggerTime) {
		this.getAni().setDelay((float)triggerTime/1000f);
	}

	/**
	 * Gets the trigger time.
	 *
	 * @return the trigger time
	 */
	public long getTriggerTime() {
		return Math.round(this.getAni().getDelay() * 1000);
	}
	
	
	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return this.getAni().getPosition() == this.getAni().getEnd();
	}

	

}
