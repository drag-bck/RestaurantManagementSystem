package com.amit.kfc.view;

import com.amit.kfc.controller.ItemController;
import com.amit.kfc.controller.Models;
import com.amit.kfc.model.Category;

import javax.swing.*;
import java.util.Vector;

class AddItem extends JFrame {
	private JButton addItem;
	private JTextField itemCost;
	private JTextField itemName;
	private JComboBox<Category> jComboBox1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	
	private ItemController itemController;
	private Vector<Category> items;
	
	AddItem() {
		items = new Vector<>(Models.getInstance().getCategories());
		itemController = new ItemController();
		initComponents();
	}
	
	private void initComponents() {
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		itemName = new JTextField();
		itemCost = new JTextField();
		addItem = new JButton();
		jComboBox1 = new JComboBox<>(items);
		
		setMinimumSize(new java.awt.Dimension(640, 480));
		setSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel1.setText("Item Category");
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("Item Name");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel3.setText("Item Cost");
		
		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel4.setText("Add Item In Menu");
		
		itemName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		itemName.setMaximumSize(new java.awt.Dimension(640, 480));
		itemName.setMinimumSize(new java.awt.Dimension(640, 480));
		itemName.setPreferredSize(new java.awt.Dimension(640, 480));
		
		itemCost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		
		addItem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		addItem.setText("ADD");
		addItem.addActionListener(this::addItemActionPerformed);
		
		jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addContainerGap()
												.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(59, 59, 59)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addGroup(layout.createSequentialGroup()
																.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(jComboBox1, 0, 200, Short.MAX_VALUE))
														.addGroup(layout.createSequentialGroup()
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
																		.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																		.addComponent(itemCost, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																		.addComponent(itemName, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
										.addGroup(layout.createSequentialGroup()
												.addGap(145, 145, 145)
												.addComponent(addItem, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(163, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(20, 20, 20)
												.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
										.addComponent(itemName, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
										.addComponent(itemCost, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addGap(51, 51, 51)
								.addComponent(addItem, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(104, Short.MAX_VALUE))
		);
		
		pack();
	}
	
	private void addItemActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			String name = itemName.getText();
			float cost;
			int catId = ((Category) jComboBox1.getSelectedItem()).getCatId();
			try {
				cost = Float.parseFloat(itemCost.getText());
			} catch (Exception e) {
				throw new Exception("Invalid Cost");
			}
			
			itemController.addItem(catId, name, cost);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
