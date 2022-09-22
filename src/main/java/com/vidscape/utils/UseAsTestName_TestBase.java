package com.vidscape.utils;

import org.testng.ITest;

public class UseAsTestName_TestBase implements ITest {
	 private String testInstanceName = "";
	@Override
	public String getTestName() {
		// TODO Auto-generated method stub
		return testInstanceName;
	}

	 private void setTestName(String anInstanceName) {
		  testInstanceName = anInstanceName;
		 }
}
