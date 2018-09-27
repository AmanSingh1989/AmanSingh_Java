package com.abc.generalledger.app.entity;

public class DailyPosition {
	
	String instrument;
	String accountType;
	Integer quantity;
	Integer accountNumber;
	Integer delta;
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getDelta() {
		return delta;
	}
	public void setDelta(Integer delta) {
		this.delta = delta;
	}
	@Override
	public String toString() {
		return  instrument + "," + accountType + "," + quantity
				+ "," + accountNumber + "," + delta;
	}
	
	

}
