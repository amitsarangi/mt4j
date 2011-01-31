package basic.scenes;

import org.mt4j.MTDesktopApplication;

public class StartSceneExample extends MTDesktopApplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initialize();
	}
	
	@Override
	public void startUp() {
		addScene(new Scene1(this, "Scene 1"));
	}

}
