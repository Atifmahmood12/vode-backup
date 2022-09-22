package com.vidscape.pojo.subscription;

import java.util.List;

import com.vidscape.pojo.moviecontent.VideoContents;

public class PurchaseOptions {
	
	
	private String startDateTime;

    private String endDateTime;

    private int policyGroupId; // parse to Int when you get it from CSV data

    private int policyId; // parse to Int when you get it from CSV data

    private String policyType;

    private Offer offer;

    private String offeredEntityType;

    private String offeredEntityId;

    private Price price;

    private String country;

    private String contractName;

    private List<String> contentTypes;
    
    private List<VideoContents> videoContents;

    public void setStartDateTime(String startDateTime){
        this.startDateTime = startDateTime;
    }
    public String getStartDateTime(){
        return this.startDateTime;
    }
    public void setEndDateTime(String endDateTime){
        this.endDateTime = endDateTime;
    }
    public String getEndDateTime(){
        return this.endDateTime;
    }
    public void setPolicyGroupId(int policyGroupId){
        this.policyGroupId = policyGroupId;
    }
    public int getPolicyGroupId(){
        return this.policyGroupId;
    }
    public void setPolicyId(int policyId){
        this.policyId = policyId;
    }
    public int getPolicyId(){
        return this.policyId;
    }
    public void setPolicyType(String policyType){
        this.policyType = policyType;
    }
    public String getPolicyType(){
        return this.policyType;
    }
    public void setOffer(Offer offer){
        this.offer = offer;
    }
    public Offer getOffer(){
        return this.offer;
    }
    public void setOfferedEntityType(String offeredEntityType){
        this.offeredEntityType = offeredEntityType;
    }
    public String getOfferedEntityType(){
        return this.offeredEntityType;
    }
    public void setOfferedEntityId(String offeredEntityId){
        this.offeredEntityId = offeredEntityId;
    }
    public String getOfferedEntityId(){
        return this.offeredEntityId;
    }
    public void setPrice(Price price){
        this.price = price;
    }
    public Price getPrice(){
        return this.price;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setContractName(String contractName){
        this.contractName = contractName;
    }
    public String getContractName(){
        return this.contractName;
    }
    public void setContentTypes(List<String> contentTypes){
        this.contentTypes = contentTypes;
    }
    public List<String> getContentTypes(){
        return this.contentTypes;
    }
	public List<VideoContents> getVideoContents() {
		return videoContents;
	}
	public void setVideoContents(List<VideoContents> videoContents) {
		this.videoContents = videoContents;
	}
	@Override
	public String toString() {
		return "PurchaseOptions [startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", policyGroupId="
				+ policyGroupId + ", policyId=" + policyId + ", policyType=" + policyType + ", offer=" + offer
				+ ", offeredEntityType=" + offeredEntityType + ", offeredEntityId=" + offeredEntityId + ", price="
				+ price + ", country=" + country + ", contractName=" + contractName + ", contentTypes=" + contentTypes
				+ ", videoContents=" + videoContents + "]";
	}
	
}
