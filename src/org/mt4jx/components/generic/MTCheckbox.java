package org.mt4jx.components.generic;

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4jx.components.menus.MTHexagonMenu.TapListener;

import processing.core.PApplet;

/**
 * The Class MTCheckbox.
 */
public class MTCheckbox extends MTForm implements BooleanForm{

	/** The boolean value. */
	boolean booleanValue = false;;	
	
	/** The background color. */
	MTColor backgroundColor;
	
	/** The stroke color. */
	MTColor strokeColor;
	
	/**
	 * Instantiates a new MTCheckBox
	 *
	 * @param size the size of the side of the square
	 * @param pApplet the applet
	 */
	public MTCheckbox(float size, PApplet pApplet) {
		super(0, 0, size, size, pApplet, MTForm.BOOLEAN);
		
		//Is the Item CSSStyle? If no, take standard colors
		if (this.isCSSStyled() && this.getCssHelper().getVirtualStyleSheet().isModifiedBackgroundColor()) {
			this.backgroundColor = this.getCssHelper().getVirtualStyleSheet().getBackgroundColor();
			if (this.getCssHelper().getVirtualStyleSheet().isModifiedBorderColor()) strokeColor = this.getCssHelper().getVirtualStyleSheet().getBorderColor();
			else strokeColor = MTColor.WHITE;
		} else {
			this.backgroundColor = MTColor.YELLOW;
			this.strokeColor = MTColor.WHITE;
		}
		this.setFillColor(backgroundColor);
		this.setNoFill(true);
		this.setNoStroke(false);
		this.setStrokeWeight(2f);
		
		this.setGestureAllowance(TapProcessor.class, true);
		this.registerInputProcessor(new TapProcessor(pApplet));
		this.addGestureListener(TapProcessor.class, new BooleanTapListener());
	}

	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#getBooleanValue()
	 */
	@Override
	public boolean getBooleanValue() {
		return booleanValue;
	}

	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#getStringValue()
	 */
	@Override
	public String getStringValue() {
		return String.valueOf(this.getBooleanValue());
	}

	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#getNumericValue()
	 */
	@Override
	public float getNumericValue() {
		if (this.getBooleanValue() == true) return 1;
		else return 0;
	}

	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#setBooleanValue(boolean)
	 */
	@Override
	public void setBooleanValue(boolean value) {
		this.booleanValue = value;
		if (this.booleanValue == true) {
			this.setNoFill(false);
		} else {
			this.setNoFill(true);
		}
		
	}


}
