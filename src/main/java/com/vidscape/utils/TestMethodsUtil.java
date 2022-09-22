package com.vidscape.utils;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;

import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.constants.DataSheetsConstants;
import com.vidscape.constants.MessageIngestorEndPoints;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestMethodsUtil implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {

	// Get Token
	public String getAuthToken(String userName, String passWord) {
		try {
			Response resAuth = given().header(AUTH_API_USERNAME, userName).given().header(AUTH_API_PASSWORD, passWord)
					.when().post(ProjectConfigs.getServer_APP_URI() + SERVER_API_AUTH_URL);
			return resAuth.then().extract().jsonPath().getString("token");
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	///////////////////

	/// getResponseMethod1
	public String getResponse(String language, String jsonPath, String userName, String passWord, String scenarioName,
			String URI) {
		try {
			Response response = given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName, passWord)).when()
					.get(ProjectConfigs.getServer_APP_URI() + URI + language);
			String actualValue = JsonPath.from(response.getBody().asString()).getString(jsonPath);
			return actualValue;
		} catch (Exception e) {
			e.getCause();
		}
		return null;

	}

	/// GerResonseMethod2
	public String getResponse(String status, String contentTypeID, String currentPage, String size, String isAvailable,
			String language, String jsonPath, String userName, String passWord, String scenarioName, String URI) {
		try {
			Response response = given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName, passWord)).when()
					.get(ProjectConfigs.getServer_APP_URI() + URI + ALL_CONTENT_URI_QUERYSTRING_STATUS + status
							+ ALL_CONTENT_URI_QUERYSTRING_CONTENT_TYPE + contentTypeID
							+ ALL_CONTENT_URI_QUERYSTRING_CURRENT + currentPage + ALL_CONTENT_URI_QUERYSTRING_SIZE
							+ size + ALL_CONTENT_URI_QUERYSTRING_IS_AVAILABLE + isAvailable
							+ ALL_CONTENT_URI_QUERYSTRING_IS_LANGUAGE + language);
			String actualValue = JsonPath.from(response.getBody().asString()).getString(jsonPath);
			return actualValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getCollectionResponse() {
		return null;

	}

	public void VerifyGenreData(Map<String, String> dataMap) {

		StringBuilder sb = new StringBuilder();
		try {
			switch (dataMap.get(KEYWORD_ACTION)) {
			case KEYWORD_CREATE:
				if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								GENRE_URI);
						Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")), "TestCase ID :-"
								+ dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario") + ", Action:- "
								+ dataMap.get(KEYWORD_ACTION)
								+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed + Creat + B");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;
					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + B");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}
				} else if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								GENRE_URI);

						Assert.assertTrue(currentJson.contains(dataMap.get("VerificationValue")),
								"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
										+ ", Action:-" + dataMap.get(KEYWORD_ACTION) + " Reason: is not ingested");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed Create + F");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;

					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + F");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}
				} else {
					System.out.println(dataMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
				}
				break;
			case KEYWORD_UPDATE:
				if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								GENRE_URI);
						Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")), "TestCase ID :-"
								+ dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario") + ", Action:- "
								+ dataMap.get(KEYWORD_ACTION)
								+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed udpate + B");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;

					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + B");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}

				} else if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								GENRE_URI);
						Assert.assertTrue(currentJson.contains(dataMap.get("VerificationValue")),
								"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
										+ ", Action:- " + dataMap.get(KEYWORD_ACTION)
										+ " Reason:- data is not ingested");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed update F");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;
					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + F");
						// sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}

				} else {
					System.out.println(dataMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
				}
				break;
			case KEYWORD_PURGE:
				try {
					String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
							ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
							ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO), GENRE_URI);

					Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")),
							"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
									+ ", Action:- " + dataMap.get(KEYWORD_ACTION)
									+ " Reason:- Data is not purged successfully");
				} catch (AssertionError e) {
					System.out.println("TestcaseGot failed Purge");
					// sb.append(e.getMessage() + "\n\t\n");
					throw e;
				} catch (NullPointerException e) {
					System.out.println("TestcaseGot failed + Creat + B");
					// sb.append(e.getMessage() + "\n\t\n");
					throw e;
				}
				break;
			default:
				try {
					System.out.println(dataMap.get(KEYWORD_ACTION) + "This case is not handled yet");

				} catch (Exception e) {
					// sb.append(e.getMessage() + "\n\t\n");
					throw e;
				}
			}
		} catch (Exception e) {
			sb.append(e.getMessage() + "\n\t\n");
			throw e;
		}
	}

	public void VerifySubscriptionData(Map<String, String> dataMap) {

		StringBuilder sb = new StringBuilder();
		try {
			switch (dataMap.get(KEYWORD_ACTION)) {
			case KEYWORD_CREATE:
				if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								PRICING_MODEL_URI);

						Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")), "TestCase ID :-"
								+ dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario") + ", Action:- "
								+ dataMap.get(KEYWORD_ACTION)
								+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed + Creat + B");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;
					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + B");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}
				} else if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								PRICING_MODEL_URI);
						Assert.assertTrue(currentJson.contains(dataMap.get("VerificationValue")),
								"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
										+ ", Action:-" + dataMap.get(KEYWORD_ACTION) + " Reason: is not ingested");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed Create + F");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;

					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + F");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}

				} else {
					System.out.println(dataMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
				}
				break;
			case KEYWORD_UPDATE:
				if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								PRICING_MODEL_URI);

						Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")), "TestCase ID :-"
								+ dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario") + ", Action:- "
								+ dataMap.get(KEYWORD_ACTION)
								+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed udpate + B");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;

					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + B");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}

				} else if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								PRICING_MODEL_URI);

						Assert.assertTrue(currentJson.contains(dataMap.get("VerificationValue")),
								"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
										+ ", Action:- " + dataMap.get(KEYWORD_ACTION)
										+ " Reason:- data is not ingested");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed update F");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;

					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + F");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}
				} else {
					System.out.println(dataMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
				}

				break;
			case KEYWORD_PURGE:
				try {
					String currentJson = getResponse(dataMap.get("Language"), dataMap.get("Json_Path"),
							ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
							ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
							PRICING_MODEL_URI);

					Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")),
							"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
									+ ", Action:- " + dataMap.get(KEYWORD_ACTION)
									+ " Reason:- Data is not purged successfully");
				} catch (NullPointerException e) {
					System.out.println("TestcaseGot failed Create + Purge");
					sb.append(e.getMessage() + "\n\t\n");
					throw e;
				} catch (Exception e) {
					System.out.println("TestcaseGot failed Purge");
					sb.append(e.getMessage() + "\n\t\n");
					throw e;
				}
				break;
			default:
				try {
					System.out.println(dataMap.get(KEYWORD_ACTION) + "This case is not handled yet");

				} catch (Exception e) {
					sb.append(e.getMessage() + "\n\t\n");
					throw e;
				}
			}
		} catch (Exception e) {
			sb.append(e.getMessage() + "\n\t\n");
			throw e;
		}
	}

	public void VerifyContentTypeData(Map<String, String> dataMap) {
		StringBuilder sb = new StringBuilder();

		try {
			switch (dataMap.get(KEYWORD_ACTION)) {
			case KEYWORD_CREATE:
				if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
					try {

						String currentJson = getResponse(dataMap.get("Status"), dataMap.get("Content_Type_ID"),
								dataMap.get("Current_Page"), dataMap.get("Size"), dataMap.get("isAvailable"),
								dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								ALL_CONTENT_URI);

						Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")), "TestCase ID :-"
								+ dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario") + ", Action:- "
								+ dataMap.get(KEYWORD_ACTION)
								+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed + Creat + B");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;
					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + B");
						sb.append(e.getMessage() + "\n\t\n");
					}

				} else if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Status"), dataMap.get("Content_Type_ID"),
								dataMap.get("Current_Page"), dataMap.get("Size"), dataMap.get("isAvailable"),
								dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								ALL_CONTENT_URI);

						Assert.assertTrue(currentJson.contains(dataMap.get("VerificationValue")),
								"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
										+ ", Action:-" + dataMap.get(KEYWORD_ACTION) + " Reason: is not ingested");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed Create + F");
						sb.append(e.getMessage() + "\n\t\n");

					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + F");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;

					}

				} else {
					System.out.println(dataMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
				}
				break;
			case KEYWORD_UPDATE:
				if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Status"), dataMap.get("Content_Type_ID"),
								dataMap.get("Current_Page"), dataMap.get("Size"), dataMap.get("isAvailable"),
								dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								ALL_CONTENT_URI);

						Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")), "TestCase ID :-"
								+ dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario") + ", Action:- "
								+ dataMap.get(KEYWORD_ACTION)
								+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed udpate + B");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;

					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + B");
						sb.append(e.getMessage() + "\n\t\n");
					}
				} else if (dataMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
					try {
						String currentJson = getResponse(dataMap.get("Status"), dataMap.get("Content_Type_ID"),
								dataMap.get("Current_Page"), dataMap.get("Size"), dataMap.get("isAvailable"),
								dataMap.get("Language"), dataMap.get("Json_Path"),
								ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
								ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
								ALL_CONTENT_URI);

						Assert.assertTrue(currentJson.contains(dataMap.get("VerificationValue")),
								"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
										+ ", Action:- " + dataMap.get(KEYWORD_ACTION)
										+ " Reason:- data is not ingested");
					} catch (AssertionError e) {
						System.out.println("TestcaseGot failed update F");
						sb.append(e.getMessage() + "\n\t\n");
					} catch (NullPointerException e) {
						System.out.println("TestcaseGot failed Create + F");
						sb.append(e.getMessage() + "\n\t\n");
						throw e;
					}
				} else {
					System.out.println(dataMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
				}

				break;
			case KEYWORD_PURGE:
				try {

					String currentJson = getResponse(dataMap.get("Status"), dataMap.get("Content_Type_ID"),
							dataMap.get("Current_Page"), dataMap.get("Size"), dataMap.get("isAvailable"),
							dataMap.get("Language"), dataMap.get("Json_Path"),
							ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
							ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), dataMap.get(KEYWORD_SCENARIO),
							ALL_CONTENT_URI);

					Assert.assertFalse(currentJson.contains(dataMap.get("VerificationValue")),
							"TestCase ID :-" + dataMap.get("TC-ID") + ", Scenario Name :-" + dataMap.get("Scenario")
									+ ", Action:- " + dataMap.get(KEYWORD_ACTION)
									+ " Reason:- Data is not purged successfully");
				} catch (AssertionError e) {
					System.out.println("TestcaseGot failed Purge");
					sb.append(e.getMessage() + "\n\t\n");
				} catch (NullPointerException e) {
					System.out.println("TestcaseGot failed + Creat + B");
					sb.append(e.getMessage() + "\n\t\n");
					throw e;

				}
				break;
			default:
				try {
					System.out.println(dataMap.get(KEYWORD_ACTION) + "This case is not handled yet");

				} catch (Exception e) {
					sb.append(e.getMessage() + "\n\t\n");
					throw e;
				}
			}
		} catch (Exception e) {
			sb.append(e.getMessage() + "\n\t\n");
			throw e;
		}

	}

	public void VerifySubscriptionData() {

	}

	public void VerifyPlayableContentTypeDataPurgeOperation() {

	}

	public void VerifyPlayableContentTypeDataCreateOperation() {

	}

	public void VerifyPlayableContentTypeDataUpdateOperation() {

	}

	public void VerifyContentCollectionTypeDataPurgeOperation() {

	}

	public void VerifyContentCollectionTypeDataCreateOperation() {

	}

	public void VerifyContentCollectionTypeDataUpdateOperation() {

	}

}
