package com.sinensia.principal;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.sinensia.classes.Broker;
import com.sinensia.classes.Stock;
import com.sinensia.utils.CsvRead;
import com.sinensia.utils.DateUtils;

public class Principal {

	public static void main(String[] args) {
		
		
		try {
			List<String[]> stra = CsvRead.read("src/main/resources/stocks-ITX.csv");
			ArrayList<Stock> stocklist= new ArrayList<Stock>();
			stra.remove(0);
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
			ArrayList<LocalDate> daystobuy = DateUtils.lastFridayOfMonth();
			ArrayList<Stock> customstocklist = DateUtils.findAvailableDay(daystobuy, stocklist);
			
			Broker brk = new Broker(new BigDecimal(0), new BigDecimal(2), new BigDecimal(0));
			brk.buyStocks(customstocklist, new BigDecimal(50));
			brk.sellStocks(LocalDate.parse("2017-12-28"), stocklist);
			
		} catch (IOException e) {
			
			System.out.println("File not found");
			
			
		}
		
		
		

	}

}
