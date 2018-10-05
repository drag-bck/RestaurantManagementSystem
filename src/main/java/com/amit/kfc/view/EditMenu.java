package com.amit.kfc.view;

import javax.swing.*;

/**
 * @author Aman Kumar
 */
public class EditMenu extends JFrame {
	
	private JButton addItem;
	private JButton deleteItem;
	private JButton editItem;
	private JLabel jLabel1;
	
	EditMenu() {
		initComponents();
	}
	
	private void initComponents() {
		jLabel1 = new JLabel();
		addItem = new JButton();
		deleteItem = new JButton();
		editItem = new JButton();
		
		setTitle("Edit Menu");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("Edit Menu");
		
		addItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		addItem.setActionCommand("ADD_ITEM");
		addItem.setText("Add Item");
		addItem.addActionListener(this::actionPerformed);
		
		deleteItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		deleteItem.setActionCommand("DELETE_ITEM");
		deleteItem.setText("Delete Item");
		deleteItem.addActionListener(this::actionPerformed);
		
		editItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		editItem.setActionCommand("EDIT_ITEM");
		editItem.setText("Edit Item");
		editItem.addActionListener(this::actionPerformed);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(49, 49, 49)
												.addComponent(jLabel1))
										.addGroup(layout.createSequentialGroup()
												.addGap(233, 233, 233)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(deleteItem, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
														.addComponent(addItem, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
														.addComponent(editItem, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(239, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(27, 27, 27)
								.addComponent(jLabel1)
								.addGap(53, 53, 53)
								.addComponent(addItem, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addGap(37, 37, 37)
								.addComponent(deleteItem, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addGap(35, 35, 35)
								.addComponent(editItem, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(107, Short.MAX_VALUE))
		);
		
		pack();
	}
	
	private void actionPerformed(java.awt.event.ActionEvent evt) {
		String action = evt.getActionCommand();
		switch (action) {
			case "ADD_ITEM":
				new AddItem().setVisible(true);
				break;
			case "DELETE_ITEM":
				new DeleteItem().setVisible(true);
				break;
			case "EDIT_ITEM":
				new EditItem().setVisible(true);
				break;
		}
	}
}
