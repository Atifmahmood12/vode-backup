package com.vidscape.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.validator.routines.UrlValidator;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.vidscape.configs.Certificate;
import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.dataproviders.DataProviders;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

@Guice
public class Banner implements APIEndPoints {
	HelperTestMethods htm = new HelperTestMethods();
	Certificate cer = new Certificate();
	HashMap<String, String> bannerData;
	UrlValidator urlValidator = new UrlValidator();
	static {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public Banner() {
		ProjectConfigs.projectConfig(null);
	}

	@Factory(dataProvider = "Banner", dataProviderClass = DataProviders.class)
	public Banner(String tcID, String tcDescription, String tcLanguageSelection, String tcStatusCode,
			String tcResponseContentType, String tcPromotionalBannerCount, String tcContentTypeBannerCount,
			String tcEPGBannerCount, String tcBannerJsonPTag, String tcImageURLJsonTag, String tcTypeJsonTag,
			String tcPBannerTypeJSONTag, String tcCBannerTypeJSONTag, String tcEPGBannerTypeJSONTag) {

		bannerData = new HashMap<String, String>();
		bannerData.put("TCID", tcID);
		bannerData.put("TCDescription", tcDescription);
		bannerData.put("TCLanguageSelection", tcLanguageSelection);
		bannerData.put("TCStatusCode", tcStatusCode);
		bannerData.put("TCResponseContentType", tcResponseContentType);

		bannerData.put("TCPromotionalBannerCount", tcPromotionalBannerCount);
		bannerData.put("TCContentTypeBannerCount", tcContentTypeBannerCount);
		bannerData.put("TCEPGBannerCount", tcEPGBannerCount);

		bannerData.put("TCBannerJsonPTag", tcBannerJsonPTag);
		bannerData.put("TCTypeJsonTag", tcTypeJsonTag);
		bannerData.put("TCImageURLJsonTag", tcImageURLJsonTag);
		bannerData.put("TCTypeJsonTag", tcTypeJsonTag);
		bannerData.put("TCPBannerTypeJSONTag", tcPBannerTypeJSONTag);
		bannerData.put("TCCBannerTypeJSONTag", tcCBannerTypeJSONTag);
		bannerData.put("TCEPGBannerTypeJSONTag", tcEPGBannerTypeJSONTag);
	}

	@Test
	public void BannerStatuCode() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));
			htm.CheckStatus(resAuth, Integer.parseInt(bannerData.get("TCStatusCode")), bannerData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void BannerResponseContentType() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));
			htm.CheckResponseContentType(resAuth, bannerData.get("TCResponseContentType"), bannerData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void verifyPromotionTypeBannerCount() {
		try {
			int BannerExistanceCounter = 0;
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					bannerData.get("TCBannerJsonPTag"));
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.get(bannerData.get("TCTypeJsonTag")).toString()
						.contains(bannerData.get("TCPBannerTypeJSONTag"))) {
					BannerExistanceCounter++;
				}
			}
			Assert.assertEquals(BannerExistanceCounter, Integer.parseInt(bannerData.get("TCPromotionalBannerCount")),
					bannerData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void verifyContentTypeBannerCount() {
		try {
			int BannerExistanceCounter = 0;
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					bannerData.get("TCBannerJsonPTag"));
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.get(bannerData.get("TCTypeJsonTag")).toString()
						.contains(bannerData.get("TCCBannerTypeJSONTag"))) {
					BannerExistanceCounter++;
				}
			}
			Assert.assertEquals(BannerExistanceCounter, Integer.parseInt(bannerData.get("TCContentTypeBannerCount")),
					bannerData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void verifyEPGTypeBannerCount() {
		try {
			int BannerExistanceCounter = 0;
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					bannerData.get("TCBannerJsonPTag"));
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.get(bannerData.get("TCTypeJsonTag")).toString()
						.contains(bannerData.get("TCEPGBannerTypeJSONTag"))) {
					BannerExistanceCounter++;
				}
			}
			Assert.assertEquals(BannerExistanceCounter, Integer.parseInt(bannerData.get("TCEPGBannerCount")),
					bannerData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void verifyPromotionTypeBannerImages() {

		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));

			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					bannerData.get("TCBannerJsonPTag"));
			StringBuilder str = new StringBuilder();
			int FailedURLCounter = 0;
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.get(bannerData.get("TCTypeJsonTag")).toString()
						.contains(bannerData.get("TCPBannerTypeJSONTag"))) {

					if (!jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString().isEmpty()) {
						try {
							// That is the way to test the status code of the URL.
							/*
							RestAssured.reset();
							RestAssured.config = RestAssured.config()
									.sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation("TLS"));
							
							
							 * Assert.assertEquals(
							 * given().get(jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")))
							 * .getStatusCode(), 200, bannerData.get("TCID")+
							 * jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
							 */
							// That is the code in which we are verifying the URL.
							// boolean valid =
							// urlValidator.isValid(jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")));

							Assert.assertTrue(
									urlValidator.isValid(jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag"))),
									bannerData.get("TCID")
											+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
						} catch (AssertionError e) {
							str.append(jsonAsArrayList1.get("bannerUUID").toString()
									+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
							FailedURLCounter++;
						}
					} else {
						str.append(jsonAsArrayList1.get("bannerUUID").toString()
								+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
						FailedURLCounter++;
					}
				}
			}
			System.out.println(str);
			Assert.assertFalse(FailedURLCounter > 0,
					bannerData.get("TCID") + ": - The Failed Images URLs are: /n " + str);
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void verifyContentTypeBannerImages() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					bannerData.get("TCBannerJsonPTag"));
			StringBuilder str = new StringBuilder();
			int FailedURLCounter = 0;
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.get(bannerData.get("TCTypeJsonTag")).toString().contains(bannerData.get("TCCBannerTypeJSONTag"))) {
					if (!jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString().isEmpty()) {
						try {
							Assert.assertTrue(
									urlValidator.isValid(jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag"))),
									bannerData.get("TCID")
											+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
						} catch (AssertionError e) {
							str.append(jsonAsArrayList1.get("bannerUUID").toString()
									+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
							FailedURLCounter++;
						}
					} else {
						str.append(jsonAsArrayList1.get("bannerUUID").toString()
								+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
						FailedURLCounter++;
					}
				}
			}
			System.out.println(str);
			Assert.assertFalse(FailedURLCounter > 0,
					bannerData.get("TCID") + ": - The Failed Images URLs are: /n " + str);
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void verifyEPGTypeBannerImages() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getBANNER_API_BASE_PATH() + LANGUAGE_PARAM
							+ bannerData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					bannerData.get("TCBannerJsonPTag"));
			StringBuilder str = new StringBuilder();
			int FailedURLCounter = 0;
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.get(bannerData.get("TCTypeJsonTag")).toString()
						.contains(bannerData.get("TCEPGBannerTypeJSONTag"))) {
					if (!jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString().isEmpty()) {

						try {
							Assert.assertTrue(
									urlValidator.isValid(jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag"))),
									bannerData.get("TCID")
											+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
						} catch (AssertionError e) {
							str.append(jsonAsArrayList1.get("bannerUUID").toString()
									+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
							FailedURLCounter++;
						}
					} else {
						str.append(jsonAsArrayList1.get("bannerUUID").toString()
								+ jsonAsArrayList1.get(bannerData.get("TCImageURLJsonTag")).toString());
						FailedURLCounter++;
					}
				}
			}
			System.out.println(str);
			Assert.assertFalse(FailedURLCounter > 0,
					bannerData.get("TCID") + ": - The Failed Images URLs are: /n " + str);
		} catch (AssertionError e) {
			throw e;
		}
	}
}
