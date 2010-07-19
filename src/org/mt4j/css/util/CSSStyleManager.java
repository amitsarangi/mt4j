package org.mt4j.css.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.css.style.CSSSelector;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.style.CSSStyleHierarchy;
import org.mt4j.util.MTColor;

public class CSSStyleManager {
	public CSSStyleManager(List<CSSStyle> styles, MTApplication app) {
		for (CSSStyle s: styles) {
			this.styles.add(new CSSStyleHierarchy(s));
		}
		this.app = app;
	}
	
	List<MTCSSStylable> components = new ArrayList<MTCSSStylable>();
	
	MTApplication app = null;
	
	List<CSSStyleHierarchy> styles = new ArrayList<CSSStyleHierarchy>();

	public List<CSSStyleHierarchy> getStyles() {
		return styles;
	}

	public void setStyles(List<CSSStyleHierarchy> styles) {
		this.styles = styles;
		applyStyles();
	}
	
	public void addStyle(CSSStyle style) {
		this.styles.add(new CSSStyleHierarchy(style));
		applyStyles();
	}
	public void addStyle(CSSStyle style, int priority) {
		this.styles.add(new CSSStyleHierarchy(style, priority));
		applyStyles();
	}
	public void removeStyle(CSSStyle style) {
		this.styles.remove(style);
		applyStyles();
	}
	
	public void applyStyles() {
		for (MTCSSStylable c: components) {
			if (c != null) {
				c.applyStyleSheet();
			}
			
			
		}
	}
	
	public CSSStyle getFirstStyleForSelector(CSSSelector s) {
		for (CSSStyleHierarchy sty: styles) {
			if (sty.getStyle().getSelector().equals(s)) return sty.getStyle();
		}
		return null;
	}
	
	public List<CSSStyleHierarchy> getRelevantStyles(MTComponent c) {
		if (!components.contains(c) && c instanceof MTCSSStylable) components.add((MTCSSStylable)c);
		
		List<CSSStyleHierarchy> relevantStyles = new ArrayList<CSSStyleHierarchy>();
		List<String> superClasses = getSuperclasses(c.getClass());
	
		
		for (CSSStyleHierarchy s: styles) {
			int temp = s.getStyle().getSelector().appliesTo(c);
			if (temp != 0)
				//Debug Only
				//Logger.getLogger("MT4J Extensions").debug("Relevant Style? " + temp + " (" + s.getStyle().getSelector() + ")");
			if (temp != 0) {
				relevantStyles.add(new CSSStyleHierarchy(s, temp % 100, (short)(temp / 100)));
			}

			
		}
		return relevantStyles;
	}
	

	private List<String> getSuperclasses(Class c) {
		List<String> superclasses = new ArrayList<String>();
		superclasses.add(c.getSimpleName().toUpperCase().replace(" ", ""));
		while (c.getSuperclass() != null) {
			c = c.getSuperclass();
			superclasses.add(c.getSimpleName().toUpperCase().replace(" ", ""));
		}
		
		
		return superclasses;
	}
	
	public IFont getDefaultFont(MTApplication app) {
		return FontManager.getInstance().createFont(app,
				"dejavu/DejaVuSans.ttf", 16, // Font size
				new MTColor(255,255,255,255), // Font fill color
				new MTColor(255,255,255,255));
	}
	
	
}
