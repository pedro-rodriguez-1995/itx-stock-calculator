package com.sinensia.logic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sinensia.classes.Broker;
import com.sinensia.classes.Stock;

public class BrokerLogic {
	/**
	 * Buys the stocks given a List of stocks and an investment per stock
	 * 
	 * @param stocklist  List of stocks
	 * @param investment investment per stock
	 * @return the total stocks purchased
	 */
	public static BigDecimal buyStocks(ArrayList<Stock> stocklist, BigDecimal investment, Broker broker) {

		for (Stock stock : stocklist) {
			BigDecimal commissionsub = investment.multiply(broker.getCommission()).divide(new BigDecimal(100));
			BigDecimal finvestment = investment.subtract(commissionsub);
			broker.setTotalcommission(broker.getTotalcommission().add(commissionsub));
			BigDecimal stocksbought = finvestment.divide(stock.getOpprice(), 3, BigDecimal.ROUND_HALF_EVEN);
			broker.setTotalstocks(broker.getTotalstocks().add(stocksbought));

			System.out.println(stock.getDate() + " Stock bought at opening price of: " + stock.getOpprice().toString()

					+ " EUR for a total of: " + stocksbought.toString() + " stocks Total stocks in this account: "
					+ broker.getTotalstocks().toString() + " Total commission: "
					+ broker.getTotalcommission().toString() + " EUR");

		}

		return broker.getTotalstocks();
	}

	/**
	 * Sells all the stocks accumulated by the broker given a date to find the stock
	 * closing price
	 * 
	 * 
	 * @param date      the date to find the stock closing price
	 * @param stocklist the list of all the stocks
	 * @return the total amount of money gained by selling the stocks
	 */
	public static BigDecimal sellStocks(LocalDate date, ArrayList<Stock> stocklist, Broker broker) {
		BigDecimal benefit = new BigDecimal(0);
		for (Stock stock : stocklist) {

			if (stock.getDate().equals(date)) {

				benefit = broker.getTotalstocks().multiply(stock.getClprice()).setScale(3, BigDecimal.ROUND_HALF_EVEN);

				System.out.println("You've sold all your: " + broker.getTotalstocks() + " stocks at a price of: "
						+ stock.getClprice() + " EUR per stock" + " for a total of: " + benefit + " EUR");

				broker.setTotalstocks(new BigDecimal(0));

			}

		}
		return benefit;
	}

}
