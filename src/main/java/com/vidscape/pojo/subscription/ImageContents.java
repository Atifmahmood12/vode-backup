package com.vidscape.pojo.subscription;

public class ImageContents {
	
	private String contentType;

    private int xResolution;

    private int yResolution;

    private Renditions renditions;

    public void setContentType(String contentType){
        this.contentType = contentType;
    }
    public String getContentType(){
        return this.contentType;
    }
    public void setXResolution(int xResolution){
        this.xResolution = xResolution;
    }
    public int getXResolution(){
        return this.xResolution;
    }
    public void setYResolution(int yResolution){
        this.yResolution = yResolution;
    }
    public int getYResolution(){
        return this.yResolution;
    }
    public void setRenditions(Renditions renditions){
        this.renditions = renditions;
    }
    public Renditions getRenditions(){
        return this.renditions;
    }
	@Override
	public String toString() {
		return "ImageContents [contentType=" + contentType + ", xResolution=" + xResolution + ", yResolution="
				+ yResolution + ", renditions=" + renditions + "]";
	}
    
    

}
