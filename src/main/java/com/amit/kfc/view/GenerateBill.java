package com.amit.kfc.view;

import com.amit.kfc.controller.Models;
import com.amit.kfc.model.Order;
import com.amit.kfc.model.Seller;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.stream.Collectors;

class GenerateBill extends JFrame {
	private JDateChooser endDate;
	private JButton generateBill;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JComboBox sellerName;
	private JDateChooser startDate;
	
	public GenerateBill() {
		initComponents();
		

		ArrayList<Seller> sellers = Models.getInstance().getSellers();
		sellerName.setModel(new DefaultComboBoxModel(sellers.toArray()));
	}
	
	private void initComponents() {
		
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		generateBill = new JButton();
		sellerName = new JComboBox();
		startDate = new JDateChooser(new Date());
		endDate = new JDateChooser(new Date());
		
		setTitle("Sales Information");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("Generate Sales Information");
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("From");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel3.setText("To");
		
		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel4.setText("Seller");
		
		generateBill.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		generateBill.setText("Generate");
		generateBill.addActionListener(this::generateBillActionPerformed);
		
		sellerName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		sellerName.setPreferredSize(new java.awt.Dimension(200, 35));
		
		startDate.setBounds(20, 20, 200, 20);
		startDate.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		endDate.setBounds(20, 20, 200, 20);
		endDate.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addGroup(layout.createSequentialGroup()
												.addGap(56, 56, 56)
												.addComponent(jLabel1))
										.addGroup(layout.createSequentialGroup()
												.addGap(97, 97, 97)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(jLabel4)
														.addComponent(jLabel3)
														.addComponent(jLabel2))
												.addGap(81, 81, 81)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(startDate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(endDate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(sellerName, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(169, 169, 169)
												.addComponent(generateBill, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(147, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(20, 20, 20)
								.addComponent(jLabel1)
								.addGap(39, 39, 39)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel2)
														.addComponent(startDate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
												.addGap(43, 43, 43)
												.addComponent(jLabel3))
										.addComponent(endDate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGap(27, 27, 27)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4)
										.addComponent(sellerName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGap(51, 51, 51)
								.addComponent(generateBill, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(94, Short.MAX_VALUE))
		);
		
		pack();
	}
	
	private void generateBillActionPerformed(ActionEvent event) {
		try {
			Seller seller = (Seller) sellerName.getSelectedItem();
			
			java.sql.Date orderStartDate = dateToSqlDate(startDate.getDate());
			java.sql.Date orderEndDate = dateToSqlDate(endDate.getDate());
			
			ArrayList<Order> orders = Models.getInstance()
					.getOrders().stream()
					.filter((order -> (order.getSellerId() == seller.getSellerId() &&
							compareLessEqualDates(orderStartDate, order.getDate()) &&
							compareLessEqualDates(order.getDate(), orderEndDate))))
					.collect(Collectors.toCollection(ArrayList::new));
			
			Vector<Vector<String>> data = orders.stream()
					.map(Order::toStringVector)
					.collect(Collectors.toCollection(Vector::new));
			
			Order total = new Order();
			total.setCustomerPhone("Total");
			total.setAmount((float) orders.stream().collect(Collectors.summarizingDouble(Order::getAmount)).getSum());
			data.add(total.toStringVector());
			
			Vector<String> columnNames = new Vector<>(Arrays.asList("Order Id", "Customer", "Phone", "Amount"));
			
			JFrame frame = new JFrame();
			frame.setTitle("Order Summary: " + seller.getName());
			
			JTable table = new JTable(data, columnNames);
			JScrollPane scrollPane = new JScrollPane(table);
			frame.add(scrollPane);
			frame.setSize(600, 300);
			frame.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private boolean compareLessEqualDates(java.sql.Date start, java.sql.Date curr) {
		return curr.getYear() >= start.getYear() && curr.getMonth() >= start.getMonth() && curr.getDate() >= start.getDate();
	}
	
	private java.sql.Date dateToSqlDate(Date date) {
		return new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());
	}
}
