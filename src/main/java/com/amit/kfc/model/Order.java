package com.amit.kfc.model;

import java.sql.ResultSet;
import java.util.Date;

import static com.amit.kfc.model.Cols.*;

public class Order extends BaseModel {
	private int orderId;
	private Date date;
	private String customerName;
	private String customerPhone;
	private int sellerId;
	private SaleType saleType;
	private float amount;
	
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
	
	public SaleType getSaleType() {
		return saleType;
	}
	
	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public void parse(ResultSet resultSet) {
		try {
			this.setOrderId(resultSet.getInt(ORDER_ID));
			this.setDate(resultSet.getDate(ORDER_DATE));
			this.setCustomerName(resultSet.getString(ORDER_CUSTOMER_NAME));
			this.setCustomerPhone(resultSet.getString(ORDER_CUSTOMER_PHONE));
			this.setSellerId(resultSet.getInt(ORDER_SELLER_ID));
			this.setSaleType(SaleType.getSaleType(resultSet.getInt(ORDER_SALE_TYPE)));
			this.setAmount(resultSet.getFloat(ORDER_AMOUNT));
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
				+ ORDER_SALE_TYPE + " integer, "
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
				+ ORDER_SALE_TYPE + ", "
				+ ORDER_AMOUNT
				+ " FROM "
				+ ORDER_TABLE + ";";
	}
	
	@Override
	public String getWriteQuery() {
		return "INSERT INTO "
				+ ORDER_TABLE + " ("
				+ ORDER_ID + ", "
				+ ORDER_DATE + ", "
				+ ORDER_CUSTOMER_NAME + ", "
				+ ORDER_CUSTOMER_PHONE + ", "
				+ ORDER_SELLER_ID + ", "
				+ ORDER_SALE_TYPE + ", "
				+ ORDER_AMOUNT + ", "
				+ ") VALUES ("
				+ this.getOrderId() + ", "
				+ this.getDate() + ", "
				+ this.getCustomerName() + ", "
				+ this.getCustomerPhone() + ", "
				+ this.getSellerId() + ", "
				+ this.getSaleType() + ", "
				+ this.getAmount()
				+ ");";
	}
	
	@Override
	public String getUpdateQuery() {
		return null;
	}
}
