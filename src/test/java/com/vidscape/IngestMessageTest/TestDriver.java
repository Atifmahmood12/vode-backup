package com.vidscape.IngestMessageTest;

import java.util.Iterator;
import java.util.Map;

import javax.jms.JMSException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.constants.DataSheetsConstants;
import com.vidscape.constants.MessageIngestorEndPoints;
import com.vidscape.dataproviders.DataProviders;
import com.vidscape.pojo.PojoBuilder;
import com.vidscape.utils.IteratorGenerator;
import com.vidscape.utils.MessageIngester;
import com.vidscape.utils.TestMethodsUtil;
import com.vidscape.utils.Utils;

public class TestDriver implements APIEndPoints, DataSheetsConstants, MessageIngestorEndPoints {

	public TestDriver() {
		ProjectConfigs.projectConfig(null);
	}

	@Test(dataProvider = "AMQTest", dataProviderClass = DataProviders.class)
	public void TestsDriver(String TCID, String dataTCID, String sheetName, String entityTypeName,
			String VerificatinType, String skipIngesstion) throws Exception {

		StringBuilder sb = new StringBuilder();
		try {
			try {
				Map<String, String> currentTCMap = null;
				Utils utils = new Utils();
				TestMethodsUtil tMethod = new TestMethodsUtil();
				String SheetName1 = sheetName;
				Iterator<Map<String, String>> genreIterator = IteratorGenerator.getIterator(SheetName1);// Get the sheet
				currentTCMap = utils.ExtractDataFromIterator(genreIterator, dataTCID); // Get the target test case
																						// data
				// Ingest Data ===============
				if (skipIngesstion.equals("N")) {
					MessageIngester mIngester = new MessageIngester();
					PojoBuilder pbuilder = new PojoBuilder();

					ObjectMapper mapper1 = new ObjectMapper();
					try {
						switch (sheetName) {
						case GENRE_CSV_FILE:
							mIngester.sendMessageToQueu(
									mapper1.writerWithDefaultPrettyPrinter()
											.writeValueAsString(pbuilder.buildGenrePojo(currentTCMap)).toString(),
									ProjectConfigs.getAMQ_CONN_STRING(), entityTypeName, currentTCMap.get("Action"),
									ProjectConfigs.getQUEUE_NAME());
							break;
						case SUBSCRIBER_CSV_FILE:
							mIngester.sendMessageToQueu(mapper1.writerWithDefaultPrettyPrinter()
									.writeValueAsString(pbuilder.buildSubscriptionPojo(currentTCMap)).toString(),
									ProjectConfigs.getAMQ_CONN_STRING(), entityTypeName, currentTCMap.get("Action"),
									ProjectConfigs.getQUEUE_NAME());
							break;
						case BRAND_CSV_FILE:
							mIngester.sendMessageToQueu(
									mapper1.writerWithDefaultPrettyPrinter()
											.writeValueAsString(pbuilder.buildBrandPojo(currentTCMap)).toString(),
									ProjectConfigs.getAMQ_CONN_STRING(), entityTypeName, currentTCMap.get("Action"),
									ProjectConfigs.getQUEUE_NAME());
							break;
						case SERIES_CSV_FILE:
							mIngester.sendMessageToQueu(
									mapper1.writerWithDefaultPrettyPrinter()
											.writeValueAsString(pbuilder.buildSeriesPojo(currentTCMap)).toString(),
									ProjectConfigs.getAMQ_CONN_STRING(), entityTypeName, currentTCMap.get("Action"),
									ProjectConfigs.getQUEUE_NAME());
							break;
						case MOVIE_CONTENT_FILE:
							mIngester.sendMessageToQueu(
									mapper1.writerWithDefaultPrettyPrinter()
											.writeValueAsString(pbuilder.buildMovieContentPojo(currentTCMap)).toString(),
									ProjectConfigs.getAMQ_CONN_STRING(), entityTypeName, currentTCMap.get("Action"),
									ProjectConfigs.getQUEUE_NAME());
							break;
						case EPISODE_CONTENT_FILE:
							mIngester.sendMessageToQueu(
									mapper1.writerWithDefaultPrettyPrinter()
											.writeValueAsString(pbuilder.buildEpisodeContentPojo(currentTCMap)).toString(),
									ProjectConfigs.getAMQ_CONN_STRING(), entityTypeName, currentTCMap.get("Action"),
									ProjectConfigs.getQUEUE_NAME());
							break;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// Data Verification
				if (VerificatinType.equals("GenreTypeData")) {

					tMethod.VerifyGenreData(currentTCMap);

				} else if (VerificatinType.equals("SubTypeData")) {

					tMethod.VerifySubscriptionData(currentTCMap);

				} else if (VerificatinType.equals("ContentTypeData")) {

					tMethod.VerifyContentTypeData(currentTCMap);
				}
			} catch (NullPointerException e) {
				sb.append(e + "Null pointer");
			} catch (JMSException e) {
				sb.append(e + "   JMS");
			}
		} catch (Exception e) {
			sb.append(e + "  Genreal");
			e.getMessage();
		}
		try {
			Assert.assertTrue(sb.toString().isEmpty(), sb.toString());
		} catch (AssertionError e) {
			System.out.println(sb.toString());
			Assert.fail(sb.toString());
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
