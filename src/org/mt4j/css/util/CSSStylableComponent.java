package org.mt4j.css.util;

// TODO: Auto-generated Javadoc
/**
 * The Interface CSSStylableComponent.
 */
public interface CSSStylableComponent {
	
	/**
	 * Checks if the Component is CSS styled.
	 *
	 * @return true, if is cSS styled
	 */
	public boolean isCSSStyled();
	
	/**
	 * Enables the CSS (if everything is right)
	 */
	public void enableCSS();
	
	/**
	 * Disable the CSS.
	 */
	public void disableCSS();
	
	/**
	 * Applíes the (global) style sheets
	 */
	public void applyStyleSheet();
	
}
