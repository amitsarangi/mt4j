package org.mt4j.css.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.mt4j.MTApplication;
import org.mt4j.css.style.CSSFont;
import org.mt4j.css.style.CSSSelector;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.style.CSSStyle.BackgroundRepeat;
import org.mt4j.css.util.CSSKeywords;
import org.mt4j.css.util.CSSKeywords.CSSBorderStyle;
import org.mt4j.css.util.CSSKeywords.CSSFontFamily;
import org.mt4j.css.util.CSSKeywords.CSSFontStyle;
import org.mt4j.css.util.CSSKeywords.CSSFontWeight;
import org.mt4j.css.util.CSSKeywords.CSSSelectorType;
import org.mt4j.util.MTColor;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.DocumentHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;

import processing.core.PImage;



public class CSSHandler implements DocumentHandler{
	
	Logger logger = null;
	List<CSSStyle> styles = null;
	List<CSSStyle> activeStyles = new ArrayList<CSSStyle>();
	CSSFont currentFont = null;
	MTApplication app = null;
	float defaultFontSize = 16f;
	
	public CSSHandler(MTApplication app, List<CSSStyle> styles) {
		logger = Logger.getLogger("MT4J Extensions");
		this.styles = styles;
		this.app = app;
	}
	
	
	@Override
	public void comment(String arg0) throws CSSException {
		//Don't hand comments
	}

	@Override
	public void endDocument(InputSource arg0) throws CSSException {
		//Document done, nothing to do
		
	}

	@Override
	public void endFontFace() throws CSSException {
		//
		
	}

	@Override
	public void endMedia(SACMediaList arg0) throws CSSException {
		//
		
	}

	@Override
	public void endPage(String arg0, String arg1) throws CSSException {
		//
		
	}

	@Override
	public void endSelector(SelectorList arg0) throws CSSException {
		if (currentFont != null && currentFont.isModified()) {
			for (CSSStyle s: activeStyles) {
				s.setCssfont(currentFont);
			}
		}
		currentFont = new CSSFont();
		activeStyles.clear();
		
	}


	
	@Override
	public void ignorableAtRule(String arg0) throws CSSException {
		//
		
	}

	@Override
	public void importStyle(String arg0, SACMediaList arg1, String arg2)
			throws CSSException {
		//
		
	}

	@Override
	public void namespaceDeclaration(String arg0, String arg1)
			throws CSSException {
		//
		
	}

	@Override
	public void property(String name, LexicalUnit value, boolean important)
			throws CSSException {
		try {
		parseValue(name, value);
		} catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	private void parseValue(String name, LexicalUnit value) {
		try {
			cssproperties prop = cssproperties.UNKNOWN;
			try {
				prop  = cssproperties.valueOf(name.replace(" ", "").replace("-", "").toUpperCase());
			} catch (IllegalArgumentException iae) {
				iae.printStackTrace();
			}
		switch (prop) {
		case BACKGROUNDCOLOR:
			MTColor color = handleColor(value);
			for (CSSStyle sty: activeStyles) sty.setBackgroundColor(color);
			
			break;
		case BACKGROUNDIMAGE:
			PImage backgroundImage = handleBackgroundImage(value);
			if (backgroundImage != null) 
				for (CSSStyle sty: activeStyles) sty.setBackgroundImage(backgroundImage);
			break;
		case BACKGROUND:
			List<LexicalUnit> parameters = new ArrayList<LexicalUnit>();
			parameters.add(value);
			while (value.getNextLexicalUnit() != null) {
				value = value.getNextLexicalUnit();
				parameters.add(value);
			}
			
			for (LexicalUnit lu: parameters) {
				switch (identifyBackgroundTag(lu)) {
				case 1:
					MTColor backgroundColor = handleColor(lu);
					if (backgroundColor != null) 
						for (CSSStyle sty: activeStyles) sty.setBackgroundColor(backgroundColor);
					break;
				case 2:
					PImage backgroundImage2 = handleBackgroundImage(lu);
					if (backgroundImage2 != null) 
						for (CSSStyle sty: activeStyles) sty.setBackgroundImage(backgroundImage2);
					break;
				case 3:
					BackgroundRepeat backgroundRepeat = handleBackgroundRepeat(lu);
					if (backgroundRepeat != null) 
						for (CSSStyle sty: activeStyles) sty.setBackgroundRepeat(backgroundRepeat);
					break;
				default:
					break;
				}
	
			}
		break;
		case BACKGROUNDREPEAT:
			BackgroundRepeat bR = handleBackgroundRepeat(value);
			if (bR != null) 
				for (CSSStyle sty: activeStyles) sty.setBackgroundRepeat(bR);
			break;
						
		case COLOR:
			color = handleColor(value);
			if (currentFont == null) currentFont = new CSSFont(color);
			else currentFont.setColor(color);
			break;
			
		case WIDTH:
			LexicalUnit parameter = value;
			
			for (CSSStyle sty: activeStyles) {
				sty.setWidth(parseMeasuringUnit(parameter,100));
				if (parameter.getLexicalUnitType() == LexicalUnit.SAC_PERCENTAGE) sty.setWidthPercentage(true);
			}
			break;
		case HEIGHT:
			parameter = value;
			for (CSSStyle sty: activeStyles) {
				sty.setHeight(parseMeasuringUnit(parameter, 100));
				if (parameter.getLexicalUnitType() == LexicalUnit.SAC_PERCENTAGE) sty.setHeightPercentage(true);
			}
			break;
		case BORDER:
			parameters = new ArrayList<LexicalUnit>();
			parameters.add(value);
			while (value.getNextLexicalUnit() != null) {
				value = value.getNextLexicalUnit();
				parameters.add(value);
			}
			
			for (LexicalUnit lu: parameters) {
				switch (identifyBorderTag(lu)) {
				case 1:
					float width = handleBorderWidth(value);
					for (CSSStyle sty: activeStyles) sty.setBorderWidth(width);
					break;
				case 2:
					CSSBorderStyle style = parseBorderStyle(value);
					for (CSSStyle sty: activeStyles) sty.setBorderStyle(style);
					break;
				case 3:
					color = handleColor(value);
					for (CSSStyle sty: activeStyles) sty.setBorderColor(color);
					break;
				default:
					break;
				}
	
			}
			break;
		case BORDERWIDTH:
			float width = handleBorderWidth(value);
			for (CSSStyle sty: activeStyles) sty.setBorderWidth(width);
			break;
		case BORDERCOLOR:
			color = handleColor(value);
			for (CSSStyle sty: activeStyles) sty.setBorderColor(color);
			
			break;
		case BORDERSTYLE:
			CSSBorderStyle style = parseBorderStyle(value);
			for (CSSStyle sty: activeStyles) sty.setBorderStyle(style);
			break;
		case PADDING:
			parameter = value;
			for (CSSStyle sty: activeStyles) sty.setPaddingWidth(parseMeasuringUnit(parameter,1));
			break;
		case FONTSIZE:
			parameter = value;
			//Have to convert to pt
			if (CSSKeywords.isMeasuringUnit(parameter)) {
			if (currentFont == null) currentFont = new CSSFont((int) (parseMeasuringUnit(parameter,defaultFontSize) * (72f/100f)));
			else currentFont.setFontsize((int) (parseMeasuringUnit(parameter,defaultFontSize)* (72f/100f)));
			} else if (CSSKeywords.isString(parameter)){
				if (currentFont == null) currentFont = new CSSFont(handleFontSizeString(parameter));
				else currentFont.setFontsize(handleFontSizeString(parameter));
			}
			break;
		case VISIBILITY:
			boolean visible; 
			visible = parseBoolean(value);
			for (CSSStyle sty: activeStyles) sty.setVisibility(visible);
			break;
			
		case FONTFAMILY:
			handleFontFamily(value);
			break;
		case FONT:
			parameters = new ArrayList<LexicalUnit>();
			parameters.add(value);
			while (value.getNextLexicalUnit() != null) {
				value = value.getNextLexicalUnit();
				parameters.add(value);
			}
			
			for (LexicalUnit lu: parameters) {
				switch (identifyFontTag(lu)) {
				case 1:
					parameter = lu;
					if (CSSKeywords.isMeasuringUnit(parameter)) {
						if (currentFont == null) currentFont = new CSSFont((int) (parseMeasuringUnit(parameter,defaultFontSize) * (72f/100f)));
						else currentFont.setFontsize((int) (parseMeasuringUnit(parameter,defaultFontSize)* (72f/100f)));
						} else if (CSSKeywords.isString(parameter)){
							if (currentFont == null) currentFont = new CSSFont(handleFontSizeString(parameter));
							else currentFont.setFontsize(handleFontSizeString(parameter));
						}
					break;
				case 2:
					handleFontWeight(lu);
					break;
				case 3:
					handleFontStyle(lu);
					break;
				case 4:
					handleFontFamily(lu);
					break;
				default:
					break;
				}
			}
			break;
		case FONTSTYLE:
			handleFontStyle(value);
			break;
		case FONTWEIGHT:
			handleFontWeight(value);
			break;
		case UNKNOWN:
		default:
			logger.error("Unknown Identifier: " + name.replace(" ", "").replace("-", "").toUpperCase());
			break;
		
			
		}}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int identifyFontTag(LexicalUnit lu) {
		//0: Unknown
		//1: font-size
		//2: font-weight
		//3: font-style
		//4: font-family
		
		if (CSSKeywords.isMeasuringUnit(lu)) {
			if (lu.getFloatValue() >= 100) return 2;
			else return 1;
		}
		if (CSSKeywords.isFontWeight(lu)) return 2;
		
		if (CSSKeywords.isFontFamily(lu)) return 4;
		
		if (CSSKeywords.isFontStyle(lu)) return 3;
		
		return 0;
	}


	private int identifyBorderTag(LexicalUnit lu) {
		//0: Unknown
		//1: Border-Width
		//2: Border-Style
		//3. Border-Color
		if (lu.getLexicalUnitType() == LexicalUnit.SAC_RGBCOLOR) return 3;
		if (CSSKeywords.isMeasuringUnit(lu)) return 1;
		if (lu.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
			if (CSSKeywords.isBorderStyle(lu)) return 2;
			if (CSSKeywords.isColor(lu)) return 3;
		
		}
		return 0;
	}


	private BackgroundRepeat handleBackgroundRepeat(LexicalUnit value) {
		logger.debug("Background Repeat Type: " + value.getLexicalUnitType());
		if (value.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
			if (value.getStringValue().replaceAll(" ", "").equalsIgnoreCase("REPEAT-X")) return BackgroundRepeat.XREPEAT;
			if (value.getStringValue().replaceAll(" ", "").equalsIgnoreCase("REPEAT-Y")) return BackgroundRepeat.YREPEAT;
			if (value.getStringValue().replaceAll(" ", "").equalsIgnoreCase("REPEAT")) return BackgroundRepeat.REPEAT;
			if (value.getStringValue().replaceAll(" ", "").equalsIgnoreCase("NO-REPEAT")) return BackgroundRepeat.NONE;
		}
		
		
		
		return null;
	}
	
	private int identifyBackgroundTag(LexicalUnit value) {
		//Return Values:
		//0: Unknown
		//1: Color
		//2: Background-Image
		//3: Repeat
		
		if (value.getLexicalUnitType() == LexicalUnit.SAC_RGBCOLOR) return 1;
		if (value.getLexicalUnitType() == LexicalUnit.SAC_URI) return 2;
		if (value.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
			if (CSSKeywords.isColor(value)) return 1;
			if (CSSKeywords.isBackgroundRepeat(value)) return 3;
			if (CSSKeywords.isBackgroundPosition(value)) return 0;
			if (CSSKeywords.isBackgroundAttachment(value)) return 0;
		}
		
		
		return 0;
	}
	
	private float handleBorderWidth(LexicalUnit lu) {
		if (CSSKeywords.isMeasuringUnit(lu)) {
			return parseMeasuringUnit(lu,1);
		} 
		if (CSSKeywords.isString(lu)) {
			if (CSSKeywords.isBorderWidth(lu)) {
				if (lu.getStringValue().replaceAll(" ", "").equalsIgnoreCase("THIN")) return 0.5f;
				if (lu.getStringValue().replaceAll(" ", "").equalsIgnoreCase("MEDIUM")) return 1f;
				if (lu.getStringValue().replaceAll(" ", "").equalsIgnoreCase("THICK")) return 2f;
			}
		
		}

		return 0f;
	}
	

	private PImage handleBackgroundImage(LexicalUnit value) {
		if (value.getLexicalUnitType() == LexicalUnit.SAC_URI) {
		
			try {
				return app.loadImage(value.getStringValue());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	
		}
		
		return null;
	}


	private int handleFontSizeString(LexicalUnit parameter) {
		
		if (parameter.getStringValue().toUpperCase().contains("SMALLER")) return 8;
		if (parameter.getStringValue().toUpperCase().contains("BIGGER")) return 30;
		
		if (parameter.getStringValue().toUpperCase().contains("SMALL")) return 12;
		if (parameter.getStringValue().toUpperCase().contains("BIG")) return 24;
		
		return 16;
	}


	private void handleFontWeight(LexicalUnit value) {
		CSSFontWeight weight = CSSFontWeight.NORMAL;
		if (currentFont == null) currentFont = new CSSFont(weight);
		switch (value.getLexicalUnitType()) {
		case LexicalUnit.SAC_IDENT:
		case LexicalUnit.SAC_STRING_VALUE:
			if (value.getStringValue().toUpperCase().contains("BOLD")) {currentFont.setWeight(CSSFontWeight.BOLD); break;}
			if (value.getStringValue().toUpperCase().contains("LIGHT")) {currentFont.setWeight(CSSFontWeight.LIGHT); break;}
			currentFont.setWeight(CSSFontWeight.NORMAL);
			break;
		case LexicalUnit.SAC_INTEGER:
			if (value.getIntegerValue() < 400) {currentFont.setWeight(CSSFontWeight.LIGHT); break;}
			if (value.getIntegerValue() > 600) {currentFont.setWeight(CSSFontWeight.BOLD); break;}
			currentFont.setWeight(CSSFontWeight.NORMAL); 
			break;
		default: break;
		}
		
	}


	private void handleFontFamily(LexicalUnit value) {
		CSSFontFamily family = CSSFontFamily.CUSTOM;
		if (currentFont == null) currentFont = new CSSFont(family);
		if (value.getLexicalUnitType() == LexicalUnit.SAC_IDENT || value.getLexicalUnitType() == LexicalUnit.SAC_STRING_VALUE) {
			
			if (value.getStringValue().toUpperCase().contains("TTF")) {
				currentFont.setFamily(CSSFontFamily.CUSTOM); 
				currentFont.setCustomType(value.getStringValue()); 
				return;}
			if (value.getStringValue().toUpperCase().contains("MONO")) {currentFont.setFamily(CSSFontFamily.MONO); return;}
			if (value.getStringValue().toUpperCase().contains("SANS")) {currentFont.setFamily(CSSFontFamily.SANS); return;}
			if (value.getStringValue().toUpperCase().contains("SERIF")) {currentFont.setFamily(CSSFontFamily.SERIF); return;}

		} else {
			logger.debug(value.getLexicalUnitType());
		}
		
	}
	private void handleFontStyle(LexicalUnit value) {
		
		CSSFontStyle style = CSSFontStyle.NORMAL;
		if (currentFont == null) currentFont = new CSSFont(style);
		if (value.getLexicalUnitType() == LexicalUnit.SAC_IDENT || value.getLexicalUnitType() == LexicalUnit.SAC_STRING_VALUE) {
			if (value.getStringValue().toUpperCase().contains("ITALIC")) {currentFont.setStyle(CSSFontStyle.ITALIC); return;}
			if (value.getStringValue().toUpperCase().contains("OBLIQUE")) {currentFont.setStyle(CSSFontStyle.OBLIQUE); return;}
		}
	}
	
	private MTColor handleColor(LexicalUnit value){
		switch (value.getLexicalUnitType()) {
		case LexicalUnit.SAC_RGBCOLOR:
				try {

					LexicalUnit parameters = value.getParameters();
					float red = parseMeasuringUnit(parameters, 255);
					parameters = parameters.getNextLexicalUnit().getNextLexicalUnit();
					float green = parseMeasuringUnit(parameters, 255);
					parameters = parameters.getNextLexicalUnit().getNextLexicalUnit();
					float blue = parseMeasuringUnit(parameters, 255);

					return new MTColor(red, green, blue, 255);
					
				} catch (Exception e) {e.printStackTrace();};
			break;
		case LexicalUnit.SAC_IDENT:
				if (value.getStringValue().equalsIgnoreCase("black")) return MTColor.BLACK;
				if (value.getStringValue().equalsIgnoreCase("white")) return MTColor.WHITE;
				if (value.getStringValue().equalsIgnoreCase("silver")) return MTColor.SILVER;
				if (value.getStringValue().equalsIgnoreCase("gray")) return MTColor.GRAY;
				if (value.getStringValue().equalsIgnoreCase("grey")) return MTColor.GREY;
				if (value.getStringValue().equalsIgnoreCase("maroon")) return MTColor.MAROON;
				if (value.getStringValue().equalsIgnoreCase("red")) return MTColor.RED;
				if (value.getStringValue().equalsIgnoreCase("purple")) return MTColor.PURPLE;
				if (value.getStringValue().equalsIgnoreCase("fuchsia")) return MTColor.FUCHSIA;
				if (value.getStringValue().equalsIgnoreCase("green")) return MTColor.GREEN;
				if (value.getStringValue().equalsIgnoreCase("lime")) return MTColor.LIME;
				if (value.getStringValue().equalsIgnoreCase("olive")) return MTColor.OLIVE;
				if (value.getStringValue().equalsIgnoreCase("yellow")) return MTColor.YELLOW;
				if (value.getStringValue().equalsIgnoreCase("navy")) return MTColor.NAVY;
				if (value.getStringValue().equalsIgnoreCase("blue")) return MTColor.BLUE;
				if (value.getStringValue().equalsIgnoreCase("teal")) return MTColor.TEAL;
				if (value.getStringValue().equalsIgnoreCase("aqua")) return MTColor.AQUA;
				
				
				
				
				
			break;
		}
		
		return new MTColor(0,0,0,0);
	}
	
	private boolean parseBoolean(LexicalUnit value) {
		try {
			return Boolean.parseBoolean(value.getStringValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	private CSSBorderStyle parseBorderStyle(LexicalUnit value) {
		LexicalUnit parameter = value;
		if (parameter.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
			if (parameter.getStringValue().equalsIgnoreCase("dashed")) return CSSBorderStyle.DASHED;
			if (parameter.getStringValue().equalsIgnoreCase("dotted")) return CSSBorderStyle.DOTTED;
			if (parameter.getStringValue().equalsIgnoreCase("none")) return CSSBorderStyle.NONE;
			if (parameter.getStringValue().equalsIgnoreCase("hidden")) return CSSBorderStyle.HIDDEN;
		}
		
		return CSSBorderStyle.SOLID;
	}
	
	private float parseNumber(LexicalUnit component) {
		if (component.getLexicalUnitType() == LexicalUnit.SAC_INTEGER) {
			return (float) component.getIntegerValue();
		}
		if (component.getLexicalUnitType() == LexicalUnit.SAC_REAL) {
			return (float) component.getFloatValue();
		}
		if (component.getLexicalUnitType() == LexicalUnit.SAC_PERCENTAGE) {
			return (float) component.getFloatValue();
		}
		
		return 0;
	}
	
	private float parseMeasuringUnit(LexicalUnit value, float referenceValue) {
		float dpi = 100f;
		
		float emtopx = 16f/72f * dpi;
		float inchtopx = dpi;
		float centtopx = (10f/254f) * dpi;
		float mmtopx = (1f/254f) * dpi;
		float pointtopx = (1f/72f) * dpi;
		float picatopx = (12f/72f) * dpi;
		float rv;
		try {
		switch (value.getLexicalUnitType()) {
		case LexicalUnit.SAC_CENTIMETER:
			return value.getFloatValue() * centtopx;
		case LexicalUnit.SAC_INCH:
			return value.getFloatValue() * inchtopx;
		case LexicalUnit.SAC_MILLIMETER:
			return value.getFloatValue() * mmtopx;
		case LexicalUnit.SAC_POINT:
			return value.getFloatValue() * pointtopx;
		case LexicalUnit.SAC_PICA:
			return value.getFloatValue() * picatopx;
		case LexicalUnit.SAC_EM:
			return (value.getFloatValue() * emtopx);
		case LexicalUnit.SAC_PIXEL:
			return value.getFloatValue();
		case LexicalUnit.SAC_INTEGER:
			return (float) value.getIntegerValue();
		case LexicalUnit.SAC_REAL:
		case LexicalUnit.SAC_PERCENTAGE:
			return (float) value.getFloatValue() / 100 *  referenceValue;
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.error("Unrecognized Measuring Unit: " + value.getLexicalUnitType());
		return 0;
	}
	
	
	@Override
	public void startDocument(InputSource arg0) throws CSSException {
		
	}

	@Override
	public void startFontFace() throws CSSException {
		
	}

	@Override
	public void startMedia(SACMediaList arg0) throws CSSException {
		
	}

	@Override
	public void startPage(String arg0, String arg1) throws CSSException {
		
	}

	@Override
	public void startSelector(SelectorList selectors) throws CSSException {
		
		for (int i=0; i < selectors.getLength(); i++) {
			styles.add(new CSSStyle(parseSelector(selectors.item(i)), app));
			activeStyles.add(styles.get(styles.size()-1));
			
		}
		
		
		
	}
	
	public CSSSelector parseSelector(org.w3c.css.sac.Selector selector) {

		
		CSSSelector newSelector = null;
		String debugoutput = "";
		
		if (selector.toString().contains(">")) {
			String[] parts = selector.toString().split(">", 2);
			newSelector = processElement(parts[0]);
			newSelector.setChild(processElement(parts[1]));
			String work0 = String.copyValueOf(parts[0].toCharArray());	
			String work1 = String.copyValueOf(parts[1].toCharArray());	
			debugoutput = "Parent: " + work0.replaceAll(" ", "_") + " Child: " + work1.replaceAll(" ", "_");
		} else {
			//No Children (yet)
			newSelector = processElement(selector.toString());
			debugoutput = "No Parent/Child: " + selector.toString().replaceAll(" ", "_");
			
		}
		//logger.debug(debugoutput + "\n" + newSelector);
		return newSelector;
	}
	
	public CSSSelector processElement(String in) {
		CSSSelector newSelector = null;
		
		
		
		String work = String.copyValueOf(in.toCharArray());	
		
		while (work.startsWith(" ")) work = work.substring(1);
		while (work.endsWith(" ")) work = work.substring(0, work.length()-1);
		
		if (work.contains("*") && !work.startsWith("*")) { // replace?
			work = work.substring(0, 1) + work.substring(1).replace("*", "");
		}
		
		if (work.startsWith("*") && !work.equals("*")) {
			work = work.substring(1);
		}
		work.replace(" ", "");
		boolean containsSharp = false;
		boolean containsDot = false;
		boolean firstCharacterDot = false;
		boolean firstCharacterSharp = false;
		boolean containsSpace = false;
		
		if (work.contains("#")) containsSharp = true;
		if (work.contains(".")) containsDot = true;
		work = work.replaceAll("[ ]+[#]", "#").replaceAll("[ ]+[.]", ".");
		if (work.contains(" ")) containsSpace = true;
		
		if (work.equals("*")) {
			newSelector = new CSSSelector("*", CSSSelectorType.UNIVERSAL);
			return newSelector;
		}
		
		if (work.startsWith(".")) {
			firstCharacterDot = true;
			containsDot = work.substring(1).contains(".");
			
		}
		
		if (work.startsWith("#")) {
			firstCharacterSharp = true;
			containsSharp = work.substring(1).contains("#");
		}
		
		
		if (!containsSharp && !containsDot) {
			newSelector = new CSSSelector(work.replace(".", "").replace("#", ""), determineType(work));
		}
		
		if (containsSpace) {
			StringTokenizer st = new StringTokenizer(work, " ");

			if (st.countTokens() > 1) {
				String part1 = st.nextToken();
				String part2 = st.nextToken();
				newSelector = new CSSSelector(part1, determineType(part1));
				newSelector.setSecondary(part2);
				newSelector.setSecondaryType(CSSSelectorType.TYPE);
				
			} 
			
			return newSelector;
		}
		
		
		if (containsSharp) {
			if (firstCharacterSharp) {
				StringTokenizer st = new StringTokenizer(work.substring(1), "#");
				
				if (st.countTokens() > 1) {			
				newSelector = new CSSSelector(st.nextToken(), CSSSelectorType.ID);
				newSelector.setSecondary(st.nextToken());
				newSelector.setSecondaryType(CSSSelectorType.ID);
				}
			} else {
			StringTokenizer st = new StringTokenizer(work, "#");

			if (st.countTokens() > 1) {
				String part1 = st.nextToken();
				String part2 = st.nextToken();
				newSelector = new CSSSelector(part1, determineType(part1));
				newSelector.setSecondary(part2);
				newSelector.setSecondaryType(CSSSelectorType.ID);
				
			} 
			}
			return newSelector;
		}
		
		if (containsDot) {
			if (firstCharacterDot) {
				StringTokenizer st = new StringTokenizer(work.substring(1), ".");
				
				if (st.countTokens() > 1) {			
				newSelector = new CSSSelector(st.nextToken(), CSSSelectorType.CLASS);
				newSelector.setSecondary(st.nextToken());
				newSelector.setSecondaryType(CSSSelectorType.CLASS);
				}
			} else {
				StringTokenizer st = new StringTokenizer(work, ".");

				if (st.countTokens() > 1) {
					String part1 = st.nextToken();
					String part2 = st.nextToken();
					newSelector = new CSSSelector(part1, determineType(part1));
					newSelector.setSecondary(part2);
					newSelector.setSecondaryType(CSSSelectorType.CLASS);
					
				} 
			}
			return newSelector;		
		}
		
		
		
		return newSelector;
	}
	
	public List<CSSStyle> getStyles() {
		return styles;
	}
	
	private CSSSelectorType determineType (String in) {
		if (in.contains(".")) return CSSSelectorType.CLASS;
		if (in.contains("#")) return CSSSelectorType.ID;
		if (in.contains("*")) return CSSSelectorType.UNIVERSAL;
			
		return CSSSelectorType.TYPE;
	}
	
	private enum cssproperties {
		COLOR, BACKGROUNDCOLOR, BORDERCOLOR, BACKGROUNDIMAGE, BACKGROUNDPOSITION, BACKGROUNDREPEAT, BORDERSTYLE, FONTFAMILY, FONT, FONTSIZE, FONTSTYLE, FONTWEIGHT, WIDTH, HEIGHT, BORDERWIDTH, PADDING, VISIBILITY, ZINDEX, BORDER, UNKNOWN, BACKGROUND 
	}
	

}
