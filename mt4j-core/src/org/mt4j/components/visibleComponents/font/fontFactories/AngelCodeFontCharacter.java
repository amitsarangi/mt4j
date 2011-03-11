package org.mt4j.components.visibleComponents.font.fontFactories;

import java.nio.FloatBuffer;

import org.mt4j.components.visibleComponents.font.IFontCharacter;
import org.mt4j.components.visibleComponents.font.ITextureFontCharacter;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.math.Vertex;
import org.mt4j.util.opengl.GL10;
import org.mt4j.util.opengl.GLTexture;
import org.mt4j.util.opengl.GLTexture.EXPANSION_FILTER;
import org.mt4j.util.opengl.GLTexture.SHRINKAGE_FILTER;
import org.mt4j.util.opengl.GLTexture.WRAP_MODE;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class AngelCodeFontCharacter extends MTRectangle implements IFontCharacter, ITextureFontCharacter {
	private short x;
	
	private short y;
	
	private short width;
	
	private short height;
	
	private short xoffset;
	
	/** The amount the y position should be offset when drawing the image */
	private short yoffset;
	
	private PImage fontImage;

	private String unicode;

	private int horizontalAdvance;

	private int[] padding;
	
	
	public AngelCodeFontCharacter(PApplet app, PImage fontImage, String unicode, short x, short y, short width, short height, short xOffset, short yOffset, int horizontalAdvance, int[] paddingVals){
		super(app, xOffset, yOffset, width, height);
		
//		super(app, xOffset - (short)paddingVals[0], yOffset - (short)paddingVals[1], width - (short)paddingVals[0], height - (short)paddingVals[1]);
		
//		super(app, xOffset - paddingVals[0], yOffset - paddingVals[1], width, height);
		
		this.setTextureEnabled(true);
		this.setNoStroke(true); 
		this.setPickable(false);
		
		this.fontImage = fontImage;
		
		this.unicode = unicode;
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.xoffset = xOffset;
		this.yoffset = yOffset;
		
		this.horizontalAdvance = horizontalAdvance;
		
		this.padding = paddingVals;
		
		//TODO have to handle p3d mode by hand!? -> setTexture(fontImage) -> texcoords etc
		
		float x_ = (float)x/(float)fontImage.width;
		float y_ = (float)y/(float)fontImage.height;
		
		float x__ = (float)(x+width) / (float)fontImage.width;
		float y__ = (float)(y+height) / (float)fontImage.height;
		
//		x_ -= 0.001f;
//		y_ -= 0.001f;
//		
//		x__ -= 0.001f;
//		y__ -= 0.001f;
	
		Vertex[] v = this.getVerticesLocal();
		v[0].setTexCoordU(x_);
		v[0].setTexCoordV(y_);
		
		v[1].setTexCoordU(x__);
		v[1].setTexCoordV(y_);
		
		v[2].setTexCoordU(x__);
		v[2].setTexCoordV(y__);
		
		v[3].setTexCoordU(x_);
		v[3].setTexCoordV(y__);
		
		v[4].setTexCoordU(x_);
		v[4].setTexCoordV(y_);
		
//		v[0].setTexCoordU(0);
//		v[0].setTexCoordV(0);
//		
//		v[1].setTexCoordU(1);
//		v[1].setTexCoordV(0);
//		
//		v[2].setTexCoordU(0 + 1);
//		v[2].setTexCoordV(0 + 1);
//		
//		v[3].setTexCoordU(0);
//		v[3].setTexCoordV(0 + 1);
//		
//		v[4].setTexCoordU(0);
//		v[4].setTexCoordV(0);
		
        this.getGeometryInfo().updateTextureBuffer(this.isUseVBOs());
//        this.setVertices(v);
	}
	
	
	

	@Override
	public void drawComponent(PGraphics g) {
		//TODO if opengl mode -> set up gl drawing like in IFont beginBatch render, 
		//because in drawPureGL we dont set up everything needed if we only want to draw this single character
		super.drawComponent(g);
	}

	@Override
	public void drawComponent(GL10 gl) {
		if (MT4jSettings.getInstance().isOpenGlMode()){
			drawPureGl(gl);
		}
	}
	
	@Override
	protected void drawPureGl(GL10 gl){
		if (!this.isNoFill()){
			FloatBuffer tbuff 			= this.getGeometryInfo().getTexBuff(); 
			FloatBuffer vertBuff 		= this.getGeometryInfo().getVertBuff();
			
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
			
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, tbuff);

			gl.glDrawArrays(this.getFillDrawMode(), 0, vertBuff.capacity()/3);
		}
		
//		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
//		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, this.getGeometryInfo().getVertBuff().capacity()/3);
	}

	@Override
	public String getUnicode() {
		return this.unicode;
	}

	@Override
	public int getHorizontalDist() {
		return this.horizontalAdvance;
	}


	@Override
	public void setTextureFiltered(boolean scalable) {
		if (MT4jSettings.getInstance().isOpenGlMode()){
			PImage tex = this.getTexture();
			if (tex instanceof GLTexture) {
				GLTexture glTex = (GLTexture) tex;
				//normally we would use GL_LINEAR as magnification filter but sometimes
				//small text is too filtered and smudged so we use NEAREST -> but this makes
				//scaled text very ugly and pixelated..
				if (scalable){
//					glTex.setFilter(GL.GL_LINEAR, GL.GL_LINEAR);
					glTex.setFilter(SHRINKAGE_FILTER.BilinearNoMipMaps, EXPANSION_FILTER.Bilinear);
				}else{
//					glTex.setFilter(GL.GL_LINEAR, GL.GL_NEAREST); 
					glTex.setFilter(SHRINKAGE_FILTER.BilinearNoMipMaps, EXPANSION_FILTER.NearestNeighbor);
				}
			}
		}
	}


}
