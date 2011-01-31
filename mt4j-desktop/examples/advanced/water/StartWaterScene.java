package advanced.water;

import org.mt4j.MTDesktopApplication;

import scenes.WaterSceneExportObf;

public class StartWaterScene extends MTDesktopApplication {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]){
		initialize();
	}
	
	@Override
	public void startUp(){
		this.addScene(new WaterSceneExportObf(this, "Water Scene"));
	}
}
