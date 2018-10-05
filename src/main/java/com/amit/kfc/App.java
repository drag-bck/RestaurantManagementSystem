package com.amit.kfc;

import com.amit.kfc.controller.InitDB;
import com.amit.kfc.controller.Models;
import com.amit.kfc.view.LandingPage;

import javax.swing.*;

public class App {
	public static void main(String args[]) {
		Models models = Models.getInstance();
		models.init();
		
		SwingUtilities.invokeLater(() -> {
			if (models.getCategories().isEmpty()) {
				JOptionPane pane = new JOptionPane("Initializing Database", JOptionPane.INFORMATION_MESSAGE);
				JDialog dialog = pane.createDialog(null, "Title");
				new Thread(() -> {
					InitDB.getInstance().initialise();
					dialog.dispose();
					SwingUtilities.invokeLater(() -> new LandingPage().setVisible(true));
				}).start();
				dialog.setVisible(true);
			} else new LandingPage().setVisible(true);
		});
	}
}
