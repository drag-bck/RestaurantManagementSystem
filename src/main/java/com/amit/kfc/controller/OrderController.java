package com.amit.kfc.controller;

import com.amit.kfc.model.Order;
import com.amit.kfc.model.SaleType;
import com.amit.kfc.utils.ModelHelper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class OrderController {
	public ArrayList<Order> getOrders() {
		return Models.getInstance().getOrders();
	}
	
	public void addOrder(String customerName, String customerPhone, int sellerId, String saleType, float amount) throws Exception {
		addOrder(new java.sql.Date(System.currentTimeMillis()), customerName, customerPhone, sellerId, saleType, amount);
	}
	
	public void addOrder(Date date, String customerName, String customerPhone, int sellerId, String saleType, float amount) throws Exception {
		if (customerName == null)
			throw new Exception("Customer Name cannot be empty!");
		customerName = customerName.trim();
		if (customerName.isEmpty())
			throw new Exception("Customer Name cannot be empty!");
		if (customerPhone == null)
			throw new Exception("Customer Phone cannot be empty!");
		customerPhone = customerPhone.trim();
		if (customerPhone.length() != 10)
			throw new Exception("10 digit Phone Number Required");
		
		SaleType tSaleType = SaleType.parse(saleType);
		if (amount <= 0)
			throw new Exception("Invalid Amount");
		
		int id = getOrders().isEmpty() ? 1 : getOrders().get(getOrders().size() - 1).getOrderId() + 1;
		
		Order order = new Order();
		order.setOrderId(id);
		order.setDate(date);
		order.setCustomerName(customerName);
		order.setCustomerPhone(customerPhone);
		order.setSellerId(sellerId);
		order.setSaleType(tSaleType);
		order.setAmount(amount);
		
		PreparedStatement query = order.getWriteQuery(Database.getConnection());
		
		if (!ModelHelper.executeQuery(query))
			throw new Exception("Order Creation Failed");
		
		getOrders().add(order);
	}
}
