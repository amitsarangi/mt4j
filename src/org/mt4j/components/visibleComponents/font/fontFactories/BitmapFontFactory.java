/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009 Christopher Ruff, Fraunhofer-Gesellschaft All rights reserved.
 *  
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ***********************************************************************/
package org.mt4j.components.visibleComponents.font.fontFactories;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.mt4j.components.visibleComponents.font.BitmapFont;
import org.mt4j.components.visibleComponents.font.BitmapFontCharacter;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

/**
 * A factory for creating BitmapFont objects.
 * @author Christopher Ruff
 */
public class BitmapFontFactory implements IFontFactory {
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(BitmapFontFactory.class.getName());
	static{
//		logger.setLevel(Level.ERROR);
//		logger.setLevel(Level.WARN);
		logger.setLevel(Level.DEBUG);
		SimpleLayout l = new SimpleLayout();
		ConsoleAppender ca = new ConsoleAppender(l);
		logger.addAppender(ca);
	}
	
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
//		char[] chars = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','Ä','Ö','Ü','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','ä','ö','ü','<','>','|',',',';','.',':','-','_','#','\'','+','*','!','?','\\','$','%','&','/','(',')','=','´','{','[',']','}','@',' '};
		String characterString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜabcdefghijklmnopqrstuvwxyzäöü<>|,;.:-_#'+*!\"§$%&/()=?´{[]}\\@";
		List<BitmapFontCharacter> bitMapCharacters = this.createCharacters(pa, p5Font, characterString, fillColor, strokeColor);
//		List<BitmapFontCharacter> bitMapCharacters = this.getCharacters(pa, chars, fillColor, strokeColor, fontFileName, fontSize);  
	
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
		String fontFamily = p5Font.psname;
//		String fontFamily = f.getFamily(); 
		int fontMaxAscent = p5Font.ascent;
		int fontMaxDescent = p5Font.descent;
		//TODO INFO: because in vector font this is a negative value, too
		Font f = p5Font.getFont();
		if (f != null){
			FontMetrics fm = pa.getFontMetrics(f);
			fontMaxDescent = fm.getDescent();
		}
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
//		newLine.setSizeLocal(defaultHorizontalAdvX, defaultHorizontalAdvX);
		newLine.setPickable(false);						    		
		newLine.setVisible(false);
		newLine.setNoFill(true);
		newLine.setNoStroke(true);
		newLine.setName("newline");
		bitMapCharacters.add(newLine);
		
		//Manually add a SPACE character to the font
//		int spaceAdvancex = defaultHorizontalAdvX;
//		int spaceAdvancex = fm.charWidth(' '); 
		int spaceIndex = p5Font.index('-');
		int spaceAdvancex = p5Font.width[spaceIndex];
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
		//TODO eureka numbers baseline wrong?
		
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
			int lastDirSeparator = fontFileName.lastIndexOf(java.io.File.separator);
			if (lastDirSeparator != -1){
				p5Font = pa.createFont(fontFileName.substring(lastDirSeparator+1, fontFileName.length()), fontSize, true); //Creats the font?	
			}else{
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
//		for (int i = 0; i < chars.length; i++) {
//			char c = chars[i];
			int charIndex = p5Font.index(c);
			if (charIndex != -1){
				PImage charImage = p5Font.images[charIndex];
				int charWidth = p5Font.width[charIndex];
				int charHeight = p5Font.height[charIndex];
				int topExtend = p5Font.topExtent[charIndex];
				int leftExtend = p5Font.leftExtent[charIndex];
				int widthDisplacement = p5Font.setWidth[charIndex];

				//float high    = (float) p5Font.height[charIndex]     / p5Font.fheight;
				//float bwidth  = (float) p5Font.width[charIndex]      / p5Font.fwidth;
				//float lextent = (float) p5Font.leftExtent[charIndex] / p5Font.fwidth;
				//float textent = (float) p5Font.topExtent[charIndex]  / p5Font.fheight;

				//int topOffset = p5Font.descent + (-charHeight - (topExtend-charHeight)); //ORIGINAL
				int topOffset =  (-charHeight - (topExtend-charHeight));

//				/*
				for (int j = 0; j < charImage.pixels.length; j++) {
					int d = charImage.pixels[j];
					/*
						int a = d >> 24 & 0xFF;
						int r = d >> 16 & 0xFF;
						int g = d >> 8 & 0xFF;
						int b = d & 0xFF;
						logger.debug("R: " + r + " G:" + g + " B:" + " A:" + a);
					 */
					charImage.pixels[j] = (d << 24) | 0x00FFFFFF; //ORIGINAL! //make it white
					//						charImage.pixels[j] = (d << 24) | pa.color(fillRed, fillGreen, fillBlue, 0);
					charImage.format = PConstants.ARGB;
				}

//				/*
				//Copy the actual font data on the image from the upper left corner 1 pixel
				//into the middle of the image to avoid anti aliasing artefacts at the corners
				PImage copy = new PImage(charImage.width, charImage.height, PImage.ARGB);
				//Clear transparent
				for (int j = 0; j < copy.pixels.length; j++) {
					copy.pixels[j] = (copy.pixels[j] << 24) | 0x00FFFFFF; //Original! //make it white
					//						copy.pixels[j] = (copy.pixels[j] << 24) | pa.color(fillRed, fillGreen, fillBlue, 0);
				}
				int shiftAmount = 1;
				copy.copy(charImage, 0, 0, charWidth, charHeight, shiftAmount, shiftAmount, charWidth, charHeight);
				charImage = copy;
				//Move the character to compensate for the shifting of the image
				topOffset -= shiftAmount;
				leftExtend -= shiftAmount;
//				*/
//				*/

				//Create bitmap font character
				String StringChar = new Character(c).toString();
				BitmapFontCharacter character = new BitmapFontCharacter(charImage, pa, StringChar, leftExtend, topOffset, widthDisplacement);
				character.setName(StringChar);
				character.setFillColor(new MTColor(fillColor.getR(), fillColor.getG(), fillColor.getB(), fillColor.getAlpha()));
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

}
