package com.sinensia.classes;

import java.math.BigDecimal;

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

}