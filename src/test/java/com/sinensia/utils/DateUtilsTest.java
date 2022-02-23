package com.sinensia.utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.sinensia.classes.Stock;
import com.sinensia.principal.Principal;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class DateUtilsTest {

	@Test
	@Parameters({ "2022-01-01, 2022-12-31", })
	public void testLastFridayOfMonth(String firstdate, String lastdate) {

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

		ArrayList<LocalDate> comparefridays = (ArrayList<LocalDate>) DateUtils.lastThursdayPlusOfMonth(firstdate,
				lastdate);
		boolean confirm = true;
		for (int i = 0; i < lastfridays.size(); i++) {

			if (!lastfridays.get(i).isEqual(comparefridays.get(i))) {
				confirm = false;
			}
		}

		assertTrue(confirm);

	}

	@Test
	@Parameters({ "2012-01-27", "2012-02-24", "2012-03-30", "2012-04-27", "2012-06-01" })
	public void testFindAvailableDay(String datetofind) {
		try (InputStreamReader input = new InputStreamReader(
				Principal.class.getClass().getResourceAsStream("/config.properties"))) {
			List<String[]> stra = CsvRead.read("/stocks-2012.csv");
			ArrayList<Stock> stocklist = new ArrayList<Stock>();

			Properties properties = new Properties();
			properties.load(input);
			Locale loc = new Locale(properties.getProperty("language"), properties.getProperty("country"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getProperty("datepattern"), loc);

			for (String[] strings : stra) {
				for (String str : strings) {
					LocalDate date = LocalDate.parse(str.split(";")[0], formatter);
					BigDecimal clprice = new BigDecimal(str.split(";")[1]);
					BigDecimal opprice = new BigDecimal(str.split(";")[2]);

					Stock stock = new Stock(date, clprice, opprice);
					stocklist.add(stock);

				}

			}
			ArrayList<LocalDate> datesarray = new ArrayList<LocalDate>();
			datesarray.add(LocalDate.parse(datetofind));
			assertTrue(!DateUtils.findAvailableDay(datesarray, stocklist).isEmpty());

		} catch (IOException e) {
			assertTrue(false);
			System.out.println("File not found");

		}

	}

}
