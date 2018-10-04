package com.amit.kfc.view;

import com.amit.kfc.controller.ItemController;
import com.amit.kfc.controller.Models;
import com.amit.kfc.model.Category;
import com.amit.kfc.model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Vector;
import java.util.stream.Collectors;

class DeleteItem extends JFrame {
	DeleteItem() {
		categoryItems = new Vector<>(Models.getInstance().getCategories());
		itemController = new ItemController();
		
		initComponents();
	}
	
	private void initComponents() {
		
		jLabel1 = new JLabel();
		deleteItem = new JButton();
		itemCat = new JComboBox<>(categoryItems);
		jCheckBox3 = new JCheckBox();
		jScrollPane2 = new JScrollPane();
		itemList = new JList<>();
		jLabel2 = new JLabel();
		
		setTitle("Delete Item");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("Delete Menu Item");
		
		deleteItem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		deleteItem.setText("Delete");
		
		itemCat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		itemCat.addItemListener(this::categoryChanged);
		
		jCheckBox3.setText("jCheckBox3");
		jScrollPane2.setViewportView(itemList);
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Items");
		
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(36, 36, 36)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
												.addComponent(jLabel1)
												.addContainerGap(281, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(itemCat, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createSequentialGroup()
																				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
																		.addGroup(layout.createSequentialGroup()
																				.addGap(91, 91, 91)
																				.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
																				.addGap(0, 0, Short.MAX_VALUE))))
														.addGroup(layout.createSequentialGroup()
																.addGap(0, 0, Short.MAX_VALUE)
																.addComponent(deleteItem, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
												.addGap(29, 29, 29))))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addComponent(jLabel1)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(47, 47, 47)
												.addComponent(itemCat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
												.addComponent(jLabel2)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
								.addComponent(deleteItem, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		
		jLabel2.getAccessibleContext().setAccessibleName("jlabel2");
		deleteItem.addActionListener(this::deleteItemEvent);
		pack();
		
		
		if (!categoryItems.isEmpty()) {
			ItemEvent event = new ItemEvent(itemCat, 0, categoryItems.get(0), ItemEvent.SELECTED);
			categoryChanged(event);
		}
	}
	
	private void categoryChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Category category = (Category) event.getItem();
			
			Vector<Item> items = Models.getInstance()
					.getItems().stream()
					.filter((item) -> item.getCatId() == category.getCatId())
					.collect(Collectors.toCollection(Vector::new));
			
			itemModel = new DefaultListModel<>();
			for (Item item : items) {
				itemModel.addElement(item);
			}
			itemList.setModel(itemModel);
		}
	}
	
	private void deleteItemEvent(ActionEvent event) {
		try {
			if (itemList.getSelectedIndex() != -1) {
				Item item = itemModel.get(itemList.getSelectedIndex());
				itemController.deleteItem(item.getItemId());
				itemModel.removeElement(item);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		Models.getInstance().init();
	}
	
	private JButton deleteItem;
	private JComboBox<Category> itemCat;
	private JList<Item> itemList;
	private JCheckBox jCheckBox3;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane2;
	
	private Vector<Category> categoryItems;
	private DefaultListModel<Item> itemModel;
	
	private ItemController itemController;
}
