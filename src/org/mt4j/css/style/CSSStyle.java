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

public class CSSStyle {
	
	MTApplication app;
	
	String uri = "";
	boolean modifiedUri = false;
	

	CSSSelector selector = null;
	boolean modifiedSelector = false;
	//Colours
	
	MTColor backgroundColor = new MTColor(0,0,0,0);
	MTColor color= new MTColor(255,255,255,255);
	MTColor borderColor = new MTColor(255,255,255,255);
	
	boolean modifiedBackgroundColor= false, modifiedColor = false, modifiedBorderColor = false;
	
	//Background Image
	
	PImage backgroundImage = null;
	float[] backgroundPosition = null;
	BackgroundRepeat backgroundRepeat = BackgroundRepeat.NONE;;
	
	public enum BackgroundRepeat {
		XREPEAT, YREPEAT, REPEAT, NONE;
	}
	
	
	boolean modifiedBackgroundImage = false, modifiedBackgroundPosition = false, modifiedBackgroundRepeat = false; 
	
	//Border
	CSSBorderStyle borderStyle = CSSBorderStyle.NONE;
	IFont font = null;
	CSSFont cssfont = new CSSFont();
	
	boolean modifiedBorderStyle = false, modifiedFont = false, modifiedCssfont = false;
	
	//Sizes
	float width = 0;
	boolean widthPercentage = false;
	float height = 0;
	boolean heightPercentage = false;
	float depth = 0;
	
	boolean modifiedWidth = false, modifiedHeight = false, modifiedDepth = false;
	boolean modifiedWidthPercentage =false, modifiedHeightPercentage = false;
	
	
	float xpos = 0;
	float ypos = 0;
	float zpos = 0;
	
	boolean modifiedXpos = false, modifiedYpos = false, modifiedZpos = false;
	
	float borderWidth = 0;
	float paddingWidth = 0; // Graphics and Text only
	
	boolean modifiedBorderWidth = false, modifiedPaddingWidth = false;
	
	int fontSize = 16;
	
	boolean modifiedFontSize = false;
	
	//General Properties
	boolean visibility = true;
	float zIndex = 0;
	
	boolean modifiedVisibility = false, modifiedZIndex = false;
	
	public CSSStyle(MTApplication app) {
		this.selector = new CSSSelector("Universal", CSSSelectorType.UNIVERSAL);
		this.app = app;
	}
	
	public CSSStyle(CSSSelector selector,MTApplication app) {
		super();
		this.selector = selector;
		this.modifiedSelector = true;
		this.app = app;
	}
	

	public CSSSelector getSelector() {
		return selector;
	}

	public void setSelector(CSSSelector selector) {
		this.selector = selector;
		this.modifiedSelector = true;
	}

	public CSSFont getCssfont() {
		return cssfont;
	}

	public void setCssfont(CSSFont cssfont) {
		this.cssfont = cssfont;
		this.modifiedCssfont = true;
	}


	public MTColor getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(MTColor backgroundColor) {
		this.backgroundColor = backgroundColor;
		this.modifiedBackgroundColor = true;
	}
	public MTColor getColor() {
		return color;
	}
	public void setColor(MTColor color) {
		this.color = color;
		this.modifiedColor = true;
	}
	public MTColor getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(MTColor borderColor) {
		this.borderColor = borderColor;
		this.modifiedBorderColor = true;
	}
	public PImage getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(PImage backgroundImage) {
		this.backgroundImage = backgroundImage;
		this.modifiedBackgroundImage = true;
	}
	public float[] getBackgroundPosition() {
		return backgroundPosition;
	}
	public void setBackgroundPosition(float[] backgroundPosition) {
		this.backgroundPosition = backgroundPosition;
		this.modifiedBackgroundPosition = true;
	}
	public BackgroundRepeat getBackgroundRepeat() {
		return backgroundRepeat;
	}
	public void setBackgroundRepeat(BackgroundRepeat backgroundRepeat) {
		this.backgroundRepeat = backgroundRepeat;
		this.modifiedBackgroundRepeat = true;
	}
	public CSSBorderStyle getBorderStyle() {
		return borderStyle;
	}
	
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
	
	public void setBorderStyle(CSSBorderStyle borderStyle) {
		this.borderStyle = borderStyle;
		this.modifiedBorderStyle = true;
	}
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
	public void setFont(IFont font) {
		this.font = font;
		this.modifiedFont = true;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
		this.modifiedWidth = true;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
		this.modifiedHeight = true;
	}
	public float getDepth() {
		return depth;
	}
	public void setDepth(float depth) {
		this.depth = depth;
		this.modifiedDepth = true;
	}
	public float getXpos() {
		return xpos;
	}
	public void setXpos(float xpos) {
		this.xpos = xpos;
		this.modifiedXpos = true;
	}
	public float getYpos() {
		return ypos;
	}
	public void setYpos(float ypos) {
		this.ypos = ypos;
		this.modifiedYpos = true;
	}
	public float getZpos() {
		return zpos;
	}
	public void setZpos(float zpos) {
		this.zpos = zpos;
		this.modifiedZpos = true;
	}
	public float getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
		this.modifiedBorderWidth = true;
	}
	public float getPaddingWidth() {
		return paddingWidth;
	}
	public void setPaddingWidth(float paddingWidth) {
		this.paddingWidth = paddingWidth;
		this.modifiedPaddingWidth = true;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		this.modifiedFontSize = true;
	}
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
		this.modifiedVisibility = true;
	}
	public float getzIndex() {
		return zIndex;
	}
	public void setzIndex(float zIndex) {
		this.zIndex = zIndex;
		this.modifiedZIndex = true;
	}

	public boolean isWidthPercentage() {
		return widthPercentage;
	}

	public void setWidthPercentage(boolean widthPercentage) {
		this.widthPercentage = widthPercentage;
		this.modifiedWidthPercentage = true;
	}

	public boolean isHeightPercentage() {
		return heightPercentage;
	}

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
		result = prime * result + Arrays.hashCode(backgroundPosition);
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
		if (!Arrays.equals(backgroundPosition, other.backgroundPosition))
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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public boolean isModifiedUri() {
		return modifiedUri;
	}

	public void setModifiedUri(boolean modifiedUri) {
		this.modifiedUri = modifiedUri;
	}

	public boolean isModifiedSelector() {
		return modifiedSelector;
	}

	public void setModifiedSelector(boolean modifiedSelector) {
		this.modifiedSelector = modifiedSelector;
	}

	public boolean isModifiedBackgroundColor() {
		return modifiedBackgroundColor;
	}

	public void setModifiedBackgroundColor(boolean modifiedBackgroundColor) {
		this.modifiedBackgroundColor = modifiedBackgroundColor;
	}

	public boolean isModifiedColor() {
		return modifiedColor;
	}

	public void setModifiedColor(boolean modifiedColor) {
		this.modifiedColor = modifiedColor;
	}

	public boolean isModifiedBorderColor() {
		return modifiedBorderColor;
	}

	public void setModifiedBorderColor(boolean modifiedBorderColor) {
		this.modifiedBorderColor = modifiedBorderColor;
	}

	public boolean isModifiedBackgroundImage() {
		return modifiedBackgroundImage;
	}

	public void setModifiedBackgroundImage(boolean modifiedBackgroundImage) {
		this.modifiedBackgroundImage = modifiedBackgroundImage;
	}

	public boolean isModifiedBackgroundPosition() {
		return modifiedBackgroundPosition;
	}

	public void setModifiedBackgroundPosition(boolean modifiedBackgroundPosition) {
		this.modifiedBackgroundPosition = modifiedBackgroundPosition;
	}

	public boolean isModifiedBackgroundRepeat() {
		return modifiedBackgroundRepeat;
	}

	public void setModifiedBackgroundRepeat(boolean modifiedBackgroundRepeat) {
		this.modifiedBackgroundRepeat = modifiedBackgroundRepeat;
	}

	public boolean isModifiedBorderStyle() {
		return modifiedBorderStyle;
	}

	public void setModifiedBorderStyle(boolean modifiedBorderStyle) {
		this.modifiedBorderStyle = modifiedBorderStyle;
	}

	public boolean isModifiedFont() {
		return modifiedFont;
	}

	public void setModifiedFont(boolean modifiedFont) {
		this.modifiedFont = modifiedFont;
	}

	public boolean isModifiedCssfont() {
		return modifiedCssfont;
	}

	public void setModifiedCssfont(boolean modifiedCssfont) {
		this.modifiedCssfont = modifiedCssfont;
	}

	public boolean isModifiedWidth() {
		return modifiedWidth;
	}

	public void setModifiedWidth(boolean modifiedWidth) {
		this.modifiedWidth = modifiedWidth;
	}

	public boolean isModifiedHeight() {
		return modifiedHeight;
	}

	public void setModifiedHeight(boolean modifiedHeight) {
		this.modifiedHeight = modifiedHeight;
	}

	public boolean isModifiedDepth() {
		return modifiedDepth;
	}

	public void setModifiedDepth(boolean modifiedDepth) {
		this.modifiedDepth = modifiedDepth;
	}

	public boolean isModifiedWidthPercentage() {
		return modifiedWidthPercentage;
	}

	public void setModifiedWidthPercentage(boolean modifiedWidthPercentage) {
		this.modifiedWidthPercentage = modifiedWidthPercentage;
	}

	public boolean isModifiedHeightPercentage() {
		return modifiedHeightPercentage;
	}

	public void setModifiedHeightPercentage(boolean modifiedHeightPercentage) {
		this.modifiedHeightPercentage = modifiedHeightPercentage;
	}

	public boolean isModifiedXpos() {
		return modifiedXpos;
	}

	public void setModifiedXpos(boolean modifiedXpos) {
		this.modifiedXpos = modifiedXpos;
	}

	public boolean isModifiedYpos() {
		return modifiedYpos;
	}

	public void setModifiedYpos(boolean modifiedYpos) {
		this.modifiedYpos = modifiedYpos;
	}

	public boolean isModifiedZpos() {
		return modifiedZpos;
	}

	public void setModifiedZpos(boolean modifiedZpos) {
		this.modifiedZpos = modifiedZpos;
	}

	public boolean isModifiedBorderWidth() {
		return modifiedBorderWidth;
	}

	public void setModifiedBorderWidth(boolean modifiedBorderWidth) {
		this.modifiedBorderWidth = modifiedBorderWidth;
	}

	public boolean isModifiedPaddingWidth() {
		return modifiedPaddingWidth;
	}

	public void setModifiedPaddingWidth(boolean modifiedPaddingWidth) {
		this.modifiedPaddingWidth = modifiedPaddingWidth;
	}

	public boolean isModifiedFontSize() {
		return modifiedFontSize;
	}

	public void setModifiedFontSize(boolean modifiedFontSize) {
		this.modifiedFontSize = modifiedFontSize;
	}

	public boolean isModifiedVisibility() {
		return modifiedVisibility;
	}

	public void setModifiedVisibility(boolean modifiedVisibility) {
		this.modifiedVisibility = modifiedVisibility;
	}

	public boolean isModifiedZIndex() {
		return modifiedZIndex;
	}

	public void setModifiedZIndex(boolean modifiedZIndex) {
		this.modifiedZIndex = modifiedZIndex;
	}
	
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
