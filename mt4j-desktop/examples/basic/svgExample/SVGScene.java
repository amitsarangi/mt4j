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
package basic.svgExample;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.widgets.MTSvg;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;


public class SVGScene extends AbstractScene {
	
//	private String svgPath = System.getProperty("user.dir")+File.separator + "examples"+File.separator +"basic"+ File.separator + "svgExample"+ File.separator + "data" + File.separator;
	private String svgPath =  "basic" + AbstractMTApplication.separator + "svgExample" + AbstractMTApplication.separator + "data" + AbstractMTApplication.separator;

	public SVGScene(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		
		this.setClearColor(new MTColor(255, 255, 255, 255));
		//Show touches
		this.registerGlobalInputProcessor(new CursorTracer(mtApplication, this));
		
		MTSvg svg = new MTSvg(mtApplication, svgPath + "windmill.svg");
		svg.setPositionGlobal(new Vector3D(mtApplication.width/2, mtApplication.height/2,0));
		this.getCanvas().addChild(svg);
		
		MTSvg butterFly = new MTSvg(mtApplication, svgPath + "butterfly.svg");
		butterFly.setPositionGlobal(new Vector3D(300, 100,0));
		this.getCanvas().addChild(butterFly);
		
		this.getCanvas().addChild(new MTSvg(mtApplication, svgPath + "primitives.svg"));
	}

	
	public void onEnter() {}
	
	public void onLeave() {}

}
