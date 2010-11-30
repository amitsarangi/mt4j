package org.mt4j.components.visibleComponents.shapes;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.css.style.CSSStyle;
import org.mt4j.components.css.util.CSSHelper;
import org.mt4j.components.css.util.CSSStylableComponent;
import org.mt4j.components.visibleComponents.GeometryInfo;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.util.math.Vertex;
import org.mt4jx.components.visibleComponents.widgets.MTSuggestionTextArea;

import processing.core.PApplet;

public abstract class MTCSSStylableShape extends AbstractShape implements CSSStylableComponent {
	private MTApplication mtApp;
	private boolean cssStyled = false;
	private boolean cssForceDisabled = false;
	private CSSHelper cssHelper;
	
	
	
	public MTCSSStylableShape(Vertex[] vertices, PApplet pApplet) {
		this(new GeometryInfo(pApplet, vertices), pApplet);
	}

	
	
	public MTCSSStylableShape(GeometryInfo geometryInfo, PApplet pApplet) {
		super(geometryInfo, pApplet);
		
		if (pApplet instanceof MTApplication) {
			this.mtApp = (MTApplication)pApplet;
			this.cssHelper = new CSSHelper(this, mtApp);
			if (this.mtApp.getCssStyleManager().isGloballyEnabled()) {
				this.enableCSS();
			}
		}
	}


	
	public CSSHelper getCssHelper() {
		return cssHelper;
	}


	public void enableCSS() {
		if (mtApp != null && cssHelper != null) {
			cssStyled = true;
		}
		applyStyleSheet();
	}

	public void disableCSS() {
		cssStyled = false;
	}
		
	public boolean isCSSStyled() {
		return cssStyled;
	}

	
	public boolean isCssForceDisabled() {
		return cssForceDisabled;
	}

	public void setCssForceDisable(boolean cssForceDisabled) {
		this.cssForceDisabled = cssForceDisabled;
	}


	
	public void applyStyleSheet(){
		if (this.isCSSStyled() && mtApp != null && this.getCssHelper() != null) {

			if (!isCssForceDisabled() && ((isCSSStyled() && !mtApp.getCssStyleManager().isGloballyDisabled()) || mtApp.getCssStyleManager().isGloballyEnabled())) {
				CSSStyle virtualStyleSheet = cssHelper.getVirtualStyleSheet(); //remember that this re-evaluates() and takes some time -> slow? 

				applyStyleSheetBasic(virtualStyleSheet);

				applyStyleSheetCustom(virtualStyleSheet);

				//Apply childrens styles
				for (MTComponent d : this.getChildren()) {
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
	private void applyStyleSheetBasic(CSSStyle virtualStyleSheet) {
//		/*
		if (virtualStyleSheet.isModifiedBackgroundColor())
			this.setFillColor(virtualStyleSheet.getBackgroundColor());
		if (virtualStyleSheet.isModifiedBorderColor())
			this.setStrokeColor(virtualStyleSheet.getBorderColor());
		if (virtualStyleSheet.isModifiedBorderWidth())
			this.setStrokeWeight(virtualStyleSheet.getBorderWidth());
		if (virtualStyleSheet.isModifiedVisibility())
			this.setVisible(virtualStyleSheet.isVisibility());
		
		if (virtualStyleSheet.isModifiedBorderStyle()) {
			if (virtualStyleSheet.getBorderStylePattern() >= 0) {
				this.setNoStroke(false);
				this.setLineStipple(virtualStyleSheet.getBorderStylePattern());
			} else {
				this.setNoStroke(true);
			}
		}
//		*/
	}

	abstract protected void applyStyleSheetCustom(CSSStyle virtualStyleSheet);


}
