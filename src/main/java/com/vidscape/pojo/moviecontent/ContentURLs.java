package com.vidscape.pojo.moviecontent;

public class ContentURLs {
	
	 private String consumerUrl;
	 private LicenseAcquisitionUrl licenseAcquisitionUrl;
	 
	 
	 public void setConsumerUrl(String consumerUrl){
	        this.consumerUrl = consumerUrl;
	    }
	    public String getConsumerUrl(){
	        return this.consumerUrl;
	    }
	    public void setLicenseAcquisitionUrl(LicenseAcquisitionUrl licenseAcquisitionUrl){
	        this.licenseAcquisitionUrl = licenseAcquisitionUrl;
	    }
	    public LicenseAcquisitionUrl getLicenseAcquisitionUrl(){
	        return this.licenseAcquisitionUrl;
	    }
		@Override
		public String toString() {
			return "ContentURLs [consumerUrl=" + consumerUrl + ", licenseAcquisitionUrl=" + licenseAcquisitionUrl + "]";
		}
	    
	    
}
