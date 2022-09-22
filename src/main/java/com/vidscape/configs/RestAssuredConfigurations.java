package com.vidscape.configs;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;



public class RestAssuredConfigurations {
	
	@BeforeSuite()
	public void configure () {
		ProjectConfigs.projectConfig(null);
		RestAssured.baseURI = ProjectConfigs.getClient_APP_URI();
		//RestAssured.port = 8080;
		RestAssured.basePath = "";
		
		
	}

}
