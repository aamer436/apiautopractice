package com.paypal.base;

public class Details {
private String subtotal;
public Details(String subtotal, String tax, String shipping, String handling_fee, String shipping_discount,
		String insurance) {
	this.subtotal = subtotal;
	this.tax = tax;
	this.shipping = shipping;
	this.handling_fee = handling_fee;
	this.shipping_discount = shipping_discount;
	this.insurance = insurance;
}
//
public Details() {


}
//
private String tax;
public String getSubtotal() {
	return subtotal;
}
public void setSubtotal(String subtotal) {
	this.subtotal = subtotal;
}
public String getTax() {
	return tax;
}
public void setTax(String tax) {
	this.tax = tax;
}
public String getShipping() {
	return shipping;
}
public void setShipping(String shipping) {
	this.shipping = shipping;
}
public String getHandling_fee() {
	return handling_fee;
}
public void setHandling_fee(String handling_fee) {
	this.handling_fee = handling_fee;
}
public String getShipping_discount() {
	return shipping_discount;
}
public void setShipping_discount(String shipping_discount) {
	this.shipping_discount = shipping_discount;
}
public String getInsurance() {
	return insurance;
}
public void setInsurance(String insurance) {
	this.insurance = insurance;
}
private String shipping;
private String handling_fee;
private String shipping_discount;
private String insurance;
}