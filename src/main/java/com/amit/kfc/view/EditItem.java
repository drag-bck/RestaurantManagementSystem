package com.amit.kfc.view;

import com.amit.kfc.controller.ItemController;
import com.amit.kfc.controller.Models;
import com.amit.kfc.utils.ComboHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author Aman Kumar
 */
public class EditItem extends javax.swing.JFrame {
	public EditItem() {
		categoryItems = Models.getInstance()
				.getCategories().stream()
				.map(category -> new ComboHelper.ComboItem(category.getCatId(), category.getName()))
				.collect(Collectors.toCollection(Vector::new));
		
		itemController = new ItemController();
		initComponents();
	}
	
	private void initComponents() {
		
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		itemCat = new javax.swing.JComboBox<>(categoryItems);
		jScrollPane1 = new javax.swing.JScrollPane();
		itemList = new javax.swing.JList<>();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		
		setTitle("EDIT ITEM PRICE");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("EDIT ITEM PRICE");
		
		jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jButton1.setText("CHANGE PRICE");
		
		itemCat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		itemCat.addItemListener((event) -> categoryChanged(event));
		
		jScrollPane1.setViewportView(itemList);
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel2.setText("Items");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel3.setText("New Price");
		
		jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addGap(26, 26, 26)
																.addComponent(jLabel1))
														.addGroup(layout.createSequentialGroup()
																.addGap(43, 43, 43)
																.addComponent(itemCat, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(43, 43, 43)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jLabel2))))
												.addGap(0, 74, Short.MAX_VALUE)))
								.addContainerGap())
						.addGroup(layout.createSequentialGroup()
								.addGap(287, 287, 287)
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(23, 23, 23)
								.addComponent(jLabel1)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(34, 34, 34)
												.addComponent(itemCat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(26, 26, 26)
												.addComponent(jLabel2)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		
		jButton1.addActionListener((event) -> editItemEvent(event));
		pack();
		
		
		if (!categoryItems.isEmpty()) {
			ItemEvent event = new ItemEvent(itemCat, 0, categoryItems.get(0), ItemEvent.SELECTED);
			categoryChanged(event);
		}
	}
	
	private void categoryChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			ComboHelper.ComboItem category = (ComboHelper.ComboItem) event.getItem();
			
			Vector<ComboHelper.ComboItem> items = Models.getInstance()
					.getItems().stream()
					.filter((item) -> item.getCatId() == category.getId())
					.map(item -> new ComboHelper.ComboItem(item.getItemId(), item.getName()))
					.collect(Collectors.toCollection(Vector::new));
			
			itemModel = new DefaultListModel<>();
			for (ComboHelper.ComboItem item : items) {
				itemModel.addElement(item);
			}
			itemList.setModel(itemModel);
		}
	}
	
	private void editItemEvent(ActionEvent event) {
		try {
			int index = itemList.getSelectedIndex();
			float cost;
			try {
				cost = Float.parseFloat(jTextField1.getText());
			} catch (Exception e) {
				throw new Exception("Invalid Cost");
			}
			ComboHelper.ComboItem item = itemModel.get(index);
			itemController.updateItem(item.getId(), cost);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		Models.getInstance().init();
	}
	
	private javax.swing.JButton jButton1;
	private javax.swing.JComboBox itemCat;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JList itemList;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField jTextField1;
	
	private Vector<ComboHelper.ComboItem> categoryItems;
	private DefaultListModel<ComboHelper.ComboItem> itemModel;
	
	private ItemController itemController;
}
