package com.vidscape.tests;

import static io.restassured.RestAssured.given;

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
import io.restassured.response.Response;

@Guice
public class GroupDetail implements APIEndPoints {
	
	
	HelperTestMethods htm = new HelperTestMethods();
	UrlValidator urlValidator = new UrlValidator();
	Certificate cer = new Certificate();
	HashMap<String, String> groupDetailData;
	static {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public GroupDetail() {
		ProjectConfigs.projectConfig(null);
	}

	@Factory(dataProvider = "GroupDetail", dataProviderClass = DataProviders.class)
	public GroupDetail(
			String tcID, String tcDescription, String tcLanguageSelection, String tcStatusCode,
			String tcResponseContentType, String tcGroupId, String tcGroupName, String tcAssociatedGroupCounts,
			String tcGroupNameJsonTag, String tcAssociatedGroupJsonTag, String tcAssociatedGroupParamJsonTag, String tcGroupImageURLJsonTag
) {

		groupDetailData = new HashMap<String, String>();
		groupDetailData.put("TCID", tcID);
		groupDetailData.put("TCDescription", tcDescription);
		groupDetailData.put("TCLanguageSelection", tcLanguageSelection);
		groupDetailData.put("TCStatusCode", tcStatusCode);
		groupDetailData.put("TCResponseContentType", tcResponseContentType);

		groupDetailData.put("TCGroupId", tcGroupId);
		groupDetailData.put("TCGroupName", tcGroupName);
		groupDetailData.put("TCAssociatedGroupCounts", tcAssociatedGroupCounts);

		groupDetailData.put("TCGroupNameJsonTag", tcGroupNameJsonTag);
		groupDetailData.put("TCAssociatedGroupJsonTag", tcAssociatedGroupJsonTag);
		groupDetailData.put("TCAssociatedGroupParamJsonTag", tcAssociatedGroupParamJsonTag);
		groupDetailData.put("TCGroupImageURLJsonTag", tcGroupImageURLJsonTag);
	}

	@Test
	public void GroupDetailStatuCode() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getGROUP_DETAIL_API_BASE_PATH()
							+ groupDetailData.get("TCGroupId") + "?" + LANGUAGE_PARAM
							+ groupDetailData.get("TCLanguageSelection"));
			htm.CheckStatus(resAuth, Integer.parseInt(groupDetailData.get("TCStatusCode")), groupDetailData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void GroupDetailResponseContentType() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getGROUP_DETAIL_API_BASE_PATH()
							+ groupDetailData.get("TCGroupId") + "?" + LANGUAGE_PARAM
							+ groupDetailData.get("TCLanguageSelection"));
			htm.CheckResponseContentType(resAuth, groupDetailData.get("TCResponseContentType"), groupDetailData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}
	@Test
	public void VerifyGroupName() {
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getGROUP_DETAIL_API_BASE_PATH()
							+ groupDetailData.get("TCGroupId") + "?" + LANGUAGE_PARAM
							+ groupDetailData.get("TCLanguageSelection"));
			Assert.assertEquals(htm.GetDirectSelectedTagDataFromJsonResponseInString(resAuth,
					groupDetailData.get("TCGroupNameJsonTag")), groupDetailData.get("TCGroupName"));

		} catch (AssertionError e) {
			throw e;
		}
	}
	@Test
	public void VerifyAssociatedSubGroupCount() {
		int AssociatedGroupCount = 0;
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getGROUP_DETAIL_API_BASE_PATH()
							+ groupDetailData.get("TCGroupId") + "?" + LANGUAGE_PARAM
							+ groupDetailData.get("TCLanguageSelection"));
			
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					groupDetailData.get("TCAssociatedGroupJsonTag"));
			
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.containsKey(groupDetailData.get("TCAssociatedGroupParamJsonTag"))) {
					AssociatedGroupCount++;
				}
			}
			Assert.assertEquals(AssociatedGroupCount, Integer.parseInt(groupDetailData.get("TCAssociatedGroupCounts")), groupDetailData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}
	
	@Test
	public void verifyAssociatedSubGroupImages (){
		
		try {
			Response resAuth = given().when()
					.get(ProjectConfigs.getClient_APP_URI() + ProjectConfigs.getGROUP_DETAIL_API_BASE_PATH()
							+ groupDetailData.get("TCGroupId") + "?" + LANGUAGE_PARAM
							+ groupDetailData.get("TCLanguageSelection"));
			Assert.assertTrue(
					urlValidator.isValid(htm.GetDirectSelectedTagDataFromJsonResponseInString(resAuth,groupDetailData.get("TCGroupImageURLJsonTag"))),
					groupDetailData.get("TCID")
							+"The Invalid URL is: - "+ htm.GetDirectSelectedTagDataFromJsonResponseInString(resAuth,groupDetailData.get("TCGroupImageURLJsonTag")));
		} catch (AssertionError e) {
			throw e;
		}
		
		
		
		
	}
	

}
