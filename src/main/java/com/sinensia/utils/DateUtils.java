package com.sinensia.utils;

import static java.time.DayOfWeek.THURSDAY;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import com.sinensia.classes.Stock;

public class DateUtils {
	
	/**
	 * Finds the last thursday plus 1 day of each month of a given interval of dates
	 * 
	 * @param firstdate the first date of the interval
	 * @param lastdate the last date of the interval
	 * @return all the days found
	 */
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
	
	/**
	 * Finds stocks available to buy stocks given a list of dates
	 * 
	 * @param daystobuy the list of dates
	 * @param stocklist the list with all the stocks
	 * @return all the stocks available to buy
	 */
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
