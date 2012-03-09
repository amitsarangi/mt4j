/***********************************************************************
 * mt4j Copyright (c) 2008 - 2009, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
 *  
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ***********************************************************************/
package org.mt4j.components;

import java.util.List;

import org.mt4j.components.clusters.Cluster;
import org.mt4j.components.clusters.ClusterManager;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.IHitTestInfoProvider;
import org.mt4j.input.inputData.MTInputEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.rotateProcessor.RotateProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.PlatformUtil;
import org.mt4j.util.camera.Icamera;
import org.mt4j.util.math.Matrix;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * MTCanvas is the root node of the component hierarchy of a MT4j scene.
 * To make a mt4j component visible and interactable you have to add it to
 * the scene's canvas or to a component which is already attached to the canvas.
 * The canvas then recursivly updates and draws all attached components each frame.
 * 
 * @author Christopher Ruff
 */
public class MTCanvas extends MTComponent implements IHitTestInfoProvider{
	
	/** The cluster manager. */
	private ClusterManager clusterManager;
	
	/** The frustum culling switch. */
	private boolean frustumCulling;
	
	private int culledObjects = 0;

	
	/**
	 * The Constructor.
	 *
	 * @param pApplet the applet
	 * @param attachedCamera the attached camera
	 */
	public MTCanvas(PApplet pApplet, Icamera attachedCamera) {
		this(pApplet, "unnamed MT Canvas", attachedCamera);
	}

	/**
	 * The Constructor.
	 * 
	 * @param pApplet the applet
	 * @param name the name
	 * @param attachedCamera the attached camera
	 */
	public MTCanvas(PApplet pApplet, String name, Icamera attachedCamera) {
		super(pApplet, name, attachedCamera);
		clusterManager = new ClusterManager(this);
		
//		this.setCollidable(false);
		
		this.setGestureAllowance(RotateProcessor.class, false);
		this.setGestureAllowance(ScaleProcessor.class, false);
		this.setGestureAllowance(TapProcessor.class, false);
		this.setGestureAllowance(DragProcessor.class, false);
		
		this.setPickable(false);
		
		//Frustum culling default
		frustumCulling = false;
	}
	
	

	/**
	 * Method for asking the canvas whether and which object is at the specified
	 * screen position.
	 * <p>
	 * IMPORTANT: this method returns the MTCanvas instance if no other object is hit.
	 * This means that the MTCanvas instance acts like the background => Gestures that
	 * are supposed to be performed on the background have to check if they hit the canvas.
	 * And the gestureevents should then have the canvas as their targetComponent!
	 * Also, you have to be careful in other gestures, as even when you dont hit an object, you will
	 * get the mtcanvas returned as the hit component - not null! 
	 * <p>Note: if the hit component is part of a cluster, the cluster is returned!
	 * 
	 * @param x the screen x coordinate
	 * @param y the screen y coordinate
	 * 
	 * @return the object at that position or this MTCanvas instance if no component was hit
	 */
	public IMTComponent3D getComponentAt(float x, float y) { 
		IMTComponent3D closest3DComp = null;
		try{
			closest3DComp = this.pick(x, y).getNearestPickResult();
			if (closest3DComp == null){
				closest3DComp = this;
			}
			//TODO anders machen..z.b. geclusterte comps einfach als kinder von
			//�bergeordnetem clusterpoly machen? aber mit clusterPoly.setComposite(TRUE);
			//Clusterpoly pickable machen damit das hier nicht gebraucht wird?
			
			Cluster sel = this.getClusterManager().getCluster(closest3DComp);
			if (sel != null){
				closest3DComp = sel;
			}
		}catch(Exception e){
			System.err.println("Error while trying to pick an object: ");
			e.printStackTrace();
		}
		
		/*
		if (closest3DComp != null)
			System.out.println("Picked: '" + closest3DComp.getName() + "' at pos (" + x + "," + y + ")");
		else
			System.out.println("Picked: '" + closest3DComp + "' at pos (" + x + "," + y + ")");
		 */
		return closest3DComp;
	}

	
	public boolean isBackGroundAt(float x, float y) {
		return this.getComponentAt(x, y).equals(this);
	}
	
	
	
	/**
	 * Updates and then draws every visible object in the canvas.
	 * First calls the <code>updateComponent(long timeDelta)</code> method. Then
	 * the <code>drawComponent()</code> method of each object in the scene graph.
	 * Also handles the setting of cameras attached to the objects.
	 * @param graphics 
	 * 
	 * @param updateTime the time passed since the last update (in ms)
	 */
	public void drawAndUpdateCanvas(PGraphics graphics, long updateTime){
		this.culledObjects = 0;
		
		//FIXME THIS IS A HACK! WE SHOULD REPLACE CLUSTERS WITH NORMAL COMPONENTS INSTEAD!
		//Update cluster components 
		Cluster[] clusters = getClusterManager().getClusters();
        for (Cluster cluster : clusters) {
            cluster.updateComponent(updateTime);
        }
		
		this.drawUpdateRecursive(this, updateTime, graphics, true);
//		System.out.println("Culled objects: " + culledObjects);
	}
	
	/**
	 * Force redraw of every visible object in the canvas without updated them first. Useful
	 * when stereoscopic rendering is enabled.
	 * Calls the <code>drawComponent()</code> method of each object in the scene graph.
	 * Also handles the setting of cameras attached to the objects.
	 * @param graphics 
	 */
	public void redrawCanvas(PGraphics graphics){
		this.culledObjects = 0;
		
		this.drawUpdateRecursive(this, 0, graphics, false);
//		System.out.println("Culled objects: " + culledObjects);
	}

	
	/**
	 * Draw the whole canvas update recursive.
	 * 
	 * @param currentcomp the currentcomp
	 * @param updateTime the update time
	 * @param graphics the renderer
	 * @param updateComp if set to true, each component to be drawn is updated first
	 */
	private void drawUpdateRecursive(MTComponent currentcomp, long updateTime, PGraphics graphics, boolean updateComp){
		if (currentcomp.isVisible()){
			if (updateComp)
				//Update current component
				currentcomp.updateComponent(updateTime);
			
			if (currentcomp.getAttachedCamera() != null){
				//Saves transformations up to this object
				graphics.pushMatrix();
				
				//Resets the modelview completely with a new camera matrix
				currentcomp.getAttachedCamera().update();
				
				if (currentcomp.getParent() != null){
					//Applies all transforms up to this components parent
					//because the new camera wiped out all previous transforms
					Matrix m = currentcomp.getParent().getGlobalMatrix();
					
					if (PlatformUtil.isAndroid()){
						getRenderer().g.applyMatrix(
								m.m00, m.m01, m.m02,  m.m03,
								m.m10, m.m11, m.m12,  m.m13,
								m.m20, m.m21, m.m22,  m.m23,
								m.m30, m.m31, m.m32,  m.m33);
					}else{
						PlatformUtil.getModelView().apply(
								m.m00, m.m01, m.m02,  m.m03,
								m.m10, m.m11, m.m12,  m.m13,
								m.m20, m.m21, m.m22,  m.m23,
								m.m30, m.m31, m.m32,  m.m33
						);
					}
				}
				
				//Apply local transform etc
				currentcomp.preDraw(graphics);
				
				//Check visibility with camera frustum
				if (frustumCulling){
					if (currentcomp.isContainedIn(currentcomp.getViewingCamera().getFrustum())){
						// DRAW THE COMPONENT  \\
						currentcomp.drawComponent(graphics);
					}else{
						culledObjects++;
						//System.out.println("Not visible: " + currentcomp.getName());
					}
				}else{
					// DRAW THE COMPONENT  \\
					currentcomp.drawComponent(graphics);
				}
				
				currentcomp.postDraw(graphics);

				//Draw Children  
				List<MTComponent> childs = currentcomp.getChildList();
				int childCount = childs.size();
				for (int i = 0; i < childCount; i++) { //Note: for each loop sometimes throws concurrentmodification error because of fail-fast iterator
					drawUpdateRecursive(childs.get(i), updateTime, graphics, updateComp);
				}

				currentcomp.postDrawChildren(graphics);
				
				//Restores the transforms of the previous camera etc
				graphics.popMatrix(); 
			}else{//If no custom camera was set
				//TODO in abstactvisiblecomp wird outine �ber gradients und clips
				//gezeichnet obwohl hier invisble war! FIXME!
				//evtl applymatrix unapply in eigene methode? dann nur das ausf�hren, kein pre/post draw!
				
				//TODO vater an kinder listener -> resize - new geometry -> resize own 
				
				currentcomp.preDraw(graphics);
				
				if (frustumCulling){
					//Check visibility with camera frustum
					if (currentcomp.isContainedIn(currentcomp.getViewingCamera().getFrustum())){
						// DRAW THE COMPONENT  \\
						currentcomp.drawComponent(graphics);
					}else{
						culledObjects++;
						//System.out.println("Not visible: " + currentcomp.getName());
					}
				}else{
					// DRAW THE COMPONENT  \\
					currentcomp.drawComponent(graphics);
				}
				
				currentcomp.postDraw(graphics);
					
				List<MTComponent> childs = currentcomp.getChildList();
				int childCount = childs.size();
				for (int i = 0; i < childCount; i++) {
					drawUpdateRecursive(childs.get(i), updateTime, graphics, updateComp);
				}
				
				currentcomp.postDrawChildren(graphics);
			}
		}//if visible end
	}

	
	/* (non-Javadoc)
	 * @see org.mt4j.components.MTComponent#processInputEvent(org.mt4j.input.inputData.MTInputEvent)
	 */
	@Override
	public boolean processInputEvent(MTInputEvent inEvt) {
		//TODO not very elegant - better approach??
		if (inEvt.hasTarget() && inEvt.getEventPhase() != MTInputEvent.BUBBLING_PHASE){
			if (!inEvt.getTarget().equals(this)){ //Avoid recursion
				//Send directed event to the target component
				return inEvt.getTarget().processInputEvent(inEvt);
			}
		}
		
//		return true;  //this.handleEvent
		//handle in superclass
		
		//The MTCanvas get events targeted at him AND events that have no target!
		return super.processInputEvent(inEvt);
	}
	
		
	
	/**
	 * Gets the cluster manager.
	 * 
	 * @return the cluster manager
	 */
	public ClusterManager getClusterManager() {
		return clusterManager;
	}


	/**
	 * Sets the cluster manager.
	 * 
	 * @param selectionManager the new cluster manager
	 */
	public void setClusterManager(ClusterManager selectionManager) {
		this.clusterManager = selectionManager;
	}


	public boolean isFrustumCulling() {
		return frustumCulling;
	}

	public void setFrustumCulling(boolean frustumCulling) {
		this.frustumCulling = frustumCulling;
	}
	

	
	
}
