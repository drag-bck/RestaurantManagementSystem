package com.amit.kfc;

import com.amit.kfc.controller.Models;

public class App {
	public static void main(String args[]) {
		Models models = Models.getInstance();
		models.init();
	}
}
