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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mt4j.components.css.parser.CSSHandler;
import org.mt4j.components.css.style.CSSSelector;
import org.mt4j.components.css.style.CSSStyle;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;


public class CSSHandlerTest extends TestCase{
	private ILogger logger = MTLoggerFactory.getLogger("MT4J Extensions");
	private StartTestApp app = new StartTestApp();
	private List<CSSStyle> styles = new ArrayList<CSSStyle>();
	private CSSHandler cssh = new CSSHandler(app, styles);
	
	@Before
	public void setUp() {
	}
	
	
	@Test 
	public void testProcessElement() {
		CSSSelector test = cssh.processElement("P.c141");
		logger.debug(test);
		test = cssh.processElement("P#c141");
		logger.debug(test);
		test = cssh.processElement("#P.c141");
		logger.debug(test);
		test = cssh.processElement("#P c141");
		logger.debug(test);
		test = cssh.processElement("#P                                .c141");
		logger.debug(test);
	}

}
