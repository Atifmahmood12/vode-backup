package com.vidscape.pojo.moviecontent;

import java.util.List;

import com.vidscape.pojo.brand.BrandRoot;
import com.vidscape.pojo.common.Translation;
import com.vidscape.pojo.genre.GenreRoot;
import com.vidscape.pojo.series.SeriesRoot;
import com.vidscape.pojo.subscription.ImageContents;
import com.vidscape.pojo.subscription.PurchaseOptions;
import com.vidscape.pojo.subscription.SubscriptionRoot;

public class MovieContentRoot {

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

    private String showType;

    private String keywords;
    private SeriesRoot series;
    private BrandRoot brand;
    

    private List<GenreRoot> genres;

    private List<Contributors> contributors;

    private List<SubscriptionRoot> subscriptionPackages;

    private List<ImageContents> imageContents;

    private Translation episodeName;

    private String displayRunTime;

    private int yearOfRelease;

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

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
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

	public List<GenreRoot> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreRoot> genres) {
		this.genres = genres;
	}

	public List<Contributors> getContributors() {
		return contributors;
	}

	public void setContributors(List<Contributors> contributors) {
		this.contributors = contributors;
	}

	public List<SubscriptionRoot> getSubscriptionPackages() {
		return subscriptionPackages;
	}

	public void setSubscriptionPackages(List<SubscriptionRoot> subscriptionPackages) {
		this.subscriptionPackages = subscriptionPackages;
	}

	public List<ImageContents> getImageContents() {
		return imageContents;
	}

	public void setImageContents(List<ImageContents> imageContents) {
		this.imageContents = imageContents;
	}

	public Translation getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(Translation episodeName) {
		this.episodeName = episodeName;
	}

	public String getDisplayRunTime() {
		return displayRunTime;
	}

	public void setDisplayRunTime(String displayRunTime) {
		this.displayRunTime = displayRunTime;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public SeriesRoot getSeries() {
		return series;
	}

	public void setSeries(SeriesRoot series) {
		this.series = series;
	}

	public BrandRoot getBrand() {
		return brand;
	}

	public void setBrand(BrandRoot brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "MovieContentRoot [id=" + id + ", provider=" + provider + ", startDateTime=" + startDateTime
				+ ", endDateTime=" + endDateTime + ", entitlementId=" + entitlementId + ", purchaseOptions="
				+ purchaseOptions + ", titleSortName=" + titleSortName + ", titleBrief=" + titleBrief + ", titleMedium="
				+ titleMedium + ", titleLong=" + titleLong + ", summaryShort=" + summaryShort + ", summaryMedium="
				+ summaryMedium + ", summaryLong=" + summaryLong + ", showType=" + showType + ", keywords=" + keywords
				+ ", series=" + series + ", brand=" + brand + ", genres=" + genres + ", contributors=" + contributors
				+ ", subscriptionPackages=" + subscriptionPackages + ", imageContents=" + imageContents
				+ ", episodeName=" + episodeName + ", displayRunTime=" + displayRunTime + ", yearOfRelease="
				+ yearOfRelease + "]";
	}

	
}
