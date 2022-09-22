package com.vidscape.tests;

import java.util.ArrayList;
import java.util.Map;

import org.jasypt.web.pbeconfig.WebPBEInitializationContextListener;
import org.testng.Assert;
import org.testng.annotations.Test;

/*import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;*/

import com.vidscape.constants.APIEndPoints;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HelperTestMethods implements APIEndPoints {

	@Test
	public void CheckStatus(Response res, int statusCode, String TestcaseName) {
		try {
			Assert.assertEquals(res.getStatusCode(), statusCode);
		} catch (AssertionError e) {
			throw e;
		}
	}

	public void CheckResponseContentType(Response res, String responseContentType, String TestcaseName) {
		try {
			Assert.assertEquals(res.contentType(), responseContentType,
					"ContentType Check Failed for : " + TestcaseName);
		} catch (AssertionError e) {
			throw e;
		}
	}

	public void CheckResponseTime(Response res, String TestcaseName) {
		try {
			Assert.assertEquals(res.contentType(), "", "ResponseTime Failed : " + TestcaseName);
		} catch (AssertionError e) {
			throw e;
		}
	}

	public String ConvertResponseBodytoString(Response res) {
		try {
			return res.then().extract().response().body().asString();
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Map<String, String>> GetSelectedTagDataFromJsonResponse(Response res, String JsonStringSplitter) {
		try {
			return JsonPath.from(ConvertResponseBodytoString(res)).get(JsonStringSplitter);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int GetDirectSelectedTagDataFromJsonResponseInInteger(Response res, String JsonStringSplitter) {
		try {
			return JsonPath.from(ConvertResponseBodytoString(res)).get(JsonStringSplitter);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String GetDirectSelectedTagDataFromJsonResponseInString(Response res, String JsonStringSplitter) {
		try {
			return JsonPath.from(ConvertResponseBodytoString(res)).get(JsonStringSplitter);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String GetThematicGroupID(String Json, String ThematicGroupName){
		
		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
		
	}
}
