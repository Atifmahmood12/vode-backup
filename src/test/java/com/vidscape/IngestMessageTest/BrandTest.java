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
import com.vidscape.pojo.subscription.ConsrUrl;
import com.vidscape.pojo.subscription.ImageContents;
import com.vidscape.pojo.subscription.Offer;
import com.vidscape.pojo.subscription.Price;
import com.vidscape.pojo.subscription.PurchaseOptions;
import com.vidscape.pojo.subscription.Renditions;
import com.vidscape.pojo.subscription.SubscriptionRoot;
import com.vidscape.utils.MessageIngester;
import com.vidscape.utils.TestMethodsUtil;

public class BrandTest implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {

	CSVReader csvR = new CSVReader();

	public BrandTest() {
		ProjectConfigs.projectConfig(null);
	}

	@Test
	public void BrandTests() throws Exception {

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

			MessageIngester mIngester = new MessageIngester();
			TestMethodsUtil testMethodUtil = new TestMethodsUtil();
			StringBuilder sb = new StringBuilder();
			Map<String, String> brandMap = null;
			Iterator<Map<String, String>> iterator = null;

			// ===============Read CSV data================================
			try {
				File brandCSVFile = new File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + BRAND_CSV_FILE);
				iterator = csvR.csvReader(brandCSVFile);
			} catch (Exception e) {
				sb.append(e + " <<<File reading got failed>>>");
				throw e;
			}
			try {
				while (iterator.hasNext()) {
					// ===============Build JSon================================
					try {
						brandRoot = new BrandRoot();
						brandMap = iterator.next();
						brandRoot.setId(brandMap.get("brand_id"));
						brandRoot.setProvider(brandMap.get("brnad_Provder"));
						brandRoot.setEntitlementId(brandMap.get("brand_entitlementId"));

						purchaseOptions.setStartDateTime(brandMap.get("purchaseOptions_startDateTime"));
						purchaseOptions.setEndDateTime(brandMap.get("purchaseOptions_endDateTime"));
						purchaseOptions
								.setPolicyGroupId(Integer.parseInt(brandMap.get("purchaseOptions_policyGroupId")));
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

					} catch (NullPointerException e) {
						sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
					} catch (Exception e) {
						sb.append(e + "<<< POJO building got failed>>>");
					}

					// ===============IngestData================================
					try {
						ObjectMapper mapper1 = new ObjectMapper();
						mIngester.sendMessageToQueu(
								mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(brandRoot).toString(),
								ProjectConfigs.getAMQ_CONN_STRING(), BRAND_ENTITY_TYPE, brandMap.get(KEYWORD_ACTION),
								ProjectConfigs.getQUEUE_NAME());
					} catch (Exception e) {
						sb.append(e + "<<< Data ingestion got failed>>>");
					}

					// ===============TestCases================================
					try {
						switch (brandMap.get(KEYWORD_ACTION)) {
						case KEYWORD_CREATE:
							if (brandMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(brandMap.get("Status"),
											brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
											brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
											brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											brandMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson = verifyBoundaryValueCheckForBrand(brandMap.get("Status"),
									 * brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
									 * brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
									 * brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * brandMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertFalse(currentJson.contains(brandMap.get("VerificationValue")),
											"TestCase ID :-" + brandMap.get("TC-ID") + ", Scenario Name :-"
													+ brandMap.get("Scenario") + ", Action:- "
													+ brandMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed + Creat + B");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else if (brandMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(brandMap.get("Status"),
											brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
											brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
											brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											brandMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson1 = verifyCreateBrand(brandMap.get("Status"),
									 * brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
									 * brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
									 * brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * brandMap.get(KEYWORD_SCENARIO));
									 */
									Assert.assertTrue(currentJson.contains(brandMap.get("VerificationValue")),
											"TestCase ID :-" + brandMap.get("TC-ID") + ", Scenario Name :-"
													+ brandMap.get("Scenario") + ", Action:-"
													+ brandMap.get(KEYWORD_ACTION) + " Reason: is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else {
								System.out.println(
										brandMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}
							break;
						case KEYWORD_UPDATE:
							if (brandMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(brandMap.get("Status"),
											brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
											brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
											brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											brandMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson1 =
									 * verifyBoundaryValueCheckForBrand(brandMap.get("Status"),
									 * brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
									 * brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
									 * brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * brandMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertFalse(currentJson.contains(brandMap.get("VerificationValue")),
											"TestCase ID :-" + brandMap.get("TC-ID") + ", Scenario Name :-"
													+ brandMap.get("Scenario") + ", Action:- "
													+ brandMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed udpate + B");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else if (brandMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {

									String currentJson = testMethodUtil.getResponse(brandMap.get("Status"),
											brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
											brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
											brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											brandMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

									/*
									 * String currentJson1= verifyUpdateBrand(brandMap.get("Status"),
									 * brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
									 * brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
									 * brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
									 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
									 * brandMap.get(KEYWORD_SCENARIO));
									 */

									Assert.assertTrue(currentJson.contains(brandMap.get("VerificationValue")),
											"TestCase ID :-" + brandMap.get("TC-ID") + ", Scenario Name :-"
													+ brandMap.get("Scenario") + ", Action:- "
													+ brandMap.get(KEYWORD_ACTION) + " Reason:- data is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed update F");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else {
								System.out.println(
										brandMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}

							break;
						case KEYWORD_PURGE:
							try {

								String currentJson = testMethodUtil.getResponse(brandMap.get("Status"),
										brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
										brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
										brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
										ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
										brandMap.get(KEYWORD_SCENARIO), ALL_CONTENT_URI);

								/*
								 * String currentJson1 = verifyPurgeBrand(brandMap.get("Status"),
								 * brandMap.get("Content_Type_ID"), brandMap.get("Current_Page"),
								 * brandMap.get("Size"), brandMap.get("isAvailable"), brandMap.get("Language"),
								 * brandMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								 * ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
								 * brandMap.get(KEYWORD_SCENARIO));
								 */

								Assert.assertFalse(currentJson.contains(brandMap.get("VerificationValue")),
										"TestCase ID :-" + brandMap.get("TC-ID") + ", Scenario Name :-"
												+ brandMap.get("Scenario") + ", Action:- "
												+ brandMap.get(KEYWORD_ACTION)
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
								System.out.println(brandMap.get(KEYWORD_ACTION) + "This case is not handled yet");

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
	 * public String verifyCreateBrand(String status, String contentTypeID, String
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
	 * public String verifyUpdateBrand(String status, String contentTypeID, String
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
	 * public String verifyPurgeBrand(String status, String contentTypeID, String
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
	 * throw e; } catch (Exception e) { e.printStackTrace(); } return null; }
	 * 
	 * public String verifyBoundaryValueCheckForBrand(String status, String
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
