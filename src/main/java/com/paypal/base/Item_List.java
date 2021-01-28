package com.paypal.base;

import java.util.List;

public class Item_List {
	
	public Item_List(List<Items> items, Shipping_address shipping_address) {
		this.items = items;
		this.shipping_address = shipping_address;
	}
	//
	public Item_List() {

	}
	//
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	public Shipping_address getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(Shipping_address shipping_address) {
		this.shipping_address = shipping_address;
	}
	private List<Items> items;
	private Shipping_address shipping_address;
}