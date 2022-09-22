package com.vidscape.dataproviders;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVReader {
	public Iterator<Map<String, String>> csvReader(File file) throws IOException {
		try {
			Iterator<Map<String, String>> iterator = new CsvMapper().readerFor(Map.class)
					.with(CsvSchema.emptySchema().withHeader()).readValues(file);
			return iterator;
		} catch (IOException e) {
			System.out.println("CSV file is not loaded properly");
			throw e;
		}
	}
}
