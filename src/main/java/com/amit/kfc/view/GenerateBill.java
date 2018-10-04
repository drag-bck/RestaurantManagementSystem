package com.amit.kfc.view;

import com.amit.kfc.controller.Models;
import com.amit.kfc.model.Order;
import com.amit.kfc.model.Seller;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author Aman Kumar
 */
public class GenerateBill extends javax.swing.JFrame {
	
	public GenerateBill() {
		initComponents();
		
		ArrayList<Seller> sellers = Models.getInstance().getSellers();
		Seller seller = new Seller();
		seller.setName("Zomato");
		seller.setSellerId(1);
		sellers.add(seller);
		sellerName.setModel(new DefaultComboBoxModel(sellers.toArray()));
	}
	
	private void initComponents() {
		
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		generateBill = new javax.swing.JButton();
		sellerName = new javax.swing.JComboBox();
		startDate = new JDateChooser(new Date());
		endDate = new JDateChooser(new Date());
		
		setTitle("GENERATE SALE INFORMATION");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("GENERATE SALE INFORMATION");
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("START DATE");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel3.setText("END DATE");
		
		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel4.setText("SELLER");
		
		generateBill.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		generateBill.setText("GENERATE");
		generateBill.addActionListener(this::generateBillActionPerformed);
		
		sellerName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		sellerName.setPreferredSize(new java.awt.Dimension(200, 35));
		
		startDate.setBounds(20, 20, 200, 20);
		startDate.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		endDate.setBounds(20, 20, 200, 20);
		endDate.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(layout.createSequentialGroup()
												.addGap(56, 56, 56)
												.addComponent(jLabel1))
										.addGroup(layout.createSequentialGroup()
												.addGap(97, 97, 97)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel4)
														.addComponent(jLabel3)
														.addComponent(jLabel2))
												.addGap(81, 81, 81)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(startDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(endDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(sellerName, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(169, 169, 169)
												.addComponent(generateBill, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(147, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(20, 20, 20)
								.addComponent(jLabel1)
								.addGap(39, 39, 39)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel2)
														.addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(43, 43, 43)
												.addComponent(jLabel3))
										.addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(27, 27, 27)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4)
										.addComponent(sellerName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(51, 51, 51)
								.addComponent(generateBill, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(94, Short.MAX_VALUE))
		);
		
		pack();
	}
	
	private void generateBillActionPerformed(ActionEvent event) {
		try {
			Seller seller = (Seller) sellerName.getSelectedItem();
			Date orderStartDate = startDate.getDate();
			Date orderEndDate = endDate.getDate();
			
			java.sql.Date stDate = new java.sql.Date(orderStartDate.getYear(), orderStartDate.getMonth(), orderStartDate.getDay());
			java.sql.Date eDate = new java.sql.Date(orderEndDate.getYear(), orderEndDate.getMonth(), orderEndDate.getDay());
			
			ArrayList<Order> orders = Models.getInstance()
					.getOrders().stream()
					.filter((order -> (order.getSellerId() == seller.getSellerId() &&
							compareLessEqualDates(stDate, order.getDate()) &&
							compareLessEqualDates(order.getDate(), eDate))))
					.collect(Collectors.toCollection(ArrayList::new));
			
			Vector<Vector<String>> data = orders.stream()
					.map(Order::toStringVector)
					.collect(Collectors.toCollection(Vector::new));
			
			Order total = new Order();
			total.setCustomerPhone("Total");
			total.setAmount((float) orders.stream().collect(Collectors.summarizingDouble(Order::getAmount)).getSum());
			data.add(total.toStringVector());
			
			Vector<String> columnNames = new Vector<>();
			columnNames.add("Order Id");
			columnNames.add("Customer");
			columnNames.add("Phone");
			columnNames.add("Amount");
			
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
		Models.getInstance().init();
	}
	
	private JDateChooser endDate;
	private javax.swing.JButton generateBill;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JComboBox sellerName;
	private JDateChooser startDate;
	
	private boolean compareLessEqualDates(java.sql.Date start, java.sql.Date curr) {
		return curr.getYear() >= start.getYear() && curr.getMonth() >= start.getMonth() && curr.getDate() >= start.getDate();
	}
}
