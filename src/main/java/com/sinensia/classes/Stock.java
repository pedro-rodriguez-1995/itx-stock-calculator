package com.sinensia.classes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock {
	private LocalDate date;
	private BigDecimal clprice;
	private BigDecimal opprice;
	
	
	
	public Stock(LocalDate date, BigDecimal clprice, BigDecimal opprice) {
		super();
		this.date = date;
		this.clprice = clprice;
		this.opprice = opprice;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public BigDecimal getClprice() {
		return clprice;
	}



	public void setClprice(BigDecimal clprice) {
		this.clprice = clprice;
	}



	public BigDecimal getOpprice() {
		return opprice;
	}



	public void setOpprice(BigDecimal opprice) {
		this.opprice = opprice;
	}
	
	
	
	
}
