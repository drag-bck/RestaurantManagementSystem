package com.amit.kfc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import static com.amit.kfc.model.Cols.*;

public class Order extends BaseModel {
	private int orderId;
	private Date date;
	private String customerName;
	private String customerPhone;
	private int sellerId;
	private double amount;
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}
	
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	public int getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void parse(ResultSet resultSet) {
		try {
			this.setOrderId(resultSet.getInt(ORDER_ID));
			this.setDate(resultSet.getDate(ORDER_DATE));
			this.setCustomerName(resultSet.getString(ORDER_CUSTOMER_NAME));
			this.setCustomerPhone(resultSet.getString(ORDER_CUSTOMER_PHONE));
			this.setSellerId(resultSet.getInt(ORDER_SELLER_ID));
			this.setAmount(resultSet.getDouble(ORDER_AMOUNT));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getInitQuery() {
		return "CREATE TABLE IF NOT EXISTS "
				+ ORDER_TABLE + "("
				+ ORDER_ID + " integer PRIMARY KEY, "
				+ ORDER_DATE + " date, "
				+ ORDER_CUSTOMER_NAME + " text, "
				+ ORDER_CUSTOMER_PHONE + " text, "
				+ ORDER_SELLER_ID + " integer, "
				+ ORDER_AMOUNT + " real);";
	}
	
	@Override
	public String getFetchQuery() {
		return "SELECT "
				+ ORDER_ID + ", "
				+ ORDER_DATE + ", "
				+ ORDER_CUSTOMER_NAME + ", "
				+ ORDER_CUSTOMER_PHONE + ", "
				+ ORDER_SELLER_ID + ", "
				+ ORDER_AMOUNT
				+ " FROM "
				+ ORDER_TABLE + ";";
	}
	
	@Override
	public PreparedStatement getWriteQuery(Connection connection) throws Exception {
		String query = "INSERT INTO "
				+ ORDER_TABLE + " ("
				+ ORDER_ID + ", "
				+ ORDER_DATE + ", "
				+ ORDER_CUSTOMER_NAME + ", "
				+ ORDER_CUSTOMER_PHONE + ", "
				+ ORDER_SELLER_ID + ", "
				+ ORDER_AMOUNT + ", "
				+ ") VALUES (?, ?, ?, ?, ?, ?);";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, getOrderId());
		statement.setDate(2, getDate());
		statement.setString(3, getCustomerName());
		statement.setString(4, getCustomerPhone());
		statement.setInt(5, getSellerId());
		statement.setDouble(6, getAmount());
		
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
		return Integer.toString(getOrderId());
	}
	
	public Vector<String> toStringVector() {
		Vector<String> data = new Vector<>();
		data.add(getOrderId() > 0 ? Integer.toString(getOrderId()) : "");
		data.add(customerName);
		data.add(customerPhone);
		data.add(Double.toString(amount));
		return data;
	}
}
