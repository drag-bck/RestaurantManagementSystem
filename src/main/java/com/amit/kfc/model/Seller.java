package com.amit.kfc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.amit.kfc.model.Cols.*;

public class Seller extends BaseModel {
	private int sellerId;
	private String name;
	private float rate;

	public Seller() {

	}
	public Seller(String tokens0, String tokens1){
		name = tokens0;
		rate = Float.parseFloat(tokens1);
	}

	public int getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getRate() {
		return rate;
	}
	
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public void parse(ResultSet resultSet) {
		try {
			this.setSellerId(resultSet.getInt(SELLER_ID));
			this.setName(resultSet.getString(SELLER_NAME));
			this.setRate(resultSet.getFloat(SELLER_RATE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getInitQuery() {
		return "CREATE TABLE IF NOT EXISTS "
				+ SELLER_TABLE + "("
				+ SELLER_ID + " integer PRIMARY KEY, "
				+ SELLER_NAME + " text, "
				+ SELLER_RATE + " real);";
	}
	
	@Override
	public String getFetchQuery() {
		return "SELECT "
				+ SELLER_ID + ", "
				+ SELLER_NAME + ", "
				+ SELLER_RATE
				+ " FROM "
				+ SELLER_TABLE + ";";
	}
	
	@Override
	public PreparedStatement getWriteQuery(Connection connection) throws Exception {
		String query = "INSERT INTO "
				+ SELLER_TABLE + " ("
				+ SELLER_ID + ", "
				+ SELLER_NAME + ", "
				+ SELLER_RATE
				+ ") VALUES (?, ?, ?, ?);";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, getSellerId());
		statement.setString(2, getName());
		statement.setFloat(3, getRate());
		
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
	
	@Override
	public String toString() {
		return getName();
	}
}
