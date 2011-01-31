package advanced.space3D;


import org.mt4j.MTDesktopApplication;


public class StartSpaceExample extends MTDesktopApplication{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String args[]){
		initialize();
	}
	
	@Override
	public void startUp(){
		this.addScene(new Space3DScene(this, "Space 3D Scene"));
	}
}
