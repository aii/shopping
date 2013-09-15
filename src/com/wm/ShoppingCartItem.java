package com.wm;

public class ShoppingCartItem {
	private Good good;
	private int quantity;
	private float price;

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		if (this.quantity != 0) {
			this.price = this.good.getPrice() * this.quantity;
		}
		return DecimalOperate.operate(price);
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
