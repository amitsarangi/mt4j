package org.mt4jx.input.gestureAction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;
import org.mt4j.components.PickResult;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.PickResult.PickEntry;
import org.mt4j.components.bounds.BoundingSphere;
import org.mt4j.components.bounds.IBoundingShape;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.input.inputData.AbstractCursorInputEvt;
import org.mt4j.input.inputData.InputCursor;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;

import org.mt4j.input.inputProcessors.componentProcessors.rotateProcessor.RotateProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.camera.Icamera;
import org.mt4j.util.camera.MTCamera;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mt4jx.components.visibileComponents.widgets.MTDepthHelper;
import org.mt4jx.input.inputProcessors.componentProcessors.depthProcessor.DepthProcessor;
import org.mt4jx.util.BoundingHelper;

import processing.core.PApplet;
import processing.core.PGraphics3D;

public class CreateDragHelperAction implements IGestureEventListener{
	
	private PApplet pApplet;
	
	private MTCanvas canvas;
	
	private Icamera cam;
	
	private MTComponent targetComponent;
	
	private MTDepthHelper depthHelper;

	/**
	 * to scale to the correct size after zooming 
	 * the distance between camera and near plane 
	 * wihtout zoom is needed
	 */
	private float zDistanceWithoutZoom;
		
	public CreateDragHelperAction(PApplet v_pApplet,MTCanvas v_canvas,Icamera v_cam,MTComponent v_targetComponent)
	{
		this.pApplet = v_pApplet;
		this.canvas = v_canvas;
		this.cam = v_cam;
		this.targetComponent = v_targetComponent;
		this.zDistanceWithoutZoom = cam.getFrustum().getZValueOfNearPlane();
	    
	}
		
	public boolean processGestureEvent(MTGestureEvent ge) {
		
		if(ge instanceof DragEvent)
		{
			DragEvent evt = (DragEvent)ge;
			switch (evt.getId()) {
			case MTGestureEvent.GESTURE_DETECTED:
				depthHelper = new MTDepthHelper(pApplet,targetComponent,cam,canvas);				
				canvas.addChild(depthHelper);				
				break;
			case MTGestureEvent.GESTURE_UPDATED:
				break;
			case MTGestureEvent.GESTURE_ENDED:	
				deleteDepthHelper();
				break;
			}
			
		}
		return false;
	}	

	private void deleteDepthHelper()
	{
		canvas.removeChild(depthHelper);
	}
	
	
	
	

}
