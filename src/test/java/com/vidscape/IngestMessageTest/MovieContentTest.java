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
import com.vidscape.pojo.common.Translation;
import com.vidscape.pojo.genre.GenreRoot;
import com.vidscape.pojo.moviecontent.ContentRedention;
import com.vidscape.pojo.moviecontent.ContentURLs;
import com.vidscape.pojo.moviecontent.LicenseAcquisitionUrl;
import com.vidscape.pojo.moviecontent.MovieContentRoot;
import com.vidscape.pojo.moviecontent.VideoContents;
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

public class MovieContentTest implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {
	CSVReader csvR = new CSVReader();

	public MovieContentTest() {

		ProjectConfigs.projectConfig(null);
	}

	@Test
	public void MovieContentTests() throws Exception {

		try {

			MovieContentRoot movieContentRoot = new MovieContentRoot();

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
			MessageIngester mIngester = new MessageIngester();
			TestMethodsUtil testMethodUtil = new TestMethodsUtil();
			StringBuilder sb = new StringBuilder();
			Map<String, String> movieContentMap = null;
			Iterator<Map<String, String>> iterator = null;

			// ===============Read CSV data================================
			try {
				File movieContentCSVFile = new File(
						ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + MOVIE_CONTENT_FILE);
				iterator = csvR.csvReader(movieContentCSVFile);
			} catch (Exception e) {
				sb.append(e + " <<<File reading got failed>>>");
				throw e;
			}

			// ===============Build JSon============================

			try {
				while (iterator.hasNext()) {
					try {
						movieContentRoot = new MovieContentRoot();
						movieContentMap = iterator.next();

						movieContentRoot.setId(movieContentMap.get("content_id"));
						movieContentRoot.setProvider(movieContentMap.get("content_provider"));
						movieContentRoot.setStartDateTime(movieContentMap.get("provider_startDateTime"));
						movieContentRoot.setEndDateTime(movieContentMap.get("provider_endDateTime"));
						movieContentRoot.setEntitlementId(movieContentMap.get("provider_entitlementId"));

						purchaseOptions.setStartDateTime(movieContentMap.get("purchaseOptions_startDateTime"));
						purchaseOptions.setEndDateTime(movieContentMap.get("purchaseOptions_endDateTime"));
						purchaseOptions.setPolicyGroupId(
								Integer.parseInt(movieContentMap.get("purchaseOptions_policyGroupId")));
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
						videoContents
								.setEntitlementContentId(movieContentMap.get("videoContents_entitlementContentId"));

						contentUrlsMobile.setConsumerUrl(movieContentMap.get("renditions_MOBILE_HD_consumerUrl"));

						contentRedention.setMOBILE_HD(contentUrlsMobile);

						contentUrlsPCSS.setConsumerUrl(movieContentMap.get("renditions_PC_SS_HD_consumerUrl"));
						licenseAcquisitionUrl
								.setPlayready(movieContentMap.get("PC_SS_HD_licenseAcquisitionUrl_playready"));
						licenseAcquisitionUrl
								.setWidevine(movieContentMap.get("PC_SS_HD_licenseAcquisitionUrl_widevine"));
						lAcquisitionURL.add(licenseAcquisitionUrl);
						contentUrlsPCSS.setLicenseAcquisitionUrl(licenseAcquisitionUrl);
						contentRedention.setPC_DASH_HD(contentUrlsPCSS);

						contentUrlsPCDash.setConsumerUrl(movieContentMap.get("renditions_PC_DASH_HD_consumerUrl"));
						licenseAcquisitionUrl
								.setPlayready(movieContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_playready"));
						licenseAcquisitionUrl
								.setWidevine(movieContentMap.get("PC_DASH_HD_licenseAcquisitionUrl_widevine"));
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

						movieContentRoot.setContributors(Utils.putContributorValuesInJSon(
								movieContentMap.get("cast_contrinutor"), movieContentMap.get("cast_sortNameEng"),
								movieContentMap.get("cast_sortNameVi"), movieContentMap.get("cast_firstNameEng"),
								movieContentMap.get("cast_firstNameVi"), movieContentMap.get("cast_LasNameVi"),
								movieContentMap.get("cast_lastNameEng"), movieContentMap.get("cast_fullNameEng"),
								movieContentMap.get("cast_fullNameVi")));

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

					} catch (NullPointerException e) {
						sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
					} catch (Exception e) {
						sb.append(e + "<<< POJO building got failed>>>");
					}
					// ===============IngestData================================
					try {
						ObjectMapper mapper1 = new ObjectMapper();
						mIngester.sendMessageToQueu(
								mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(movieContentRoot)
										.toString(),
								ProjectConfigs.getAMQ_CONN_STRING(), PROGRAM_ENTITY_TYPE, movieContentMap.get("Action"),
								ProjectConfigs.getQUEUE_NAME());
					} catch (Exception e) {
						sb.append(e + "<<< Data ingestion got failed>>>");
					}
					// ===============TestCases================================

					try {
						switch (movieContentMap.get(KEYWORD_ACTION)) {
						case KEYWORD_CREATE:
							if (movieContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(movieContentMap.get("Status"),
											movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
											movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
											movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											movieContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyBoundaryValueCheckForMovieContent(
									 * movieContentMap.get("Status"), movieContentMap.get("Content_Type_ID"),
									 * movieContentMap.get("Current_Page"), movieContentMap.get("Size"),
									 * movieContentMap.get("isAvailable"), movieContentMap.get("Language"),
									 * movieContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * movieContentMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertFalse(currentJson.contains(movieContentMap.get("VerificationValue")),
											"TestCase ID :-" + movieContentMap.get("TC-ID") + ", Scenario Name :-"
													+ movieContentMap.get("Scenario") + ", Action:- "
													+ movieContentMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed + Creat + B");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else if (movieContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(movieContentMap.get("Status"),
											movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
											movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
											movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											movieContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyCreateMovieContent(movieContentMap.get("Status"),
									 * movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
									 * movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
									 * movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * movieContentMap.get(KEYWORD_SCENARIO));
									 */
									Assert.assertTrue(currentJson.contains(movieContentMap.get("VerificationValue")),
											"TestCase ID :-" + movieContentMap.get("TC-ID") + ", Scenario Name :-"
													+ movieContentMap.get("Scenario") + ", Action:-"
													+ movieContentMap.get(KEYWORD_ACTION) + " Reason: is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else {
								System.out.println(
										movieContentMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}
							break;
						case KEYWORD_UPDATE:
							if (movieContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(movieContentMap.get("Status"),
											movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
											movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
											movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											movieContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyBoundaryValueCheckForMovieContent(
									 * movieContentMap.get("Status"), movieContentMap.get("Content_Type_ID"),
									 * movieContentMap.get("Current_Page"), movieContentMap.get("Size"),
									 * movieContentMap.get("isAvailable"), movieContentMap.get("Language"),
									 * movieContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * movieContentMap.get(KEYWORD_SCENARIO));
									 */
									Assert.assertFalse(currentJson.contains(movieContentMap.get("VerificationValue")),
											"TestCase ID :-" + movieContentMap.get("TC-ID") + ", Scenario Name :-"
													+ movieContentMap.get("Scenario") + ", Action:- "
													+ movieContentMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed udpate + B");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else if (movieContentMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(movieContentMap.get("Status"),
											movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
											movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
											movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
											ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											movieContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyUpdateMovieContent(movieContentMap.get("Status"),
									 * movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
									 * movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
									 * movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * movieContentMap.get(KEYWORD_SCENARIO));
									 */
									Assert.assertTrue(currentJson.contains(movieContentMap.get("VerificationValue")),
											"TestCase ID :-" + movieContentMap.get("TC-ID") + ", Scenario Name :-"
													+ movieContentMap.get("Scenario") + ", Action:- "
													+ movieContentMap.get(KEYWORD_ACTION)
													+ " Reason:- data is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed update F");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else {
								System.out.println(
										movieContentMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}

							break;
						case KEYWORD_PURGE:
							try {

								String currentJson = testMethodUtil.getResponse(movieContentMap.get("Status"),
										movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
										movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
										movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
										ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
										ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
										movieContentMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

								/*
								 * String currentJson = verifyPurgeMovieContent(movieContentMap.get("Status"),
								 * movieContentMap.get("Content_Type_ID"), movieContentMap.get("Current_Page"),
								 * movieContentMap.get("Size"), movieContentMap.get("isAvailable"),
								 * movieContentMap.get("Language"), movieContentMap.get("Json_Path"),
								 * ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
								 * movieContentMap.get(KEYWORD_SCENARIO));
								 */
								Assert.assertFalse(currentJson.contains(movieContentMap.get("VerificationValue")),
										"TestCase ID :-" + movieContentMap.get("TC-ID") + ", Scenario Name :-"
												+ movieContentMap.get("Scenario") + ", Action:- "
												+ movieContentMap.get(KEYWORD_ACTION)
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
								System.out
										.println(movieContentMap.get(KEYWORD_ACTION) + "This case is not handled yet");

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
