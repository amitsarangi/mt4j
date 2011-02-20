package basic.basicQuads;

import org.mt4j.MTAndroidApplication;

public class StartTest extends MTAndroidApplication {
	
    @Override
    public void startUp() {
    	this.addScene(new TestScene1(this, "TestScene1"));
    	
    }
    
    
}