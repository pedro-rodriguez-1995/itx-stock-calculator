package com.sinensia.utils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvRead {

	/**
	 * Reads from csv file into a List
	 * 
	 * @param file
	 * @return List<String[]>
	 * @throws IOException
	 */
	public static List<String[]> read(String file) throws IOException {
		List<String[]> rows = null;
		try {

			Reader reader = Files.newBufferedReader(Paths.get(file));
			CSVReader csvr = new CSVReader(reader);
			rows = csvr.readAll();
			csvr.close();
		} catch (IOException ex) {

			throw ex;
		}
		
		return rows;
		
	}
}
