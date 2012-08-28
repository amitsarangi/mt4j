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

import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.AnimationManager;

import de.looksgood.ani.Ani;

/**
 * The Class AniAdapter.
 */
public class AniAdapter extends Ani {
	
	/** The corresponding animation. */
	private AniAnimation correspondingAnimation;
	
	/** The animation target. */
	private Object animationTarget;
	
	/** The current step delta. */
	private float currentStepDelta;
	
	
	static{
		Ani.noAutostart();
	}
	
//	public AdaptedAni(Adapter theTarget, float from, float to, float theDuration, float theDelay,
//			String theFieldName, String theEasing,
//			String theCallback) {
//		super(theTarget, theDuration, theDelay, "x", to, theEasing,
//				theCallback);
//		
//		this.setBegin(from);
//		this.correspondingAnimation = theTarget;
//	}

	

	/**
 * Instantiates a new ani adapter.
 *
 * @param theTarget the the target
 * @param from the from
 * @param to the to
 * @param theDuration the the duration
 * @param theDelay the the delay
 * @param theFieldName the the field name
 * @param theEasing the the easing
 * @param animationTarget the animation target
 */
public AniAdapter(AniAnimation theTarget, float from, float to, float theDuration, float theDelay, String theFieldName, String theEasing, Object animationTarget) {
		super(theTarget, theDuration, theDelay, theFieldName, to, theEasing);
		this.setBegin(from);
		this.currentStepDelta = 0;
		
		this.correspondingAnimation = theTarget;
		this.animationTarget = animationTarget;
	}

	
	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#seek(float)
	 */
	@Override
	public void seek(float theValue) {
		if (theValue == 0.0f){
			this.currentStepDelta = 0.0f;
			this.position = getBegin(); //FIXME TEST to fix bug with currentStepDelta
		}
		super.seek(theValue);
	}
	
	
	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#dispatchOnStart()
	 */
	@Override
	protected void dispatchOnStart() {
		super.dispatchOnStart();
		correspondingAnimation.fireAnimationEvent(new AnimationEvent(this, AnimationEvent.ANIMATION_STARTED, correspondingAnimation, animationTarget));
	}
	
	
	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#updatePosition()
	 */
	@Override
	protected void updatePosition() {
		float lastPosition = this.getPosition();
		super.updatePosition();
		this.currentStepDelta = this.getPosition() - lastPosition;
//		if (this.currentStepDelta == -1.0f){
//			System.out.println();
//		}
		correspondingAnimation.fireAnimationEvent(new AnimationEvent(this, AnimationEvent.ANIMATION_UPDATED, correspondingAnimation, animationTarget));
	}
	
	
	
	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#dispatchOnEnd()
	 */
	@Override
	protected void dispatchOnEnd() {
		this.currentStepDelta = 0.0f; //Else we get the same delta as from the last step twice!
		super.dispatchOnEnd();
		correspondingAnimation.fireAnimationEvent(new AnimationEvent(this, AnimationEvent.ANIMATION_ENDED, correspondingAnimation, animationTarget));
	}
	
	
	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#start()
	 */
	@Override
	public void start() {
//		this.currentStepDelta = 0;
		AnimationManager.getInstance().registerAnimation(correspondingAnimation);
		super.start();
	}
	
	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#end()
	 */
	@Override
	public void end() {
		AnimationManager.getInstance().unregisterAnimation(correspondingAnimation);
		super.end();
//		this.currentStepDelta = 0;
	}
	
	
	/**
	 * Gets the current step delta.
	 *
	 * @return the current step delta
	 */
	public float getCurrentStepDelta(){
		return this.currentStepDelta;
	}
	

	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#repeat(int)
	 */
	@Override
	public void repeat(int theRepeatCount) {
		super.repeat(theRepeatCount);
	}


	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#setCallback(java.lang.String)
	 */
	@Override
	public void setCallback(String theCallback) {
		super.setCallback(theCallback);
	}

	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#setDelay(float)
	 */
	@Override
	public void setDelay(float theDurationDelay) {
		super.setDelay(theDurationDelay);
	}

	/* (non-Javadoc)
	 * @see de.looksgood.ani.AniCore#setDuration(float)
	 */
	@Override
	public void setDuration(float theDurationEasing) {
		super.setDuration(theDurationEasing);
	}


	
	
	
	

}
