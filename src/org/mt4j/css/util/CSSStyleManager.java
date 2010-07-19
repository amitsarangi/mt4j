package org.mt4j.css.util;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.css.parser.CSSParserConnection;
import org.mt4j.css.style.CSSSelector;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.style.CSSStyleHierarchy;
import org.mt4j.util.MTColor;

// TODO: Auto-generated Javadoc
/**
 * The Class CSSStyleManager.
 */
public class CSSStyleManager {
	
	/**
	 * Instantiates a new CSS style manager.
	 *
	 * @param styles the CSSStyles
	 * @param app the MTApplication
	 */
	public CSSStyleManager(List<CSSStyle> styles, MTApplication app) {
		for (CSSStyle s: styles) {
			this.styles.add(new CSSStyleHierarchy(s));
		}
		this.app = app;
	}
	
	/**
	 * Instantiates a new (empty) CSS style manager.
	 *
	 * @param app the MTApplication
	 */
	public CSSStyleManager(MTApplication app) {
		this.app = app;
	}
	
	/**
	 * Load styles from file
	 *
	 * @param uri the uri of the file
	 */
	public void loadStyles(String uri) {
		CSSParserConnection pc = new CSSParserConnection(uri, app);
		List<CSSStyle> newStyles = pc.getCssh().getStyles();
		for (CSSStyle s: newStyles) {
			this.styles.add(new CSSStyleHierarchy(s));
		}
	}
	
	/**
	 * Clear all styles.
	 */
	public void clearStyles() {
		this.styles.clear();
		applyStyles();
	}
	
	/** The components (of registered MTComponents) */
	List<CSSStylableComponent> components = new ArrayList<CSSStylableComponent>();
	
	/** The MTApplication. */
	MTApplication app = null;
	
	/** The styles. */
	List<CSSStyleHierarchy> styles = new ArrayList<CSSStyleHierarchy>();

	/**
	 * Gets the styles.
	 *
	 * @return the styles
	 */
	public List<CSSStyleHierarchy> getStyles() {
		return styles;
	}

	/**
	 * Sets the styles.
	 *
	 * @param styles the new styles
	 */
	public void setStyles(List<CSSStyleHierarchy> styles) {
		this.styles = styles;
		applyStyles();
	}
	
	/**
	 * Adds a style.
	 *
	 * @param style the style
	 */
	public void addStyle(CSSStyle style) {
		this.styles.add(new CSSStyleHierarchy(style));
		applyStyles();
	}
	
	/**
	 * Adds a style with a certain priority
	 *
	 * @param style the style
	 * @param priority the priority
	 */
	public void addStyle(CSSStyle style, int priority) {
		this.styles.add(new CSSStyleHierarchy(style, priority));
		applyStyles();
	}
	
	/**
	 * Removes a style.
	 *
	 * @param style the style
	 */
	public void removeStyle(CSSStyle style) {
		this.styles.remove(style);
		applyStyles();
	}
	
	/**
	 * Applies the global style sheets on all registered components.
	 */
	public void applyStyles() {
		for (CSSStylableComponent c: components) {
			if (c != null) {
				c.applyStyleSheet();
			}
			
			
		}
	}
	
	/**
	 * Gets the first style which contains a specific selector.
	 *
	 * @param s the Selector
	 * @return the first style which contains the selector
	 */
	public CSSStyle getFirstStyleForSelector(CSSSelector s) {
		for (CSSStyleHierarchy sty: styles) {
			if (sty.getStyle().getSelector().equals(s)) return sty.getStyle();
		}
		return null;
	}
	
	/**
	 * Gets all relevant styles for a MTComponent
	 *
	 * @param c the MTComponent
	 * @return the relevant styles
	 */
	public List<CSSStyleHierarchy> getRelevantStyles(MTComponent c) {
		if (!components.contains(c) && c instanceof CSSStylableComponent) components.add((CSSStylableComponent)c);
		
		List<CSSStyleHierarchy> relevantStyles = new ArrayList<CSSStyleHierarchy>();

	
		
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
	

	/**
	 * Gets the superclasses of an Object
	 *
	 * @param c the MTComponent
	 * @return the superclasses
	 */
	private List<String> getSuperclasses(Class c) {
		List<String> superclasses = new ArrayList<String>();
		superclasses.add(c.getSimpleName().toUpperCase().replace(" ", ""));
		while (c.getSuperclass() != null) {
			c = c.getSuperclass();
			superclasses.add(c.getSimpleName().toUpperCase().replace(" ", ""));
		}
		
		
		return superclasses;
	}
	
	/**
	 * Gets the default font.
	 *
	 * @param app the app
	 * @return the default font
	 */
	public IFont getDefaultFont(MTApplication app) {
		return FontManager.getInstance().createFont(app,
				"dejavu/DejaVuSans.ttf", 16, // Font size
				new MTColor(255,255,255,255), // Font fill color
				new MTColor(255,255,255,255));
	}
	
	
}
