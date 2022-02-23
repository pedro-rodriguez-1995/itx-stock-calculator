package com.sinensia.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvRead {

	/**
	 * Reads from csv file into a List
	 * 
	 * @param file the path of the file
	 * @return List<String[]> all the lines of the csv file
	 * @throws IOException when the file is not found
	 */
	public static List<String[]> read(String file) throws IOException {
		List<String[]> rows = null;

		InputStreamReader inputstreamreader = new InputStreamReader(CsvRead.class.getClass().getResourceAsStream(file));

		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

		try (CSVReader csvr = new CSVReader(bufferedreader);) {

			rows = csvr.readAll();

		} catch (IOException ex) {

			throw ex;
		}

		return rows;

	}
}
