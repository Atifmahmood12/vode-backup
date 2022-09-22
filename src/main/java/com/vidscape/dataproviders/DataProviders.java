package com.vidscape.dataproviders;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.DataSheetsConstants;

public class DataProviders implements DataSheetsConstants {
	public DataProviders() {
		ProjectConfigs.projectConfig(null);
	}

	@DataProvider(name = "Auth")
	public static Object[][] dataProviderMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {

			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}

	@DataProvider(name = "Banner")
	public static Object[][] dataProviderBannerMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), BANNER_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}

	@DataProvider(name = "Group")
	public static Object[][] dataProviderGroupMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), GROUP_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}

	@DataProvider(name = "ContentAuth")
	public static Object[][] dataProviderContentAuthMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), CONTENT_AUTH_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}

	@DataProvider(name = "SubGroup")
	public static Object[][] dataProviderSubGroupMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), SUB_GROUP_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}

	@DataProvider(name = "ContentDetail")
	public static Object[][] dataProviderContentDetailMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), CONTENT_DETAIL_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}

	@DataProvider(name = "Search")
	public static Object[][] dataProviderSearchMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), SEARCH_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}

	@DataProvider(name = "GroupDetail")
	public static Object[][] dataProviderGroupDetailMethod() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), GROUP_DETAIL_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}
	
	@DataProvider(name = "AMQTest")
	public static Object[][] AMQTestDataProvider() {
		ExcelUtils abc = new ExcelUtils();
		Object[][] xData = new Object[0][0];
		try {
			return xData = abc.getTableArray(ProjectConfigs.getTEST_DATA_FILE_PATH(), AMQ_TEST_DATA_SHEET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xData;
	}	
	
	


	@DataProvider(name = "GetMap")
	public static List<Map<String, String>> GetMapVal() {
		Map<String, String> dataMap = null;
		Iterator<Map<String, String>> iterator = null;

		try {
			return (List<Map<String, String>>) (dataMap = iterator.next());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<Map<String, String>>) dataMap;
	}

}
