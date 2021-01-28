package com.paypal.base;

public class Amount_order {
public Amount_order(String currency_code, double value) {
this.currency_code = currency_code;
this.value = value;
}
private String currency_code;
public String getCurrency_code() {
return currency_code;
}
public void setCurrency_code(String currency_code) {
this.currency_code = currency_code;
}
public double getValue() {
return value;
}
public void setValue(double value) {
this.value = value;
}
private double value;
}
