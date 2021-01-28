package com.paypal.base;

import java.util.ArrayList;
import java.util.List;

public class Paypal_request {
public PayPal_PaymentObj createPaymentBody() {
Payer payer=new Payer("paypal");
Redirect_urls redirect_urls=new Redirect_urls("https://example.com/return","https://example.com/cancel");
Shipping_address shipping_address=new Shipping_address("Brian Robinson","4th Floor","Unit #34","San Jose","US","95131","011862212345870","CA");
Items i1=new Items("hat","Brown hat.","5","3","0.01","1","USD");
Items i2=new Items("handbag","Black handbag.","1","15","0.02","product34","USD");
List<Items> items=new ArrayList<Items>();
items.add(i1);
items.add(i2);
Item_List item_lst=new Item_List(items,shipping_address);
Payment_options payment_options=new 
Payment_options("INSTANT_FUNDING_SOURCE");
Details details=new Details("30.00","0.07","0.03","1.00","-1.00","0.01");
Amount amount=new Amount("30.11","USD",details);
Transactions t1=new Transactions(amount,"Transferring 50000k.","EBAY_EMS_9004863008787","48786789987",payment_options,"ECHI5786766",item_lst);
List<Transactions> transactions=new ArrayList<Transactions>();
transactions.add(t1);

return new PayPal_PaymentObj("sale",payer,transactions,"Contact us for any questions on your order.",redirect_urls);
}



}
