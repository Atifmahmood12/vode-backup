package com.vidscape.dataproviders;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import com.vidscape.configs.ProjectConfigs;
import com.vidscape.constants.APIEndPoints;
import com.vidscape.constants.DataSheetsConstants;
import com.vidscape.constants.MessageIngestorEndPoints;

public class IteratorProvider implements  DataSheetsConstants, MessageIngestorEndPoints{
	
	
	CSVReader csvR = new CSVReader();

	public IteratorProvider() {
		ProjectConfigs.projectConfig(null);
	}
	public Iterator CSVIterator(File fileName) {
		try {
			Map<String, String> gMap = null;
			StringBuilder sb = new StringBuilder();
			Iterator<Map<String, String>> iterator = null;
			File genreCSVFile = new File(
					ProjectConfigs.getTEST_DATA_CSV_SOURCE_FOLDER_PATH() + GENRE_CSV_FILE);
			return iterator = csvR.csvReader(genreCSVFile);
		} catch (Exception e) {
//			sb.append(e + " <<<File reading got failed>>>");
			return null;
			
		}
	
		
	}

}
