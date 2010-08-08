package org.mt4jx.components.generic;

import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PApplet;

/**
 * The Class MTOptionBox.
 */
public class MTOptionBox extends MTForm implements BooleanForm {
	
	/** The boolean value. */
	boolean booleanValue = false;
	
	/** The background color. */
	MTColor backgroundColor;
	
	/** The stroke color. */
	MTColor strokeColor;
	
	/** The option box. */
	MTEllipse optionBox;
	
	/** The group. */
	OptionGroup group;
	
	/**
	 * Instantiates a new MTOptionBox
	 *
	 * @param size the size of the ellipse
	 * @param app the PApplet
	 * @param group the OptionGroup
	 */
	public MTOptionBox(float size,
			PApplet app, OptionGroup group) {
		super(0, 0, size, size, app, MTForm.BOOLEAN);
		group.addOptionBox(this);
		this.setCssForceDisable(true);
		this.setNoStroke(true);
		this.setNoFill(true);
		this.group = group;
		
		optionBox = new MTEllipse(app, new Vector3D(size/2f,size/2f), size/2f, size/2f);
		optionBox.setCssForceDisable(true);
		this.addChild(optionBox);
		
		
		//Check if it's CSS styled
		if (this.getCssHelper() != null) {
			CSSStyle vss = this.getCssHelper().virtualStyleSheet;
			if (vss.isModifiedBorderColor()) optionBox.setStrokeColor(vss.getBorderColor());
			else optionBox.setStrokeColor(MTColor.WHITE);
			
			if (vss.isModifiedBackgroundColor()) optionBox.setFillColor(vss.getBackgroundColor());
			else optionBox.setFillColor(MTColor.YELLOW);
			
			if (vss.isModifiedBorderWidth()) optionBox.setStrokeWeight(vss.getBorderWidth());
			else optionBox.setStrokeWeight(2f);
			
			
		} else {
			//Else set default values
			optionBox.setStrokeColor(MTColor.WHITE);
			optionBox.setFillColor(MTColor.YELLOW);
			optionBox.setStrokeWeight(2f);
		}
		
		optionBox.setPickable(false);
		optionBox.setNoFill(true);
		
		this.setGestureAllowance(TapProcessor.class, true);
		this.registerInputProcessor(new TapProcessor(app));
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
			optionBox.setNoFill(false);
			if (group != null)
			group.setEnabled(this);
		} else {
			optionBox.setNoFill(true);
		}
		
	}
	
	/**
	 * Disable.
	 */
	public void disable() {
		this.booleanValue = false;
		optionBox.setNoFill(true);
	}

}
