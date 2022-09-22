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

import com.google.common.base.Splitter;
import com.vidscape.configs.Certificate;
import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.dataproviders.DataProviders;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Guice
public class Group implements APIEndPoints {

	HelperTestMethods htm = new HelperTestMethods();
	UrlValidator urlValidator = new UrlValidator();
	Certificate cer = new Certificate();
	HashMap<String, String> groupData;
	static {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public Group() {
		ProjectConfigs.projectConfig(null);
	}

	@Factory(dataProvider = "Group", dataProviderClass = DataProviders.class)
	public Group(String tcID, String tcDescription, String tcLanguageSelection, String tcStatusCode,
			String tcResponseContentType, String tcGroupCounts, String tcGroupCountwithExtraAttribute,
			String tcExtraAttributeValues, String tcGroupJsonPTag, String tcTypeJsonTag, String tcImageURLJsonTag,
			String tcExtraAttributeJSonTag, String tcExtraAttributeJSonTagValue, String tcGroupName) {

		groupData = new HashMap<String, String>();
		groupData.put("TCID", tcID);
		groupData.put("TCDescription", tcDescription);
		groupData.put("TCLanguageSelection", tcLanguageSelection);
		groupData.put("TCStatusCode", tcStatusCode);
		groupData.put("TCResponseContentType", tcResponseContentType);

		groupData.put("TCGroupCounts", tcGroupCounts);
		groupData.put("TCGroupCountwithExtraAttribute", tcGroupCountwithExtraAttribute);
		groupData.put("TCExtraAttributeValues", tcExtraAttributeValues);

		groupData.put("TCGroupJsonPTag", tcGroupJsonPTag);
		groupData.put("TCTypeJsonTag", tcTypeJsonTag);
		groupData.put("TCImageURLJsonTag", tcImageURLJsonTag);
		groupData.put("TCExtraAttributeJSonTag", tcExtraAttributeJSonTag);
		groupData.put("TCExtraAttributeJSonTagValue", tcExtraAttributeJSonTagValue);
		groupData.put("TCGroupName", tcGroupName);

	}

	@Test
	public void GroupStatuCode() {
		try {
			Response resAuth = given().when().get(ProjectConfigs.getClient_APP_URI()
					+ ProjectConfigs.getGROUP_API_BASE_PATH() + LANGUAGE_PARAM + groupData.get("TCLanguageSelection"));
			htm.CheckStatus(resAuth, Integer.parseInt(groupData.get("TCStatusCode")), groupData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void GroupResponseContentType() {
		try {
			Response resAuth = given().when().get(ProjectConfigs.getClient_APP_URI()
					+ ProjectConfigs.getGROUP_API_BASE_PATH() + LANGUAGE_PARAM + groupData.get("TCLanguageSelection"));
			htm.CheckResponseContentType(resAuth, groupData.get("TCResponseContentType"), groupData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void VerifyGroupCounts() {
		try {
			int GoupItemCounter = 0;
			Response resAuth = given().when().get(ProjectConfigs.getClient_APP_URI()
					+ ProjectConfigs.getGROUP_API_BASE_PATH() + LANGUAGE_PARAM + groupData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					groupData.get("TCGroupJsonPTag"));
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (!jsonAsArrayList1.get(groupData.get("TCImageURLJsonTag")).isEmpty()) {
					GoupItemCounter++;
				}
			}
			Assert.assertEquals(GoupItemCounter, Integer.parseInt(groupData.get("TCGroupCounts")),
					groupData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void VerifyGroupContentImages() {
		try {
			StringBuilder str = new StringBuilder();
			int FailedURLCounter = 0;
			Response resAuth = given().when().get(ProjectConfigs.getClient_APP_URI()+ ProjectConfigs.getGROUP_API_BASE_PATH() + LANGUAGE_PARAM + groupData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,groupData.get("TCGroupJsonPTag"));
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (!jsonAsArrayList1.get(groupData.get("TCImageURLJsonTag")).toString().isEmpty()) {
					try {
						Assert.assertTrue(
								urlValidator.isValid(jsonAsArrayList1.get(groupData.get("TCImageURLJsonTag"))),
								groupData.get("TCID")
										+ jsonAsArrayList1.get(groupData.get("TCImageURLJsonTag")).toString());
					} catch (AssertionError e) {
						str.append(jsonAsArrayList1.get("menuUUID").toString()
								+ jsonAsArrayList1.get(groupData.get("TCImageURLJsonTag")).toString());
						str.append(System.getProperty("line.separator"));
						FailedURLCounter++;
					}
				} else {
					str.append(jsonAsArrayList1.get("menuUUID").toString()
							+ jsonAsArrayList1.get(groupData.get("TCImageURLJsonTag")).toString());
					str.append(System.getProperty("line.separator"));
					FailedURLCounter++;
				}
			}
			Assert.assertFalse(FailedURLCounter > 0,groupData.get("TCID") + ": - The Failed Images URLs are: /n " + str);
		} catch (

		AssertionError e) {

			throw e;
		}
	}

	@Test
	public void VerifyGroupCountswithExtraAttribute() {
		try {
			int extraAttributeCounter = 0;
			Response resAuth = given().when().get(ProjectConfigs.getClient_APP_URI()
					+ ProjectConfigs.getGROUP_API_BASE_PATH() + LANGUAGE_PARAM + groupData.get("TCLanguageSelection"));
			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					groupData.get("TCGroupJsonPTag"));
			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				if (jsonAsArrayList1.containsKey(groupData.get("TCExtraAttributeJSonTag"))) {
					extraAttributeCounter++;
				}
			}
			Assert.assertEquals(extraAttributeCounter,
					Integer.parseInt(groupData.get("TCGroupCountwithExtraAttribute")), groupData.get("TCID"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void VerifyGroupExtraAttribute() {
		String groupwithSubGroupIdData = groupData.get("TCExtraAttributeJSonTagValue");
		try {
			Map<String, String> expectedValueInMap = Splitter.on("::").withKeyValueSeparator("-")
					.split(groupwithSubGroupIdData);
			System.out.println(expectedValueInMap);

			Response resAuth = given().when().get(ProjectConfigs.getClient_APP_URI()
					+ ProjectConfigs.getGROUP_API_BASE_PATH() + LANGUAGE_PARAM + groupData.get("TCLanguageSelection"));

			ArrayList<Map<String, String>> jsonAsArrayList = htm.GetSelectedTagDataFromJsonResponse(resAuth,
					groupData.get("TCGroupJsonPTag"));

			StringBuilder str = new StringBuilder();

			for (Map<String, String> jsonAsArrayList1 : jsonAsArrayList) {
				System.out.println(expectedValueInMap.get(jsonAsArrayList1.get(groupData.get("TCGroupName"))));

				if (jsonAsArrayList1.containsKey(groupData.get("TCExtraAttributeJSonTag"))) {
					try {
						Assert.assertEquals(jsonAsArrayList1.get(groupData.get("TCExtraAttributeJSonTag")),
								expectedValueInMap.get(jsonAsArrayList1.get(groupData.get("TCGroupName"))));
					} catch (AssertionError e) {
						str.append(jsonAsArrayList1.get(groupData.get("TCGroupName")).toString()
								+ jsonAsArrayList1.get(groupData.get("TCExtraAttributeJSonTag")));
					}
				}
			}
			Assert.assertTrue(str.toString().isEmpty(),
					groupData.get("TCID") + ": - The Failed Groups Names are: /n " + str);
		} catch (AssertionError e) {
			throw e;
		}

	}

}
