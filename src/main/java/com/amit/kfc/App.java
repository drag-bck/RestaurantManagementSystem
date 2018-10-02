package com.amit.kfc;

import com.amit.kfc.controller.Models;
import com.amit.kfc.view.EditItem;

import javax.swing.*;

public class App {
	public static void main(String args[]) {
		Models models = Models.getInstance();
		models.init();
		
		SwingUtilities.invokeLater(() -> new EditItem().setVisible(true));
	}
}
