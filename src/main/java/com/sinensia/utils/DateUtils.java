package com.sinensia.utils;

import static java.time.DayOfWeek.THURSDAY;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import com.sinensia.classes.Stock;

public class DateUtils {
	
	public static ArrayList<LocalDate> lastThursdayPlusOfMonth(String firstdate, String lastdate){
		ArrayList<LocalDate> daystobuy = new ArrayList<LocalDate>();
		
		
		
		
		
		for (LocalDate date = LocalDate.parse(firstdate); date
				.isBefore(LocalDate.parse(lastdate)); date = date.plusMonths(1)) {

			LocalDate daytobuy = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth())
					.with(lastInMonth(THURSDAY)).plusDays(1);

			daystobuy.add(daytobuy);

		}
		
		
		return daystobuy;
	}
	
	public static ArrayList<Stock> findAvailableDay(ArrayList<LocalDate> daystobuy ,ArrayList<Stock> stocklist){
		
		Collections.reverse(stocklist);
		ArrayList<Stock> customstocklist = new ArrayList<Stock>();
		for (LocalDate daytobuy : daystobuy) {
			int i = 0;
			boolean validday = false;
			while (!validday && i <= 30) {
				for (Stock stock : stocklist) {

					if (daytobuy.isEqual(stock.getDate())) {
						validday = true;
						customstocklist.add(stock);

					}
				}
				if (!validday) {
					daytobuy = daytobuy.plusDays(1);
				}
				i++;
			}

		}
		return customstocklist;
	}
	

}
