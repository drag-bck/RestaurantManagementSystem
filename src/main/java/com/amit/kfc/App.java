package com.amit.kfc;

import com.amit.kfc.controller.Models;
import com.amit.kfc.view.GenerateBill;
import com.amit.kfc.view.LandingPage;
import com.amit.kfc.view.MenuPage;

import javax.swing.*;

public class App {
	public static void main(String args[]) {
		Models models = Models.getInstance();
		models.init();
		
		SwingUtilities.invokeLater(() -> new LandingPage().setVisible(true));
	}
}
