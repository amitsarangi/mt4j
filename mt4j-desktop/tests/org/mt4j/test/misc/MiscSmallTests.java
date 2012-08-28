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
package org.mt4j.test.misc;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTOverlayContainer;
import org.mt4j.test.AbstractWindowTestcase;
import org.mt4j.test.testUtil.DummyScene;
import org.mt4j.test.testUtil.TestRunnable;
import org.mt4j.util.MTColor;

public class MiscSmallTests extends AbstractWindowTestcase {

	private DummyScene scene;

	@Override
	public void inStartUp(AbstractMTApplication app) {
		//Add a scene to the mt application
		this.scene = new DummyScene(app, "Dummy SceneA");
		app.addScene(scene);

	}
	
	public void testMTOverlayContainer(){
		runTest(new TestRunnable() {
			@Override
			public void runMTTestCode() {
				//MTOverlayContainer test
      MTOverlayContainer c1 = new MTOverlayContainer(getMTApplication(), "c1");
		MTRectangle r1 = new MTRectangle(getMTApplication(),0, 0,100, 100);
		c1.addChild(r1);
		MTOverlayContainer c2 = new MTOverlayContainer(getMTApplication(), "c2");
		MTRectangle r2 = new MTRectangle(getMTApplication(),20, 20,100, 100);
		r2.setFillColor(new MTColor(255,150,150));
		c2.addChild(r2);
		scene.getCanvas().addChild(c1);
		scene.getCanvas().addChild(c2);
			}
		});
		
	}

}
