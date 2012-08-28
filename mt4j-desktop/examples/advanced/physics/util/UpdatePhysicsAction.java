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
package advanced.physics.util;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.mt4j.sceneManagement.IPreDrawAction;
import org.mt4j.util.math.Vector3D;

import advanced.physics.physicsShapes.IPhysicsComponent;


public class UpdatePhysicsAction implements IPreDrawAction {
	private World world;
	private float timeStep;
	private int constraintIterations;
	private float scale;
	

	public UpdatePhysicsAction(World world, float timeStep,	int constraintIterations, float scale) {
		super();
		this.world = world;
		this.timeStep = timeStep;
		this.constraintIterations = constraintIterations;
		this.scale = scale;
	}

	public void processAction() {
		try{
			//Take a timestep in the physics world
			world.step(timeStep, constraintIterations);
			for (Body body = world.getBodyList(); body != null; body = body.getNext()) {
				if (!body.isSleeping()){
					Vec2 newPos		= body.getPosition();
					body.wakeUp();
					float newAngle 	= body.getAngle();
					if (body.getUserData() != null){
						if (body.getUserData() instanceof IPhysicsComponent){
							IPhysicsComponent shape = (IPhysicsComponent)body.getUserData();
							shape.setPositionGlobal(new Vector3D(newPos.x * scale, newPos.y * scale,0));
							shape.setCenterRotation(newAngle);
						}
					}
				}
			}
		}catch (Exception e) {
			System.err.println("Physics engine error during simulation - behaviour is now undefined!");
			e.printStackTrace();
		}
	}

	public boolean isLoop() {
		return true;
	}

}
