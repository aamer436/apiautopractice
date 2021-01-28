package com.paypal.base;

public class Amount {

public Amount(String total, String currency, Details details) {
	this.total = total;
	this.currency = currency;
	this.details = details;
}
//
public Amount() {

}
//
private String total;
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public Details getDetails() {
	return details;
}
public void setDetails(Details details) {
	this.details = details;
}
private String currency;
private Details details;
}