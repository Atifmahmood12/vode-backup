package com.vidscape.utils;

import org.testng.Assert;

public class AssertionsUtil {
	
	public void AssertDataExists(String Json,String verificationVal,String TCID, String ScenarioName, String ActionName) {
		
		Assert.assertTrue(Json.contains(verificationVal),
				"TestCase ID :- " + TCID+ ", Scenario Name :- "
						+ ScenarioName + ", Action:- " + ActionName
						+ " Reason:- Data is not ingested into the system which is wrong behavior");

	}

	public void AssertDataNotExists(String Json,String verificationVal,String TCID, String ScenarioName, String ActionName) {
		Assert.assertFalse(Json.contains(verificationVal),
				"TestCase ID :- " + TCID+ ", Scenario Name :- "
						+ ScenarioName + ", Action:- " + ActionName
						+ " Reason:- Data is ingested into the system which is wrong behavior");

	}

	/*public void AssertSubscriptionExists() {
		Assert.assertTrue(condition);

	}

	public void AssertSubscriptionNotExists() {

		Assert.assertFalse(condition);
	}

	public void AssertPlayableContentExists() {
		Assert.assertTrue(condition);

	}

	public void AssertPlayableContentNotExists() {
		Assert.assertFalse(condition);

	}

	public void AssertCollectionContentExists() {
		
		Assert.assertTrue(condition);

	}

	public void AssertCollectionContentNotExists() {
		Assert.assertFalse(condition);

	}
	*/

}
