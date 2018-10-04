package com.amit.kfc.view;

import com.amit.kfc.controller.Models;
import com.amit.kfc.model.Order;

import javax.swing.*;
import java.util.Vector;
import java.util.stream.Collectors;

public class OrderHistory extends JFrame {
	public OrderHistory() {
		initComponents();
	}
	
	public void initComponents() {
		Vector<Vector<String>> data = Models.getInstance().getOrders().stream()
				.map(Order::toStringVector)
				.collect(Collectors.toCollection(Vector::new));
		
		Order total = new Order();
		total.setCustomerPhone("Total");
		total.setAmount((float) Models.getInstance().getOrders().stream().collect(Collectors.summarizingDouble(Order::getAmount)).getSum());
		data.add(total.toStringVector());
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Order Id");
		columnNames.add("Customer");
		columnNames.add("Phone");
		columnNames.add("Amount");
		
		this.setTitle("Order Summary");
		
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		this.setSize(600, 300);
	}
}
