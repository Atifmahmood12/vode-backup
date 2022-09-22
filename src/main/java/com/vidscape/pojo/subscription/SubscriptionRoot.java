package com.vidscape.pojo.subscription;

import java.util.List;

import com.vidscape.pojo.common.Translation;

public class SubscriptionRoot {

	private String id;
	private String provider;

	private String startDateTime;

	private String endDateTime;

	private String entitlementId;

	private List<PurchaseOptions> purchaseOptions;
	
	private Translation titleSortName;
	
	private Translation titleBrief;
	
	private Translation titleMedium;
	
	private Translation titleLong;
	
	private Translation summaryShort;
	
	private Translation summaryMedium;
	
	private Translation summaryLong;
	
	private List<String> countryOfOrigin;
	private String showType;
	private String keywords;

	private List<ImageContents> imageContents;
	

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getStartDateTime() {
		return this.startDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getEndDateTime() {
		return this.endDateTime;
	}

	public void setEntitlementId(String entitlementId) {
		this.entitlementId = entitlementId;
	}

	public String getEntitlementId() {
		return this.entitlementId;
	}
	public Translation getTitleSortName() {
		return titleSortName;
	}

	public void setTitleSortName(Translation titleSortName) {
		this.titleSortName = titleSortName;
	}

	public Translation getTitleBrief() {
		return titleBrief;
	}

	public void setTitleBrief(Translation titleBrief) {
		this.titleBrief = titleBrief;
	}

	public Translation getTitleMedium() {
		return titleMedium;
	}

	public void setTitleMedium(Translation titleMedium) {
		this.titleMedium = titleMedium;
	}

	public Translation getTitleLong() {
		return titleLong;
	}

	public void setTitleLong(Translation titleLong) {
		this.titleLong = titleLong;
	}

	public Translation getSummaryShort() {
		return summaryShort;
	}

	public void setSummaryShort(Translation summaryShort) {
		this.summaryShort = summaryShort;
	}

	public Translation getSummaryMedium() {
		return summaryMedium;
	}

	public void setSummaryMedium(Translation summaryMedium) {
		this.summaryMedium = summaryMedium;
	}

	public Translation getSummaryLong() {
		return summaryLong;
	}

	public void setSummaryLong(Translation summaryLong) {
		this.summaryLong = summaryLong;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getShowType() {
		return this.showType;
	}

	
	public List<PurchaseOptions> getPurchaseOptions() {
		return purchaseOptions;
	}

	public void setPurchaseOptions(List<PurchaseOptions> purchaseOptions) {
		this.purchaseOptions = purchaseOptions;
	}

	public List<ImageContents> getImageContents() {
		return imageContents;
	}

	public void setImageContents(List<ImageContents> imageContents) {
		this.imageContents = imageContents;
	}
	

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public List<String> getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(List<String> countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	@Override
	public String toString() {
		return "SubscriptionRoot [id=" + id + ", provider=" + provider + ", startDateTime=" + startDateTime
				+ ", endDateTime=" + endDateTime + ", entitlementId=" + entitlementId + ", purchaseOptions="
				+ purchaseOptions + ", titleSortName=" + titleSortName + ", titleBrief=" + titleBrief + ", titleMedium="
				+ titleMedium + ", titleLong=" + titleLong + ", summaryShort=" + summaryShort + ", summaryMedium="
				+ summaryMedium + ", summaryLong=" + summaryLong + ", countryOfOrigin=" + countryOfOrigin
				+ ", showType=" + showType + ", keywords=" + keywords + ", imageContents=" + imageContents + "]";
	}

	
}
