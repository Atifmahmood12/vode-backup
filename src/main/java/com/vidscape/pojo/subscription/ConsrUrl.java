package com.vidscape.pojo.subscription;

public class ConsrUrl {
	
	
	private String consumerUrl;

    public void setConsumerUrl(String consumerUrl){
        this.consumerUrl = consumerUrl;
    }
    
    public String getConsumerUrl(){
        return this.consumerUrl;
    }
	@Override
	public String toString() {
		return "ConsrUrl [consumerUrl=" + consumerUrl + "]";
	}

}
