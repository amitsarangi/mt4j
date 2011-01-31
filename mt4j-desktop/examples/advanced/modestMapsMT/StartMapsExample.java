package advanced.modestMapsMT;

import org.mt4j.MTDesktopApplication;

public class StartMapsExample extends MTDesktopApplication{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String args[]){
		initialize();
	}
	
	@Override
	public void startUp(){
		this.addScene(new MapsScene(this, "Map scene"));
	}
}
