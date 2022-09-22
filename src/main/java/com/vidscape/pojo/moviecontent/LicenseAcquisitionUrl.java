package com.vidscape.pojo.moviecontent;

public class LicenseAcquisitionUrl {
	
	 private String playready;

	    private String widevine;

	    public void setPlayready(String playready){
	        this.playready = playready;
	    }
	    public String getPlayready(){
	        return this.playready;
	    }
	    public void setWidevine(String widevine){
	        this.widevine = widevine;
	    }
	    public String getWidevine(){
	        return this.widevine;
	    }
		@Override
		public String toString() {
			return "LicenseAcquisitionUrl [playready=" + playready + ", widevine=" + widevine + "]";
		}

}
