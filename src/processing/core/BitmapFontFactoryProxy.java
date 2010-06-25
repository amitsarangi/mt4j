package processing.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.BitmapFont;
import org.mt4j.components.visibleComponents.font.BitmapFontCharacter;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.font.fontFactories.IFontFactory;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;

import processing.core.PFont.Glyph;

/**
 * A factory for creating BitmapFont objects.
 * @author Christopher Ruff
 */
public class BitmapFontFactoryProxy implements IFontFactory {
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(BitmapFontFactoryProxy.class.getName());
	static{
//		logger.setLevel(Level.ERROR);
//		logger.setLevel(Level.WARN);
		logger.setLevel(Level.DEBUG);
		SimpleLayout l = new SimpleLayout();
		ConsoleAppender ca = new ConsoleAppender(l);
		logger.addAppender(ca);
	}
	
	public static String defaultCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜabcdefghijklmnopqrstuvwxyzäöü<>|,;.:-_#'+*!\"§$%&/()=?´{[]}\\@";
	
//	static{
//		FontManager.getInstance().registerFontFactory("", new BitmapFontFactory());
//	}

	
	
	/* (non-Javadoc)
	 * @see org.mt4j.components.visibleComponents.font.fontFactories.IFontFactory#createFont(processing.core.PApplet, java.lang.String, int, org.mt4j.util.MTColor, org.mt4j.util.MTColor)
	 */
	public IFont createFont(
			PApplet pa, 
			String fontFileName, 
			int fontSize,
			MTColor fillColor, 
			MTColor strokeColor
	) {
		PFont p5Font = this.getProcessingFont(pa, fontFileName, fontSize);
		List<BitmapFontCharacter> bitMapCharacters = this.createCharacters(pa, p5Font, defaultCharacters, fillColor, strokeColor);
	
		//font is null sometimes (vlw)
		/*
		Font f = p5Font.getFont();
		FontMetrics fm = pa.getFontMetrics(f); 
		Map<TextAttribute, ?> atts = f.getAttributes();
		Set<TextAttribute> attKeys = atts.keySet();
		for (Iterator iterator = attKeys.iterator(); iterator.hasNext();) {
			TextAttribute textAttribute = (TextAttribute) iterator.next();
			Object value = atts.get(textAttribute);
			logger.debug("Key: " + textAttribute + " Value: " + value);
		}
//		FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(f);
		 */
		
		int defaultHorizontalAdvX = bitMapCharacters.get(0).getHorizontalDist(); //FIXME HACK!
		String fontFamily = p5Font.getPostScriptName();
//		String fontFamily = f.getFamily(); 
		//FIXME ascent() and descent() return to small values! wheres the difference??
		int fontMaxAscent = Math.round(p5Font.ascent()* (fontSize));
		fontMaxAscent +=(float)fontSize/5.5f; //FIXME HACK! because the same ttf fonts seem to have bigger ascents
//		int fontMaxAscent = p5Font.lazyMetrics.getAscent();
		int fontMaxDescent = Math.round(p5Font.descent() * fontSize);
		/*
		//TODO INFO: because in vector font this is a negative value, too
		Font f = p5Font.getFont();
		if (f != null){
			FontMetrics fm = pa.getFontMetrics(f);
			fontMaxDescent = fm.getDescent();
		}
		*/
		fontMaxDescent *= -1; //We use negative descent values
		
		//logger.debug("Bitmapfont max descent: " + fontMaxDescent);
		
//		int fontMaxAscent = Math.round(p5Font.ascent()*fontSize);
//		int fontMaxDescent = Math.round(p5Font.descent()*fontSize);
//		int fontMaxAscent = fm.getMaxAscent(); 
//		int fontMaxDescent = fm.getMaxDescent(); 
		int unitsPerEm = 1000; //FIXME HACK!
		int originalFontSize = fontSize; //important for font cache
		
		PImage dummy = new PImage(1,1);
//		/*
		//Manually add a newLine character to the font
		BitmapFontCharacter newLine = new BitmapFontCharacter(dummy, pa, "\n", 0, 0, 0);
		newLine.setPickable(false);						    		
		newLine.setVisible(false);
		newLine.setNoFill(true);
		newLine.setNoStroke(true);
		newLine.setName("newline");
		bitMapCharacters.add(newLine);
		
		//Manually add a SPACE character to the font
//		int spaceAdvancex = defaultHorizontalAdvX;
//		int spaceAdvancex = fm.charWidth(' '); 
		//TODO hack, we use the dash character's width for the space width, because dont know how to get it
//		int spaceIndex = p5Font.index('-');
//		int spaceAdvancex = p5Font.width[spaceIndex];
//		int spaceAdvancex = p5Font.getGlyph('-').width;
		int spaceAdvancex = Math.round(((float) p5Font.width('i') * (float) fontSize));
//		int spaceAdvancex = Math.round(pa.textWidth(' '));
//		int spaceAdvancex = Math.round(p5Font.width(' ') * p5Font.size);
		BitmapFontCharacter space = new BitmapFontCharacter(dummy, pa, " ", 0, 0, spaceAdvancex);
		space.setPickable(false);						    		
		space.setVisible(false);
		space.setNoFill(true);
		space.setNoStroke(true);
		space.setName("space");
		bitMapCharacters.add(space);
		
		//Manually add a TAB character to the font
		int defaultTabWidth = spaceAdvancex*4;
		BitmapFontCharacter tab = new BitmapFontCharacter(dummy, pa, "\t", 0, 0, defaultTabWidth);
		try {
			int tabWidth = 4 * space.getHorizontalDist();
			tab.setHorizontalDist(tabWidth);
		} catch (Exception e) {
			tab.setHorizontalDist(defaultTabWidth);
		}
		tab.setPickable(false);
		tab.setName("tab"); 
		tab.setVisible(false);
		tab.setNoFill(true);
		tab.setNoStroke(true);
		bitMapCharacters.add(tab);
//		*/
		
		//TODO bitmap font size seems different to same size vector font, we must have check descent -> textarea -> res*em*etc
		//TODO eureka font -  numbers baseline wrong?
		
		//Create the bitmap font
		BitmapFontCharacter[] characters = bitMapCharacters.toArray(new BitmapFontCharacter[bitMapCharacters.size()]);
		BitmapFont bitmapFont = new BitmapFont(characters, defaultHorizontalAdvX, fontFamily, fontMaxAscent, fontMaxDescent, unitsPerEm, originalFontSize, 
				fillColor,
				strokeColor
		);
		bitmapFont.setFontFileName(fontFileName);
		return bitmapFont;
	}
	
	
	
	/**
	 * Gets the processing font.
	 * @param pa the pa
	 * @param fontFileName the font file name
	 * @param fontSize the font size
	 * @return the processing font
	 */
	private PFont getProcessingFont(PApplet pa, String fontFileName, int fontSize){
		PFont p5Font;
		//FIXME when loading the vlw font the font size is already determined with the file
		//and our parameter isnt honored
		if (fontFileName.endsWith(".vlw")){
			int lastDirSeparator = fontFileName.lastIndexOf(java.io.File.separator);
			if (lastDirSeparator != -1){
//				p5Font = pa.createFont(fontFileName.substring(lastDirSeparator+1, fontFileName.length()), fontSize, false); //FIXME TEST
				p5Font = pa.loadFont(fontFileName.substring(lastDirSeparator+1, fontFileName.length()));
			}else{
				p5Font = pa.loadFont(fontFileName);
			}
		}
		else if (fontFileName.endsWith(".ttf") || fontFileName.endsWith(".otf")){
			int lastDirSeparator = fontFileName.lastIndexOf(java.io.File.separator);
			if (lastDirSeparator != -1){
				p5Font = pa.createFont(fontFileName.substring(lastDirSeparator+1, fontFileName.length()), fontSize, true); 
			}else{
				p5Font = pa.loadFont(fontFileName);
			}
		}
		else{
			int lastDirFileSeparator = fontFileName.lastIndexOf(java.io.File.separator);
			int lastDirSeparator = fontFileName.lastIndexOf(MTApplication.separator);
			if (lastDirSeparator != -1){
				p5Font = pa.createFont(fontFileName.substring(lastDirSeparator+1, fontFileName.length()), fontSize, true); //Creats the font	
			}else if (lastDirFileSeparator != -1){
				p5Font = pa.createFont(fontFileName.substring(lastDirFileSeparator+1, fontFileName.length()), fontSize, true); //Creats the font
			}
			else{
				p5Font = pa.loadFont(fontFileName);
			}
		}
		return p5Font;
	}
	
	
	
	/**
	 * Creates the specified characters.
	 * 
	 * @param pa the pa
	 * @param chars the chars
	 * @param fillColor the fill color
	 * @param strokeColor the stroke color
	 * @param fontFileName the font file name
	 * @param fontSize the font size
	 * @return the characters
	 */
	public List<BitmapFontCharacter> getCharacters(PApplet pa, 
			String chars,
			MTColor fillColor, 
			MTColor strokeColor,
			String fontFileName, 
			int fontSize
	){
		PFont p5Font = this.getProcessingFont(pa, fontFileName, fontSize);
		return createCharacters(pa, p5Font, chars, fillColor, strokeColor);
	}
	
	
	private List<BitmapFontCharacter> createCharacters(PApplet pa, PFont p5Font, String chars, MTColor fillColor, MTColor strokeColor){
		List<BitmapFontCharacter> bitMapCharacters = new ArrayList<BitmapFontCharacter>();
		
		for (int i = 0; i < chars.length(); i++) {
			char c = chars.charAt(i);
//			int charIndex = p5Font.index(c);
			Glyph glyph = p5Font.getGlyph(c);
			if (glyph != null){
				PImage charImage = glyph.image;
				int charWidth = glyph.width;
				int charHeight = glyph.height;
				int topExtend = glyph.topExtent;
				int leftExtend = glyph.leftExtent;
				int widthDisplacement = glyph.setWidth;
				

				//int topOffset = p5Font.descent + (-charHeight - (topExtend-charHeight)); //ORIGINAL
				int topOffset =  (-charHeight - (topExtend-charHeight));
				
				//Copy the actual font data on the image from the upper left corner 1 pixel
				//into the middle of the image to avoid anti aliasing artefacts at the corners
//				PImage copy = new PImage(charImage.width, charImage.height, PImage.ARGB); //ORG

//				for (int j = 0; j < charImage.pixels.length; j++) { //ORG
//					int d = charImage.pixels[j];
//					/*
//						int a = d >> 24 & 0xFF;
//						int r = d >> 16 & 0xFF;
//						int g = d >> 8 & 0xFF;
//						int b = d & 0xFF;
//						logger.debug("R: " + r + " G:" + g + " B:" + " A:" + a);
//					 */
//					charImage.pixels[j] = (d << 24) | 0x00FFFFFF; //ORIGINAL! //make it white
////					charImage.pixels[j] = (d << 24) | pa.color(fillColor.getR(), fillColor.getG(), fillColor.getB(), 0);
////					charImage.pixels[j] = (charImage.pixels[j] << 24) | 0x00FFFFFF;
//					//charImage.format = PConstants.ARGB;
//					
//					//Clear the copy image in the same loop
//					copy.pixels[j] = (copy.pixels[j] << 24) | 0x00FFFFFF; //Original! //make it white
////					copy.pixels[j] = (d << 24) | 0x00FFFFFF; //Original! //make it white
//				}
				
				for (int j = 0; j < charImage.pixels.length; j++) { //ORG
					charImage.pixels[j] = (charImage.pixels[j] << 24) | 0x00FFFFFF; //ORIGINAL! //make it white
				}
				
				//Shift character image data down and right in the image because of aliasing artifacts at the border
				//we need to compensate for this when displaying the char
				//FIXME this creates far to big images..but because of artefacts needed..?
				int topShiftAmount = 4;
				int leftShiftAmount = 4;
				
//				PImage copy = new PImage(ToolsMath.nearestPowerOfTwo(charWidth + shiftAmount), ToolsMath.nearestPowerOfTwo(charHeight + shiftAmount), PImage.ARGB);
//				
				PImage copy = new PImage(nextPowerOfTwo(charImage.width + leftShiftAmount + 1), nextPowerOfTwo(charImage.height + topShiftAmount), PImage.ARGB);
//				PImage copy = new PImage(charImage.width + leftShiftAmount + 1, charImage.height + topShiftAmount, PImage.ARGB);
				
				copy.copy(charImage, 0, 0, charWidth, charHeight, leftShiftAmount, topShiftAmount, charWidth, charHeight);
				
//				copy.copy(charImage, 0, 0, charImage.width, charImage.height, leftShiftAmount, topShiftAmount, charImage.width, charImage.height);
				
//				copy.copy(charImage, 0, 0, charWidth, charHeight, shiftAmount, shiftAmount, charWidth, charHeight);
//				copy.copy(charImage, 0, 0, charImage.width, charImage.height, shiftAmount, shiftAmount, charImage.width, charImage.height);
//				copy.copy(charImage, 0, 0, charImage.width, charImage.height, shiftAmount, shiftAmount, charImage.width, charImage.height);
//				copy.copy(charImage, 0, 0, charWidth, charHeight, shiftAmount, shiftAmount, charWidth, charHeight);
				
				charImage = copy;
				
				//FIXME the topoffset is smaller than with the vector font! check that!
				//FIXME anti aliasing artefacts may also stem from using a perspective and not ortho camera!!
				//FIXME space character too wide..
				
				//Move the character to compensate for the shifting of the image
				topOffset -= topShiftAmount; //org shiftamount 
				leftExtend -= leftShiftAmount;
				
				//FIXME TEST
//				if (c == 'i'){
//					copy.save(MT4jSettings.DEFAULT_IMAGES_PATH + "i.png");
//				}
				
				//Create bitmap font character
				String StringChar = new Character(c).toString();
				BitmapFontCharacter character = new BitmapFontCharacter(charImage, pa, StringChar, leftExtend, topOffset, widthDisplacement);
				character.setName(StringChar);
				character.setFillColor(new MTColor(fillColor));
				if (MT4jSettings.getInstance().isOpenGlMode()){
					character.generateAndUseDisplayLists();
				}
				bitMapCharacters.add(character);
				//logger.debug("Char: " + c + " charWidth: " + charWidth +  " leftExtend: " + leftExtend + " widthDisplacement: " + widthDisplacement + " imageHeight: " + charImage.height + " charHeight: " + charHeight +  " topExtent: " + topExtend);
			}else{
				logger.warn("Couldnt create bitmap character : " + c + " -> not found!");
			}
		}
		return bitMapCharacters;
	}
	
	private int nextPowerOfTwo(int val) {
	      int ret = 1;
	      while (ret < val) {
	        ret <<= 1;
	      }
	      return ret;
	    }

//	
//	  /**
//	   * Create a .vlw font on the fly from either a font name that's
//	   * installed on the system, or from a .ttf or .otf that's inside
//	   * the data folder of this sketch.
//	   * <P/>
//	   * Many .otf fonts don't seem to be supported by Java, perhaps because 
//	   * they're CFF based?
//	   * <P/>
//	   * Font names are inconsistent across platforms and Java versions.
//	   * On Mac OS X, Java 1.3 uses the font menu name of the font,
//	   * whereas Java 1.4 uses the PostScript name of the font. Java 1.4
//	   * on OS X will also accept the font menu name as well. On Windows,
//	   * it appears that only the menu names are used, no matter what
//	   * Java version is in use. Naming system unknown/untested for 1.5.
//	   * <P/>
//	   * Use 'null' for the charset if you want to dynamically create
//	   * character bitmaps only as they're needed. (Version 1.0.9 and
//	   * earlier would interpret null as all unicode characters.)
//	   */
//	  public PFont createFont(PApplet app, String name, float size,
//	                          boolean smooth, char charset[]) {
//	    String lowerName = name.toLowerCase();
//	    Font baseFont = null;
//
//	    try {
//	      InputStream stream = null;
//	      if (lowerName.endsWith(".otf") || lowerName.endsWith(".ttf")) {
//	        stream = app.createInput(name);
//	        if (stream == null) {
//	          System.err.println("The font \"" + name + "\" " +
//	                             "is missing or inaccessible, make sure " +
//	                             "the URL is valid or that the file has been " +
//	                             "added to your sketch and is readable.");
//	          return null;
//	        }
//	        baseFont = Font.createFont(Font.TRUETYPE_FONT, app.createInput(name));
//
//	      } else {
//	        baseFont = PFont.findFont(name);
//	      }
//	      return new PFont(baseFont.deriveFont(size), smooth, charset, 
//	                       stream != null);
//
//	    } catch (Exception e) {
//	      System.err.println("Problem createFont(" + name + ")");
//	      e.printStackTrace();
//	      return null;
//	    }
//	  }
//	  
//	 private class MYPFont extends PFont{
//		 
//		 public void getGlyphImage(){
//			 getGlyph('a');
//		 }
//		 
//		 public class bla extends PFont.Glyph{
//			 
//		 }
//		 
//	 }


}
