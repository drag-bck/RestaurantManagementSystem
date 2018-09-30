package com.amit.kfc.model;

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
	public String getWriteQuery() {
		return "INSERT INTO "
				+ CATEGORY_TABLE + " ("
				+ CATEGORY_ID + ", "
				+ CATEGORY_NAME
				+ ") VALUES ("
				+ this.getCatId() + ", "
				+ this.getName()
				+ ");";
	}
	
	@Override
	public String getUpdateQuery() {
		return null;
	}
}
