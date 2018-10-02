package com.amit.kfc.view;

/**
 * @author Aman Kumar
 */
public class EditMenu extends javax.swing.JFrame {
	
	public EditMenu() {
		initComponents();
	}
	
	private void initComponents() {
		
		jLabel1 = new javax.swing.JLabel();
		addItem = new javax.swing.JButton();
		deleteItem = new javax.swing.JButton();
		editItem = new javax.swing.JButton();
		
		setTitle("Edit Menu");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("EDIT MENU");
		
		addItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		addItem.setText("ADD ITEM");
		addItem.addActionListener((event) -> actionPerformed(event));
		
		deleteItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		deleteItem.setText("DELETE ITEM");
		deleteItem.addActionListener((event) -> actionPerformed(event));
		
		editItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		editItem.setText("EDIT ITEM");
		editItem.addActionListener((event) -> actionPerformed(event));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(49, 49, 49)
												.addComponent(jLabel1))
										.addGroup(layout.createSequentialGroup()
												.addGap(233, 233, 233)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(deleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(editItem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(239, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(27, 27, 27)
								.addComponent(jLabel1)
								.addGap(53, 53, 53)
								.addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(37, 37, 37)
								.addComponent(deleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(35, 35, 35)
								.addComponent(editItem, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(107, Short.MAX_VALUE))
		);
		
		pack();
	}
	
	private void actionPerformed(java.awt.event.ActionEvent evt) {
		String action = evt.getActionCommand();
		switch (action) {
			case "ADD ITEM":
				new AddItem().setVisible(true);
				break;
			case "DELETE ITEM":
				new DeleteItem().setVisible(true);
				break;
			case "EDIT ITEM":
				new EditItem().setVisible(true);
				break;
		}
	}
	
	private javax.swing.JButton addItem;
	private javax.swing.JButton deleteItem;
	private javax.swing.JButton editItem;
	private javax.swing.JLabel jLabel1;
}
