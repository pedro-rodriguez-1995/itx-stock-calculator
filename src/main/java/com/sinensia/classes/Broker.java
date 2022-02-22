package com.sinensia.classes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Broker {
	private BigDecimal totalstocks;
	private BigDecimal commission;
	private BigDecimal totalcommission;

	public Broker(BigDecimal totalstocks, BigDecimal commission, BigDecimal totalcommission) {
		super();
		this.totalstocks = totalstocks;
		this.commission = commission;
		this.totalcommission = totalcommission;
	}

	public BigDecimal getTotalstocks() {
		return totalstocks;
	}

	public void setTotalstocks(BigDecimal totalstocks) {
		this.totalstocks = totalstocks;
	}

	public BigDecimal getTotalcommission() {
		return totalcommission;
	}

	public void setTotalcommission(BigDecimal totalcommission) {
		this.totalcommission = totalcommission;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	/**
	 * Buys the stocks given a List of stocks and an investment per stock
	 * 
	 * @param stocklist List of stocks
	 * @param investment investment per stock
	 * @return the total stocks purchased
	 */
	public BigDecimal buyStocks(ArrayList<Stock> stocklist, BigDecimal investment) {

		for (Stock stock : stocklist) {
			BigDecimal commissionsub = investment.multiply(commission).divide(new BigDecimal(100));
			BigDecimal finvestment = investment.subtract(commissionsub);
			this.setTotalcommission(this.getTotalcommission().add(commissionsub));
			BigDecimal stocksbought = finvestment.divide(stock.getOpprice(), 3, BigDecimal.ROUND_HALF_EVEN);
			this.setTotalstocks(this.getTotalstocks().add(stocksbought));

			System.out.println(stock.getDate() + " Stock bought at opening price of: " + stock.getOpprice().toString()

					+ "€ for a total of: " + stocksbought.toString() + " stocks Total stocks in this account: "
					+ this.totalstocks.toString() + " Total commission: " + this.getTotalcommission().toString() + "€");


		}

		return totalstocks;
	}

	/**
	 * Sells all the stocks accumulated by the broker given a date to find the stock closing price
	 * 
	 * 
	 * @param date the date to find the stock closing price
	 * @param stocklist the list of all the stocks
	 * @return the total amount of money gained by selling the stocks
	 */
	public BigDecimal sellStocks(LocalDate date, ArrayList<Stock> stocklist) {
		BigDecimal benefit = new BigDecimal(0);
		for (Stock stock : stocklist) {

			if (stock.getDate().equals(date)) {


				benefit = this.getTotalstocks().multiply(stock.getClprice()).setScale(3, BigDecimal.ROUND_HALF_EVEN);

				System.out.println("You've sold all your: " + this.getTotalstocks() + " stocks at a price of: "
						+ stock.getClprice() + "€ per stock" + " for a total of: " + benefit + "€");

				this.setTotalstocks(new BigDecimal(0));

			}


		}
		return benefit;
	}

}