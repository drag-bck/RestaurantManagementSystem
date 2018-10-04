package com.amit.kfc.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

class MenuPage extends javax.swing.JFrame {
	private JButton changeUser;
	private JButton editMenu;
	private JButton generateBill;
	private JButton orderHistory;
	private JButton takeOrder;
	
	MenuPage() {
		initComponents();
	}
	
	private void initComponents() {
		takeOrder = new javax.swing.JButton();
		orderHistory = new javax.swing.JButton();
		editMenu = new javax.swing.JButton();
		generateBill = new javax.swing.JButton();
		changeUser = new javax.swing.JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Punjabi Chulha");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		takeOrder.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		takeOrder.setActionCommand("TAKE_ORDER");
		takeOrder.setText("Take Order");
		
		orderHistory.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		orderHistory.setActionCommand("ORDER_HISTORY");
		orderHistory.setText("Order History");
		
		editMenu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		editMenu.setActionCommand("EDIT_MENU");
		editMenu.setText("Edit Menu");
		
		generateBill.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		generateBill.setActionCommand("GENERATE_BILL");
		generateBill.setText("Generate Bill");
		
		changeUser.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		changeUser.setActionCommand("ADD_USER");
		changeUser.setText("Add User");
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(61, 61, 61)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(orderHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
										.addComponent(generateBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(editMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(changeUser, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
								.addGap(66, 66, 66))
						.addGroup(layout.createSequentialGroup()
								.addGap(200, 200, 200)
								.addComponent(takeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(37, 37, 37)
								.addComponent(takeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(45, 45, 45)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(orderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(editMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(32, 32, 32)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(generateBill, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(changeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(186, Short.MAX_VALUE))
		);
		
		takeOrder.addActionListener(this::actionPerformed);
		changeUser.addActionListener(this::actionPerformed);
		editMenu.addActionListener(this::actionPerformed);
		generateBill.addActionListener(this::actionPerformed);
		orderHistory.addActionListener(this::actionPerformed);
		
		pack();
	}
	
	private void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		switch (action) {
			case "TAKE_ORDER":
				//Todo:Implement
				break;
			case "ORDER_HISTORY":
				new OrderHistory().setVisible(true);
				break;
			case "EDIT_MENU":
				new EditMenu().setVisible(true);
				break;
			case "GENERATE_BILL":
				new GenerateBill().setVisible(true);
				break;
			case "ADD_USER":
				new AddUser().setVisible(true);
				break;
		}
	}
}
