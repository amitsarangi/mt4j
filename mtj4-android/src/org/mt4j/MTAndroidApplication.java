package org.mt4j;

import org.mt4j.util.AndroidGraphicsUtil;
import org.mt4j.util.GraphicsUtil;


public abstract class MTAndroidApplication extends MTApplication{
	
	@Override
	public void setup() {
		//TODO
		
		
		
		/////////////////////// //FIXME TEST
		GraphicsUtil.setGraphicsUtilProvider(new AndroidGraphicsUtil(this));
		///////////////////////
	}
	
	@Override
	public int sketchWidth() {
		// TODO Auto-generated method stub
		return super.sketchWidth();
	}
	
	@Override
	public int sketchHeight() {
		// TODO Auto-generated method stub
		return super.sketchHeight();
	}
	
	protected void loadGL(){
		//TODO
	}


	
}
