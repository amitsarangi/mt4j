package basic.mtGestures;

import org.mt4j.MTDesktopApplication;

public class StartMTGestures extends MTDesktopApplication {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		initialize();
	}
	@Override
	public void startUp() {
		addScene(new MTGesturesExampleScene(this, "Multi-touch Gestures Example Scene"));
	}
}
