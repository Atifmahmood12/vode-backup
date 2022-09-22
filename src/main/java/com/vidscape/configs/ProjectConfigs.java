package com.vidscape.configs;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class ProjectConfigs {
	
	public static final String INPUT_FILE_PATH = "./config.xml";
	public static String Server_APP_URI ="";
	public static String Client_APP_URI ="";
	public static String BANNER_API_BASE_PATH= "";
	public static String GROUP_API_BASE_PATH= "";
	public static String GROUP_DETAIL_API_BASE_PATH= "";
	public static String SUB_GROUP_API_BASE_PATH= "";
	public static String CONTENT_DETAIL_API_BASE_PATH= "";
	public static String CONTENT_AUTHORIZATION_API_BASE_PATH= "";
	public static String VOD_SEARCH_API_BASE_PATH= "";
	public static String TEST_DATA_FILE_PATH = "";
	public static String TEST_DATA_CSV_SOURCE_FOLDER_PATH = "";
	public static String AMQ_CONN_STRING = "";
	public static String QUEUE_NAME = "";
	public static String SERVER_API_ACCESSOR_USERNAME = "";
	public static String SERVER_API_ACCESSOR_PASSWORD = "";
	
	public  static void projectConfig(String[] args) {
		try {
			File fXmlFile = new File(INPUT_FILE_PATH);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			setServer_APP_URI(doc.getElementsByTagName("ServerAPPURI").item(0).getTextContent());
			setClient_APP_URI(doc.getElementsByTagName("ClientAPPURI").item(0).getTextContent());
			setBANNER_API_BASE_PATH(doc.getElementsByTagName("BannerAPIBasePath").item(0).getTextContent());
			setGROUP_API_BASE_PATH(doc.getElementsByTagName("GroupAPIBasePath").item(0).getTextContent());
			setGROUP_DETAIL_API_BASE_PATH(doc.getElementsByTagName("GroupDetailAPIBasePath").item(0).getTextContent());
			setSUB_GROUP_API_BASE_PATH(doc.getElementsByTagName("SubGroupAPIBasePath").item(0).getTextContent());
			setCONTENT_DETAIL_API_BASE_PATH(doc.getElementsByTagName("ContentDetailAPIBasePath").item(0).getTextContent());
			setCONTENT_AUTHORIZATION_API_BASE_PATH(doc.getElementsByTagName("ContentAuthorizationDetailAPIBasePath").item(0).getTextContent());
			setVOD_SEARCH_API_BASE_PATH(doc.getElementsByTagName("VODSearchAPIBasePath").item(0).getTextContent());
			setTEST_DATA_FILE_PATH(doc.getElementsByTagName("TestDataFilePath").item(0).getTextContent());
			setCONTENT_AUTHORIZATION_API_BASE_PATH(doc.getElementsByTagName("ContentAuthorizationDetailAPIBasePath").item(0).getTextContent());
			setSUB_GROUP_API_BASE_PATH(doc.getElementsByTagName("SubGroupAPIBasePath").item(0).getTextContent());
			setCONTENT_DETAIL_API_BASE_PATH(doc.getElementsByTagName("ContentDetailAPIBasePath").item(0).getTextContent());
			setVOD_SEARCH_API_BASE_PATH(doc.getElementsByTagName("VODSearchAPIBasePath").item(0).getTextContent());
			setGROUP_DETAIL_API_BASE_PATH(doc.getElementsByTagName("GroupDetailAPIBasePath").item(0).getTextContent());
			
			setTEST_DATA_CSV_SOURCE_FOLDER_PATH(doc.getElementsByTagName("TestDataCSVsourceFolderPath").item(0).getTextContent());
			setAMQ_CONN_STRING(doc.getElementsByTagName("AMQConnString").item(0).getTextContent());
			setQUEUE_NAME(doc.getElementsByTagName("QueueName").item(0).getTextContent());
			setSERVER_API_ACCESSOR_USERNAME(doc.getElementsByTagName("ServerAPIAccessorUserName").item(0).getTextContent());
			setSERVER_API_ACCESSOR_PASSWORD(doc.getElementsByTagName("ServerAPIAccessorPassword").item(0).getTextContent());
			
			
		}
		catch (ParserConfigurationException e){
			e.printStackTrace();
			/// This needs to change
			
		}
		catch (SAXException e){
			e.printStackTrace();
			// This needs to change 
			
		}
		catch (IOException e){
			e.printStackTrace();
			//			e.printStackTrace();
		}
	}

	public  String getInputFilePath() {
		return INPUT_FILE_PATH;
	}

	public static String getServer_APP_URI() {
		return Server_APP_URI;
	}

	public static void setServer_APP_URI(String server_APP_URL) {
		Server_APP_URI = server_APP_URL;
	}

	public static String getClient_APP_URI() {
		return Client_APP_URI;
	}

	public static void setClient_APP_URI(String client_APP_URL) {
		Client_APP_URI = client_APP_URL;
	}

	public static String getBANNER_API_BASE_PATH() {
		return BANNER_API_BASE_PATH;
	}

	public static void setBANNER_API_BASE_PATH(String bANNER_API_BASE_PATH) {
		BANNER_API_BASE_PATH = bANNER_API_BASE_PATH;
	}

	public static String getGROUP_API_BASE_PATH() {
		return GROUP_API_BASE_PATH;
	}

	public static void setGROUP_API_BASE_PATH(String gROUP_API_BASE_PATH) {
		GROUP_API_BASE_PATH = gROUP_API_BASE_PATH;
	}

	public static String getGROUP_DETAIL_API_BASE_PATH() {
		return GROUP_DETAIL_API_BASE_PATH;
	}

	public static void setGROUP_DETAIL_API_BASE_PATH(String gROUP_DETAIL_API_BASE_PATH) {
		GROUP_DETAIL_API_BASE_PATH = gROUP_DETAIL_API_BASE_PATH;
	}

	public static String getSUB_GROUP_API_BASE_PATH() {
		return SUB_GROUP_API_BASE_PATH;
	}

	public static void setSUB_GROUP_API_BASE_PATH(String sUB_GROUP_API_BASE_PATH) {
		SUB_GROUP_API_BASE_PATH = sUB_GROUP_API_BASE_PATH;
	}

	public static String getCONTENT_DETAIL_API_BASE_PATH() {
		return CONTENT_DETAIL_API_BASE_PATH;
	}

	public static void setCONTENT_DETAIL_API_BASE_PATH(String cONTENT_DETAIL_API_BASE_PATH) {
		CONTENT_DETAIL_API_BASE_PATH = cONTENT_DETAIL_API_BASE_PATH;
	}

	public static String getCONTENT_AUTHORIZATION_API_BASE_PATH() {
		return CONTENT_AUTHORIZATION_API_BASE_PATH;
	}

	public static void setCONTENT_AUTHORIZATION_API_BASE_PATH(String cONTENT_AUTHORIZATION_API_BASE_PATH) {
		CONTENT_AUTHORIZATION_API_BASE_PATH = cONTENT_AUTHORIZATION_API_BASE_PATH;
	}

	public static String getVOD_SEARCH_API_BASE_PATH() {
		return VOD_SEARCH_API_BASE_PATH;
	}

	public static void setVOD_SEARCH_API_BASE_PATH(String vOD_SEARCH_API_BASE_PATH) {
		VOD_SEARCH_API_BASE_PATH = vOD_SEARCH_API_BASE_PATH;
	}

	public static String getTEST_DATA_FILE_PATH() {
		return TEST_DATA_FILE_PATH;
	}

	public static void setTEST_DATA_FILE_PATH(String tEST_DATA_FILE_PATH) {
		TEST_DATA_FILE_PATH = tEST_DATA_FILE_PATH;
	}

	public static String getTEST_DATA_CSV_SOURCE_FOLDER_PATH() {
		return TEST_DATA_CSV_SOURCE_FOLDER_PATH;
	}

	public static void setTEST_DATA_CSV_SOURCE_FOLDER_PATH(String tEST_DATA_CSV_SOURCE_FOLDER_PATH) {
		TEST_DATA_CSV_SOURCE_FOLDER_PATH = tEST_DATA_CSV_SOURCE_FOLDER_PATH;
	}

	public static String getAMQ_CONN_STRING() {
		return AMQ_CONN_STRING;
	}

	public static void setAMQ_CONN_STRING(String aMQ_CONN_STRING) {
		AMQ_CONN_STRING = aMQ_CONN_STRING;
	}

	public static String getQUEUE_NAME() {
		return QUEUE_NAME;
	}

	public static void setQUEUE_NAME(String qUEUE_NAME) {
		QUEUE_NAME = qUEUE_NAME;
	}

	public static String getSERVER_API_ACCESSOR_USERNAME() {
		return SERVER_API_ACCESSOR_USERNAME;
	}

	public static void setSERVER_API_ACCESSOR_USERNAME(String sERVER_API_ACCESSOR_USERNAME) {
		SERVER_API_ACCESSOR_USERNAME = sERVER_API_ACCESSOR_USERNAME;
	}

	public static String getSERVER_API_ACCESSOR_PASSWORD() {
		return SERVER_API_ACCESSOR_PASSWORD;
	}

	public static void setSERVER_API_ACCESSOR_PASSWORD(String sERVER_API_ACCESSOR_PASSWORD) {
		SERVER_API_ACCESSOR_PASSWORD = sERVER_API_ACCESSOR_PASSWORD;
	}
			

}
