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
import com.vidscape.pojo.subscription.ConsrUrl;
import com.vidscape.pojo.subscription.ImageContents;
import com.vidscape.pojo.subscription.Offer;
import com.vidscape.pojo.subscription.Price;
import com.vidscape.pojo.subscription.PurchaseOptions;
import com.vidscape.pojo.subscription.Renditions;
import com.vidscape.pojo.subscription.SubscriptionRoot;
import com.vidscape.utils.MessageIngester;
import com.vidscape.utils.TestMethodsUtil;

public class SubscriptionTest implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {

	CSVReader csvR = new CSVReader();

	public SubscriptionTest() {
		ProjectConfigs.projectConfig(null);
	}

	@Test
	public void SubscriptionsTests() throws Exception {

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
			MessageIngester mIngester = new MessageIngester();
			TestMethodsUtil testMethodUtil = new TestMethodsUtil();
			StringBuilder sb = new StringBuilder();
			Map<String, String> subMap = null;
			Iterator<Map<String, String>> iterator = null;
			try {
				File subCSVFile = new File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SUBSCRIBER_CSV_FILE);
				iterator = csvR.csvReader(subCSVFile);
			} catch (Exception e) {
				sb.append(e + " <<<File reading got failed>>>");
				throw e;
			}
			try {
				while (iterator.hasNext()) {

					// ===============Build JSon================================
					try {

						subRoot = new SubscriptionRoot();
						subMap = iterator.next();
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

					} catch (NullPointerException e) {
						sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
					} catch (Exception e) {
						sb.append(e.getCause() + "<<< POJO building got failed>>>");
					}
					// ===============IngestData================================
					try {
						ObjectMapper mapper1 = new ObjectMapper();
						mIngester.sendMessageToQueu(
								mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(subRoot).toString(),
								ProjectConfigs.getAMQ_CONN_STRING(), SUBSCRIPTION_ENTITY_TYPE, subMap.get("Action"),
								ProjectConfigs.getQUEUE_NAME());
					} catch (Exception e) {
						sb.append(e + "<<< Data ingestion got failed>>>");
					}
					// ===============TestCases================================
					try {
						switch (subMap.get(KEYWORD_ACTION)) {
						case KEYWORD_CREATE:
							if (subMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(subMap.get("Language"),
											subMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											subMap.get(KEYWORD_SCENARIO), PRICING_MODEL_URI);

									Assert.assertFalse(currentJson.contains(subMap.get("VerificationValue")),
											"TestCase ID :-" + subMap.get("TC-ID") + ", Scenario Name :-"
													+ subMap.get("Scenario") + ", Action:- "
													+ subMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed + Creat + B");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else if (subMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(subMap.get("Language"),
											subMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											subMap.get(KEYWORD_SCENARIO), PRICING_MODEL_URI);
									Assert.assertTrue(currentJson.contains(subMap.get("VerificationValue")),
											"TestCase ID :-" + subMap.get("TC-ID") + ", Scenario Name :-"
													+ subMap.get("Scenario") + ", Action:-" + subMap.get(KEYWORD_ACTION)
													+ " Reason: is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else {
								System.out
										.println(subMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}
							break;
						case KEYWORD_UPDATE:
							if (subMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(subMap.get("Language"),
											subMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											subMap.get(KEYWORD_SCENARIO), PRICING_MODEL_URI);

									Assert.assertFalse(currentJson.contains(subMap.get("VerificationValue")),
											"TestCase ID :-" + subMap.get("TC-ID") + ", Scenario Name :-"
													+ subMap.get("Scenario") + ", Action:- "
													+ subMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed udpate + B");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else if (subMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(subMap.get("Language"),
											subMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											subMap.get(KEYWORD_SCENARIO), PRICING_MODEL_URI);

									Assert.assertTrue(currentJson.contains(subMap.get("VerificationValue")),
											"TestCase ID :-" + subMap.get("TC-ID") + ", Scenario Name :-"
													+ subMap.get("Scenario") + ", Action:- "
													+ subMap.get(KEYWORD_ACTION) + " Reason:- data is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed update F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else {
								System.out
										.println(subMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}

							break;
						case KEYWORD_PURGE:
							try {
								String currentJson = testMethodUtil.getResponse(subMap.get("Language"),
										subMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
										ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), subMap.get(KEYWORD_SCENARIO),
										PRICING_MODEL_URI);

								Assert.assertFalse(currentJson.contains(subMap.get("VerificationValue")),
										"TestCase ID :-" + subMap.get("TC-ID") + ", Scenario Name :-"
												+ subMap.get("Scenario") + ", Action:- " + subMap.get(KEYWORD_ACTION)
												+ " Reason:- Data is not purged successfully");
							} catch (NullPointerException e) {
								System.out.println("TestcaseGot failed Create + Purge");
								sb.append(e.getMessage() + "\n\t\n");
							} catch (Exception e) {
								System.out.println("TestcaseGot failed Purge");
								sb.append(e.getMessage() + "\n\t\n");
							}
							break;
						default:
							try {
								System.out.println(subMap.get(KEYWORD_ACTION) + "This case is not handled yet");

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

	/*
	 * public String getAuthToken(String userName, String passWord) { try { Response
	 * resAuth = given().header(AUTH_API_USERNAME,
	 * userName).given().header(AUTH_API_PASSWORD, passWord)
	 * .when().post(ProjectConfigs.getServer_APP_URI() + SERVER_API_AUTH_URL);
	 * return resAuth.then().extract().jsonPath().getString("token"); } catch
	 * (Exception e) { e.getMessage(); } return null; }
	 * 
	 * public String verifyCreateSubscription(String language,String jsonPath,String
	 * userName, String passWord, String scenarioName) { try { Response subResponse
	 * = given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName,
	 * passWord)).when() .get(ProjectConfigs.getServer_APP_URI() + PRICING_MODEL_URI
	 * + language); String actualValue =
	 * JsonPath.from(subResponse.getBody().asString()).getString("uuid");
	 * System.out.println(subResponse.getBody().asString().toString()); return
	 * actualValue; } catch (Exception e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 * 
	 * public String verifyUpdateSubscription(String language,String jsonPath,String
	 * userName, String passWord, String scenarioName) { try { Response subResponse
	 * = given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName,
	 * passWord)).when() .get(ProjectConfigs.getServer_APP_URI() + PRICING_MODEL_URI
	 * + language); String actualValue =
	 * JsonPath.from(subResponse.getBody().asString()).getString("uuid"); return
	 * actualValue; // Assert.assertTrue(actualValue.contains(verificationValue), //
	 * scenarioName + ": Data" + genreID + " , is not ingested successfully"); }
	 * catch (AssertionError e) {
	 * 
	 * throw e; } catch (Exception e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 * 
	 * public String verifyPurgeSubscription(String language,String jsonPath,String
	 * userName, String passWord, String scenarioName) { try { Response subResponse
	 * = given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName,
	 * passWord)).when() .get(ProjectConfigs.getServer_APP_URI() + PRICING_MODEL_URI
	 * + language); String actualValue =
	 * JsonPath.from(subResponse.getBody().asString()).getString("uuid"); return
	 * actualValue; } catch (AssertionError e) {
	 * 
	 * throw e; } catch (Exception e) { e.printStackTrace(); } return null; }
	 * 
	 * public String verifyBoundaryValueCheckForSubscription(String language,String
	 * jsonPath, String userName, String passWord, String scenarioName) { try {
	 * Response subResponse = given().header(AUTH_TOKEN_HEADER_FIELD,
	 * getAuthToken(userName, passWord)).when()
	 * .get(ProjectConfigs.getServer_APP_URI() + GENRE_URI + language); String
	 * actualValue =
	 * JsonPath.from(subResponse.getBody().asString()).getString("uuid"); return
	 * actualValue; } catch (Exception e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 */
}
