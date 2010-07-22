package org.mt4j.css.style;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.css.util.CSSFontManager;
import org.mt4j.css.util.CSSKeywords.CSSBorderStyle;
import org.mt4j.css.util.CSSKeywords.CSSSelectorType;
import org.mt4j.util.MTColor;

import processing.core.PImage;

// TODO: Auto-generated Javadoc
/**
 * The Class CSSStyle.
 */
public class CSSStyle {
	
	/** The MTApplication. */
	MTApplication app;
	
	/** The uri of the style file */
	String uri = "";
	
	/** Has the uri been modified */
	boolean modifiedUri = false;
	

	/** The selector. */
	CSSSelector selector = null;
	
	/** Has the selector been modified. */
	boolean modifiedSelector = false;
	//Colours
	
	/** The background color. */
	MTColor backgroundColor = new MTColor(0,0,0,0);
	
	/** The color. */
	MTColor color= new MTColor(255,255,255,255);
	
	/** The border color. */
	MTColor borderColor = new MTColor(255,255,255,255);
	
	/** Have the colors been modified. */
	boolean modifiedBackgroundColor= false, modifiedColor = false, modifiedBorderColor = false;
	
	//Background Image
	
	/** The background image. */
	PImage backgroundImage = null;
	
	/** The position of the background image */
	CSSBackgroundPosition backgroundPosition = new CSSBackgroundPosition();
	
	/** The background repeat options */
	BackgroundRepeat backgroundRepeat = BackgroundRepeat.REPEAT;
	
	/**
	 * The Enum BackgroundRepeat.
	 */
	public enum BackgroundRepeat {
		
		/** The XREPEAT. */
		XREPEAT, 
	 /** The YREPEAT. */
	 YREPEAT, 
	 /** The REPEAT. */
	 REPEAT, 
	 /** The NONE. */
	 NONE;
		}
	
	
	/** Have the background image options been modified. */
	boolean modifiedBackgroundImage = false, modifiedBackgroundPosition = false, modifiedBackgroundRepeat = false; 
	
	//Border
	/** The border style. */
	CSSBorderStyle borderStyle = CSSBorderStyle.NONE;
	
	/** The font. */
	IFont font = null;
	
	/** The cssfont. */
	CSSFont cssfont = new CSSFont();
	
	/** Have the font options been modified. */
	boolean modifiedBorderStyle = false, modifiedFont = false, modifiedCssfont = false;
	
	//Sizes
	/** The width. */
	float width = 0;
	
	/** Is the width relative */
	boolean widthPercentage = false;
	
	/** The height. */
	float height = 0;
	
	/** Is the height relative. */
	boolean heightPercentage = false;
	
	/** The depth. */
	float depth = 0;
	
	/** Have the width/height been modified */
	boolean modifiedWidth = false, modifiedHeight = false, modifiedDepth = false;
	
	/** Have the relative options been modified. */
	boolean modifiedWidthPercentage =false, modifiedHeightPercentage = false;
	
	
	/** The x-position. */
	float xpos = 0;
	
	/** The y-position. */
	float ypos = 0;
	
	/** The z-position. */
	float zpos = 0;
	
	/** Have x/y/z-pos been modified? */
	boolean modifiedXpos = false, modifiedYpos = false, modifiedZpos = false;
	
	/** The border width. */
	float borderWidth = 0;
	
	/** The padding width. */
	float paddingWidth = 0; // Graphics and Text only
	
	/** Have the border/padding width been modified. */
	boolean modifiedBorderWidth = false, modifiedPaddingWidth = false;
	
	/** The font size. */
	int fontSize = 16;
	
	/** Has the font size been modified */
	boolean modifiedFontSize = false;
	
	//General Properties
	/** The visibility. */
	boolean visibility = true;
	
	/** The zIndex. */
	float zIndex = 0;
	
	/** Has the visibility/zIndex been modified.*/
	boolean modifiedVisibility = false, modifiedZIndex = false;
	
	/**
	 * Instantiates a new CSS style.
	 *
	 * @param app the MTApplication
	 */
	public CSSStyle(MTApplication app) {
		this.selector = new CSSSelector("Universal", CSSSelectorType.UNIVERSAL);
		this.app = app;
	}
	
	/**
	 * Instantiates a new CSS style using a selector
	 *
	 * @param selector the selector
	 * @param app the MTApplication
	 */
	public CSSStyle(CSSSelector selector,MTApplication app) {
		super();
		this.selector = selector;
		this.modifiedSelector = true;
		this.app = app;
	}
	

	/**
	 * Gets the selector.
	 *
	 * @return the selector
	 */
	public CSSSelector getSelector() {
		return selector;
	}

	/**
	 * Sets the selector.
	 *
	 * @param selector the new selector
	 */
	public void setSelector(CSSSelector selector) {
		this.selector = selector;
		this.modifiedSelector = true;
	}

	/**
	 * Gets the cssfont.
	 *
	 * @return the cssfont
	 */
	public CSSFont getCssfont() {
		return cssfont;
	}

	/**
	 * Sets the cssfont.
	 *
	 * @param cssfont the new cssfont
	 */
	public void setCssfont(CSSFont cssfont) {
		this.cssfont = cssfont;
		this.modifiedCssfont = true;
	}


	/**
	 * Gets the background color.
	 *
	 * @return the background color
	 */
	public MTColor getBackgroundColor() {
		return backgroundColor;
	}
	
	/**
	 * Sets the background color.
	 *
	 * @param backgroundColor the new background color
	 */
	public void setBackgroundColor(MTColor backgroundColor) {
		this.backgroundColor = backgroundColor;
		this.modifiedBackgroundColor = true;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public MTColor getColor() {
		return color;
	}
	
	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(MTColor color) {
		this.color = color;
		this.modifiedColor = true;
	}
	
	/**
	 * Gets the border color.
	 *
	 * @return the border color
	 */
	public MTColor getBorderColor() {
		return borderColor;
	}
	
	/**
	 * Sets the border color.
	 *
	 * @param borderColor the new border color
	 */
	public void setBorderColor(MTColor borderColor) {
		this.borderColor = borderColor;
		this.modifiedBorderColor = true;
	}
	
	/**
	 * Gets the background image.
	 *
	 * @return the background image
	 */
	public PImage getBackgroundImage() {
		return backgroundImage;
	}
	
	/**
	 * Sets the background image.
	 *
	 * @param backgroundImage the new background image
	 */
	public void setBackgroundImage(PImage backgroundImage) {
		this.backgroundImage = backgroundImage;
		this.modifiedBackgroundImage = true;
	}
	
	/**
	 * Gets the background position.
	 *
	 * @return the background position
	 */
	public CSSBackgroundPosition getBackgroundPosition() {
		return backgroundPosition;
	}
	
	/**
	 * Sets the background position.
	 *
	 * @param backgroundPosition the new background position
	 */
	public void setBackgroundPosition(CSSBackgroundPosition backgroundPosition) {
		this.backgroundPosition = backgroundPosition;
		this.modifiedBackgroundPosition = true;
	}
	
	/**
	 * Gets the background repeat.
	 *
	 * @return the background repeat
	 */
	public BackgroundRepeat getBackgroundRepeat() {
		return backgroundRepeat;
	}
	
	/**
	 * Sets the background repeat.
	 *
	 * @param backgroundRepeat the new background repeat
	 */
	public void setBackgroundRepeat(BackgroundRepeat backgroundRepeat) {
		this.backgroundRepeat = backgroundRepeat;
		this.modifiedBackgroundRepeat = true;
	}
	
	/**
	 * Gets the border style.
	 *
	 * @return the border style
	 */
	public CSSBorderStyle getBorderStyle() {
		return borderStyle;
	}
	
	/**
	 * Gets the border style pattern.
	 *
	 * @return the border style pattern (as short, for OpenGL)
	 */
	public short getBorderStylePattern() {
		switch (borderStyle) {
		case SOLID:
		case NONE:
			return (short) 0;
		case DOTTED:
			return (short) 0xCCCC;
		case DASHED:
			return (short) 0x00FF;
		case HIDDEN:
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Sets the border style.
	 *
	 * @param borderStyle the new border style
	 */
	public void setBorderStyle(CSSBorderStyle borderStyle) {
		this.borderStyle = borderStyle;
		this.modifiedBorderStyle = true;
	}
	
	/**
	 * Gets the font.
	 *
	 * @return the font
	 */
	public IFont getFont() {
		CSSFontManager fm = new CSSFontManager(app);
		if (font == null) {
			font = fm.selectFont(getCssfont());
		} else {
			if (cssfont.isModified()) {
				font = fm.selectFont(getCssfont());
				cssfont.setModified(false);
			} else {
				//Do Nothing
			}
		}
	
		return font;
	}
	
	/**
	 * Sets the font.
	 *
	 * @param font the new font
	 */
	public void setFont(IFont font) {
		this.font = font;
		this.modifiedFont = true;
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(float width) {
		this.width = width;
		this.modifiedWidth = true;
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(float height) {
		this.height = height;
		this.modifiedHeight = true;
	}
	
	/**
	 * Gets the depth.
	 *
	 * @return the depth
	 */
	public float getDepth() {
		return depth;
	}
	
	/**
	 * Sets the depth.
	 *
	 * @param depth the new depth
	 */
	public void setDepth(float depth) {
		this.depth = depth;
		this.modifiedDepth = true;
	}
	
	/**
	 * Gets the xpos.
	 *
	 * @return the xpos
	 */
	public float getXpos() {
		return xpos;
	}
	
	/**
	 * Sets the xpos.
	 *
	 * @param xpos the new xpos
	 */
	public void setXpos(float xpos) {
		this.xpos = xpos;
		this.modifiedXpos = true;
	}
	
	/**
	 * Gets the ypos.
	 *
	 * @return the ypos
	 */
	public float getYpos() {
		return ypos;
	}
	
	/**
	 * Sets the ypos.
	 *
	 * @param ypos the new ypos
	 */
	public void setYpos(float ypos) {
		this.ypos = ypos;
		this.modifiedYpos = true;
	}
	
	/**
	 * Gets the zpos.
	 *
	 * @return the zpos
	 */
	public float getZpos() {
		return zpos;
	}
	
	/**
	 * Sets the zpos.
	 *
	 * @param zpos the new zpos
	 */
	public void setZpos(float zpos) {
		this.zpos = zpos;
		this.modifiedZpos = true;
	}
	
	/**
	 * Gets the border width.
	 *
	 * @return the border width
	 */
	public float getBorderWidth() {
		return borderWidth;
	}
	
	/**
	 * Sets the border width.
	 *
	 * @param borderWidth the new border width
	 */
	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
		this.modifiedBorderWidth = true;
	}
	
	/**
	 * Gets the padding width.
	 *
	 * @return the padding width
	 */
	public float getPaddingWidth() {
		return paddingWidth;
	}
	
	/**
	 * Sets the padding width.
	 *
	 * @param paddingWidth the new padding width
	 */
	public void setPaddingWidth(float paddingWidth) {
		this.paddingWidth = paddingWidth;
		this.modifiedPaddingWidth = true;
	}
	
	/**
	 * Gets the font size.
	 *
	 * @return the font size
	 */
	public int getFontSize() {
		return fontSize;
	}
	
	/**
	 * Sets the font size.
	 *
	 * @param fontSize the new font size
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		this.modifiedFontSize = true;
	}
	
	/**
	 * Checks if is visible.
	 *
	 * @return true, if is visibility
	 */
	public boolean isVisibility() {
		return visibility;
	}
	
	/**
	 * Sets the visibility.
	 *
	 * @param visibility the new visibility
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
		this.modifiedVisibility = true;
	}
	
	/**
	 * Gets the z index.
	 *
	 * @return the z index
	 */
	public float getzIndex() {
		return zIndex;
	}
	
	/**
	 * Sets the z index.
	 *
	 * @param zIndex the new z index
	 */
	public void setzIndex(float zIndex) {
		this.zIndex = zIndex;
		this.modifiedZIndex = true;
	}

	/**
	 * Checks if the width is a percentage.
	 *
	 * @return true, if is width percentage
	 */
	public boolean isWidthPercentage() {
		return widthPercentage;
	}

	/**
	 * Sets if the width is a percentage.
	 *
	 * @param widthPercentage the new width percentage
	 */
	public void setWidthPercentage(boolean widthPercentage) {
		this.widthPercentage = widthPercentage;
		this.modifiedWidthPercentage = true;
	}

	/**
	 * Checks if the height is a percentage.
	 *
	 * @return true, if is height percentage
	 */
	public boolean isHeightPercentage() {
		return heightPercentage;
	}

	/**
	 * Sets if the height is a percentage.
	 *
	 * @param heightPercentage the new height percentage
	 */
	public void setHeightPercentage(boolean heightPercentage) {
		this.heightPercentage = heightPercentage;
		this.modifiedHeightPercentage = true;
	}






	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		result = prime * result
				+ ((backgroundColor == null) ? 0 : backgroundColor.hashCode());
		result = prime * result
				+ ((backgroundImage == null) ? 0 : backgroundImage.hashCode());
		result = prime
				* result
				+ ((backgroundPosition == null) ? 0 : backgroundPosition
						.hashCode());
		result = prime
				* result
				+ ((backgroundRepeat == null) ? 0 : backgroundRepeat.hashCode());
		result = prime * result
				+ ((borderColor == null) ? 0 : borderColor.hashCode());
		result = prime * result
				+ ((borderStyle == null) ? 0 : borderStyle.hashCode());
		result = prime * result + Float.floatToIntBits(borderWidth);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((cssfont == null) ? 0 : cssfont.hashCode());
		result = prime * result + Float.floatToIntBits(depth);
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + fontSize;
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + (heightPercentage ? 1231 : 1237);
		result = prime * result + (modifiedBackgroundColor ? 1231 : 1237);
		result = prime * result + (modifiedBackgroundImage ? 1231 : 1237);
		result = prime * result + (modifiedBackgroundPosition ? 1231 : 1237);
		result = prime * result + (modifiedBackgroundRepeat ? 1231 : 1237);
		result = prime * result + (modifiedBorderColor ? 1231 : 1237);
		result = prime * result + (modifiedBorderStyle ? 1231 : 1237);
		result = prime * result + (modifiedBorderWidth ? 1231 : 1237);
		result = prime * result + (modifiedColor ? 1231 : 1237);
		result = prime * result + (modifiedCssfont ? 1231 : 1237);
		result = prime * result + (modifiedDepth ? 1231 : 1237);
		result = prime * result + (modifiedFont ? 1231 : 1237);
		result = prime * result + (modifiedFontSize ? 1231 : 1237);
		result = prime * result + (modifiedHeight ? 1231 : 1237);
		result = prime * result + (modifiedHeightPercentage ? 1231 : 1237);
		result = prime * result + (modifiedPaddingWidth ? 1231 : 1237);
		result = prime * result + (modifiedSelector ? 1231 : 1237);
		result = prime * result + (modifiedUri ? 1231 : 1237);
		result = prime * result + (modifiedVisibility ? 1231 : 1237);
		result = prime * result + (modifiedWidth ? 1231 : 1237);
		result = prime * result + (modifiedWidthPercentage ? 1231 : 1237);
		result = prime * result + (modifiedXpos ? 1231 : 1237);
		result = prime * result + (modifiedYpos ? 1231 : 1237);
		result = prime * result + (modifiedZIndex ? 1231 : 1237);
		result = prime * result + (modifiedZpos ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(paddingWidth);
		result = prime * result
				+ ((selector == null) ? 0 : selector.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		result = prime * result + (visibility ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(width);
		result = prime * result + (widthPercentage ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(xpos);
		result = prime * result + Float.floatToIntBits(ypos);
		result = prime * result + Float.floatToIntBits(zIndex);
		result = prime * result + Float.floatToIntBits(zpos);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CSSStyle other = (CSSStyle) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		if (backgroundColor == null) {
			if (other.backgroundColor != null)
				return false;
		} else if (!backgroundColor.equals(other.backgroundColor))
			return false;
		if (backgroundImage == null) {
			if (other.backgroundImage != null)
				return false;
		} else if (!backgroundImage.equals(other.backgroundImage))
			return false;
		if (backgroundPosition == null) {
			if (other.backgroundPosition != null)
				return false;
		} else if (!backgroundPosition.equals(other.backgroundPosition))
			return false;
		if (backgroundRepeat != other.backgroundRepeat)
			return false;
		if (borderColor == null) {
			if (other.borderColor != null)
				return false;
		} else if (!borderColor.equals(other.borderColor))
			return false;
		if (borderStyle != other.borderStyle)
			return false;
		if (Float.floatToIntBits(borderWidth) != Float
				.floatToIntBits(other.borderWidth))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (cssfont == null) {
			if (other.cssfont != null)
				return false;
		} else if (!cssfont.equals(other.cssfont))
			return false;
		if (Float.floatToIntBits(depth) != Float.floatToIntBits(other.depth))
			return false;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
			return false;
		if (fontSize != other.fontSize)
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (heightPercentage != other.heightPercentage)
			return false;
		if (modifiedBackgroundColor != other.modifiedBackgroundColor)
			return false;
		if (modifiedBackgroundImage != other.modifiedBackgroundImage)
			return false;
		if (modifiedBackgroundPosition != other.modifiedBackgroundPosition)
			return false;
		if (modifiedBackgroundRepeat != other.modifiedBackgroundRepeat)
			return false;
		if (modifiedBorderColor != other.modifiedBorderColor)
			return false;
		if (modifiedBorderStyle != other.modifiedBorderStyle)
			return false;
		if (modifiedBorderWidth != other.modifiedBorderWidth)
			return false;
		if (modifiedColor != other.modifiedColor)
			return false;
		if (modifiedCssfont != other.modifiedCssfont)
			return false;
		if (modifiedDepth != other.modifiedDepth)
			return false;
		if (modifiedFont != other.modifiedFont)
			return false;
		if (modifiedFontSize != other.modifiedFontSize)
			return false;
		if (modifiedHeight != other.modifiedHeight)
			return false;
		if (modifiedHeightPercentage != other.modifiedHeightPercentage)
			return false;
		if (modifiedPaddingWidth != other.modifiedPaddingWidth)
			return false;
		if (modifiedSelector != other.modifiedSelector)
			return false;
		if (modifiedUri != other.modifiedUri)
			return false;
		if (modifiedVisibility != other.modifiedVisibility)
			return false;
		if (modifiedWidth != other.modifiedWidth)
			return false;
		if (modifiedWidthPercentage != other.modifiedWidthPercentage)
			return false;
		if (modifiedXpos != other.modifiedXpos)
			return false;
		if (modifiedYpos != other.modifiedYpos)
			return false;
		if (modifiedZIndex != other.modifiedZIndex)
			return false;
		if (modifiedZpos != other.modifiedZpos)
			return false;
		if (Float.floatToIntBits(paddingWidth) != Float
				.floatToIntBits(other.paddingWidth))
			return false;
		if (selector == null) {
			if (other.selector != null)
				return false;
		} else if (!selector.equals(other.selector))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		if (visibility != other.visibility)
			return false;
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width))
			return false;
		if (widthPercentage != other.widthPercentage)
			return false;
		if (Float.floatToIntBits(xpos) != Float.floatToIntBits(other.xpos))
			return false;
		if (Float.floatToIntBits(ypos) != Float.floatToIntBits(other.ypos))
			return false;
		if (Float.floatToIntBits(zIndex) != Float.floatToIntBits(other.zIndex))
			return false;
		if (Float.floatToIntBits(zpos) != Float.floatToIntBits(other.zpos))
			return false;
		return true;
	}

	/**
	 * Gets the uri.
	 *
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the uri.
	 *
	 * @param uri the new uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Checks if is modified uri.
	 *
	 * @return true, if is modified uri
	 */
	public boolean isModifiedUri() {
		return modifiedUri;
	}

	/**
	 * Sets, if the field has been modified: uri.
	 *
	 * @param modifiedUri the new modified uri
	 */
	public void setModifiedUri(boolean modifiedUri) {
		this.modifiedUri = modifiedUri;
	}

	/**
	 * Checks if is modified selector.
	 *
	 * @return true, if is modified selector
	 */
	public boolean isModifiedSelector() {
		return modifiedSelector;
	}

	/**
	 * Sets, if the field has been modified: selector.
	 *
	 * @param modifiedSelector the new modified selector
	 */
	public void setModifiedSelector(boolean modifiedSelector) {
		this.modifiedSelector = modifiedSelector;
	}

	/**
	 * Checks if is modified background color.
	 *
	 * @return true, if is modified background color
	 */
	public boolean isModifiedBackgroundColor() {
		return modifiedBackgroundColor;
	}

	/**
	 * Sets, if the field has been modified: background color.
	 *
	 * @param modifiedBackgroundColor the new modified background color
	 */
	public void setModifiedBackgroundColor(boolean modifiedBackgroundColor) {
		this.modifiedBackgroundColor = modifiedBackgroundColor;
	}

	/**
	 * Checks if is modified color.
	 *
	 * @return true, if is modified color
	 */
	public boolean isModifiedColor() {
		return modifiedColor;
	}

	/**
	 * Sets, if the field has been modified: color.
	 *
	 * @param modifiedColor the new modified color
	 */
	public void setModifiedColor(boolean modifiedColor) {
		this.modifiedColor = modifiedColor;
	}

	/**
	 * Checks if is modified border color.
	 *
	 * @return true, if is modified border color
	 */
	public boolean isModifiedBorderColor() {
		return modifiedBorderColor;
	}

	/**
	 * Sets, if the field has been modified: border color.
	 *
	 * @param modifiedBorderColor the new modified border color
	 */
	public void setModifiedBorderColor(boolean modifiedBorderColor) {
		this.modifiedBorderColor = modifiedBorderColor;
	}

	/**
	 * Checks if is modified background image.
	 *
	 * @return true, if is modified background image
	 */
	public boolean isModifiedBackgroundImage() {
		return modifiedBackgroundImage;
	}

	/**
	 * Sets, if the field has been modified: background image.
	 *
	 * @param modifiedBackgroundImage the new modified background image
	 */
	public void setModifiedBackgroundImage(boolean modifiedBackgroundImage) {
		this.modifiedBackgroundImage = modifiedBackgroundImage;
	}

	/**
	 * Checks if is modified background position.
	 *
	 * @return true, if is modified background position
	 */
	public boolean isModifiedBackgroundPosition() {
		return modifiedBackgroundPosition;
	}

	/**
	 * Sets, if the field has been modified: background position.
	 *
	 * @param modifiedBackgroundPosition the new modified background position
	 */
	public void setModifiedBackgroundPosition(boolean modifiedBackgroundPosition) {
		this.modifiedBackgroundPosition = modifiedBackgroundPosition;
	}

	/**
	 * Checks if is modified background repeat.
	 *
	 * @return true, if is modified background repeat
	 */
	public boolean isModifiedBackgroundRepeat() {
		return modifiedBackgroundRepeat;
	}

	/**
	 * Sets, if the field has been modified: background repeat.
	 *
	 * @param modifiedBackgroundRepeat the new modified background repeat
	 */
	public void setModifiedBackgroundRepeat(boolean modifiedBackgroundRepeat) {
		this.modifiedBackgroundRepeat = modifiedBackgroundRepeat;
	}

	/**
	 * Checks if is modified border style.
	 *
	 * @return true, if is modified border style
	 */
	public boolean isModifiedBorderStyle() {
		return modifiedBorderStyle;
	}

	/**
	 * Sets, if the field has been modified: border style.
	 *
	 * @param modifiedBorderStyle the new modified border style
	 */
	public void setModifiedBorderStyle(boolean modifiedBorderStyle) {
		this.modifiedBorderStyle = modifiedBorderStyle;
	}

	/**
	 * Checks if is modified font.
	 *
	 * @return true, if is modified font
	 */
	public boolean isModifiedFont() {
		return modifiedFont;
	}

	/**
	 * Sets, if the field has been modified: font.
	 *
	 * @param modifiedFont the new modified font
	 */
	public void setModifiedFont(boolean modifiedFont) {
		this.modifiedFont = modifiedFont;
	}

	/**
	 * Checks if is modified cssfont.
	 *
	 * @return true, if is modified cssfont
	 */
	public boolean isModifiedCssfont() {
		return modifiedCssfont;
	}

	/**
	 * Sets, if the field has been modified: cssfont.
	 *
	 * @param modifiedCssfont the new modified cssfont
	 */
	public void setModifiedCssfont(boolean modifiedCssfont) {
		this.modifiedCssfont = modifiedCssfont;
	}

	/**
	 * Checks if is modified width.
	 *
	 * @return true, if is modified width
	 */
	public boolean isModifiedWidth() {
		return modifiedWidth;
	}

	/**
	 * Sets, if the field has been modified: width.
	 *
	 * @param modifiedWidth the new modified width
	 */
	public void setModifiedWidth(boolean modifiedWidth) {
		this.modifiedWidth = modifiedWidth;
	}

	/**
	 * Checks if is modified height.
	 *
	 * @return true, if is modified height
	 */
	public boolean isModifiedHeight() {
		return modifiedHeight;
	}

	/**
	 * Sets, if the field has been modified: height.
	 *
	 * @param modifiedHeight the new modified height
	 */
	public void setModifiedHeight(boolean modifiedHeight) {
		this.modifiedHeight = modifiedHeight;
	}

	/**
	 * Checks if is modified depth.
	 *
	 * @return true, if is modified depth
	 */
	public boolean isModifiedDepth() {
		return modifiedDepth;
	}

	/**
	 * Sets, if the field has been modified: depth.
	 *
	 * @param modifiedDepth the new modified depth
	 */
	public void setModifiedDepth(boolean modifiedDepth) {
		this.modifiedDepth = modifiedDepth;
	}

	/**
	 * Checks if is modified width percentage.
	 *
	 * @return true, if is modified width percentage
	 */
	public boolean isModifiedWidthPercentage() {
		return modifiedWidthPercentage;
	}

	/**
	 * Sets, if the field has been modified: width percentage.
	 *
	 * @param modifiedWidthPercentage the new modified width percentage
	 */
	public void setModifiedWidthPercentage(boolean modifiedWidthPercentage) {
		this.modifiedWidthPercentage = modifiedWidthPercentage;
	}

	/**
	 * Checks if is modified height percentage.
	 *
	 * @return true, if is modified height percentage
	 */
	public boolean isModifiedHeightPercentage() {
		return modifiedHeightPercentage;
	}

	/**
	 * Sets, if the field has been modified: height percentage.
	 *
	 * @param modifiedHeightPercentage the new modified height percentage
	 */
	public void setModifiedHeightPercentage(boolean modifiedHeightPercentage) {
		this.modifiedHeightPercentage = modifiedHeightPercentage;
	}

	/**
	 * Checks if is modified xpos.
	 *
	 * @return true, if is modified xpos
	 */
	public boolean isModifiedXpos() {
		return modifiedXpos;
	}

	/**
	 * Sets, if the field has been modified: xpos.
	 *
	 * @param modifiedXpos the new modified xpos
	 */
	public void setModifiedXpos(boolean modifiedXpos) {
		this.modifiedXpos = modifiedXpos;
	}

	/**
	 * Checks if is modified ypos.
	 *
	 * @return true, if is modified ypos
	 */
	public boolean isModifiedYpos() {
		return modifiedYpos;
	}

	/**
	 * Sets, if the field has been modified: ypos.
	 *
	 * @param modifiedYpos the new modified ypos
	 */
	public void setModifiedYpos(boolean modifiedYpos) {
		this.modifiedYpos = modifiedYpos;
	}

	/**
	 * Checks if is modified zpos.
	 *
	 * @return true, if is modified zpos
	 */
	public boolean isModifiedZpos() {
		return modifiedZpos;
	}

	/**
	 * Sets, if the field has been modified: zpos.
	 *
	 * @param modifiedZpos the new modified zpos
	 */
	public void setModifiedZpos(boolean modifiedZpos) {
		this.modifiedZpos = modifiedZpos;
	}

	/**
	 * Checks if is modified border width.
	 *
	 * @return true, if is modified border width
	 */
	public boolean isModifiedBorderWidth() {
		return modifiedBorderWidth;
	}

	/**
	 * Sets, if the field has been modified: border width.
	 *
	 * @param modifiedBorderWidth the new modified border width
	 */
	public void setModifiedBorderWidth(boolean modifiedBorderWidth) {
		this.modifiedBorderWidth = modifiedBorderWidth;
	}

	/**
	 * Checks if is modified padding width.
	 *
	 * @return true, if is modified padding width
	 */
	public boolean isModifiedPaddingWidth() {
		return modifiedPaddingWidth;
	}

	/**
	 * Sets, if the field has been modified: padding width.
	 *
	 * @param modifiedPaddingWidth the new modified padding width
	 */
	public void setModifiedPaddingWidth(boolean modifiedPaddingWidth) {
		this.modifiedPaddingWidth = modifiedPaddingWidth;
	}

	/**
	 * Checks if is modified font size.
	 *
	 * @return true, if is modified font size
	 */
	public boolean isModifiedFontSize() {
		return modifiedFontSize;
	}

	/**
	 * Sets, if the field has been modified: font size.
	 *
	 * @param modifiedFontSize the new modified font size
	 */
	public void setModifiedFontSize(boolean modifiedFontSize) {
		this.modifiedFontSize = modifiedFontSize;
	}

	/**
	 * Checks if is modified visibility.
	 *
	 * @return true, if is modified visibility
	 */
	public boolean isModifiedVisibility() {
		return modifiedVisibility;
	}

	/**
	 * Sets, if the field has been modified: visibility.
	 *
	 * @param modifiedVisibility the new modified visibility
	 */
	public void setModifiedVisibility(boolean modifiedVisibility) {
		this.modifiedVisibility = modifiedVisibility;
	}

	/**
	 * Checks if is modified z index.
	 *
	 * @return true, if is modified z index
	 */
	public boolean isModifiedZIndex() {
		return modifiedZIndex;
	}

	/**
	 * Sets, if the field has been modified: z index.
	 *
	 * @param modifiedZIndex the new modified z index
	 */
	public void setModifiedZIndex(boolean modifiedZIndex) {
		this.modifiedZIndex = modifiedZIndex;
	}
	
	/**
	 * Merges two style sheets
	 *
	 * @param s the style sheet to be added
	 */
	public void addStyleSheet(CSSStyle s) {
		CSSStyle v = this;
		if (s.isModifiedBackgroundColor()) {
			v.setBackgroundColor(s.getBackgroundColor());
		}
		if (s.isModifiedBackgroundImage()) {
			v.setBackgroundImage(s.getBackgroundImage());
			v.setBackgroundPosition(s.getBackgroundPosition());
			v.setBackgroundRepeat(s.getBackgroundRepeat());
		}
		if (s.isModifiedBackgroundPosition()) {
			v.setBackgroundPosition(s.getBackgroundPosition());
		}
		if (s.isModifiedBackgroundRepeat()) {
			v.setBackgroundRepeat(s.getBackgroundRepeat());
		}
		if (s.isModifiedBorderColor()) {
			v.setBorderColor(s.getBorderColor());
		}
		if (s.isModifiedBorderStyle()) {
			v.setBorderStyle(s.getBorderStyle());
		}
		if (s.isModifiedBorderWidth()) {
			v.setBorderWidth(s.getBorderWidth());
		}
		if (s.isModifiedColor()) {
			v.setColor(s.getColor());
		}
		if (s.isModifiedCssfont()) {
			v.setCssfont(s.getCssfont());
		}
		if (s.isModifiedDepth()) {
			v.setDepth(s.getDepth());
		}
		//if (s.isModifiedFont()) {
		//	v.setFont(s.getFont());
		//}
		if (s.isModifiedFontSize()) {
			v.setFontSize(s.getFontSize());
		}
		if (s.isModifiedHeight()) {
			v.setHeight(s.getHeight());
			v.setHeightPercentage(s.isHeightPercentage());
		}
		if (s.isModifiedPaddingWidth()) {
			v.setPaddingWidth(s.getPaddingWidth());
		}
		if (s.isModifiedVisibility()) {
			v.setVisibility(s.isVisibility());
		}
		if (s.isModifiedWidth()) {
			v.setWidth(s.getWidth());
			v.setWidthPercentage(s.isWidthPercentage());
		}
		if (s.isModifiedXpos()) {
			v.setXpos(s.getXpos());
		}
		if (s.isModifiedYpos()) {
			v.setYpos(s.getYpos());
		}
		if (s.isModifiedZIndex()) {
			v.setzIndex(s.getzIndex());
		}
		if (s.isModifiedZpos()) {
			v.setZpos(s.getZpos());
		}
	}


	
	
}
