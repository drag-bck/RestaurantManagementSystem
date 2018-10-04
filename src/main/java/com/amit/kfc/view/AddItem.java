package com.amit.kfc.view;

import com.amit.kfc.controller.ItemController;
import com.amit.kfc.controller.Models;

import javax.swing.*;
import java.util.Vector;
import java.util.stream.Collectors;

import static com.amit.kfc.utils.ComboHelper.ComboItem;

/**
 * @author Aman Kumar
 */
public class AddItem extends javax.swing.JFrame {
	public AddItem() {
		items = Models.getInstance()
				.getCategories().stream()
				.map(category -> new ComboItem(category.getCatId(), category.getName()))
				.collect(Collectors.toCollection(Vector::new));
		itemController = new ItemController();
		initComponents();
	}
	
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		itemName = new javax.swing.JTextField();
		itemCost = new javax.swing.JTextField();
		addItem = new javax.swing.JButton();
		jComboBox1 = new javax.swing.JComboBox<>(items);
		
		setMinimumSize(new java.awt.Dimension(640, 480));
		setSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel1.setText("ComboItem Category");
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("ComboItem Name");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel3.setText("ComboItem Cost");
		
		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel4.setText("ADD ITEM IN MENU");
		
		itemName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		itemName.setMaximumSize(new java.awt.Dimension(640, 480));
		itemName.setMinimumSize(new java.awt.Dimension(640, 480));
		itemName.setPreferredSize(new java.awt.Dimension(640, 480));
		
		itemCost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		
		addItem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		addItem.setText("ADD");
		addItem.addActionListener((event) -> addItemActionPerformed(event));
		
		jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addContainerGap()
												.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(59, 59, 59)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addGroup(layout.createSequentialGroup()
																.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(jComboBox1, 0, 200, Short.MAX_VALUE))
														.addGroup(layout.createSequentialGroup()
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addComponent(itemCost, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																		.addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
										.addGroup(layout.createSequentialGroup()
												.addGap(145, 145, 145)
												.addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(163, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(20, 20, 20)
												.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(itemCost, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(51, 51, 51)
								.addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(104, Short.MAX_VALUE))
		);
		
		pack();
	}
	
	private void addItemActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			String name = itemName.getText();
			float cost;
			int catId = ((ComboItem) (jComboBox1.getSelectedItem())).getId();
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
	
	private javax.swing.JButton addItem;
	private javax.swing.JTextField itemCost;
	private javax.swing.JTextField itemName;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	
	private ItemController itemController;
	private Vector<ComboItem> items;
}
