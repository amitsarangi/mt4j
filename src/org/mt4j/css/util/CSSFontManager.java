package org.mt4j.css.util;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.css.style.CSSFont;
import org.mt4j.css.util.CSSKeywords.CSSFontWeight;
import org.mt4j.util.MTColor;


public class CSSFontManager {
	MTApplication app;
	public CSSFontManager(MTApplication app) {
		this.app = app;
	}
	
	
	public IFont selectFont(CSSFont currentFont2) {

		if (currentFont2 != null) {
		try {
		switch (currentFont2.getFamily()) {
		
		case SERIF:
			switch (currentFont2.getStyle()) {
			case ITALIC:
			case OBLIQUE:
				if (currentFont2.getWeight() == CSSFontWeight.BOLD) {
					return getFont("dejavu/DejaVuSerif-BoldItalic.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.LIGHT) {
					return getFont("dejavu/DejaVuSerif-Italic.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.NORMAL) {
					return getFont("dejavu/DejaVuSerif-Italic.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
			
				break;
			case NORMAL:
			default:
				if (currentFont2.getWeight() == CSSFontWeight.BOLD) {
					return getFont("dejavu/DejaVuSerif-Bold.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.LIGHT) {
					return getFont("dejavu/DejaVuSerif.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.NORMAL) {
					return getFont("dejavu/DejaVuSerif.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				break;
			}
			break;
		case MONO:
			switch (currentFont2.getStyle()) {
			case ITALIC:
			case OBLIQUE:
				if (currentFont2.getWeight() == CSSFontWeight.BOLD) {
					return getFont("dejavu/DejaVuSansMono-BoldOblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.LIGHT) {
					return getFont("dejavu/DejaVuSansMono-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.NORMAL) {
					return getFont("dejavu/DejaVuSansMono-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
			
				break;
			case NORMAL:
			default:
				if (currentFont2.getWeight() == CSSFontWeight.BOLD) {
					return getFont("dejavu/DejaVuSansMono-Bold.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.LIGHT) {
					return getFont("dejavu/DejaVuSansMono.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.NORMAL) {
					return getFont("dejavu/DejaVuSansMono.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				break;
			}
			break;
		case CUSTOM:
			return getFont(currentFont2.getCustomType(), currentFont2.getFontsize(), currentFont2.getColor());
			
		case DEFAULT:
		case SANS:
		default:
			switch (currentFont2.getStyle()) {
			case ITALIC:
			case OBLIQUE:
				if (currentFont2.getWeight() == CSSFontWeight.BOLD) {
					return getFont("dejavu/DejaVuSans-BoldOblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.LIGHT) {
					return getFont("dejavu/DejaVuSans-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.NORMAL) {
					return getFont("dejavu/DejaVuSans-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
			
				break;
			case NORMAL:
			default:
				if (currentFont2.getWeight() == CSSFontWeight.BOLD) {
					return getFont("dejavu/DejaVuSans-Bold.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.LIGHT) {
					return getFont("dejavu/DejaVuSans-ExtraLight.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == CSSFontWeight.NORMAL) {
					return getFont("dejavu/DejaVuSans.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				break;
			}
			break;
		
		}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		}
		

		if (currentFont2 == null) {
			currentFont2 = new CSSFont(16);
		}
		
		 return FontManager.getInstance().createFont(app,
				"dejavu/DejaVuSans.ttf", currentFont2.getFontsize(), // Font size
				currentFont2.getColor(), // Font fill color
				currentFont2.getColor()); 
	}

	private IFont getFont(String font, int size, MTColor color) {
		try {
			IFont returnFont = FontManager.getInstance().createFont(app,
		
				font, size, // Font size
				color, // Font fill color
				color);
			if (returnFont == null) throw new Exception();
			return returnFont;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FontManager.getInstance().createFont(app,
				"dejavu/DejaVuSans.ttf", size, // Font size
				color, // Font fill color
				color); 
	}
}
