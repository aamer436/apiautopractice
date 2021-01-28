package com.paypal.base;

public class Purchase_Units {
public Purchase_Units(Amount_order amount) {
this.amount = amount;
}

private Amount_order amount;

public Amount_order getAmount() {
return amount;
}

public void setAmount(Amount_order amount) {
this.amount = amount;
}



}
