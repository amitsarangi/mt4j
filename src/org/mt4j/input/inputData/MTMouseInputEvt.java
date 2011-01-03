package org.mt4j.input.inputData;

import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputSources.AbstractInputSource;

/**
 * The Class MTMouseInputEvt.
 */
public class MTMouseInputEvt extends MTFingerInputEvt {

	/** The mouse button. */
	private int mouseButton;

	/**
	 * Instantiates a new mT mouse input evt.
	 *
	 * @param source the source
	 * @param target the target
	 * @param mouseButton the mouse button
	 * @param positionX the position x
	 * @param positionY the position y
	 * @param id the id
	 * @param m the m
	 */
	public MTMouseInputEvt(AbstractInputSource source, IMTComponent3D target, int mouseButton, float positionX, float positionY, int id, InputCursor m) {
		super(source, target, positionX, positionY, id, m);
		this.mouseButton = mouseButton;
	}

	/**
	 * Instantiates a new mT mouse input evt.
	 *
	 * @param source the source
	 * @param mouseButton the mouse button
	 * @param positionX the position x
	 * @param positionY the position y
	 * @param id the id
	 * @param m the m
	 */
	public MTMouseInputEvt(AbstractInputSource source, int mouseButton, float positionX, float positionY, int id, InputCursor m) {
		super(source, positionX, positionY, id, m);
		this.mouseButton = mouseButton;
	}
	
	/**
	 * Gets the button.
	 *
	 * @return the button
	 */
	public int getButton(){
		return this.mouseButton;
	}

}
