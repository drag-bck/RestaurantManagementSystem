package com.amit.kfc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.amit.kfc.model.Cols.*;

public class User extends BaseModel {
	private int userId;
	private String userName;
	private String password;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void parse(ResultSet resultSet) {
		try {
			this.setUserId(resultSet.getInt(USER_ID));
			this.setUserName(resultSet.getString(USER_NAME));
			this.setPassword(resultSet.getString(USER_PASSWORD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getInitQuery() {
		return "CREATE TABLE IF NOT EXISTS "
				+ USER_TABLE + "("
				+ USER_ID + " integer PRIMARY KEY, "
				+ USER_NAME + " text, "
				+ USER_PASSWORD + " text);";
		//Todo: Unique Username
	}
	
	@Override
	public String getFetchQuery() {
		return "SELECT "
				+ USER_ID + ", "
				+ USER_NAME + ", "
				+ USER_PASSWORD
				+ " FROM "
				+ USER_TABLE + ";";
	}
	
	@Override
	public PreparedStatement getWriteQuery(Connection connection) throws Exception {
		String query = "INSERT INTO "
				+ USER_TABLE + " ("
				+ USER_ID + ", "
				+ USER_NAME + ", "
				+ USER_PASSWORD
				+ ") VALUES (?, ?, ?);";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, getUserId());
		statement.setString(2, getUserName());
		statement.setString(3, getPassword());
		
		return statement;
	}
	
	@Override
	public PreparedStatement getUpdateQuery(Connection connection) throws Exception {
		return null;
	}
	
	@Override
	public PreparedStatement getDeleteStatement(Connection connection) throws Exception {
		return null;
	}
}
