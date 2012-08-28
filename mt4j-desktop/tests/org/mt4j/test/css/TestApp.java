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

import org.mt4j.AbstractMTApplication;
import org.mt4j.sceneManagement.AbstractScene;


public class TestApp extends AbstractScene{

	public TestApp(AbstractMTApplication mtApplication, String name) {
		super( mtApplication,  name);
		
		/*System.out.println("Started Test Application");
		MTRectangle rect = new MTRectangle(400,400, mtApplication);
		this.setClearColor(new MTColor(0, 0, 64, 255));
		this.getCanvas().addChild(rect);
		Logger logger = Logger.getLogger("MT4J Extensions");
		SimpleLayout l = new SimpleLayout();
		ConsoleAppender ca = new ConsoleAppender(l);
				
		logger.addAppender(ca);
		//app.initApp();
		System.out.println(this.getMTApplication().g != null);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.getMTApplication().g != null);
		parserConnector pc = new parserConnector("selectortest.css", this.getMTApplication());
		pc.toString();*/
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}
	
}
