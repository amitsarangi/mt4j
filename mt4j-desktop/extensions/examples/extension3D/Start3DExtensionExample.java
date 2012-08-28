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
package examples.extension3D;

import org.mt4j.MTApplication;
import org.mt4j.sceneManagement.Iscene;


public class Start3DExtensionExample extends MTApplication{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Iscene scene;

	public static void main(String args[]){		
		initialize();
	}
	
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)
	 * @see org.mt4j.MTApplication#startUp()
	 */
	/* (non-Javadoc)j
	 * @see org.mt4j.MTApplication#startUp()
	 */
	@Override
	public void startUp(){
		scene = new Extension3DScene(this, "3D Model scene");
		this.addScene(scene);
	}

	@Override
	public void draw() {
		super.draw();        
	}
	
}
