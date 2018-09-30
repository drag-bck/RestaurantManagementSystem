package com.amit.kfc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String dbUrl = "jdbc:sqlite:d://sqlite/db/test.db";
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
