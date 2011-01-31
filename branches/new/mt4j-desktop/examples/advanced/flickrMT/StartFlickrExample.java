package advanced.flickrMT;

import org.mt4j.MTDesktopApplication;


public class StartFlickrExample extends MTDesktopApplication{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String args[]){
		initialize();
	}
	
	@Override
	public void startUp(){
		this.addScene(new FlickrScene(this, "Flickr Scene"));
	}
	
}