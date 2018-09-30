package com.amit.kfc.view;

import com.amit.kfc.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Splash {
	private UserController controller;
	private JTextField textField1;
	private JTextField textField2;
	private JButton button1;
	
	public Splash() {
		controller = new UserController();
		
		button1.addActionListener((ActionEvent event) -> {
			String username = textField1.getText();
			String password = textField2.getText();
			
			try{
				controller.login(username, password);
			}
			catch (Exception e){
			
			}
		});
	}
}
