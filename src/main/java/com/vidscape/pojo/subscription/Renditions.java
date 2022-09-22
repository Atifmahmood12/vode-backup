package com.vidscape.pojo.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Renditions {
	
	
	private ConsrUrl consumerUrl;
	
	@JsonProperty ("*")
	public ConsrUrl getConsumerUrl() {
		return consumerUrl;
	}

	public void setConsumerUrl(ConsrUrl consumerUrl) {
		this.consumerUrl = consumerUrl;
	}

	@Override
	public String toString() {
		return "Renditions [consumerUrl=" + consumerUrl + "]";
	}
	
}
