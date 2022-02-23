package com.sinensia.principal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import com.sinensia.classes.Broker;
import com.sinensia.classes.Stock;
import com.sinensia.logic.BrokerLogic;
import com.sinensia.utils.CsvRead;
import com.sinensia.utils.DateUtils;

public class Principal {

	public static void main(String[] args) {

		try (InputStreamReader input = new InputStreamReader(
				Principal.class.getClass().getResourceAsStream("/config.properties"))) {

			Properties properties = new Properties();
			properties.load(input);
			List<String[]> stra = CsvRead.read(properties.getProperty("csvmain"));
			ArrayList<Stock> stocklist = new ArrayList<Stock>();
			stra.remove(0);
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
			ArrayList<LocalDate> daystobuy = (ArrayList<LocalDate>) DateUtils.lastThursdayPlusOfMonth("2001-05-23",
					"2017-12-28");
			ArrayList<Stock> customstocklist = (ArrayList<Stock>) DateUtils.findAvailableDay(daystobuy, stocklist);

			Broker broker = new Broker(new BigDecimal(0), new BigDecimal(2), new BigDecimal(0));
			BrokerLogic.buyStocks(customstocklist, new BigDecimal(50), broker);
			BrokerLogic.sellStocks(LocalDate.parse("2017-12-28"), stocklist, broker);

		} catch (IOException e) {

			System.out.println("File not found");

		}

	}

}
