package com.amit.kfc.model;

import java.util.Arrays;
import java.util.Vector;

public class OrderItem {
	private Item item;
	private int quantity;
	
	public OrderItem(Item item, int qty) {
		this.item = item;
		this.quantity = qty;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getName() {
		return item.getName();
	}
	
	public float getPrice() {
		return item.getCost();
	}
	
	public float getAmount() {
		return quantity * item.getCost();
	}
	
	public Vector<String> getStringArray() {
		String[] data = {getName(), Float.toString(getPrice()), Integer.toString(getQuantity()), Float.toString(getAmount())};
		return new Vector<>(Arrays.asList(data));
	}
}
