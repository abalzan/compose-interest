package com.andrei.compoundInterest.domain;

import java.math.BigDecimal;

public class MarketLendMoney {

	private String lender;
	private Double rate;
	private Integer availableAmount;
	
	public MarketLendMoney(String lender, Double rate, Integer availableAmount) {
		this.lender = lender;
		this.rate = rate;
		this.availableAmount = availableAmount;
	}

	public String getLender() {
		return lender;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}

	public Double getRate() {
		return new BigDecimal(rate.toString()).doubleValue();
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(Integer availableAmount) {
		this.availableAmount = availableAmount;
	}

	@Override
	public String toString() {
		return "MarketBorrowMoney [lender=" + lender + ", rate=" + rate + ", availableAmount=" + availableAmount + "]";
	}	
}
