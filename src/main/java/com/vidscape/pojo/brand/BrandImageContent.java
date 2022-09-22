package com.vidscape.pojo.brand;

import com.vidscape.pojo.subscription.Renditions;

public class BrandImageContent {
	   private String contentType;

	    private Renditions renditions;

	    public void setContentType(String contentType){
	        this.contentType = contentType;
	    }
	    public String getContentType(){
	        return this.contentType;
	    }
	    public void setRenditions(Renditions renditions){
	        this.renditions = renditions;
	    }
	    public Renditions getRenditions(){
	        return this.renditions;
	    }
	
	

}
