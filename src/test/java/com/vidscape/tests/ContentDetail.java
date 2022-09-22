package com.vidscape.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.dataproviders.DataProviders;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Guice
public class ContentDetail implements APIEndPoints {

	HelperTestMethods htm = new HelperTestMethods();
	HashMap<String, String> contentDetailData;
	static {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public ContentDetail() {
		ProjectConfigs.projectConfig(null);
	}

	@Factory(dataProvider = "ContentDetail", dataProviderClass = DataProviders.class)
	public ContentDetail(String tcID, String tcDescription, String tcLanguageSelection, String tcStatusCode,
			String tcResponseContentType, String tcContentID, String tcContentTitle, String tcMobileConsumerURL,
			String tcPCBidashConsumerURL, String tcPCSSConsumerURL, String tcContentTitleJsonTag,
			String tcMobileConsumerURLJsonTag, String tcPCBidashConsumerURLJsonTag, String tcPCSSConsumerURLJsonTag) {

		contentDetailData = new HashMap<String, String>();
		contentDetailData.put("TCID", tcID);
		contentDetailData.put("TCDescription", tcDescription);
		contentDetailData.put("TCLanguageSelection", tcLanguageSelection);
		contentDetailData.put("TCStatusCode", tcStatusCode);
		contentDetailData.put("TCResponseContentType", tcResponseContentType);

		contentDetailData.put("TCContentID", tcContentID);
		contentDetailData.put("TCContentTitle", tcContentTitle);
		contentDetailData.put("TCMobileConsumerURL", tcMobileConsumerURL);
		contentDetailData.put("TCPCBidashConsumerURL", tcPCBidashConsumerURL);
		contentDetailData.put("TCPCSSConsumerURL", tcPCSSConsumerURL);
		contentDetailData.put("TCContentTitleJsonTag", tcContentTitleJsonTag);
		contentDetailData.put("TCMobileConsumerURLJsonTag", tcMobileConsumerURLJsonTag);
		contentDetailData.put("TCPCBidashConsumerURLJsonTag", tcPCBidashConsumerURLJsonTag);
		contentDetailData.put("TCPCSSConsumerURLJsonTag", tcPCSSConsumerURLJsonTag);

	}

	@Test
	public void ContentDetailStatuCode() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getCONTENT_DETAIL_API_BASE_PATH()
							+ contentDetailData.get("TCContentID") + "?" + LANGUAGE_PARAM
							+ contentDetailData.get("TCLanguageSelection"));
			htm.CheckStatus(resAuth, Integer.parseInt(contentDetailData.get("TCStatusCode")),
					contentDetailData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void ContentDetailContentType() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getCONTENT_DETAIL_API_BASE_PATH()
							+ contentDetailData.get("TCContentID") + "?" + LANGUAGE_PARAM
							+ contentDetailData.get("TCLanguageSelection"));
			htm.CheckResponseContentType(resAuth, contentDetailData.get("TCResponseContentType"),
					contentDetailData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void VerifyConentTitle() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getCONTENT_DETAIL_API_BASE_PATH()
							+ contentDetailData.get("TCContentID") + "?" + LANGUAGE_PARAM
							+ contentDetailData.get("TCLanguageSelection"));

			Assert.assertEquals(htm.GetDirectSelectedTagDataFromJsonResponseInString(resAuth,
					contentDetailData.get("TCContentTitleJsonTag")), contentDetailData.get("TCContentTitle"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void VerifyMobileConentConsumerURL() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getCONTENT_DETAIL_API_BASE_PATH()
							+ contentDetailData.get("TCContentID") + "?" + LANGUAGE_PARAM
							+ contentDetailData.get("TCLanguageSelection"));
			Assert.assertTrue(resAuth.jsonPath().getJsonObject(contentDetailData.get("TCMobileConsumerURLJsonTag"))
					.toString().contains(contentDetailData.get("TCMobileConsumerURL")));

		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void VerifyBidDashConentConsumerURL() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getCONTENT_DETAIL_API_BASE_PATH()
							+ contentDetailData.get("TCContentID") + "?" + LANGUAGE_PARAM
							+ contentDetailData.get("TCLanguageSelection"));
			Assert.assertTrue(resAuth.jsonPath().getJsonObject(contentDetailData.get("TCPCBidashConsumerURLJsonTag"))
					.toString().contains(contentDetailData.get("TCPCBidashConsumerURL")));
		} catch (AssertionError e) {
			throw e;
		}

	}

	@Test
	public void VerifySilverLightConentConsumerURL() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getCONTENT_DETAIL_API_BASE_PATH()
							+ contentDetailData.get("TCContentID") + "?" + LANGUAGE_PARAM
							+ contentDetailData.get("TCLanguageSelection"));
			Assert.assertTrue(resAuth.jsonPath().getJsonObject(contentDetailData.get("TCPCSSConsumerURLJsonTag"))
					.toString().contains(contentDetailData.get("TCPCSSConsumerURL")), contentDetailData.get("TCID"));

		} catch (AssertionError e) {
			throw e;
		}

	}

}
