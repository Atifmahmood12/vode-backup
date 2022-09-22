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

public class SeriesTest implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {
	CSVReader csvR = new CSVReader();

	public SeriesTest() {
		ProjectConfigs.projectConfig(null);
	}

	@Test
	public void SeriesTests() throws Exception {

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

			MessageIngester mIngester = new MessageIngester();
			TestMethodsUtil testMethodUtil = new TestMethodsUtil();
			Map<String, String> seriesMap = null;
			StringBuilder sb = new StringBuilder();
			Iterator<Map<String, String>> iterator = null;

			// ===============Read CSV data================================
			try {
				File genreCSVFile = new File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SERIES_CSV_FILE);
				iterator = csvR.csvReader(genreCSVFile);
			} catch (Exception e) {
				sb.append(e + " <<<File reading got failed>>>");
				throw e;
			}

			try {

				while (iterator.hasNext()) {
					// ===============Build JSon================================
					try {
						seriesRoot = new SeriesRoot();

						seriesMap = iterator.next();

						seriesRoot.setId(seriesMap.get("series_id"));
						seriesRoot.setProvider(seriesMap.get("series_Provder"));
						seriesRoot.setEntitlementId(seriesMap.get("series_entitlementId"));

						purchaseOptions.setStartDateTime(seriesMap.get("purchaseOptions_startDateTime"));
						purchaseOptions.setEndDateTime(seriesMap.get("purchaseOptions_endDateTime"));
						purchaseOptions
								.setPolicyGroupId(Integer.parseInt(seriesMap.get("purchaseOptions_policyGroupId")));
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
						imageContentForsub.setXResolution(
								Integer.parseInt(seriesMap.get("subscriptionPackages_imageContents_xResolution")));
						imageContentForsub.setYResolution(
								Integer.parseInt(seriesMap.get("subscriptionPackages_imageContents_yResolution")));
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
					} catch (NullPointerException e) {
						sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
					} catch (Exception e) {
						sb.append(e + "<<< POJO building got failed>>>");
					}

					// ===============IngestData================================
					try {
						ObjectMapper mapper1 = new ObjectMapper();
						mIngester.sendMessageToQueu(
								mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(seriesRoot).toString(),
								ProjectConfigs.getAMQ_CONN_STRING(), SERIES_ENTITY_TYPE, seriesMap.get("Action"),
								ProjectConfigs.getQUEUE_NAME());
					} catch (Exception e) {
						sb.append(e + "<<< Data ingestion got failed>>>");
					}

					// ===============TestCases================================

					try {
						switch (seriesMap.get(KEYWORD_ACTION)) {
						case KEYWORD_CREATE:
							if (seriesMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(seriesMap.get("Status"),
											seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
											seriesMap.get("Size"), seriesMap.get("isAvailable"),
											seriesMap.get("Language"), seriesMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											seriesMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson =
									 * verifyBoundaryValueCheckForSeries(seriesMap.get("Status"),
									 * seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
									 * seriesMap.get("Size"), seriesMap.get("isAvailable"),
									 * seriesMap.get("Language"), seriesMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * seriesMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertFalse(currentJson.contains(seriesMap.get("VerificationValue")),
											"TestCase ID :-" + seriesMap.get("TC-ID") + ", Scenario Name :-"
													+ seriesMap.get("Scenario") + ", Action:- "
													+ seriesMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed + Creat + B");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else if (seriesMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(seriesMap.get("Status"),
											seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
											seriesMap.get("Size"), seriesMap.get("isAvailable"),
											seriesMap.get("Language"), seriesMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											seriesMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyCreateSeries(seriesMap.get("Status"),
									 * seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
									 * seriesMap.get("Size"), seriesMap.get("isAvailable"),
									 * seriesMap.get("Language"), seriesMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * seriesMap.get(KEYWORD_SCENARIO));
									 */
									Assert.assertTrue(currentJson.contains(seriesMap.get("VerificationValue")),
											"TestCase ID :-" + seriesMap.get("TC-ID") + ", Scenario Name :-"
													+ seriesMap.get("Scenario") + ", Action:-"
													+ seriesMap.get(KEYWORD_ACTION) + " Reason: is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else {
								System.out.println(
										seriesMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}
							break;
						case KEYWORD_UPDATE:
							if (seriesMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(seriesMap.get("Status"),
											seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
											seriesMap.get("Size"), seriesMap.get("isAvailable"),
											seriesMap.get("Language"), seriesMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											seriesMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson =
									 * verifyBoundaryValueCheckForSeries(seriesMap.get("Status"),
									 * seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
									 * seriesMap.get("Size"), seriesMap.get("isAvailable"),
									 * seriesMap.get("Language"), seriesMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * seriesMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertFalse(currentJson.contains(seriesMap.get("VerificationValue")),
											"TestCase ID :-" + seriesMap.get("TC-ID") + ", Scenario Name :-"
													+ seriesMap.get("Scenario") + ", Action:- "
													+ seriesMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed udpate + B");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else if (seriesMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(seriesMap.get("Status"),
											seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
											seriesMap.get("Size"), seriesMap.get("isAvailable"),
											seriesMap.get("Language"), seriesMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											seriesMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyUpdateSeries(seriesMap.get("Status"),
									 * seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
									 * seriesMap.get("Size"), seriesMap.get("isAvailable"),
									 * seriesMap.get("Language"), seriesMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * seriesMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertTrue(currentJson.contains(seriesMap.get("VerificationValue")),
											"TestCase ID :-" + seriesMap.get("TC-ID") + ", Scenario Name :-"
													+ seriesMap.get("Scenario") + ", Action:- "
													+ seriesMap.get(KEYWORD_ACTION) + " Reason:- data is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed update F");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else {
								System.out.println(
										seriesMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}

							break;
						case KEYWORD_PURGE:
							try {
								String currentJson = testMethodUtil.getResponse(seriesMap.get("Status"),
										seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
										seriesMap.get("Size"), seriesMap.get("isAvailable"), seriesMap.get("Language"),
										seriesMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
										ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
										seriesMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);
								/*
								 * String currentJson = verifyPurgeSeries(seriesMap.get("Status"),
								 * seriesMap.get("Content_Type_ID"), seriesMap.get("Current_Page"),
								 * seriesMap.get("Size"), seriesMap.get("isAvailable"),
								 * seriesMap.get("Language"), seriesMap.get("Json_Path"),
								 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
								 * seriesMap.get(KEYWORD_SCENARIO));
								 */

								Assert.assertFalse(currentJson.contains(seriesMap.get("VerificationValue")),
										"TestCase ID :-" + seriesMap.get("TC-ID") + ", Scenario Name :-"
												+ seriesMap.get("Scenario") + ", Action:- "
												+ seriesMap.get(KEYWORD_ACTION)
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
								System.out.println(seriesMap.get(KEYWORD_ACTION) + "This case is not handled yet");

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
	 * public String verifyCreateSeries(String status, String contentTypeID, String
	 * currentPage, String size, String isAvailable, String language, String
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
	 * public String verifyUpdateSeries(String status, String contentTypeID, String
	 * currentPage, String size, String isAvailable, String language, String
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
	 * public String verifyPurgeSeries(String status, String contentTypeID, String
	 * currentPage, String size, String isAvailable, String language, String
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
	 * actualValue; } catch (AssertionError e) { throw e; } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 * 
	 * public String verifyBoundaryValueCheckForSeries(String status, String
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
	 * System.out.println(allContentwithTypeResponse.getBody().prettyPrint()); //
	 * JsonPath.from(allContentwithTypeResponse.prettyPrint()) // String temp =
	 * allContentwithTypeResponse.getBody().asString(); //
	 * System.out.println(JsonPath.from(allContentwithTypeResponse.getBody().
	 * asString()).toJsonString()); //
	 * 
	 * JsonPath.parse(allContentwithTypeResponse.body().jsonPath()).read(
	 * "$.my.deep.structure.persons.phoneNumbers", List.class);
	 * 
	 * if
	 * (JsonPath.from(allContentwithTypeResponse.getBody().asString()).getJsonObject
	 * ("type").equals("SINGLE") &&
	 * JsonPath.from(allContentwithTypeResponse.getBody().asString()).getJsonObject(
	 * "name").equals("Movie") ) { String temp =
	 * JsonPath.from(allContentwithTypeResponse.getBody().asString()).getJsonObject(
	 * "id"); } String contentID =
	 * JsonPath.from(allContentwithTypeResponse.getBody().asString()).getJsonObject(
	 * "type"); System.out.println(contentID);
	 * 
	 * 
	 * JsonPath jp = new JsonPath(allContentwithTypeResponse.getBody().asString());
	 * 
	 * Map<String, Object> location = jp.("type"); System.out.println(location);
	 * 
	 * return null; } catch (Exception e) { // TODO: handle exception } return null;
	 * }
	 */
}
