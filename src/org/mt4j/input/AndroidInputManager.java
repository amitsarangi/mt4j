package org.mt4j.input;

import org.mt4j.MTApplication;

public class AndroidInputManager extends InputManager {
	
	
	public AndroidInputManager(MTApplication pa) {
		this(pa, true);
	}
	
	public AndroidInputManager(MTApplication pa, boolean registerDefaultSources) {
		super(pa, registerDefaultSources);
		
		
	}
	
	@Override
	protected void registerDefaultInputSources() {
		super.registerDefaultInputSources();
		
	}



}
