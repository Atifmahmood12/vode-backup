package com.vidscape.tests;



import java.util.HashMap;

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
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

@Guice
public class SubGroup implements APIEndPoints {

	HelperTestMethods htm = new HelperTestMethods();
	UrlValidator urlValidator = new UrlValidator();
	Certificate cer = new Certificate();
	HashMap<String, String> subGroupData;
	static {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public SubGroup() {
		ProjectConfigs.projectConfig(null);
	}

	@Factory(dataProvider = "SubGroup", dataProviderClass = DataProviders.class)
	public SubGroup(String tcID, String tcDescription, String tcLanguageSelection, String tcStatusCode,
			String tcResponseContentType, String tcSubGroupId, String tcSortType, String tcCurrentPage,
			String tcPageSize, String tcTotalRecord, String tcCurrentPageInResponse, String tcLastPageInResponse,
			String tcNextPageInResponse, String tcSubGroupJsonPTag, String tcSubGroupJsonContentTag,
			String tcSubGroupJsonContentPageTag, String tcSubGroupJsonContentPageSizeTag,
			String tcSubGroupJsonContentPageTotalRecordsTag, String tcSubGroupJsonContentDataTag,
			String tcSubGroupJsonContentDataTypeTag, String tcSubGroupImageJsonTag) {

		subGroupData = new HashMap<String, String>();
		subGroupData.put("TCID", tcID);
		subGroupData.put("TCDescription", tcDescription);
		subGroupData.put("TCLanguageSelection", tcLanguageSelection);
		subGroupData.put("TCStatusCode", tcStatusCode);
		subGroupData.put("TCResponseContentType", tcResponseContentType);

		subGroupData.put("TCSubGroupId", tcSubGroupId);
		subGroupData.put("TCSortType", tcSortType);
		subGroupData.put("TCCurrentPage", tcCurrentPage);
		subGroupData.put("TCPageSize", tcPageSize);

		subGroupData.put("TCTotalRecord", tcTotalRecord);
		subGroupData.put("TCCurrentPageInResponse", tcCurrentPageInResponse);
		subGroupData.put("TCLastPageInResponse", tcLastPageInResponse);
		subGroupData.put("TCNextPageInResponse", tcNextPageInResponse);

		subGroupData.put("TCSubGroupJsonPTag", tcSubGroupJsonPTag);
		subGroupData.put("TCSubGroupJsonContentTag", tcSubGroupJsonContentTag);
		subGroupData.put("TCSubGroupJsonContentPageTag", tcSubGroupJsonContentPageTag);
		subGroupData.put("TCSubGroupJsonContentPageSizeTag", tcSubGroupJsonContentPageSizeTag);
		subGroupData.put("TCSubGroupJsonContentPageTotalRecordsTag", tcSubGroupJsonContentPageTotalRecordsTag);
		subGroupData.put("TCSubGroupJsonContentDataTag", tcSubGroupJsonContentDataTag);
		subGroupData.put("TCSubGroupJsonContentDataTypeTag", tcSubGroupJsonContentDataTypeTag);
		subGroupData.put("TCSubGroupImageJsonTag", tcSubGroupImageJsonTag);

	}

	@Test
	public void SubGroupStatuCode() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getSUB_GROUP_API_BASE_PATH()
							+ subGroupData.get("TCSubGroupId") + "/" + subGroupData.get("TCSortType") + "?current="
							+ subGroupData.get("TCCurrentPage") + "&size=" + subGroupData.get("TCPageSize") + "&"
							+ LANGUAGE_PARAM + subGroupData.get("TCLanguageSelection"));
			
			System.out.println(resAuth.getBody().asString().toString());
			htm.CheckStatus(resAuth, Integer.parseInt(subGroupData.get("TCStatusCode")), subGroupData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void SubGroupResponseContentType() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getSUB_GROUP_API_BASE_PATH()
							+ subGroupData.get("TCSubGroupId") + "/" + subGroupData.get("TCSortType") + "?current="
							+ subGroupData.get("TCCurrentPage") + "&size=" + subGroupData.get("TCPageSize") + "&"
							+ LANGUAGE_PARAM + subGroupData.get("TCLanguageSelection"));
			htm.CheckResponseContentType(resAuth, subGroupData.get("TCResponseContentType"), subGroupData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void verifyPageSizeReturnInResponse() {
		try {

			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getSUB_GROUP_API_BASE_PATH()
							+ subGroupData.get("TCSubGroupId") + "/" + subGroupData.get("TCSortType") + "?current="
							+ subGroupData.get("TCCurrentPage") + "&size=" + subGroupData.get("TCPageSize") + "&"
							+ LANGUAGE_PARAM + subGroupData.get("TCLanguageSelection"));
			Assert.assertEquals(
					htm.GetDirectSelectedTagDataFromJsonResponseInInteger(resAuth,
							subGroupData.get("TCSubGroupJsonContentPageSizeTag")),
					Integer.parseInt(subGroupData.get("TCPageSize")));

		} catch (AssertionError e) {
			throw e;
		}

	}

	@Test
	public void verifyTotalRecordReturnInResponse() {
		try {

			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getSUB_GROUP_API_BASE_PATH()
							+ subGroupData.get("TCSubGroupId") + "/" + subGroupData.get("TCSortType") + "?current="
							+ subGroupData.get("TCCurrentPage") + "&size=" + subGroupData.get("TCPageSize") + "&"
							+ LANGUAGE_PARAM + subGroupData.get("TCLanguageSelection"));
			Assert.assertEquals(
					htm.GetDirectSelectedTagDataFromJsonResponseInInteger(resAuth,
							subGroupData.get("TCSubGroupJsonContentPageTotalRecordsTag")),
					Integer.parseInt(subGroupData.get("TCTotalRecord")));

		} catch (AssertionError e) {
			throw e;
		}

	}
	
	@Test
	public void verifyAssociatedSubGroupImage () {
		
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getSUB_GROUP_API_BASE_PATH()
							+ subGroupData.get("TCSubGroupId") + "/" + subGroupData.get("TCSortType") + "?current="
							+ subGroupData.get("TCCurrentPage") + "&size=" + subGroupData.get("TCPageSize") + "&"
							+ LANGUAGE_PARAM + subGroupData.get("TCLanguageSelection"));
			Assert.assertTrue(
					urlValidator.isValid(htm.GetDirectSelectedTagDataFromJsonResponseInString(resAuth,subGroupData.get("TCSubGroupImageJsonTag"))),
					subGroupData.get("TCID")
							+"The Invalid URL is: - "+ htm.GetDirectSelectedTagDataFromJsonResponseInString(resAuth,subGroupData.get("TCSubGroupImageJsonTag")));
		} catch (AssertionError e) {
			throw e;
		}
		
		
		
		
	}

}
