/***********************************************************************
*   MT4j Copyright (c) 2008 - 2012, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
*
*   This file is part of MT4j.
*
*   MT4j is free software: you can redistribute it and/or modify
*   it under the terms of the GNU Lesser General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   MT4j is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
*   GNU Lesser General Public License for more details.
*
*   You should have received a copy of the GNU Lesser General Public License
*   along with MT4j.  If not, see <http://www.gnu.org/licenses/>.
*
************************************************************************/
package org.mt4j.components.visibleComponents.shapes;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.css.style.CSSStyle;
import org.mt4j.components.css.util.CSSHelper;
import org.mt4j.components.css.util.CSSStylableComponent;
import org.mt4j.util.math.Vertex;

import processing.core.PApplet;

/**
 * The Class MTCSSStylableShape.
 */
public abstract class MTCSSStylableShape extends AbstractShape implements CSSStylableComponent {
	
	/** The mt app. */
	private AbstractMTApplication mtApp;
	
	/** The css styled. */
	private boolean cssStyled = false;
	
	/** The css force disabled. */
	private boolean cssForceDisabled = false;
	
	/** The css helper. */
	private CSSHelper cssHelper;
	
	
	
	/**
	 * Instantiates a new mTCSS stylable shape.
	 * @param pApplet the applet
	 * @param vertices the vertices
	 */
	public MTCSSStylableShape(PApplet pApplet, Vertex[] vertices) {
		this(pApplet, new GeometryInfo(pApplet, vertices));
	}

	
	
	/**
	 * Instantiates a new mTCSS stylable shape.
	 * @param pApplet the applet
	 * @param geometryInfo the geometry info
	 */
	public MTCSSStylableShape(PApplet pApplet, GeometryInfo geometryInfo) {
		super(pApplet, geometryInfo);
		
		if (pApplet instanceof AbstractMTApplication) {
			this.mtApp = (AbstractMTApplication)pApplet;
			this.cssHelper = new CSSHelper(this, mtApp);
			if (this.mtApp.getCssStyleManager().isGloballyEnabled()) {
				this.enableCSS();
			}
		}
	}


	
	/* (non-Javadoc)
	 * @see org.mt4j.components.css.util.CSSStylableComponent#getCssHelper()
	 */
	public CSSHelper getCssHelper() {
		return cssHelper;
	}


	/* (non-Javadoc)
	 * @see org.mt4j.components.css.util.CSSStylableComponent#enableCSS()
	 */
	public void enableCSS() {
		if (mtApp != null && cssHelper != null) {
			cssStyled = true;
		}
		applyStyleSheet();
	}

	/* (non-Javadoc)
	 * @see org.mt4j.components.css.util.CSSStylableComponent#disableCSS()
	 */
	public void disableCSS() {
		cssStyled = false;
	}
		
	/* (non-Javadoc)
	 * @see org.mt4j.components.css.util.CSSStylableComponent#isCSSStyled()
	 */
	public boolean isCSSStyled() {
		return cssStyled;
	}

	
	/* (non-Javadoc)
	 * @see org.mt4j.components.css.util.CSSStylableComponent#isCssForceDisabled()
	 */
	public boolean isCssForceDisabled() {
		return cssForceDisabled;
	}

	/* (non-Javadoc)
	 * @see org.mt4j.components.css.util.CSSStylableComponent#setCssForceDisable(boolean)
	 */
	public void setCssForceDisable(boolean cssForceDisabled) {
		this.cssForceDisabled = cssForceDisabled;
	}


	
	/* (non-Javadoc)
	 * @see org.mt4j.components.css.util.CSSStylableComponent#applyStyleSheet()
	 */
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
	 * @param virtualStyleSheet the virtual style sheet
	 */
	private void applyStyleSheetBasic(CSSStyle virtualStyleSheet) {
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
	}

	/**
	 * Apply style sheet custom.
	 *
	 * @param virtualStyleSheet the virtual style sheet
	 */
	abstract protected void applyStyleSheetCustom(CSSStyle virtualStyleSheet);


}
