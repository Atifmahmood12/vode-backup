package com.vidscape.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.vidscape.configs.Certificate;
import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.dataproviders.DataProviders;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Guice
public class Search implements APIEndPoints {

	HelperTestMethods htm = new HelperTestMethods();
	Certificate cer = new Certificate();
	HashMap<String, String> searchData;
	static {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public Search() {
		ProjectConfigs.projectConfig(null);
	}

	@Factory(dataProvider = "Search", dataProviderClass = DataProviders.class)
	public Search(String tcID, String tcDescription, String tcLanguageSelection, String tcStatusCode,
			String tcResponseContentType, String tcSearchText, String tcFrom, String tcSize, String tcType,
			String tcSearchExpectedRecord, String tcSearchResultsJsonTag) {

		searchData = new HashMap<String, String>();
		searchData.put("TCID", tcID);
		searchData.put("TCDescription", tcDescription);
		searchData.put("TCLanguageSelection", tcLanguageSelection);
		searchData.put("TCStatusCode", tcStatusCode);
		searchData.put("TCResponseContentType", tcResponseContentType);

		searchData.put("TCSearchText", tcSearchText);
		searchData.put("TCFrom", tcFrom);
		searchData.put("TCSize", tcSize);
		searchData.put("TCType", tcType);
		searchData.put("TCSearchExpectedRecord", tcSearchExpectedRecord);

		searchData.put("TCSearchResultsJsonTag", tcSearchResultsJsonTag);

	}

	@Test
	public void SearchStatuCode() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getVOD_SEARCH_API_BASE_PATH()
							+ SEARCH_VAR_PARAM + searchData.get("TCSearchText") + SEARCH_FROM_PARAM
							+ searchData.get("TCFrom") + SEARCH_SIZE_PARAM + searchData.get("TCSize")
							+ SEARCH_TYPE_PARAM + searchData.get("TCType") + "&" + LANGUAGE_PARAM
							+ searchData.get("TCLanguageSelection"));

			htm.CheckStatus(resAuth, Integer.parseInt(searchData.get("TCStatusCode")), searchData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void SearchResponseContentType() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getVOD_SEARCH_API_BASE_PATH()
							+ SEARCH_VAR_PARAM + searchData.get("TCSearchText") + SEARCH_FROM_PARAM
							+ searchData.get("TCFrom") + SEARCH_SIZE_PARAM + searchData.get("TCSize")
							+ SEARCH_TYPE_PARAM + searchData.get("TCType") + "&" + LANGUAGE_PARAM
							+ searchData.get("TCLanguageSelection"));
			htm.CheckResponseContentType(resAuth, searchData.get("TCResponseContentType"), searchData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void VerifyTotalSearchRecord() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getVOD_SEARCH_API_BASE_PATH()
							+ SEARCH_VAR_PARAM + searchData.get("TCSearchText") + SEARCH_FROM_PARAM
							+ searchData.get("TCFrom") + SEARCH_SIZE_PARAM + searchData.get("TCSize")
							+ SEARCH_TYPE_PARAM + searchData.get("TCType") + "&" + LANGUAGE_PARAM
							+ searchData.get("TCLanguageSelection"));
			Assert.assertEquals(
					htm.GetDirectSelectedTagDataFromJsonResponseInInteger(resAuth,
							searchData.get("TCSearchResultsJsonTag")),
					Integer.parseInt(searchData.get("TCSearchExpectedRecord")));

		} catch (AssertionError e) {
			throw e;
		}
	}
}
