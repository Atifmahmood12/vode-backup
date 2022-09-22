package com.vidscape.main;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class XMLSuites {

	public static void xmlSuiteExecutor() {
		try {
			TestNG testNg = new TestNG();
			List<String> suites = Lists.newArrayList();
			
			suites.add("testng.xml");
			
			//suites.add("");
			testNg.setTestSuites(suites);
			testNg.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}