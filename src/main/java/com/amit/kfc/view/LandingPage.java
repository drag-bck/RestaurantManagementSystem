package com.amit.kfc.view;

import com.amit.kfc.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class LandingPage extends JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JPanel jPanel1;
	private JButton login;
	private JPasswordField password;
	private JTextField username;
	
	private UserController userController;
	
	public LandingPage() {
		userController = new UserController();
		initComponents();
	}
	
	private void initComponents() {
		jPanel1 = new JPanel();
		username = new JTextField();
		login = new JButton();
		password = new JPasswordField();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel1 = new JLabel();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Punjabi Chulha");
		setMinimumSize(new java.awt.Dimension(640, 480));
		
		jPanel1.setLayout(null);
		
		username.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jPanel1.add(username);
		username.setBounds(300, 280, 160, 30);
		
		login.setActionCommand("LOGIN");
		login.setText("Login");
		login.addActionListener(this::loginActionPerformed);
		jPanel1.add(login);
		login.setBounds(240, 380, 140, 50);
		
		password.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		password.setMaximumSize(new java.awt.Dimension(640, 480));
		password.setMinimumSize(new java.awt.Dimension(640, 480));
		password.setPreferredSize(new java.awt.Dimension(640, 40));
		jPanel1.add(password);
		password.setBounds(300, 330, 160, 30);
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(255, 255, 255));
		jLabel2.setText("Login");
		jPanel1.add(jLabel2);
		jLabel2.setBounds(270, 220, 90, 50);
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setText("Username");
		jPanel1.add(jLabel3);
		jLabel3.setBounds(170, 270, 130, 50);
		
		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel4.setForeground(new java.awt.Color(255, 255, 255));
		jLabel4.setText("Password");
		jPanel1.add(jLabel4);
		jLabel4.setBounds(170, 320, 120, 50);
		
		URL resource = ClassLoader.getSystemResource("logo.jpg");
		jLabel1.setIcon(new ImageIcon(resource)); // NOI18N
		jLabel1.setText("jLabel1");
		jPanel1.add(jLabel1);
		jLabel1.setBounds(0, 0, 640, 480);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
		);
		
		pack();
	}
	
	private void loginActionPerformed(ActionEvent event) {
		String user = username.getText();
		String pwd = password.getText();
		try {
			userController.login(user, pwd);
			
			dispose();
			new MenuPage().setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
