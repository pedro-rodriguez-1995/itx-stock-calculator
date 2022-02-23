package com.sinensia.classes;

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

import com.sinensia.logic.BrokerLogic;
import com.sinensia.principal.Principal;
import com.sinensia.utils.CsvRead;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class BrokerTest {

	@Test
	@Parameters({ "/stocks-2001.csv, 1960.625", "/stocks-2012.csv, 790.539",
			"/stocks-2003.csv, 3063.011", "/stocks-2014.csv, 567.329", "/stocks-2005.csv, 2713.425" })
	public void testBuyStocks(String file, String totalstocks) {

		try (InputStreamReader input = new InputStreamReader(
				Principal.class.getClass().getResourceAsStream("/config.properties"))) {
			List<String[]> stra = CsvRead.read(file);
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

			Broker broker = new Broker(new BigDecimal(0), new BigDecimal(2), new BigDecimal(0));

			assertTrue(BrokerLogic.buyStocks(stocklist, new BigDecimal(50), broker)
					.compareTo(new BigDecimal(totalstocks)) == 0);

		} catch (IOException e) {
			assertTrue(false);
			System.out.println("File not found");

		}

	}

	@Test
	@Parameters({ "/stocks-2001.csv, 8395.396, 2001-12-28", "/stocks-2012.csv, 16680.373, 2012-12-31",
			"/stocks-2003.csv, 9862.895, 2003-12-30", "/stocks-2014.csv, 13448.534, 2014-12-31",
			"/stocks-2005.csv, 14950.972, 2005-12-30" })
	public void testSellStocks(String file, String benefit, String selldate) {

		try (InputStreamReader input = new InputStreamReader(
				Principal.class.getClass().getResourceAsStream("/config.properties"))) {
			List<String[]> stra = CsvRead.read(file);
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

			Broker broker = new Broker(new BigDecimal(0), new BigDecimal(2), new BigDecimal(0));
			BrokerLogic.buyStocks(stocklist, new BigDecimal(50), broker);
			assertTrue(BrokerLogic.sellStocks(LocalDate.parse(selldate), stocklist, broker)
					.compareTo(new BigDecimal(benefit)) == 0);

		} catch (IOException e) {
			assertTrue(false);
			System.out.println("File not found");

		}

	}

}
