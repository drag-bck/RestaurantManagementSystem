package com.amit.kfc.model;

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
			this.setUserName(resultSet.getString(USER_PASSWORD));
			this.setPassword(resultSet.getString(USER_NAME));
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
	public String getWriteQuery() {
		return null;
	}
	
	@Override
	public String getUpdateQuery() {
		return null;
	}
}
