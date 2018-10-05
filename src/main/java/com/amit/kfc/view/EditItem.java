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

class EditItem extends JFrame {
	private JButton jButton1;
	private JComboBox itemCat;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JList<Item> itemList;
	private JScrollPane jScrollPane1;
	private JTextField jTextField1;
	
	private Vector<Category> categoryItems;
	private DefaultListModel<Item> itemModel;
	
	private ItemController itemController;
	
	EditItem() {
		categoryItems = new Vector<>(Models.getInstance().getCategories());
		
		itemController = new ItemController();
		initComponents();
	}
	
	private void initComponents() {
		
		jLabel1 = new JLabel();
		jButton1 = new JButton();
		itemCat = new JComboBox<>(categoryItems);
		jScrollPane1 = new JScrollPane();
		itemList = new JList<>();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jTextField1 = new JTextField();
		
		setTitle("Edit Item Price");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("Edit Item Price");
		
		jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jButton1.setText("Change Price");
		
		itemCat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		itemCat.addItemListener(this::categoryChanged);
		
		jScrollPane1.setViewportView(itemList);
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel2.setText("Items");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel3.setText("New Price");
		
		jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addGap(26, 26, 26)
																.addComponent(jLabel1))
														.addGroup(layout.createSequentialGroup()
																.addGap(43, 43, 43)
																.addComponent(itemCat, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
																.addGap(43, 43, 43)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
																		.addComponent(jLabel2))))
												.addGap(0, 74, Short.MAX_VALUE)))
								.addContainerGap())
						.addGroup(layout.createSequentialGroup()
								.addGap(287, 287, 287)
								.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(23, 23, 23)
								.addComponent(jLabel1)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(34, 34, 34)
												.addComponent(itemCat, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(26, 26, 26)
												.addComponent(jLabel2)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
										.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
								.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		
		jButton1.addActionListener(this::editItemEvent);
		pack();
		
		
		if (!categoryItems.isEmpty()) {
			ItemEvent event = new ItemEvent(itemCat, 0, categoryItems.get(0), ItemEvent.SELECTED);
			categoryChanged(event);
		}
		
		itemList.addListSelectionListener((e) -> {
			Item item = itemList.getSelectedValue();
			jTextField1.setText(Float.toString(item.getCost()));
		});
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
	
	private void editItemEvent(ActionEvent event) {
		try {
			int index = itemList.getSelectedIndex();
			float cost;
			try {
				cost = Float.parseFloat(jTextField1.getText());
			} catch (Exception e) {
				throw new Exception("Invalid Cost");
			}
			Item item = itemModel.get(index);
			itemController.updateItem(item.getItemId(), cost);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		Models.getInstance().init();
	}
}
