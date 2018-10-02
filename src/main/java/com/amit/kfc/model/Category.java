package com.amit.kfc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.amit.kfc.model.Cols.*;

public class Category extends BaseModel {
	private int catId;
	private String name;
	
	public int getCatId() {
		return catId;
	}
	
	public void setCatId(int catId) {
		this.catId = catId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void parse(ResultSet resultSet) {
		try {
			this.setCatId(resultSet.getInt(CATEGORY_ID));
			this.setName(resultSet.getString(CATEGORY_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getInitQuery() {
		return "CREATE TABLE IF NOT EXISTS "
				+ CATEGORY_TABLE + "("
				+ CATEGORY_ID + " integer PRIMARY KEY, "
				+ CATEGORY_NAME + " text NOT NULL);";
	}
	
	@Override
	public String getFetchQuery() {
		return "SELECT "
				+ CATEGORY_ID + ", "
				+ CATEGORY_NAME
				+ " FROM "
				+ CATEGORY_TABLE + ";";
	}
	
	@Override
	public PreparedStatement getWriteQuery(Connection connection) throws Exception{
		String query = "INSERT INTO "
				+ CATEGORY_TABLE + " ("
				+ CATEGORY_ID + ", "
				+ CATEGORY_NAME
				+ ") VALUES (? , ?);";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, getCatId());
		statement.setString(2, getName());
		
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
