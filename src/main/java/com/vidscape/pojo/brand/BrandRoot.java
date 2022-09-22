package com.vidscape.pojo.brand;

import java.util.List;

import com.vidscape.pojo.common.Translation;
import com.vidscape.pojo.genre.GenreRoot;
import com.vidscape.pojo.subscription.ImageContents;
import com.vidscape.pojo.subscription.PurchaseOptions;
import com.vidscape.pojo.subscription.SubscriptionRoot;

public class BrandRoot {

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

	private List<SubscriptionRoot> subscriptionPackages;
	private List<GenreRoot> genres;

//	private List<BrandImageContent> brandimageContents;
	
	private List<ImageContents> imageContents;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setEntitlementId(String entitlementId) {
		this.entitlementId = entitlementId;
	}

	public String getEntitlementId() {
		return this.entitlementId;
	}


	public void setPurchaseOptions(List<PurchaseOptions> purchaseOptions) {
		this.purchaseOptions = purchaseOptions;
	}

	public List<PurchaseOptions> getPurchaseOptions() {
		return this.purchaseOptions;
	}

	public void setCountryOfOrigin(List<String> countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public List<String> getCountryOfOrigin() {
		return this.countryOfOrigin;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getShowType() {
		return this.showType;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return this.keywords;
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

	/*public List<BrandImageContent> getBrandimageContents() {
		return brandimageContents;
	}

	public void setBrandimageContents(List<BrandImageContent> brandimageContents) {
		this.brandimageContents = brandimageContents;
	}*/

	public List<ImageContents> getImageContents() {
		return imageContents;
	}

	public void setImageContents(List<ImageContents> imageContents) {
		this.imageContents = imageContents;
	}

	

	
	
}
