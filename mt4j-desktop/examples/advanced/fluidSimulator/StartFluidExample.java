package advanced.fluidSimulator;

import org.mt4j.MTDesktopApplication;

public class StartFluidExample extends MTDesktopApplication{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String args[]){
		initialize();
	}
	
	@Override
	public void startUp(){
		this.addScene(new FluidSimulationScene(this, "Fluid scene"));
	}
}
