package com.amit.kfc.view;

import com.amit.kfc.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;

class AddUser extends JFrame {
	private JButton changeUser;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPasswordField password;
	private JTextField username;
	
	private UserController userController;
	
	AddUser() {
		userController = new UserController();
		initComponents();
	}
	
	private void initComponents() {
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		changeUser = new JButton();
		username = new JTextField();
		jLabel3 = new JLabel();
		password = new JPasswordField();
		
		setTitle("Add User");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel1.setText("Username");
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("Password");
		
		changeUser.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		changeUser.setText("Add");
		
		username.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel3.setText("Add User");
		
		password.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(63, 63, 63)
												.addComponent(jLabel3))
										.addGroup(layout.createSequentialGroup()
												.addGap(78, 78, 78)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(jLabel1)
														.addComponent(jLabel2))
												.addGap(91, 91, 91)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(password, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
														.addComponent(username)))
										.addGroup(layout.createSequentialGroup()
												.addGap(209, 209, 209)
												.addComponent(changeUser, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(68, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel3)
								.addGap(63, 63, 63)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1)
										.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(63, 63, 63)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2)
										.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(49, 49, 49)
								.addComponent(changeUser, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(188, Short.MAX_VALUE))
		);
		
		changeUser.addActionListener(this::changeUser);
		pack();
	}
	
	private void changeUser(ActionEvent event) {
		String user = username.getText();
		String pwd = password.getText();
		
		try {
			userController.addUser(user, pwd);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
