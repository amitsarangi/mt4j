package basic.css;

import org.mt4j.MTDesktopApplication;


public class StartCssExample extends MTDesktopApplication {
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		initialize();
	}
	
	
	@Override
	public void startUp() {
		addScene(new CssExampleScene(this, "Integration  Test Scene"));
	}
}
