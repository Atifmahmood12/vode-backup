package com.vidscape.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vidscape.pojo.brand.BrandRoot;
import com.vidscape.pojo.common.Translation;
import com.vidscape.pojo.genre.GenreRoot;
import com.vidscape.pojo.moviecontent.ContentRedention;
import com.vidscape.pojo.moviecontent.ContentURLs;
import com.vidscape.pojo.moviecontent.LicenseAcquisitionUrl;
import com.vidscape.pojo.moviecontent.MovieContentRoot;
import com.vidscape.pojo.moviecontent.VideoContents;
import com.vidscape.pojo.series.SeriesRoot;
import com.vidscape.pojo.subscription.ConsrUrl;
import com.vidscape.pojo.subscription.ImageContents;
import com.vidscape.pojo.subscription.Offer;
import com.vidscape.pojo.subscription.Price;
import com.vidscape.pojo.subscription.PurchaseOptions;
import com.vidscape.pojo.subscription.Renditions;
import com.vidscape.pojo.subscription.SubscriptionRoot;
import com.vidscape.utils.Utils;

public class PojoBuilder {
	StringBuilder sb = null;

	public PojoBuilder() {
		sb = new StringBuilder();
	}

	public GenreRoot buildGenrePojo(Map<String, String> dataMap) {
		try {
			GenreRoot gRoot = null;
			Translation title = new Translation();

			gRoot = new GenreRoot();

			gRoot.setId(dataMap.get("id"));
			title.setEng(dataMap.get("titleENG"));
			title.setVi(dataMap.get("titleVI"));
			gRoot.setTitle(title);
			return gRoot;

		} catch (NullPointerException e) {
			sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
		} catch (Exception e) {
			sb.append(e + "<<< POJO building got failed>>>");
		}
		return null;

	}

	public SubscriptionRoot buildSubscriptionPojo(Map<String, String> subMap) {
		try {
			SubscriptionRoot subRoot = null;
			Translation titleSortName = new Translation();
			Translation titleBrief = new Translation();
			Translation titleMedium = new Translation();
			Translation titleLong = new Translation();
			Translation summaryShort = new Translation();
			Translation summaryMedium = new Translation();
			Translation summaryLong = new Translation();
			PurchaseOptions purchaseOptions = new PurchaseOptions();
			List<PurchaseOptions> purchaseOpt = new ArrayList<PurchaseOptions>();
			ImageContents imageContents = new ImageContents();
			List<ImageContents> imageCont = new ArrayList<ImageContents>();
			Offer offer = new Offer();
			Price price = new Price();
			List<String> contentTypes = new ArrayList<String>();
			ConsrUrl consumerURL = new ConsrUrl();
			Renditions renditions = new Renditions();

			// =============
			subRoot = new SubscriptionRoot();
			subRoot.setId(subMap.get("sub_ID"));
			subRoot.setStartDateTime(subMap.get("sub_startDateTime"));
			subRoot.setEndDateTime(subMap.get("sub_endDateTime"));
			subRoot.setEntitlementId(subMap.get("sub_entitlementId"));
			purchaseOptions.setStartDateTime(subMap.get("purchaseOptions_startDateTime"));
			purchaseOptions.setEndDateTime(subMap.get("purchaseOptions_endDateTime"));
			purchaseOptions.setPolicyGroupId(Integer.parseInt(subMap.get("purchaseOptions_policyGroupId")));
			purchaseOptions.setPolicyId(Integer.parseInt(subMap.get("purchaseOptions_policyId")));
			purchaseOptions.setPolicyType(subMap.get("purchaseOptions_policyType"));
			offer.setId(subMap.get("offer_ID"));
			purchaseOptions.setOffer(offer);
			purchaseOptions.setOfferedEntityType(subMap.get("offeredEntityType"));
			purchaseOptions.setOfferedEntityId(subMap.get("offeredEntityId"));
			price.setUSD(Integer.parseInt(subMap.get("price_USD")));
			purchaseOptions.setPrice(price);
			purchaseOptions.setCountry(subMap.get("country"));
			purchaseOptions.setContractName(subMap.get("contractName"));
			contentTypes.add(subMap.get("contentTypes_val1"));
			contentTypes.add(subMap.get("contentTypes_val2"));
			purchaseOptions.setContentTypes(contentTypes);
			purchaseOpt.add(purchaseOptions);

			subRoot.setPurchaseOptions(purchaseOpt);

			titleSortName.setEng(subMap.get("titleSortName_eng"));
			titleSortName.setVi(subMap.get("titleSortName_vi"));
			subRoot.setTitleSortName(titleSortName);
			titleBrief.setEng(subMap.get("titleBrief_eng"));
			titleBrief.setVi(subMap.get("titleBrief_vi"));
			subRoot.setTitleBrief(titleBrief);
			titleMedium.setEng(subMap.get("titleMedium_eng"));
			titleMedium.setVi(subMap.get("titleMedium_vi"));
			subRoot.setTitleMedium(titleMedium);
			titleLong.setEng(subMap.get("titleLong_vi"));
			titleLong.setVi(subMap.get("titleLong_vi"));
			subRoot.setTitleLong(titleLong);
			summaryShort.setEng(subMap.get("summaryShort_eng"));
			summaryShort.setVi(subMap.get("summaryShort_vi"));
			subRoot.setSummaryShort(summaryShort);
			summaryMedium.setEng(subMap.get("summaryMedium_eng"));
			summaryMedium.setVi(subMap.get("summaryMedium_vi"));
			subRoot.setSummaryMedium(summaryMedium);
			summaryLong.setEng(subMap.get("summaryLong_eng"));
			summaryLong.setVi(subMap.get("summaryLong_vi"));
			subRoot.setSummaryLong(summaryLong);

			subRoot.setShowType(subMap.get("showType"));

			imageContents.setContentType(subMap.get("imageContents_contentType"));
			imageContents.setXResolution(Integer.parseInt(subMap.get("imageContents_xResolution")));
			imageContents.setYResolution(Integer.parseInt(subMap.get("imageContents_yResolution")));
			consumerURL.setConsumerUrl(subMap.get("renditions_consumerUrl"));
			imageContents.setRenditions(renditions);
			renditions.setConsumerUrl(consumerURL);
			imageCont.add(imageContents);
			subRoot.setImageContents(imageCont);
			return subRoot;

		} catch (NullPointerException e) {
			sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
		} catch (Exception e) {
			sb.append(e + "<<< POJO building got failed>>>");
		}
		return null;

	}

	public BrandRoot buildBrandPojo(Map<String, String> brandMap) {
		try {

			BrandRoot brandRoot = null;
			Translation title = new Translation();
			Translation titleSortName = new Translation();
			Translation brandTitleSortName = new Translation();

			Translation titleBrief = new Translation();
			Translation brandTitleBrief = new Translation();

			Translation titleMedium = new Translation();
			Translation brandTitleMedium = new Translation();

			Translation titleLong = new Translation();
			Translation brandTitleLong = new Translation();

			Translation summaryShort = new Translation();
			Translation brandSummaryShort = new Translation();

			Translation summaryMedium = new Translation();
			Translation brandSummaryMedium = new Translation();

			Translation summaryLong = new Translation();

			Translation brandSummaryLong = new Translation();

			PurchaseOptions purchaseOptions = new PurchaseOptions();
			List<PurchaseOptions> purchaseOpt = new ArrayList<PurchaseOptions>();

			SubscriptionRoot subscriptionRoot = new SubscriptionRoot();
			List<SubscriptionRoot> subRoot = new ArrayList<SubscriptionRoot>();

			ImageContents imageContents = new ImageContents();
			List<ImageContents> imageCont = new ArrayList<ImageContents>();

			ImageContents imageContentsForBrand = new ImageContents();
			List<ImageContents> imageContFB = new ArrayList<ImageContents>();

			Offer offer = new Offer();
			Price price = new Price();
			List<String> contentTypes = new ArrayList<String>();
			ConsrUrl consumerURL = new ConsrUrl();
			Renditions renditions = new Renditions();
			Renditions brandRenditions = new Renditions();
			List<String> countryOfOrigin = new ArrayList<String>();
			GenreRoot gRoot = new GenreRoot();

			brandRoot = new BrandRoot();

			brandRoot.setId(brandMap.get("brand_id"));
			brandRoot.setProvider(brandMap.get("brnad_Provder"));
			brandRoot.setEntitlementId(brandMap.get("brand_entitlementId"));

			purchaseOptions.setStartDateTime(brandMap.get("purchaseOptions_startDateTime"));
			purchaseOptions.setEndDateTime(brandMap.get("purchaseOptions_endDateTime"));
			purchaseOptions.setPolicyGroupId(Integer.parseInt(brandMap.get("purchaseOptions_policyGroupId")));
			purchaseOptions.setPolicyId(Integer.parseInt(brandMap.get("purchaseOptions_policyId")));
			purchaseOptions.setPolicyType(brandMap.get("purchaseOptions_policyType"));
			offer.setId(brandMap.get("offer_ID"));
			purchaseOptions.setOffer(offer);

			purchaseOptions.setOfferedEntityType(brandMap.get("offeredEntityType"));
			purchaseOptions.setOfferedEntityId(brandMap.get("offeredEntityId"));

			price.setUSD(Integer.parseInt(brandMap.get("price_USD")));
			purchaseOptions.setPrice(price);
			purchaseOptions.setCountry(brandMap.get("country"));
			purchaseOptions.setContractName(brandMap.get("contractName"));
			contentTypes.add(brandMap.get("contentTypes_val1"));
			contentTypes.add(brandMap.get("contentTypes_val2"));
			purchaseOptions.setContentTypes(contentTypes);
			purchaseOpt.add(purchaseOptions);
			brandRoot.setPurchaseOptions(purchaseOpt);

			brandTitleSortName.setEng(brandMap.get("brand_titleSortName_eng"));
			brandTitleSortName.setVi(brandMap.get("brand_titleSortName_vi"));
			brandRoot.setTitleSortName(brandTitleSortName);

			brandTitleBrief.setEng(brandMap.get("brand_titleBrief_eng"));
			brandTitleBrief.setVi(brandMap.get("brand_titleBrief_vi"));
			brandRoot.setTitleBrief(brandTitleBrief);

			brandTitleMedium.setEng(brandMap.get("brand_titleMedium_eng"));
			brandTitleMedium.setVi(brandMap.get("brand_titleMedium_vi"));
			brandRoot.setTitleMedium(brandTitleMedium);

			brandTitleLong.setEng(brandMap.get("brand_titleLong_eng"));
			brandTitleLong.setVi(brandMap.get("brand_titleLong_vi"));
			brandRoot.setTitleLong(brandTitleLong);

			brandSummaryShort.setEng(brandMap.get("brand_summaryShort_eng"));
			brandSummaryShort.setVi(brandMap.get("brand_summaryShort_vi"));
			brandRoot.setSummaryShort(brandSummaryShort);

			brandSummaryMedium.setEng(brandMap.get("brand_summaryMedium_eng"));
			brandSummaryMedium.setVi(brandMap.get("brand_summaryMedium_vi"));
			brandRoot.setSummaryMedium(brandSummaryMedium);

			brandSummaryLong.setEng(brandMap.get("brand_summaryLong_eng"));
			brandSummaryLong.setVi(brandMap.get("brand_summaryLong_vi"));
			brandRoot.setSummaryLong(brandSummaryLong);

			countryOfOrigin.add(brandMap.get("countryOfOrigin"));
			brandRoot.setCountryOfOrigin(countryOfOrigin);
			brandRoot.setShowType(brandMap.get("brand_showType"));
			brandRoot.setKeywords(brandMap.get("brand_keywords"));

			subscriptionRoot.setId(brandMap.get("subscriptionPackages_id"));
			subscriptionRoot.setEntitlementId(brandMap.get("subscriptionPackages_entitlementId"));

			titleSortName.setEng(brandMap.get("titleSortName_eng"));
			titleSortName.setVi(brandMap.get("titleSortName_vi"));
			subscriptionRoot.setTitleSortName(titleSortName);

			titleBrief.setEng(brandMap.get("titleBrief_eng"));
			titleBrief.setVi(brandMap.get("titleBrief_vi"));
			subscriptionRoot.setTitleBrief(titleBrief);
			titleMedium.setEng(brandMap.get("titleMedium_eng"));
			titleMedium.setVi(brandMap.get("titleMedium_vi"));
			subscriptionRoot.setTitleMedium(titleMedium);
			titleLong.setEng(brandMap.get("titleLong_vi"));
			titleLong.setVi(brandMap.get("titleLong_vi"));
			subscriptionRoot.setTitleLong(titleLong);
			summaryShort.setEng(brandMap.get("summaryShort_eng"));
			summaryShort.setVi(brandMap.get("summaryShort_vi"));
			subscriptionRoot.setSummaryShort(summaryShort);
			summaryMedium.setEng(brandMap.get("summaryMedium_eng"));
			summaryMedium.setVi(brandMap.get("summaryMedium_vi"));
			subscriptionRoot.setSummaryMedium(summaryMedium);
			summaryLong.setEng(brandMap.get("summaryLong_eng"));
			summaryLong.setVi(brandMap.get("summaryLong_vi"));
			subscriptionRoot.setSummaryLong(summaryLong);

			subscriptionRoot.setShowType(brandMap.get("showType"));

			imageContents.setContentType(brandMap.get("imageContents_contentType"));
			imageContents.setXResolution(Integer.parseInt(brandMap.get("imageContents_xResolution")));
			imageContents.setYResolution(Integer.parseInt(brandMap.get("imageContents_yResolution")));
			consumerURL.setConsumerUrl(brandMap.get("renditions_consumerUrl"));
			renditions.setConsumerUrl(consumerURL);
			imageContents.setRenditions(renditions);
			imageCont.add(imageContents);
			subscriptionRoot.setImageContents(imageCont);
			subRoot.add(subscriptionRoot);
			brandRoot.setSubscriptionPackages(subRoot);

			title.setEng(brandMap.get("genre_id"));
			title.setVi(brandMap.get("genre_titleVI"));
			gRoot.setId(brandMap.get("genre_titleENG"));
			gRoot.setTitle(title);

			imageContentsForBrand.setContentType(brandMap.get("brand_imageContents_contentType"));
			consumerURL.setConsumerUrl(brandMap.get("brand_imageContents_renditions_consumerUrl"));
			brandRenditions.setConsumerUrl(consumerURL);
			imageContentsForBrand.setRenditions(brandRenditions);
			imageContFB.add(imageContentsForBrand);
			brandRoot.setImageContents(imageContFB);

			return brandRoot;

		} catch (NullPointerException e) {
			sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
		} catch (Exception e) {
			sb.append(e + "<<< POJO building got failed>>>");
		}
		return null;

	}

	public SeriesRoot buildSeriesPojo(Map<String, String> seriesMap) {
		try {

			SeriesRoot seriesRoot = null;

			PurchaseOptions purchaseOptions = new PurchaseOptions();
			List<PurchaseOptions> purchaseOpt = new ArrayList<PurchaseOptions>();

			Translation seriesTitleSortName = new Translation();
			Translation seriesTitleBrief = new Translation();
			Translation seriesTitleMedium = new Translation();
			Translation seriesTitleLong = new Translation();
			Translation seriesSummaryShort = new Translation();
			Translation seriesSummaryMedium = new Translation();
			Translation seriesSummaryLong = new Translation();
			List<String> countryOfOrigin = new ArrayList<String>();

			Translation brandTitleSortName = new Translation();
			Translation brandTitleBrief = new Translation();
			Translation brandTitleMedium = new Translation();
			Translation brandTitleLong = new Translation();
			Translation brandSummaryShort = new Translation();
			Translation brandSummaryMedium = new Translation();
			Translation brandSummaryLong = new Translation();
			List<String> countryOfOriginforBrand = new ArrayList<String>();

			BrandRoot brandRoot = new BrandRoot();

			Translation subscriptionTitleSortName = new Translation();
			Translation subscriptionTitleBrief = new Translation();
			Translation subscriptionTitleMedium = new Translation();
			Translation subscriptionTitleLong = new Translation();
			Translation subscriptionSummaryShort = new Translation();
			Translation subscriptionSummaryMedium = new Translation();
			Translation subscriptionSummaryLong = new Translation();

			SubscriptionRoot subscriptionRoot = new SubscriptionRoot();
			List<SubscriptionRoot> subRoot = new ArrayList<SubscriptionRoot>();

			GenreRoot genreRoot = new GenreRoot();
			Translation gtitle = new Translation();
			List<GenreRoot> gRoot = new ArrayList<GenreRoot>();

			ImageContents imageContentForsub = new ImageContents();
			List<ImageContents> imageContSub = new ArrayList<ImageContents>();
			ImageContents imageContentsForBrand = new ImageContents();
			List<ImageContents> imageContFB = new ArrayList<ImageContents>();
			ImageContents imageContentForSeries = new ImageContents();
			List<ImageContents> imageContSR = new ArrayList<ImageContents>();

			Offer offer = new Offer();
			Price price = new Price();
			List<String> contentTypes = new ArrayList<String>();
			ConsrUrl consumerURL = new ConsrUrl();
			Renditions renditions = new Renditions();
			// Renditions brandRenditions = new Renditions();

			seriesRoot = new SeriesRoot();

			seriesRoot.setId(seriesMap.get("series_id"));
			seriesRoot.setProvider(seriesMap.get("series_Provder"));
			seriesRoot.setEntitlementId(seriesMap.get("series_entitlementId"));

			purchaseOptions.setStartDateTime(seriesMap.get("purchaseOptions_startDateTime"));
			purchaseOptions.setEndDateTime(seriesMap.get("purchaseOptions_endDateTime"));
			purchaseOptions.setPolicyGroupId(Integer.parseInt(seriesMap.get("purchaseOptions_policyGroupId")));
			purchaseOptions.setPolicyId(Integer.parseInt(seriesMap.get("purchaseOptions_policyId")));
			purchaseOptions.setPolicyType(seriesMap.get("purchaseOptions_policyType"));
			offer.setId(seriesMap.get("offer_ID"));
			purchaseOptions.setOffer(offer);
			purchaseOptions.setOfferedEntityType(seriesMap.get("offeredEntityType"));
			purchaseOptions.setOfferedEntityId(seriesMap.get("offeredEntityId"));
			price.setUSD(Integer.parseInt(seriesMap.get("price_USD")));
			purchaseOptions.setPrice(price);
			purchaseOptions.setCountry(seriesMap.get("country"));
			purchaseOptions.setContractName(seriesMap.get("contractName"));
			// Need to add contentTypes field in csv file.
			contentTypes.add(seriesMap.get("contentTypes"));
			purchaseOptions.setContentTypes(contentTypes);
			purchaseOpt.add(purchaseOptions);
			seriesRoot.setPurchaseOptions(purchaseOpt);

			seriesTitleSortName.setVi(seriesMap.get("series_titleSortName_vi"));
			seriesTitleSortName.setEng(seriesMap.get("series_titleSortName_eng"));
			seriesRoot.setTitleSortName(seriesTitleSortName);
			seriesTitleBrief.setVi(seriesMap.get("series_titleBrief_vi"));
			seriesTitleBrief.setEng(seriesMap.get("series_titleBrief_eng"));
			seriesRoot.setTitleBrief(seriesTitleBrief);
			seriesTitleMedium.setVi(seriesMap.get("series_titleMedium_vi"));
			seriesTitleMedium.setEng(seriesMap.get("series_titleMedium_eng"));
			seriesRoot.setTitleMedium(seriesTitleMedium);
			seriesTitleLong.setVi(seriesMap.get("series_titleLong_vi"));
			seriesTitleLong.setEng(seriesMap.get("series_titleLong_eng"));
			seriesRoot.setTitleLong(seriesTitleLong);

			seriesSummaryShort.setVi(seriesMap.get("series_summaryShort_vi"));
			seriesSummaryShort.setEng(seriesMap.get("series_summaryShort_eng"));

			seriesRoot.setSummaryShort(seriesSummaryShort);
			seriesSummaryMedium.setVi(seriesMap.get("series_summaryMedium_vi"));
			seriesSummaryMedium.setEng(seriesMap.get("series_summaryMedium_eng"));
			seriesRoot.setSummaryMedium(seriesSummaryMedium);
			seriesSummaryLong.setVi(seriesMap.get("series_summaryLong_vi"));
			seriesSummaryLong.setEng(seriesMap.get("series_summaryLong_eng"));
			seriesRoot.setSummaryLong(seriesSummaryLong);
			countryOfOrigin.add(seriesMap.get("series_countryOfOrigin"));
			seriesRoot.setCountryOfOrigin(countryOfOrigin);
			seriesRoot.setShowType(seriesMap.get("series_showType"));
			seriesRoot.setKeywords(seriesMap.get("series_keywords"));

			brandRoot.setId(seriesMap.get("brand_id"));
			brandRoot.setProvider(seriesMap.get("brand_Provder"));
			brandRoot.setEntitlementId(seriesMap.get("brand_entitlementId"));
			brandTitleSortName.setEng(seriesMap.get("brand_titleSortName_eng"));
			brandTitleSortName.setVi(seriesMap.get("brand_titleSortName_vi"));
			brandRoot.setTitleSortName(brandTitleSortName);
			brandTitleBrief.setEng(seriesMap.get("brand_titleBrief_eng"));
			brandTitleBrief.setVi(seriesMap.get("brand_titleBrief_vi"));
			brandRoot.setTitleBrief(brandTitleBrief);
			brandTitleMedium.setEng(seriesMap.get("brand_titleMedium_eng"));
			brandTitleMedium.setVi(seriesMap.get("brand_titleMedium_vi"));
			brandRoot.setTitleMedium(brandTitleMedium);
			brandTitleLong.setEng(seriesMap.get("brand_titleLong_eng"));
			brandTitleLong.setVi(seriesMap.get("brand_titleLong_vi"));
			brandRoot.setTitleLong(brandTitleLong);
			brandSummaryShort.setEng(seriesMap.get("brand_summaryShort_eng"));
			brandSummaryShort.setVi(seriesMap.get("brand_summaryShort_vi"));
			brandRoot.setSummaryShort(brandSummaryShort);
			brandSummaryMedium.setEng(seriesMap.get("brand_summaryMedium_eng"));
			brandSummaryMedium.setVi(seriesMap.get("brand_summaryMedium_vi"));
			brandRoot.setSummaryMedium(brandSummaryMedium);
			brandSummaryLong.setEng(seriesMap.get("brand_summaryLong_eng"));
			brandSummaryLong.setVi(seriesMap.get("brand_summaryLong_vi"));
			brandRoot.setSummaryLong(brandSummaryLong);
			countryOfOriginforBrand.add(seriesMap.get("brand_countryOfOrigin"));
			brandRoot.setCountryOfOrigin(countryOfOriginforBrand);
			brandRoot.setShowType(seriesMap.get("brand_showType"));
			brandRoot.setKeywords(seriesMap.get("brand_keywords"));
			imageContentsForBrand.setContentType(seriesMap.get("brand_imageContents_contentType"));
			consumerURL.setConsumerUrl(seriesMap.get("brand_imageContents_renditions_consumerUrl"));
			renditions.setConsumerUrl(consumerURL);
			imageContentsForBrand.setRenditions(renditions);
			imageContFB.add(imageContentsForBrand);
			brandRoot.setImageContents(imageContFB);
			seriesRoot.setBrand(brandRoot);
			// subscriptions///

			subscriptionRoot.setId(seriesMap.get("subscriptionPackages_id"));
			subscriptionRoot.setEntitlementId(seriesMap.get("subscriptionPackages_entitlementId"));
			subscriptionTitleSortName.setEng(seriesMap.get("subscriptionPackages_titleSortName_eng"));
			subscriptionTitleSortName.setVi(seriesMap.get("subscriptionPackages_titleSortName_vi"));
			subscriptionRoot.setTitleSortName(subscriptionTitleSortName);
			subscriptionTitleBrief.setEng(seriesMap.get("subscriptionPackages_titleBrief_eng"));
			subscriptionTitleBrief.setVi(seriesMap.get("subscriptionPackages_titleBrief_vi"));
			subscriptionRoot.setTitleBrief(subscriptionTitleBrief);
			subscriptionTitleMedium.setEng(seriesMap.get("subscriptionPackages_titleMedium_eng"));
			subscriptionTitleMedium.setVi(seriesMap.get("subscriptionPackages_titleMedium_vi"));
			subscriptionRoot.setTitleMedium(subscriptionTitleMedium);
			subscriptionTitleLong.setEng(seriesMap.get("subscriptionPackages_titleLong_eng"));
			subscriptionTitleLong.setVi(seriesMap.get("subscriptionPackages_titleLong_vi"));
			subscriptionRoot.setTitleLong(subscriptionTitleLong);
			subscriptionSummaryShort.setEng(seriesMap.get("subscriptionPackages_summaryShort_eng"));
			subscriptionSummaryShort.setVi(seriesMap.get("subscriptionPackages_summaryShort_vi"));
			subscriptionRoot.setSummaryShort(subscriptionSummaryShort);
			subscriptionSummaryMedium.setEng(seriesMap.get("subscriptionPackages_summaryMedium_eng"));
			subscriptionSummaryMedium.setVi(seriesMap.get("subscriptionPackages_summaryMedium_vi"));
			subscriptionRoot.setSummaryMedium(subscriptionSummaryMedium);
			subscriptionSummaryLong.setEng(seriesMap.get("subscriptionPackages_summaryLong_eng"));
			subscriptionSummaryLong.setVi(seriesMap.get("subscriptionPackages_summaryLong_vi"));
			subscriptionRoot.setSummaryLong(subscriptionSummaryLong);
			subscriptionRoot.setShowType(seriesMap.get("subscriptionPackages_showType"));

			imageContentForsub.setContentType(seriesMap.get("subscription_imageContents_contentType"));
			imageContentForsub
					.setXResolution(Integer.parseInt(seriesMap.get("subscriptionPackages_imageContents_xResolution")));
			imageContentForsub
					.setYResolution(Integer.parseInt(seriesMap.get("subscriptionPackages_imageContents_yResolution")));
			consumerURL.setConsumerUrl(seriesMap.get("subscriotion_renditions_consumerUrl"));
			imageContentForsub.setRenditions(renditions);
			renditions.setConsumerUrl(consumerURL);
			imageContSub.add(imageContentForsub);
			subscriptionRoot.setImageContents(imageContSub);
			subRoot.add(subscriptionRoot);
			seriesRoot.setSubscriptionPackages(subRoot);

			genreRoot.setId(seriesMap.get("genre_id"));
			gtitle.setVi(seriesMap.get("genre_titleVI"));
			gtitle.setEng(seriesMap.get("genre_titleENG"));
			genreRoot.setTitle(gtitle);
			gRoot.add(genreRoot);
			seriesRoot.setGenres(gRoot);

			imageContentForSeries.setContentType(seriesMap.get("Series_imageContents_contentType"));
			imageContentForSeries.setRenditions(renditions);
			consumerURL.setConsumerUrl(seriesMap.get("Series_imageContents_renditions_consumerUrl"));
			renditions.setConsumerUrl(consumerURL);
			imageContSR.add(imageContentForSeries);
			seriesRoot.setImageContents(imageContSR);
			seriesRoot.setSeason(seriesMap.get("season"));
			seriesRoot.setProgramCount(seriesMap.get("programCount"));

			return seriesRoot;

		} catch (NullPointerException e) {
			sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
		} catch (Exception e) {
			sb.append(e + "<<< POJO building got failed>>>");
		}
		return null;

	}

	public MovieContentRoot buildMovieContentPojo(Map<String, String> movieContentMap) {
		try {

			MovieContentRoot movieContentRoot = null;

			PurchaseOptions purchaseOptions = new PurchaseOptions();
			List<PurchaseOptions> purchaseOpt = new ArrayList<PurchaseOptions>();

			VideoContents videoContents = new VideoContents();
			List<VideoContents> vContent = new ArrayList<VideoContents>();

			LicenseAcquisitionUrl licenseAcquisitionUrl = new LicenseAcquisitionUrl();
			List<LicenseAcquisitionUrl> lAcquisitionURL = new ArrayList<LicenseAcquisitionUrl>();

			Offer offer = new Offer();
			Price price = new Price();
			List<String> contentTypes = new ArrayList<String>();
			List<String> countryOfOrigin = new ArrayList<String>();

			Translation contentTitleSortName = new Translation();
			Translation contentTitleBrief = new Translation();
			Translation contentTitleMedium = new Translation();
			Translation contentTitleLong = new Translation();
			Translation contentSummaryShort = new Translation();
			Translation contentSummaryMedium = new Translation();
			Translation contentSummaryLong = new Translation();

			Translation subscriptionTitleSortName = new Translation();
			Translation subscriptionTitleBrief = new Translation();
			Translation subscriptionTitleMedium = new Translation();
			Translation subscriptionTitleLong = new Translation();
			Translation subscriptionSummaryShort = new Translation();
			Translation subscriptionSummaryMedium = new Translation();
			Translation subscriptionSummaryLong = new Translation();

			SubscriptionRoot subscriptionRoot = new SubscriptionRoot();
			List<SubscriptionRoot> subRoot = new ArrayList<SubscriptionRoot>();
			ContentRedention contentRedention = new ContentRedention();

			ContentURLs contentUrlsMobile = new ContentURLs();
			ContentURLs contentUrlsPCSS = new ContentURLs();
			ContentURLs contentUrlsPCDash = new ContentURLs();

			GenreRoot genreRoot = new GenreRoot();
			Translation gtitle = new Translation();
			List<GenreRoot> gRoot = new ArrayList<GenreRoot>();

			ImageContents movieImageContents = new ImageContents();
			List<ImageContents> movieImageCont = new ArrayList<ImageContents>();
			ConsrUrl consumerURL = new ConsrUrl();
			Renditions renditions = new Renditions();

			Translation episodeName = new Translation();
			movieContentRoot = new MovieContentRoot();
			movieContentRoot.setId(movieContentMap.get("content_id"));
			movieContentRoot.setProvider(movieContentMap.get("content_provider"));
			movieContentRoot.setStartDateTime(movieContentMap.get("provider_startDateTime"));
			movieContentRoot.setEndDateTime(movieContentMap.get("provider_endDateTime"));
			movieContentRoot.setEntitlementId(movieContentMap.get("provider_entitlementId"));

			purchaseOptions.setStartDateTime(movieContentMap.get("purchaseOptions_startDateTime"));
			purchaseOptions.setEndDateTime(movieContentMap.get("purchaseOptions_endDateTime"));
			purchaseOptions.setPolicyGroupId(Integer.parseInt(movieContentMap.get("purchaseOptions_policyGroupId")));
			purchaseOptions.setPolicyId(Integer.parseInt(movieContentMap.get("purchaseOptions_policyId")));
			purchaseOptions.setPolicyType(movieContentMap.get("purchaseOptions_policyType"));
			offer.setId(movieContentMap.get("offer_ID"));
			purchaseOptions.setOffer(offer);

			purchaseOptions.setOfferedEntityType(movieContentMap.get("offeredEntityType"));
			purchaseOptions.setOfferedEntityId(movieContentMap.get("offeredEntityId"));

			price.setUSD(Integer.parseInt(movieContentMap.get("price_USD")));
			purchaseOptions.setPrice(price);
			purchaseOptions.setCountry(movieContentMap.get("country"));
			purchaseOptions.setContractName(movieContentMap.get("contractName"));
			contentTypes.add(movieContentMap.get("contentTypes"));
			purchaseOptions.setContentTypes(contentTypes);

			videoContents.setContentType(movieContentMap.get("videoContents_contentType"));
			videoContents.setDuration(movieContentMap.get("videoContents_duration"));
			videoContents.setEntitlementContentId(movieContentMap.get("videoContents_entitlementContentId"));

			contentUrlsMobile.setConsumerUrl(movieContentMap.get("renditions_MOBILE_HD_consumerUrl"));

			contentRedention.setMOBILE_HD(contentUrlsMobile);

			contentUrlsPCSS.setConsumerUrl(movieContentMap.get("renditions_PC_SS_HD_consumerUrl"));
			licenseAcquisitionUrl.setPlayready(movieContentMap.get("PC_SS_HD_licenseAcquisitionUrl_playready"));
			licenseAcquisitionUrl.setWidevine(movieContentMap.get("PC_SS_HD_licenseAcquisitionUrl_widevine"));
			lAcquisitionURL.add(licenseAcquisitionUrl);
			contentUrlsPCSS.setLicenseAcquisitionUrl(licenseAcquisitionUrl);
			contentRedention.setPC_DASH_HD(contentUrlsPCSS);

			contentUrlsPCDash.setConsumerUrl(movieContentMap.get("renditions_PC_DASH_HD_consumerUrl"));
			licenseAcquisitionUrl.setPlayready(movieContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_playready"));
			licenseAcquisitionUrl.setWidevine(movieContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_widevine"));
			lAcquisitionURL.add(licenseAcquisitionUrl);
			contentUrlsPCDash.setLicenseAcquisitionUrl(licenseAcquisitionUrl);
			contentRedention.setPC_SS_HD(contentUrlsPCDash);

			videoContents.setRenditions(contentRedention);/// Feeling that this needs to replace with
			vContent.add(videoContents); /// List
			purchaseOptions.setVideoContents(vContent);

			purchaseOpt.add(purchaseOptions);
			movieContentRoot.setPurchaseOptions(purchaseOpt);

			contentTitleSortName.setEng(movieContentMap.get("content_titleSortName_eng"));
			contentTitleSortName.setVi(movieContentMap.get("content_titleSortName_vi"));
			movieContentRoot.setTitleSortName(contentTitleSortName);

			contentTitleBrief.setEng(movieContentMap.get("content_titleBrief_eng"));
			contentTitleBrief.setVi(movieContentMap.get("content_titleBrief_vi"));
			movieContentRoot.setTitleBrief(contentTitleBrief);

			contentTitleMedium.setEng(movieContentMap.get("content_titleMedium_eng"));
			contentTitleMedium.setVi(movieContentMap.get("content_titleMedium_vi"));
			movieContentRoot.setTitleMedium(contentTitleMedium);

			contentTitleLong.setEng(movieContentMap.get("content_titleLong_eng"));
			contentTitleLong.setVi(movieContentMap.get("content_titleLong_vi"));
			movieContentRoot.setTitleLong(contentTitleLong);

			contentSummaryShort.setEng(movieContentMap.get("content_summaryShort_eng"));
			contentSummaryShort.setVi(movieContentMap.get("content_summaryShort_vi"));
			movieContentRoot.setSummaryShort(contentSummaryShort);

			contentSummaryMedium.setEng(movieContentMap.get("content_summaryMedium_eng"));
			contentSummaryMedium.setVi(movieContentMap.get("content_summaryMedium_vi"));
			movieContentRoot.setSummaryMedium(contentSummaryMedium);

			contentSummaryLong.setEng(movieContentMap.get("content_summaryLong_eng"));
			contentSummaryLong.setVi(movieContentMap.get("content_summaryLong_vi"));
			movieContentRoot.setSummaryLong(contentSummaryLong);

			movieContentRoot.setShowType(movieContentMap.get("content_showType"));
			movieContentRoot.setKeywords(movieContentMap.get("content_keywords"));

			genreRoot.setId(movieContentMap.get("genre_id"));
			genreRoot.setGenrePath(movieContentMap.get("genre_path"));
			gtitle.setVi(movieContentMap.get("genre_titleVI"));
			gtitle.setEng(movieContentMap.get("genre_titleENG"));
			genreRoot.setTitle(gtitle);
			gRoot.add(genreRoot);
			movieContentRoot.setGenres(gRoot);

			movieContentRoot.setContributors(Utils.putContributorValuesInJSon(movieContentMap.get("cast_contrinutor"),
					movieContentMap.get("cast_sortNameEng"), movieContentMap.get("cast_sortNameVi"),
					movieContentMap.get("cast_firstNameEng"), movieContentMap.get("cast_firstNameVi"),
					movieContentMap.get("cast_LasNameVi"), movieContentMap.get("cast_lastNameEng"),
					movieContentMap.get("cast_fullNameEng"), movieContentMap.get("cast_fullNameVi")));

			subscriptionRoot.setId(movieContentMap.get("subscriptionPackages_id"));
			subscriptionRoot.setProvider(movieContentMap.get("subscriptionPackages_provider"));
			subscriptionRoot.setEntitlementId(movieContentMap.get("subscriptionPackages_entitlementId"));
			subscriptionTitleSortName.setEng(movieContentMap.get("subscriptionPackages_titleSortName_eng"));
			subscriptionTitleSortName.setVi(movieContentMap.get("subscriptionPackages_titleSortName_vi"));
			subscriptionRoot.setTitleSortName(subscriptionTitleSortName);
			subscriptionTitleBrief.setEng(movieContentMap.get("subscriptionPackages_titleBrief_eng"));
			subscriptionTitleBrief.setVi(movieContentMap.get("subscriptionPackages_titleBrief_vi"));
			subscriptionRoot.setTitleBrief(subscriptionTitleBrief);
			subscriptionTitleMedium.setEng(movieContentMap.get("subscriptionPackages_titleMedium_eng"));
			subscriptionTitleMedium.setVi(movieContentMap.get("subscriptionPackages_titleMedium_vi"));
			subscriptionRoot.setTitleMedium(subscriptionTitleMedium);
			subscriptionTitleLong.setEng(movieContentMap.get("subscriptionPackages_titleLong_eng"));
			subscriptionTitleLong.setVi(movieContentMap.get("subscriptionPackages_titleLong_vi"));
			subscriptionRoot.setTitleLong(subscriptionTitleLong);
			subscriptionSummaryShort.setEng(movieContentMap.get("subscriptionPackages_summaryShort_eng"));
			subscriptionSummaryShort.setVi(movieContentMap.get("subscriptionPackages_summaryShort_vi"));
			subscriptionRoot.setSummaryShort(subscriptionSummaryShort);
			subscriptionSummaryMedium.setEng(movieContentMap.get("subscriptionPackages_summaryMedium_eng"));
			subscriptionSummaryMedium.setVi(movieContentMap.get("subscriptionPackages_summaryMedium_vi"));
			subscriptionRoot.setSummaryMedium(subscriptionSummaryMedium);
			subscriptionSummaryLong.setEng(movieContentMap.get("subscriptionPackages_summaryLong_eng"));
			subscriptionSummaryLong.setVi(movieContentMap.get("subscriptionPackages_summaryLong_vi"));
			subscriptionRoot.setSummaryLong(subscriptionSummaryLong);

			countryOfOrigin.add(movieContentMap.get("subscriptionPackages_countryoforigin"));
			subscriptionRoot.setCountryOfOrigin(countryOfOrigin);

			subscriptionRoot.setShowType(movieContentMap.get("subscriptionPackages_showType"));
			subscriptionRoot.setKeywords(movieContentMap.get("subscriptionPackages_keywords"));
			subRoot.add(subscriptionRoot);
			movieContentRoot.setSubscriptionPackages(subRoot);

			movieImageContents.setContentType(movieContentMap.get("movieContent_contentType"));
			movieImageContents.setRenditions(renditions);
			consumerURL.setConsumerUrl(movieContentMap.get("movieContent_renditions_consumerUrl"));
			renditions.setConsumerUrl(consumerURL);
			movieImageCont.add(movieImageContents);
			movieContentRoot.setImageContents(movieImageCont);
			episodeName.setEng(movieContentMap.get("episodeName_eng"));
			episodeName.setVi(movieContentMap.get("episodeName_vi"));
			movieContentRoot.setEpisodeName(episodeName);

			movieContentRoot.setDisplayRunTime(movieContentMap.get("displayRunTime"));
			movieContentRoot.setYearOfRelease(Integer.parseInt(movieContentMap.get("yearOfRelease")));

			return movieContentRoot;

		} catch (NullPointerException e) {
			sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
		} catch (Exception e) {
			sb.append(e + "<<< POJO building got failed>>>");
		}
		return null;

	}

	public MovieContentRoot buildEpisodeContentPojo(Map<String, String> episodeContentMap) {
		try {

			MovieContentRoot EpisodeContentRoot = null;

			PurchaseOptions purchaseOptions = new PurchaseOptions();
			List<PurchaseOptions> purchaseOpt = new ArrayList<PurchaseOptions>();

			VideoContents videoContents = new VideoContents();
			List<VideoContents> vContent = new ArrayList<VideoContents>();

			LicenseAcquisitionUrl licenseAcquisitionUrl = new LicenseAcquisitionUrl();
			List<LicenseAcquisitionUrl> lAcquisitionURL = new ArrayList<LicenseAcquisitionUrl>();

			Offer offer = new Offer();
			Price price = new Price();
			List<String> contentTypes = new ArrayList<String>();
			List<String> countryOfOrigin = new ArrayList<String>();

			Translation contentTitleSortName = new Translation();
			Translation contentTitleBrief = new Translation();
			Translation contentTitleMedium = new Translation();
			Translation contentTitleLong = new Translation();
			Translation contentSummaryShort = new Translation();
			Translation contentSummaryMedium = new Translation();
			Translation contentSummaryLong = new Translation();

			Translation subscriptionTitleSortName = new Translation();
			Translation subscriptionTitleBrief = new Translation();
			Translation subscriptionTitleMedium = new Translation();
			Translation subscriptionTitleLong = new Translation();
			Translation subscriptionSummaryShort = new Translation();
			Translation subscriptionSummaryMedium = new Translation();
			Translation subscriptionSummaryLong = new Translation();

			SubscriptionRoot subscriptionRoot = new SubscriptionRoot();
			List<SubscriptionRoot> subRoot = new ArrayList<SubscriptionRoot>();
			ContentRedention contentRedention = new ContentRedention();

			ContentURLs contentUrlsMobile = new ContentURLs();
			ContentURLs contentUrlsPCSS = new ContentURLs();
			ContentURLs contentUrlsPCDash = new ContentURLs();

			GenreRoot genreRoot = new GenreRoot();
			Translation gtitle = new Translation();
			List<GenreRoot> gRoot = new ArrayList<GenreRoot>();

			SeriesRoot seriesRoot = new SeriesRoot();
			BrandRoot brandRoot = new BrandRoot();

			Translation seriesTitleSortName = new Translation();
			Translation seriesTitleBrief = new Translation();
			Translation seriesTitleMedium = new Translation();
			Translation seriesTitleLong = new Translation();
			Translation seriesSummaryShort = new Translation();
			Translation seriesSummaryMedium = new Translation();
			Translation seriesSummaryLong = new Translation();

			Translation brandTitleSortName = new Translation();
			Translation brandTitleBrief = new Translation();
			Translation brandTitleMedium = new Translation();
			Translation brandTitleLong = new Translation();
			Translation brandSummaryShort = new Translation();
			Translation brandSummaryMedium = new Translation();
			Translation brandSummaryLong = new Translation();

			ImageContents movieImageContents = new ImageContents();
			List<ImageContents> movieImageCont = new ArrayList<ImageContents>();
			ConsrUrl consumerURL = new ConsrUrl();
			Renditions renditions = new Renditions();

			ImageContents imageContentForSeries = new ImageContents();
			List<ImageContents> imageContSR = new ArrayList<ImageContents>();

			ImageContents imageContentsForBrand = new ImageContents();
			List<ImageContents> imageContFB = new ArrayList<ImageContents>();

			Translation episodeName = new Translation();

			EpisodeContentRoot = new MovieContentRoot();

			EpisodeContentRoot.setId(episodeContentMap.get("content_id"));
			EpisodeContentRoot.setProvider(episodeContentMap.get("content_provider"));
			EpisodeContentRoot.setStartDateTime(episodeContentMap.get("provider_startDateTime"));
			EpisodeContentRoot.setEndDateTime(episodeContentMap.get("provider_endDateTime"));
			EpisodeContentRoot.setEntitlementId(episodeContentMap.get("provider_entitlementId"));

			purchaseOptions.setStartDateTime(episodeContentMap.get("purchaseOptions_startDateTime"));
			purchaseOptions.setEndDateTime(episodeContentMap.get("purchaseOptions_endDateTime"));
			purchaseOptions.setPolicyGroupId(Integer.parseInt(episodeContentMap.get("purchaseOptions_policyGroupId")));
			purchaseOptions.setPolicyId(Integer.parseInt(episodeContentMap.get("purchaseOptions_policyId")));
			purchaseOptions.setPolicyType(episodeContentMap.get("purchaseOptions_policyType"));
			offer.setId(episodeContentMap.get("offer_ID"));
			purchaseOptions.setOffer(offer);

			purchaseOptions.setOfferedEntityType(episodeContentMap.get("offeredEntityType"));
			purchaseOptions.setOfferedEntityId(episodeContentMap.get("offeredEntityId"));

			price.setUSD(Integer.parseInt(episodeContentMap.get("price_USD")));
			purchaseOptions.setPrice(price);
			purchaseOptions.setCountry(episodeContentMap.get("country"));
			purchaseOptions.setContractName(episodeContentMap.get("contractName"));
			contentTypes.add(episodeContentMap.get("contentTypes"));
			purchaseOptions.setContentTypes(contentTypes);

			videoContents.setContentType(episodeContentMap.get("videoContents_contentType"));
			videoContents.setDuration(episodeContentMap.get("videoContents_duration"));
			videoContents.setEntitlementContentId(episodeContentMap.get("videoContents_entitlementContentId"));

			contentUrlsMobile.setConsumerUrl(episodeContentMap.get("renditions_MOBILE_HD_consumerUrl"));

			contentRedention.setMOBILE_HD(contentUrlsMobile);

			contentUrlsPCSS.setConsumerUrl(episodeContentMap.get("renditions_PC_SS_HD_consumerUrl"));
			licenseAcquisitionUrl.setPlayready(episodeContentMap.get("PC_SS_HD_licenseAcquisitionUrl_playready"));
			licenseAcquisitionUrl.setWidevine(episodeContentMap.get("PC_SS_HD_licenseAcquisitionUrl_widevine"));
			lAcquisitionURL.add(licenseAcquisitionUrl);
			contentUrlsPCSS.setLicenseAcquisitionUrl(licenseAcquisitionUrl);
			contentRedention.setPC_DASH_HD(contentUrlsPCSS);

			contentUrlsPCDash.setConsumerUrl(episodeContentMap.get("renditions_PC_DASH_HD_consumerUrl"));
			licenseAcquisitionUrl.setPlayready(episodeContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_playready"));
			licenseAcquisitionUrl.setWidevine(episodeContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_widevine"));
			lAcquisitionURL.add(licenseAcquisitionUrl);
			contentUrlsPCDash.setLicenseAcquisitionUrl(licenseAcquisitionUrl);
			contentRedention.setPC_SS_HD(contentUrlsPCDash);

			videoContents.setRenditions(contentRedention);
			vContent.add(videoContents);
			purchaseOptions.setVideoContents(vContent);

			purchaseOpt.add(purchaseOptions);
			EpisodeContentRoot.setPurchaseOptions(purchaseOpt);

			contentTitleSortName.setEng(episodeContentMap.get("content_titleSortName_eng"));
			contentTitleSortName.setVi(episodeContentMap.get("content_titleSortName_vi"));
			EpisodeContentRoot.setTitleSortName(contentTitleSortName);

			contentTitleBrief.setEng(episodeContentMap.get("content_titleBrief_eng"));
			contentTitleBrief.setVi(episodeContentMap.get("content_titleBrief_vi"));
			EpisodeContentRoot.setTitleBrief(contentTitleBrief);

			contentTitleMedium.setEng(episodeContentMap.get("content_titleMedium_eng"));
			contentTitleMedium.setVi(episodeContentMap.get("content_titleMedium_vi"));
			EpisodeContentRoot.setTitleMedium(contentTitleMedium);

			contentTitleLong.setEng(episodeContentMap.get("content_titleLong_eng"));
			contentTitleLong.setVi(episodeContentMap.get("content_titleLong_vi"));
			EpisodeContentRoot.setTitleLong(contentTitleLong);

			contentSummaryShort.setEng(episodeContentMap.get("content_summaryShort_eng"));
			contentSummaryShort.setVi(episodeContentMap.get("content_summaryShort_vi"));
			EpisodeContentRoot.setSummaryShort(contentSummaryShort);

			contentSummaryMedium.setEng(episodeContentMap.get("content_summaryMedium_eng"));
			contentSummaryMedium.setVi(episodeContentMap.get("content_summaryMedium_vi"));
			EpisodeContentRoot.setSummaryMedium(contentSummaryMedium);

			contentSummaryLong.setEng(episodeContentMap.get("content_summaryLong_eng"));
			contentSummaryLong.setVi(episodeContentMap.get("content_summaryLong_vi"));
			EpisodeContentRoot.setSummaryLong(contentSummaryLong);

			EpisodeContentRoot.setShowType(episodeContentMap.get("content_showType"));
			EpisodeContentRoot.setKeywords(episodeContentMap.get("content_keywords"));

			genreRoot.setId(episodeContentMap.get("genre_id"));
			genreRoot.setGenrePath(episodeContentMap.get("genre_path"));
			gtitle.setVi(episodeContentMap.get("genre_titleVI"));
			gtitle.setEng(episodeContentMap.get("genre_titleENG"));
			genreRoot.setTitle(gtitle);
			gRoot.add(genreRoot);
			EpisodeContentRoot.setGenres(gRoot);

			/// Add Series data
			seriesRoot.setId(episodeContentMap.get("series_id"));
			seriesRoot.setProvider(episodeContentMap.get("series_Provder"));
			seriesRoot.setEntitlementId(episodeContentMap.get("series_entitlementId"));

			seriesTitleSortName.setVi(episodeContentMap.get("series_titleSortName_vi"));
			seriesTitleSortName.setEng(episodeContentMap.get("series_titleSortName_eng"));
			seriesRoot.setTitleSortName(seriesTitleSortName);
			seriesTitleBrief.setVi(episodeContentMap.get("series_titleBrief_vi"));
			seriesTitleBrief.setEng(episodeContentMap.get("series_titleBrief_eng"));
			seriesRoot.setTitleBrief(seriesTitleBrief);
			seriesTitleMedium.setVi(episodeContentMap.get("series_titleMedium_vi"));
			seriesTitleMedium.setEng(episodeContentMap.get("series_titleMedium_eng"));
			seriesRoot.setTitleMedium(seriesTitleMedium);
			seriesTitleLong.setVi(episodeContentMap.get("series_titleLong_vi"));
			seriesTitleLong.setEng(episodeContentMap.get("series_titleLong_eng"));
			seriesRoot.setTitleLong(seriesTitleLong);

			seriesSummaryShort.setVi(episodeContentMap.get("series_summaryShort_vi"));
			seriesSummaryShort.setEng(episodeContentMap.get("series_summaryShort_eng"));

			seriesRoot.setSummaryShort(seriesSummaryShort);
			seriesSummaryMedium.setVi(episodeContentMap.get("series_summaryMedium_vi"));
			seriesSummaryMedium.setEng(episodeContentMap.get("series_summaryMedium_eng"));
			seriesRoot.setSummaryMedium(seriesSummaryMedium);
			seriesSummaryLong.setVi(episodeContentMap.get("series_summaryLong_vi"));
			seriesSummaryLong.setEng(episodeContentMap.get("series_summaryLong_eng"));
			seriesRoot.setSummaryLong(seriesSummaryLong);
			seriesRoot.setShowType(episodeContentMap.get("series_showType"));
			seriesRoot.setKeywords(episodeContentMap.get("series_keywords"));
			/// Series Image
			imageContentForSeries.setContentType(episodeContentMap.get("series_imageContents_contentType"));
			consumerURL.setConsumerUrl(episodeContentMap.get("series_imageContents_renditions_consumerUrl"));
			renditions.setConsumerUrl(consumerURL);
			imageContentForSeries.setRenditions(renditions);
			imageContSR.add(imageContentsForBrand);
			seriesRoot.setImageContents(imageContSR);
			EpisodeContentRoot.setSeries(seriesRoot);

			/// Brand
			brandRoot.setId(episodeContentMap.get("brand_id"));
			brandRoot.setProvider(episodeContentMap.get("brand_Provder"));
			brandRoot.setEntitlementId(episodeContentMap.get("brand_entitlementId"));
			brandTitleSortName.setEng(episodeContentMap.get("brand_titleSortName_eng"));
			brandTitleSortName.setVi(episodeContentMap.get("brand_titleSortName_vi"));
			brandRoot.setTitleSortName(brandTitleSortName);
			brandTitleBrief.setEng(episodeContentMap.get("brand_titleBrief_eng"));
			brandTitleBrief.setVi(episodeContentMap.get("brand_titleBrief_vi"));
			brandRoot.setTitleBrief(brandTitleBrief);
			brandTitleMedium.setEng(episodeContentMap.get("brand_titleMedium_eng"));
			brandTitleMedium.setVi(episodeContentMap.get("brand_titleMedium_vi"));
			brandRoot.setTitleMedium(brandTitleMedium);
			brandTitleLong.setEng(episodeContentMap.get("brand_titleLong_eng"));
			brandTitleLong.setVi(episodeContentMap.get("brand_titleLong_vi"));
			brandRoot.setTitleLong(brandTitleLong);
			brandSummaryShort.setEng(episodeContentMap.get("brand_summaryShort_eng"));
			brandSummaryShort.setVi(episodeContentMap.get("brand_summaryShort_vi"));
			brandRoot.setSummaryShort(brandSummaryShort);
			brandSummaryMedium.setEng(episodeContentMap.get("brand_summaryMedium_eng"));
			brandSummaryMedium.setVi(episodeContentMap.get("brand_summaryMedium_vi"));
			brandRoot.setSummaryMedium(brandSummaryMedium);
			brandSummaryLong.setEng(episodeContentMap.get("brand_summaryLong_eng"));
			brandSummaryLong.setVi(episodeContentMap.get("brand_summaryLong_vi"));
			brandRoot.setSummaryLong(brandSummaryLong);

			brandRoot.setShowType(episodeContentMap.get("brand_showType"));
			brandRoot.setKeywords(episodeContentMap.get("brand_keywords"));

			imageContentsForBrand.setContentType(episodeContentMap.get("brand_imageContents_contentType"));
			consumerURL.setConsumerUrl(episodeContentMap.get("brand_imageContents_renditions_consumerUrl"));
			renditions.setConsumerUrl(consumerURL);
			imageContentsForBrand.setRenditions(renditions);
			imageContFB.add(imageContentsForBrand);
			brandRoot.setImageContents(imageContFB);
			EpisodeContentRoot.setBrand(brandRoot);

			EpisodeContentRoot
					.setContributors(Utils.putContributorValuesInJSon(episodeContentMap.get("cast_contrinutor"),
							episodeContentMap.get("cast_sortNameEng"), episodeContentMap.get("cast_sortNameVi"),
							episodeContentMap.get("cast_firstNameEng"), episodeContentMap.get("cast_firstNameVi"),
							episodeContentMap.get("cast_LasNameVi"), episodeContentMap.get("cast_lastNameEng"),
							episodeContentMap.get("cast_fullNameEng"), episodeContentMap.get("cast_fullNameVi")));

			subscriptionRoot.setId(episodeContentMap.get("subscriptionPackages_id"));
			subscriptionRoot.setProvider(episodeContentMap.get("subscriptionPackages_provider"));
			subscriptionRoot.setEntitlementId(episodeContentMap.get("subscriptionPackages_entitlementId"));
			subscriptionTitleSortName.setEng(episodeContentMap.get("subscriptionPackages_titleSortName_eng"));
			subscriptionTitleSortName.setVi(episodeContentMap.get("subscriptionPackages_titleSortName_vi"));
			subscriptionRoot.setTitleSortName(subscriptionTitleSortName);
			subscriptionTitleBrief.setEng(episodeContentMap.get("subscriptionPackages_titleBrief_eng"));
			subscriptionTitleBrief.setVi(episodeContentMap.get("subscriptionPackages_titleBrief_vi"));
			subscriptionRoot.setTitleBrief(subscriptionTitleBrief);
			subscriptionTitleMedium.setEng(episodeContentMap.get("subscriptionPackages_titleMedium_eng"));
			subscriptionTitleMedium.setVi(episodeContentMap.get("subscriptionPackages_titleMedium_vi"));
			subscriptionRoot.setTitleMedium(subscriptionTitleMedium);
			subscriptionTitleLong.setEng(episodeContentMap.get("subscriptionPackages_titleLong_eng"));
			subscriptionTitleLong.setVi(episodeContentMap.get("subscriptionPackages_titleLong_vi"));
			subscriptionRoot.setTitleLong(subscriptionTitleLong);
			subscriptionSummaryShort.setEng(episodeContentMap.get("subscriptionPackages_summaryShort_eng"));
			subscriptionSummaryShort.setVi(episodeContentMap.get("subscriptionPackages_summaryShort_vi"));
			subscriptionRoot.setSummaryShort(subscriptionSummaryShort);
			subscriptionSummaryMedium.setEng(episodeContentMap.get("subscriptionPackages_summaryMedium_eng"));
			subscriptionSummaryMedium.setVi(episodeContentMap.get("subscriptionPackages_summaryMedium_vi"));
			subscriptionRoot.setSummaryMedium(subscriptionSummaryMedium);
			subscriptionSummaryLong.setEng(episodeContentMap.get("subscriptionPackages_summaryLong_eng"));
			subscriptionSummaryLong.setVi(episodeContentMap.get("subscriptionPackages_summaryLong_vi"));
			subscriptionRoot.setSummaryLong(subscriptionSummaryLong);

			countryOfOrigin.add(episodeContentMap.get("subscriptionPackages_countryoforigin"));
			subscriptionRoot.setCountryOfOrigin(countryOfOrigin);

			subscriptionRoot.setShowType(episodeContentMap.get("subscriptionPackages_showType"));
			subscriptionRoot.setKeywords(episodeContentMap.get("subscriptionPackages_keywords"));
			subRoot.add(subscriptionRoot);
			EpisodeContentRoot.setSubscriptionPackages(subRoot);

			movieImageContents.setContentType(episodeContentMap.get("movieContent_contentType"));
			movieImageContents.setRenditions(renditions);
			consumerURL.setConsumerUrl(episodeContentMap.get("movieContent_renditions_consumerUrl"));
			renditions.setConsumerUrl(consumerURL);
			movieImageCont.add(movieImageContents);
			EpisodeContentRoot.setImageContents(movieImageCont);
			episodeName.setEng(episodeContentMap.get("episodeName_eng"));
			episodeName.setVi(episodeContentMap.get("episodeName_vi"));
			EpisodeContentRoot.setEpisodeName(episodeName);

			EpisodeContentRoot.setDisplayRunTime(episodeContentMap.get("displayRunTime"));
			EpisodeContentRoot.setYearOfRelease(Integer.parseInt(episodeContentMap.get("yearOfRelease")));

			return EpisodeContentRoot;

		} catch (NullPointerException e) {
			sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
		} catch (Exception e) {
			sb.append(e + "<<< POJO building got failed>>>");
		}
		return null;

	}

}
