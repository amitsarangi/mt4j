package org.mt4j.util.opengl;

import org.mt4j.util.opengl.GLTexture.EXPANSION_FILTER;
import org.mt4j.util.opengl.GLTexture.SHRINKAGE_FILTER;
import org.mt4j.util.opengl.GLTexture.TEXTURE_TARGET;
import org.mt4j.util.opengl.GLTexture.WRAP_MODE;


public class GLTextureSettings {
	
	public SHRINKAGE_FILTER shrinkFilter;
	public EXPANSION_FILTER expansionFilter;
	
	public WRAP_MODE wrappingHorizontal;
	public WRAP_MODE wrappingVertical;
	
	public TEXTURE_TARGET target;
	
//	public INTERNAL_FORMAT textureInternalFormat;
    
    public GLTextureSettings(){
    	this(TEXTURE_TARGET.TEXTURE_2D, SHRINKAGE_FILTER.BilinearNoMipMaps, EXPANSION_FILTER.Bilinear, WRAP_MODE.CLAMP_TO_EDGE, WRAP_MODE.CLAMP_TO_EDGE);
    }
    
    public GLTextureSettings(TEXTURE_TARGET target, SHRINKAGE_FILTER shrinkFilter, EXPANSION_FILTER expansionFilter, WRAP_MODE wrappingHorizontal, WRAP_MODE wrappingVertical){
    	this.target = target;
		
		// Filter mode
		this.shrinkFilter 		= shrinkFilter;
		this.expansionFilter 	= expansionFilter;
		
		// Texture Wrapping mode
		this.wrappingHorizontal = wrappingHorizontal; //TODO use clamp_to_edge - but what examples etc would that break?
		this.wrappingVertical 	= wrappingVertical;
    }
    
    
    @Override
    public boolean equals(Object obj) {
//    	return super.equals(obj);
    	
    	if (obj instanceof GLTextureSettings) {
			GLTextureSettings settings = (GLTextureSettings) obj;
			return( 
					this.target == settings.target
					&& this.shrinkFilter == settings.shrinkFilter
					&& this.expansionFilter == settings.expansionFilter
					&& this.wrappingHorizontal == settings.wrappingHorizontal
					&& this.wrappingVertical == settings.wrappingVertical
//					&& this.textureInternalFormat == settings.textureInternalFormat //FIXME REMOVE!?
			);
		}else{
			return false;
		}
    }
	

}
