package basic.css.menus;

import org.mt4j.MTDesktopApplication;


public class StartMenuExample extends MTDesktopApplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		initialize();
	}
	
	
	@Override
	public void startUp() {
		addScene(new MenuExampleScene(this, "Integration  Test Scene"));
	}
}
