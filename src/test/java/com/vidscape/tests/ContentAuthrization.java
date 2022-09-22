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
public class ContentAuthrization implements APIEndPoints{
	HelperTestMethods htm = new HelperTestMethods();
	HashMap<String, String> AuthData;
	static {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public ContentAuthrization() {
		ProjectConfigs.projectConfig(null);
	}

	@Factory(dataProvider = "ContentAuth", dataProviderClass = DataProviders.class)
	public ContentAuthrization(String tcID, String tcDescription, String tcLanguageSelection, String tcStatusCode,
			String tcResponseContentType, String RequestContentType, String tcContentId, String tcTechnicalOffer,
			String tcVerifyResponJson) {

		AuthData = new HashMap<String, String>();
		AuthData.put("TCID", tcID);
		AuthData.put("TCDescription", tcDescription);
		AuthData.put("TCLanguageSelection", tcLanguageSelection);
		AuthData.put("TCStatusCode", tcStatusCode);
		AuthData.put("TCResponseContentType", tcResponseContentType);

		AuthData.put("TCRequestContentType", RequestContentType);
		AuthData.put("TCContentId", tcContentId);
		AuthData.put("TCTechnicalOffer", tcTechnicalOffer);
		AuthData.put("TCVerifyResponJson", tcVerifyResponJson);
	}

	@Test
	public void ContentAuthStatuCode() {
		try {

			// given().contentType("").body("").post()
			Response resAuth = given().contentType(AuthData.get("TCRequestContentType"))
					.body(AuthData.get("TCTechnicalOffer")).when().post(ProjectConfigs.getClient_APP_URI()
							+ ProjectConfigs.getCONTENT_AUTHORIZATION_API_BASE_PATH() + AuthData.get("TCContentId"));
			htm.CheckStatus(resAuth, Integer.parseInt(AuthData.get("TCStatusCode")), AuthData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void ContentAuthContentType() {
		try {
			Response resAuth = given().contentType(AuthData.get("TCRequestContentType"))
					.body(AuthData.get("TCTechnicalOffer")).when().post(ProjectConfigs.getClient_APP_URI()
							+ ProjectConfigs.getCONTENT_AUTHORIZATION_API_BASE_PATH() + AuthData.get("TCContentId"));

			htm.CheckResponseContentType(resAuth, AuthData.get("TCResponseContentType"), AuthData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void CotentAuthResponseVerification() {

		try {
			Response resAuth = given().contentType(AuthData.get("TCRequestContentType"))
					.body(AuthData.get("TCTechnicalOffer")).when().post(ProjectConfigs.getClient_APP_URI()
							+ ProjectConfigs.getCONTENT_AUTHORIZATION_API_BASE_PATH() + AuthData.get("TCContentId"));
			Assert.assertEquals(htm.ConvertResponseBodytoString(resAuth), AuthData.get("TCVerifyResponJson"),
					AuthData.get("TCID") + ":- The response is " + htm.ConvertResponseBodytoString(resAuth));
		} catch (AssertionError e) {
			throw e;
		}

	}

}
