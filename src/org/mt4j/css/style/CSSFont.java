package org.mt4j.css.style;

import org.apache.log4j.Logger;
import org.mt4j.css.util.CSSKeywords.CSSFontFamily;
import org.mt4j.css.util.CSSKeywords.CSSFontStyle;
import org.mt4j.css.util.CSSKeywords.CSSFontWeight;
import org.mt4j.util.MTColor;

public class CSSFont {
	private CSSFontFamily family = CSSFontFamily.CUSTOM;
	private CSSFontStyle style = CSSFontStyle.NORMAL;
	private String customType = "";
	private CSSFontWeight weight = CSSFontWeight.NORMAL;
	private int fontsize = 16;
	private MTColor color = new MTColor(255, 255, 255, 255);
	private boolean modified = false;

	public CSSFont(MTColor color) {
		super();
		this.color = color;
	}
	public CSSFont() {
		super();
		this.family = CSSFontFamily.DEFAULT;
	}
	
	public CSSFont(int fontsize) {
		super();
		this.fontsize = fontsize;
		this.modified = true;
	}

	public CSSFont(String customType) {
		super();
		this.customType = customType;
		this.modified = true;
		debugOutput();
	}

	public CSSFont(CSSFontStyle style) {
		super();
		this.style = style;
		this.modified = true;
		debugOutput();
	}

	public CSSFont(CSSFontFamily family) {
		super();
		this.family = family;
		debugOutput();
	}

	public CSSFont(CSSFontWeight weight) {
		super();
		this.weight = weight;
		this.modified = true;
		debugOutput();
	}

	public MTColor getColor() {
		return color;
	}

	public void setColor(MTColor color) {
		this.color = color;
		this.modified = true;
	}

	public int getFontsize() {
		return fontsize;

	}

	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
		this.modified = true;
		debugOutput();
	}

	public CSSFontWeight getWeight() {
		return weight;
	}

	public void setWeight(CSSFontWeight weight) {
		this.weight = weight;
		this.modified = true;
		debugOutput();
	}

	public CSSFontFamily getFamily() {
		return family;
	}

	public void setFamily(CSSFontFamily family) {
		this.family = family;
		this.modified = true;
		debugOutput();
	}

	public CSSFontStyle getStyle() {
		return style;
	}

	public void setStyle(CSSFontStyle style) {
		this.style = style;
		this.modified = true;
		debugOutput();
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
		this.modified = true;
		debugOutput();
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
	public boolean isModified() {
		return modified;
	}
	private void debugOutput() {
		/*Logger logger = Logger.getLogger("MT4J Extensions");
		logger.debug("Font Family: " + family + ", Font Style: " + style
				+ ", Font Weight: " + weight + ", Font Size: " + fontsize
				+ ", Custom TTF Font: " + customType);*/
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result
				+ ((customType == null) ? 0 : customType.hashCode());
		result = prime * result + ((family == null) ? 0 : family.hashCode());
		result = prime * result + fontsize;
		result = prime * result + ((style == null) ? 0 : style.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		CSSFont other = (CSSFont) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (customType == null) {
			if (other.customType != null)
				return false;
		} else if (!customType.equals(other.customType))
			return false;
		if (family == null) {
			if (other.family != null)
				return false;
		} else if (!family.equals(other.family))
			return false;
		if (fontsize != other.fontsize)
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
}
