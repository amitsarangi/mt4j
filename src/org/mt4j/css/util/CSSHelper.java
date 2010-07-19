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
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.components.visibleComponents.shapes.MTLine;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.style.CSSStyleHierarchy;
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


import processing.core.PImage;

public class CSSHelper {

	List<CSSStyle> privateStyleSheets = new ArrayList<CSSStyle>();

	List<CSSStyleHierarchy> sheets = new ArrayList<CSSStyleHierarchy>();
	public CSSStyle virtualStyleSheet = null;
	CSSStyleManager cssStyleManager;
	MTApplication app;
	private MTComponent c;

	public CSSHelper(MTComponent c, MTApplication a, CSSStyleManager sm) {
		this.c = c;
		this.app = a;
		this.cssStyleManager = sm;
		addListeners();
	}

	public CSSHelper(MTComponent c, MTApplication a, CSSStyleManager sm,
			CSSStyle s) {
		this.c = c;
		this.app = a;
		this.cssStyleManager = sm;
		this.getPrivateStyleSheets().add(s);
		addListeners();

	}

	public CSSHelper(MTComponent c, MTApplication a, CSSStyleManager sm,
			List<CSSStyle> s) {
		this.c = c;
		this.app = a;
		this.cssStyleManager = sm;
		this.getPrivateStyleSheets().addAll(s);
		addListeners();

	}

	private void addListeners() {
		c.addStateChangeListener(StateChange.ADDED_TO_PARENT,
				new StateChangeListener() {
					@Override
					public void stateChanged(StateChangeEvent evt) {
						applyStyleSheet();
					}
				});

	}

	public void applyStyleSheet() {
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
		for (MTComponent d : c.getChildren()) {
			if (d instanceof MTCSSStylable) {
				MTCSSStylable s = (MTCSSStylable) d;
				s.applyStyleSheet();
			}
		}
	}

	public void applyStyleSheetBasic(AbstractShape p) {

		p.setFillColor(virtualStyleSheet.getBackgroundColor());
		p.setStrokeColor(virtualStyleSheet.getBorderColor());
		p.setStrokeWeight(virtualStyleSheet.getBorderWidth());
		p.setVisible(virtualStyleSheet.isVisibility());

		if (virtualStyleSheet.getBorderStylePattern() >= 0) {
			p.setNoStroke(false);
			p.setLineStipple(virtualStyleSheet.getBorderStylePattern());
		} else {
			p.setNoStroke(true);
		}

	}
	
	public void applyStyleSheetPolygon(MTPolygon p) {
		if (virtualStyleSheet.isModifiedBackgroundImage()) {
			tiledBackground(p, virtualStyleSheet.getBackgroundImage());
		}
	}

	private void applyStyleSheetEllipse(MTEllipse e) {
		if (virtualStyleSheet.isHeightPercentage()) {
			if (e.getParent() != null)
				e.setHeightXYRelativeToParent(virtualStyleSheet.getHeight() / 100f
						* e.getParent().getBounds()
								.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
		} else {
			e.setHeightXYRelativeToParent(virtualStyleSheet.getHeight());
		}

		if (virtualStyleSheet.isWidthPercentage()) {
			if (e.getParent() != null)
				e.setWidthXYRelativeToParent(virtualStyleSheet.getWidth() / 100f
						* e.getParent().getBounds()
								.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
		} else {
			e.setWidthXYRelativeToParent(virtualStyleSheet.getWidth());
		}
	}

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

	private void applyStyleSheetTextArea(MTTextArea ta) {
		if (!virtualStyleSheet.getFont().equals(
				cssStyleManager.getDefaultFont(app))) {
			ta.setFont(virtualStyleSheet.getFont());
		}
	}

	@SuppressWarnings("unchecked")
	public void evaluateStyleSheets() {
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

	public List<CSSStyle> getPrivateStyleSheets() {
		return privateStyleSheets;
	}

	public List<CSSStyleHierarchy> getSheets() {
		return sheets;
	}

	private float getXDistance(float x, Vertex v2) {
		float distance = v2.x - x;
		if (distance >= 0)
			return distance;
		else
			return -distance;

	}

	private float getYDistance(float y, Vertex v2) {
		float distance = v2.y - y;
		if (distance >= 0)
			return distance;
		else
			return -distance;
	}

	public void setPrivateStyleSheets(List<CSSStyle> privateStyleSheets) {
		this.privateStyleSheets = privateStyleSheets;
	}

	public void setSheets(List<CSSStyleHierarchy> sheets) {
		this.sheets = sheets;
	}

	public void setStyleSheet(CSSStyle sheet) {
		this.privateStyleSheets.add(sheet);
	}

	private void tiledBackground(MTPolygon p, PImage bgImage) {
		boolean pot = Tools3D.isPowerOfTwoDimension(bgImage);
		boolean tiled = true;
		p.setFillColor(MTColor.WHITE);
		if (tiled) {
			// Generate texture coordinates to repeat the texture over the whole
			// background (works only with OpenGL)
			float u = p.getBounds().getWidthXY(TransformSpace.LOCAL)
					/ bgImage.width;
			float v = p.getBounds().getHeightXY(TransformSpace.LOCAL)
					/ bgImage.height;

			Vector3D[] bV = p.getBounds().getVectorsLocal();

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

		if (MT4jSettings.getInstance().isOpenGlMode()) {

			GLTextureSettings g = new GLTextureSettings(
					TEXTURE_TARGET.TEXTURE_2D,
					SHRINKAGE_FILTER.BilinearNoMipMaps,
					EXPANSION_FILTER.Bilinear, WRAP_MODE.REPEAT,
					WRAP_MODE.REPEAT);
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

	}

}
