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
package org.mt4j.test.css;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mt4j.components.css.parser.CSSParserConnection;
import org.mt4j.components.css.style.CSSSelector;
import org.mt4j.components.css.style.CSSStyle;
import org.mt4j.components.css.util.CSSKeywords.CSSBorderStyle;
import org.mt4j.components.css.util.CSSKeywords.CSSSelectorType;
import org.mt4j.util.MTColor;


public class BorderTest extends TestCase {
	private StartTestApp app = new StartTestApp();
	private CSSParserConnection pc;
	private List<CSSStyle> styles;

	@Before
	public void setUp() {
		pc = new CSSParserConnection("junit/bordertest.css", app);
		styles= pc.getCssh().getStyles();
	}
	
	
	protected void tearDown() {
		//app.destroy();
	}
	
	@Test 
	public void testWidth() {
		CSSSelector borderwidth = new CSSSelector("borderwidth", CSSSelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(borderwidth)) {
				exists = !exists;
				float comp = (1200f/72f)*4f;
				assertTrue(closeTo(s.getBorderWidth(),comp));
			}
		}
		assertTrue(exists);
	}
	
	@Test
	public void testStyle() {
		CSSSelector borderstyle = new CSSSelector("borderstyle", CSSSelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(borderstyle)) {
				exists = !exists;
				assertTrue(s.getBorderStyle() == CSSBorderStyle.DASHED);
			}
		}
		assertTrue(exists);
	}
	
	@Test
	public void testColor() {
		CSSSelector bordercolor = new CSSSelector("bordercolor", CSSSelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(bordercolor)) {
				exists = !exists;
				assertTrue(s.getBorderColor().equals(new MTColor(255,0,0,255)));
			}
		}
		assertTrue(exists);
	}
	
	private boolean closeTo(float a, float b) {
		float c = a-b;
		if (c < 0) c *= -1f;
		if (c < 0.001) return true;
		return false;
	}
}
