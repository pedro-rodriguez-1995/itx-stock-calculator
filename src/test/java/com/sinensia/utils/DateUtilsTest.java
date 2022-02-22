package com.sinensia.utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;


import com.sinensia.classes.Stock;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
@RunWith(JUnitParamsRunner.class)
public class DateUtilsTest {

	@Test
	@Parameters({ 
	      "2022-01-01, 2022-12-31",
	      })
	public void testLastFridayOfMonth(String firstdate,String lastdate) {
		
		ArrayList<LocalDate> lastfridays = new ArrayList<LocalDate>();
		lastfridays.add(LocalDate.parse("2022-01-27").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-02-24").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-03-31").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-04-28").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-05-26").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-06-30").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-07-28").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-08-25").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-09-29").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-10-27").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-11-24").plusDays(1));
		lastfridays.add(LocalDate.parse("2022-12-29").plusDays(1));
		
		
		ArrayList<LocalDate> comparefridays =DateUtils.lastThursdayPlusOfMonth(firstdate, lastdate);
		boolean confirm=true;
		for (int i = 0; i < lastfridays.size(); i++) {
			
			if(!lastfridays.get(i).isEqual(comparefridays.get(i))) {
				confirm=false;
			}
		}
		
		
		assertTrue(confirm);
		
		
		
	}

	@Test
	@Parameters({ 
	      "2012-01-27", 
	      "2012-02-24",
	      "2012-03-30",
	      "2012-04-27",
	      "2012-06-01"
	      })
	public void testFindAvailableDay(String datetofind) {
		try {
			List<String[]> stra = CsvRead.read("src/test/resources/stocks-2012.csv");
			ArrayList<Stock> stocklist= new ArrayList<Stock>();
		
			Locale loc = new Locale("es", "ES");
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", loc);
			
			for (String[] strings : stra) {
				for (String str : strings) {
					LocalDate date = LocalDate.parse(str.split(";")[0],formatter);
					BigDecimal clprice = new BigDecimal(str.split(";")[1]);
					BigDecimal opprice = new BigDecimal(str.split(";")[2]);
					
					Stock stock = new Stock(date, clprice, opprice);
					stocklist.add(stock);
					
				}
						
					
			}
			ArrayList<LocalDate> datesarray= new ArrayList<LocalDate>();
			datesarray.add(LocalDate.parse(datetofind));
			assertTrue(!DateUtils.findAvailableDay(datesarray, stocklist).isEmpty());
			
			
			
			
		} catch (IOException e) {
			assertTrue(false);
			System.out.println("File not found");
			
			
		}
		
		
		
		
	}

}
