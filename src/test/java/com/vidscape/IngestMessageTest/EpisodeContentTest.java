package com.vidscape.IngestMessageTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.constants.DataSheetsConstants;
import com.vidscape.constants.MessageIngestorEndPoints;
import com.vidscape.dataproviders.CSVReader;
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
import com.vidscape.utils.MessageIngester;
import com.vidscape.utils.TestMethodsUtil;
import com.vidscape.utils.Utils;

public class EpisodeContentTest implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {
	CSVReader csvR = new CSVReader();

	public EpisodeContentTest() {

		ProjectConfigs.projectConfig(null);
	}

	@Test
	public void EpisodeContentTests() throws Exception {

		try {

			MovieContentRoot EpisodeContentRoot = new MovieContentRoot();

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
			MessageIngester mIngester = new MessageIngester();
			TestMethodsUtil testMethodUtil = new TestMethodsUtil();
			StringBuilder sb = new StringBuilder();
			Map<String, String> episodeContentMap = null;
			Iterator<Map<String, String>> iterator = null;

			// ===============Read CSV data================================
			try {
				File movieContentCSVFile = new File(
						ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + EPISODE_CONTENT_FILE);
				iterator = csvR.csvReader(movieContentCSVFile);
			} catch (Exception e) {
				sb.append(e + " <<<File reading got failed>>>");
				throw e;
			}

			// ===============Build JSon============================

			try {
				while (iterator.hasNext()) {
					try {
						EpisodeContentRoot = new MovieContentRoot();
						episodeContentMap = iterator.next();

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
						videoContents
								.setEntitlementContentId(episodeContentMap.get("videoContents_entitlementContentId"));

						contentUrlsMobile.setConsumerUrl(episodeContentMap.get("renditions_MOBILE_HD_consumerUrl"));

						contentRedention.setMOBILE_HD(contentUrlsMobile);

						contentUrlsPCSS.setConsumerUrl(episodeContentMap.get("renditions_PC_SS_HD_consumerUrl"));
						licenseAcquisitionUrl
								.setPlayready(episodeContentMap.get("PC_SS_HD_licenseAcquisitionUrl_playready"));
						licenseAcquisitionUrl
								.setWidevine(episodeContentMap.get("PC_SS_HD_licenseAcquisitionUrl_widevine"));
						lAcquisitionURL.add(licenseAcquisitionUrl);
						contentUrlsPCSS.setLicenseAcquisitionUrl(licenseAcquisitionUrl);
						contentRedention.setPC_DASH_HD(contentUrlsPCSS);

						contentUrlsPCDash.setConsumerUrl(episodeContentMap.get("renditions_PC_DASH_HD_consumerUrl"));
						licenseAcquisitionUrl
								.setPlayready(episodeContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_playready"));
						licenseAcquisitionUrl
								.setWidevine(episodeContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_widevine"));
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
						consumerURL
								.setConsumerUrl(episodeContentMap.get("series_imageContents_renditions_consumerUrl"));
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

						EpisodeContentRoot.setContributors(Utils.putContributorValuesInJSon(
								episodeContentMap.get("cast_contrinutor"), episodeContentMap.get("cast_sortNameEng"),
								episodeContentMap.get("cast_sortNameVi"), episodeContentMap.get("cast_firstNameEng"),
								episodeContentMap.get("cast_firstNameVi"), episodeContentMap.get("cast_LasNameVi"),
								episodeContentMap.get("cast_lastNameEng"), episodeContentMap.get("cast_fullNameEng"),
								episodeContentMap.get("cast_fullNameVi")));

						subscriptionRoot.setId(episodeContentMap.get("subscriptionPackages_id"));
						subscriptionRoot.setProvider(episodeContentMap.get("subscriptionPackages_provider"));
						subscriptionRoot.setEntitlementId(episodeContentMap.get("subscriptionPackages_entitlementId"));
						subscriptionTitleSortName
								.setEng(episodeContentMap.get("subscriptionPackages_titleSortName_eng"));
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
						subscriptionSummaryMedium
								.setEng(episodeContentMap.get("subscriptionPackages_summaryMedium_eng"));
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

					} catch (NullPointerException e) {
						sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
					} catch (Exception e) {
						sb.append(e + "<<< POJO building got failed>>>");
					}
					// ===============IngestData================================
					try {
						ObjectMapper mapper1 = new ObjectMapper();
						mIngester.sendMessageToQueu(
								mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(EpisodeContentRoot)
										.toString(),
								ProjectConfigs.getAMQ_CONN_STRING(), PROGRAM_ENTITY_TYPE,
								episodeContentMap.get("Action"), ProjectConfigs.getQUEUE_NAME());
					} catch (Exception e) {
						sb.append(e + "<<< Data ingestion got failed>>>");
					}
					// ===============TestCases================================

					try {
						switch (episodeContentMap.get(KEYWORD_ACTION)) {
						case KEYWORD_CREATE:
							if (episodeContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(episodeContentMap.get("Status"),
											episodeContentMap.get("Content_Type_ID"),
											episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
											episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
											episodeContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											episodeContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyBoundaryValueCheckForMovieContent(
									 * episodeContentMap.get("Status"), episodeContentMap.get("Content_Type_ID"),
									 * episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
									 * episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
									 * episodeContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * episodeContentMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertFalse(currentJson.contains(episodeContentMap.get("VerificationValue")),
											"TestCase ID :-" + episodeContentMap.get("TC-ID") + ", Scenario Name :-"
													+ episodeContentMap.get("Scenario") + ", Action:- "
													+ episodeContentMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed + Creat + B");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else if (episodeContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(episodeContentMap.get("Status"),
											episodeContentMap.get("Content_Type_ID"),
											episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
											episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
											episodeContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											episodeContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson =
									 * verifyCreateMovieContent(episodeContentMap.get("Status"),
									 * episodeContentMap.get("Content_Type_ID"),
									 * episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
									 * episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
									 * episodeContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * episodeContentMap.get(KEYWORD_SCENARIO));
									 */
									Assert.assertTrue(currentJson.contains(episodeContentMap.get("VerificationValue")),
											"TestCase ID :-" + episodeContentMap.get("TC-ID") + ", Scenario Name :-"
													+ episodeContentMap.get("Scenario") + ", Action:-"
													+ episodeContentMap.get(KEYWORD_ACTION)
													+ " Reason: is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else {
								System.out.println(episodeContentMap.get(KEYWORD_SCENARIO)
										+ "is still not handled by this script");
							}
							break;
						case KEYWORD_UPDATE:
							if (episodeContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(episodeContentMap.get("Status"),
											episodeContentMap.get("Content_Type_ID"),
											episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
											episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
											episodeContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											episodeContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyBoundaryValueCheckForMovieContent(
									 * episodeContentMap.get("Status"), episodeContentMap.get("Content_Type_ID"),
									 * episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
									 * episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
									 * episodeContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * episodeContentMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertFalse(currentJson.contains(episodeContentMap.get("VerificationValue")),
											"TestCase ID :-" + episodeContentMap.get("TC-ID") + ", Scenario Name :-"
													+ episodeContentMap.get("Scenario") + ", Action:- "
													+ episodeContentMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed udpate + B");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else if (episodeContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(episodeContentMap.get("Status"),
											episodeContentMap.get("Content_Type_ID"),
											episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
											episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
											episodeContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											episodeContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson =
									 * verifyUpdateMovieContent(episodeContentMap.get("Status"),
									 * episodeContentMap.get("Content_Type_ID"),
									 * episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
									 * episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
									 * episodeContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * episodeContentMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertTrue(currentJson.contains(episodeContentMap.get("VerificationValue")),
											"TestCase ID :-" + episodeContentMap.get("TC-ID") + ", Scenario Name :-"
													+ episodeContentMap.get("Scenario") + ", Action:- "
													+ episodeContentMap.get(KEYWORD_ACTION)
													+ " Reason:- data is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed update F");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else {
								System.out.println(episodeContentMap.get(KEYWORD_SCENARIO)
										+ "is still not handled by this script");
							}

							break;
						case KEYWORD_PURGE:
							try {

								String currentJson = testMethodUtil.getResponse(episodeContentMap.get("Status"),
										episodeContentMap.get("Content_Type_ID"), episodeContentMap.get("Current_Page"),
										episodeContentMap.get("Size"), episodeContentMap.get("isAvailable"),
										episodeContentMap.get("Language"), episodeContentMap.get("Json_Path"),
										ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
										ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
										episodeContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

								/*
								 * String currentJson = verifyPurgeMovieContent(episodeContentMap.get("Status"),
								 * episodeContentMap.get("Content_Type_ID"),
								 * episodeContentMap.get("Current_Page"), episodeContentMap.get("Size"),
								 * episodeContentMap.get("isAvailable"), episodeContentMap.get("Language"),
								 * episodeContentMap.get("Json_Path"),
								 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
								 * episodeContentMap.get(KEYWORD_SCENARIO));
								 */
								Assert.assertFalse(currentJson.contains(episodeContentMap.get("VerificationValue")),
										"TestCase ID :-" + episodeContentMap.get("TC-ID") + ", Scenario Name :-"
												+ episodeContentMap.get("Scenario") + ", Action:- "
												+ episodeContentMap.get(KEYWORD_ACTION)
												+ " Reason:- Data is not purged successfully");
							} catch (AssertionError e) {
								System.out.println("TestcaseGot failed Purge");
								sb.append(e.getMessage() + "\n\t\n");
							} catch (NullPointerException e) {
								System.out.println("TestcaseGot failed + Creat + B");
								sb.append(e.getMessage() + "\n\t\n");

							}
							break;
						default:
							try {
								System.out.println(
										episodeContentMap.get(KEYWORD_ACTION) + "This case is not handled yet");

							} catch (Exception e) {
								sb.append(e.getMessage() + "\n\t\n");
							}
						}

					} catch (Exception e) {
						sb.append(e.getMessage() + "\n\t\n");
					}

				}
				Assert.assertTrue(sb.toString().isEmpty(), sb.toString());

			} catch (AssertionError e) {
				System.out.println(sb.toString());
				Assert.fail(sb.toString());
			}

		} catch (Exception e) {
			throw e;
		}

	}

	// ======================================

	/*
	 * public String getAuthToken(String userName, String passWord) { try { Response
	 * resAuth = given().header(AUTH_API_USERNAME,
	 * userName).given().header(AUTH_API_PASSWORD, passWord)
	 * .when().post(ProjectConfigs.getServer_APP_URI() + SERVER_API_AUTH_URL);
	 * return resAuth.then().extract().jsonPath().getString("token"); } catch
	 * (Exception e) { e.getMessage(); } return null; }
	 * 
	 * public String verifyCreateMovieContent(String status, String contentTypeID,
	 * String currentPage, String size, String isAvailable, String language, String
	 * jsonPath, String userName, String passWord, String scenarioName) { try {
	 * Response brandResponse = given().header(AUTH_TOKEN_HEADER_FIELD,
	 * getAuthToken(userName, passWord)).when()
	 * .get(ProjectConfigs.getServer_APP_URI() + ALL_CONTENT_URI +
	 * ALL_CONTENT_URI_QUERYSTRING_STATUS + status +
	 * ALL_CONTENT_URI_QUERYSTRING_CONTENT_TYPE + contentTypeID +
	 * ALL_CONTENT_URI_QUERYSTRING_CURRENT + currentPage +
	 * ALL_CONTENT_URI_QUERYSTRING_SIZE + size +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_AVAILABLE + isAvailable +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_LANGUAGE + language); String actualValue =
	 * JsonPath.from(brandResponse.getBody().asString()).getString(jsonPath);
	 * System.out.println(brandResponse.getBody().asString().toString()); return
	 * actualValue; } catch (Exception e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 * 
	 * public String verifyUpdateMovieContent(String status, String contentTypeID,
	 * String currentPage, String size, String isAvailable, String language, String
	 * jsonPath, String userName, String passWord, String scenarioName) { try {
	 * Response brandResponse = given().header(AUTH_TOKEN_HEADER_FIELD,
	 * getAuthToken(userName, passWord)).when()
	 * .get(ProjectConfigs.getServer_APP_URI() + ALL_CONTENT_URI +
	 * ALL_CONTENT_URI_QUERYSTRING_STATUS + status +
	 * ALL_CONTENT_URI_QUERYSTRING_CONTENT_TYPE + contentTypeID +
	 * ALL_CONTENT_URI_QUERYSTRING_CURRENT + currentPage +
	 * ALL_CONTENT_URI_QUERYSTRING_SIZE + size +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_AVAILABLE + isAvailable +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_LANGUAGE + language); String actualValue =
	 * JsonPath.from(brandResponse.getBody().asString()).getString(jsonPath); return
	 * actualValue; } catch (AssertionError e) {
	 * 
	 * throw e; } catch (Exception e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 * 
	 * public String verifyPurgeMovieContent(String status, String contentTypeID,
	 * String currentPage, String size, String isAvailable, String language, String
	 * jsonPath, String userName, String passWord, String scenarioName) { try {
	 * Response brandResponse = given().header(AUTH_TOKEN_HEADER_FIELD,
	 * getAuthToken(userName, passWord)).when()
	 * .get(ProjectConfigs.getServer_APP_URI() + ALL_CONTENT_URI +
	 * ALL_CONTENT_URI_QUERYSTRING_STATUS + status +
	 * ALL_CONTENT_URI_QUERYSTRING_CONTENT_TYPE + contentTypeID +
	 * ALL_CONTENT_URI_QUERYSTRING_CURRENT + currentPage +
	 * ALL_CONTENT_URI_QUERYSTRING_SIZE + size +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_AVAILABLE + isAvailable +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_LANGUAGE + language); String actualValue =
	 * JsonPath.from(brandResponse.getBody().asString()).getString(jsonPath); return
	 * actualValue; } catch (AssertionError e) {
	 * 
	 * throw e; } catch (Exception e) { e.printStackTrace(); } return null; }
	 * 
	 * public String verifyBoundaryValueCheckForMovieContent(String status, String
	 * contentTypeID, String currentPage, String size, String isAvailable, String
	 * language, String jsonPath, String userName, String passWord, String
	 * scenarioName) { try { Response brandResponse =
	 * given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName,
	 * passWord)).when() .get(ProjectConfigs.getServer_APP_URI() + ALL_CONTENT_URI +
	 * ALL_CONTENT_URI_QUERYSTRING_STATUS + status +
	 * ALL_CONTENT_URI_QUERYSTRING_CONTENT_TYPE + contentTypeID +
	 * ALL_CONTENT_URI_QUERYSTRING_CURRENT + currentPage +
	 * ALL_CONTENT_URI_QUERYSTRING_SIZE + size +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_AVAILABLE + isAvailable +
	 * ALL_CONTENT_URI_QUERYSTRING_IS_LANGUAGE + language); String actualValue =
	 * JsonPath.from(brandResponse.getBody().asString()).getString(jsonPath); return
	 * actualValue; } catch (Exception e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 * 
	 * public String getContentID(String userName, String passWord) {
	 * 
	 * try { Response allContentwithTypeResponse = given()
	 * .header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName, passWord)).when()
	 * .get(ProjectConfigs.getServer_APP_URI() + CONTENT_TYPE_URI);
	 * System.out.println(allContentwithTypeResponse.getBody().prettyPrint());
	 * 
	 * JsonPath jp = new JsonPath(allContentwithTypeResponse.getBody().asString());
	 * 
	 * Map<String, Object> location = jp.("type"); System.out.println(location);
	 * 
	 * return null; } catch (Exception e) { // TODO: handle exception } return null;
	 * }
	 */
}
