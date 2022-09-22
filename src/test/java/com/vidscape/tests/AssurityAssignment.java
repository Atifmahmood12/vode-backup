package com.vidscape.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.response.Response;


@Guice
public class AssurityAssignment {
	
	public Response resResponse;
	public AssurityAssignment() {
		resResponse = null;
	}
	@Test
	@Parameters({ "apiURL", "version", "catalogueFlag", "categoryId" })
	public void CategoryDetailStatuCode(String APIURL, String Version, String catalogueFlag, String CategoryId ) {
		try {
			Response response = given().when().get(APIURL + Version + "/Categories/" + CategoryId + "/Details.json?catalogue=" + catalogueFlag);
			setResResponse(response);
			System.out.println("In first method"+getResResponse().contentType());
			Assert.assertEquals(getResResponse().getStatusCode(), 200);
		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Test
	public void VerifyCategoyAPIContentType() {
		try {
			System.out.println(getResResponse().contentType());
			Assert.assertEquals(getResResponse().getContentType(), "application/json");
		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}
	}
	public Response getResResponse() {
		return resResponse;
	}

	public void setResResponse(Response resResponse) {
		this.resResponse = resResponse;
	}

}
