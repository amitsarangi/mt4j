package advanced.physics;

import org.mt4j.MTDesktopApplication;

import advanced.physics.scenes.AirHockeyScene;

public class StartAirHockey extends MTDesktopApplication {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		initialize();
	}
	
	@Override
	public void startUp() {
		addScene(new AirHockeyScene(this, "Air Hockey Scene"));
	}

}
