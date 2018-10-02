package com.amit.kfc.view;

import com.amit.kfc.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Aman Kumar
 */
public class AddUser extends javax.swing.JFrame {
	public AddUser() {
		userController = new UserController();
		initComponents();
	}
	
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		changeUser = new javax.swing.JButton();
		username = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		password = new javax.swing.JPasswordField();
		
		setTitle("ADD USER");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel1.setText("USERNAME");
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("PASSWORD");
		
		changeUser.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		changeUser.setText("ADD");
		
		username.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel3.setText("ADD USER");
		
		password.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(63, 63, 63)
												.addComponent(jLabel3))
										.addGroup(layout.createSequentialGroup()
												.addGap(78, 78, 78)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel1)
														.addComponent(jLabel2))
												.addGap(91, 91, 91)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
														.addComponent(username)))
										.addGroup(layout.createSequentialGroup()
												.addGap(209, 209, 209)
												.addComponent(changeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(68, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel3)
								.addGap(63, 63, 63)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1)
										.addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(63, 63, 63)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2)
										.addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(49, 49, 49)
								.addComponent(changeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(188, Short.MAX_VALUE))
		);
		
		changeUser.addActionListener((event) -> changeUser(event));
		
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
	
	private javax.swing.JButton changeUser;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPasswordField password;
	private javax.swing.JTextField username;
	
	private UserController userController;
}
