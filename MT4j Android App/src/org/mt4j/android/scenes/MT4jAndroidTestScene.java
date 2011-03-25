package org.mt4j.android.scenes;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.gestureAction.DefaultLassoAction;
import org.mt4j.input.gestureAction.DefaultPanAction;
import org.mt4j.input.gestureAction.DefaultZoomAction;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.lassoProcessor.LassoProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.panProcessor.PanProcessorTwoFingers;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

public class MT4jAndroidTestScene extends AbstractScene {

	public MT4jAndroidTestScene(MTApplication mtApplication, String name) {
		super(mtApplication, name);
		
		this.setClearColor(new MTColor(146, 150, 188, 255));
		//Show touches
		this.registerGlobalInputProcessor(new CursorTracer(mtApplication, this));
		
		LassoProcessor lp = new LassoProcessor(mtApplication, getCanvas(), getSceneCam());
		getCanvas().registerInputProcessor(lp);
		getCanvas().addGestureListener(LassoProcessor.class, new DefaultLassoAction(mtApplication, getCanvas().getClusterManager(), getCanvas()));
		
		ZoomProcessor zp = new ZoomProcessor(mtApplication, 800);
		getCanvas().registerInputProcessor(zp);
		getCanvas().addGestureListener(ZoomProcessor.class, new DefaultZoomAction());
		
		PanProcessorTwoFingers pp = new PanProcessorTwoFingers(mtApplication, 800);
		getCanvas().registerInputProcessor(pp);
		getCanvas().addGestureListener(PanProcessorTwoFingers.class, new DefaultPanAction());
		
		IFont fontArial = FontManager.getInstance().createFont(mtApplication, "arial20.fnt", 20, MTColor.WHITE, true);
		//Create a textfield
		MTTextArea textField = new MTTextArea(mtApplication, fontArial); 
		
		textField.setNoStroke(true);
		textField.setNoFill(true);
		
		textField.setText("Hello Android World!");
		//Center the textfield on the screen
		textField.setPositionGlobal(new Vector3D(mtApplication.width/2f, mtApplication.height/2f));
		//Add the textfield to our canvas
		this.getCanvas().addChild(textField);
	}

}
