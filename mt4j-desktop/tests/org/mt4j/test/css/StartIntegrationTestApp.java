package org.mt4j.test.css;

import org.mt4j.MTDesktopApplication;

public class StartIntegrationTestApp extends MTDesktopApplication{
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		initialize();
	}
	
	
	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		addScene(new IntegrationTestApp(this, "Integration  Test Scene"));
	}

}
