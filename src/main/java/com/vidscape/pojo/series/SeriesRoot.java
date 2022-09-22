package com.vidscape.pojo.series;

import java.util.List;

import com.vidscape.pojo.brand.BrandRoot;
import com.vidscape.pojo.common.Translation;
import com.vidscape.pojo.genre.GenreRoot;
import com.vidscape.pojo.subscription.ImageContents;
import com.vidscape.pojo.subscription.PurchaseOptions;
import com.vidscape.pojo.subscription.SubscriptionRoot;

public class SeriesRoot {
	
	private String id;

    private String provider;

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
    private BrandRoot brand;
    
    private List<SubscriptionRoot> subscriptionPackages;
	
    private List<GenreRoot> genres;
	
	private List<ImageContents> imageContents;
    private String season;
    private String programCount;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getEntitlementId() {
		return entitlementId;
	}
	public void setEntitlementId(String entitlementId) {
		this.entitlementId = entitlementId;
	}
	public List<PurchaseOptions> getPurchaseOptions() {
		return purchaseOptions;
	}
	public void setPurchaseOptions(List<PurchaseOptions> purchaseOptions) {
		this.purchaseOptions = purchaseOptions;
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
	public List<String> getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(List<String> countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public BrandRoot getBrand() {
		return brand;
	}
	public void setBrand(BrandRoot brand) {
		this.brand = brand;
	}
	public List<SubscriptionRoot> getSubscriptionPackages() {
		return subscriptionPackages;
	}
	public void setSubscriptionPackages(List<SubscriptionRoot> subscriptionPackages) {
		this.subscriptionPackages = subscriptionPackages;
	}
	public List<GenreRoot> getGenres() {
		return genres;
	}
	public void setGenres(List<GenreRoot> genres) {
		this.genres = genres;
	}
	public List<ImageContents> getImageContents() {
		return imageContents;
	}
	public void setImageContents(List<ImageContents> imageContents) {
		this.imageContents = imageContents;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getProgramCount() {
		return programCount;
	}
	public void setProgramCount(String programCount) {
		this.programCount = programCount;
	}
	@Override
	public String toString() {
		return "SeriesRoot [id=" + id + ", provider=" + provider + ", entitlementId=" + entitlementId
				+ ", purchaseOptions=" + purchaseOptions + ", titleSortName=" + titleSortName + ", titleBrief="
				+ titleBrief + ", titleMedium=" + titleMedium + ", titleLong=" + titleLong + ", summaryShort="
				+ summaryShort + ", summaryMedium=" + summaryMedium + ", summaryLong=" + summaryLong
				+ ", countryOfOrigin=" + countryOfOrigin + ", showType=" + showType + ", keywords=" + keywords
				+ ", brand=" + brand + ", subscriptionPackages=" + subscriptionPackages + ", genres=" + genres
				+ ", imageContents=" + imageContents + ", season=" + season + ", programCount=" + programCount + "]";
	}


}
