package com.vidscape.IngestMessageTest;

import java.io.File;
import java.util.Iterator;
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
import com.vidscape.pojo.genre.GenreRoot;
import com.vidscape.utils.IteratorGenerator;
import com.vidscape.utils.MessageIngester;
import com.vidscape.utils.TestMethodsUtil;
import com.vidscape.utils.Utils;

public class GenreTest implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {
	CSVReader csvR = new CSVReader();

	public GenreTest() {
		ProjectConfigs.projectConfig(null);
	}

	@Test
	public void GenreTests() throws Exception {
			try {
			GenreRoot gRoot = null;
			Translation title = new Translation();
			
			MessageIngester mIngester = new MessageIngester();
			
			TestMethodsUtil testMethodUtil = new TestMethodsUtil();
			
			Map<String, String> gMap = null;
			StringBuilder sb = new StringBuilder();
			Iterator<Map<String, String>> iterator = null;

			// ===============Read CSV data================================
			try {
				File genreCSVFile = new File(
						ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + GENRE_CSV_FILE);
				iterator = csvR.csvReader(genreCSVFile);
			} catch (Exception e) {
				sb.append(e + " <<<File reading got failed>>>");
				throw e;
			}

			try {
				while (iterator.hasNext()) {
					// ===============Build JSon================================
					try {
						gRoot = new GenreRoot();
						gMap = iterator.next();
						gRoot.setId(gMap.get("id"));
						title.setEng(gMap.get("titleENG"));
						title.setVi(gMap.get("titleVI"));
						gRoot.setTitle(title);

					} catch (NullPointerException e) {
						sb.append(e + "<<< One or more keys are invalid or data is invalid>>>");
					} catch (Exception e) {
						sb.append(e + "<<< POJO building got failed>>>");
					}

					// ===============IngestData================================
					try {
						ObjectMapper mapper1 = new ObjectMapper();
						mIngester.sendMessageToQueu(
								mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(gRoot).toString(),
								ProjectConfigs.getAMQ_CONN_STRING(), GENRE_ENTITY_TYPE, gMap.get("Action"),
								ProjectConfigs.getQUEUE_NAME());
					} catch (Exception e) {
						sb.append(e + "<<< Data ingestion got failed>>>");
					}
					// ===============TestCases================================

					try {
						switch (gMap.get(KEYWORD_ACTION)) {
						case KEYWORD_CREATE:
							if (gMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(gMap.get("Language"),
											gMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											gMap.get(KEYWORD_SCENARIO), GENRE_URI);
									Assert.assertFalse(currentJson.contains(gMap.get("VerificationValue")),
											"TestCase ID :-" + gMap.get("TC-ID") + ", Scenario Name :-"
													+ gMap.get("Scenario") + ", Action:- " + gMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed + Creat + B");
									sb.append(e.getMessage() + "\n\t\n");
								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else if (gMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(gMap.get("Language"),
											gMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											gMap.get(KEYWORD_SCENARIO), GENRE_URI);

									Assert.assertTrue(currentJson.contains(gMap.get("VerificationValue")),
											"TestCase ID :-" + gMap.get("TC-ID") + ", Scenario Name :-"
													+ gMap.get("Scenario") + ", Action:-" + gMap.get(KEYWORD_ACTION)
													+ " Reason: is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}
							} else {
								System.out.println(gMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}
							break;
						case KEYWORD_UPDATE:
							if (gMap.get(KEYWORD_SCENARIO).equals(KEYWORD_BOUNDARY_VALUE_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(gMap.get("Language"),
											gMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											gMap.get(KEYWORD_SCENARIO), GENRE_URI);
									Assert.assertFalse(currentJson.contains(gMap.get("VerificationValue")),
											"TestCase ID :-" + gMap.get("TC-ID") + ", Scenario Name :-"
													+ gMap.get("Scenario") + ", Action:- " + gMap.get(KEYWORD_ACTION)
													+ " Reason:- is ingested into the system which is a wrong behaviore for this testscase");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed udpate + B");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + B");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else if (gMap.get(KEYWORD_SCENARIO).equals(KEYWORD_FUNCTIONAL_CHECK)) {
								try {
									String currentJson = testMethodUtil.getResponse(gMap.get("Language"),
											gMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
											ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(),
											gMap.get(KEYWORD_SCENARIO), GENRE_URI);
									Assert.assertTrue(currentJson.contains(gMap.get("VerificationValue")),
											"TestCase ID :-" + gMap.get("TC-ID") + ", Scenario Name :-"
													+ gMap.get("Scenario") + ", Action:- " + gMap.get(KEYWORD_ACTION)
													+ " Reason:- data is not ingested");
								} catch (AssertionError e) {
									System.out.println("TestcaseGot failed update F");
									sb.append(e.getMessage() + "\n\t\n");

								} catch (NullPointerException e) {
									System.out.println("TestcaseGot failed Create + F");
									sb.append(e.getMessage() + "\n\t\n");
								}

							} else {
								System.out.println(gMap.get(KEYWORD_SCENARIO) + "is still not handled by this script");
							}
							break;
						case KEYWORD_PURGE:
							try {
								String currentJson = testMethodUtil.getResponse(gMap.get("Language"),
										gMap.get("Json_Path"), ProjectConfigs.getSERVER_API_ACCESSOR_USERNAME(),
										ProjectConfigs.getSERVER_API_ACCESSOR_PASSWORD(), gMap.get(KEYWORD_SCENARIO),
										GENRE_URI);

								Assert.assertFalse(currentJson.contains(gMap.get("VerificationValue")),
										"TestCase ID :-" + gMap.get("TC-ID") + ", Scenario Name :-"
												+ gMap.get("Scenario") + ", Action:- " + gMap.get(KEYWORD_ACTION)
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
								System.out.println(gMap.get(KEYWORD_ACTION) + "This case is not handled yet");

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
	 * (Exception e) { e.getCause(); } return null; }
	 * 
	 * public String verifyCreateGenre(String genreID, String genreViName, String
	 * genreEngName, String language, String jsonPath, String userName, String
	 * passWord, String scenarioName) { try { Response genreResponse =
	 * given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName,
	 * passWord)).when() .get(ProjectConfigs.getServer_APP_URI() + GENRE_URI +
	 * language); String actualValue =
	 * JsonPath.from(genreResponse.getBody().asString()).getString(jsonPath); return
	 * actualValue; } catch (Exception e) { e.getCause(); } return null;
	 * 
	 * }
	 * 
	 * public String verifyUpdateGenre(String genreID, String genreViName, String
	 * genreEngName, String language, String jsonPath, String userName, String
	 * passWord, String scenarioName) { try { Response genreResponse =
	 * given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName,
	 * passWord)).when() .get(ProjectConfigs.getServer_APP_URI() + GENRE_URI +
	 * language); String actualValue =
	 * JsonPath.from(genreResponse.getBody().asString()).getString(jsonPath); return
	 * actualValue; } catch (Exception e) { e.getCause(); } return null;
	 * 
	 * }
	 * 
	 * public String verifyPurgeGenre(String genreID, String genreViName, String
	 * genreEngName, String language, String jsonPath, String userName, String
	 * passWord, String scenarioName) { try { Response genreResponse =
	 * given().header(AUTH_TOKEN_HEADER_FIELD, getAuthToken(userName,
	 * passWord)).when() .get(ProjectConfigs.getServer_APP_URI() + GENRE_URI +
	 * language); String actualValue =
	 * JsonPath.from(genreResponse.getBody().asString()).getString(jsonPath); return
	 * actualValue; } catch (Exception e) { e.getCause(); } return null; }
	 * 
	 * public String verifyBoundaryValueCheckForGenre(String genreID, String
	 * genreViName, String genreEngName, String language, String jsonPath, String
	 * userName, String passWord, String scenarioName) { try { Response
	 * genreResponse = given().header(AUTH_TOKEN_HEADER_FIELD,
	 * getAuthToken(userName, passWord)).when()
	 * .get(ProjectConfigs.getServer_APP_URI() + GENRE_URI + language); String
	 * actualValue =
	 * JsonPath.from(genreResponse.getBody().asString()).getString(jsonPath); return
	 * actualValue; } catch (Exception e) { e.getCause(); } return null;
	 * 
	 * }
	 */
}
