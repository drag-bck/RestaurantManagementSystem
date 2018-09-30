package com.amit.kfc.model;

import java.sql.ResultSet;

import static com.amit.kfc.model.Cols.*;

public class Seller extends BaseModel {
	private int sellerId;
	private SaleType saleType;
	private String name;
	private float rate;
	
	public int getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public SaleType getSaleType() {
		return saleType;
	}
	
	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
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
			this.setSaleType(SaleType.parse(resultSet.getInt(SELLER_SALE_TYPE)));
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
				+ SELLER_SALE_TYPE + " integer, "
				+ SELLER_RATE + " real);";
	}
	
	@Override
	public String getFetchQuery() {
		return "SELECT "
				+ SELLER_ID + ", "
				+ SELLER_NAME + ", "
				+ SELLER_SALE_TYPE + ", "
				+ SELLER_RATE
				+ " FROM "
				+ SELLER_TABLE + ";";
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
