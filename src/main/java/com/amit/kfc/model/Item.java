package com.amit.kfc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.amit.kfc.model.Cols.*;

public class Item extends BaseModel {
	private int itemId;
	private int catId;
	private String name;
	private float cost;
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
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
	
	public float getCost() {
		return cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public void parse(ResultSet resultSet) {
		try {
			this.setItemId(resultSet.getInt(ITEM_ID));
			this.setCatId(resultSet.getInt(ITEM_CAT));
			this.setName(resultSet.getString(ITEM_NAME));
			this.setCost(resultSet.getFloat(ITEM_COST));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getInitQuery() {
		return "CREATE TABLE IF NOT EXISTS "
				+ ITEM_TABLE + "("
				+ ITEM_ID + " integer PRIMARY KEY, "
				+ ITEM_NAME + " text NOT NULL, "
				+ ITEM_CAT + " integer, "
				+ ITEM_COST + " real);";
	}
	
	@Override
	public String getFetchQuery() {
		return "SELECT "
				+ ITEM_ID + ", "
				+ ITEM_CAT + ", "
				+ ITEM_NAME + ", "
				+ ITEM_COST
				+ " FROM "
				+ ITEM_TABLE + ";";
	}
	
	@Override
	public PreparedStatement getWriteQuery(Connection connection) throws Exception {
		String query = "INSERT INTO "
				+ ITEM_TABLE + " ("
				+ ITEM_ID + ", "
				+ ITEM_CAT + ", "
				+ ITEM_NAME + ", "
				+ ITEM_COST + ", "
				+ ") VALUES (?, ?, ?, ?);";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, getItemId());
		statement.setInt(2, getCatId());
		statement.setString(3, getName());
		statement.setFloat(4, getCost());
		
		return statement;
	}
	
	@Override
	public PreparedStatement getUpdateQuery() throws Exception {
		return null;
	}
}
