package org.mt4j.css.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.clipping.Clip;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.components.visibleComponents.shapes.MTLine;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTImage;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.style.CSSStyleHierarchy;
import org.mt4j.css.style.CSSStyle.BackgroundRepeat;
import org.mt4j.css.util.CSSKeywords.Position;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;
import org.mt4j.util.opengl.GLTexture;
import org.mt4j.util.opengl.GLTextureSettings;
import org.mt4j.util.opengl.GLTexture.EXPANSION_FILTER;
import org.mt4j.util.opengl.GLTexture.SHRINKAGE_FILTER;
import org.mt4j.util.opengl.GLTexture.TEXTURE_TARGET;
import org.mt4j.util.opengl.GLTexture.WRAP_MODE;
import org.mt4jx.components.generic.MTSuggestionTextArea;


import processing.core.PImage;

// TODO: Auto-generated Javadoc
/**
 * The Class CSSHelper.
 */
public class CSSHelper {

	/** The private style sheets (unique to an object) */
	List<CSSStyle> privateStyleSheets = new ArrayList<CSSStyle>();

	/** The currently relevant global style sheet */
	List<CSSStyleHierarchy> sheets = new ArrayList<CSSStyleHierarchy>();
	
	/** The virtual style sheet (generated from the global and private style sheets) */
	public CSSStyle virtualStyleSheet = null;
	
	/** The CSS style manager. */
	CSSStyleManager cssStyleManager;
	
	/** The MTApplication. */
	MTApplication app;
	
	/** The MTComponent. */
	private MTComponent c;

	/**
	 * Instantiates a new CSS helper.
	 *
	 * @param c the MTComponent
	 * @param a the MTApplication
	 */
	public CSSHelper(MTComponent c, MTApplication a) {
		this.c = c;
		this.app = a;
		this.cssStyleManager = a.getCssStyleManager();
		addListeners();
	}

	/**
	 * Instantiates a new CSS helper.
	 *
	 * @param c the MTComponent
	 * @param a the MTApplication
	 * @param s the new private CSSStyle
	 */
	public CSSHelper(MTComponent c, MTApplication a, CSSStyle s) {
		this(c,a);
		this.getPrivateStyleSheets().add(s);
		

	}

	/**
	 * Instantiates a new CSS helper.
	 *
	 * @param c the MTComponent
	 * @param a the MTApplication
	 * @param s the list of private style sheets
	 */
	public CSSHelper(MTComponent c, MTApplication a, List<CSSStyle> s) {
		this(c,a);
		this.getPrivateStyleSheets().addAll(s);
		

	}

	/**
	 * Adds the listeners to the MTComponent, so applyStyleSheet() is called every time the component is added as child
	 */
	private void addListeners() {
		c.addStateChangeListener(StateChange.ADDED_TO_PARENT,
				new StateChangeListener() {
					@Override
					public void stateChanged(StateChangeEvent evt) {
						applyStyleSheet();
					}
				});

	}

	/**
	 * Apply the style sheet. Disambiguate between different subclasses of MTComponent
	 */
	public void applyStyleSheet() {
		
		if (c instanceof CSSStylableComponent) {
			CSSStylableComponent sc = (CSSStylableComponent)c;
			if (!sc.isCssForceDisabled() && ((sc.isCSSStyled() && !app.getCssStyleManager().isGloballyDisabled()) || app.getCssStyleManager().isGloballyEnabled())) {
				evaluateStyleSheets();
				if ((c instanceof MTPolygon) || (c instanceof MTLine)) {
					applyStyleSheetBasic((AbstractShape) c);
				}
				if (c instanceof MTPolygon) {
					applyStyleSheetPolygon((MTPolygon)c);
				}
				
				if (c instanceof MTRectangle) {
					applyStyleSheetRectangle((MTRectangle) c);
				}
				if (c instanceof MTTextArea) {
					applyStyleSheetTextArea((MTTextArea) c);
				}
				if (c instanceof MTEllipse) {
					applyStyleSheetEllipse((MTEllipse) c);
				}
				
				if (c instanceof MTSuggestionTextArea) {
					MTSuggestionTextArea sta = (MTSuggestionTextArea)c;
					sta.init();
				}
				
				for (MTComponent d : c.getChildren()) {
					if (d instanceof CSSStylableComponent) {
						CSSStylableComponent s = (CSSStylableComponent) d;
						s.applyStyleSheet();
					}
				}
			}
		}
	}

	/**
	 * Apply basic style sheet properties, applicable to all objects.
	 *
	 * @param p the AbstractShape (MTPolygon or MTLine)
	 */
	private void applyStyleSheetBasic(AbstractShape p) {

		if (virtualStyleSheet.isModifiedBackgroundColor())
			p.setFillColor(virtualStyleSheet.getBackgroundColor());
		if (virtualStyleSheet.isModifiedBorderColor())
			p.setStrokeColor(virtualStyleSheet.getBorderColor());
		if (virtualStyleSheet.isModifiedBorderWidth())
			p.setStrokeWeight(virtualStyleSheet.getBorderWidth());
		if (virtualStyleSheet.isModifiedVisibility())
			p.setVisible(virtualStyleSheet.isVisibility());
		
		if (virtualStyleSheet.isModifiedBorderStyle()) {
			if (virtualStyleSheet.getBorderStylePattern() >= 0) {
				p.setNoStroke(false);
				p.setLineStipple(virtualStyleSheet.getBorderStylePattern());
			} else {
				p.setNoStroke(true);
			}
		}
	}
	
	/**
	 * Apply style sheet specific to a MTPolygon (and subclasses)
	 *
	 * @param p the MTPolygon
	 */
	private void applyStyleSheetPolygon(MTPolygon p) {
		if (virtualStyleSheet.isModifiedBackgroundImage()) {
			setBackground(p);
		}
	}

	/**
	 * Apply style sheet to a MTEllipse.
	 *
	 * @param e the MTEllipse
	 */
	private void applyStyleSheetEllipse(MTEllipse e) {
		if (virtualStyleSheet.isModifiedHeight()) {
			if (virtualStyleSheet.isHeightPercentage()) {
				if (e.getParent() != null)
					e.setHeightXYRelativeToParent(virtualStyleSheet.getHeight() / 100f
							* e.getParent().getBounds()
									.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
			} else {
				e.setHeightXYRelativeToParent(virtualStyleSheet.getHeight());
			}
		}
		
		if (virtualStyleSheet.isModifiedWidth()) {
			if (virtualStyleSheet.isWidthPercentage()) {
				if (e.getParent() != null)
					e.setWidthXYRelativeToParent(virtualStyleSheet.getWidth() / 100f
							* e.getParent().getBounds()
									.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
			} else {
				e.setWidthXYRelativeToParent(virtualStyleSheet.getWidth());
			}
		}
	}

	/**
	 * Apply style sheet to a MTRectangle.
	 *
	 * @param r the MTRectangle
	 */
	private void applyStyleSheetRectangle(MTRectangle r) {
		
		if (virtualStyleSheet.isWidthPercentage()
				&& virtualStyleSheet.isHeightPercentage()) {
			if (r.getParent() != null) {
				if (virtualStyleSheet.getWidth() > 0)
					r.setWidthLocal(virtualStyleSheet.getWidth() / 100f
							* r.getParent().getBounds()
									.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));

				if (virtualStyleSheet.getHeight() > 0)
					r.setHeightLocal(virtualStyleSheet.getHeight()/ 100f
							* r.getParent().getBounds()
									.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));

			}
		} else if (virtualStyleSheet.isWidthPercentage()) {
			if (virtualStyleSheet.getWidth() > 0)
				r.setWidthLocal(virtualStyleSheet.getWidth() / 100f
						* r.getParent().getBounds()
								.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));

			if (virtualStyleSheet.getHeight() > 0)
				r.setHeightLocal(virtualStyleSheet.getHeight());
		} else if (virtualStyleSheet.isHeightPercentage()) {
			if (virtualStyleSheet.getWidth() > 0)
				r.setWidthLocal(virtualStyleSheet.getWidth());

			if (virtualStyleSheet.getHeight() > 0)
				r.setHeightLocal(virtualStyleSheet.getHeight() / 100f
						* r.getParent().getBounds()
								.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));

		} else {
			if (virtualStyleSheet.getWidth() > 0)
				r.setWidthLocal(virtualStyleSheet.getWidth());

			if (virtualStyleSheet.getHeight() > 0)
				r.setHeightLocal(virtualStyleSheet.getHeight());
		}
	}

	/**
	 * Apply style sheet to a MTTextArea
	 *
	 * @param ta the MTTextArea
	 */
	private void applyStyleSheetTextArea(MTTextArea ta) {
		if (!virtualStyleSheet.getFont().equals(
				cssStyleManager.getDefaultFont(app))
				&& !ta.isIgnoreCSSFont()) {
			ta.setFont(virtualStyleSheet.getFont());
		}
	}

	/**
	 * Evaluate the style sheets (in order of relevance).
	 */
	@SuppressWarnings("unchecked")
	private void evaluateStyleSheets() {
		sheets = cssStyleManager.getRelevantStyles(c);
		Collections.sort(sheets);
		virtualStyleSheet = new CSSStyle(app);
		for (CSSStyleHierarchy h : sheets) {
			virtualStyleSheet.addStyleSheet(h.getStyle());

		}
		for (CSSStyle s : privateStyleSheets) {
			virtualStyleSheet.addStyleSheet(s);
		}

	}

	/**
	 * Gets the private style sheets.
	 *
	 * @return the private style sheets
	 */
	public List<CSSStyle> getPrivateStyleSheets() {
		return privateStyleSheets;
	}

	/**
	 * Gets the currently relevant style sheets.
	 *
	 * @return the sheets
	 */
	public List<CSSStyleHierarchy> getSheets() {
		return sheets;
	}

	/**
	 * Gets the x distance (between a float and a Vertex)
	 *
	 * @param x the x-position
	 * @param v2 the vertex to compare to
	 * @return the x-distance
	 */
	public static float getXDistance(float x, Vertex v2) {
		float distance = v2.x - x;
		if (distance >= 0)
			return distance;
		else
			return -distance;

	}

	/**
	 * Gets the y distance (between a float and a vertex)
	 *
	 * @param y the y-position
	 * @param v2 the vertex to compare to
	 * @return the y-distance
	 */
	public static  float getYDistance(float y, Vertex v2) {
		float distance = v2.y - y;
		if (distance >= 0)
			return distance;
		else
			return -distance;
	}

	/**
	 * Sets the private style sheets.
	 *
	 * @param privateStyleSheets the new private style sheets
	 */
	public void setPrivateStyleSheets(List<CSSStyle> privateStyleSheets) {
		this.privateStyleSheets = privateStyleSheets;
	}

	/**
	 * Sets the relevant style sheets.
	 *
	 * @param sheets the new sheets
	 */
	public void setSheets(List<CSSStyleHierarchy> sheets) {
		this.sheets = sheets;
	}

	/**
	 * Adds a style sheet.
	 *
	 * @param sheet the new style sheet
	 */
	public void setStyleSheet(CSSStyle sheet) {
		this.privateStyleSheets.add(sheet);
	}

	/**
	 * Sets the texture for a tiled background.
	 *
	 * @param p the MTPolygon to apply it to
	 * @param bgImage the background-image
	 */
	private void setBackground(MTPolygon p) {
		PImage bgImage = virtualStyleSheet.getBackgroundImage();
		if (bgImage != null) {
		if (virtualStyleSheet.getBackgroundRepeat() != BackgroundRepeat.NONE) {
			
		boolean pot = Tools3D.isPowerOfTwoDimension(bgImage);
		boolean tiled = true;
		p.setFillColor(MTColor.WHITE);
		if (tiled) {
			// Generate texture coordinates to repeat the texture over the whole
			// background (works only with OpenGL)


			Vertex[] backgroundVertices = p.getVerticesLocal();

			float minx, miny;

			if (backgroundVertices.length > 0) {
				minx = backgroundVertices[0].x;
				miny = backgroundVertices[0].y;

				for (Vertex vtx : backgroundVertices) {
					if (vtx.x < minx)
						minx = vtx.x;
					if (vtx.y < miny)
						miny = vtx.y;
				}

				for (Vertex vtx : backgroundVertices) {
					vtx.setTexCoordU(getXDistance(minx, vtx)
							/ bgImage.width);
					vtx.setTexCoordV(getYDistance(miny, vtx)
							/ bgImage.height);
				}

			}

			// Update changed texture coordinates for opengl buffer drawing
			if (MT4jSettings.getInstance().isOpenGlMode())
				p.getGeometryInfo().updateTextureBuffer(p.isUseVBOs());
		}
		
		WRAP_MODE horizontal = WRAP_MODE.CLAMP, vertical = WRAP_MODE.CLAMP;
		switch (virtualStyleSheet.getBackgroundRepeat()) {
		case REPEAT:
			horizontal = WRAP_MODE.REPEAT;
			vertical = WRAP_MODE.REPEAT;
		case XREPEAT:
			horizontal = WRAP_MODE.REPEAT;
		case YREPEAT:
			vertical = WRAP_MODE.REPEAT;
		}
				
		
		if (MT4jSettings.getInstance().isOpenGlMode()) {

			GLTextureSettings g = new GLTextureSettings(
					TEXTURE_TARGET.TEXTURE_2D,
					SHRINKAGE_FILTER.BilinearNoMipMaps,
					EXPANSION_FILTER.Bilinear, horizontal,
					vertical);
			GLTexture tex;
			if (pot) {
				tex = new GLTexture(app, bgImage, g);
			} else {
				if (tiled) {
					g.target = TEXTURE_TARGET.RECTANGULAR;
					g.shrinkFilter = SHRINKAGE_FILTER.Trilinear; // Because NPOT texture with GL_REPEAT isnt supported
																	// -> gluBuild2Dmipmapds strechtes the texture to POT size

					tex = new GLTexture(app, bgImage, g);
				} else {
					g.target = TEXTURE_TARGET.RECTANGULAR;

					tex = new GLTexture(app, bgImage, g);
				}
			}
			p.setTexture(tex);
		} else {
			p.setTexture(bgImage);
		}
		} else {
			if (virtualStyleSheet.getBackgroundPosition() != null) {
			MTImage img = new MTImage(bgImage,app);
			p.addChild(img);
			img.setPickable(false);
			
			
			float xPos = 0;
			float yPos = 0;
			
			switch (virtualStyleSheet.getBackgroundPosition().getxType()) {
			case ABSOLUTE:
				xPos = virtualStyleSheet.getBackgroundPosition().getxPos();
				break;
			case RELATIVE:
				xPos = determineAbsolutePosition(p, virtualStyleSheet.getBackgroundPosition().getxPos(), true);
				break;
			case KEYWORD:
				xPos = determineAbsolutePosition(p, virtualStyleSheet.getBackgroundPosition().getxKeywordPosition(), true);
				break;
			}
			
			switch (virtualStyleSheet.getBackgroundPosition().getyType()) {
			case ABSOLUTE:
				xPos = virtualStyleSheet.getBackgroundPosition().getyPos();
				break;
			case RELATIVE:
				xPos = determineAbsolutePosition(p, virtualStyleSheet.getBackgroundPosition().getyPos(), false);
				break;
			case KEYWORD:
				xPos = determineAbsolutePosition(p, virtualStyleSheet.getBackgroundPosition().getyKeywordPosition(), false);
				break;
			}
			
				img.setPositionRelativeToParent(
						p.getVerticesLocal()[0].addLocal(calcPos(p, virtualStyleSheet.getBackgroundImage(), xPos, yPos)));
			
				
			Clip c = new Clip(app, p.getBounds().getVectorsLocal()[0].x,p.getBounds().getVectorsLocal()[0].y,p.getBounds().getWidthXY(TransformSpace.LOCAL),p.getBounds().getHeightXY(TransformSpace.LOCAL));	
				
			img.setClip(c);	
			//p.setChildClip(new Clip(p));
			
			
		}	else {
			p.setTexture(bgImage);
		} 
			
		}
		}
	}
	
	private float determineAbsolutePosition(MTPolygon p, Position po, boolean isHorizontal) {
		float returnValue = 0;
		if (isHorizontal) {
			switch (po) {
			case LEFT:
				return calcPos(p,virtualStyleSheet.getBackgroundImage(), 0,0).x;
			case RIGHT:
				return calcPos(p,virtualStyleSheet.getBackgroundImage(), p.getWidthXY(TransformSpace.LOCAL) - (float)virtualStyleSheet.getBackgroundImage().width,0).x;
			case CENTER:
				return calcPos(p,virtualStyleSheet.getBackgroundImage(), (p.getWidthXY(TransformSpace.LOCAL) / 2f) - ((float)virtualStyleSheet.getBackgroundImage().width/2f),0).x;
			}
		} else {
			switch (po) {
			case TOP:
				return calcPos(p,virtualStyleSheet.getBackgroundImage(), 0,0).y;
			case BOTTOM:
				return calcPos(p, virtualStyleSheet.getBackgroundImage(), 0, p.getHeightXY(TransformSpace.LOCAL) - (float)virtualStyleSheet.getBackgroundImage().height).y;
			case CENTER:
				return calcPos(p, virtualStyleSheet.getBackgroundImage(), 0, (p.getHeightXY(TransformSpace.LOCAL)/2f) - ((float)virtualStyleSheet.getBackgroundImage().height / 2f)).y;
			}
		}
		
		
		return returnValue;
	}
	private float determineAbsolutePosition(MTPolygon p, float po, boolean isHorizontal) {
		
		if (isHorizontal) {
			return calcPos(p, virtualStyleSheet.getBackgroundImage(), p.getWidthXY(TransformSpace.LOCAL) * po ,0).x;
		} else {
			return calcPos(p, virtualStyleSheet.getBackgroundImage(), 0 ,p.getHeightXY(TransformSpace.LOCAL) * po).x;
		}
		
	}
	public Vector3D calcPos(MTPolygon box, PImage ta, float xo, float yo) {

		return new Vector3D((ta.width / 2)	+ xo, 
				(ta.height / 2) + yo);
	}

	public CSSStyle getVirtualStyleSheet() {
		evaluateStyleSheets();
		return virtualStyleSheet;
	}
	
	
}
