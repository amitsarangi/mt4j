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
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.math.Vertex;

public class IntegrationTestApp extends AbstractScene{
	private MTComponent parent;
	private AbstractMTApplication app;
	
	public IntegrationTestApp(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);

		this.app = mtApplication;
			//this.getCanvas().addChild(new MTBackgroundImage(app, app.loadImage("256x256.jpg"), true));
			
			//Set up components
			parent = new MTComponent(app);
			this.getCanvas().addChild(parent);
		
			MTRectangle r = new MTRectangle(app, 500, 500, 500, 500);
			r.enableCSS();
			this.getCanvas().addChild(r);
			
			Vertex[] vtcs = {new Vertex(100,100), new Vertex(200, 20), new Vertex(300, 200) ,new Vertex(100,100)};
			MTPolygon p = new MTPolygon(app, vtcs);
			this.getCanvas().addChild(p);
			p.enableCSS();
	}


	@Override
	public void onEnter() {
	}

	@Override
	public void onLeave() {
		
	}

}
