package com.paypal.base;

import java.util.ArrayList;
import java.util.List;

public class OrderObject {
public OrderObject() {
super();
}
public OrderObject(String intent, List<Purchase_Units> purchase_units) {
this.intent = intent;
this.purchase_units = purchase_units;
}

private String intent;
public String getIntent() {
return intent;
}
public void setIntent(String intent) {
this.intent = intent;
}
public List<Purchase_Units> getPurchase_units() {
return purchase_units;
}
public void setPurchase_units(List<Purchase_Units> purchase_units) {
this.purchase_units = purchase_units;
}
private List<Purchase_Units> purchase_units;

public static OrderObject createOrderRequest() {
Amount_order a=new Amount_order("USD",100.20);
Purchase_Units p=new Purchase_Units(a);
List<Purchase_Units> plist=new ArrayList<Purchase_Units>();
plist.add(p);
OrderObject o=new OrderObject("CAPTURE",plist);
return o;

}
}
