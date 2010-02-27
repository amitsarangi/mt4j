/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009 C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
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

package org.mt4j.util.modelImporter.fileObj;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import javax.media.opengl.GL;

import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.opengl.GLMaterial;
import org.mt4j.util.opengl.GLTexture;

import processing.core.PApplet;
import processing.core.PImage;


class ObjectFileMaterials implements ImageObserver {
    // DEBUG
    // 1 = Name of materials
    // 16 = Tokens
    private static final int DEBUG = 0;

    private String curName = null;
    private ObjectFileMaterial cur = null;

    private HashMap materials;			// key=String name of material
						// value=ObjectFileMaterial
    
    private Map<String, PImage> textureCache = new WeakHashMap<String, PImage>();
    PApplet pa;
 
    private String basePath;
    private boolean fromUrl;

    private class ObjectFileMaterial {
//  	public Color3f Ka;
//  	public Color3f Kd;
//  	public Color3f Ks;
    	public float[] Ka;
    	public float[] Kd;
    	public float[] Ks;

    	public int illum;
    	public float Ns;
//  	public Texture2D t;
    	public PImage t;
    	public boolean transparent;
    	public float transparencyLevel;

    	public PImage d;

    }
    
    
   

    void assignMaterial(GL gl, String matName, AbstractShape shape) {
    	ObjectFileMaterial p = null;

    	if ((DEBUG & 1) != 0) 
    		System.out.println("Color " + matName);
    	
    	GLMaterial m = new GLMaterial(gl);
    	p = (ObjectFileMaterial)materials.get(matName);
//    	Appearance a = new Appearance();

    	if (p != null) {
    		
    		// Set ambient & diffuse color
    		if (p.Ka != null) 
//    			m.setAmbient(p.Ka);
    			m.setAmbient(new float[]{p.Ka[0],p.Ka[1],p.Ka[2], 1.0f}); //we have to add the last value, else material will not be opaque
    		
    		if (p.Kd != null) 
//    			m.setDiffuse(p.Kd);
    			m.setDiffuse(new float[]{p.Kd[0],p.Kd[1],p.Kd[2], 1.0f});

    		// Set specular color
    		if ((p.Ks != null) && (p.illum != 1)) 
//    			m.setSpecular(p.Ks);
    			m.setSpecular(new float[]{p.Ks[0],p.Ks[1],p.Ks[2], 1.0f});
    		else if (p.illum == 1) 
    			m.setSpecular(new float[]{0.0f, 0.0f, 0.0f, 1.0f});

//  		if (p.illum >= 1) m.setLightingEnable(true);
//  		else if (p.illum == 0) m.setLightingEnable(false);

    		if (p.Ns != -1.0f) 
    			m.setShininess(p.Ns);
    		

    		if (p.t != null) {
    			PImage tex = p.t;
    			
//    			/*
    			//Apply alpha mask from transparancy map to the texture
    			if (p.d != null){
    				//System.out.println("Trying to add alpha mask for material: " + matName);
    				
        			PImage alphaMap = p.d;
        			if (alphaMap.width == tex.width && alphaMap.height == tex.height){
        				tex.mask(alphaMap);
            			
            			if (tex instanceof GLTexture) {
    						GLTexture glTex = (GLTexture) tex;
    						glTex.putPixelsIntoTexture(tex);
    					}
        			}else{
        				//System.out.println("Alpha map isnt the same size as the texture for material: " + matName);
        			}
        		}
//    			*/
    			
    			shape.setTexture(tex);
    			System.out.println("Texture assigned to object: " + shape.getName());
    		}

//    		if (p.transparent) 
//    			a.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST,p.transparencyLevel));
    		
    		shape.setMaterial(m);
    	}else{
    		System.err.println("No material \"" + matName + "\" found for object " + shape.getName());
    	}
    	
    	if ((DEBUG & 1) != 0) 
    		System.out.println(m);
    } // End of assignMaterial


    private void readName(ObjectFileParser st) throws ParsingErrorException {
    	st.getToken();

    	if (st.ttype == ObjectFileParser.TT_WORD) {

    		if (curName != null) materials.put(curName, cur);
    		curName = new String(st.sval);
    		cur = new ObjectFileMaterial();
    	}
    	st.skipToNextLine();
    } // End of readName


    private void readAmbient(ObjectFileParser st) throws ParsingErrorException {
//  	Color3f p = new Color3f();
    	float[] p = new float[3];

    	st.getNumber();
    	p[0] = (float)st.nval;
    	st.getNumber();
    	p[1] = (float)st.nval;
    	st.getNumber();
    	p[2] = (float)st.nval;

    	cur.Ka = p;

    	st.skipToNextLine();
    } // End of readAmbient


    private void readDiffuse(ObjectFileParser st) throws ParsingErrorException {
//    	Color3f p = new Color3f();
    	float[] p = new float[3];

    	st.getNumber();
    	p[0] = (float)st.nval;
    	st.getNumber();
    	p[1] = (float)st.nval;
    	st.getNumber();
    	p[2] = (float)st.nval;

    	cur.Kd = p;

    	st.skipToNextLine();
    } // End of readDiffuse


    private void readSpecular(ObjectFileParser st) throws ParsingErrorException {
//    	Color3f p = new Color3f();
    	float[] p = new float[3];

    	st.getNumber();
    	p[0] = (float)st.nval;
    	st.getNumber();
    	p[1] = (float)st.nval;
    	st.getNumber();
    	p[2] = (float)st.nval;

    	cur.Ks = p;

    	st.skipToNextLine();
    } // End of readSpecular


    private void readIllum(ObjectFileParser st) throws ParsingErrorException {
    	st.getNumber();
    	cur.illum = (int)st.nval;

    	st.skipToNextLine();
    } // End of readSpecular

    private void readTransparency(ObjectFileParser st) throws ParsingErrorException {
    	st.getNumber();
    	cur.transparencyLevel = (float)st.nval;
    	if ( cur.transparencyLevel < 1.0f ){
    		cur.transparent = true;
    	}
    	st.skipToNextLine();
    } // End of readTransparency

    private void readShininess(ObjectFileParser st) throws ParsingErrorException {
    	float f;

    	st.getNumber();
    	cur.Ns = (float)st.nval;
    	if (cur.Ns < 1.0f) cur.Ns = 1.0f;
    	else if (cur.Ns > 128.0f) cur.Ns = 128.0f;

    	st.skipToNextLine();
    } // End of readSpecular


    
    public void readMapKd(ObjectFileParser st) {
    	// Filenames are case sensitive
    	st.lowerCaseMode(false);

    	// Get name of texture file (skip path)
    	String tFile = "";
    	do {
    		st.getToken();
//    		if (st.ttype == ObjectFileParser.TT_WORD){ 
//    			tFile += st.sval;
//    			
//    		}
    		if (st.ttype != ObjectFileParser.TT_EOL ){ 
    			tFile += st.sval; 
    		}
    		
    	} while (st.ttype != ObjectFileParser.TT_EOL);

    	st.lowerCaseMode(true);

    	if (tFile != "") {
    		
    		PImage texture = null;
    		
    		// Check for filename with no extension
    		if (tFile.lastIndexOf('.') != -1) {
    			try {
    				// Convert filename to lower case for extension comparisons
    				String suffix = tFile.substring(tFile.lastIndexOf('.') + 1).toLowerCase();

//    				TextureLoader t = null;
    				
    				tFile = toLowerCase(tFile);

    				if ((suffix.equals("int")) || (suffix.equals("inta")) ||
    						(suffix.equals("rgb")) || (suffix.equals("rgba")) ||
    						(suffix.equals("bw")) || (suffix.equals("sgi"))
    				) {
    					
    					PImage cachedImage = textureCache.get(tFile);
    					if (cachedImage != null){
    						texture = cachedImage;
    						//System.out.println("->Loaded texture from CACHE : \"" + tFile + "\"");
    					}else{
    						File textureFile = new File(basePath + tFile);
    						if (textureFile.exists()){
    							boolean success = textureFile.renameTo(new File(basePath + tFile));
    						    if (!success) {
    						        // File was not successfully renamed
    						    	System.out.println("failed to RENAME file: " + textureFile.getAbsolutePath());
    						    }
    						    
    						    if (MT4jSettings.getInstance().isOpenGlMode()){
    						    	texture = new GLTexture(pa, basePath + tFile);
    						    }else{
    						    	texture = pa.loadImage(basePath + tFile);
    						    }
    						    textureCache.put(tFile, texture);
    						}
    					}
    					
//    					RgbFile f;
//    					if (fromUrl) {
//    						f = new RgbFile(new URL(basePath + tFile).openStream());
//    					} else {
//    						f = new RgbFile(new FileInputStream(basePath + tFile));
//    					}
//    					BufferedImage bi = f.getImage();

    					boolean luminance = suffix.equals("int") || suffix.equals("inta");
    					boolean alpha = suffix.equals("inta") || suffix.equals("rgba");
    					cur.transparent = alpha;

    					String s = null;
    					if (luminance && alpha) s = "LUM8_ALPHA8";
    					else if (luminance) s = "LUMINANCE";
    					else if (alpha) s = "RGBA";
    					else s = "RGB";

//    					t = new TextureLoader(bi, s, TextureLoader.GENERATE_MIPMAP);
    				} else {
//    					tFile.toLowerCase();
//    					tFile.toLowerCase(Locale.ENGLISH);
//    					basePath.toLowerCase();
    					
    					PImage cachedImage = textureCache.get(tFile);
    					if (cachedImage != null){
    						texture = cachedImage;
    						//System.out.println("->Loaded texture from CACHE : \"" + tFile + "\"");
    					}else{
    						File textureFile = new File(basePath + tFile);
    						if (textureFile.exists()){
    							boolean success = textureFile.renameTo(new File(basePath + tFile));
    						    if (!success) {
    						        // File was not successfully renamed
    						    	System.out.println("failed to RENAME file: " + textureFile.getAbsolutePath());
    						    }
    						    
    						    if (MT4jSettings.getInstance().isOpenGlMode()){
    						    	texture = new GLTexture(pa, basePath + tFile);
    						    }else{
    						    	texture = pa.loadImage(basePath + tFile);
    						    }
    						    textureCache.put(tFile, texture);
    						}
    					}

//    					if (ConstantsAndSettings.getInstance().isOpenGlMode()){
//    						GLTexture glTex = new GLTexture(pa, basePath + tFile);
//    		    			texture = glTex;
//    		    		}else{
//    		    			texture = pa.loadImage(basePath + tFile);
//    		    		}
    					
//    					// For all other file types, use the TextureLoader
//    					if (fromUrl) {
//    						t = new TextureLoader(new URL(basePath + tFile), "RGB",
//    								TextureLoader.GENERATE_MIPMAP, null);
//    					} else {
//    						t = new TextureLoader(basePath + tFile, "RGB",
//    								TextureLoader.GENERATE_MIPMAP, null);
//    					}
    				}
    				
    				
//    				Texture2D texture = (Texture2D)t.getTexture();
    				
    				
    				if (texture != null) 
    					cur.t = texture;
    			}
    			catch (Exception e) {
    				// Texture won't get loaded if file can't be found
    				e.printStackTrace();
    			}
//    			catch (MalformedURLException e) {
//    				// Texture won't get loaded if file can't be found
//    			}
//    			catch (IOException e) {
//    				// Texture won't get loaded if file can't be found
//    			}
    		}
    	}
    	st.skipToNextLine();
    } // End of readMapKd


    /**
     * 
     * @param string
     * @return
     */
    public String toLowerCase(String string){
    	char[] chars = new char[string.length()];
		for (int i = 0; i < chars.length; i++) {
			char c = string.charAt(i);
			c = Character.toLowerCase(c);
			chars[i] = c;
		}
		return new String(chars);
    }
    
    public void readMapD(ObjectFileParser st) {
    	// Filenames are case sensitive
    	st.lowerCaseMode(false);
    	// Get name of texture file (skip path)
    	String tFile = "";
    	
    	do {
    		st.getToken();
//    		if (st.ttype == ObjectFileParser.TT_WORD){ 
//    			tFile += st.sval;
//    		}
    		if (st.ttype != ObjectFileParser.TT_EOL ){ 
    			tFile += st.sval; 
    		}
    	}while (st.ttype != ObjectFileParser.TT_EOL);

    	st.lowerCaseMode(true);

    	if (tFile != "") {
    		PImage alphaMap;
    		// Check for filename with no extension
    		if (tFile.lastIndexOf('.') != -1) {
    			try {
    				// Convert filename to lower case for extension comparisons
    				String suffix = tFile.substring(tFile.lastIndexOf('.') + 1).toLowerCase();

    				if ((suffix.equals("int")) || (suffix.equals("inta")) ||
    						(suffix.equals("rgb")) || (suffix.equals("rgba")) ||
    						(suffix.equals("bw")) || (suffix.equals("sgi"))
    				) {
    					tFile = toLowerCase(tFile);
    					alphaMap = pa.loadImage(basePath + tFile);
    				} else {
    					tFile = toLowerCase(tFile);
    					alphaMap = pa.loadImage(basePath + tFile);
    				}
    				
    				if (alphaMap != null){ 
    					cur.d = alphaMap;
    				}
    			}catch (Exception e) {
    				// Texture won't get loaded if file can't be found
    				e.printStackTrace();
    			}
    		}
    	}
    	st.skipToNextLine();
    } // End of readMapKd
    
    
    /**
     * 
     * @param st
     * @throws ParsingErrorException
     */
    private void readFile(ObjectFileParser st) throws ParsingErrorException {
    	int t;
    	st.getToken();
    	while (st.ttype != ObjectFileParser.TT_EOF) {

    		// Print out one token for each line
    		if ((DEBUG & 16) != 0) {
    			System.out.print("Token ");
    			if (st.ttype == ObjectFileParser.TT_EOL) System.out.println("EOL");
    			else if (st.ttype == ObjectFileParser.TT_WORD)
    				System.out.println(st.sval);
    			else System.out.println((char)st.ttype);
    		}

    		if (st.ttype == ObjectFileParser.TT_WORD) {
    			if (st.sval.equals("newmtl")) {
    				readName(st);
    			} else if (st.sval.equals("ka")) {
    				readAmbient(st);
    			} else if (st.sval.equals("kd")) {
    				readDiffuse(st);
    			} else if (st.sval.equals("ks")) {
    				readSpecular(st);
    			} else if (st.sval.equals("illum")) {
    				readIllum(st);
    			} else if (st.sval.equals("d")) {
    				readTransparency(st);
    			} else if (st.sval.equals("ns")) {
    				readShininess(st);
    			} else if (st.sval.equals("tf")) {
    				st.skipToNextLine();
    			} else if (st.sval.equals("sharpness")) {
    				st.skipToNextLine();
    			} else if (st.sval.equals("map_kd")) {
    				readMapKd(st);
    			} else if (st.sval.equals("map_ka")) {
    				st.skipToNextLine();
    			} else if (st.sval.equals("map_ks")) {
    				st.skipToNextLine();
    			} else if (st.sval.equals("map_ns")) {
    				st.skipToNextLine();
    			} else if (st.sval.equals("bump")) {
    				st.skipToNextLine();
    			}else if (st.sval.equals("map_d")) {
    				readMapD(st);
    			}
    		}

    		st.skipToNextLine();

    		// Get next token
    		st.getToken();
    	}
    	if (curName != null) materials.put(curName, cur);
    } // End of readFile


    /**
     * 
     * @param basePath
     * @param fileName
     * @throws ParsingErrorException
     */
    void readMaterialFile(String basePath, String fileName)   throws ParsingErrorException {
    	Reader reader;

    	this.basePath = basePath;
//  	this.fromUrl = fromUrl;

    	try {
    		if (fromUrl) {
    			reader = (Reader)
    			(new InputStreamReader(
    					new BufferedInputStream(
    							(new URL(basePath + fileName).openStream()))));
    		} else {
    			reader = new BufferedReader(new FileReader(basePath + fileName));
    		}
    	}
    	catch (IOException e) {
    		// couldn't find it - ignore mtllib
    		e.printStackTrace();
    		return;
    	}
    	if ((DEBUG & 1) != 0)
    		System.out.println("Material file: " + basePath + fileName);

    	ObjectFileParser st = new ObjectFileParser(reader);
    	readFile(st);
    }  // End of readMaterialFile


    ObjectFileMaterials() throws ParsingErrorException {
//    	Reader reader = new StringReader(DefaultMaterials.materials);
//
//    	ObjectFileParser st = new ObjectFileParser(reader);
    	materials = new HashMap(50);
//    	readFile(st);
    } // End of ObjectFileMaterials


    /**
     * Implement the ImageObserver interface.  Needed to load jpeg and gif
     * files using the Toolkit.
     */
    public boolean imageUpdate(Image img, int flags,
    		int x, int y, int w, int h) {

    	return (flags & (ALLBITS | ABORT)) == 0;
    } // End of imageUpdate

} // End of class ObjectFileMaterials

// End of file ObjectFileMaterials.java
