package com.vidscape.tests;

import static io.restassured.RestAssured.given;

//import static com.jayway.restassured.RestAssured.given;


import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.constants.DataSheetsConstants;
import com.vidscape.dataproviders.DataProviders;

import io.restassured.response.Response;

@Guice
public class Authentication implements APIEndPoints, DataSheetsConstants {

	public Authentication() {
		ProjectConfigs.projectConfig(null);
	}
	
	public String AuthKey = "";
	SoftAssert softAssert = new SoftAssert();
	
	@Test(priority = 1, dataProvider = "Auth", dataProviderClass = DataProviders.class)
	public void AuthStatusCode(String a1, String a2, String a3, String a4, String a5)  {
		
		System.out.println(ProjectConfigs.getServer_APP_URI()+AUTH_API);
		try {
			Response resAuth =
					given().header( AUTH_API_USERNAME, a2)
					.given().header(AUTH_API_PASSWORD, a3)
					.when()
					.post(ProjectConfigs.getServer_APP_URI()+AUTH_API);
			System.out.println("The results is : - "+resAuth.getStatusCode());
			System.out.println("Thr response is "+ resAuth.body());
			System.out.println("Thr response is "+ resAuth.thenReturn().asString());	
			
			Assert.assertEquals(resAuth.getStatusCode(), Integer.parseInt(a5), a1);
			
		} catch (AssertionError e) {
			throw e;
		}
	}

	// Verify Response Content Type.
	@Test(priority = 2, dataProvider = "Auth", dataProviderClass = DataProviders.class)
	public void AuthResponseContentType(String a1, String a2, String a3, String a4, String a5) {
	
		try {
			Response resAuth =
					given().header(AUTH_API_USERNAME, a2)
					.given().header(AUTH_API_PASSWORD, a3).when()
					.post(ProjectConfigs.getServer_APP_URI()+AUTH_API);
			
			System.out.println(resAuth);
			Assert.assertEquals(resAuth.getStatusCode(), Integer.parseInt(a5), a1);
			softAssert.assertEquals(resAuth.getContentType(), a4, "Status Code Assertion Pass for " + a1);
		} catch (AssertionError e) {
			e.getMessage();
		}
	}

	// Verify AuthToken.
	@Test(priority = 3, dataProvider = "Auth", dataProviderClass = DataProviders.class)
	public void AuthTokenVerification(String a1, String a2, String a3, String a4, String a5) {
		try {
			Response resAuth =
					given().header(AUTH_API_USERNAME, a2)
					.given().header(AUTH_API_PASSWORD, a3).when()
					.post(ProjectConfigs.getServer_APP_URI()+AUTH_API);
			Assert.assertEquals(resAuth.getStatusCode(), Integer.parseInt(a5), a1);
			softAssert.assertNotNull(resAuth.then().extract().jsonPath().getString("token"));
		} catch (Exception e) {
			e.getMessage();
		}

	}

}

