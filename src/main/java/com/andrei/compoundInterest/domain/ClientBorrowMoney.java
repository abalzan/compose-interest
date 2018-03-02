package com.andrei.compoundInterest.domain;

public class ClientBorrowMoney {

	private Double compoundInterest;
	private Integer requestAmount;
	private Integer value;
	private Double rate;
	private Double monthRepayment;
	private Double totalRepayment;
	
	public Double getCompoundInterest() {
		return compoundInterest;
	}
	public void setCompoundInterest(Double compoundInterest) {
		this.compoundInterest = compoundInterest;
	}
	public Integer getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(Integer requestAmount) {
		this.requestAmount = requestAmount;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getMonthRepayment() {
		return monthRepayment;
	}
	public void setMonthRepayment(Double monthRepayment) {
		this.monthRepayment = monthRepayment;
	}
	public Double getTotalRepayment() {
		return totalRepayment;
	}
	public void setTotalRepayment(Double totalRepayment) {
		this.totalRepayment = totalRepayment;
	}

}
