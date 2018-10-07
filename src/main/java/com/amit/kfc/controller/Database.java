package com.amit.kfc.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static final String dbUrl = "jdbc:sqlite:" + System.getProperty("user.home") + "\\kfc.db";
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(dbUrl);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
