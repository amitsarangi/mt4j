package org.mt4j.test.components;

import org.mt4j.MTApplication;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.test.AbstractWindowTestcase;
import org.mt4j.test.testUtil.DummyScene;
import org.mt4j.test.testUtil.TestRunnable;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.ToolsMath;


public class MTComponentTest extends AbstractWindowTestcase {
	private MTComponent parent;
	private MTApplication app;
	private Iscene scene;
	
	@Override
	public void inStartUp(MTApplication app) {
		this.app = app;
		//Add a scene to the mt application
		this.scene = new DummyScene(app, "Dummy Scene");
		app.addScene(scene);
		
		//Set up components
		parent = new MTComponent(app);
		getCanvas().addChild(parent);
	}
	
	public MTCanvas getCanvas(){
		return this.scene.getCanvas();
	}
	
	public void testComponentAddRemove(){
		runTest(new TestRunnable() {
			@Override
			public void runMTTestCode() {
				System.out.println("\nTesting some base MTComponent functions..");
				System.out.println("Ext supported: " + Tools3D.isGLExtensionSupported(app, "test"));
				
				assertEquals(0, parent.getChildCount());
				int numChildren = Math.round(ToolsMath.getRandom(1, 10));
				assertTrue("children created and added: " + numChildren  + " -> >= 1 && <= 10", numChildren >= 1 && numChildren <= 10);

				for (int i = 0; i < numChildren; i++) {
					MTComponent newChild = new MTComponent(app);
					parent.addChild(newChild);
				}
				assertTrue("children list not emtpy", parent.getChildren().length > 0);

				assertEquals("parent Childcount == created children number", numChildren, parent.getChildCount());

				MTComponent first = parent.getChildByIndex(0);
				//parent.sendChildToFront(first);
				first.sendToFront();
				MTComponent last = parent.getChildByIndex(parent.getChildCount()-1);
				assertEquals("Sent first to front (last) - is it there now?" , first, last);

				
				int id = first.getID();
				MTComponent childByID = parent.getChildbyID(id);
				assertEquals(first, childByID);
				
				assertTrue(parent.containsChild(first));
				assertTrue(parent.containsDirectChild(first));
				
				assertTrue(first.getViewingCamera() != null);
				
				parent.removeAllChildren();
				assertEquals("All children removed?", 0	, parent.getChildCount());
			}
		});
	}
	
	
	
	
	
	
	
	

}
