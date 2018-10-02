package com.amit.kfc.view;

import com.amit.kfc.controller.ItemController;
import com.amit.kfc.controller.Models;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Vector;
import java.util.stream.Collectors;

import static com.amit.kfc.utils.ComboHelper.ComboItem;

/**
 * @author Aman Kumar
 */
public class DeleteItem extends javax.swing.JFrame {
	public DeleteItem() {
		categoryItems = Models.getInstance()
				.getCategories().stream()
				.map(category -> new ComboItem(category.getCatId(), category.getName()))
				.collect(Collectors.toCollection(Vector::new));
		itemController = new ItemController();
		
		initComponents();
	}
	
	private void initComponents() {
		
		jLabel1 = new javax.swing.JLabel();
		deleteItem = new javax.swing.JButton();
		itemCat = new javax.swing.JComboBox(categoryItems);
		jCheckBox3 = new javax.swing.JCheckBox();
		jScrollPane2 = new javax.swing.JScrollPane();
		itemList = new javax.swing.JList<>();
		jLabel2 = new javax.swing.JLabel();
		
		setTitle("DELETE ITEM");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("DELETE MENU ITEM");
		
		deleteItem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		deleteItem.setText("DELETE");
		
		itemCat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		itemCat.addItemListener((event) -> categoryChanged(event));
		
		jCheckBox3.setText("jCheckBox3");
		jScrollPane2.setViewportView(itemList);
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Items");
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(36, 36, 36)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
												.addComponent(jLabel1)
												.addContainerGap(281, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(itemCat, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createSequentialGroup()
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(layout.createSequentialGroup()
																				.addGap(91, 91, 91)
																				.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGap(0, 0, Short.MAX_VALUE))))
														.addGroup(layout.createSequentialGroup()
																.addGap(0, 0, Short.MAX_VALUE)
																.addComponent(deleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGap(29, 29, 29))))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addComponent(jLabel1)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(47, 47, 47)
												.addComponent(itemCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
												.addComponent(jLabel2)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
								.addComponent(deleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		
		jLabel2.getAccessibleContext().setAccessibleName("jlabel2");
		deleteItem.addActionListener((event) -> deleteItemEvent(event));
		pack();
		
		
		if (!categoryItems.isEmpty()) {
			ItemEvent event = new ItemEvent(itemCat, 0, categoryItems.get(0), ItemEvent.SELECTED);
			categoryChanged(event);
		}
	}
	
	private void categoryChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			ComboItem category = (ComboItem) event.getItem();
			
			Vector<ComboItem> items = Models.getInstance()
					.getItems().stream()
					.filter((item) -> item.getCatId() == category.getId())
					.map(item -> new ComboItem(item.getItemId(), item.getName()))
					.collect(Collectors.toCollection(Vector::new));
			
			itemModel = new DefaultListModel<>();
			for (ComboItem item : items) {
				itemModel.addElement(item);
			}
			itemList.setModel(itemModel);
		}
	}
	
	private void deleteItemEvent(ActionEvent event) {
		try {
			int[] selectedIx = itemList.getSelectedIndices();
			
			for (int index : selectedIx) {
				ComboItem item = itemModel.get(index);
				itemController.deleteItem(item.getId());
				itemModel.removeElement(item);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		Models.getInstance().init();
	}
	
	private javax.swing.JButton deleteItem;
	private javax.swing.JComboBox itemCat;
	private javax.swing.JList itemList;
	private javax.swing.JCheckBox jCheckBox3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane2;
	
	private Vector<ComboItem> categoryItems;
	private DefaultListModel<ComboItem> itemModel;
	
	private ItemController itemController;
}
