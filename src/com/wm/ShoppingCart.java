package com.wm;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	private Map<Integer, ShoppingCartItem> items;

	public ShoppingCart() {
		this.items = new HashMap<Integer, ShoppingCartItem>();
	}

	private float price;

	public Map<Integer, ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(Map<Integer, ShoppingCartItem> items) {
		this.items = items;
	}

	public float getPrice() {
		float price = 0;
		for (ShoppingCartItem item : items.values()) {
			price += item.getPrice();
		}
		return DecimalOperate.operate(price);
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
