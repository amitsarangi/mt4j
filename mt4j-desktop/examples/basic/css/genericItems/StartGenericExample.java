package basic.css.genericItems;

import org.mt4j.MTDesktopApplication;


public class StartGenericExample extends MTDesktopApplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		initialize();
	}
	
	
	@Override
	public void startUp() {
		addScene(new GenericExampleScene(this, "Integration  Test Scene"));
	}
}
