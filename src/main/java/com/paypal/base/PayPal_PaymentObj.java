package com.paypal.base;

import java.util.ArrayList;
import java.util.List;

public class PayPal_PaymentObj {

	public PayPal_PaymentObj(String intent, Payer payer, List<Transactions> transactions, String note_to_payer,
			Redirect_urls redirect_urls) {

		this.intent = intent;
		this.payer = payer;
		this.transactions = transactions;
		this.note_to_payer = note_to_payer;
		this.redirect_urls = redirect_urls;
	}

	public PayPal_PaymentObj() {
// TODO Auto-generated constructor stub
	}

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	public String getNote_to_payer() {
		return note_to_payer;
	}

	public void setNote_to_payer(String note_to_payer) {
		this.note_to_payer = note_to_payer;
	}

	public Redirect_urls getRedirect_urls() {
		return redirect_urls;
	}

	public void setRedirect_urls(Redirect_urls redirect_urls) {
		this.redirect_urls = redirect_urls;
	}

	private String intent;
	private Payer payer;
	private List<Transactions> transactions;
	private String note_to_payer;
	private Redirect_urls redirect_urls;

//response vars
	private String create_time;

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	private String id;
	private String state;
	private List<Links> links;

	public static PayPal_PaymentObj createPaymentBody() {
		Payer payer = new Payer("paypal");
		Redirect_urls redirect_urls = new Redirect_urls("https://example.com/return", "https://example.com/cancel");
		Shipping_address shipping_address = new Shipping_address("Brian Robinson", "4th Floor", "Unit #34", "San Jose",
				"US", "95131", "011862212345870", "CA");
		Items i1 = new Items("hat", "Brown hat.", "5", "3", "0.01", "1", "USD");
		Items i2 = new Items("handbag", "Black handbag.", "1", "15", "0.02", "product34", "USD");
		List<Items> items = new ArrayList<Items>();
		items.add(i1);
		items.add(i2);
		Item_List item_list = new Item_List(items, shipping_address);
		Payment_options payment_options = new Payment_options("INSTANT_FUNDING_SOURCE");
		Details details = new Details("30.00", "0.07", "0.03", "1.00", "-1.00", "0.01");
		Amount amount = new Amount("30.11", "USD", details);
		Transactions t1 = new Transactions(amount, "Transferring 10000k.", "EBAY_EMS_9004877678757", "48782346987",
				payment_options, "ECHI5733386", item_list);
		List<Transactions> transactions = new ArrayList<Transactions>();
		transactions.add(t1);

		return new PayPal_PaymentObj("sale", payer, transactions, "Contact us for any questions on your order.",
				redirect_urls);
	}
}
