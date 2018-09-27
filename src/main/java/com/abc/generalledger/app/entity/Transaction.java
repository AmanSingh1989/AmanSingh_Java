package com.abc.generalledger.app.entity;

public class Transaction {
	
	 @Override
	public String toString() {
		return "Transcation [transactionId=" + transactionId + ", instrument=" + instrument + ", transactionType="
				+ transactionType + ", transactionQuantity=" + transactionQuantity + "]";
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Integer getTransactionQuantity() {
		return transactionQuantity;
	}
	public void setTransactionQuantity(Integer transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
	}
	Integer transactionId;
     String instrument;
     String transactionType;
     Integer transactionQuantity;
}
