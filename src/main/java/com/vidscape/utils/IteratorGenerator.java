package com.vidscape.utils;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.DataSheetsConstants;
import com.vidscape.dataproviders.CSVReader;

public class IteratorGenerator implements DataSheetsConstants {

	StringBuilder sb = null;

	public IteratorGenerator() {
		ProjectConfigs.projectConfig(null);

	}

	static Iterator<Map<String, String>> sheetIterator = null;

	/*
	 * static Iterator<Map<String, String>> subscriptionIterator = null; static
	 * Iterator<Map<String, String>> brandIterator = null; static
	 * Iterator<Map<String, String>> seriesIterator = null; static
	 * Iterator<Map<String, String>> movieContentIterator = null; static
	 * Iterator<Map<String, String>> episodContentIterator = null;
	 */

	public static  Iterator<Map<String, String>> getIterator(String SheetName) throws Exception {
		CSVReader csvR = new CSVReader();
		try {
			File genreCSVFile = new File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SheetName);
			sheetIterator = csvR.csvReader(genreCSVFile);
			return sheetIterator;
		} catch (Exception e) {
			//sb.append(e + " <<<File reading got failed>>>");
			throw e;
		}
	}

	/*
	 * public Iterator<Map<String, String>> getSubscriptionIterator() {
	 * 
	 * CSVReader csvR = new CSVReader(); try { File genreCSVFile = new
	 * File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SERIES_CSV_FILE);
	 * subscriptionIterator = csvR.csvReader(genreCSVFile); return
	 * subscriptionIterator;
	 * 
	 * } catch (Exception e) { // sb.append(e + " <<<File reading got failed>>>");
	 * // throw e; } return subscriptionIterator;
	 * 
	 * }
	 * 
	 * public Iterator<Map<String, String>> getBrandIterator() { CSVReader csvR =
	 * new CSVReader(); try { File genreCSVFile = new
	 * File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SERIES_CSV_FILE);
	 * brandIterator = csvR.csvReader(genreCSVFile); return brandIterator;
	 * 
	 * } catch (Exception e) { // sb.append(e + " <<<File reading got failed>>>");
	 * // throw e; } return brandIterator; }
	 * 
	 * public Iterator<Map<String, String>> getSeriesIterator() {
	 * 
	 * CSVReader csvR = new CSVReader(); try { File genreCSVFile = new
	 * File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SERIES_CSV_FILE);
	 * seriesIterator = csvR.csvReader(genreCSVFile); return seriesIterator;
	 * 
	 * } catch (Exception e) { // sb.append(e + " <<<File reading got failed>>>");
	 * // throw e; } return seriesIterator;
	 * 
	 * }
	 * 
	 * public Iterator<Map<String, String>> getMovieContentIterator() {
	 * 
	 * CSVReader csvR = new CSVReader(); try { File genreCSVFile = new
	 * File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SERIES_CSV_FILE);
	 * movieContentIterator = csvR.csvReader(genreCSVFile); return
	 * movieContentIterator;
	 * 
	 * } catch (Exception e) { // sb.append(e + " <<<File reading got failed>>>");
	 * // throw e; } return movieContentIterator; }
	 * 
	 * public Iterator<Map<String, String>> getEpisodContentIterator() {
	 * 
	 * CSVReader csvR = new CSVReader(); try { File genreCSVFile = new
	 * File(ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + SERIES_CSV_FILE);
	 * episodContentIterator = csvR.csvReader(genreCSVFile); return
	 * episodContentIterator;
	 * 
	 * } catch (Exception e) { // sb.append(e + " <<<File reading got failed>>>");
	 * // throw e; } return episodContentIterator;
	 * 
	 * }
	 */

}
