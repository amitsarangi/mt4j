package org.mt4j.css.util;

import java.util.Arrays;

import org.w3c.css.sac.LexicalUnit;

public class CSSKeywords {
	public static String[] colourNames = new String[] {
			"BLACK", "WHITE", "SILVER", "GREY", "GRAY",
			"AQUA", "BLUE", "NAVY",
			"FUCHSIA", "MAROON", "RED", "PURPLE",
			"GREEN", "LIME", "TEAL",
			"YELLOW", "OLIVE"
	};
	
	public static String[] backgroundPositionNames = new String[] {
			"LEFT", "RIGHT", "BOTTOM", "TOP", "CENTER"
	};
	
	public static String[] backgroundRepeatNames = new String[] {
		"REPEAT-X", "REPEAT-Y", "REPEAT", "NO-REPEAT"	
	};
	
	public static String[] backgroundAttachmentNames = new String[] {
		"FIXED", "SCROLL"	
	};
	
	public static String [] borderStyleNames = new String[] {
		"SOLID", "NONE", "DASHED", "DOTTED"
	};
	
	public static String[] fontFamilyNames = new String[] {
		"SERIF", "SANS-SERIF", "MONO", "MONOSPACE"
	};
	public static String[] fontStyleNames = new String[] {
		"ITALIC", "OBLIQUE", "NORMAL"
	};
	public static String[] fontWeightNames = new String[] {
		"BOLD", "BOLDER", "LIGHTER", "LIGHT"
	};
	
	public static short[] measuringUnits = new short[] {
		LexicalUnit.SAC_CENTIMETER, LexicalUnit.SAC_INCH,LexicalUnit.SAC_MILLIMETER,
		LexicalUnit.SAC_PIXEL, LexicalUnit.SAC_POINT,
		LexicalUnit.SAC_PICA, LexicalUnit.SAC_EM,
		LexicalUnit.SAC_INTEGER, LexicalUnit.SAC_REAL,
		LexicalUnit.SAC_PERCENTAGE
	};
	
	public static String[] borderWidthNames = new String[] {
		"THIN", "MEDIUM", "THICK"
	};
	
	public static short[] stringValues = new short[] {
		LexicalUnit.SAC_IDENT, LexicalUnit.SAC_STRING_VALUE
	};
	
	public static boolean isMeasuringUnit(LexicalUnit lu) {
			
		if (contains(measuringUnits,lu))
			return true;
		return false;
	}
	
	private static boolean contains(short[] keywords, LexicalUnit lu) {
		for (short s: keywords) {
			if (s == lu.getLexicalUnitType()) return true;
		}
		return false;
	}
	
	public static boolean isString(LexicalUnit lu) {
		if (contains(stringValues,lu))
			return true;
		return false;
	}
	private static boolean contains(String[] keywords, LexicalUnit lu) {
		if (isString(lu)) {
			if (Arrays.asList(keywords).contains(lu.getStringValue().toUpperCase().replaceAll(" ", "")))
				return true;
		}
		return false;
	}
	
	public static boolean isColor(LexicalUnit lu) {
		if (lu.getLexicalUnitType() == LexicalUnit.SAC_RGBCOLOR) return true;
		
		return contains(colourNames, lu);
	}
	public static boolean isBackgroundPosition(LexicalUnit lu) {
		return contains(backgroundPositionNames, lu);
	}
	public static boolean isBackgroundRepeat(LexicalUnit lu) {
		return contains(backgroundRepeatNames, lu);
	}
	public static boolean isBackgroundAttachment(LexicalUnit lu) {
		return contains(backgroundAttachmentNames, lu);
	}
	public static boolean isBorderStyle(LexicalUnit lu) {
		return contains(borderStyleNames, lu);
	}
	public static boolean isBorderWidth(LexicalUnit lu) {
		return contains(borderWidthNames, lu);
	}
	public static boolean isFontStyle(LexicalUnit lu) {
		return contains(fontStyleNames, lu);
	}
	public static boolean isFontFamily(LexicalUnit lu) {
		return contains(fontFamilyNames, lu);
	}
	public static boolean isFontWeight(LexicalUnit lu) {
		return contains(fontWeightNames, lu);
	}
	public enum CSSFontWeight {
		BOLD, LIGHT, NORMAL;
	}
	public enum CSSSelectorType {
		UNIVERSAL, TYPE, ID, CLASS, CUSTOM;
	}
	public enum CSSFontFamily {
		SANS, SERIF, MONO, CUSTOM, DEFAULT;
	}
	public enum CSSFontStyle {
		ITALIC, OBLIQUE, NORMAL;
	}
	public enum CSSBorderStyle {
		SOLID, DASHED, DOTTED, NONE, HIDDEN;
	}
}
